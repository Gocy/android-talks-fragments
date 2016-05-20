package com.gluketic.example.fragments.masterdetail.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gluketic.example.fragments.Extras;
import com.gluketic.example.fragments.R;
import com.gluketic.example.fragments.masterdetail.data.DummyContentManager;
import com.gluketic.example.fragments.masterdetail.data.DummyItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
/**
 * Created by Goran Luketic
 */
public class ItemDetailFragment extends Fragment {

    @Nullable
    @BindView(R.id.item_detail)
    TextView mDetailView;

    private Unbinder mUnbinder;
    private DummyItem mItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            if (getArguments().containsKey(Extras.ARG_ITEM_ID)) {
                mItem = DummyContentManager.getInstance().getItemsMap().get(getArguments().getString(Extras.ARG_ITEM_ID));
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.item_detail, container, false);
        mUnbinder = ButterKnife.bind(this, rootView);
        if (mItem != null) {
            if (mDetailView != null) {
                mDetailView.setText(mItem.getDetails());
            }
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }
}
