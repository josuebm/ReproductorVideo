package com.dam2.reproductorvideo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Josué on 23/01/2015.
 */
public class Reproductor extends Activity {

    VideoView vv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reproductor);
        vv = (VideoView)this.findViewById(R.id.videoView);
        //Añadimos un MediaController
        vv.setMediaController(new MediaController(this));
        //Recogemos la ruta del vídeo
        Uri ruta = (Uri)getIntent().getExtras().get("ruta");
        vv.setVideoURI(ruta);
        vv.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("pos", vv.getCurrentPosition());
        boolean playing = false;
        if(vv.isPlaying())
            playing = true;
        outState.putBoolean("estado", playing);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        vv.seekTo(savedInstanceState.getInt("pos"));
        boolean playing = savedInstanceState.getBoolean("estado");
        if(!playing)
            vv.pause();
    }
}