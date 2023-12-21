package com.example.sports_reserves;

public class ReservaDatos {
    String codigo_reserva,login_usuario,codigo_instalacion,fecha,hora;

    public ReservaDatos(String codigo_reserva, String login_usuario, String codigo_instalacion, String fecha, String hora) {
        this.codigo_reserva = codigo_reserva;
        this.login_usuario = login_usuario;
        this.codigo_instalacion = codigo_instalacion;
        this.fecha = fecha;
        this.hora = hora;
    }

    public String getCodigo_reserva() {
        return codigo_reserva;
    }

    public void setCodigo_reserva(String codigo_reserva) {
        this.codigo_reserva = codigo_reserva;
    }

    public String getLogin_usuario() {
        return login_usuario;
    }

    public void setLogin_usuario(String login_usuario) {
        this.login_usuario = login_usuario;
    }

    public String getCodigo_instalacion() {
        return codigo_instalacion;
    }

    public void setCodigo_instalacion(String codigo_instalacion) {
        this.codigo_instalacion = codigo_instalacion;
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
}
