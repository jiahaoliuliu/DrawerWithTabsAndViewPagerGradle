package com.jiahaoliuliu.tabswipe;

import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.ActionBar.Tab;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.MenuItem;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends SherlockFragmentActivity {

	private DrawerLayout mDrawerLayout;
	private LinearLayout mDrawer;
	private ActionBarDrawerToggle mDrawerToggle;
	
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;

	// Tabs titles
	private String[] tabsTitles = {"Tab1", "Tab2", "Tab3", "Tab4"};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Drawer
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawer = (LinearLayout)findViewById(R.id.drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

		getSupportActionBar().setHomeButtonEnabled(true);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		mDrawerToggle = new ActionBarDrawerToggle(
				this,
				mDrawerLayout,
				R.drawable.ic_drawer,
				R.string.drawer_open,
				R.string.drawer_close) {
			
			public void onDrawerClosed(View view) {
				super.onDrawerClosed(view);
			}
			
			public void onDrawerOpened(View drawerView) {
				// Set the title on the action when drawer open
				getSupportActionBar().setTitle("Title");
				super.onDrawerOpened(drawerView);
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
		
		// Initialization
		final TabHost tabHost=(TabHost)findViewById(android.R.id.tabhost);
		tabHost.setup();
		
		for (int i = 0; i < tabsTitles.length; i++) {
			String tabName = tabsTitles[i];
			TabHost.TabSpec spec=tabHost.newTabSpec(tabName);
			spec.setContent(R.id.fakeTabContent);
			spec.setIndicator(tabName);
			tabHost.addTab(spec);
		}

		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getSupportActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager(), tabsTitles.length);
		viewPager.setAdapter(mAdapter);
		tabHost.setOnTabChangedListener(new OnTabChangeListener() {
			
			@Override
			public void onTabChanged(String tabId) {
				for (int i = 0; i < tabsTitles.length; i++) {
					if (tabId.equals(tabsTitles[i])) {
						viewPager.setCurrentItem(i);
						break;
					}
				}
			}
		});

		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				tabHost.setCurrentTab(position);
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getItemId() == android.R.id.home) {
			if (mDrawerLayout.isDrawerOpen(mDrawer)) {
				mDrawerLayout.closeDrawer(mDrawer);
			} else {
				mDrawerLayout.openDrawer(mDrawer);
			}
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
	}
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggles
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
}
