package com.guess_apps.advancedandroid.ui;

import com.guess_apps.advancedandroid.di.ActivityScope;
import com.guess_apps.advancedandroid.lifecycle.ActivityLifeCycleTask;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoSet;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

    @Binds
    @IntoSet
    abstract ActivityLifeCycleTask bindScreenNavigatorTask(DefaultScreenNavigator screenNavigator);
}
