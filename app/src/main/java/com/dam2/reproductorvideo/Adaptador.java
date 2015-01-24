package com.dam2.reproductorvideo;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Josué on 23/01/2015.
 */
public class Adaptador extends CursorAdapter{

    Context contexto;

    public Adaptador(Context context, Cursor c) {
        super(context, c, true);
        contexto = context;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        LayoutInflater i = LayoutInflater.from(parent.getContext());
        View v = i.inflate(R.layout.lista_detalle, parent, false);
        return v;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        TextView tv1, tv2, tv3;
        ImageView iv;
        tv1 = (TextView)view.findViewById(R.id.tvTitulo);
        tv2 = (TextView)view.findViewById(R.id.tvTamano);
        tv3 = (TextView)view.findViewById(R.id.tvDuracion);
        iv = (ImageView)view.findViewById(R.id.ivMiniatura);
        Video v = GestorVideo.getRow(cursor);
        tv1.setText(v.getNombre());
        tv2.setText(tamano(v.getTamano()));
        tv3.setText(duracion(v.getDuracion()));
        iv.setImageBitmap(miniatura(v));
    }

    public String duracion(String ms){
        String duracion;
        int min=0, seg;
        seg = Integer.parseInt(ms) / 1000;
        while(seg >= 60){
            min++;
            seg -= 60;
        }
        if(String.valueOf(min).length() <= 1)
            duracion = "0" + min;
        else
            duracion = min+"";
        if(String.valueOf(seg).length() <= 1)
            duracion += ":" + "0" + seg;
        else
            duracion += ":" + seg;
        return duracion;
    }

    public String tamano(String b){
        long bytes = Long.valueOf(b);
        int unit = 1024;
        if (bytes < unit)
            return bytes + " B";
        int exp = (int) (Math.log(bytes) / Math.log(unit));
        if(exp == 1)
            return String.format("%d KB", (int)(bytes / Math.pow(unit, exp)));
        String pre = ("KMGTPE").charAt(exp-1) + "";
        return String.format("%.1f %sB", bytes / Math.pow(unit, exp), pre);
    }
/*
    public Bitmap miniatura(Video v){
        ThumbnailUtils miniatura = new ThumbnailUtils();
        return miniatura.createVideoThumbnail(v.getRuta(), MediaStore.Video.Thumbnails.MICRO_KIND);
    }*/

    public Bitmap miniatura(Video v){
        int id = Integer.valueOf(v.getId());
        ContentResolver crThumb = contexto.getContentResolver();
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inSampleSize = 3;
        Bitmap curThumb = MediaStore.Video.Thumbnails.getThumbnail(crThumb, id,
                MediaStore.Video.Thumbnails.MICRO_KIND, options);
        return curThumb;
    }
}
