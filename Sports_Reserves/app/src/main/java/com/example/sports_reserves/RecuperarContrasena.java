package com.example.sports_reserves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RecuperarContrasena extends AppCompatActivity {

    Button btnActualizarContrasena;
    EditText txtMiUsuario,txtNuevaContrasena1,txtNuevaContrasena2;
    ImageButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_contrasena);
        txtMiUsuario=findViewById(R.id.txtMiUsuario);
        txtNuevaContrasena1=findViewById(R.id.txtNuevaContrasena1);
        txtNuevaContrasena2=findViewById(R.id.txtNuevaContrasena2);
        btnActualizarContrasena=findViewById(R.id.btnActualizarContrasena);

        btnAtras = findViewById(R.id.btn_Atras);

        btnActualizarContrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(txtMiUsuario.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"El usuario no puede estar vacío",Toast.LENGTH_SHORT).show();
                }
                else if(txtNuevaContrasena1.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"La contraseña no puede estar vacía",Toast.LENGTH_SHORT).show();
                }
                else if(txtNuevaContrasena2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"La repetición de contraseña no puede estar vacía",Toast.LENGTH_SHORT).show();
                }
                else if(txtNuevaContrasena1.getText().toString().compareTo(txtNuevaContrasena2.getText().toString())==0){
                    ComprobarNombre comprobarNombre =new ComprobarNombre();
                    comprobarNombre.execute();
                }
                else{
                    Toast.makeText(RecuperarContrasena.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
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
                url = new URL("https://sportsreserves.000webhostapp.com/ComprobarUsuario.php?username="+txtMiUsuario.getText());
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
            if (todo.length()!=22){
                Log.v("valor","entra");
                Toast.makeText(RecuperarContrasena.this, "Contraseña actualizada", Toast.LENGTH_SHORT).show();
                Recuperar recuperar =new Recuperar();
                recuperar.execute();
            }else{
                Toast.makeText(RecuperarContrasena.this, todo, Toast.LENGTH_SHORT).show();
            }
        }
    }
    private class Recuperar extends AsyncTask<Void, Void, Void>{

        String todo;
        @Override
        protected Void doInBackground(Void... voids) {
            URL url;
            HttpURLConnection httpURLConnection;
            try {
                url = new URL("https://sportsreserves.000webhostapp.com/RecuperarContrasena.php?username="+txtMiUsuario.getText()+"&contrasena="+txtNuevaContrasena1.getText());
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
            Intent intentReg = new Intent(RecuperarContrasena.this, MainActivity.class);
            RecuperarContrasena.this.startActivity(intentReg);
            //Toast.makeText(RecuperarContrasena.this, todo, Toast.LENGTH_SHORT).show();
        }
    }
}