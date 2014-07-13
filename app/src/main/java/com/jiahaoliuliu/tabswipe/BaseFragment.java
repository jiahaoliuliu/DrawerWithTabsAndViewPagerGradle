package com.jiahaoliuliu.tabswipe;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class BaseFragment extends Fragment{

    /**
     * Te key used to pass the content text to the fragment
     */
    public static final String FRAGMENT_CONTENT_KEY = "com.jiahaoliuliu.tabswipe.fragmentcontentkey";

    protected TextView contentTextView;
    private String content;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Get the content as argument of the fragment
        Bundle args = getArguments();
        if (args != null && args.containsKey(FRAGMENT_CONTENT_KEY)) {
            content = args.getString(FRAGMENT_CONTENT_KEY);
        }

        if (content != null && contentTextView != null) {
            contentTextView.setText(content);
        }
    }
}
