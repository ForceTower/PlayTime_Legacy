package com.forcetower.playtime.rep;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.TMDbService;
import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.api.tmdb.GenreResponse;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.rep.res.NetworkBoundResource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import timber.log.Timber;

@Singleton
public class MovieRepository {
    private final PlayDatabase database;
    private final TMDbService tmdbService;
    private final AppExecutors executors;

    @Inject
    public MovieRepository(PlayDatabase database, TMDbService tmdbService, AppExecutors executors) {
        this.database = database;
        this.tmdbService = tmdbService;
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
                return tmdbService.getMovieGenres();
            }
        }.asLiveData();
    }
}
