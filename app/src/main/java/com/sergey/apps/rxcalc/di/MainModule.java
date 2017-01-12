package com.sergey.apps.rxcalc.di;


import android.app.Application;

import com.sergey.apps.rxcalc.FragmentFactory;
import com.sergey.apps.rxcalc.operations.OperationFactory;
import com.sergey.apps.rxcalc.utils.NumericUtil;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class MainModule {
    private RxCalcApplication app;

    MainModule(RxCalcApplication application) {
        app = application;
    }

    @Provides
    @Singleton
    Application provideApplication() {
        return app;
    }

    @Provides
    @Singleton
    FragmentFactory provideFragmentFactory() {
        return new FragmentFactory();
    }

    @Provides
    @Singleton
    NumericUtil provideNumericUtil() {
        return new NumericUtil();
    }

    @Provides
    @Singleton
    OperationFactory provideOperationFactory() {
        return new OperationFactory();
    }
}
