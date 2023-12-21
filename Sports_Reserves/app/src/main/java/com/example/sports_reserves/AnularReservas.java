package com.example.sports_reserves;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AnularReservas extends AppCompatActivity {

    ListView listView;
    Adapter adapter;
    String url;

    AppPreferences preferences;
    public static ArrayList<ReservaDatos>reservaDatosArrayList=new ArrayList<>();
    ReservaDatos reservaDatos;
    ImageButton btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anular_reservas);

        preferences=new AppPreferences(getSharedPreferences("local",MODE_PRIVATE));

        btnAtras=findViewById(R.id.btn_Atras);
        listView=findViewById(R.id.listMostrar);
        adapter=new Adapter(this,reservaDatosArrayList);
        listView.setAdapter(adapter);

        final String login_usuario=preferences.getUsername();

        url="https://sportsreserves.000webhostapp.com/mostrar.php?&login_usuario="+login_usuario+"";

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder=new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog=new ProgressDialog(view.getContext());

                CharSequence[] dialogoItem={"Eliminar"};
                builder.setTitle(reservaDatosArrayList.get(position).getFecha());
                builder.setTitle("¿Eliminar reserva a las "+reservaDatosArrayList.get(position).getHora()+" horas?");
                builder.setItems(dialogoItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                EliminarDatos(reservaDatosArrayList.get(position).getCodigo_reserva());
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        ListarDatos();


        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), Menu.class);
                startActivity(intent);
            }
        });
    }

    private void EliminarDatos(final String codigo_reserva) {
    StringRequest request=new StringRequest(Request.Method.POST, "https://sportsreserves.000webhostapp.com/eliminar.php?&codigo_reserva="+codigo_reserva+"", new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {
            if (response.equalsIgnoreCase("datos borrados")) {
                Toast.makeText(AnularReservas.this, "Eliminando...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), AnularReservas.class));
            } else {
                Toast.makeText(AnularReservas.this, "No se ha podido eliminar", Toast.LENGTH_SHORT).show();
            }
        }
    }, new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            Toast.makeText(AnularReservas.this, "Error", Toast.LENGTH_SHORT).show();

        }
    }){
        @Nullable
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String ,String> params=new HashMap<>();
            params.put("codigo_reserva",codigo_reserva);
            return super.getParams();
        }
    };
    RequestQueue requestQueue=Volley.newRequestQueue(this);
    requestQueue.add(request);
    }

    private void ListarDatos() {
        StringRequest request=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                reservaDatosArrayList.clear();
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String exito = jsonObject.getString("exito");
                    JSONArray jsonArray = jsonObject.getJSONArray("datos");
                    if (exito.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);

                            String codigo_reserva = object.getString("codigo_reserva");
                            String login_usuario = object.getString("login_usuario");
                            String codigo_instalacion = object.getString("codigo_instalacion");
                            String fecha = object.getString("fecha");
                            String hora = object.getString("hora");

                            reservaDatos = new ReservaDatos(codigo_reserva, login_usuario, codigo_instalacion, fecha, hora);
                            reservaDatosArrayList.add(reservaDatos);
                            adapter.notifyDataSetChanged();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(AnularReservas.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}