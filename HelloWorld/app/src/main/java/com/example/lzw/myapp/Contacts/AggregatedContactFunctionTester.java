package com.example.lzw.myapp.Contacts;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by Administrator on 2017/6/11.
 */

public class AggregatedContactFunctionTester extends URIFunctionTester {
    public AggregatedContactFunctionTester(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    private Cursor getContacts()
    {
        Uri uri= ContactsContract.Contacts.CONTENT_URI;
        String sortOrder=ContactsContract.Contacts.DISPLAY_NAME+" COLLATE LOCALIZED ASC";
        Activity activity=(Activity)this.mContext;
        return activity.getContentResolver().query(uri,null,null,null,sortOrder);
    }

    public void listContactCursorFields()
    {
        Cursor cursor=null;
        try {
            cursor=getContacts();
            int i=cursor.getColumnCount();
            this.mReportTo.reportBack(tag,"Number of columns:"+i);
            this.printCursorColumnNames(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    public void listContacts()
    {
        Cursor cursor=null;

        try {
            cursor=getContacts();
            int i= cursor.getColumnCount();
            this.mReportTo.reportBack(tag,"Number of columns:"+i);
            this.printLookupKeys(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    private void printLookupKeys(Cursor cursor) {
        for (cursor.moveToFirst();!cursor.isAfterLast(); cursor.moveToNext())
        {
            String name=this.getContactName(cursor);
            String lookupKey=this.getLookupKey(cursor);
            String luri=this.getLookupUri(lookupKey);
            this.mReportTo.reportBack(tag,name+":"+lookupKey);
            this.mReportTo.reportBack(tag,name+":"+luri);
        }
    }

    private String getLookupUri(String lookupKey) {
        String luri=ContactsContract.Contacts.CONTENT_LOOKUP_URI+"/"+lookupKey;
        return luri;
    }

    private String getLookupKey(Cursor cursor) {
        int lookupkeyIndex=cursor.getColumnIndex(ContactsContract.Contacts.LOOKUP_KEY);
        return cursor.getString(lookupkeyIndex);
    }

    private String getContactName(Cursor cursor) {
        return Utils.getColumnValue(cursor,ContactsContract.Contacts.DISPLAY_NAME);
    }


    public void listLookupUriColumns()
    {
        Cursor cursor=null;
        try {
            cursor=getContacts();
            String firstContactLookupUri=getFirstLookupUri(cursor);
            printLookupUriColumns(firstContactLookupUri);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    private void printLookupUriColumns(String lookupUri) {
        Cursor cursor=null;

        cursor=getASingleContact(lookupUri);
        int i=cursor.getColumnCount();
        this.mReportTo.reportBack(tag,"Number of columns:"+i);
        int j=cursor.getCount();
        this.mReportTo.reportBack(tag,"Number of rows:"+j);
        this.printCursorColumnNames(cursor);
    }

    private Cursor getASingleContact(String lookupUri) {
        Activity activity=(Activity)this.mContext;
        return activity.getContentResolver().query(Uri.parse(lookupUri),null,null,null,null);
    }

    private String getFirstLookupUri(Cursor cursor) {
        cursor.moveToFirst();
        if(cursor.isAfterLast())
        {
            Log.d(tag,"No rows to get the first contact");
            return null;
        }
        String lookupKey=this.getLookupKey(cursor);
        return this.getLookupUri(lookupKey);
    }

    protected AggregatedContact getFirstContact()
    {
        Cursor cursor=null;

        try {
            cursor=getContacts();
            cursor.moveToFirst();
            if(cursor.isAfterLast())
            {
                Log.d(tag,"No contacts");
                return null;
            }
            AggregatedContact firstcontact=new AggregatedContact();
            firstcontact.fillinFrom(cursor);
            return firstcontact;
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }
}
