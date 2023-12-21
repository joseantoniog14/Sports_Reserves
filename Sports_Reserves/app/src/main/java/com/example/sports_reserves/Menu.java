package com.example.sports_reserves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    static final String NOMBRE_FICHERO = "DATOS";
    ImageButton btnReservas, btnAnularReservas, btnPerfil, btnContacto, btnCerrarSesion, btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        //Compruebo conexión
        ConnectivityManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            Toast.makeText(this, "Tienes conexión", Toast.LENGTH_SHORT).show();


            btnReservas = findViewById(R.id.btn_Reserva);
            btnReservas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Reservas.class);
                    startActivity(intent);
                }
            });

            btnAnularReservas = findViewById(R.id.btn_AnularReserva);
            btnAnularReservas.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), AnularReservas.class);
                    startActivity(intent);
                }
            });

            btnPerfil = findViewById(R.id.btn_Perfil);
            btnPerfil.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Perfil.class);
                    startActivity(intent);
                }
            });

            btnContacto = findViewById(R.id.btn_Contacto);
            btnContacto.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), Contacto.class);
                    startActivity(intent);
                }
            });

            btnSalir = findViewById(R.id.btn_Salir);
            btnSalir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //aquí cerramos el actícity actual
                    finish();
                    //creamos un nuevo intent de action_main para el cierre de todo lo que esté abierto
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            });

            btnCerrarSesion = findViewById(R.id.btn_cerrar);
            btnCerrarSesion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences preferences = getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
                    SharedPreferences preferencias = getSharedPreferences("Datos", Context.MODE_PRIVATE);
                    SharedPreferences preferenciasfoto = getSharedPreferences(NOMBRE_FICHERO, Context.MODE_PRIVATE);
                    preferences.edit().clear().commit();
                    preferencias.edit().clear().commit();
                    preferenciasfoto.edit().clear().commit();
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            });
        } else {
            Toast.makeText(this, "No tienes conexión", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 2000);
        }
    }
}