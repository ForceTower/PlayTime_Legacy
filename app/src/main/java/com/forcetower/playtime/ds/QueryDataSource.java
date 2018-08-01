package com.forcetower.playtime.ds;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.TMDbService;
import com.forcetower.playtime.api.tmdb.PopularResult;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

import static com.forcetower.playtime.ds.DataSource.prepareDate;

public class QueryDataSource extends PageKeyedDataSource<Integer, Title> {
    private int type;
    private String queryGenres;
    private String nakedYear;
    private String year;
    private int yearType;
    private final PlayDatabase database;
    private final TMDbService tmService;
    private final AppExecutors executors;
    private final String query;
    private Hashtable<Integer, String> genres;

    public QueryDataSource(PlayDatabase database, TMDbService tmService, AppExecutors executors, String query) {
        this.database = database;
        this.tmService = tmService;
        this.executors = executors;
        this.query = query;
    }

    public void setType(int type, String genres, String year, int yearType) {
        this.type = type;
        queryGenres = genres;
        this.nakedYear = year;
        this.year = year + "-01-01";
        this.yearType = yearType;
    }

    private void checkGenres() {
        if (genres == null) {
            this.genres = new Hashtable<>();
            List<Genre> genres = database.genresDao().loadAllDirect();
            for (Genre genre : genres) this.genres.put(genre.getUid(), genre.getName());
        }
    }

    private void setGenres(List<Title> titles) {
        for (Title title : titles) {
            List<Integer> genreIds = title.getGenreIds();
            StringBuilder builder = new StringBuilder();
            if (genreIds != null) {
                for (int i = 0; i < genreIds.size(); i++) {
                    if (i == 0) {
                        builder.append(genres.get(genreIds.get(i)));
                    } else {
                        builder.append(" / ").append(genres.get(genreIds.get(i)));
                    }
                }
            }

            String string = builder.toString();
            title.setGenres(string);
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Title> callback) {
        executors.networkIO().execute(() -> {
            checkGenres();
            PopularResult result = loadPage(1);
            if (result != null) {
                int totalPages = result.getTotalPages();
                int totalResults = result.getTotalResults();
                List<Title> titles = result.getResults();
                prepareDate(titles);
                Timber.d("Total Pages: " + totalPages);
                Timber.d("Results: " + titles);

               for (Title title : titles) title.setMovie(true);
                setGenres(titles);

                executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                callback.onResult(titles, 0, totalResults, null, 2);
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Title> callback) {
        executors.networkIO().execute(() -> {
            checkGenres();
            PopularResult result = loadPage(params.key);
            if (result != null) {
                int page = result.getPage();
                int totalPages = result.getTotalPages();
                List<Title> titles = result.getResults();
                prepareDate(titles);
                Timber.d("Total Pages: " + totalPages);
                Timber.d("Results: " + titles);
                for (Title title : titles) title.setMovie(true);
                setGenres(titles);

                executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                callback.onResult(titles, page - 1);
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Title> callback) {
        executors.networkIO().execute(() -> {
            checkGenres();
            PopularResult result = loadPage(params.key);
            if (result != null) {
                int page = result.getPage();
                int totalPages = result.getTotalPages();
                List<Title> titles = result.getResults();
                prepareDate(titles);
                Timber.d("Total Pages: " + totalPages);
                Timber.d("Results: " + titles);

                for (Title title : titles) title.setMovie(true);
                setGenres(titles);

                executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                callback.onResult(titles, page + 1);
            }
        });
    }

    @Nullable
    private PopularResult loadPage(int key) {
        Call<PopularResult> call = null;
        switch (type) {
            case 0:
                if (yearType == 1)
                    call = tmService.discoverMoviesGenYearEq(key, queryGenres, year);
                else if (yearType == 2)
                    call = tmService.discoverMoviesGenYearGt(key, queryGenres, year);
                else
                    call = tmService.discoverMoviesGenYearLt(key, queryGenres, year);
                break;
            case 1:
                call = tmService.discoverMoviesGen(key, queryGenres);
                break;
            case 2:
                if (yearType == 1)
                    call = tmService.discoverMoviesYearEq(key, year);
                else if (yearType == 2)
                    call = tmService.discoverMoviesYearGt(key, year);
                else
                    call = tmService.discoverMoviesYearLt(key, year);
                break;
            case 3:
                call = tmService.discoverMovies(key);
                break;
            case 4:
                call = tmService.searchMovieWithYear(key, query, nakedYear);
                break;
            case 5:
                call = tmService.searchMovie(key, query);
                break;
        }

        if (call == null) {
            Timber.d("Failed");
            return null;
        } else {
            try {
                Response<PopularResult> execute = call.execute();
                if (execute.isSuccessful()) {
                    return execute.body();
                } else {
                    Timber.d("Failed execute: " + execute.code());
                    Timber.d("Failed execute: " + execute.errorBody().string());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
