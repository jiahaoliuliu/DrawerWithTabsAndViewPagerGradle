package com.jiahaoliuliu.tabswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WhiteFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.white_fragment_layout, container, false);
        contentTextView = (TextView) rootView.findViewById(R.id.contentTextView);
    	return rootView;
    }
}
