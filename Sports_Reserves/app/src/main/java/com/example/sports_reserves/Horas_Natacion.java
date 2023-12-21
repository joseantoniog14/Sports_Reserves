package com.example.sports_reserves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sports_reserves.entities.ReservasList;
import com.google.gson.Gson;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Horas_Natacion extends AppCompatActivity {

    Button btn9_10_natacion, btn10_11_natacion, btn11_12_natacion, btn12_13_natacion,
            btn13_14_natacion, btn14_15_natacion, btn15_16_natacion, btn16_17_natacion,
            btn17_18_natacion, btn18_19_natacion, btn19_20_natacion, btn20_21_natacion;
    AppPreferences preferences;
    ImageButton btnAtras;
    private ReservasList reservasList5 = new ReservasList();
    ArrayList<Button> botonesList5 = new ArrayList<Button>();
    String hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas_natacion);

        //Compruebo conexión
        ConnectivityManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            Toast.makeText(this, "Tienes conexión", Toast.LENGTH_SHORT).show();
            preferences = new AppPreferences(getSharedPreferences("local", MODE_PRIVATE));

            btn9_10_natacion = findViewById(R.id.btn9_10_natacion);
            btn10_11_natacion = findViewById(R.id.btn10_11_natacion);
            btn11_12_natacion = findViewById(R.id.btn11_12_natacion);
            btn12_13_natacion = findViewById(R.id.btn12_13_natacion);
            btn13_14_natacion = findViewById(R.id.btn13_14_natacion);
            btn14_15_natacion = findViewById(R.id.btn14_15_natacion);
            btn15_16_natacion = findViewById(R.id.btn15_16_natacion);
            btn16_17_natacion = findViewById(R.id.btn16_17_natacion);
            btn17_18_natacion = findViewById(R.id.btn17_18_natacion);
            btn18_19_natacion = findViewById(R.id.btn18_19_natacion);
            btn19_20_natacion = findViewById(R.id.btn19_20_natacion);
            btn20_21_natacion = findViewById(R.id.btn20_21_natacion);
            btnAtras = findViewById(R.id.btn_Atras);

            botonesList5.add(btn9_10_natacion);
            botonesList5.add(btn10_11_natacion);
            botonesList5.add(btn11_12_natacion);
            botonesList5.add(btn12_13_natacion);
            botonesList5.add(btn13_14_natacion);
            botonesList5.add(btn14_15_natacion);
            botonesList5.add(btn15_16_natacion);
            botonesList5.add(btn16_17_natacion);
            botonesList5.add(btn17_18_natacion);
            botonesList5.add(btn18_19_natacion);
            botonesList5.add(btn19_20_natacion);
            botonesList5.add(btn20_21_natacion);

            btn9_10_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "9";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn9_10_natacion.setBackgroundColor(Color.RED);
                    btn9_10_natacion.setEnabled(false);

                }
            });
            btn10_11_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "10";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn10_11_natacion.setBackgroundColor(Color.RED);
                    btn10_11_natacion.setEnabled(false);

                }
            });
            btn11_12_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "11";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn11_12_natacion.setBackgroundColor(Color.RED);
                    btn11_12_natacion.setEnabled(false);

                }
            });
            btn12_13_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "12";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn12_13_natacion.setBackgroundColor(Color.RED);
                    btn12_13_natacion.setEnabled(false);

                }
            });
            btn13_14_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "13";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn13_14_natacion.setBackgroundColor(Color.RED);
                    btn13_14_natacion.setEnabled(false);

                }
            });
            btn14_15_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "14";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn14_15_natacion.setBackgroundColor(Color.RED);
                    btn14_15_natacion.setEnabled(false);

                }
            });
            btn15_16_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "15";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn15_16_natacion.setBackgroundColor(Color.RED);
                    btn15_16_natacion.setEnabled(false);

                }
            });
            btn16_17_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "16";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn16_17_natacion.setBackgroundColor(Color.RED);
                    btn16_17_natacion.setEnabled(false);

                }
            });
            btn17_18_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "17";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn17_18_natacion.setBackgroundColor(Color.RED);
                    btn17_18_natacion.setEnabled(false);

                }
            });
            btn18_19_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "18";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn18_19_natacion.setBackgroundColor(Color.RED);
                    btn18_19_natacion.setEnabled(false);

                }
            });
            btn19_20_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "19";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn19_20_natacion.setBackgroundColor(Color.RED);
                    btn19_20_natacion.setEnabled(false);

                }
            });
            btn20_21_natacion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "20";
                    Horas_Natacion.Insertar insertar = new Horas_Natacion.Insertar();
                    insertar.execute();
                    btn20_21_natacion.setBackgroundColor(Color.RED);
                    btn20_21_natacion.setEnabled(false);

                }
            });
            btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Reservas.class);
                    startActivity(intent);
                }
            });
            Horas_Natacion.obtenerReservas obtener5 = new Horas_Natacion.obtenerReservas();
            obtener5.execute();
        } else {
            Toast.makeText(this, "No tienes conexión", Toast.LENGTH_SHORT).show();
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    finish();
                }
            }, 1000);
        }

    }

    SimpleDateFormat formatoFecha = new SimpleDateFormat("dd MM yyyy", Locale.forLanguageTag("es_ES"));
    String fecha = formatoFecha.format(new Date());

    private class Insertar extends AsyncTask<Void, Void, Void> {

        String todo;

        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection httpURLConnection;
            final String usuario = preferences.getUsername();
            final String cod_instalacion = "5";
            try {
                url = new URL("https://sportsreserves.000webhostapp.com/Reservas.php?NULL&login_usuario=" + usuario + "&codigo_instalacion=" + cod_instalacion + "&fecha=" + fecha + "&hora=" + hora);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {

                    InputStream inputStream = httpURLConnection.getInputStream();
                    BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                    String linea = "";
                    while ((linea = br.readLine()) != null) {
                        todo += linea + "\n";

                    }
                    String palabra = "";
                    for (int i = 0; i < 4; i++) {
                        palabra += String.valueOf(todo.charAt(i));
                    }


                    if (palabra.compareTo("null") == 0) {
                        todo = todo.substring(4);
                    }
                    br.close();
                    inputStream.close();
                }
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void unused) {
            Toast.makeText(Horas_Natacion.this, todo, Toast.LENGTH_SHORT).show();
        }
    }

    public class obtenerReservas extends AsyncTask<Void, Void, ReservasList> {
        private String phpUrl = "https://sportsreserves.000webhostapp.com/mostrarReservas.php?codigo_instalacion=5";

        @Override
        protected ReservasList doInBackground(Void... voids) {
            ReservasList reservasList5 = new ReservasList();
            HttpURLConnection urlConnection = null;

            try {
                URL url = new URL(phpUrl);
                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.setConnectTimeout(5000);
                urlConnection.setReadTimeout(5000);

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                StringBuilder stringBuilder = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                Gson gson = new Gson();
                reservasList5 = gson.fromJson(stringBuilder.toString(), ReservasList.class);
                Log.v("reservaslist", reservasList5.toString());
                for (int i = 0; i < reservasList5.getDatos().size(); i++) {
                    if (Integer.parseInt(reservasList5.getDatos().get(i).getHora()) > 0 && Integer.parseInt(reservasList5.getDatos().get(i).getHora()) < 10) {
                        reservasList5.getDatos().get(i).setHora("0" + reservasList5.getDatos().get(i).getHora() + ":00-" + (Integer.parseInt(reservasList5.getDatos().get(i).getHora()) + 1) + ":00");
                    } else {
                        reservasList5.getDatos().get(i).setHora(reservasList5.getDatos().get(i).getHora() + ":00-" + (Integer.parseInt(reservasList5.getDatos().get(i).getHora()) + 1) + ":00");
                    }
                    Log.v("reserva", reservasList5.getDatos().get(i).getHora());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect(); // Asegúrate de cerrar la conexión
                }
            }

            return reservasList5;
        }


        @Override
        protected void onPostExecute(ReservasList reservasList5) {
            String username = preferences.getUsername();


            //reservasList.getDatos().get(i).getLoginUsuario().equals(username)
            for (int i = 0; i < botonesList5.size(); i++) {
                for (int j = 0; j < reservasList5.getDatos().size(); j++) {
                    if (botonesList5.get(i).getText().equals(reservasList5.getDatos().get(j).getHora()) && reservasList5.getDatos().get(j).getLoginUsuario().equals(username)) {
                        //pintar boton color rojo
                        botonesList5.get(i).setBackgroundColor(Color.RED);
                        botonesList5.get(i).setEnabled(false);
                    } else if (botonesList5.get(i).getText().equals(reservasList5.getDatos().get(j).getHora()) && !reservasList5.getDatos().get(j).getLoginUsuario().equals(username)) {
                        //pintar boton color verde y desabilitarlo
                        botonesList5.get(i).setBackgroundColor(Color.GRAY);
                        botonesList5.get(i).setEnabled(false);
                    }
                }
            }
        }
    }
}