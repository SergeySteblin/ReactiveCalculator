package com.sergey.apps.rxcalc.operations;

import com.sergey.apps.rxcalc.R;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OperationMulTest {
    // java floating point arithmetic may produce slightly different result
    private static final double DELTA = 0;

    private OperationMul mTestedObject = new OperationMul();

    @Test
    public void calculate() throws Exception {
        assertEquals("wrong operation result", 24, mTestedObject.calculate(12, 2), DELTA);
        assertEquals("wrong operation result", -6.12, mTestedObject.calculate(2.04, -3), DELTA);
        assertEquals("wrong operation result", -93.75, mTestedObject.calculate(-7.5, 12.5), DELTA);
        assertEquals("wrong operation result", 8.4, mTestedObject.calculate(-4.2, -2), DELTA);
    }

    @Test
    public void getType() throws Exception {
        assertEquals("wrong operation type", OperationType.MUL, mTestedObject.getType());
    }

    @Test
    public void getImageResource() throws Exception {
        assertEquals("wrong image resource", R.mipmap.ic_multiply_black, mTestedObject.getImageResource());
    }
}
