package com.dam2.reproductorvideo;

import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Josué on 23/01/2015.
 */
public class GestorVideo {

    Context contexto;


    public GestorVideo(Context c){
        contexto = c;
    }


    public static Video getRow(Cursor c) {
        Video objeto = new Video();
        objeto.setId(c.getString(0));
        objeto.setNombre(c.getString(1));
        objeto.setRuta(c.getString(2));
        objeto.setMime(c.getString(3));
        objeto.setFecha(c.getString(4));
        objeto.setTamano(c.getString(5));
        objeto.setDuracion(c.getString(6));
        return objeto;
    }

    public Cursor getCursor() {
        String[] columnas = {MediaStore.Video.Media._ID, MediaStore.Video.Media.TITLE, MediaStore.Video.Media.DATA, MediaStore.Video.Media.MIME_TYPE, MediaStore.Video.Media.DATE_MODIFIED, MediaStore.Video.Media.SIZE, MediaStore.Video.Media.DURATION};
        Cursor cursor = contexto.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columnas, null, null, MediaStore.Video.Media.DATE_MODIFIED + " desc");
        return cursor;
    }

    public Cursor getCursor(String orden) {
        String[] columnas = {MediaStore.Video.Media._ID, MediaStore.Video.Media.TITLE, MediaStore.Video.Media.DATA, MediaStore.Video.Media.MIME_TYPE, MediaStore.Video.Media.DATE_MODIFIED, MediaStore.Video.Media.SIZE, MediaStore.Video.Media.DURATION};
        Cursor cursor = contexto.getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI, columnas, null, null, orden);
        return cursor;
    }
/*
    public ArrayList<Video> select() {
        ArrayList<Video> lista = new ArrayList();
        Cursor cursor = getCursor();
        Video video;
        if (cursor.moveToFirst()) {
            do {
                try{
                    video = getRow(cursor);
                    lista.add(video);
                }catch (Exception e){
                }
            } while (cursor.moveToNext());
        }
        return lista;
    }*/
}
