package com.guess_apps.advancedandroid.ui;

import com.guess_apps.advancedandroid.lifecycle.ActivityLifeCycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoSet;

@Module
public abstract class TestNavigationModule {

    @Binds
    abstract ScreenNavigator bindScreenNavigator(TestScreenNavigator testScreenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifeCycleTask bindScreenNavigatorTask(TestScreenNavigator testScreenNavigator);
}
