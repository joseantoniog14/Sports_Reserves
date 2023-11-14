package com.example.sports_reserves;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Horas extends AppCompatActivity {

    Button btn9_10;
    boolean activobtn9_10=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas);
        btn9_10=findViewById(R.id.btn9_10);
        //Calendar fecha= Calendar.getInstance();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("dd MM yyyy", Locale.forLanguageTag("es_ES"));
        String fecha= formatoFecha.format(new Date());
        recuperarPreferencias();

        btn9_10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btn9_10.setBackgroundColor(Color.RED);
                btn9_10.setEnabled(false);
                guardarPreferencias();
                activobtn9_10=true;
                final String usuario="jose";
                final String cod_instalacion="2";
                final String hora="9";
                Horas.Insertar insertar = new Horas.Insertar();
                insertar.execute("http://192.168.18.26/Reservas.php?NULL"+"&login_usuario=" + usuario + "&codigo_instalacion=" + cod_instalacion + "&fecha=" + fecha + "&hora=" + hora);
                Toast.makeText(Horas.this, "Reserva realizada correctamente", Toast.LENGTH_SHORT).show();
                Intent intentReg = new Intent(Horas.this, Menu.class);
                Horas.this.startActivity(intentReg);
            }
        });
    }
    private class Insertar extends AsyncTask<String,Void,Void> {
        ProgressDialog progreso;
        @Override
        protected Void doInBackground(String... strings) {

            String ruta = strings[0];
            URL url;
            HttpURLConnection httpURLConnection;
            Log.v("ruta",ruta);
            try {
                url = new URL(ruta);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();
                Log.v("valor", String.valueOf(httpURLConnection.getResponseCode()));
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    Log.v("conexion", "correcta");
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPreExecute() {

            progreso= new ProgressDialog(Horas.this);
            progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progreso.setMessage("Cargando");
            progreso.setProgress(0);
            progreso.setCancelable(false);
            progreso.show();
        }

        @Override
        protected void onPostExecute(Void unused) {
            progreso.dismiss();
        }
    }
    private void guardarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        btn9_10.setEnabled(false);
        editor.putBoolean("sesion",true);
        editor.commit();
    }
    //metodo para recuperar los datos
    private void recuperarPreferencias(){
        SharedPreferences preferences=getSharedPreferences("preferenciasLogin",Context.MODE_PRIVATE);
        //por si no hubiese preferencias guardadas
        activobtn9_10=true;

    }

}