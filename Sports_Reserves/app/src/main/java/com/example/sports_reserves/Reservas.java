package com.example.sports_reserves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Reservas extends AppCompatActivity {

    Button btnPadel,btnGimnasio,btnFutbol,btnNatacion,btnTenis,btnBaloncesto;
    ImageButton btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservas);
        btnPadel=findViewById(R.id.btnPadel);
        btnGimnasio=findViewById(R.id.btnGimnasio);
        btnFutbol=findViewById(R.id.btnFutbol);
        btnNatacion=findViewById(R.id.btnNatacion);
        btnTenis=findViewById(R.id.btnTenis);
        btnBaloncesto=findViewById(R.id.btnBaloncesto);
        btnAtras=findViewById(R.id.btn_Atras);

        btnBaloncesto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Horas_Baloncesto.class);
                startActivity(intent);
            }
        });

        btnGimnasio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Horas_Gimnasio.class);
                startActivity(intent);
            }
        });

        btnFutbol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Horas_Futbol.class);
                startActivity(intent);
            }
        });

        btnNatacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Horas_Natacion.class);
                startActivity(intent);
            }
        });

        btnTenis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Horas_Tenis.class);
                startActivity(intent);
            }
        });

        btnPadel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Horas_Padel.class);
                startActivity(intent);
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }
}