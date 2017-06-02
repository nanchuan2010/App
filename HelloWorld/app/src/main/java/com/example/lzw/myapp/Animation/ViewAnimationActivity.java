package com.example.lzw.myapp.Animation;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lzw.myapp.R;


public class ViewAnimationActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_animation);
        setupListView();
        this.setupButton();
    }

    private void setupListView() {
        String[] listItems=new String[]{"Item 1","Item 2","Item 3","Item 4","Item 5","Item 6"};
        ArrayAdapter<String> listItemAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        ListView lv=(ListView)this.findViewById(R.id.list_view_id);
        lv.setAdapter(listItemAdapter);
    }

    private void setupButton() {
        Button b=(Button)this.findViewById(R.id.btn_animate);
        b.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v)
            {
                animateListView();
            }
        });
    }

    private void animateListView()
    {
        ListView lv=(ListView)this.findViewById(R.id.list_view_id);

        float cx=(float)(lv.getWidth()/2.0);
        float cy=(float)(lv.getWidth()/2.0);

        ViewAnimation animation=new ViewAnimation(cx,cy);
        animation.setAnimationListener(new ViewAnimationListener());
        lv.startAnimation(animation);
    }

}
