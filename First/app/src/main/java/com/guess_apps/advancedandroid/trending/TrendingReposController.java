package com.guess_apps.advancedandroid.trending;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.guess_apps.advancedandroid.R;

import com.guess_apps.advancedandroid.base.BaseController;
import com.tuesday_apps.poweradapter.adapter.RecyclerAdapter;
import com.tuesday_apps.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class TrendingReposController extends BaseController {

    @Inject TrendingReposPresenter presenter;
    @Inject TrendingReposViewModel viewModel;
    @Inject RecyclerDataSource dataSource;

    @BindView(R.id.repo_list) RecyclerView repoList;
    @BindView(R.id.loading_indicator) View loadingView;
    @BindView(R.id.tv_error) TextView errorText;

    @Override
    protected void onViewBound(View view) {
        repoList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        repoList.setAdapter(new RecyclerAdapter(dataSource));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[] {
                viewModel.loading()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(loading -> {
                        loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);
                        repoList.setVisibility(loading ? View.GONE : View.VISIBLE);
                        errorText.setVisibility(loading ? View.GONE : View.VISIBLE);
                }),
                viewModel.error()
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(errorRes -> {
                        if (errorRes == -1) {
                            errorText.setText(null);
                            errorText.setVisibility(View.GONE);
                        } else {
                            errorText.setVisibility(View.VISIBLE);
                            repoList.setVisibility(View.GONE);
                            errorText.setText(errorRes);
                        }
                }),
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_trending_repos;
    }
}
