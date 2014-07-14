package com.jiahaoliuliu.tabswipe;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.jiahaoliuliu.tabswipe.interfaces.OnShowNewFragmentRequestedListener;

public class MainActivity extends ActionBarActivity
        implements OnShowNewFragmentRequestedListener {

    private static final String TAG = "MainActivity";

	private DrawerLayout mDrawerLayout;
	private FrameLayout mDrawer;
	private ActionBarDrawerToggle mDrawerToggle;

    private ActionBar actionBar;
    private Context context;
    private FragmentManager fragmentManager;

    // Fragments
    private Fragment menuFragment;
    private Fragment specialMenuFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

        context = this;

        actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Set the fragments
        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.drawer, new DrawerFragment()).commit();
        // The default fragment to show is the menu fragment
        showNewFragmentRequested(FragmentId.MENU_FRAGMENT, false);

		// Drawer
		mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
		mDrawer = (FrameLayout)findViewById(R.id.drawer);
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

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
				super.onDrawerOpened(drawerView);
			}
		};
		
		mDrawerLayout.setDrawerListener(mDrawerToggle);
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

    @Override
    public void showNewFragmentRequested(FragmentId fragmentId, boolean addToBackStack) {
        showNewFragmentRequested(fragmentId, addToBackStack, null);
    }

    @Override
    public void showNewFragmentRequested(FragmentId fragmentId,
                                         boolean addToBackStack, Bundle bundle) {
        if (fragmentId == null) {
            throw new NullPointerException("The fragment id cannot be null");
        }

        // Check if the fragment shown is the fragment requested
        Fragment fragmentShown = fragmentManager.findFragmentById(R.id.fragment_container);
        if (fragmentShown != null && fragmentId.toString().equals(fragmentShown.getTag())) {
            Log.i(TAG, "The user has selected the same fragment shown.");
            closeDrawer();
            return;
        }

        // Set the fragment to show
        Fragment fragmentToShow = null;
        switch (fragmentId) {
            case SPECIAL_MENU_FRAGMENT:
                // Lazy instantiation
                if (specialMenuFragment == null) {
                    specialMenuFragment = new SpecialMenusFragment();
                    // If the bundle is not null, it will be set as argument of the fragment
                    // According to the official doc, the argument must be set before it has
                    // been build. This is, before the Fragment has been attached
                    // Otherwise, an IllegalStateException will be throw.
                } else if (
                        bundle != null &&
                                specialMenuFragment.getArguments() != null) {
                    specialMenuFragment = new SpecialMenusFragment();
                }

                fragmentToShow = specialMenuFragment;
                break;
            case MENU_FRAGMENT:
                // By default, show the menu fragment
            default:
                // Lazy instantiation
                if (menuFragment == null) {
                    menuFragment = new MenuFragment();
                }

                // If the bundle is not null, it will be set as argument of the fragment
                // According to the official doc, the argument must be set before it has
                // been build. This is, before the Fragment has been attached
                // Otherwise, an IllegalStateException will be throw.
                if (
                        bundle != null &&
                                menuFragment.getArguments() != null) {
                    menuFragment = new MenuFragment();
                }

                fragmentToShow = menuFragment;
                fragmentId = FragmentId.MENU_FRAGMENT;
                break;
        }

        // Set the extra argument for the fragment
        if (bundle != null) {
            fragmentToShow.setArguments(bundle);
        }

        // Set the title to the action bar
        actionBar.setTitle(fragmentId.getTitle());

        // Start the transaction
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragmentToShow, fragmentId.toString());
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragmentId.toString());
        }
        fragmentTransaction.commit();

        closeDrawer();
    }

    /**
     * Close the drawer if it is open
     */
    private void closeDrawer() {
        // Close the drawer if it is open
        if (mDrawerLayout != null) {
            if (mDrawerLayout.isDrawerOpen(mDrawer)) {
                mDrawerLayout.closeDrawer(mDrawer);
            }
        }
    }
}
