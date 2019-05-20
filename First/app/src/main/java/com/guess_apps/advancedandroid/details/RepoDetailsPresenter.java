package com.guess_apps.advancedandroid.details;

import com.guess_apps.advancedandroid.data.RepoRepository;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.model.Contributor;
import com.guess_apps.advancedandroid.model.Repo;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.functions.Consumer;

@ScreenScope
public class RepoDetailsPresenter {

    @Inject
    RepoDetailsPresenter(
            @Named("repo_owner") String repoOwner,
            @Named("repo_name") String repoName,
            RepoRepository repoRepository,
            RepoDetailsViewModel viewModel) {
        repoRepository.getRepo(repoOwner, repoName)
               .doOnSuccess((Consumer<? super Repo>) viewModel.processRepo())
               .doOnError((Consumer<? super Throwable>) viewModel.detailsError())
               .flatMap(repo -> repoRepository.getContributors(repo.contributorsUrl())
                .doOnError((Consumer<? super Throwable>) viewModel.contributorsError()))
                .subscribe((Consumer<? super List<Contributor>>) viewModel.processContributors(), throwable -> {
                    // We handle logging in the view model
                });
    }
}
