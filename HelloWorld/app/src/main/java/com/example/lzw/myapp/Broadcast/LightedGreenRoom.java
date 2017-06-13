package com.example.lzw.myapp.Broadcast;

import android.content.Context;
import android.os.PowerManager;
import android.util.Log;

import com.example.lzw.myapp.Utils.Utils;

/**
 * Created by LZW on 2017/06/01.
 */
public class LightedGreenRoom {
    private static String tag="LightedGreenRoom";
    private static LightedGreenRoom s_self=null;
    public static void setup(Context inCtx)
    {
        if(s_self==null)
        {
            Log.d(LightedGreenRoom.tag,"Creating green room and lighting it");
            s_self=new LightedGreenRoom(inCtx);
            s_self.turnOnLights();
        }
    }

    public static boolean isSetup()
    {
        return (s_self!=null)?true:false;
    }

    public static int s_enter()
    {
        assertSetup();
        return s_self.enter();
    }

    public static int s_leave()
    {
        assertSetup();
        return s_self.leave();
    }

    public static void ds_emptyTheRoom()
    {
        assertSetup();
        s_self.emptyTheRoom();
        return;
    }

    public static void s_registerClient()
    {
        assertSetup();
        s_self.registerClient();
        return;
    }

    public static void s_unRegisterClient()
    {
        assertSetup();
        s_self.unRegisterClient();
        return;
    }

    private static void assertSetup()
    {
        if(LightedGreenRoom.s_self==null)
        {
            Log.w(LightedGreenRoom.tag,"You need to call setup first");
            throw new RuntimeException("You need to setup GreenRoom first");
        }
    }

    private int count;

    private Context ctx=null;

    PowerManager.WakeLock wl=null;

    private int clientCount=0;

    private LightedGreenRoom(Context inCtx)
    {
        ctx=inCtx;
        wl=this.createWakeLock(inCtx);
    }

    synchronized private int enter()
    {
        count++;
        Log.d(tag,"A new visitor:count:"+count);
        return count;
    }

    synchronized private int leave()
    {
        Log.d(tag,"Leaving room:count at the call:"+count);
        if(count==0)
        {
            Log.w(tag,"Count is zero.");
            return count;
        }
        count--;
        if(count==0)
        {
            turnOffLights();
        }

        return count;
    }

    synchronized private int getCount()
    {
        return count;
    }

    private void turnOnLights()
    {
        Log.d(tag,"Turning on lights.Count:"+count);
        this.wl.acquire();
    }

    private void turnOffLights()
    {
        if(this.wl.isHeld())
        {
            Log.d(tag,"Releasing wake lock.No more visitors");
            this.wl.release();
        }
    }


    private PowerManager.WakeLock createWakeLock(Context inCtx)
    {
        PowerManager pm=(PowerManager)inCtx.getSystemService(Context.POWER_SERVICE);
        PowerManager.WakeLock wl=pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,tag);
        return wl;
    }


    private int registerClient()
    {
        Utils.logThreadSignature(tag);
        this.clientCount++;
        Log.d(tag,"registering a new client:count:"+clientCount);
        return clientCount;
    }

    private int unRegisterClient()
    {
        Utils.logThreadSignature(tag);
        Log.d(tag,"un registering a new client:count:"+clientCount);
        if(clientCount==0)
        {
            Log.w(tag,"There are no clients to unregister.");
            return 0;
        }
        clientCount--;
        if(clientCount==0)
        {
            emptyTheRoom();
        }
        return clientCount;
    }

    synchronized private void emptyTheRoom()
    {
        Log.d(tag,"Call to empty the room");
        count=0;
        this.turnOffLights();
    }
}
