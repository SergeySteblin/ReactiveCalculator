package com.sergey.apps.rxcalc.utils;

import android.text.TextUtils;
import android.util.Log;

public class NumericUtil {

    public Double toDouble(CharSequence charSequence1) {
        if(isValidNumber(charSequence1)) {
            try {
                //noinspection ResultOfMethodCallIgnored
                return Double.parseDouble(charSequence1.toString());

            } catch(NumberFormatException e) {
                // should not happen after 'isValidNumber' returns true, but ...
                Log.i("RxCalc", String.format("Failed to convert %s to double. reason: %s", charSequence1.toString(), e.getMessage()));
            }
        }
        return Double.NaN;
    }

    private boolean isValidNumber(CharSequence charSequence) {
        if(TextUtils.isEmpty(charSequence)) {
            return false;
        }
        String numberStr = charSequence.toString();
        // our EditText elements defined as "numberSigned|numberDecimal"
        // so the number will be not valid if it consist from '-', '.' characters alone
        return !("-".equals(numberStr) || ".".equals(numberStr) || "-.".equals(numberStr));
    }
}
