package com.jiahaoliuliu.tabswipe;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.common.view.SlidingTabLayout;

public class MenuFragment extends Fragment{

    private static final String TAG = "MenuFragment";

    private Activity activity;
    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    // Tabs titles
    private String[] tabsTitles = {"Salad", "Sushi (2pcs)", "Maki (8pcs)", "Roll (8 pcs)", "Sashimi (12 pcs)"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	
    	View rootView = inflater.inflate(R.layout.basic_slider_tab_and_view_pager_layout, container, false);
        // ViewPager
        mViewPager = (ViewPager) rootView.findViewById(R.id.viewpager);

        // Sliding tab layout
        mSlidingTabLayout = (SlidingTabLayout) rootView.findViewById(R.id.sliding_tabs);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v(TAG, "The activity seems to be created. Setting the adapter for the view pager");
        mViewPager.setAdapter(
                new ViewPagerAdapter(
                        getChildFragmentManager(), tabsTitles));
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
