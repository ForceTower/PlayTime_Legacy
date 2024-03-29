package com.forcetower.playtime.ds;

import com.forcetower.playtime.AppExecutors;
import com.forcetower.playtime.api.PlayService;
import com.forcetower.playtime.api.TMDbService;
import com.forcetower.playtime.db.PlayDatabase;
import com.forcetower.playtime.db.model.Title;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;

public class ItemBoundaryCallback extends PagedList.BoundaryCallback<Title> {
    private final PlayDatabase database;
    private final PlayService service;
    private final TMDbService tmdbService;
    private final AppExecutors executors;
    private boolean endLoading = false;

    public ItemBoundaryCallback(PlayDatabase database, PlayService service, TMDbService tmdbService, AppExecutors executors) {
        this.database = database;
        this.service = service;
        this.tmdbService = tmdbService;
        this.executors = executors;
    }

    @Override
    public void onItemAtFrontLoaded(@NonNull Title itemAtFront) {
        super.onItemAtFrontLoaded(itemAtFront);
        if (!endLoading) {
            endLoading = true;
            executors.networkIO().execute(() -> {

            });
        }
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Title itemAtEnd) {
        super.onItemAtEndLoaded(itemAtEnd);
    }
}
