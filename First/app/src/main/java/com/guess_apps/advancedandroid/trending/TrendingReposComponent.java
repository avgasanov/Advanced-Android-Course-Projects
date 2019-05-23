package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.base.ScreenModule;
import com.guess_apps.advancedandroid.di.ScreenComponent;
import com.guess_apps.advancedandroid.di.ScreenScope;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;

@ScreenScope
@Subcomponent(modules = {
        ScreenModule.class,
        TrendingReposScreenModule.class
})
public interface TrendingReposComponent extends ScreenComponent<TrendingReposController> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<TrendingReposController> {

    }
}
