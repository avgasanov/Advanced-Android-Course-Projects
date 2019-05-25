package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.model.Repo;
import com.jakewharton.rxrelay2.BehaviorRelay;
import com.guess_apps.advancedandroid.R;
import java.util.List;


import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

@ScreenScope
class TrendingReposViewModel {

    private final BehaviorRelay<Integer> errorRelay = BehaviorRelay.create();
    private final BehaviorRelay<Boolean> loadingRelay = BehaviorRelay.create();

    @Inject
    TrendingReposViewModel() {

    }

    Observable<Boolean> loading() {
        return loadingRelay;
    }



    Observable<Integer> error() {
        return errorRelay;
    }

    Consumer<Boolean> loadingUpdated() {
        return loadingRelay;
    }

    Action reposUpdated() {
        return () -> errorRelay.accept(-1);
    }

    Consumer<Throwable> onError() {
        return throwable -> {
            Timber.e(throwable, "Error loading repos");
            errorRelay.accept(R.string.api_error_repos);
        };
    }
}
