package com.sergey.apps.rxcalc;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.sergey.apps.rxcalc.di.RxCalcApplication;

import javax.inject.Inject;


public class MainActivity extends AppCompatActivity {
    public static final String ADD_FRAGMENT = "addFragment";
    public static final String SUB_FRAGMENT = "subFragment";
    public static final String MUL_FRAGMENT = "mulFragment";
    public static final String DIV_FRAGMENT = "divFragment";

    @Inject
    FragmentFactory mFragmentFactory;

    private Fragment addFragment;
    private Fragment subFragment;
    private Fragment mulFragment;
    private Fragment divFragment;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxCalcApplication.component().inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        if(savedInstanceState == null) {
            addFragment = mFragmentFactory.createAddFragment();
            subFragment = mFragmentFactory.createSubFragment();
            mulFragment = mFragmentFactory.createMulFragment();
            divFragment = mFragmentFactory.createDivFragment();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.add_fragment_container, addFragment, ADD_FRAGMENT);
            fragmentTransaction.add(R.id.sub_fragment_container, subFragment, SUB_FRAGMENT);
            fragmentTransaction.add(R.id.mul_fragment_container, mulFragment, MUL_FRAGMENT);
            fragmentTransaction.add(R.id.div_fragment_container, divFragment, DIV_FRAGMENT);
            fragmentTransaction.commit();
        } else {
            addFragment = fragmentManager.getFragment(savedInstanceState, ADD_FRAGMENT);
            subFragment = fragmentManager.getFragment(savedInstanceState, SUB_FRAGMENT);
            mulFragment = fragmentManager.getFragment(savedInstanceState, MUL_FRAGMENT);
            divFragment = fragmentManager.getFragment(savedInstanceState, DIV_FRAGMENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.putFragment(outState, ADD_FRAGMENT, addFragment);
        fragmentManager.putFragment(outState, SUB_FRAGMENT, subFragment);
        fragmentManager.putFragment(outState, MUL_FRAGMENT, mulFragment);
        fragmentManager.putFragment(outState, DIV_FRAGMENT, divFragment);
    }
}
