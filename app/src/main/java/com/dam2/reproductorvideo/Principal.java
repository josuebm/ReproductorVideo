package com.dam2.reproductorvideo;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;


public class Principal extends Activity {

    private ListView lv;
    private Cursor cursor;
    private GestorVideo gv;
    private Adaptador ad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        lv = (ListView) findViewById(R.id.lista);
        gv = new GestorVideo(this);
        cargarLista(null);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (cursor.moveToPosition(position)) {
                    Video v = GestorVideo.getRow(cursor);
                    reproductorDefecto(v);
                }
            }
        });
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                if (cursor.moveToPosition(position)) {
                    Video v = GestorVideo.getRow(cursor);
                    reproductorPersonal(v);
                }
                return true;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.orden_fecha_des) {
            cargarLista(MediaStore.Video.Media.DATE_MODIFIED + " desc");
            return true;
        }else if(id == R.id.orden_fecha_asc){
            cargarLista(MediaStore.Video.Media.DATE_MODIFIED + " asc");
            return true;
        }else if(id == R.id.orden_nombre_des){
            cargarLista(MediaStore.Video.Media.TITLE + " desc");
            return true;
        }else if(id == R.id.orden_nombre_asc) {
            cargarLista(MediaStore.Video.Media.TITLE + " asc");
            return true;
        }else if(id == R.id.orden_tamano_des) {
            cargarLista(MediaStore.Video.Media.SIZE + " desc");
            return true;
        }else if(id == R.id.orden_tamano_asc) {
            cargarLista(MediaStore.Video.Media.SIZE + " asc");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void cargarLista(String orden){
        if(orden == null)
            cursor = gv.getCursor();
        else
            cursor = gv.getCursor(orden);
        ad = new Adaptador(this, cursor);
        lv.setAdapter(ad);
    }

    public void reproductorDefecto(Video v) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = Uri.parse(v.getRuta());
        intent.setDataAndType(data, v.getMime());
        startActivity(intent);
    }

    public void reproductorPersonal(Video v) {
        Intent intent = new Intent(this, Reproductor.class);
        Uri data = Uri.parse(v.getRuta());
        intent.putExtra("ruta", data);
        startActivity(intent);
    }
}
