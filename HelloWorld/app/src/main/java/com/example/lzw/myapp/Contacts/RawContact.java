package com.example.lzw.myapp.Contacts;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RawContact {
    public String rawContactId;
    public String aggregatedContactId;
    public String accountName;
    public String accountType;
    public String displayName;

    public void fillinFrom(Cursor cursor)
    {
        rawContactId= Utils.getColumnValue(cursor,"_ID");
        accountName=Utils.getColumnValue(cursor, ContactsContract.RawContacts.ACCOUNT_NAME);
        accountType=Utils.getColumnValue(cursor,ContactsContract.RawContacts.ACCOUNT_TYPE);
        aggregatedContactId=Utils.getColumnValue(cursor,ContactsContract.RawContacts.CONTACT_ID);
        displayName=Utils.getColumnValue(cursor,"display_name");
    }

    public String toString()
    {
        return displayName+"/"+accountName+":"+accountType
                +"/"+rawContactId
                +"/"+aggregatedContactId;
    }
}
