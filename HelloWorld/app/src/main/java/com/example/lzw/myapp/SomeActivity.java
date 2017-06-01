package com.example.lzw.myapp;

import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.List;

public class SomeActivity extends Activity {

    public void invokePick(Activity activity)
    {
        Intent pickIntent=new Intent(Intent.ACTION_PICK);
      /*  int requestCode=1;
        pickIntent.setData(Uri.parse("content://com.google.provider.NotePad/notes"));*/

        int requestCode=2;
        pickIntent.setType("vnd.android.cursor.item/vnd.google.note");
        try {
            startActivityForResult(pickIntent,requestCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void invokeGetContent(Activity activity)
    {
        /*Intent pickIntent=new Intent(Intent.ACTION_GET_CONTENT);
        int requestCode=2;
        pickIntent.setType("vnd.android.cursor.item/vnd.google.note");
        activity.startActivityForResult(pickIntent,requestCode);

        Intent mainIntent=new Intent(Intent.ACTION_MAIN,null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
       // PackageManager pm=getPackageManager();
        List<ResolveInfo> list=pm.queryIntentActivities(mainIntent,0);

        for (ResolveInfo ri:list)
        {
            Log.d("test",ri.toString());
            String packagename=ri.activityInfo.packageName;
            String classname=ri.activityInfo.name;
            Log.d("test",packagename+":"+classname);

            if(classname.equals("com.ai.androidbook.resources.TestActivity")){
                Intent ni=new Intent();
                ni.setClassName(packagename,classname);
                activity.startActivity(ni);
            }
        }*/
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        parseResult(this,requestCode,resultCode,data);
    }

    public  void parseResult(Activity activity,int requestCode,int resultCode,Intent data)
    {
        if(requestCode!=1)
        {
            Log.d("Test","Someone else called this. not us");
            return;
        }
        if(resultCode!=Activity.RESULT_OK)
        {
            Log.d("Test","Result code is not ok:"+resultCode);
            return;
        }

        Log.d("Test","Result code is OK:"+resultCode);
        Uri selectedUri=data.getData();
        Log.d("Test","The output uri:"+selectedUri.toString());

        data.setAction(Intent.ACTION_VIEW);
        startActivity(data);
    }

    protected void onListItemClick(ListView l, View v,int position,long
                                   id)
    {
        Uri uri= ContentUris.withAppendedId(getIntent().getData(),id);
        String action=getIntent().getAction();
        if(Intent.ACTION_PICK.equals(action))
        {
            setResult(RESULT_OK,new Intent().setData(uri));
            finish();
        }
    }


}
