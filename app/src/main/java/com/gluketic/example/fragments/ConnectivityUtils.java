package com.gluketic.example.fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Goran Luketic
 */
public class ConnectivityUtils {

    /**
     * Check if it has an active connection.
     *
     * @param context some Context.
     * @return does it have an active Network connection.
     */
    public static boolean hasActiveNetworkConnection(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return ((networkInfo != null) && networkInfo.isConnected());
    }
}
