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

public class Horas_Futbol extends AppCompatActivity {
    Button btn9_10_futbol, btn10_11_futbol, btn11_12_futbol, btn12_13_futbol,
            btn13_14_futbol, btn14_15_futbol, btn15_16_futbol, btn16_17_futbol,
            btn17_18_futbol, btn18_19_futbol, btn19_20_futbol, btn20_21_futbol;
    AppPreferences preferences;
    ImageButton btnAtras;
    private ReservasList reservasList = new ReservasList();
    ArrayList<Button> botonesList = new ArrayList<Button>();
    String hora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horas_futbol);

        //Compruebo conexión
        ConnectivityManager cm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
            cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        }
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni != null && ni.isConnectedOrConnecting()) {
            Toast.makeText(this, "Tienes conexión", Toast.LENGTH_SHORT).show();
            preferences = new AppPreferences(getSharedPreferences("local", MODE_PRIVATE));

            btn9_10_futbol = findViewById(R.id.btn9_10_futbol);
            btn10_11_futbol = findViewById(R.id.btn10_11_futbol);
            btn11_12_futbol = findViewById(R.id.btn11_12_futbol);
            btn12_13_futbol = findViewById(R.id.btn12_13_futbol);
            btn13_14_futbol = findViewById(R.id.btn13_14_futbol);
            btn14_15_futbol = findViewById(R.id.btn14_15_futbol);
            btn15_16_futbol = findViewById(R.id.btn15_16_futbol);
            btn16_17_futbol = findViewById(R.id.btn16_17_futbol);
            btn17_18_futbol = findViewById(R.id.btn17_18_futbol);
            btn18_19_futbol = findViewById(R.id.btn18_19_futbol);
            btn19_20_futbol = findViewById(R.id.btn19_20_futbol);
            btn20_21_futbol = findViewById(R.id.btn20_21_futbol);
            btnAtras = findViewById(R.id.btn_Atras);

            botonesList.add(btn9_10_futbol);
            botonesList.add(btn10_11_futbol);
            botonesList.add(btn11_12_futbol);
            botonesList.add(btn12_13_futbol);
            botonesList.add(btn13_14_futbol);
            botonesList.add(btn14_15_futbol);
            botonesList.add(btn15_16_futbol);
            botonesList.add(btn16_17_futbol);
            botonesList.add(btn17_18_futbol);
            botonesList.add(btn18_19_futbol);
            botonesList.add(btn19_20_futbol);
            botonesList.add(btn20_21_futbol);

            btn9_10_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "9";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn9_10_futbol.setBackgroundColor(Color.RED);
                    btn9_10_futbol.setEnabled(false);

                }
            });
            btn10_11_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "10";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn10_11_futbol.setBackgroundColor(Color.RED);
                    btn10_11_futbol.setEnabled(false);

                }
            });
            btn11_12_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "11";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn11_12_futbol.setBackgroundColor(Color.RED);
                    btn11_12_futbol.setEnabled(false);

                }
            });
            btn12_13_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "12";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn12_13_futbol.setBackgroundColor(Color.RED);
                    btn12_13_futbol.setEnabled(false);

                }
            });
            btn13_14_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "13";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn13_14_futbol.setBackgroundColor(Color.RED);
                    btn13_14_futbol.setEnabled(false);

                }
            });
            btn14_15_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "14";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn14_15_futbol.setBackgroundColor(Color.RED);
                    btn14_15_futbol.setEnabled(false);

                }
            });
            btn15_16_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "15";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn15_16_futbol.setBackgroundColor(Color.RED);
                    btn15_16_futbol.setEnabled(false);

                }
            });
            btn16_17_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "16";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn16_17_futbol.setBackgroundColor(Color.RED);
                    btn16_17_futbol.setEnabled(false);

                }
            });
            btn17_18_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "17";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn17_18_futbol.setBackgroundColor(Color.RED);
                    btn17_18_futbol.setEnabled(false);

                }
            });
            btn18_19_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "18";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn18_19_futbol.setBackgroundColor(Color.RED);
                    btn18_19_futbol.setEnabled(false);

                }
            });
            btn19_20_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "19";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn19_20_futbol.setBackgroundColor(Color.RED);
                    btn19_20_futbol.setEnabled(false);

                }
            });
            btn20_21_futbol.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    hora = "20";
                    Horas_Futbol.Insertar insertar = new Horas_Futbol.Insertar();
                    insertar.execute();
                    btn20_21_futbol.setBackgroundColor(Color.RED);
                    btn20_21_futbol.setEnabled(false);

                }
            });
            btnAtras.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), Reservas.class);
                    startActivity(intent);
                }
            });
            Horas_Futbol.obtenerReservas obtener2 = new Horas_Futbol.obtenerReservas();
            obtener2.execute();
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
            final String cod_instalacion = "1";
            try {
                url = new URL("https://sportsreserves.000webhostapp.com/Reservas.php?NULL&login_usuario=" + usuario
                        + "&codigo_instalacion=" + cod_instalacion + "&fecha=" + fecha + "&hora=" + hora);
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
            Toast.makeText(Horas_Futbol.this, todo, Toast.LENGTH_SHORT).show();
        }
    }

    public class obtenerReservas extends AsyncTask<Void, Void, ReservasList> {
        private String phpUrl = "https://sportsreserves.000webhostapp.com/mostrarReservas.php?codigo_instalacion=1";

        @Override
        protected ReservasList doInBackground(Void... voids) {
            ReservasList reservasList = new ReservasList();
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
                reservasList = gson.fromJson(stringBuilder.toString(), ReservasList.class);
                Log.v("reservaslist", reservasList.toString());
                for (int i = 0; i < reservasList.getDatos().size(); i++) {
                    if (Integer.parseInt(reservasList.getDatos().get(i).getHora()) > 0 && Integer.parseInt(reservasList.getDatos().get(i).getHora()) < 10) {
                        reservasList.getDatos().get(i).setHora("0" + reservasList.getDatos().get(i).getHora() + ":00-" + (Integer.parseInt(reservasList.getDatos().get(i).getHora()) + 1) + ":00");
                    } else {
                        reservasList.getDatos().get(i).setHora(reservasList.getDatos().get(i).getHora() + ":00-" + (Integer.parseInt(reservasList.getDatos().get(i).getHora()) + 1) + ":00");
                    }
                    Log.v("reserva", reservasList.getDatos().get(i).getHora());
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect(); // Asegúrate de cerrar la conexión
                }
            }

            return reservasList;
        }


        @Override
        protected void onPostExecute(ReservasList reservasList) {
            String username = preferences.getUsername();


            //reservasList.getDatos().get(i).getLoginUsuario().equals(username)
            for (int i = 0; i < botonesList.size(); i++) {
                for (int j = 0; j < reservasList.getDatos().size(); j++) {
                    if (botonesList.get(i).getText().equals(reservasList.getDatos().get(j).getHora())
                            && reservasList.getDatos().get(j).getLoginUsuario().equals(username)) {
                        //pintar boton color rojo
                        botonesList.get(i).setBackgroundColor(Color.RED);
                        botonesList.get(i).setEnabled(false);
                    } else if (botonesList.get(i).getText().equals(reservasList.getDatos().get(j).getHora())
                            && !reservasList.getDatos().get(j).getLoginUsuario().equals(username)) {
                        //pintar boton color verde y desabilitarlo
                        botonesList.get(i).setBackgroundColor(Color.GRAY);
                        botonesList.get(i).setEnabled(false);
                    }
                }
            }
        }
    }
}