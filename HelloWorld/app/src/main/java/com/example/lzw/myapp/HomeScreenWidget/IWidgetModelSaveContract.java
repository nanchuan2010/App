package com.example.lzw.myapp.HomeScreenWidget;

import java.util.Map;

/**
 * Created by Administrator on 2017/6/6.
 */

public interface IWidgetModelSaveContract {
    public void setValueForPref(String key,String value);
    public String getPrefname();
    public Map<String,String> getPrefsToSave();
    public void init();
}
