package com.gluketic.example.fragments.masterdetail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.gluketic.example.fragments.R;
import com.gluketic.example.fragments.masterdetail.data.DummyItem;
import com.gluketic.example.fragments.masterdetail.listener.OnRecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goran Luketic
 */
public class SimpleRecyclerAdapter extends RecyclerView.Adapter<SimpleRecyclerAdapter.ViewHolder> {

    private ArrayList<DummyItem> mValues;
    private OnRecyclerItemClickListener mItemClickListener;

    public SimpleRecyclerAdapter() {
        mValues = new ArrayList<>();
    }

    public void setItemClickListener(OnRecyclerItemClickListener listener) {
        mItemClickListener = listener;
    }

    public synchronized void setData(ArrayList<DummyItem> data) {
        mValues = data;
        notifyDataSetChanged();
    }

    public DummyItem getItemAtPosition(int position) {
        return mValues.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        DummyItem item = getItemAtPosition(position);
        holder.mIdView.setText(item.getId());
        holder.mContentView.setText(item.getContent());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.id)
        TextView mIdView;
        @BindView(R.id.content)
        TextView mContentView;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mItemClickListener != null) {
                        mItemClickListener.onItemClick(v, getAdapterPosition());
                    }
                }
            });

        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
