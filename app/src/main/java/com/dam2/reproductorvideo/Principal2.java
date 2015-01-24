package com.dam2.reproductorvideo;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.widget.MediaController;
import android.widget.VideoView;

/**
 * Created by Josué on 23/01/2015.
 */
public class Principal2 extends Activity {
    VideoView vv;


    /*No se reproduce el vídeo sin los permisos de acceso a la tarjeta externa.
    * Hay que trabajar con el layout para centrar el vídeo, que ocupe toda la pantalla, dar un color de fondo, etc.
    * Al girar la pantalla el vídeo empieza de nuevo.
    * No se muestran controles por defecto.
    * Al añadir un MediaController disponemos de las opciones de avanzar hacia atrás y hacia delante, cosa que no nos
    * ofrece el Intent (imagino que dependerá de la versión de Android) pero no nos ofrece el maximizar,
    * que sí nos ofrece el Intent.*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal2);
        vv = (VideoView)this.findViewById(R.id.videoView);
        //Añadimos un MediaController
        vv.setMediaController(new MediaController(this));
        Uri videoUri = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/1.mp4");
        vv.setVideoURI(videoUri);
        vv.start();
    }
}