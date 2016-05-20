package com.gluketic.example.fragments.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.gluketic.example.fragments.Extras;

/**
 * Created by Goran Luketic
 */
public class BaseActivity extends AppCompatActivity {

    protected void replaceFragment(int id, Fragment fragment) {
        removeAddedFragmentIfExists();
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction
                .replace(id, fragment)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    protected void addFragment(int id, Fragment fragment) {
        FragmentManager fm = getFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction
                .add(id, fragment, Extras.ADDED_FRAGMENT_TAG)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }


    /**
     * Remove added fragment if exists in container
     */
    private void removeAddedFragmentIfExists() {
        Fragment addedFragment = getFragmentManager().findFragmentByTag(Extras.ADDED_FRAGMENT_TAG);
        if (addedFragment != null && addedFragment.isVisible()) {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentByTag(Extras.ADDED_FRAGMENT_TAG)).commit();
        }
    }
}
