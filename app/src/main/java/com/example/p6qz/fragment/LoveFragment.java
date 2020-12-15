package com.example.p6qz.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.p6qz.R;


public class LoveFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_love, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        WebView web = view.findViewById(R.id.webview);
        web.loadUrl("https://www.baidu.com/index.php?tn=monline_3_dg");
        web.setWebViewClient(new WebViewClient());
    }
}