package com.forcetower.playtime.rep;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.TMDbService;
import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.api.tmdb.GenreResponse;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.ds.DataSource;
import com.forcetower.playtime.rep.res.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;
import timber.log.Timber;

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

    private PagedList<Title> getTitles(DataSource dataSource) {
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
}
