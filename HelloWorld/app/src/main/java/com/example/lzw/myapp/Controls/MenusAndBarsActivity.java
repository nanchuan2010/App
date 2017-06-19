package com.example.lzw.myapp.Controls;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lzw.myapp.DebugActivity;
import com.example.lzw.myapp.IReportBack;
import com.example.lzw.myapp.R;

public class MenusAndBarsActivity extends Activity implements View.OnClickListener{

    private Menu curMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_menus_bars);

        Button btn=(Button)findViewById(R.id.removeGroup);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.checkableGroup);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.enabledGroup);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.visibleGroup);
        btn.setOnClickListener(this);

        TextView tv=(TextView)this.findViewById(R.id.tvContextMenu);
        registerForContextMenu(tv);

        btn=(Button)findViewById(R.id.btnShowPopup);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnStandardActionBar);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnTabbedActionBar);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnListActionBar);
        btn.setOnClickListener(this);

        btn=(Button)findViewById(R.id.btnSearchView);
        btn.setOnClickListener(this);

        showSearchView();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        final Intent queryIntent=getIntent();
        doSearchQuery(queryIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        menu.add(0,1,0,"item1");
        menu.add(0,2,1,"item2");
        menu.add(0,3,2,"item3");

        int group1=1;
        menu.add(group1,1,3,"g1.item1");
        menu.add(group1,2,4,"g1.item2");

        int group2=2;
        menu.add(group2,3,5,"g2.item1");
        MenuItem item=menu.add(group2,4,6,"g2.item2");
        item.setIcon(R.drawable.icon);

        addSubMenu(menu);

/*        MenuItem search=dialogs_menu.add(3,1,8,"Search");
        search.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        search.setActionView(new android.widget.SearchView(this));*/

        curMenu=menu;

        super.onCreateOptionsMenu(menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private void addSubMenu(Menu menu)
    {
        int base=Menu.FIRST+100;
        SubMenu sm=menu.addSubMenu(base,base+1,7,"submenu...");
        sm.add(base,base+2,base+2,"sub item1");
        sm.add(base,base+3,base+3,"sub item2");
        sm.add(base,base+4,base+4,"sub item3");

    }

    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id)
        {
            case R.id.removeGroup:
                curMenu.removeGroup(1);
                break;
            case R.id.checkableGroup:
                curMenu.setGroupCheckable(2,true,false);
                break;
            case R.id.enabledGroup:
                curMenu.setGroupEnabled(2,false);
                break;
            case R.id.visibleGroup:
                curMenu.setGroupVisible(2,false);
                break;
            case R.id.btnShowPopup:
                showPopupMenu();
                break;
            case R.id.btnStandardActionBar:
                showStandardActionBar();
                break;
            case R.id.btnTabbedActionBar:
                showTabbedActionBar();
                break;
            case R.id.btnListActionBar:
                showListActionBar();
                break;
            case R.id.btnSearchView:
                showSearchView();
                break;

        }
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.setHeaderTitle("Sample Context Menu");
        menu.add(200,200,200,"item1");
        menu.add(200,201,201,"item2");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        if(item.getItemId()==200)
        {
            return true;
        }

        return super.onContextItemSelected(item);
    }

    private void showPopupMenu()
    {
        TextView tv=(TextView) findViewById(R.id.tvPopUpMenu);
        PopupMenu popup=new PopupMenu(this,tv);

        popup.inflate(R.menu.controls_my_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                String msg=menuItem.getTitle().toString();
                Toast.makeText(getApplicationContext(),msg,Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        popup.show();
    }

    private void showStandardActionBar()
    {
        ActionBar bar=this.getActionBar();
        bar.setTitle("Some title of your choosing");
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
    }

    private void showTabbedActionBar()
    {
        ActionBar bar=this.getActionBar();
        bar.setTitle("Tabbed Action Bar");
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        TabListener listener=new TabListener(this);
        Tab tab1=bar.newTab();
        tab1.setText("Tab1");
        tab1.setTabListener(listener);
        bar.addTab(tab1);

        Tab tab2=bar.newTab();
        tab2.setText("Tab2");
        tab2.setTabListener(listener);
        bar.addTab(tab2);

    }


    /*
     * //TODO 搜索待研究
     */
    private void showListActionBar() {
        ActionBar bar=this.getActionBar();
        bar.setTitle("title");
        bar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        bar.setListNavigationCallbacks(new SimpleSpinnerArrayAdapter(this),new ListListener());
    }


    private void showSearchView() {
        final Intent queryIntent=getIntent();
        doSearchQuery(queryIntent);
    }

    private void doSearchQuery(Intent queryIntent) {
        final String queryAction = queryIntent.getAction();
        String msg="";
        if (!(Intent.ACTION_SEARCH.equals(queryAction))) {
            msg="intent NOT for search";
        } else{
           msg=queryIntent.getStringExtra(SearchManager.QUERY);
        }

        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();

    }
}
