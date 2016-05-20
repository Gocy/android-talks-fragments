package com.gluketic.example.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gluketic.example.fragments.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * Created by Goran Luketic
 */
public class FragmentBackStackExample extends Fragment {

    @Nullable
    @BindView(R.id.description)
    TextView mDescriptionView;

    @Nullable
    @BindView(R.id.result_text)
    TextView mResultView;

    @Nullable
    @BindView(R.id.edit_text)
    EditText mEditView;

    private Unbinder mUnbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_back_stack, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Optional
    @OnClick(R.id.btn_set)
    void handleSetBtn() {
        if (mEditView != null && mResultView != null) {
            mResultView.setText(mEditView.getText().toString());
        }
    }

    @Optional
    @OnClick(R.id.btn_next)
    void onNext() {
        getFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.fragment_container, new ReplaceFragment())
                .commit();
    }
}
