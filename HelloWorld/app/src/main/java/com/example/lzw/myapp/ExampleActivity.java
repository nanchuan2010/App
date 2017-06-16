package com.example.lzw.myapp;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.SpinnerAdapter;

public class ExampleActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.example);

//        EditText et=(EditText)findViewById(R.id.tv);
//        et.setText("Styling the content of an EditText Dynamically");
//        Spannable spn=(Spannable)et.getText();
//        spn.setSpan(new BackgroundColorSpan(Color.RED),0,7,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        spn.setSpan(new StyleSpan(Typeface.BOLD_ITALIC),0,7,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

    }

    public boolean isMultiPane()
    {
        return getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE;
    }

    public   static final String TAG= "FRAGMENTS";

    public void showDetails(int index)
    {
        Log.v(TAG,"in MainActivity showDetails("+index+")");
        if(isMultiPane())
        {
            DetailsFragment details=(DetailsFragment)getFragmentManager().findFragmentById(R.id.details);
            if((details==null)||(details.getShownIndex()!=index))
            {
                details=DetailsFragment.newInstance(index);
                Log.v(TAG,"about to run FragmentTransaction...");

                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.replace(R.id.details,details);
                ft.commit();
            }
        }
            else
            {
//                Intent intent=new Intent();
//                intent.setClass(this,DetailsActivity.class);
//                intent.putExtra("index",index);
//                startActivity(intent);
                // Otherwise we need to launch a new activity to display
                // the dialog fragment with selected text.
                Intent intent = new Intent();
                intent.setClass(this, DetailsActivity.class);
                intent.putExtra("index", index);
                startActivity(intent);

            }

    }

    private void setupSearchView(Menu menu)
    {
        SearchView searchView=(SearchView)menu.findItem(R.id.menu_search).getActionView();

        SearchManager searchManager=(SearchManager)getSystemService(Context.SEARCH_SERVICE);
        ComponentName cn=new ComponentName(this,SearchResultsActivity.class);
        SearchableInfo info=searchManager.getSearchableInfo(cn);

        searchView.setSearchableInfo(info);
        searchView.setIconifiedByDefault(false);
    }

    private void workwithListActionBar() {
        ActionBar bar=this.getActionBar();
        bar.setTitle("title");
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        bar.setListNavigationCallbacks(new SimpleSpinnerArrayAdapter(this),new ListListener());
    }

    public void workwithTabbedActionBar()
    {

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       // return true;
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.controls_my_menu,menu);
        setupSearchView(menu);

//        int group1=1;
//        menu.add(group1,1,1,"g1.item1");
//        menu.add(group1,2,2,"g1.item2");
//
//        int group2=2;
//        menu.add(group2,3,3,"g2.item1");
//        menu.add(group2,4,4,"g2.item2");
//
//        addSubMenu(menu);
//        menu.add(0,1,0,"item1");
//        menu.add(0,2,1,"item2");
//        menu.add(0,3,2,"item3");

        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
//        menu.setHeaderTitle("Sample Context Menu");
//        menu.add(200,200,200,"item1");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
//        if(item.getItemId()==200)
//        {
//            return true;
//        }
        return super.onContextItemSelected(item);
    }

    private void addSubMenu(Menu menu)
    {
        int base=Menu.FIRST+100;
        SubMenu sm=menu.addSubMenu(base,base+1,Menu.NONE,"submenu");
        sm.add(base,base+2,base+2,"sub itemm1");
        sm.add(base,base+3,base+3,"sub itemm2");
        sm.add(base,base+4,base+4,"sub itemm3");

        sm.setIcon(R.drawable.icon);

        sm.addSubMenu("abc");

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.menu_item1)
        {
            return true;
        }
        else if(item.getItemId()==R.id.menu_item2)
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showPopupMenu()
    {
        //EditText tv=(EditText) findViewById(R.id.textViewId);
//        PopupMenu popup=new PopupMenu(this,tv);
//
//        popup.inflate(R.menu.controls_my_menu);
//        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//            @Override
//            public boolean onMenuItemClick(MenuItem menuItem) {
//                    return true;
//            }
//        });
//
//        popup.show();
    }
}


class TestTabListener implements ActionBar.TabListener
{
    public TestTabListener(){}


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

    }
}

class SimpleSpinnerArrayAdapter extends ArrayAdapter<String> implements SpinnerAdapter
{
    public SimpleSpinnerArrayAdapter(Context ctx)
    {
        super(ctx,android.R.layout.simple_spinner_item,new String[]{"one","two"});
        this.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
            return super.getDropDownView(position, convertView, parent);
    }
}

class ListListener implements ActionBar.OnNavigationListener{
    public ListListener(){}

    @Override
    public boolean onNavigationItemSelected(int i, long l) {
        return true;
    }
}

