package com.sergey.apps.rxcalc.di;

import android.app.Application;


public class RxCalcApplication extends Application {
    private static RxCalcComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initDaggerComponents();
    }

    /**
     * Needs for injecting objects into activity/fragment/...
     * Usage: RxCalcApplication.component().inject(...)
     *
     * @return A component that used in dagger binding
     */
    public static RxCalcComponent component() {
        return component;
    }

    private void initDaggerComponents() {
        component = DaggerRxCalcComponent.builder()
                .mainModule(new MainModule(this))
                .build();
    }
}
