package com.jiahaoliuliu.tabswipe;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter{

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

		switch(index % 2) {
		case 0:
            fragmentToShow = new BlackFragment();
            break;
        default:
		case 1:
			fragmentToShow = new WhiteFragment();
            break;
		}

        Bundle args = new Bundle();
        args.putString(BlackFragment.FRAGMENT_CONTENT_KEY, tabsTitles[index]);
        fragmentToShow.setArguments(args);
        return fragmentToShow;
	}
	
	@Override
	public int getCount() {
        return tabsTitles.length;
	}
}
