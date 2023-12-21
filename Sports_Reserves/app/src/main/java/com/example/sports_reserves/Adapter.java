package com.example.sports_reserves;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter extends ArrayAdapter<ReservaDatos> {


    Context context;
    List<ReservaDatos> arrayReservas;

    public Adapter(@NonNull Context context, List<ReservaDatos>arrayReservas) {
        super(context, R.layout.list_reservas,arrayReservas);
        this.context=context;
        this.arrayReservas=arrayReservas;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_reservas,null,true);
        TextView txtID=view.findViewById(R.id.txtId);
        TextView txtFecha=view.findViewById(R.id.txtFecha);
        TextView txtHora=view.findViewById(R.id.txtHora);
        TextView txtInstalacion=view.findViewById(R.id.txtInstalacion);

        txtID.setText(arrayReservas.get(position).getCodigo_reserva());
        txtFecha.setText(arrayReservas.get(position).getFecha());
        txtHora.setText(arrayReservas.get(position).getHora());
        txtInstalacion.setText(arrayReservas.get(position).getCodigo_instalacion());
        return view;
    }
}
