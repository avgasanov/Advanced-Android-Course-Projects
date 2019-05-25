package com.guess_apps.advancedandroid.details;

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
public abstract class RepoDetailsScreenModule {

    @Binds
    @IntoSet
    abstract ScreenLifecycleTask bindUiManagerTask(RepoDetailsUiManager uiManager);

    @Binds
    @IntoMap
    @RenderKey("Contributor")
    abstract ItemRenderer<? extends RecyclerItem> bindContributorRenderer(ContributorRenderer renderer);

    @Provides
    @ScreenScope
    static RecyclerDataSource provideDataSource(Map<String, ItemRenderer<? extends RecyclerItem>> renderers) {
        return new RecyclerDataSource(renderers);
    }
}
