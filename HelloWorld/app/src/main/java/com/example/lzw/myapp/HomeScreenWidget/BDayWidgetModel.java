package com.example.lzw.myapp.HomeScreenWidget;

import android.content.Context;
import android.util.Log;

import com.example.lzw.myapp.Utils;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public class BDayWidgetModel extends APrefWidgetModel {

    private static String tag="BDayWidgetModel";
    private static String BDAY_WIDGET_PROVIDER_NAME="com.androidbook.BDayWidget.BDayWidgetProvider";
    private String name="anon";
    private static String F_NAME="name";
    private String bday="1/1/2001";
    private static String F_BDAY="bday";


    public BDayWidgetModel(int instanceId) {
        super(instanceId);
    }

    public BDayWidgetModel(int instanceId,String inName,String inBday)
    {
        super(instanceId);
        name=inName;
        bday=inBday;
    }

    public void setName(String inname)
    {
        name=inname;
    }

    public void setBday(String inbday)
    {
        bday=inbday;
    }

    public String getName()
    {
        return name;
    }

    public String getBday()
    {
        return bday;
    }

    public long howManyDays()
    {
        try
        {
            return Utils.howfarInDaysThisYear(Utils.getDate(this.bday));
        }
        catch (ParseException x)
        {
            return 20000;
        }
    }

    @Override
    public void init() {

    }

    @Override
    public void setValueForPref(String key, String value) {
        if(key.equals(getStoredKeyForFieldName(BDayWidgetModel.F_NAME)))
        {
            Log.d(tag,"Setting name to:"+value);
            this.name=value;
            return;
        }

        if(key.equals(getStoredKeyForFieldName(BDayWidgetModel.F_BDAY)))
        {
            Log.d(tag,"Setting bday to:"+value);
            this.bday=value;
            return;
        }

        Log.d(tag,"Sorry the key does not match:"+key);
    }

    @Override
    public String getPrefname()
    {
        return BDayWidgetModel.BDAY_WIDGET_PROVIDER_NAME;
    }

    public Map<String,String> getPrefsToSave()
    {
        Map<String,String>  map=new HashMap<String,String>();
        map.put(BDayWidgetModel.F_NAME,this.name);
        map.put(BDayWidgetModel.F_BDAY,this.bday);
        return map;
    }

    public String toString()
    {
        StringBuffer sbuf=new StringBuffer();
        sbuf.append("iid:"+iid);
        sbuf.append("name:"+name);
        sbuf.append("bday:"+bday);
        return sbuf.toString();
    }

    public static  void  clearAllPreferences(Context ctx)
    {
        APrefWidgetModel.clearAllPreferences(ctx,BDayWidgetModel.BDAY_WIDGET_PROVIDER_NAME);
    }

    public static BDayWidgetModel retrieveModel(Context ctx,int widgetId)
    {
        BDayWidgetModel m=new BDayWidgetModel(widgetId);
        boolean found=m.retrievePrefs(ctx);
        return found?m:null;
    }
}
