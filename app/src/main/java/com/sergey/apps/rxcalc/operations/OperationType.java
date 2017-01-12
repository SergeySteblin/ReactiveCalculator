package com.sergey.apps.rxcalc.operations;

import android.support.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.sergey.apps.rxcalc.operations.OperationType.MUL;
import static com.sergey.apps.rxcalc.operations.OperationType.SUB;
import static com.sergey.apps.rxcalc.operations.OperationType.ADD;
import static com.sergey.apps.rxcalc.operations.OperationType.DIV;

@IntDef({ADD, SUB, MUL, DIV})
@Retention(RetentionPolicy.SOURCE)
public @interface OperationType {
    int ADD = 0;
    int SUB = 1;
    int MUL = 2;
    int DIV = 3;
}