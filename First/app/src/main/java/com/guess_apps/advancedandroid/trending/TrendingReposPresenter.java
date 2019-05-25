package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.data.RepoRepository;
import com.guess_apps.advancedandroid.data.RepoRequester;
import com.guess_apps.advancedandroid.di.ForScreen;
import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.DisposableManager;
import com.guess_apps.advancedandroid.model.Repo;
import com.guess_apps.advancedandroid.ui.ScreenNavigator;
import com.tuesday_apps.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;

@ScreenScope
class TrendingReposPresenter{

    private final TrendingReposViewModel viewModel;
    private final RepoRepository repoRepository;
    private final ScreenNavigator screenNavigator;
    private DisposableManager disposableManager;
    private RecyclerDataSource dataSource;

    @Inject
    TrendingReposPresenter(
            TrendingReposViewModel viewModel,
            RepoRepository repoRepository,
            ScreenNavigator screenNavigator,
            @ForScreen DisposableManager disposableManager,
            RecyclerDataSource dataSource) {
        this.viewModel = viewModel;
        this.repoRepository = repoRepository;
        this.screenNavigator = screenNavigator;
        this.disposableManager = disposableManager;
        this.dataSource = dataSource;
        loadRepos();
    }

    private void loadRepos() {
        disposableManager.add(repoRepository.getTrendingRepos()
                .doOnSubscribe(__ -> viewModel.loadingUpdated().accept(true))
                .doOnEvent((d, t) -> viewModel.loadingUpdated().accept(false))
                .doOnSuccess(__ -> viewModel.reposUpdated().run())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(dataSource::setData, viewModel.onError()));
    }

    public void onRepoClicked(Repo repo) {
        screenNavigator.goToRepoDetails(repo.owner().login(), repo.name());
    }
}
