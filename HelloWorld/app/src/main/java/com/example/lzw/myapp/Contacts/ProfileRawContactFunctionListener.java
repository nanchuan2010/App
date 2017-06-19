package com.example.lzw.myapp.Contacts;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by Administrator on 2017/6/12.
 */

public class ProfileRawContactFunctionListener extends AggregatedContactFunctionListener {

    public ProfileRawContactFunctionListener(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void showAllRawContracts()
    {
        Cursor cursor=null;

        try {
            String whereClause=null;
            cursor=this.getACursor(ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI,whereClause);
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
}
