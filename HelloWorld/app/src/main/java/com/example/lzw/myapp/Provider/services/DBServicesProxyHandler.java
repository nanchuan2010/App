package com.example.lzw.myapp.Provider.services;

import android.util.Log;

import com.example.lzw.myapp.Provider.services.impl.BookPSSQLite;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by LZW on 2017/06/08.
 */
public class DBServicesProxyHandler implements InvocationHandler {
    private BookPSSQLite bookServiceImpl=new BookPSSQLite();
    private static String tag="DBServiceProxyHandler";
    DBServicesProxyHandler(){}

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logMethodSignature(method);
        String mname=method.getName();
        if(mname.startsWith("get"))
        {
            return this.invokeForReads(method,args);
        }
        else
        {
            return this.invokeForWrites(method,args);
        }
    }

    private Object invokeForReads(Method method, Object[] args) throws Throwable {
        if(DatabaseContext.isItAlreadyInsideATransaction()==true)
        {
            return invokeForReadsWithoutATransactionalWrap(method,args);
        }else
        {
            return invokeForReadsWithATransactionalWrap(method,args);
        }
    }

    private Object callDelegateMethod(Method method,Object[] args) throws Throwable {
        return method.invoke(bookServiceImpl,args);
    }

    private Object invokeForReadsWithATransactionalWrap(Method method, Object[] args) throws Throwable {
        try {
            DatabaseContext.setReadableDatabaseContext();
            return callDelegateMethod(method,args);
        } finally {
            DatabaseContext.reset();
        }
    }

    private Object invokeForReadsWithoutATransactionalWrap(Method method, Object[] args) throws Throwable {
        return callDelegateMethod(method,args);
    }

    private void logMethodSignature(Method method) {
        String interfaceName=method.getDeclaringClass().getName();
        String mname=method.getName();
        Log.d(tag,String.format("%s : %s",interfaceName,mname));
    }


    private Object invokeForWrites(Method method, Object[] args) throws Throwable {
        if(DatabaseContext.isItAlreadyInsideATransaction()==true)
        {
            return invokeForWritesWithoutATransactionalWrap(method,args);
        }
        else
        {
            return invokeForWritesWithATransactionalWrap(method,args);
        }
    }

    private Object invokeForWritesWithoutATransactionalWrap(Method method, Object[] args) throws Throwable {
        return callDelegateMethod(method,args);
    }

    private Object invokeForWritesWithATransactionalWrap(Method method,Object[] args) throws Throwable {
        try {
            DatabaseContext.setWritableDatabaseContext();
            DatabaseContext.beginTransaction();
            Object rtnObject=callDelegateMethod(method,args);
            DatabaseContext.setTransactionSuccessful();
            return rtnObject;
        } finally {
            try {
                DatabaseContext.endTransaction();
            } finally {
                DatabaseContext.reset();
            }
        }
    }
}
