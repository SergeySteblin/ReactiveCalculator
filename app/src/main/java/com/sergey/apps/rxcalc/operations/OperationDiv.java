package com.sergey.apps.rxcalc.operations;


import android.support.annotation.DrawableRes;

import com.sergey.apps.rxcalc.R;
import com.sergey.apps.rxcalc.exceptions.DivideByZeroException;

class OperationDiv implements Operation {

    @Override
    public double calculate(double n1, double n2) {
        if(n2 != 0) {
            return n1 / n2;
        } else {
            throw new DivideByZeroException();
        }
    }

    @Override
    public @OperationType int getType() {
        return OperationType.DIV;
    }

    @Override
    public @DrawableRes int getImageResource() {
        return R.mipmap.ic_divide_black;
    }
}
