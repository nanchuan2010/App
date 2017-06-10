package com.example.lzw.myapp.Provider;

import android.view.View;

import com.example.lzw.myapp.Provider.directaccess.DirectSQLitePersistenceTestActivity;
import com.example.lzw.myapp.R;

/**
 * Created by Administrator on 2017/6/9.
 */

public class TestPersistenceDriverActivity extends BaseActivity {
    public static String tag="TestPersistenceDriverActivity";

    public TestPersistenceDriverActivity()
    {
        super(R.layout.antivity_persistence_driver,-1,tag);
    }

    public void startTestContentProviderActivity(View btn1)
    {
        gotoActivity(ContentProviderTestActivity.class);
    }

    public void startTestDirectSQLiteStorageActivity(View btn1)
    {
        gotoActivity(DirectSQLitePersistenceTestActivity.class);
    }
}
