package com.example.lzw.myapp.Contacts;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.RawContacts.Data;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import com.example.lzw.myapp.IReportBack;

/**
 * Created by Administrator on 2017/6/12.
 */

public class AddProfileContactFunctionListener extends ContactDataFunctionListener {

    private static Uri PROFILE_DATA_URI=Uri.parse("content://com.android.contacts/data");

    public AddProfileContactFunctionListener(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void addProfileContact()
    {
        long rawContactId=insertProfileRawContact();
        this.mReportTo.reportBack(tag,"RawcontactId:"+rawContactId);
        insertProfileNickName(rawContactId);
        insertProfilePhoneNumber(rawContactId);
        showProfileRawContactsDataForRawContact(rawContactId);
    }

    private void showProfileRawContactsDataForRawContact(long rawContactId) {
        Cursor cursor=null;
        try {
            Uri uri=ContactsContract.RawContactsEntity.PROFILE_CONTENT_URI;
            cursor=this.getACursor(uri,"_id="+rawContactId);
            this.printRawContactsData(cursor);
        } finally {
            if(cursor!=null)
                cursor.close();
        }
    }

    private void insertProfilePhoneNumber(long rawContactId) {
        ContentValues cv=new ContentValues();
        cv.put(Data.RAW_CONTACT_ID,rawContactId);
        cv.put(Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE);
        cv.put(Phone.NUMBER,"P123 123 "+rawContactId);
        cv.put(Phone.TYPE,Phone.TYPE_HOME);
        Uri uri=this.mContext.getContentResolver().insert(PROFILE_DATA_URI,cv);
        if(uri==null)
        {
            this.mReportTo.reportBack(tag,"Not able to insert phone number");
        }
        else
        {
            this.mReportTo.reportBack(tag,uri.toString());
        }
    }

    private void insertProfileNickName(long rawContactId) {
        ContentValues cv=new ContentValues();
        cv.put(Data.RAW_CONTACT_ID,rawContactId);
        cv.put(Data.MIMETYPE,CommonDataKinds.Nickname.CONTENT_ITEM_TYPE);
        cv.put(CommonDataKinds.Nickname.NAME,"LiuZW_"+rawContactId);
        Uri nickNameUri=this.mContext.getContentResolver().insert(PROFILE_DATA_URI,cv);
        if(nickNameUri==null)
        {
            this.mReportTo.reportBack(tag,"Not able to insert nickname");
        }
        else
        {
            this.mReportTo.reportBack(tag,nickNameUri.toString());
        }
    }

    private long insertProfileRawContact() {
        ContentValues cv=new ContentValues();
        cv.put(RawContacts.ACCOUNT_TYPE,"com.google");
        cv.put(RawContacts.ACCOUNT_NAME,"lzw@gmail.com");
        Uri rawContactUri=this.mContext.getContentResolver().insert(ContactsContract.Profile.CONTENT_RAW_CONTACTS_URI,cv);
        long rawContactId= ContentUris.parseId(rawContactUri);
        return rawContactId;
    }
}
