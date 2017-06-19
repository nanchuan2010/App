package com.example.lzw.myapp.Contacts;

import android.util.Log;
import android.view.MenuItem;

import com.example.lzw.myapp.DebugActivity;
import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

public class TestContactsDriverActivity extends DebugActivity implements IReportBack{

    public static final String tag="Test Contacts";

    AccountsFunctionListener accountsFunctionTester=null;
    AggregatedContactFunctionListener aggregatedContactFunctionTester=null;
    RawContactFunctionListener rawContactFunctionTester=null;
    ContactDataFunctionListener contactDataFunctionTester=null;
    AddContactFunctionListener addContactFunctionTester=null;
    ProfileRawContactFunctionListener profileRawContactFunctionTester=null;
    AddProfileContactFunctionListener addProfileContactFunctionTester=null;

    public TestContactsDriverActivity() {
        super(R.menu.contacts_menu, tag);
        accountsFunctionTester=new AccountsFunctionListener(this,this);
        aggregatedContactFunctionTester=new AggregatedContactFunctionListener(this,this);
        rawContactFunctionTester=new RawContactFunctionListener(this,this);
        contactDataFunctionTester=new ContactDataFunctionListener(this,this);
        addContactFunctionTester=new AddContactFunctionListener(this,this);
        profileRawContactFunctionTester=new ProfileRawContactFunctionListener(this,this);
        addProfileContactFunctionTester=new AddProfileContactFunctionListener(this,this);
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
        if(item.getItemId()==R.id.menu_show_rc)
        {
            rawContactFunctionTester.showRawContactsForFirstAggregatedContact();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_rce_cursor)
        {
            contactDataFunctionTester.showRawContactsEntityCursor();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_rce_data)
        {
            contactDataFunctionTester.showRawContactsData();
            return true;
        }
        if(item.getItemId()==R.id.menu_add_contact)
        {
            addContactFunctionTester.addContact();
            return true;
        }
        if(item.getItemId()==R.id.menu_show_profile_raw_contacts)
        {
            profileRawContactFunctionTester.showAllRawContracts();
            return true;
        }
        if(item.getItemId()==R.id.menu_add_profile_contact)
        {
            addProfileContactFunctionTester.addProfileContact();
            return true;
        }

        return true;
    }

}
