package com.forcetower.playtime.rep;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.TMDbService;
import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.api.tmdb.Certification;
import com.forcetower.playtime.api.tmdb.GenreResponse;
import com.forcetower.playtime.api.tmdb.TitleCertification;
import com.forcetower.playtime.api.tmdb.TitleImages;
import com.forcetower.playtime.api.tmdb.TitleVideo;
import com.forcetower.playtime.api.tmdb.VideoResults;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.Cast;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.TVSeason;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.model.TitleImage;
import com.forcetower.playtime.db.model.TitleWatch;
import com.forcetower.playtime.db.model.WatchlistItem;
import com.forcetower.playtime.db.relations.TitleWatchlist;
import com.forcetower.playtime.ds.DataSource;
import com.forcetower.playtime.ds.SimilarDataSource;
import com.forcetower.playtime.rep.res.NetworkBoundResource;
import com.forcetower.playtime.utils.DateUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import timber.log.Timber;

import static com.forcetower.playtime.utils.StringUtils.validString;

@Singleton
public class TitlesRepository {
    private final PlayDatabase database;
    private final TMDbService tmdbService;
    private final PlayService service;
    private final AppExecutors executors;

    @Inject
    public TitlesRepository(PlayDatabase database, TMDbService tmdbService, PlayService service, AppExecutors executors) {
        this.database = database;
        this.tmdbService = tmdbService;
        this.service = service;
        this.executors = executors;
    }

