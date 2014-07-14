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

public class SpecialMenusFragment extends Fragment {

    private static final String TAG = "SpecialMenusFragment";

    private ViewPager mViewPager;
    private SlidingTabLayout mSlidingTabLayout;

    // Tabs titles
    private String[] tabsTitles = {"Sushi mix", "Special sushi", "Maguro sake set",
            "Sashimi sushi complete","Maki mix", "Special Maki", "Maki sushi combination","Sushi combination"};

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
        mViewPager.setAdapter(
                new ViewPagerAdapter(
                        getChildFragmentManager(), tabsTitles));
        mSlidingTabLayout.setViewPager(mViewPager);
    }
}
