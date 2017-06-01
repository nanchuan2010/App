package com.example.lzw.myapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.w3c.dom.Text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by LZW on 2016/09/13.
 */
public class HttpGetDemo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        BufferedReader in=null;
        try
        {
            HttpClient client=new DefaultHttpClient();
//            HttpGet request=new HttpGet("https://www.baidu.com/");

            HttpPost request=new HttpPost("https://www.baidu.com/");
            List<NameValuePair> postParameters=new ArrayList<NameValuePair>();
            postParameters.add(new BasicNameValuePair("url","DisplayNoteIMPURL"));
            postParameters.add(new BasicNameValuePair("reportId","4788"));
            postParameters.add(new BasicNameValuePair("ownerUserId","android"));
            postParameters.add(new BasicNameValuePair("aspire_output_format","embeded-xml"));

            UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(postParameters);
            request.setEntity(formEntity);

            HttpResponse response=client.execute(request);

            in=new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

            StringBuffer sb=new StringBuffer("");
            String line="";
            String NL=System.getProperty("line.separator");
            while((line=in.readLine())!=null)
            {
                sb.append(line+NL);
            }
            in.close();

            String page=sb.toString();
            System.out.println(page);
            TextView txt=(TextView)findViewById(R.id.text1);
            txt.setText(page);
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
}
