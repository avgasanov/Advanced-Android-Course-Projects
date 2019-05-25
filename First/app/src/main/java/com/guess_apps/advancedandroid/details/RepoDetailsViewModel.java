package com.guess_apps.advancedandroid.details;

import com.guess_apps.advancedandroid.R;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.model.Contributor;
import com.guess_apps.advancedandroid.model.Repo;
import com.jakewharton.rxrelay2.BehaviorRelay;


import org.threeten.bp.format.DateTimeFormatter;

import java.util.List;
import io.reactivex.functions.Consumer;

import javax.inject.Inject;

import io.reactivex.Observable;
import timber.log.Timber;

@ScreenScope
public class RepoDetailsViewModel {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd, yyyy");

    private final BehaviorRelay<RepoDetailsState> detailsStateRelay = BehaviorRelay.create();
    private final BehaviorRelay<ContributorState> contributorStateRelay = BehaviorRelay.create();

    @Inject
    public RepoDetailsViewModel() {
        detailsStateRelay.accept(RepoDetailsState.builder().loading(true).build());
        contributorStateRelay.accept(ContributorState.builder().loading(true).build());
    }

    Observable<RepoDetailsState> details() {
        return detailsStateRelay;
    }

    Observable<ContributorState> contributors() {
        return contributorStateRelay;
    }

    Consumer<Repo> processRepo() {
        return repo -> {
          detailsStateRelay.accept(
                  RepoDetailsState.builder()
                        .loading(false)
                        .name(repo.name())
                        .description(repo.description())
                        .createdDate(repo.createdDate().format(DATE_FORMATTER))
                        .updatedDate(repo.updatedDate().format(DATE_FORMATTER))
                        .build()
          );
        };
    }

    Consumer<Object> contributorsLoaded() {
        return __ -> {
            contributorStateRelay.accept(
                    ContributorState.builder()
                    .loading(false)
                    .build()
            );
        };
    }

    Consumer<Throwable> detailsError() {
        return throwable -> {
            Timber.e(throwable, "Error loading repo details");
            detailsStateRelay.accept(
                    RepoDetailsState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_single_repo)
                        .build()
            );
        };
    }

    Consumer<Throwable> contributorsError() {
        return throwable -> {
            Timber.e(throwable, "Error loading contributors");
            contributorStateRelay.accept(
                    ContributorState.builder()
                        .loading(false)
                        .errorRes(R.string.api_error_contributors)
                        .build()
            );
        };
    }
}
