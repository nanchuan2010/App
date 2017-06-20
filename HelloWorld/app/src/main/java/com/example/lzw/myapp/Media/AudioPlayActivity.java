package com.example.lzw.myapp.Media;

import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.lzw.myapp.R;

import java.util.zip.Inflater;

public class AudioPlayActivity extends Activity implements MediaPlayer.OnPreparedListener {

    static final String AUDIO_PATH="http://www.androidbook.com/akc/filestorage/android/documentfiles/3389/play.mp3";
    //SD Card
    //static final String AUDIO_PATH= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"/music_file.mp3".

    private MediaPlayer mediaPlayer;
    private int playbackPosition=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio_play);
    }

    public void doClick(View view)
    {
        switch (view.getId())
        {
            case R.id.startPlayerBtn:
                try
                {
                    playAudio(AUDIO_PATH);
/*                    playLocalAudio();
                    playLocalAudio_UsingDescriptor();*/
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                break;
            case R.id.pausePlayerBtn:
                if(mediaPlayer!=null && mediaPlayer.isPlaying())
                {
                    playbackPosition=mediaPlayer.getCurrentPosition();
                    mediaPlayer.pause();
                }
                break;
            case R.id.restartPlayerBtn:
                if(mediaPlayer!=null && !mediaPlayer.isPlaying())
                {
                    mediaPlayer.seekTo(playbackPosition);
                    mediaPlayer.start();
                }
                break;
            case R.id.stopPlayerBtn:
                if(mediaPlayer!=null)
                {
                    mediaPlayer.stop();
                    playbackPosition=0;
                }
                break;
        }
    }

    private void playAudio(String url) throws Exception
    {
        killMediaPlayer();

        mediaPlayer=new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.setDataSource(url);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.prepareAsync();
    }

    private void playLocalAudio() throws Exception
    {
        mediaPlayer=MediaPlayer.create(this,R.raw.music_file);
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaPlayer.start();
    }

    private void playLocalAudio_UsingDescriptor() throws Exception
    {
        AssetFileDescriptor fileDesc=getResources().openRawResourceFd(R.raw.music_file);
        if(fileDesc!=null)
        {
            mediaPlayer=new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(fileDesc.getFileDescriptor(),fileDesc.getStartOffset(),fileDesc.getLength());
            fileDesc.close();

            mediaPlayer.prepare();
            mediaPlayer.start();
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        mp.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer()
    {
        if(mediaPlayer!=null)
        {
            try
            {
                mediaPlayer.release();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.async_task_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent=null;
        switch (item.getItemId())
        {
           /* case R.id.menu_test_async2:
                intent=new Intent(this,SoundPoolActivity.class);
                this.startActivity(intent);
                break;
            case R.id.menu_test_async3:
                intent=new Intent(this,VideoPlayActivity.class);
                this.startActivity(intent);
                break;*/
        }

        return true;
    }
}
