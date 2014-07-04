package com.jiahaoliuliu.tabswipe;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class DrawerFragment extends ListFragment {
    private String[] japaneseMenu = new String[] {"Menu", "Special menu"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /** Creating an array adapter to store the list of countries **/
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        inflater.getContext(), android.R.layout.simple_list_item_1, japaneseMenu);

        /** Setting the list adapter for the ListFragment */
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
