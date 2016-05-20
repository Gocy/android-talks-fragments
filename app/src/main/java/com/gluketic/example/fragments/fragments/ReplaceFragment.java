package com.gluketic.example.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.gluketic.example.fragments.Extras;
import com.gluketic.example.fragments.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Optional;
import butterknife.Unbinder;

/**
 * Created by Goran Luketic
 */
public class ReplaceFragment extends Fragment {

    @Nullable
    @BindView(R.id.result_text)
    TextView mResultView;

    @Nullable
    @BindView(R.id.edit_text)
    EditText mEditView;

    private Unbinder mUnbinder;
    private String mText;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(Extras.EXTRA_TEXT)) {
                mText = savedInstanceState.getString(Extras.EXTRA_TEXT);
            }
        } else {
            Bundle b = getArguments();
            if (b != null) {
                if (b.containsKey(Extras.EXTRA_TEXT)) {
                    mText = b.getString(Extras.EXTRA_TEXT);
                }
            }
        }
    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(Extras.EXTRA_TEXT, mText);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_replace, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        if (mResultView != null) {
            mResultView.setText(mText);
        }
        return v;
    }

    @Optional
    @OnClick(R.id.btn_set)
    void handleSetBtn() {
        if (mEditView != null) {
            mText = mEditView.getText().toString();
            setText(mText);
        }
    }

    private void setText(String text) {
        if (mResultView != null) {
            mResultView.setText(text);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
