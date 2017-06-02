package com.example.lzw.myapp;

import android.app.Activity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by LZW on 2017/05/31.
 */
public class RetainedADO extends DefaultADO implements IRetainedADO  {

    private String mName=null;

    private ArrayList<IRetainedADO> childRetainedADOs=new ArrayList<IRetainedADO>();

    IRetainedADO parentRADO=null;

    public RetainedADO(Activity parent,String tag)
    {
        super(tag);
    }

    public RetainedADO(IRetainedADO parent,String tag)
    {
        super(tag);
        parent.addChildRetainedADO(this);
        parentRADO=parent;
    }
    private IRetainedADO host=null;
    public RetainedADO(IRetainedADO parent,String tag,IRetainedADO inHost)
    {
        super(tag);
        parentRADO=parent;
        host=inHost;
        parent.addChildRetainedADO(this);

        MonitoredActivityWithADOSupport act=parent.getActivity();
        if(act!=null)
        {
            this.setActivity(act);
        }
    }

    public MonitoredActivityWithADOSupport getActivity()
    {
        MonitoredActivityWithADOSupport a=super.getActivity();
        if(a==null)
        {
            Log.w(tag,"Activity is being asked when it is null");
        }

        return a;
    }

    @Override
    public void reset() {
        Log.d(tag,"Activity is being set to null.It is being stopped");
        setActivity(null);
        resetChildADOs();
    }

    private void resetChildADOs()
    {
        for (IRetainedADO rado:this.childRetainedADOs)
        {
            rado.reset();
        }
    }

    @Override
    public void attach(MonitoredActivityWithADOSupport act) {
        Log.d(tag,"Activity is being attached.called from onstart");
        setActivity(act);
        attachToChildADOs(act);
    }

    private void attachToChildADOs(MonitoredActivityWithADOSupport act)
    {
        for (IRetainedADO rado:this.childRetainedADOs)
        {
            rado.attach(act);
        }
    }

    @Override
    public void releaseContracts() {
        Log.d(tag,"Most likely activity is getting destroyed.release resources");
        releaseChildADOContracts();
    }

    private void releaseChildADOContracts()
    {
        for (IRetainedADO rado:this.childRetainedADOs)
        {
            rado.releaseContracts();
        }
    }

    @Override
    public boolean isActivityReady() {
        return (getActivity()!=null)?true:false;
    }

    @Override
    public boolean isUIReady() {
        if(isActivityReady()==false)
        return false;

        MonitoredActivityWithADOSupport act=getActivity();
        return act.isUIReady();
    }

    @Override
    public boolean isConfigurationChanging() {
        if(isActivityReady()==false)
        {
            throw  new RuntimeException("Wrong state.Activity is not available");
        }

        return getActivity().isChangingConfigurations();
    }

    @Override
    public void addChildRetainedADO(IRetainedADO childRetainedADO) {
        this.childRetainedADOs.add(childRetainedADO);
        if(isActivityReady())
        {
            childRetainedADO.attach(getActivity());
        }
    }

    @Override
    public void addChildRetainedADOOnly(IRetainedADO childRetainedADO) {
        this.childRetainedADOs.add(childRetainedADO);

    }

    @Override
    public void removeChildRetainedADO(IRetainedADO childRetainedADO) {
        Log.d(tag,"Removing a childRetainedADO");
        this.childRetainedADOs.remove(childRetainedADO);
    }

    @Override
    public void removeAllChildRetainedADOs() {
        Log.w(tag,"Removing all child RADOs.Beware of the side affects");
        this.childRetainedADOs.clear();
    }

    @Override
    public String getName() {
        return mName;
    }

    @Override
    public void detachFromParent() {
        if(parentRADO!=null)
        {
            Log.d(tag,"Removing a child RADO from a parent");
            if(host!=null)
            {
                Log.d(tag,"Removing the host child RADO from a parent");
                parentRADO.removeChildRetainedADO(host);
            }else
            {
                parentRADO.removeChildRetainedADO(this);
            }
        }
    }

    @Override
    public void logStatus() {
        Log.d(tag,"Number of Child RADO objects:"+this.childRetainedADOs.size());
        for (IRetainedADO rado:this.childRetainedADOs)
        {
            rado.logStatus();
        }
    }
}
