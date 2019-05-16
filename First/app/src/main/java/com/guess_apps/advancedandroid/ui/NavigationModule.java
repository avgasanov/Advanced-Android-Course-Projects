package com.guess_apps.advancedandroid.ui;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;

@Module
public abstract class NavigationModule {

    @Binds
    abstract ScreenNavigator provideScreenNavigator(DefaultScreenNavigator screenNavigator);

}
