package com.example.img_144.sutictac_as;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b=(Button) findViewById(R.id.Game0);

    }
    public void SMDP3X3( View v) {
        Intent i=new Intent(this,SMDP3X3.class);
        startActivity(i);
    }
}