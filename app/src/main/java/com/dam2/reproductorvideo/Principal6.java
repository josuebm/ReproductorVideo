package com.dam2.reproductorvideo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;


public class Principal6 extends ActionBarActivity implements View.OnClickListener {
    Button playButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal6);
        playButton = (Button) this.findViewById(R.id.btPlay);
        playButton.setOnClickListener(this);
    }

    public void onClick(View v) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
        Uri data = Uri.parse(Environment.getExternalStorageDirectory().getPath() + "/1.mp4");
        intent.setDataAndType(data, "video/mp4");
        startActivity(intent);
    }
}