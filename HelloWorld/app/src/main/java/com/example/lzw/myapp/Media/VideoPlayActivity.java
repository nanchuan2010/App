package com.example.lzw.myapp.Media;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.lzw.myapp.R;


public class VideoPlayActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.media_video_play);

        VideoView videoView=(VideoView)this.findViewById(R.id.videoView);
        MediaController mc=new MediaController(this);
        videoView.setMediaController(mc);
        videoView.setVideoURI(Uri.parse("http://www.androidbook.com/akc/filestorage/android/" +
                "documentfiles/3389/movie.mp4"));

        //videoView.setVideoURI(Uri.parse("file://"+ Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES)+"/movie.mp4"));

        videoView.requestFocus();
        videoView.start();
    }

}
