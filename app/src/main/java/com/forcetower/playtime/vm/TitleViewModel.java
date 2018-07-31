package com.forcetower.playtime.vm;

import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.rep.TitlesRepository;
import com.forcetower.playtime.rep.SeriesRepository;

import java.util.List;

import javax.inject.Inject;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

public class TitleViewModel extends ViewModel {
    private final TitlesRepository titlesRepository;
    private final SeriesRepository seriesRepository;

    @Inject
    public TitleViewModel(TitlesRepository titlesRepository, SeriesRepository seriesRepository) {
        this.titlesRepository = titlesRepository;
        this.seriesRepository = seriesRepository;
    }

    public LiveData<PagedList<Title>> getAllMovies() {
        return null;
    }

    public LiveData<Resource<List<Genre>>> loadMovieGenres() {
        return titlesRepository.loadMovieGenres();
    }

    public LiveData<Resource<List<Genre>>> loadSeriesGenres() {
        return seriesRepository.loadSeriesGenres();
    }

    public PagedList<Title> getMovies() {
        return titlesRepository.loadMovies();
    }

    public PagedList<Title> getSeries() {
        return titlesRepository.loadSeries();
    }
}
