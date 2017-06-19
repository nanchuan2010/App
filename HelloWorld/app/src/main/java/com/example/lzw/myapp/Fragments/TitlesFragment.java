package com.example.lzw.myapp.Fragments;

import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lzw.myapp.Fragments.FragmentActivity;
import com.example.lzw.myapp.Fragments.Shakespeare;

/**
 * Created by LZW on 2016/08/21.
 */
public class TitlesFragment extends ListFragment {
    private FragmentActivity myActivity=null;
    int mCurCheckPosition=0;

    @Override
    public void onAttach(Activity activity) {
        Log.v(FragmentActivity.TAG,"in TitlesFragment onAttach;activity is :"+myActivity);

        super.onAttach(activity);
        this.myActivity=(FragmentActivity)activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v(FragmentActivity.TAG,"in TitlesFragment onActivityCreated. saveState contains:");
        if(savedInstanceState!=null)
        {
            for (String key :
                    savedInstanceState.keySet()) {
                Log.v(FragmentActivity.TAG, "   " + key);
            }
        }
        else
        {
            Log.v(FragmentActivity.TAG,"    savedState is null");
        }
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1, Shakespeare.TITLES));

        if(savedInstanceState!=null)
        {
            mCurCheckPosition=savedInstanceState.getInt("curChoice",0);
        }

        ListView lv=getListView();
        lv.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        lv.setSelection(mCurCheckPosition);
        myActivity.showDetails(mCurCheckPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.v(FragmentActivity.TAG,"in TitlesFragment onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice",mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(FragmentActivity.TAG,"in TitlesFragment onListItemClick.pos ="+position);
        myActivity.showDetails(position);
        mCurCheckPosition=position;

    }

    @Override
    public void onDetach() {
        Log.v(FragmentActivity.TAG,"in TitlesFragment onDetach");
        super.onDetach();
        myActivity=null;
    }
}
