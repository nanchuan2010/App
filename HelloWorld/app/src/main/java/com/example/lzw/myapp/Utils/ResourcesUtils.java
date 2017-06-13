package com.example.lzw.myapp.Utils;

import android.app.Activity;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.renderscript.ScriptGroup;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2017/6/14.
 */

public class ResourcesUtils {

    public Drawable getDrawableById(Activity activity,int id)
    {
        Resources res=activity.getResources();
        int somecolor=res.getColor(id);
        ColorDrawable redDrawable=(ColorDrawable)res.getDrawable(id);
        return redDrawable;
    }

    public String readAnXMLFile(Activity activity,int viewId) throws IOException, XmlPullParserException {
        StringBuffer sb = new StringBuffer();
        Resources res = activity.getResources();
        XmlResourceParser xpp = res.getXml(viewId);
        xpp.next();
        int eventType = xpp.getEventType();
        while (eventType != XmlPullParser.END_DOCUMENT) {
            if (eventType == XmlPullParser.START_DOCUMENT){
                sb.append("******Start document");
            }else if (eventType == XmlPullParser.END_TAG) {
                sb.append("\nStart tag "+xpp.getName());
            } else if (eventType == XmlPullParser.TEXT) {
                sb.append("\nText "+xpp.getText());
            }
            eventType=xpp.next();
        }

        sb.append("\n******End document");
        return sb.toString();
    }


    public String getStringFromRawFile(Activity activity,int id) throws IOException {
        Resources res=activity.getResources();
        InputStream stream=res.openRawResource(id);
        String str=convertStreamToString(stream);
        stream.close();
        return str;
    }

    public String getStringFromAssetFile(Activity activity,String source) throws IOException {
        AssetManager am=activity.getAssets();
        InputStream stream=am.open(source);
        String str=convertStreamToString(stream);
        stream.close();
        return str;
    }

    private String convertStreamToString(InputStream stream) throws IOException {
        BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
        StringBuffer sb=new StringBuffer();
        String line=reader.readLine();
        while (line!=null)
        {
            sb.append(line+"/n");
            reader.readLine();
        }

        return sb.toString();
    }
}
