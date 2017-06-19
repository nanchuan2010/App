package com.example.lzw.myapp.Contacts;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by LZW on 2017/06/12.
 */
public class ContactDataFunctionListener extends RawContactFunctionListener {
    public ContactDataFunctionListener(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void showRawContactsEntityCursor()
    {
        Cursor cursor=null;
        try {
            Uri uri= ContactsContract.RawContactsEntity.CONTENT_URI;
            cursor=this.getACursor(uri,null);
            this.printCursorColumnNames(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    public void showRawContactsData()
    {
        Cursor cursor=null;
        try {
            Uri uri=ContactsContract.RawContactsEntity.CONTENT_URI;
            cursor=this.getACursor(uri,"contact_id in (3,4,5)");
            this.printRawContactsData(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    protected void printRawContactsData(Cursor cursor) {
        for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext())
        {
            ContactData dataRecord=new ContactData();
            dataRecord.fillinFrom(cursor);
            this.mReportTo.reportBack(tag,dataRecord.toString());
        }
    }
}
