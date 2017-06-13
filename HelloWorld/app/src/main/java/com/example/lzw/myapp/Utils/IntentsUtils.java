package com.example.lzw.myapp.Utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;

/**
 * Created by Administrator on 2017/6/13.
 */

public class IntentsUtils {
    public static void invokeWebBrowser(Activity activity,String strUri) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUri));
        activity.startActivity(intent);

    }

    public static void invokeWebSearch(Activity activity,String strUri) {
        Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
        intent.setData(Uri.parse(strUri));
        activity.startActivity(intent);
    }

    public static void dial(Activity activity) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        activity.startActivity(intent);
    }

    public static void call(Activity activity, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        activity.startActivity(intent);
    }

    public static void showMapAtLatLong(Activity activity,String strGeo)
    {
        Intent intent=new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strGeo));
        activity.startActivity(intent);
    }

}
