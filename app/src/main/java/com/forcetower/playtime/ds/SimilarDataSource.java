package com.forcetower.playtime.ds;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
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

public class SimilarDataSource extends PageKeyedDataSource<Integer, Title> {
    private final int source;
    private final long titleId;
    private final PlayDatabase database;
    private final TMDbService tmService;
    private final AppExecutors executors;
    private Hashtable<Integer, String> genres;

    public SimilarDataSource(int source, long titleId, PlayDatabase database, TMDbService tmService, AppExecutors executors) {
        this.source = source;
        this.database = database;
        this.tmService = tmService;
        this.executors = executors;
        this.titleId = titleId;
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

                if (source == 0) for (Title title : titles) title.setMovie(true);
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
                if (source == 0) for (Title title : titles) title.setMovie(true);
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

                if (source == 0) for (Title title : titles) title.setMovie(true);
                setGenres(titles);

                executors.diskIO().execute(() -> database.titleGenreDao().insertTitleAndGenres(titles));
                callback.onResult(titles, page + 1);
            }
        });
    }

    @Nullable
    private PopularResult loadPage(int page) {
        Call<PopularResult> call;
        if (source == 0) call = tmService.getSimilarMovies(titleId, page);
        else call = tmService.getSimilarSeries(titleId, page);

        try {
            Response<PopularResult> execute = call.execute();
            if (execute.isSuccessful())
                return execute.body();

            Timber.e("Error loading similar page " + page + " code: " + execute.code());
            return null;
        } catch (IOException e) {
            Timber.e("Error Loading Page");
            e.printStackTrace();
        }
        return null;
    }
}
