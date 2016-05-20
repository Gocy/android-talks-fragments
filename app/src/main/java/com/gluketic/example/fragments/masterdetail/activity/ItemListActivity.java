package com.gluketic.example.fragments.masterdetail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.gluketic.example.fragments.Extras;
import com.gluketic.example.fragments.R;
import com.gluketic.example.fragments.activities.BaseActivity;
import com.gluketic.example.fragments.activities.MainActivity;
import com.gluketic.example.fragments.masterdetail.adapter.SimpleRecyclerAdapter;
import com.gluketic.example.fragments.masterdetail.data.DummyContentManager;
import com.gluketic.example.fragments.masterdetail.data.DummyItem;
import com.gluketic.example.fragments.masterdetail.fragment.ItemDetailFragment;
import com.gluketic.example.fragments.masterdetail.listener.OnRecyclerItemClickListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goran Luketic
 */
public class ItemListActivity extends BaseActivity implements OnRecyclerItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.item_list)
    RecyclerView recyclerView;

    private SimpleRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.nav_item_master_detail));
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mAdapter = new SimpleRecyclerAdapter();
        mAdapter.setItemClickListener(this);
        ArrayList<DummyItem> items = DummyContentManager.getInstance().getItems();
        mAdapter.setData(items);
        recyclerView.setAdapter(mAdapter);
        if (items.size() >= 1) {
            showDetails(items.get(0));
        }
    }


    @Override
    public void onItemClick(View view, int position) {
        showDetails(mAdapter.getItemAtPosition(position));
    }

    private void showDetails(DummyItem item) {
        Bundle arguments = new Bundle();
        arguments.putString(Extras.ARG_ITEM_ID, item.getId());
        ItemDetailFragment fragment = new ItemDetailFragment();
        fragment.setArguments(arguments);
        replaceFragment(R.id.item_detail_container, fragment);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
