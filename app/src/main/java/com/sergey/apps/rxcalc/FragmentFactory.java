package com.sergey.apps.rxcalc;

import com.sergey.apps.rxcalc.operations.OperationType;


public class FragmentFactory {

    OperationFragment createAddFragment() {
        return OperationFragment.newInstance(OperationType.ADD);
    }

    OperationFragment createSubFragment() {
        return OperationFragment.newInstance(OperationType.SUB);
    }

    OperationFragment createMulFragment() {
        return OperationFragment.newInstance(OperationType.MUL);
    }

    OperationFragment createDivFragment() {
        return OperationFragment.newInstance(OperationType.DIV);
    }
}
