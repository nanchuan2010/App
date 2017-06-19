package com.example.lzw.myapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by LZW on 2016/09/14.
 */
public class StockQuoteService extends Service {
    private static final String TAG="StockQuoteService";
    public class StockQuoteServiceImpl extends IStockQuoteService.Stub
    {
        @Override
        public double getQuote(String ticker) throws RemoteException {
            Log.v(TAG,"getQuote() called for "+ticker);
            return 20.0;

        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.v(TAG,"onCreate() called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.v(TAG,"onDestroy() called");
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.v(TAG,"onBind() called");
        return new StockQuoteServiceImpl();
    }
}
