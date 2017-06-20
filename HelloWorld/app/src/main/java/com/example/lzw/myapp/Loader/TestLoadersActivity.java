package com.example.lzw.myapp.Loader;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import com.example.lzw.myapp.R;

public class TestLoadersActivity extends MonitoredListActivity
        implements LoaderManager.LoaderCallbacks<Cursor>
                    ,SearchView.OnQueryTextListener
{
    private static final String tag="TestLoadersActivity";

    SimpleCursorAdapter mAdapter;

    String mCurFilter;

    static final String[] PROJECTION=new String[]{ContactsContract.Data._ID,ContactsContract.Data.DISPLAY_NAME};
    static final String SELECTION="(("+ContactsContract.Data.DISPLAY_NAME+" NOTNULL) AND ("+
            ContactsContract.Data.DISPLAY_NAME+"!=''))";

    public TestLoadersActivity()
    {
        super(tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_loaders);
        //return ;
        this.mAdapter=createEmptyAdapter();
        this.setListAdapter(mAdapter);

        this.showProgressbar();
        getLoaderManager().initLoader(0,null,this);
    }

    private SimpleCursorAdapter createEmptyAdapter() {
        String[] fromColumns={ContactsContract.Data.DISPLAY_NAME};
        int[] toViews={android.R.id.text1};
        return new SimpleCursorAdapter(this,android.R.layout.simple_list_item_1,null,fromColumns,toViews);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(tag,"onCreateLoader for loaded id:"+id);
        Uri baseUri;
        if(mCurFilter!=null)
        {
            baseUri=Uri.withAppendedPath(ContactsContract.Contacts.CONTENT_FILTER_URI,Uri.encode(mCurFilter));
        }else
        {
            baseUri= ContactsContract.Contacts.CONTENT_URI;
        }

        String[] selectionArgs=null;
        String sortOrder=null;
        return new CursorLoader(this,baseUri,PROJECTION,SELECTION,selectionArgs,sortOrder);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(tag,"onLoadFinished for loader id: "+loader.getId());
        Log.d(tag,"Number of contacts found:"+cursor.getCount());
        this.hideProgressbar();
        this.mAdapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(tag,"onLoadedReset for loader id:"+loader.getId());
        this.showProgressbar();
        this.mAdapter.swapCursor(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem item=menu.add("Search");
        item.setIcon(android.R.drawable.ic_menu_search);
        item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        SearchView sv=new SearchView(this);
        sv.setOnQueryTextListener(this);
        item.setActionView(sv);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        mCurFilter=!TextUtils.isEmpty(newText)?newText:null;
        Log.d(tag,"Restarting the loader");
        getLoaderManager().restartLoader(0,null,this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return true;
    }

    private void showProgressbar()
    {
        View pbar=this.getProgressbar();
        pbar.setVisibility(View.VISIBLE);
        this.getListView().setVisibility(View.GONE);
        findViewById(android.R.id.empty).setVisibility(View.GONE);
    }

    private void hideProgressbar()
    {
        View pbar=this.getProgressbar();
        pbar.setVisibility(View.GONE);
        this.getListView().setVisibility(View.VISIBLE);
    }

    private View getProgressbar()
    {
        return findViewById(R.id.tla_pbar);
    }
}
