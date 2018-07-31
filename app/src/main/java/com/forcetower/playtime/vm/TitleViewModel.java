package com.forcetower.playtime.vm;

import com.forcetower.playtime.api.adapter.Resource;
import com.forcetower.playtime.db.model.Cast;
import com.forcetower.playtime.db.model.Genre;
import com.forcetower.playtime.db.model.TVSeason;
import com.forcetower.playtime.db.model.Title;
import com.forcetower.playtime.db.relations.TitleWatchlist;
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

    public LiveData<Resource<Title>> getTitle(long titleId, boolean isMovie) {
        return titlesRepository.getTitleDetails(titleId, isMovie);
    }

    public LiveData<Title> getTitleFromDatabase(long titleId, boolean isMovie) {
        return titlesRepository.getTitleDetailsFromDatabase(titleId, isMovie);
    }

    public LiveData<Resource<Title>> getTitleRating(long titleId, boolean isMovie) {
        return titlesRepository.getTitleRating(titleId, isMovie);
    }

    public LiveData<List<Cast>> getCast(long titleId) {
        return titlesRepository.getCast(titleId);
    }

    public LiveData<List<TitleWatchlist>> getWatchlist() {
        return titlesRepository.getWatchlist();
    }

    public void markAsWatched(TitleWatchlist item) {
        titlesRepository.markAsWatched(item.getTitle().getUid(), item.getId(), item.getTitle().isMovie());
    }

    public PagedList<Title> getMoviesAlike(long titleId, boolean isMovie) {
        return isMovie
                ? titlesRepository.loadSimilarMovies(titleId)
                : titlesRepository.loadSimilarSeries(titleId);
    }

    public LiveData<List<TVSeason>> getSeasons(long titleId) {
        return titlesRepository.getSeasons(titleId);
    }
}
