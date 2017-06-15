package com.example.lzw.myapp.Controls;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lzw.myapp.R;

import java.util.Formatter;


public class ControlsDemoActivity extends Activity {

    private Switch sw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_demo_activity);

        createTextControls();
        createButtonControls();
        createCheckBoxControls();
        createSwitchControls();
        createRadioControls();
        createImageViewControls();
        createDateTimeControls();
    }

    private static final String[] CONTRIES=new String[]{
            "Belgium", "France", "Italy", "Germany", "Spain"
    };

    private void createTextControls()
    {
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,CONTRIES);
        AutoCompleteTextView mytextView=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView1);
        mytextView.setAdapter(adapter);

        MultiAutoCompleteTextView mactv=(MultiAutoCompleteTextView)this.findViewById(R.id.multiAutoCompleteTextView1);
        mactv.setAdapter(adapter);

        mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
    }

    private void createButtonControls()
    {
        Button btn=(Button)this.findViewById(R.id.button1);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
                startActivity(intent);
            }
        });
    }


    private void createCheckBoxControls() {
        CheckBox fishCB=(CheckBox)findViewById(R.id.fishCB);
        if(fishCB.isChecked())
        {
            fishCB.toggle();
        }

        fishCB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Toast.makeText(compoundButton.getContext(),"The fish checkbox is now "+(isChecked?"checked":"not checked"),Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createSwitchControls() {
        sw=(Switch)findViewById(R.id.switchControl);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if(isChecked)
                    sw.setText("This switch is : on");
                else
                    sw.setText("This switch is : off");
            }
        });
    }

    private void createRadioControls() {
        RadioGroup radGrp=(RadioGroup)findViewById(R.id.rBtnGrp);
        int checkedRadioButtonId=radGrp.getCheckedRadioButtonId();
        radGrp.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int id) {
                String msg=null;
                switch (id)
                {
                    case -1:
                        msg="Choices cleared!";
                        break;
                    case R.id.chRBtn:
                        msg="Chose Chicken";
                        break;
                    case R.id.fishRBtn:
                        msg="Chose Fish";
                        break;
                    case R.id.stkRBtn:
                        msg="Chose Steak";
                        break;
                    default:
                        msg="Huh?";
                        break;
                }

                if(!msg.isEmpty())
                {
                    Toast.makeText(radioGroup.getContext(),msg,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createImageViewControls() {
        ImageView imgView=(ImageView)findViewById(R.id.image3);
        imgView.setImageResource(R.drawable.colored_ball1);
/*        imgView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.colored_ball1));
        imgView.setImageDrawable(Drawable.createFromPath("/mnt/sdcard/dave2.jpg"));
        imgView.setImageURI(Uri.parse("file://mnt/sdcard/dave2.jpg"));*/
    }


    private void createDateTimeControls() {
        TextView dateDefault=(TextView)findViewById(R.id.dateDefault);
        TextView timeDefault=(TextView)findViewById(R.id.timeDefault);

        DatePicker dp=(DatePicker)this.findViewById(R.id.datePicker);
        dateDefault.setText("Date defaulted to "+(dp.getMonth()+1)+"/"+dp.getDayOfMonth()+"/"+dp.getYear());
        dp.init(2008,11,10,null);

        TimePicker tp=(TimePicker)this.findViewById(R.id.timePicker);
        Formatter formatter=new Formatter();
        formatter.format("Time defaulted to %d:%02d",tp.getCurrentHour(),tp.getCurrentMinute());
        timeDefault.setText(formatter.toString());

        tp.setIs24HourView(true);
        tp.setCurrentHour(new Integer(10));
        tp.setCurrentMinute(new Integer(10));

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.controls_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id)
        {
            case R.id.menu_list:
                Intent intent=new Intent(this,ListDemoActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
