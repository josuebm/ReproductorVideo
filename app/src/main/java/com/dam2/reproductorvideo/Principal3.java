package com.dam2.reproductorvideo;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.MediaController;

import java.io.IOException;

/**
 * Created by Josué on 23/01/2015.
 */
public class Principal3 extends Activity implements MediaPlayer.OnCompletionListener, MediaPlayer.OnErrorListener, MediaPlayer.OnInfoListener, MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener, MediaPlayer.OnVideoSizeChangedListener, SurfaceHolder.Callback, MediaController.MediaPlayerControl {
    Display currentDisplay;
    SurfaceView surfaceView;
    SurfaceHolder surfaceHolder;
    MediaPlayer mediaPlayer;
    int videoWidth = 0;
    int videoHeight = 0;
    boolean readyToPlay = false;
    public final static String LOGTAG = "CUSTOM_VIDEO_PLAYER";
    MediaController controller;

    /*También tenemos que configurar el Layout para que nos lo presente adecuadamente.
    * Tenemos que controlar cuando se gira la pantalla para que no se cierre.
    * Sólo tiene sentido si queremos programar con detalle cada uno de los eventos*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal3);
        surfaceView = (SurfaceView) this.findViewById(R.id.SurfaceView);
        surfaceHolder = surfaceView.getHolder();
        surfaceHolder.addCallback(this);
        surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnInfoListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnVideoSizeChangedListener(this);
        String filePath = Environment.getExternalStorageDirectory().getPath() + "/1.mp4";
        try {
            mediaPlayer.setDataSource(filePath);
        } catch (IllegalArgumentException e) {
            Log.v(LOGTAG, e.getMessage());
            finish();
        } catch (IllegalStateException e) {
            Log.v(LOGTAG, e.getMessage());
            finish();
        } catch (IOException e) {
            Log.v(LOGTAG, e.getMessage());
            finish();
        }
        currentDisplay = getWindowManager().getDefaultDisplay();
        controller = new MediaController(this);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceCreated Called");
        mediaPlayer.setDisplay(holder);
        try {
            mediaPlayer.prepare();
        } catch (IllegalStateException e) {
            Log.v(LOGTAG, e.getMessage());
            finish();
        } catch (IOException e) {
            Log.v(LOGTAG, e.getMessage());
            finish();
        }
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        Log.v(LOGTAG, "surfaceChanged Called");
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.v(LOGTAG, "surfaceDestroyed Called");
    }

    public void onCompletion(MediaPlayer mp) {
        Log.v(LOGTAG, "onCompletion Called");
        finish();
    }

    public boolean onError(MediaPlayer mp, int whatError, int extra) {
        Log.v(LOGTAG, "onError Called");
        if (whatError == MediaPlayer.MEDIA_ERROR_SERVER_DIED) {
            Log.v(LOGTAG, "Media Error, Server Died " + extra);
        } else if (whatError == MediaPlayer.MEDIA_ERROR_UNKNOWN) {
            Log.v(LOGTAG, "Media Error, Error Unknown " + extra);
        }
        return false;
    }

    public boolean onInfo(MediaPlayer mp, int whatInfo, int extra) {
        if (whatInfo == MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING) {
            Log.v(LOGTAG, "Media Info, Media Info Bad Interleaving " + extra);
        } else if (whatInfo == MediaPlayer.MEDIA_INFO_NOT_SEEKABLE) {
            Log.v(LOGTAG, "Media Info, Media Info Not Seekable " + extra);
        } else if (whatInfo == MediaPlayer.MEDIA_INFO_UNKNOWN) {
            Log.v(LOGTAG, "Media Info, Media Info Unknown " + extra);
        } else if (whatInfo == MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING) {
            Log.v(LOGTAG, "MediaInfo, Media Info Video Track Lagging " + extra);
        } else if (whatInfo == MediaPlayer.MEDIA_INFO_METADATA_UPDATE) {
            Log.v(LOGTAG, "MediaInfo, Media Info Metadata Update " + extra);
        }
        return false;
    }

    public void onPrepared(MediaPlayer mp) {
        Log.v(LOGTAG, "onPrepared Called");
        videoWidth = mp.getVideoWidth();
        videoHeight = mp.getVideoHeight();
        if (videoWidth > currentDisplay.getWidth() || videoHeight > currentDisplay.getHeight()) {
            float heightRatio = (float) videoHeight / (float) currentDisplay.getHeight();
            float widthRatio = (float) videoWidth / (float) currentDisplay.getWidth();
            if (heightRatio > 1 || widthRatio > 1) {
                if (heightRatio > widthRatio) {
                    videoHeight = (int) Math.ceil((float) videoHeight / (float) heightRatio);
                    videoWidth = (int) Math.ceil((float) videoWidth / (float) heightRatio);
                } else {
                    videoHeight = (int) Math.ceil((float) videoHeight / (float) widthRatio);
                    videoWidth = (int) Math.ceil((float) videoWidth / (float) widthRatio);
                }
            }
        }
        surfaceView.setLayoutParams(new LinearLayout.LayoutParams(videoWidth, videoHeight));
        mp.start();
        controller.setMediaPlayer(this);
        controller.setAnchorView(this.findViewById(R.id.MainView));
        controller.setEnabled(true);
        controller.show();
    }

    public void onSeekComplete(MediaPlayer mp) {
        Log.v(LOGTAG, "onSeekComplete Called");
    }

    public void onVideoSizeChanged(MediaPlayer mp, int width, int height) {
        Log.v(LOGTAG, "onVideoSizeChanged Called");
    }

    public boolean canPause() {
        return true;
    }

    public boolean canSeekBackward() {
        return true;
    }

    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return 0;
    }

    public int getBufferPercentage() {
        return 0;
    }

    public int getCurrentPosition() {
        return mediaPlayer.getCurrentPosition();
    }

    public int getDuration() {
        return mediaPlayer.getDuration();
    }

    public boolean isPlaying() {
        return mediaPlayer.isPlaying();
    }

    public void pause() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
        }
    }

    public void seekTo(int pos) {
        mediaPlayer.seekTo(pos);
    }

    public void start() {
        mediaPlayer.start();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (controller.isShowing()) {
            controller.hide();
        } else {
            controller.show();
        }
        return false;
    }
}