package com.example.lzw.myapp.Contacts;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.MenuItem;

import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

public class TestContactsDriverActivity extends DebugActivity implements IReportBack{

    public static final String tag="Test Contacts";

    AccountsFunctionTester accountsFunctionTester=null;
    AggregatedContactFunctionTester aggregatedContactFunctionTester=null;
    RawContactFunctionTester rawContactFunctionTester=null;

    public TestContactsDriverActivity() {
        super(R.menu.contacts_menu, tag);
        accountsFunctionTester=new AccountsFunctionTester(this,this);
        aggregatedContactFunctionTester=new AggregatedContactFunctionTester(this,this);
        rawContactFunctionTester=new RawContactFunctionTester(this,this);
    }

    @Override
    protected boolean onMenuItemSelected(MenuItem item) {
        Log.d(tag,item.getTitle().toString());
        if(item.getItemId()==R.id.menu_show_accounts)
        {
            accountsFunctionTester.testAccounts();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_contact_cursor)
        {
            aggregatedContactFunctionTester.listContactCursorFields();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_contacts)
        {
            aggregatedContactFunctionTester.listContacts();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_single_contact_cursor)
        {
            aggregatedContactFunctionTester.listLookupUriColumns();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_rc_cursor)
        {
            rawContactFunctionTester.showRawContactsCursor();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_rc_all)
        {
            rawContactFunctionTester.showAllRawContacts();
            return true;
        }

        return true;
    }

}
