package com.sergey.apps.rxcalc;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class AppInstrumentedTest {

    @Rule
    public ActivityTestRule<MainActivity> rule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.sergey.apps.rxcalc", appContext.getPackageName());
    }

    @Test
    public void verifyNotNumericInputs() throws Exception {
        performAndCheck(getAddOpp(), new InOut("1", ".", ""));
        performAndCheck(getSubOpp(), new InOut("-", "3", ""));
        performAndCheck(getMulOpp(), new InOut("", "-", ""));
        performAndCheck(getDivOpp(), new InOut("-.", "7", ""));
    }

    @Test
    public void verifyZeroInputs() throws Exception {
        performAndCheck(getAddOpp(), new InOut("0", "0", "0"));
        performAndCheck(getSubOpp(), new InOut("0", "0", "0"));
        performAndCheck(getMulOpp(), new InOut("0", "0", "0"));
        performAndCheck(getDivOpp(), new InOut("0", "0", getStringResource(R.string.result_error)));
    }

    @Test
    public void verifyNegativeInputs() throws Exception {
        performAndCheck(getAddOpp(), new InOut("-12", "-33", "-45"));
        performAndCheck(getSubOpp(), new InOut("-41", "-9", "-32"));
        performAndCheck(getMulOpp(), new InOut("-3", "-15", "45"));
        performAndCheck(getDivOpp(), new InOut("-24", "-8", "3"));
    }

    @Test
    public void verifyFloatingPointInputs() throws Exception {
        performAndCheck(getAddOpp(), new InOut("2.34", "3.45", "5.79"));
        performAndCheck(getSubOpp(), new InOut("41.2", "12.8", "28.4"));
        performAndCheck(getMulOpp(), new InOut("6.23", "7.47", "46.5381"));
        performAndCheck(getDivOpp(), new InOut("0.123", "0.015", "8.2"));
    }

    @Test
    public void verifyMoreInputs() throws Exception {
        performAndCheck(getAddOpp(), new InOut("-1.45", "3.52", "2.07"));
        performAndCheck(getSubOpp(), new InOut("-38.2", "18.3", "-56.5"));
        performAndCheck(getMulOpp(), new InOut("3.1", "-74.8", "-231.88"));
        performAndCheck(getDivOpp(), new InOut("12.3", "-8.2", "-1.5"));
    }

    // ==========================================
    //  help methods
    // ==========================================
    private void performAndCheck(Opp add, InOut inOut) {
        onView(add.number1()).perform(typeText(inOut.getNumber1()));
        onView(add.number2()).perform(typeText(inOut.getNumber2()));
        onView(add.result()).check(matches(withText(inOut.getResult())));
    }

    @NonNull
    private Opp getAddOpp() {
        return getOpp(R.id.add_fragment_container);
    }

    @NonNull
    private Opp getSubOpp() {
        return getOpp(R.id.sub_fragment_container);
    }

    @NonNull
    private Opp getMulOpp() {
        return getOpp(R.id.mul_fragment_container);
    }

    @NonNull
    private Opp getDivOpp() {
        return getOpp(R.id.div_fragment_container);
    }

    @NonNull
    private Opp getOpp(int operationId) {
        return new Opp(operationId);
    }

    @NonNull
    private String getStringResource(int resourceId) {
        return InstrumentationRegistry.getTargetContext().getResources().getString(resourceId);
    }
}

class Opp {
    private int mOperationId;

    Opp(int operationId) {
        this.mOperationId = operationId;
    }

    Matcher<View> number1() {
        return allOf(withId(R.id.number1), withParent(elementsContainer()));
    }

    Matcher<View> number2() {
        return allOf(withId(R.id.number2), withParent(elementsContainer()));
    }

    Matcher<View> result() {
        return allOf(withId(R.id.result), withParent(elementsContainer()));
    }

    private Matcher<View> elementsContainer() {
        return allOf(withId(R.id.operation), withParent(withId(mOperationId)));
    }
}

class InOut {
    private String mNumber1;
    private String mNumber2;
    private String mResult;

    InOut(String number1, String number2, String result) {
        this.mNumber1 = number1;
        this.mNumber2 = number2;
        this.mResult = result;
    }

    String getNumber1() {
        return mNumber1;
    }

    String getNumber2() {
        return mNumber2;
    }

    String getResult() {
        return mResult;
    }
}
