package com.example.lzw.myapp.Animation;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/06/01.
 */
public class LayoutAnimationActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_list_layout);
        setupListView();
    }


    private void setupListView()
    {
        String[] listItems=new String[]{
                "Item 1","Item 2","Item 3","Item 4","Item 5","Item 6"
        };

        ArrayAdapter<String> listItemAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        ListView lv=(ListView)this.findViewById(R.id.list_view_id);
        lv.setAdapter(listItemAdapter);
    }

    public void doClick(View view)
    {
        ListView lv=(ListView) findViewById(R.id.list_view_id);
        lv.setVisibility(View.GONE);

        Animation animation=null;

        switch (view.getId())
        {
            case R.id.btn_alpha:
                animation=AnimationUtils.loadAnimation(this,R.anim.alpha);
                break;
            case R.id.btn_alpha_translate:
                animation=AnimationUtils.loadAnimation(this,R.anim.alpha_translate);
                break;
            case R.id.btn_rotate:
                animation=AnimationUtils.loadAnimation(this,R.anim.rotate);
                break;
        }

        LayoutAnimationController controller=new LayoutAnimationController(animation);
        controller.setDelay(0.5f);
        controller.setOrder(LayoutAnimationController.ORDER_RANDOM);
        lv.setLayoutAnimation(controller);

        lv.setVisibility(View.VISIBLE);
    }
}
