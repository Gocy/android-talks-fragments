package com.gluketic.example.fragments.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.gluketic.example.fragments.ConnectivityUtils;
import com.gluketic.example.fragments.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Goran Luketic
 */
public class HomeFragment extends Fragment {

    @Nullable
    @BindView(R.id.web_view)
    WebView mWebView;

    private Unbinder mUnbinder;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home, container, false);
        mUnbinder = ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (!ConnectivityUtils.hasActiveNetworkConnection(getActivity())) {
            Toast.makeText(getActivity(), getString(R.string.error_internet), Toast.LENGTH_SHORT).show();
            return;
        }
        if (mWebView != null) {
            mWebView.setWebViewClient(new MyWebClient());
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.loadUrl(getString(R.string.web_url));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
