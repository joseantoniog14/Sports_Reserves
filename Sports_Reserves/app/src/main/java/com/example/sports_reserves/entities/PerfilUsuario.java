package com.example.sports_reserves.entities;

import com.google.gson.annotations.SerializedName;

public class PerfilUsuario {
    String nombre, apellidos, edad, altura, peso, fecha_nacimiento, genero;
    @SerializedName("imagen_codificada")
    String imagen;

    public PerfilUsuario() {

    }

    public PerfilUsuario(String nombre, String apellidos, String imagen, String edad, String altura, String peso, String fecha_nacimiento, String genero) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.imagen = imagen;
        this.edad = edad;
        this.altura = altura;
        this.peso = peso;
        this.fecha_nacimiento = fecha_nacimiento;
        this.genero = genero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }


}
