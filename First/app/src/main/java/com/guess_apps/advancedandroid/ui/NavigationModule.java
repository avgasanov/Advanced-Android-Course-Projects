package com.guess_apps.advancedandroid.ui;

import com.guess_apps.advancedandroid.di.ActivityScope;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class NavigationModule {

    @Binds
    @ActivityScope
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

}
