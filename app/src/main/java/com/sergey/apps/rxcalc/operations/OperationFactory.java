package com.sergey.apps.rxcalc.operations;

import com.sergey.apps.rxcalc.exceptions.UnknownOperationTypeException;


public class OperationFactory {

    public Operation create(@OperationType int operationType) {
        switch(operationType) {
            case OperationType.ADD: return new OperationAdd();
            case OperationType.SUB: return new OperationSub();
            case OperationType.MUL: return new OperationMul();
            case OperationType.DIV: return new OperationDiv();
            default:
                throw new UnknownOperationTypeException(operationType);
        }
    }
}
