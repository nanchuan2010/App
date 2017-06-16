package com.example.lzw.myapp.Controls;

import android.os.Bundle;
import android.app.Activity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

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
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
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
        curMenu=menu;
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
}
