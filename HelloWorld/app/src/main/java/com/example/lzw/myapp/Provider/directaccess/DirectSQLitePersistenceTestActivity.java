package com.example.lzw.myapp.Provider.directaccess;

import android.util.Log;
import android.view.MenuItem;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.Provider.MonitoredDebugActivity;
import com.example.lzw.myapp.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public class DirectSQLitePersistenceTestActivity  extends MonitoredDebugActivity implements IReportBack {

    public static final String tag="DirectSQLitePersistenceTestActivity";
    private SQLitePersistenceTester sqLitePersistenceTester=null;

    public DirectSQLitePersistenceTestActivity()
    {
        super(R.menu.test_book_persistence_menu,tag);
        this.retainState();
        sqLitePersistenceTester=new SQLitePersistenceTester(this,this);
    }

    @Override
    public boolean onMenuItemSelected(int featureId, MenuItem item) {
        Log.d(tag,item.getTitle().toString());
        if(item.getItemId()==R.id.menu_add_book)
        {
            sqLitePersistenceTester.addBook();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_books)
        {
            sqLitePersistenceTester.showBooks();
            return true;
        }
        if(item.getItemId()==R.id.menu_delete_book)
        {
            sqLitePersistenceTester.removeBook();
            return true;
        }
        return true;
    }

    @Override
    public void reportTransient(String tag, String message) {

    }

    @Override
    protected boolean onMenuItemSelected(MenuItem item) {
        return false;
    }
}
