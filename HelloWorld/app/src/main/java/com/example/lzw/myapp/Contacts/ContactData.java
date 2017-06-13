package com.example.lzw.myapp.Contacts;

import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/06/12.
 */
public class ContactData {
    public String rawContactId;
    public String aggregatedContactId;
    public String dataId;
    public String accountName;
    public String accountType;
    public String mimetype;
    public String data1;

    public void fillinFrom(Cursor cursor)
    {
        rawContactId= Utils.getColumnValue(cursor,"_ID");
        accountName=Utils.getColumnValue(cursor, ContactsContract.RawContacts.ACCOUNT_NAME);
        accountType=Utils.getColumnValue(cursor,ContactsContract.RawContacts.ACCOUNT_TYPE);
        aggregatedContactId=Utils.getColumnValue(cursor,ContactsContract.RawContacts.CONTACT_ID);
        mimetype=Utils.getColumnValue(cursor,ContactsContract.RawContactsEntity.MIMETYPE);
        data1=Utils.getColumnValue(cursor,ContactsContract.RawContactsEntity.DATA1);
        dataId=Utils.getColumnValue(cursor,ContactsContract.RawContactsEntity.DATA_ID);
    }

    public String toString()
    {
        return data1+"/"+mimetype
                +"/"+accountName+":"+accountType
                +"/"+data1
                +"/"+rawContactId
                +"/"+aggregatedContactId;
    }

}
