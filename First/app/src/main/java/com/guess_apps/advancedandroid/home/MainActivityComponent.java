package com.guess_apps.advancedandroid.home;

import com.guess_apps.advancedandroid.di.ActivityScope;
import com.guess_apps.advancedandroid.ui.NavigationModule;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        MainScreenBindingModule.class,
        NavigationModule.class,
})
public interface MainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract  class Builder extends AndroidInjector.Builder<MainActivity> {

        @Override
        public void seedInstance(MainActivity instance) {

        }
    }
}
