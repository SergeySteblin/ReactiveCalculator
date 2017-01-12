package com.sergey.apps.rxcalc;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.sergey.apps.rxcalc.di.RxCalcApplication;
import com.sergey.apps.rxcalc.exceptions.DivideByZeroException;
import com.sergey.apps.rxcalc.operations.Operation;
import com.sergey.apps.rxcalc.operations.OperationFactory;
import com.sergey.apps.rxcalc.operations.OperationType;
import com.sergey.apps.rxcalc.utils.NumericUtil;

import java.text.DecimalFormat;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import rx.Observable;
import rx.Subscription;
import rx.subscriptions.Subscriptions;


public class OperationFragment extends Fragment {
    // used in test
    static final String ARG_OPERATION_TYPE  = "operationType";
    private static final String KEY_NUMBER1 = "number1";
    private static final String KEY_NUMBER2 = "number2";

    // will make 'nice' print of numbers with floating point
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.#############");
    private static final String TAG = "RxCalc";

    // initialize with empty for not checking for null
    private Subscription mSubscription = Subscriptions.empty();

    private Unbinder mButterKnifeUnbinder;

    @Inject
    NumericUtil mNumericUtil;

    @Inject
    OperationFactory mOperationFactory;

    private Operation mOperation;

    @BindView(R.id.number1) EditText mNumber1;
    @BindView(R.id.number2) EditText mNumber2;
    @BindView(R.id.result)  TextView mResult;


    public OperationFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param operationType Parameter that will help to create Operation.
     * @return A new instance of fragment OperationFragment.
     */
    public static OperationFragment newInstance(@OperationType int operationType) {
        OperationFragment fragment = new OperationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_OPERATION_TYPE, operationType);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RxCalcApplication.component().inject(this);

        if(getArguments() != null) {
            @OperationType int operationType = getArguments().getInt(ARG_OPERATION_TYPE);
            mOperation = mOperationFactory.create(operationType);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_operation, container, false);
        mButterKnifeUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setOperationImage(view);

        mSubscription = createCombineLatestObservable(mNumber1, mNumber2).subscribe(
                resultText -> mResult.setText(resultText),
                t -> Log.e(TAG, "error occurred", t));
    }

    private void setOperationImage(View view) {
        ImageView imgOperation = ButterKnife.findById(view, R.id.imgOperation);
        imgOperation.setImageResource(mOperation.getImageResource());
    }

    @NonNull
    private Observable<String> createCombineLatestObservable(EditText editText1, EditText editText2) {
        Observable<Double> number1Observable = createEditTextObservable(editText1);
        Observable<Double> number2Observable = createEditTextObservable(editText2);

        return Observable.combineLatest(number1Observable, number2Observable, (charSequence1, charSequence2) -> {
            try {
                if(!Double.isNaN(charSequence1) && !Double.isNaN(charSequence2)) {
                    double result = mOperation.calculate(charSequence1, charSequence2);
                    return DECIMAL_FORMAT.format(result);
                }
                return "";

            } catch(DivideByZeroException e) {
                return getResources().getString(R.string.result_error);
            } catch(Exception e) {
                Log.e(TAG, "Got some general exception: " + e.getMessage(), e);
                return "";
            }
        });
    }

    @NonNull
    private Observable<Double> createEditTextObservable(EditText editText) {
        // skip first event since it fires on initialization and always empty
        return RxTextView.textChanges(editText).skip(1)
                .map(charSequence -> mNumericUtil.toDouble(charSequence))
                .distinctUntilChanged();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(KEY_NUMBER1, mNumber1.getText().toString());
        outState.putString(KEY_NUMBER2, mNumber2.getText().toString());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if(savedInstanceState != null) {
            mNumber1.setText(savedInstanceState.getString(KEY_NUMBER1));
            mNumber2.setText(savedInstanceState.getString(KEY_NUMBER2));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if( ! mSubscription.isUnsubscribed()) {
            mSubscription.unsubscribe();
        }
        if(mButterKnifeUnbinder != null) {
            mButterKnifeUnbinder.unbind();
        }
    }
}
