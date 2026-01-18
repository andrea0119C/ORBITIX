/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

public class Pasaje {
    private String codigo;
    private double precio;
    private String asiento; 
    private ClaseAsiento clase;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private Equipaje equipaje;

    public Pasaje(String codigo, double precio, String asiento, ClaseAsiento clase, Pasajero pasajero, Vuelo vuelo, Equipaje equipaje) {
        this.codigo = codigo;
        this.precio = precio;
        this.asiento = asiento;
        this.clase = clase;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.equipaje = equipaje;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    public ClaseAsiento getClase() {
        return clase;
    }

    public void setClase(ClaseAsiento clase) {
        this.clase = clase;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public void setPasajero(Pasajero pasajero) {
        this.pasajero = pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public void setVuelo(Vuelo vuelo) {
        this.vuelo = vuelo;
    }

    public Equipaje getEquipaje() {
        return equipaje;
    }

    public void setEquipaje(Equipaje equipaje) {
        this.equipaje = equipaje;
    }

    public double getRecargo() {

        double recargoExtra = 0;

        // 1. Recargo por Clase
        if (this.clase == ClaseAsiento.PRIMERA_CLASE) recargoExtra += 100.0;
        else if (this.clase == ClaseAsiento.EJECUTIVA) recargoExtra += 50.0;

        // 2. Recargo por Equipaje
        if (this.equipaje != null) {
            if (this.equipaje.getTipo() == TipoEquipaje.MALETA_BODEGA) {
                recargoExtra += 50.0; // Aquí están los 50.0 que viste en tu archivo
            }
        }
        
        return recargoExtra;
  
    }
    
    
}