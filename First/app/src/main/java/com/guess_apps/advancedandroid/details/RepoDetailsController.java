package com.guess_apps.advancedandroid.details;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.bluelinelabs.conductor.Controller;
import com.guess_apps.advancedandroid.base.BaseController;
import com.guess_apps.advancedandroid.R;
import com.tuesday_apps.poweradapter.adapter.RecyclerAdapter;
import com.tuesday_apps.poweradapter.adapter.RecyclerDataSource;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class RepoDetailsController extends BaseController{

    static final String REPO_NAME_KEY = "repo_name";
    static final String REPO_OWNER_KEY = "repo_owner";

    public static Controller newInstance(String repoName, String repoOwner) {
        Bundle bundle = new Bundle();
        bundle.putString(REPO_NAME_KEY, repoName);
        bundle.putString(REPO_OWNER_KEY, repoOwner);
        return new RepoDetailsController(bundle);
    }

    @Inject RepoDetailsViewModel viewModel;
    @Inject RepoDetailsPresenter presenter;
    @Inject RecyclerDataSource contributorDataSource;

    @BindView(R.id.tv_repo_name) TextView repoNameText;
    @BindView(R.id.tv_repo_description) TextView repoDescriptionText;
    @BindView(R.id.tv_creation_date) TextView createdDateText;
    @BindView(R.id.tv_updated_date) TextView updatedDateText;
    @BindView(R.id.contributor_list) RecyclerView contributorList;
    @BindView(R.id.loading_indicator) View detailsLoadingView;
    @BindView(R.id.contributor_loading_indicator) View contributorsLoadingView;
    @BindView(R.id.content) View contentContainer;
    @BindView(R.id.tv_error) TextView errorText;
    @BindView(R.id.tv_contributors_error) TextView contributorsErrorText;

    public RepoDetailsController(Bundle bundle) {
        super(bundle);
    }

    @Override
    protected void onViewBound(View view) {
        contributorList.setLayoutManager(new LinearLayoutManager(view.getContext()));
        contributorList.setAdapter(new RecyclerAdapter(contributorDataSource));
    }

    @Override
    protected Disposable[] subscriptions() {
        return new Disposable[] {
            viewModel.details()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(details -> {
                if(details.loading()) {
                    detailsLoadingView.setVisibility(View.VISIBLE);
                    contentContainer.setVisibility(View.GONE);
                    errorText.setVisibility(View.GONE);
                    errorText.setText(null);
                } else {
                    if(details.isSuccess()) {
                        errorText.setText(null);
                    } else {
                        errorText.setText(details.errorRes());
                    }
                    detailsLoadingView.setVisibility(View.GONE);
                    contentContainer.setVisibility(details.isSuccess() ? View.VISIBLE : View.GONE);
                    errorText.setVisibility(details.isSuccess() ? View.GONE : View.VISIBLE);
                    repoNameText.setText(details.name());
                    repoDescriptionText.setText(details.description());
                    createdDateText.setText(details.createdDate());
                    updatedDateText.setText(details.updatedDate());
                }
            }),
            viewModel.contributors()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(contributorDetails -> {
                if(contributorDetails.loading()) {
                    contributorsLoadingView.setVisibility(View.VISIBLE);
                    contributorList.setVisibility(View.GONE);
                    contributorsErrorText.setVisibility(View.GONE);
                    contributorsErrorText.setText(null);
                } else {
                    contributorsLoadingView.setVisibility(View.GONE);
                    contributorList.setVisibility(contributorDetails.isSuccess() ? View.VISIBLE : View.GONE);
                    contributorsErrorText.setVisibility(contributorDetails.isSuccess() ? View.GONE : View.VISIBLE);
                    if(contributorDetails.isSuccess()) {
                        contributorsErrorText.setText(null);
                    } else {
                        contributorsErrorText.setText(contributorDetails.errorRes());
                    }
                }
            })
        };
    }

    @Override
    protected int layoutRes() {
        return R.layout.screen_repo_details;
    }
}
