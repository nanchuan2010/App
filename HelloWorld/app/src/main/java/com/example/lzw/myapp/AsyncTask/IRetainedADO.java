package com.example.lzw.myapp.AsyncTask;

/**
 * Created by LZW on 2017/05/31.
 */
public interface IRetainedADO extends IActivityDependentObject {

    public MonitoredActivityWithADOSupport getActivity();

    public void reset();

    public void attach(MonitoredActivityWithADOSupport act);

    public void releaseContracts();

    public boolean isActivityReady();

    public boolean isUIReady();

    public boolean isConfigurationChanging();

    public void addChildRetainedADO(IRetainedADO childRetainedADO);

    public void addChildRetainedADOOnly(IRetainedADO childRetainedADO);

    public void removeChildRetainedADO(IRetainedADO childRetainedADO);


    public void removeAllChildRetainedADOs();

    public String getName();

    public void detachFromParent();

    public void logStatus();

}
