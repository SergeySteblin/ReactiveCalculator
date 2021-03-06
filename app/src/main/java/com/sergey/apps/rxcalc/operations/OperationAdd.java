package com.sergey.apps.rxcalc.operations;


import android.support.annotation.DrawableRes;

import com.sergey.apps.rxcalc.R;

class OperationAdd implements Operation {

    @Override
    public double calculate(double n1, double n2) {
        return n1 + n2;
    }

    @Override
    public @OperationType int getType() {
        return OperationType.ADD;
    }

    @Override
    public @DrawableRes int getImageResource() {
        return R.mipmap.ic_plus_black;
    }
}
