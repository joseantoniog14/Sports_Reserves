package com.example.sports_reserves.entities;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ReservasList {
    @SerializedName ("datos")
    public ArrayList <Reserva> datos = new ArrayList <>();

    public ReservasList() {
        this.datos=new ArrayList<>();
    }

    public ReservasList(ArrayList<Reserva> datos) {
        this.datos = datos;
    }

    public ArrayList<Reserva> getDatos() {
        return datos;
    }

    public void setDatos(ArrayList<Reserva> datos) {
        this.datos = datos;
    }

    public int getSize() {
        return datos.size();
    }
}
