package com.jiahaoliuliu.tabswipe;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.jiahaoliuliu.tabswipe.interfaces.OnShowNewFragmentRequestedListener;
import com.jiahaoliuliu.tabswipe.interfaces.OnShowNewFragmentRequestedListener.FragmentId;

public class DrawerFragment extends ListFragment {

    private FragmentId[] fragmentIds = fragmentIds = FragmentId.values();

    private OnShowNewFragmentRequestedListener onShowNewFragmentRequestedListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            onShowNewFragmentRequestedListener = (OnShowNewFragmentRequestedListener) activity;
        } catch (ClassCastException classCastException) {
            throw new ClassCastException("The attached activity must implements OnShowNewFragmentRequestedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        /** Creating an array adapter to store the list of countries **/
        ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(
                        inflater.getContext(), android.R.layout.simple_list_item_1, FragmentId.getTitles());

        /** Setting the list adapter for the ListFragment */
        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        FragmentId fragmentClickedId = fragmentIds[position];
        // For now it is not needed to add the transition to the back stack
        onShowNewFragmentRequestedListener.showNewFragmentRequested(fragmentClickedId, false);
    }
}
