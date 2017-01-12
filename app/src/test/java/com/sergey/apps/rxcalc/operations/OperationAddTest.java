package com.sergey.apps.rxcalc.operations;

import com.sergey.apps.rxcalc.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OperationAddTest {
    // java floating point arithmetic may produce slightly different result
    private static final double DELTA = 0;

    private OperationAdd mTestedObject = new OperationAdd();

    @Test
    public void calculate() throws Exception {
        assertEquals("wrong operation result", 14, mTestedObject.calculate(12, 2), DELTA);
        assertEquals("wrong operation result", -0.96, mTestedObject.calculate(2.04, -3), DELTA);
        assertEquals("wrong operation result", 5, mTestedObject.calculate(-7.5, 12.5), DELTA);
        assertEquals("wrong operation result", -6.2, mTestedObject.calculate(-4.2, -2), DELTA);
    }

    @Test
    public void getType() throws Exception {
        assertEquals("wrong operation type", OperationType.ADD, mTestedObject.getType());
    }

    @Test
    public void getImageResource() throws Exception {
        assertEquals("wrong image resource", R.mipmap.ic_plus_black, mTestedObject.getImageResource());
    }
}
