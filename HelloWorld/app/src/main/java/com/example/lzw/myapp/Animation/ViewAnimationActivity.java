package com.example.lzw.myapp.Animation;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.lzw.myapp.R;


public class ViewAnimationActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation_list_layout);
        setupListView();
        this.setupButton();
    }

    private void setupListView() {
        String[] listItems=new String[]{"Item 1","Item 2","Item 3","Item 4","Item 5","Item 6"};
        ArrayAdapter<String> listItemAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems);
        ListView lv=(ListView)this.findViewById(R.id.list_view_id);
        lv.setLayoutAnimation(null);
        lv.setAdapter(listItemAdapter);
    }

    private void setupButton() {
        Button btn=(Button)this.findViewById(R.id.btn_alpha);
        btn.setText("View Animation 1");
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.btn_alpha_translate);
        btn.setText("View Animation 2");
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.btn_rotate);
        btn.setText("View Animation 3");
        btn.setOnClickListener(this);
    }

    private void viewAnimateListView1()
    {
        ListView lv=(ListView)this.findViewById(R.id.list_view_id);
        lv.startAnimation(new ViewAnimation());
    }

    private void viewAnimateListView2(int type)
    {
        ListView lv=(ListView)this.findViewById(R.id.list_view_id);
        lv.startAnimation(new ViewAnimation());

        float cx=(float)(lv.getWidth()/2.0);
        float cy=(float)(lv.getWidth()/2.0);

        ViewAnimation animation=new ViewAnimation(cx,cy,type);
        animation.setAnimationListener(new ViewAnimationListener());
        lv.startAnimation(animation);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.btn_alpha:
                viewAnimateListView1();
                break;
            case R.id.btn_alpha_translate:
                viewAnimateListView2(2);
                break;
            case R.id.btn_rotate:
                viewAnimateListView2(3);
                break;

        }
    }
}
