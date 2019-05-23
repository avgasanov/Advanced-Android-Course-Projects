package com.guess_apps.advancedandroid.details;

import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);
}
