package com.sergey.apps.rxcalc.exceptions;


import com.sergey.apps.rxcalc.operations.OperationType;

public class UnknownOperationTypeException extends RuntimeException {

    public UnknownOperationTypeException(@OperationType int operationType) {
        super("operationType: "+operationType);
    }
}
