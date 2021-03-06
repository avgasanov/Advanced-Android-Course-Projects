package com.guess_apps.advancedandroid.details;

import com.guess_apps.advancedandroid.data.RepoRepository;
import com.guess_apps.advancedandroid.di.ForScreen;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.DisposableManager;
import com.guess_apps.advancedandroid.model.Contributor;
import com.guess_apps.advancedandroid.model.Repo;
import com.tuesday_apps.poweradapter.adapter.RecyclerDataSource;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

@ScreenScope
public class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource contributorDataSource) {
        disposableManager.add(repoRepository.getRepo(repoOwner, repoName)
               .doOnSuccess(viewModel.processRepo())
               .doOnError(viewModel.detailsError())
               .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                .doOnError(viewModel.contributorsError()))
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSuccess(contributorDataSource::setData)
                .subscribe(viewModel.contributorsLoaded(), throwable -> {
                    // We handle logging in the view model
                }));
    }
}
