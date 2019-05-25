package com.guess_apps.advancedandroid.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.guess_apps.advancedandroid.database.favorites.FavoriteContributor;
import com.guess_apps.advancedandroid.database.favorites.FavoriteContributorDao;

@Database(entities = {FavoriteContributor.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase{

    public abstract FavoriteContributorDao favoriteContributorDao();
}
