package com.guess_apps.advancedandroid.trending;

import com.guess_apps.advancedandroid.di.ScreenScope;
import com.guess_apps.advancedandroid.lifecycle.ScreenLifecycleTask;
import com.tuesday_apps.poweradapter.adapter.RecyclerDataSource;
import com.tuesday_apps.poweradapter.item.ItemRenderer;
import com.tuesday_apps.poweradapter.item.RecyclerItem;
import com.tuesday_apps.poweradapter.item.RenderKey;

import java.util.Map;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.multibindings.IntoMap;
import dagger.multibindings.IntoSet;

@Module
public abstract class TrendingReposScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManager(TrendingReposUIManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Repo")
    abstract ItemRenderer<? extends RecyclerItem> bindRepoRenderer(RepoRenderer repoRenderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideRecyclerDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
