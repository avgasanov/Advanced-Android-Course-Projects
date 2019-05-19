package com.guess_apps.advancedandroid.base;

import com.guess_apps.advancedandroid.data.TestRepoService;
import com.guess_apps.advancedandroid.data.TestRepoServiceModule;
import com.guess_apps.advancedandroid.networking.ServiceModule;
import com.guess_apps.advancedandroid.trending.TrendingReposControllerTest;
import com.guess_apps.advancedandroid.ui.NavigationModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        NavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent{

    void inject(TrendingReposControllerTest trendingReposControllerTest);
}
