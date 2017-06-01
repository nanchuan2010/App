package com.example.lzw.myapp;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SearchResultsActivity extends Activity {

    private static String tag="SearchResultsActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        final Intent queryIntent=getIntent();
        doSearchQuery(queryIntent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        final Intent queryIntent=getIntent();
        doSearchQuery(queryIntent);
    }

    private void doSearchQuery(final Intent queryIntent) {
        final String queryAction=queryIntent.getAction();
        if(!(Intent.ACTION_SEARCH.equals(queryAction)))
        {
            Log.d(tag,"intent NOT for search");
            return;
        }
        final String queryString=queryIntent.getStringExtra(SearchManager.QUERY);
        Log.d(tag,queryString);
    }
}
