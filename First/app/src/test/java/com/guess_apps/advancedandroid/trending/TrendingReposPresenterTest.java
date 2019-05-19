package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.data.RepoRepository;
import com.guess_apps.advancedandroid.data.RepoRequester;
import com.guess_apps.advancedandroid.data.TrendingReposResponse;
import com.guess_apps.advancedandroid.model.Repo;
import com.guess_apps.advancedandroid.testutils.TestUtils;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class TrendingReposPresenterTest {

    @Mock
    RepoRepository repoRepository;
    @Mock TrendingReposViewModel viewModel;
    @Mock Consumer<Throwable> onErrorConsumer;
    @Mock Consumer<List<Repo>> onSuccessConsumer;
    @Mock Consumer<Boolean> loadingConsumer;

    TrendingReposPresenter trendingReposPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        when(viewModel.loadingUpdated()).thenReturn(loadingConsumer);
        when(viewModel.onError()).thenReturn(onErrorConsumer);
        when(viewModel.reposUpdated()).thenReturn(onSuccessConsumer);
    }

    @Test
    public void reposLoaded() throws Exception{
        List<Repo> repos = setUpSuccess();
        initializePresenter();

        verify(repoRepository).getTrendingRepos();
        verify(onSuccessConsumer).accept(repos);
        verifyZeroInteractions(onErrorConsumer);
    }

    @Test
    public void reposLoadedError() throws Exception {
        Throwable error = setUpError();
        initializePresenter();;

        verify(onErrorConsumer).accept(error);
        verifyZeroInteractions(onSuccessConsumer);
    }

    @Test
    public void loadingSuccess() throws Exception {
        setUpSuccess();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void loadingError() throws Exception {
        setUpError();
        initializePresenter();

        InOrder inOrder = Mockito.inOrder(loadingConsumer);
        inOrder.verify(loadingConsumer).accept(true);
        inOrder.verify(loadingConsumer).accept(false);
    }

    @Test
    public void onRepoClicked() {
        // TODO
    }

    private List<Repo> setUpSuccess() {
        TrendingReposResponse response = TestUtils.loadJson("mock/get_trending_repos.json", TrendingReposResponse.class);
        List<Repo> repos = response.repos();

        when(repoRepository.getTrendingRepos()).thenReturn(Single.just(repos));

        return repos;
    }

    private Throwable setUpError() {
        Throwable error = new IOException();
        when(repoRepository.getTrendingRepos()).thenReturn(Single.error(error));

        return error;
    }

    private void initializePresenter() {
        trendingReposPresenter = new TrendingReposPresenter(viewModel, repoRepository);
    }
}