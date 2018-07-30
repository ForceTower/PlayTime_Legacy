package com.forcetower.playtime.vm;

import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.rep.MovieRepository;
import com.forcetower.playtime.rep.SeriesRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class TitleViewModel extends ViewModel {
    private final MovieRepository movieRepository;
    private final SeriesRepository seriesRepository;

    @Inject
    public TitleViewModel(MovieRepository movieRepository, SeriesRepository seriesRepository) {
        this.movieRepository = movieRepository;
        this.seriesRepository = seriesRepository;
    }

    public LiveData<PagedList<Title>> getAllMovies() {
        return null;
    }

    public LiveData<Resource<List<Genre>>> loadMovieGenres() {
        return movieRepository.loadMovieGenres();
    }

    public LiveData<Resource<List<Genre>>> loadSeriesGenres() {
        return seriesRepository.loadSeriesGenres();
    }
}
