package com.sergey.apps.rxcalc.di;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(MockitoJUnitRunner.class)
public class MainModuleTest {

    @Mock
    private RxCalcApplication application;

    @Test
    public void provideApplication() throws Exception {
        assertEquals("Applications not equal", application, new MainModule(application).provideApplication());
    }

    @Test
    public void provideFragmentFactory() throws Exception {
        assertNotNull("No FragmentFactory to provide", new MainModule(application).provideFragmentFactory());
    }

    @Test
    public void provideNumericUtil() throws Exception {
        assertNotNull("No NumericUtil to provide", new MainModule(application).provideNumericUtil());
    }

    @Test
    public void provideOperationFactory() throws Exception {
        assertNotNull("No OperationFactory to provide", new MainModule(application).provideOperationFactory());
    }
}
