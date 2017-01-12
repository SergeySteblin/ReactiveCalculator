package com.sergey.apps.rxcalc.operations;

import android.support.annotation.DrawableRes;

import com.sergey.apps.rxcalc.exceptions.NumericOperationException;

public interface Operation {

    double calculate(double n1, double n2) throws NumericOperationException;

    @OperationType int getType();

    @DrawableRes int getImageResource();
}
