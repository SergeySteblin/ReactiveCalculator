package com.sergey.apps.rxcalc.operations;

import com.sergey.apps.rxcalc.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OperationSubTest {
    // java floating point arithmetic may produce slightly different result
    private static final double DELTA = 0;

    private OperationSub mTestedObject = new OperationSub();

    @Test
    public void calculate() throws Exception {
        assertEquals("wrong operation result", 10, mTestedObject.calculate(12, 2), DELTA);
        assertEquals("wrong operation result", 5.04, mTestedObject.calculate(2.04, -3), DELTA);
        assertEquals("wrong operation result", -20, mTestedObject.calculate(-7.5, 12.5), DELTA);
        assertEquals("wrong operation result", -2.2, mTestedObject.calculate(-4.2, -2), DELTA);
    }

    @Test
    public void getType() throws Exception {
        assertEquals("wrong operation type", OperationType.SUB, mTestedObject.getType());
    }

    @Test
    public void getImageResource() throws Exception {
        assertEquals("wrong image resource", R.mipmap.ic_minus_black, mTestedObject.getImageResource());
    }
}
