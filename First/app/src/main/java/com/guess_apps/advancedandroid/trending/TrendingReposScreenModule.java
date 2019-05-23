package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(TrendingReposUIManager uiManager);
}
