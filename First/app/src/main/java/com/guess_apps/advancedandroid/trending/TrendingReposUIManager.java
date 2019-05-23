package com.guess_apps.advancedandroid.trending;

import android.view.View;
import android.support.v7.widget.Toolbar;

import com.guess_apps.advancedandroid.R;

import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.guess_apps.advancedandroid.util.ButterKnifeUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.Binds;

@ScreenScope
public class TrendingReposUIManager extends ScreenLifecycleTask {

    @BindView(R.id.toolbar) Toolbar toolbar;

    private Unbinder unbinder;

    @Inject
    TrendingReposUIManager() {

    }

    @Override
    public void onEnterScope(View view) {
        unbinder = ButterKnife.bind(this, view);
        toolbar.setTitle(R.string.screen_title_trending);
    }

    @Override
    public void onExitScope(View view) {
        ButterKnifeUtils.unbind(unbinder);
    }
}

