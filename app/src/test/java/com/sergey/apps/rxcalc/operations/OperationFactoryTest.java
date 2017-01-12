package com.sergey.apps.rxcalc.operations;

import com.sergey.apps.rxcalc.R;
import com.sergey.apps.rxcalc.exceptions.UnknownOperationTypeException;

import org.junit.Test;

import static org.junit.Assert.*;


public class OperationFactoryTest {

    private OperationFactory mTestedObject = new OperationFactory();

    @Test
    public void create_add() throws Exception {
        Operation operation = mTestedObject.create(OperationType.ADD);
        assertEquals("wrong operation type", OperationType.ADD, operation.getType());
        assertEquals("wrong image resource", R.mipmap.ic_plus_black, operation.getImageResource());
    }

    @Test
    public void create_sub() throws Exception {
        Operation operation = mTestedObject.create(OperationType.SUB);
        assertEquals("wrong operation type", OperationType.SUB, operation.getType());
        assertEquals("wrong image resource", R.mipmap.ic_minus_black, operation.getImageResource());
    }

    @Test
    public void create_mul() throws Exception {
        Operation operation = mTestedObject.create(OperationType.MUL);
        assertEquals("wrong operation type", OperationType.MUL, operation.getType());
        assertEquals("wrong image resource", R.mipmap.ic_multiply_black, operation.getImageResource());
    }

    @Test
    public void create_div() throws Exception {
        Operation operation = mTestedObject.create(OperationType.DIV);
        assertEquals("wrong operation type", OperationType.DIV, operation.getType());
        assertEquals("wrong image resource", R.mipmap.ic_divide_black, operation.getImageResource());
    }

    @Test(expected = UnknownOperationTypeException.class)
    public void create_() throws Exception {
        int div = OperationType.DIV;
        div++;
        mTestedObject.create(div);
    }
}
