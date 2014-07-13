package com.jiahaoliuliu.tabswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jiahaoliuliu.tabswipe.interfaces.OnShowNewFragmentRequestedListener;

public class BlackFragment extends BaseFragment{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	View rootView = inflater.inflate(R.layout.black_fragment_layout, container, false);
        contentTextView = (TextView) rootView.findViewById(R.id.contentTextView);
        return rootView;
    }
}
