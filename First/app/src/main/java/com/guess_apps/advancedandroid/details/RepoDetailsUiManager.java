package com.guess_apps.advancedandroid.details;

import android.view.View;
import android.support.v7.widget.Toolbar;
import com.guess_apps.advancedandroid.R;

import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.guess_apps.advancedandroid.ui.ScreenNavigator;
import com.guess_apps.advancedandroid.util.ButterKnifeUtils;

import javax.inject.Inject;
import javax.inject.Named;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

@ScreenScope
public class RepoDetailsUiManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private final String repoName;
    private final ScreenNavigator screenNavigator;

    private Unbinder unbinder;

    @Inject
    RepoDetailsUiManager(@Named("repo_name") String repoName, ScreenNavigator screenNavigator) {
        this.repoName = repoName;
        this.screenNavigator = screenNavigator;
    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(repoName);
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(v -> screenNavigator.pop());
    }

    @Override
    public void onExitScope(View view) {
        toolbar.setNavigationOnClickListener(null);
        ButterKnifeUtils.unbind(unbinder);
    }
}
