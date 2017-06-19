package com.example.lzw.myapp.Contacts;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

import com.example.lzw.myapp.BaseListener;
import com.example.lzw.myapp.IReportBack;

/**
 * Created by Administrator on 2017/6/11.
 */

public class AccountsFunctionListener extends BaseListener {

    private static String tag="tc>";

    public AccountsFunctionListener(Context ctx, IReportBack target) {
        super(ctx, target);
    }

    public void testAccounts()
    {
        AccountManager am=AccountManager.get(this.mContext);
        Account[] accounts=am.getAccounts();
        for (Account ac :
                accounts) {
            String acname = ac.name;
            String actype=ac.type;
            this.mReportTo.reportBack(tag,acname+":"+actype);
        }

        if(accounts.length==0)
        {
            this.mReportTo.reportBack(tag,"No accounts detected other then the device");
        }
    }
}
