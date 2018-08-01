package com.forcetower.playtime.ds;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.TMDbService;
import com.forcetower.playtime.api.tmdb.PopularResult;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.utils.DateUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

public class DataSource extends PageKeyedDataSource<Integer, Title> {
    private final int source;
    private final PlayService service;
    private final PlayDatabase database;
    private final TMDbService tmService;
    private final AppExecutors executors;
    private Hashtable<Integer, String> genres;

    public DataSource(int source, PlayService service, PlayDatabase database, TMDbService tmService, AppExecutors executors) {
        this.source = source;
        this.service = service;
        this.database = database;
        this.tmService = tmService;
        this.executors = executors;
    }

    private void checkGenres() {
        if (genres == null) {
            this.genres = new Hashtable<>();
            List<Genre> genres = database.genresDao().loadAllDirect();
            for (Genre genre : genres) this.genres.put(genre.getUid(), genre.getName());
        }
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Title> callback) {
        executors.networkIO().execute(() -> {
            checkGenres();
            Call<PopularResult> call;
            if (source == 1)
                call = tmService.getSeriesPopular(1);
            else
                call = tmService.getMoviesPopular(1);

            try {
                Response<PopularResult> response = call.execute();
                if (response.isSuccessful()) {
                    PopularResult result = response.body();
                    if (result != null) {
                        int totalPages = result.getTotalPages();
                        int totalResults = result.getTotalResults();
                        List<Title> titles = result.getResults();

                        prepareDate(titles);

                        Timber.d("Total Pages: " + totalPages);
                        Timber.d("Results: " + titles);

                        if (source == 0) for (Title title : titles) title.setMovie(true);
                        setGenres(titles);

                        executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                        callback.onResult(titles, 0, totalResults, null, 2);
                    } else {
                        Timber.d("The result is null");
                    }
                } else {
                    Timber.e("Load failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Title> callback) {
        Timber.d("Load Before: " + params.key);
        executors.networkIO().execute(() -> {
            checkGenres();
            Call<PopularResult> call;
            if (source == 1)
                call = tmService.getSeriesPopular(params.key);
            else
                call = tmService.getMoviesPopular(params.key);
            try {
                Response<PopularResult> response = call.execute();
                if (response.isSuccessful()) {
                    PopularResult result = response.body();
                    if (result != null) {
                        int page = result.getPage();
                        int totalPages = result.getTotalPages();
                        List<Title> titles = result.getResults();

                        prepareDate(titles);

                        Timber.d("Total Pages: " + totalPages);
                        Timber.d("Results: " + titles);
                        if (source == 0) for (Title title : titles) title.setMovie(true);
                        setGenres(titles);

                        executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                        callback.onResult(titles, page - 1);
                    } else {
                        Timber.d("The result is null");
                    }
                } else {
                    Timber.e("Load failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Title> callback) {
        Timber.d("Load After: " + params.key);
        executors.networkIO().execute(() -> {
            checkGenres();
            Call<PopularResult> call;
            if (source == 1)
                call = tmService.getSeriesPopular(params.key);
            else
                call = tmService.getMoviesPopular(params.key);
            try {
                Response<PopularResult> response = call.execute();
                if (response.isSuccessful()) {
                    PopularResult result = response.body();
                    if (result != null) {
                        int page = result.getPage();
                        int totalPages = result.getTotalPages();
                        List<Title> titles = result.getResults();

                        prepareDate(titles);

                        Timber.d("Total Pages: " + totalPages);
                        Timber.d("Results: " + titles);

                        if (source == 0) for (Title title : titles) title.setMovie(true);
                        setGenres(titles);

                        executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                        callback.onResult(titles, page + 1);
                    } else {
                        Timber.d("The result is null");
                    }
                } else {
                    Timber.e("Load failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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

    public static void prepareDate(List<Title> titles) {
        for (Title title : titles) {
            String release = title.getReleaseDate();
            if (release == null) continue;

            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(release);
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                String parsed = (calendar.get(Calendar.DAY_OF_MONTH) + " de " +
                        DateUtils.monthFromInt(calendar.get(Calendar.MONTH) + 1) + " de " +
                        calendar.get(Calendar.YEAR));
                title.setReleaseDate(parsed);
            } catch (Exception e) {
                Timber.d("Exception raised on dare parse: " + e.getMessage());
            }
        }
    }
}
