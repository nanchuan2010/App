package com.example.lzw.myapp;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by LZW on 2016/08/21.
 */
public class TitlesFragment extends ListFragment {
    private ExampleActivity myActivity=null;
    int mCurCheckPosition=0;

    @Override
    public void onAttach(Activity activity) {
        Log.v(ExampleActivity.TAG,"in TitlesFragment onAttach;activity is :"+myActivity);

        super.onAttach(activity);
        this.myActivity=(ExampleActivity)activity;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        Log.v(ExampleActivity.TAG,"in TitlesFragment onActivityCreated. saveState contains:");
        if(savedInstanceState!=null)
        {
            for (String key :
                    savedInstanceState.keySet()) {
                Log.v(ExampleActivity.TAG, "   " + key);
            }
        }
        else
        {
            Log.v(ExampleActivity.TAG,"    savedState is null");
        }
        super.onActivityCreated(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,Shakespeare.TITLES));

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
        Log.v(ExampleActivity.TAG,"in TitlesFragment onSaveInstanceState");
        super.onSaveInstanceState(outState);
        outState.putInt("curChoice",mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.v(ExampleActivity.TAG,"in TitlesFragment onListItemClick.pos ="+position);
        myActivity.showDetails(position);
        mCurCheckPosition=position;

    }

    @Override
    public void onDetach() {
        Log.v(ExampleActivity.TAG,"in TitlesFragment onDetach");
        super.onDetach();
        myActivity=null;
    }
}
