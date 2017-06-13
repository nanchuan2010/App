package com.example.lzw.myapp.Contacts;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by Administrator on 2017/6/11.
 */

public class AggregatedContact {
    public String id;
    public String lookupUri;
    public String lookupKey;
    public String displayName;
    public void fillinFrom(Cursor c)
    {
        id= Utils.getColumnValue(c,"_ID");
        lookupKey=Utils.getColumnValue(c, ContactsContract.Contacts.LOOKUP_KEY);
        lookupUri=ContactsContract.Contacts.CONTENT_LOOKUP_URI+"/"+lookupUri;
        displayName=Utils.getColumnValue(c,ContactsContract.Contacts.DISPLAY_NAME);
    }
}
