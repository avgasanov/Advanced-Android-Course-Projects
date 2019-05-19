package com.guess_apps.advancedandroid.home;

import com.guess_apps.advancedandroid.di.ActivityScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ActivityScope
@Subcomponent(modules = {
        TestScreenBindingModule.class
})
public interface TestMainActivityComponent extends AndroidInjector<MainActivity> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<MainActivity> {

    }
}
