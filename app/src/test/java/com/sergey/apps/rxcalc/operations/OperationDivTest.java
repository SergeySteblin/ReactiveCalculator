package com.sergey.apps.rxcalc.operations;

import com.sergey.apps.rxcalc.R;
import com.sergey.apps.rxcalc.exceptions.DivideByZeroException;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class OperationDivTest {
    // java floating point arithmetic may produce slightly different result
    private static final double DELTA = 0;

    private OperationDiv mTestedObject = new OperationDiv();

    @Test
    public void calculate() throws Exception {
        assertEquals("wrong operation result", 6, mTestedObject.calculate(12, 2), DELTA);
        assertEquals("wrong operation result", -0.68, mTestedObject.calculate(2.04, -3), DELTA);
        assertEquals("wrong operation result", -0.6, mTestedObject.calculate(-7.5, 12.5), DELTA);
        assertEquals("wrong operation result", 2.1, mTestedObject.calculate(-4.2, -2), DELTA);
    }

    @Test(expected = DivideByZeroException.class)
    public void calculate_exception() throws Exception {
        mTestedObject.calculate(-4.2, 0);
    }

    @Test
    public void getType() throws Exception {
        assertEquals("wrong operation type", OperationType.DIV, mTestedObject.getType());
    }

    @Test
    public void getImageResource() throws Exception {
        assertEquals("wrong image resource", R.mipmap.ic_divide_black, mTestedObject.getImageResource());
    }
}
