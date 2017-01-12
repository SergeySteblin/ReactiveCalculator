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

    private Fragment mAddFragment;
    private Fragment mSubFragment;
    private Fragment mMulFragment;
    private Fragment mDivFragment;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RxCalcApplication.component().inject(this);

        FragmentManager fragmentManager = getFragmentManager();
        if(savedInstanceState == null) {
            mAddFragment = mFragmentFactory.createAddFragment();
            mSubFragment = mFragmentFactory.createSubFragment();
            mMulFragment = mFragmentFactory.createMulFragment();
            mDivFragment = mFragmentFactory.createDivFragment();

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.add_fragment_container, mAddFragment, ADD_FRAGMENT);
            fragmentTransaction.add(R.id.sub_fragment_container, mSubFragment, SUB_FRAGMENT);
            fragmentTransaction.add(R.id.mul_fragment_container, mMulFragment, MUL_FRAGMENT);
            fragmentTransaction.add(R.id.div_fragment_container, mDivFragment, DIV_FRAGMENT);
            fragmentTransaction.commit();
        } else {
            mAddFragment = fragmentManager.getFragment(savedInstanceState, ADD_FRAGMENT);
            mSubFragment = fragmentManager.getFragment(savedInstanceState, SUB_FRAGMENT);
            mMulFragment = fragmentManager.getFragment(savedInstanceState, MUL_FRAGMENT);
            mDivFragment = fragmentManager.getFragment(savedInstanceState, DIV_FRAGMENT);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save the fragment's instance
        FragmentManager fragmentManager = getFragmentManager();
        fragmentManager.putFragment(outState, ADD_FRAGMENT, mAddFragment);
        fragmentManager.putFragment(outState, SUB_FRAGMENT, mSubFragment);
        fragmentManager.putFragment(outState, MUL_FRAGMENT, mMulFragment);
        fragmentManager.putFragment(outState, DIV_FRAGMENT, mDivFragment);
    }
}
