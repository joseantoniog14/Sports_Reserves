package com.example.sports_reserves.entities;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Reserva implements Serializable {
    @SerializedName("codigo_reserva")
    private String codigoReserva;
    @SerializedName("login_usuario")
    private String loginUsuario;
    @SerializedName("codigo_instalacion")
    private String codigoInstalacion;
    @SerializedName("fecha")
    private String fecha;
    @SerializedName("hora")
    private String hora;

    public Reserva(){

    }

    public Reserva(String codigoReserva, String loginUsuario, String codigoInstalacion, String fecha, String hora) {
        this.codigoReserva = codigoReserva;
        this.loginUsuario = loginUsuario;
        this.codigoInstalacion = codigoInstalacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCodigoReserva() {
        return codigoReserva;
    }

    public void setCodigoReserva(String codigoReserva) {
        this.codigoReserva = codigoReserva;
    }

    public String getLoginUsuario() {
        return loginUsuario;
    }

    public void setLoginUsuario(String loginUsuario) {
        this.loginUsuario = loginUsuario;
    }

    public String getCodigoInstalacion() {
        return codigoInstalacion;
    }

    public void setCodigoInstalacion(String codigoInstalacion) {
        this.codigoInstalacion = codigoInstalacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    @Override
    public String toString() {
        return "Reserva{" +
                "codigoReserva='" + codigoReserva + '\'' +
                ", loginUsuario='" + loginUsuario + '\'' +
                ", codigoInstalacion='" + codigoInstalacion + '\'' +
                ", fecha='" + fecha + '\'' +
                ", hora='" + hora + '\'' +
                '}';
    }
}
