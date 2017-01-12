package com.sergey.apps.rxcalc;

import com.sergey.apps.rxcalc.operations.OperationType;

import org.junit.Test;

import static org.junit.Assert.*;

public class FragmentFactoryTest {

    private FragmentFactory mTestedObject = new FragmentFactory();

    @Test
    public void getAddFragment() throws Exception {
        OperationFragment fragment = mTestedObject.createAddFragment();
        @OperationType int operationType = fragment.getArguments().getInt(OperationFragment.ARG_OPERATION_TYPE);
        assertEquals("wrong operation type", OperationType.ADD, operationType);
    }

    @Test
    public void getSubFragment() throws Exception {
        OperationFragment fragment = mTestedObject.createSubFragment();
        @OperationType int operationType = fragment.getArguments().getInt(OperationFragment.ARG_OPERATION_TYPE);
        assertEquals("wrong operation type", OperationType.SUB, operationType);
    }

    @Test
    public void getMulFragment() throws Exception {
        OperationFragment fragment = mTestedObject.createMulFragment();
        @OperationType int operationType = fragment.getArguments().getInt(OperationFragment.ARG_OPERATION_TYPE);
        assertEquals("wrong operation type", OperationType.MUL, operationType);
    }

    @Test
    public void getDivFragment() throws Exception {
        OperationFragment fragment = mTestedObject.createDivFragment();
        @OperationType int operationType = fragment.getArguments().getInt(OperationFragment.ARG_OPERATION_TYPE);
        assertEquals("wrong operation type", OperationType.DIV, operationType);
    }
}
