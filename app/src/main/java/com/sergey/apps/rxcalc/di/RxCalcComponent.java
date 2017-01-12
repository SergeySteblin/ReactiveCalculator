package com.sergey.apps.rxcalc.di;

import com.sergey.apps.rxcalc.MainActivity;
import com.sergey.apps.rxcalc.OperationFragment;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {MainModule.class})
public interface RxCalcComponent {

    void inject(MainActivity mainActivity);

    void inject(OperationFragment operationFragment);
}