package com.example.lzw.myapp;

/**
 * Created by LZW on 2017/05/31.
 */
public abstract class DefaultADO implements IActivityDependentObject {
    protected volatile MonitoredActivityWithADOSupport m_parent=null;
    protected String tag=null;

    public DefaultADO(String intag)
    {
        tag=intag;
    }

    public MonitoredActivityWithADOSupport getActivity()
    {
        return m_parent;
    }

    protected void setActivity(MonitoredActivityWithADOSupport a)
    {
        m_parent=a;
    }
}
