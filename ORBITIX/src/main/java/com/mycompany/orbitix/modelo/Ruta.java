/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

/**
 *
 * @author USUARIO
 */
public class Ruta {

    private String origen;
    private String destino;
    private double duracion;

    public Ruta(String origen, String destino, double duracion) {
        this.origen = origen;
        this.destino = destino;
        this.duracion = duracion;
    }
    public String getDuracionFormateada() {
    int totalMinutos = (int) Math.round(this.duracion * 60);
    
    int h = totalMinutos / 60;
    int m = totalMinutos % 60;

    if (h > 0) {
        return h + "h " + (m > 0 ? m + "min" : "");
    } else {
        return m + " min";
    }
}

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getDuracion() {
        return duracion;
    }

    public void setDuracion(double duracion) {
        this.duracion = duracion;
    }

    @Override
    public String toString() {
        return "Ruta{" +
                "origen=" + origen +
                ", destino=" + destino +
                ", duracion=" + duracion +
                '}';
    }
}

