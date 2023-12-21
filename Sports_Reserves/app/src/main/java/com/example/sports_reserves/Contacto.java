package com.example.sports_reserves;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Contacto extends AppCompatActivity {

    Button btnMapa;
    ImageButton btnAtras;
    int REQUEST_CODE = 200;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);
        btnMapa = findViewById(R.id.btnMapa);
        btnAtras = findViewById(R.id.btn_Atras);

        btnMapa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUbicacion();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
    public void getUbicacion(){
        int permisoUbicacion= ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION);
        if(permisoUbicacion==PackageManager.PERMISSION_GRANTED){
                Uri localizacion = Uri.parse("geo:0,0?q=38.4827,-4.95539");
                Intent i = new Intent(Intent.ACTION_VIEW, localizacion);
                startActivity(i);
        }
        else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},100);
            }
        }
    }
}
