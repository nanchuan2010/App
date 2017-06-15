package com.example.lzw.myapp.Architecture;

import android.app.Activity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.lzw.myapp.R;

import java.util.List;


/*
 * 调用Activity并返回值
 */
public class SomeActivity extends Activity implements View.OnClickListener {

    public static void invokePickNotePad(Activity activity)
    {
        Intent pickIntent=new Intent(Intent.ACTION_PICK);
        int requestCode=1;
        pickIntent.setData(Uri.parse("content://com.google.provider.NotePad/notes"));

        try {
            activity.startActivityForResult(pickIntent,requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invokePickGoogleNote(Activity activity)
    {
        Intent pickIntent=new Intent(Intent.ACTION_PICK);

        int requestCode=2;
        pickIntent.setType("vnd.android.cursor.item/vnd.google.note");
        try {
            activity.startActivityForResult(pickIntent,requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void invokeGetContent(Activity activity)
    {
        Intent pickIntent=new Intent(Intent.ACTION_GET_CONTENT);
        int requestCode=2;
        pickIntent.setType("vnd.android.cursor.item/vnd.google.note");
        activity.startActivityForResult(pickIntent,requestCode);
    }

    public  void invokeCategoryActivities(Activity activity)
    {
        Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm=getPackageManager();
        List<ResolveInfo> list=pm.queryIntentActivities(mainIntent,0);

        for (ResolveInfo ri:list)
        {
            Log.d("test",ri.toString());
            String packagename=ri.activityInfo.packageName;
            String classname=ri.activityInfo.name;
            Log.d("test",packagename+":"+classname);
            Toast.makeText(this,packagename+"\\"+classname,Toast.LENGTH_LONG).show();
            if(classname.equals("com.ai.androidbook.resources.TestActivity")){
                Intent ni=new Intent();
                ni.setClassName(packagename,classname);
                activity.startActivity(ni);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.art_some_activity);

        Button btn= (Button) this.findViewById(R.id.btnPickNotePad);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.btnPickGoogleNote);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.btnGetContent);
        btn.setOnClickListener(this);

        btn=(Button)this.findViewById(R.id.btnCategoryActivities);
        btn.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        parseResult(this,requestCode,resultCode,intent);
    }

    public  void parseResult(Activity activity,int requestCode,int resultCode,Intent intent)
    {
        if(resultCode!=Activity.RESULT_OK)
        {
            String result="Result code is not ok:"+resultCode;
            Log.d("Test",result);
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            return;
        }

        if(requestCode!=1 && requestCode!=2)
        {
            String result="Someone else called this. not us";
            Log.d("Test",result);
            Toast.makeText(this,result,Toast.LENGTH_LONG).show();
            return;
        }

        String str1="Result code is OK:"+resultCode;
        Log.d("Test",str1);
        Uri selectedUri=intent.getData();
        String str2="The output uri:"+selectedUri.toString();
        Log.d("Test",str2);
        Toast.makeText(this,str1+"\n"+str2,Toast.LENGTH_LONG).show();
        intent.setAction(Intent.ACTION_VIEW);
        startActivity(intent);
    }

    /*
     * 自定义目标Activity中设置
     */
    protected void onListItemClick(ListView l, View v,int position,long id)
    {
        Uri uri= ContentUris.withAppendedId(getIntent().getData(),id);
        String action=getIntent().getAction();
        if(Intent.ACTION_PICK.equals(action) || Intent.ACTION_GET_CONTENT.equals(action))
        {
            setResult(RESULT_OK,new Intent().setData(uri));
            finish();
        }
    }


    @Override
    public void onClick(View view) {

        int id=view.getId();
        switch (id)
        {
            case R.id.btnPickNotePad:
                invokePickNotePad(this);
                break;
            case R.id.btnPickGoogleNote:
                invokePickGoogleNote(this);
                break;
            case R.id.btnGetContent:
                invokeGetContent(this);
                break;
            case R.id.btnCategoryActivities:
                invokeCategoryActivities(this);
                break;
        }
    }
}
