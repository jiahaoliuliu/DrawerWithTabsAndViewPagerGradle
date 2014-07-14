package com.jiahaoliuliu.tabswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

public class ViewPagerAdapter extends FragmentPagerAdapter{
    private static final String TAG = "ViewPagerAdapter";

	private String[] tabsTitles;

    public ViewPagerAdapter(FragmentManager fm, String[] tabsTitles) {
		super(fm);
        this.tabsTitles = tabsTitles;
	}

    @Override
    public CharSequence getPageTitle(int position) {
        return tabsTitles[position];
    }

    @Override
	public Fragment getItem(int index) {
        Fragment fragmentToShow;
        Log.v(TAG, "Getting the new fragment for the position " + index);

		switch(index % 2) {
		case 0:
            fragmentToShow = new BlackFragment();
            Log.v(TAG, "The fragment to be shown will be the black fragment");
            break;
        default:
		case 1:
			fragmentToShow = new WhiteFragment();
            Log.v(TAG, "The fragment to be shown will be the white fragment");
            break;
		}

        Bundle args = new Bundle();
        args.putString(BlackFragment.FRAGMENT_CONTENT_KEY, tabsTitles[index]);
        fragmentToShow.setArguments(args);
        Log.v(TAG, "Passing as argument " + tabsTitles[index]);
        return fragmentToShow;
	}


	
	@Override
	public int getCount() {
        return tabsTitles.length;
	}
}