    public LiveData<Resource<List<Genre>>> loadMovieGenres() {
        return new NetworkBoundResource<List<Genre>, GenreResponse> (executors) {

            @Override
            protected void saveCallResult(@NonNull GenreResponse item) {
                Timber.d("Movies Genres: " + item.getGenres());
                database.genresDao().insert(item.getGenres());
            }

            @Override
            protected boolean shouldFetch(@Nullable List<Genre> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<Genre>> loadFromDb() {
                return database.genresDao().loadAll();
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<GenreResponse>> createCall() {
                return tmdbService.getMoviesGenres();
            }
        }.asLiveData();
    }

    public PagedList<Title> loadMovies() {
        DataSource dataSource = new DataSource(0, service, database, tmdbService, executors);
        return getTitles(dataSource);
    }

    public PagedList<Title> loadSeries() {
        DataSource dataSource = new DataSource(1, service, database, tmdbService, executors);
        return getTitles(dataSource);
    }

    public PagedList<Title> loadSimilarMovies(long id) {
        SimilarDataSource dataSource = new SimilarDataSource(0, id, database, tmdbService, executors);
        return getTitles(dataSource);
    }

    public PagedList<Title> loadSimilarSeries(long id) {
        SimilarDataSource dataSource = new SimilarDataSource(1, id, database, tmdbService, executors);
        return getTitles(dataSource);
    }

    private PagedList<Title> getTitles(androidx.paging.DataSource<Integer, Title> dataSource) {
        PagedList.Config config = new PagedList.Config.Builder()
                .setPageSize(20)
                .setEnablePlaceholders(true)
                .setInitialLoadSizeHint(20)
                .build();

        return new PagedList.Builder<>(dataSource, config)
                .setNotifyExecutor(executors.mainThread())
                .setFetchExecutor(executors.paging())
                .build();
    }

    public LiveData<Resource<Title>> getTitleDetails(long titleId, boolean isMovie) {
        return new NetworkBoundResource<Title, Title>(executors) {
            @Override
            protected void saveCallResult(@NonNull Title item) {
                Timber.d("Fetched Item: " + item);
                item.setMovie(isMovie);
                String release = item.getReleaseDate();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = format.parse(release);
                    Calendar calendar = Calendar.getInstance();
                    calendar.setTime(date);
                    Timber.d("Date: " + date);
                    Timber.d("Release: " + release);
                    String parsed = (calendar.get(Calendar.DAY_OF_MONTH) + " de " +
                            DateUtils.monthFromInt(calendar.get(Calendar.MONTH) + 1) + " de " +
                            calendar.get(Calendar.YEAR));
                    Timber.d("Parsed Release Date: " + parsed);
                    item.setReleaseDate(parsed);
                } catch (Exception e) {
                    Timber.d("Exception raised on dare parse");
                    e.printStackTrace();
                }
                setGenres(item);
                VideoResults videos = item.getVideos();
                if (videos != null) {
                    Timber.d("Videos not null");
                    for (TitleVideo video : videos.getResults()) {
                        if (video.getSite().equalsIgnoreCase("youtube")) {
                            item.setTrailer("https://www.youtube.com/watch?v=" + video.getKey());
                            Timber.d("Youtube video found");
                            break;
                        }
                    }
                }

                List<Integer> episodeRunTime = item.getEpisodeRunTime();
                if (episodeRunTime != null && !episodeRunTime.isEmpty() && !isMovie) {
                    item.setRuntime(episodeRunTime.get(0));
                    Timber.d("Runtime match condition");
                }

                database.titleGenreDao().insertSingleTitle(item);

                try {
                    List<Cast> cast = item.getCredits().getCast();
                    for (Cast c : cast) c.setTitleId(item.getUid());
                    database.castDao().insert(cast);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (!isMovie) {
                    database.tvSeasonDao().insertTVSeasons(item);
                }
            }

            @Override
            protected boolean shouldFetch(@Nullable Title data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<Title> loadFromDb() {
                return database.titleDao().getTitleById(titleId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<Title>> createCall() {
                if (isMovie) return tmdbService.getMovieDetails(titleId, "videos,credits");
                else         return tmdbService.getSeriesDetails(titleId, "videos,credits");
            }
        }.asLiveData();
    }

    private void setGenres(Title title) {
        List<Genre> genres = title.getGenreList();
        StringBuilder builder = new StringBuilder();
        if (genres != null) {
            int size = genres.size();
            for (int i = 0; i < (size > 3 ? 3 : size); i++) {
                if (i == 0) {
                    builder.append(genres.get(i).getName());
                } else {
                    builder.append(" / ").append(genres.get(i));
                }
            }
        }

        String string = builder.toString();
        title.setGenres(string);
    }

    public LiveData<Title> getTitleDetailsFromDatabase(long titleId, boolean isMovie) {
        return database.titleDao().getTitleById(titleId);
    }

    public LiveData<Resource<Title>> getTitleRating(long titleId, boolean isMovie) {
        return new NetworkBoundResource<Title, TitleCertification>(executors) {
            @Override
            protected void saveCallResult(@NonNull TitleCertification item) {
                String companion = "";
                boolean brazil = false;
                boolean inBrazil;
                for (Certification certification : item.getResults()) {
                    List<Certification.ReleaseDate> releaseDates = certification.getReleaseDates();
                    if (certification.getIso().equalsIgnoreCase("br")) {
                        Timber.d("BR Cert found");
                    }

                    inBrazil = certification.getIso().equalsIgnoreCase("br");

                    if (isMovie) {
                        for (Certification.ReleaseDate releaseDate : releaseDates) {
                            if (validString(releaseDate.certification)) {
                                if (!brazil) {
                                    companion = releaseDate.certification;
                                    if (inBrazil) brazil = true;
                                    break;
                                }
                            }
                        }
                    } else {
                        companion = certification.getRating();
                        if (inBrazil)
                            break;
                    }
                }

                try {
                    Integer.parseInt(companion);
                    companion += " anos";
                } catch (Exception ignored){}
                database.titleDao().setClassification(titleId, companion);
            }

            @Override
            protected boolean shouldFetch(@Nullable Title data) {
                return data == null || data.getClassification() == null
                        || data.getClassification().trim().isEmpty();
            }

            @NonNull
            @Override
            protected LiveData<Title> loadFromDb() {
                return database.titleDao().getTitleById(titleId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<TitleCertification>> createCall() {
                if (isMovie) return tmdbService.getMovieRating(titleId);
                return tmdbService.getSeriesRating(titleId);
            }
        }.asLiveData();
    }

    public LiveData<List<Cast>> getCast(long titleId) {
        return database.castDao().getTitleCast(titleId);
    }

    public LiveData<List<TitleWatchlist>> getWatchlist() {
        return database.watchlistItemDao().getWatchlist();
    }

    public void markAsWatched(long titleId, int watchlistId, boolean isMovie) {
        executors.diskIO().execute(() -> {
            database.watchlistItemDao().deleteById(watchlistId);
            database.titleWatchDao().insert(new TitleWatch(System.currentTimeMillis(), titleId, isMovie));
        });
    }

    public LiveData<List<TVSeason>> getSeasons(long titleId) {
        return database.tvSeasonDao().getSeasons(titleId);
    }

    public void markToWatchLater(long titleId, boolean isMovie) {
        database.titleWatchDao().deleteIfExisting(titleId, isMovie);
        database.watchlistItemDao().insert(new WatchlistItem(titleId, System.currentTimeMillis()));
    }

    public void markAsWatched(long titleId, boolean isMovie) {
        database.watchlistItemDao().deleteIfExisting(titleId);
        database.titleWatchDao().insert(new TitleWatch(System.currentTimeMillis(), titleId, isMovie));
    }

    public LiveData<Resource<List<TitleImage>>> getTitleImages(long titleId, boolean isMovie) {
        return new NetworkBoundResource<List<TitleImage>, TitleImages>(executors) {
            @Override
            protected void saveCallResult(@NonNull TitleImages item) {
                List<TitleImage> mergedList = item.getMergedList();
                for (TitleImage im : mergedList) im.setTitleId(titleId);
                database.titleImageDao().insert(mergedList);
            }

            @Override
            protected boolean shouldFetch(@Nullable List<TitleImage> data) {
                return true;
            }

            @NonNull
            @Override
            protected LiveData<List<TitleImage>> loadFromDb() {
                return database.titleImageDao().getTitleImages(titleId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<TitleImages>> createCall() {
                if (isMovie) return tmdbService.getMovieImages(titleId, "null");
                else return tmdbService.getSeriesImages(titleId, "null");
            }
        }.asLiveData();
    }
}
