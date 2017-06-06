package com.example.lzw.myapp.HomeScreenWidget;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;

/**
 * Created by LZW on 2017/06/05.
 */
public class BDayWidgetProvider extends AppWidgetProvider {
    private static final String tag="BDayWidgetProvider";
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        final int N=appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId=appWidgetIds[i];
            updateAppWidget(context,appWidgetManager,appWidgetId);
        }
    }

    public void onDeleted(Context context,int[] appWidgetIds){
        final int N=appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            BDayWidgetModel bwm=BDayWidgetModel.retrieveModel(context,appWidgetIds[i]);
            bwm.removePrefs(context);
        }
    }
    public void onEnabled(Context context){
        BDayWidgetModel.clearAllPreferences(context);
        PackageManager pm=context.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName("com.androidbook.BDayWidget",".BDayWidgetProvider"),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,PackageManager.DONT_KILL_APP);
    }
    public void onDisabled(Context context){
        BDayWidgetModel.clearAllPreferences(context);
        PackageManager pm=context.getPackageManager();
        pm.setComponentEnabledSetting(new ComponentName("com.androidbook.BDayWidget",".BDayWidgetProvider"),PackageManager.
                COMPONENT_ENABLED_STATE_DISABLED,PackageManager.DONT_KILL_APP);
    }

    private void updateAppWidget(Context context,AppWidgetManager appWidgetManager,int appWidgetId)
    {
        BDayWidgetModel bwm=BDayWidgetModel.retrieveModel(context,appWidgetId);
        if(bwm==null)
        {
            return;
        }

        ConfigureBDayWidgetActivity.updateAppWidget(context,appWidgetManager,bwm);
    }
}
