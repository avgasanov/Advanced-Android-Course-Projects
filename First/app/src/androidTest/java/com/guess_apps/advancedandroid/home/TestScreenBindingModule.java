package com.guess_apps.advancedandroid.home;

import com.bluelinelabs.conductor.Controller;
import com.guess_apps.advancedandroid.di.ControllerKey;
import com.guess_apps.advancedandroid.trending.TrendingReposComponent;
import com.guess_apps.advancedandroid.trending.TrendingReposController;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.multibindings.IntoMap;

@Module(subcomponents = {
        TrendingReposComponent.class,
})
public abstract class TestScreenBindingModule {

    @Binds
    @IntoMap
    @ControllerKey(TrendingReposController.class)
    abstract AndroidInjector.Factory<? extends Controller> bindTrendingReposInjector(TrendingReposComponent.Builder build);

}
