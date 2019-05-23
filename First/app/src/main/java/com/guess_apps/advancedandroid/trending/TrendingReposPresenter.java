package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.data.RepoRepository;
import com.guess_apps.advancedandroid.data.RepoRequester;
import com.guess_apps.advancedandroid.di.ForScreen;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.DisposableManager;
import com.guess_apps.advancedandroid.model.Repo;
import com.guess_apps.advancedandroid.ui.ScreenNavigator;

import javax.inject.Inject;

@ScreenScope
class TrendingReposPresenter implements RepoAdapter.RepoClickedListener{

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private final ScreenNavigator screenNavigator;
    private DisposableManager disposableManager;

    @Inject
    TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator,
            @ForScreen DisposableManager disposableManager) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        loadRepos();
    }

    private void loadRepos() {
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .subscribe(viewModel.reposUpdated(), viewModel.onError()));
    }

    @Override
    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
