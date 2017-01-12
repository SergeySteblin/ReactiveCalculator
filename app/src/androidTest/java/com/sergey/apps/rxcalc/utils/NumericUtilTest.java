package com.sergey.apps.rxcalc.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class NumericUtilTest {

    private NumericUtil mTestedObject = new NumericUtil();

    @Test
    public void toDouble_not_numeric() throws Exception {
        assertNotNumericString("");
        assertNotNumericString(" ");
        assertNotNumericString("abc");
        assertNotNumericString(".1.");
        assertNotNumericString("-2-");
        assertNotNumericString(".3-");
        assertNotNumericString("-");
        assertNotNumericString(".");
        assertNotNumericString("-.");
    }

    @Test
    public void toDouble_numeric() throws Exception {
        assertNumericString("0");
        assertNumericString("00");
        assertNumericString("-0");
        assertNumericString("-.1");
        assertNumericString(".2");
        assertNumericString("-0.3");
        assertNumericString("0.4");
        assertNumericString("-1.003");
    }

    private void assertNotNumericString(String strNumber) {
        assertEquals("Wrong conversion", Double.NaN, mTestedObject.toDouble(strNumber), 0);
    }

    private void assertNumericString(String strNumber) {
        assertEquals("Wrong conversion", Double.parseDouble(strNumber), mTestedObject.toDouble(strNumber), 0);
    }
}
