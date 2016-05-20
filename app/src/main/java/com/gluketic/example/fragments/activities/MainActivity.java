package com.gluketic.example.fragments.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.gluketic.example.fragments.Extras;
import com.gluketic.example.fragments.R;
import com.gluketic.example.fragments.fragments.AddFragment;
import com.gluketic.example.fragments.fragments.FragmentBackStackExample;
import com.gluketic.example.fragments.fragments.HomeFragment;
import com.gluketic.example.fragments.fragments.ReplaceFragment;
import com.gluketic.example.fragments.masterdetail.activity.ItemListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Goran Luketic
 */
public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    private int mSelectedNavItem = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigate(R.id.nav_home);
        }

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Extras.EXTRA_SELECTED_ITEM, mSelectedNavItem);
    }


    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (getFragmentManager().getBackStackEntryCount() != 0) {
                getFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        mSelectedNavItem = item.getItemId();
        navigate(mSelectedNavItem);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void navigate(int id) {
        switch (id) {
            case R.id.nav_home:
                navigateHome();
                break;
            case R.id.nav_replace:
                navigateReplace();
                break;
            case R.id.nav_add:
                navigateAdd();
                break;
            case R.id.nav_master_detail:
                navigateMasterDetail();
                break;
            case R.id.nav_back_stack:
                navigateBackStack();
                break;
            default:
                navigateHome();
                break;
        }
    }

    private void navigateHome() {
        replaceFragment(R.id.fragment_container, new HomeFragment());
    }

    private void navigateReplace() {
        replaceFragment(R.id.fragment_container, new ReplaceFragment());
    }

    private void navigateBackStack() {
        replaceFragment(R.id.fragment_container, new FragmentBackStackExample());
    }

    private void navigateAdd() {
        addFragment(R.id.fragment_container, new AddFragment());
    }

    private void navigateMasterDetail() {
        startActivity(new Intent(MainActivity.this, ItemListActivity.class));
    }
}
