package com.example.lzw.myapp.Contacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.Data;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by LZW on 2017/06/12.
 */
public class AddContactFunctionListener extends ContactDataFunctionListener {
    public AddContactFunctionListener(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void addContact()
    {
        long rawContactId=insertRawContact();
        this.mReportTo.reportBack(tag,"RawcontactId:"+rawContactId);
        insertName(rawContactId);
        insertPhoneNumber(rawContactId);
        showRawContactsDataForRawContact(rawContactId);
    }

    private void insertPhoneNumber(long rawContactId) {
        ContentValues cv=new ContentValues();
        cv.put(Data.RAW_CONTACT_ID,rawContactId);
        cv.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        cv.put(Phone.NUMBER,"123 123 "+rawContactId);
        cv.put(Phone.TYPE,Phone.TYPE_HOME);
        this.mContext.getContentResolver().insert(Data.CONTENT_URI,cv);
    }

    private void insertName(long rawContactId) {
        ContentValues cv=new ContentValues();
        cv.put(Data.RAW_CONTACT_ID,rawContactId);
        cv.put(Data.MIMETYPE,StructuredName.CONTENT_ITEM_TYPE);
        cv.put(StructuredName.DISPLAY_NAME,"Liu_"+rawContactId);
        this.mContext.getContentResolver().insert(Data.CONTENT_URI,cv);
    }

    private void showRawContactsDataForRawContact(long rawContactId)
    {
        Cursor cursor=null;

        try {
            Uri uri=ContactsContract.RawContactsEntity.CONTENT_URI;
            cursor=this.getACursor(uri,"_id="+rawContactId);
            this.printRawContactsData(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    private long insertRawContact() {
        ContentValues cv=new ContentValues();
        cv.put(RawContacts.ACCOUNT_TYPE,"com.google");
        cv.put(RawContacts.ACCOUNT_NAME,"lzw@gmail.com");
        Uri rawContactUri=this.mContext.getContentResolver().insert(RawContacts.CONTENT_URI,cv);
        long rawContactId= ContentUris.parseId(rawContactUri);
        return rawContactId;
    }


}
