package com.example.lzw.myapp.Controls;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lzw.myapp.R;

/**
 * Created by LZW on 2017/06/16.
 */
public class GridViewCustomAdapter extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.controls_gridview_custom);
        
        GridView gv=(GridView)findViewById(R.id.gridview);
        CustomAdapter adapter=new CustomAdapter(this);
        gv.setAdapter(adapter);
        
    }
    
    
    public static class CustomAdapter extends BaseAdapter
    {
        private static final String TAG="CustomAdapter";
        private static int convertViewCounter=0;
        private Context mContext;
        private LayoutInflater mInflater;
        static class ViewHolder{
            ImageView image;
        }
        
        private int[] imgSoure={R.drawable.colored_ball1,R.drawable.colored_ball2,R.drawable.colored_ball3,
                                 R.drawable.colored_ball4,R.drawable.colored_ball5,R.drawable.colored_ball6,
                                 R.drawable.colored_ball7,R.drawable.colored_ball8};
        
        private Bitmap[] images=new Bitmap[imgSoure.length];
        private Bitmap[] thumbs=new Bitmap[imgSoure.length];
        
        public CustomAdapter(Context context)
        {
            Log.v(TAG,"Constructing CustomAdapter");
            this.mContext=context;
            mInflater=LayoutInflater.from(context);

            for (int i = 0; i < imgSoure.length; i++) {
                images[i]= BitmapFactory.decodeResource(context.getResources(),imgSoure[i]);
                thumbs[i]=Bitmap.createScaledBitmap(images[i],100,100,false);
            }
        }

        @Override
        public int getCount() {
            Log.v(TAG,"in getCount()");
            return imgSoure.length;
        }

        @Override
        public Object getItem(int i) {
            Log.v(TAG,"in getItem() for position" +i);
            return images[i];
        }

        @Override
        public long getItemId(int i) {
            Log.v(TAG,"in getItemId() for position "+i);
            return i;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
           ViewHolder holder;
            Log.v(TAG,"in getView for position "+position+", convertView is "+((convertView==null)?"null":"being recycled"));
            if(convertView==null)
            {
                convertView=mInflater.inflate(R.layout.controls_gridimage,null);
                convertViewCounter++;
                Log.v(TAG,convertViewCounter+" convertViews have been created");
                holder=new ViewHolder();
                holder.image=(ImageView)convertView.findViewById(R.id.griImageView);
                convertView.setTag(holder);
            }
            else
            {
                holder=(ViewHolder)convertView.getTag();
            }

            holder.image.setImageBitmap(images[position]);
            return convertView;
        }
    }
}
