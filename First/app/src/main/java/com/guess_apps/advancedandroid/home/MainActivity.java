package com.guess_apps.advancedandroid.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bluelinelabs.conductor.Controller;
import com.guess_apps.advancedandroid.R;
import com.guess_apps.advancedandroid.base.BaseActivity;
import com.guess_apps.advancedandroid.trending.TrendingReposController;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected Controller initialScreen() {
        return new TrendingReposController();
    }
}
