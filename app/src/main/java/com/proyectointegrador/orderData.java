package com.proyectointegrador;

import java.io.Serializable;

public class orderData implements Serializable {
    private String nombre;
    private String apellido;
    private String snEquipo;
    private String celular;
    private String correo;
    private String cedula;
    private String valorArreglo;
    private String fechaIngreso;
    private String fechaSalida;
    private String tecnicoAsignado;
    private String estado;

    public orderData() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSnEquipo() {
        return snEquipo;
    }

    public void setSnEquipo(String snEquipo) {
        this.snEquipo = snEquipo;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getValorArreglo() {
        return valorArreglo;
    }

    public void setValorArreglo(String valorArreglo) {
        this.valorArreglo = valorArreglo;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(String fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public String getTecnicoAsignado() {
        return tecnicoAsignado;
    }

    public void setTecnicoAsignado(String tecnicoAsignado) {
        this.tecnicoAsignado = tecnicoAsignado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
