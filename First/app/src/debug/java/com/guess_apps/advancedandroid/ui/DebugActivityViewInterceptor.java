package com.guess_apps.advancedandroid.ui;

import com.guess_apps.advancedandroid.R;
import com.guess_apps.advancedandroid.settings.DebugPreferences;
import com.jakewharton.rxbinding2.widget.RxCompoundButton;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Switch;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;

public class DebugActivityViewInterceptor implements ActivityViewInterceptor{

    @BindView(R.id.sw_mock_responses) Switch mockResponseSwitch;

    private final DebugPreferences debugPreferences;
    private final CompositeDisposable disposable = new CompositeDisposable();

    private Unbinder unbinder;

    @Inject
    public DebugActivityViewInterceptor(DebugPreferences debugPreferences) {
        this.debugPreferences = debugPreferences;
    }

    @Override
    public void setContentView(Activity activity, int layoutRes) {
        View debugLayout = LayoutInflater.from(activity).inflate(R.layout.debug_drawer, null);
        unbinder = ButterKnife.bind(this, debugLayout);
        initializePrefs();

        View activityLayout = LayoutInflater.from(activity).inflate(layoutRes, null);
        debugLayout.<ViewGroup>findViewById(R.id.activity_layout_container).addView(activityLayout);
        activity.setContentView(debugLayout);
    }

    @Override
    public void clear() {
        disposable.clear();
        if(unbinder != null) {
            unbinder.unbind();
        }
    }

    private void initializePrefs() {
        mockResponseSwitch.setChecked(debugPreferences.useMockResponsesEnabled());

        disposable.addAll(
                RxCompoundButton.checkedChanges(mockResponseSwitch).subscribe(debugPreferences::setUseMockReponses)
        );
    }
}
