package com.example.lzw.myapp.Services;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.lzw.myapp.R;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/09/13.
 */
public class GetAndPostActivity extends Activity {

    TextView tvResponse=null;
    HttpClient httpClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.services_main_activity);

        tvResponse=(TextView)findViewById(R.id.tvResponse);
        httpClient= CustomHttpClient.getHttpClient();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.services_munu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        Intent intent=new Intent();
        switch (id)
        {
            case R.id.menu_local_service:
                intent.setClass(this,ServiceActivity.class);
                startActivity(intent);
                break;
            case R.id.menu_stock_quote:
                intent.setClass(this,StockQuoteClient.class);
                startActivity(intent);
                break;
            case R.id.menu_message_handler:
                intent.setClass(this,MessengerClient.class);
                startActivity(intent);
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void doClick(View view)
    {

        switch (view.getId())
        {
            case R.id.btnGetRequest:
                new Thread(getTask).start();
                break;
            case R.id.btnPostRequest:
                new Thread(postTask).start();
                break;
            case R.id.btnSendMultipart:
                new Thread(multipartTask).start();
                break;
            case R.id.btnSendCustom:
                new Thread(customTask).start();
                break;
        }
    }



    Handler handler=new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle=msg.getData();
            String result=bundle.getString("result");
            tvResponse.setText(result);
        }
    };

    Runnable getTask=new Runnable() {
        @Override
        public void run() {
            sendGetRequest();
        }
    };

    private void sendGetRequest()
    {
        BufferedReader in=null;
        try
        {
            HttpClient client=new DefaultHttpClient();
            HttpGet request=new HttpGet("https://www.baidu.com/");

            HttpResponse response=client.execute(request);
            in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            showResultFromReader(in);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(in!=null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    Runnable postTask=new Runnable() {
        @Override
        public void run() {
            sendPostRequest();
        }
    };

    private void sendPostRequest()
    {
        BufferedReader in=null;
        try
        {
            HttpClient client=new DefaultHttpClient();

            HttpPost request=new HttpPost("http://www.androidbook.com/akc/display");
            List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("url","DisplayNoteIMPURL"));
            postParameters.add(new BasicNameValuePair("reportId","4788"));
            postParameters.add(new BasicNameValuePair("ownerUserId","android"));
            postParameters.add(new BasicNameValuePair("aspire_output_format","embedded-xml"));

            UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);

            HttpResponse response=client.execute(request);

            in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            showResultFromReader(in);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(in!=null)
            {
                try
                {
                    in.close();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    Runnable multipartTask=new Runnable() {
        @Override
        public void run() {
            sendMultipartRequest();
        }
    };

    private void sendMultipartRequest()
    {
        try{
            Ion.with(this,"http://www.androidbook.com/akc/update/PublicUploadTest")
                    .setMultipartParameter("field1","This is field number 1")
                    .setMultipartParameter("field2","Field 2 is shorter")
                    .setMultipartFile("datafile",new File(Environment.getExternalStorageDirectory()+"/testfile.txt"))
                    .asString()
                    .setCallback(new FutureCallback<String>() {
                        @Override
                        public void onCompleted(Exception e, String s) {
                            System.out.println(s);
                            tvResponse.setText(s);
                        }
                    });
        }
        catch (Exception e)
        {
            System.out.println("Got exception: "+e);
        }
    }

    Runnable customTask=new Runnable() {
        @Override
        public void run() {
            sendCustomHttpClient();
        }
    };

    private void sendCustomHttpClient()
    {
        try {
            HttpGet request=new HttpGet("https://www.baidu.com");
            HttpParams params=request.getParams();
            HttpConnectionParams.setSoTimeout(params,60000);
            request.setParams(params);

            String data=httpClient.execute(request,new BasicResponseHandler());
            sendResponseMessage(data);

        }catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void showResultFromReader(BufferedReader reader)
            throws IOException
    {
        StringBuffer sb=new StringBuffer("");
        String line="";
        String NL=System.getProperty("line.separator");
        while((line=reader.readLine())!=null)
        {
            sb.append(line+NL);
        }

        reader.close();

        String page=sb.toString();
        sendResponseMessage(page);
    }

    private void sendResponseMessage(String data)
    {
        Message msg=new Message();
        Bundle bundle=new Bundle();
        bundle.putString("result",data);
        msg.setData(bundle);
        handler.sendMessage(msg);
    }
}
