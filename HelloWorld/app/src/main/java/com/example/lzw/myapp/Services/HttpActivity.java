package com.example.lzw.myapp.Services;

import android.app.Activity;
import android.os.Bundle;

import com.example.lzw.myapp.R;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.IOException;

/**
 * Created by LZW on 2016/09/13.
 */
public class HttpActivity extends Activity {
    private HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_main_activity);

        httpClient= CustomHttpClient.getHttpClient();
        getHttpContent();
    }

    public void getHttpContent() {
        try {
            HttpGet request=new HttpGet("https://www.baidu.com");
            HttpParams params=request.getParams();
            HttpConnectionParams.setSoTimeout(params,60000);
            request.setParams(params);

            String page=httpClient.execute(request,new BasicResponseHandler());
            System.out.println(page);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
