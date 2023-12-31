package com.example.sports_reserves;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Registro extends AppCompatActivity {
    EditText txtNombre,txtUsuario,txtApellidos,txtContrasena;
    Button btnRegistro;
    String url;
    ImageButton btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        txtNombre=findViewById(R.id.txtNombre);
        txtUsuario=findViewById(R.id.txtUsuario);
        txtApellidos=findViewById(R.id.txtApellidos);
        txtContrasena=findViewById(R.id.txtContrasena);
        btnRegistro=findViewById(R.id.btnRegistro);
        btnAtras = findViewById(R.id.btn_Atras);



        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = txtUsuario.getText().toString();
                final String nombre = txtNombre.getText().toString();
                final String apellidos = txtApellidos.getText().toString();
                final String contrasena = txtContrasena.getText().toString();
                url="https://sportsreserves.000webhostapp.com/Comprobar.php?&username="+username;
                if(txtUsuario.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"El usuario no puede estar vacío",Toast.LENGTH_SHORT).show();
                }
                else if(txtNombre.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"El nombre no puede estar vacío",Toast.LENGTH_SHORT).show();
                }
                else if(txtApellidos.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Los apellidos no pueden estar vacíos",Toast.LENGTH_SHORT).show();
                }
                else if(txtContrasena.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"La contraseña no puede estar vacía",Toast.LENGTH_SHORT).show();
                }
                else {
                    Registro.ComprobarNombre comprobarNombre =new Registro.ComprobarNombre();
                    comprobarNombre.execute();
                }
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
    private class ComprobarNombre extends AsyncTask<Void, Void, Void>{

        String todo;
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url = new URL("https://sportsreserves.000webhostapp.com/ComprobarUsuario.php?username="+txtUsuario.getText());
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
            Log.v("valor",todo.length()+"");
            Log.v("valor",todo);
            if (todo.length()!=19){
                Log.v("valor","entra");
                Toast.makeText(Registro.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                Registro.Insertar recuperar =new Registro.Insertar();
                recuperar.execute();
            }else{
                Toast.makeText(Registro.this, "El usuario ya existe", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class Insertar extends AsyncTask<Void, Void, Void>{

        String todo;
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url = new URL("https://sportsreserves.000webhostapp.com/Register.php?username="+txtUsuario.getText()+"&nombre="+txtNombre.getText()+"&apellidos="+txtApellidos.getText()+"&contrasena="+txtContrasena.getText());
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
            Intent intentReg = new Intent(Registro.this, MainActivity.class);
            Registro.this.startActivity(intentReg);
            //Toast.makeText(RecuperarContrasena.this, todo, Toast.LENGTH_SHORT).show();
        }
    }
    }
