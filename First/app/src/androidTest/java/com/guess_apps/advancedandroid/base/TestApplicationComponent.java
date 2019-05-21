package com.guess_apps.advancedandroid.base;

import com.guess_apps.advancedandroid.data.RepoRepository;
import com.guess_apps.advancedandroid.data.TestRepoService;
import com.guess_apps.advancedandroid.data.TestRepoServiceModule;
import com.guess_apps.advancedandroid.networking.ServiceModule;
import com.guess_apps.advancedandroid.trending.TrendingReposControllerTest;
import com.guess_apps.advancedandroid.ui.NavigationModule;
import com.guess_apps.advancedandroid.ui.TestNavigationModule;
import com.guess_apps.advancedandroid.ui.TestScreenNavigator;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        TestActivityBindingModule.class,
        TestRepoServiceModule.class,
        ServiceModule.class,
        TestNavigationModule.class,
})
public interface TestApplicationComponent extends ApplicationComponent{

    void inject(TrendingReposControllerTest trendingReposControllerTest);

    TestScreenNavigator screenNavigator();

    TestRepoService repoService();

    RepoRepository repoRepository();
}
