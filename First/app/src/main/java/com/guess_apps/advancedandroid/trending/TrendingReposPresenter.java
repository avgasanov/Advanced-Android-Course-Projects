package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.data.RepoRequester;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.model.Repo;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener{

    private final TrendingReposViewModel viewModel;
    private final RepoRequester repoRequester;

    @Inject
    TrendingReposPresenter(TrendingReposViewModel viewModel, RepoRequester repoRequester) {
        this.viewModel = viewModel;
        this.repoRequester = repoRequester;
        loadRepos();
    }

    private void loadRepos() {
        repoRequester.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError());
    }

    @Override
    public void onRepoClicked(Repo repo) {

    }
}
