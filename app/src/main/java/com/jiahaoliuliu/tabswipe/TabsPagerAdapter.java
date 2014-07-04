package com.jiahaoliuliu.tabswipe;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter{

	/**
	 * The total number of tabs
	 */
	private int totalTabs;

	public TabsPagerAdapter(FragmentManager fm, int totalTabs) {
		super(fm);
		this.totalTabs = totalTabs;
	}

	@Override
	public Fragment getItem(int index) {
		switch(index % 2) {
		case 0:
			return new BlackFragment();
		case 1:
			return new WhiteFragment();
		}
		
		return null;
	}
	
	@Override
	public int getCount() {
		return totalTabs;
	}
}
