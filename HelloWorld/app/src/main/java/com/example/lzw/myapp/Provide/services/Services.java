package com.example.lzw.myapp.Provide.services;

import android.util.Log;

import com.example.lzw.myapp.Provide.services.impl.Database;

import java.lang.reflect.Proxy;

/**
 * Created by Administrator on 2017/6/9.
 */

public class Services {
    public static String tag="Services";
    public static class PersistenceServices
    {
        public static IBookPS bookps=null;
        static
        {
            Services.init();
        }
    }

    public static void init(){}
    private static Object mainProxy;
    static
    {
        Database.initialize();

        ClassLoader cl=IBookPS.class.getClassLoader();
        Class[] variousServiceInterface=new Class[]{IBookPS.class};
        mainProxy= Proxy.newProxyInstance(cl,variousServiceInterface,new DBServicesProxyHandler());
        Log.d(Services.tag,"Main Services Proxy Initialized");
        PersistenceServices.bookps=(IBookPS)mainProxy;
    }
}
