package com.jiahaoliuliu.tabswipe.interfaces;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public interface OnShowNewFragmentRequestedListener {

    public enum FragmentId {
        MENU_FRAGMENT("Menu"), SPECIAL_MENU_FRAGMENT ("Special menu");

        // The title to be shown in the drawer
        private String title;

        FragmentId (String title) {
            this.title = title;
        }

        public String getTitle() {
            return title;
        }

        public static FragmentId toFragmentId(String fragmentId) {
            try {
                return valueOf(fragmentId);
            } catch (Exception ex) {
                // By default show the normal menu fragment
                return MENU_FRAGMENT;
            }
        }

        /**
         * Get the list of titles of each fragment Id.
         * @return A list of titles corresponding to each fragment Id
         */
        public static List<String> getTitles() {
            List<String> titles = new ArrayList<String>();
            for (FragmentId fragmentId: values()) {
                titles.add(fragmentId.getTitle());
            }
            return titles;
        }
    }

    /**
     * Request to show a new fragment
     * @param fragmentId     The id of the fragment to show
     * @param addToBackStack If it should be added to the back stack
     */
    public abstract void showNewFragmentRequested(FragmentId fragmentId, boolean addToBackStack);

    /**
     * Request to shwo a new fragment with an extra argument
     * @param fragmentId     The id of the fragment to show
     * @param addToBackStack If it should be added to the back stack
     * @param bundle         The extra argument which the fragment should take
     */
    public abstract void showNewFragmentRequested(FragmentId fragmentId, boolean addToBackStack, Bundle bundle);
}
