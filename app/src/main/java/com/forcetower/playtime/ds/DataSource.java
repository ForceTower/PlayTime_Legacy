package com.forcetower.playtime.ds;

import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.adapter.ApiResponse;
import com.forcetower.playtime.db.model.Title;

import java.io.IOException;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.paging.PageKeyedDataSource;
import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;

public class DataSource extends PageKeyedDataSource<Integer, Title> {
    private final PlayService service;

    public DataSource(PlayService service) {
        this.service = service;
    }

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Title> callback) {
        Call<List<Title>> call = service.getPopularMovies(1);
        try {
            Response<List<Title>> response = call.execute();
            if (response.isSuccessful()) {
                List<Title> body = response.body();

            } else {
                Timber.e("Load failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Title> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Title> callback) {

    }
}
