package com.example.lzw.myapp.Contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by Administrator on 2017/6/12.
 */

public class RawContactFunctionListener extends AggregatedContactFunctionListener {

    public RawContactFunctionListener(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void showRawContactsCursor()
    {
        Cursor cursor=null;
        try {
            cursor=this.getACursor(ContactsContract.RawContacts.CONTENT_URI,null);
            this.printCursorColumnNames(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    public void showAllRawContacts()
    {
        Cursor cursor=null;

        try {
            cursor=this.getACursor(getRawContactsUri(),null);
            this.printRawContacts(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    private void printRawContacts(Cursor cursor) {
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            RawContact rc=new RawContact();
            rc.fillinFrom(cursor);
            this.mReportTo.reportBack(tag,rc.toString());
        }
    }


    public Uri getRawContactsUri() {
        return ContactsContract.RawContacts.CONTENT_URI;
    }

    public void showRawContactsForFirstAggregatedContact()
    {
        AggregatedContact ac=getFirstContact();
        Cursor cursor=null;
        try {
            cursor=this.getACursor(getRawContactsUri(),getClause(ac.id));
            this.printRawContacts(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    private String getClause(String contactId) {
        return "contact_id="+contactId;
    }


}
