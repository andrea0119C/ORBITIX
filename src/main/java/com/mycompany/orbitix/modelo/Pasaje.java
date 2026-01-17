/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

public class Pasaje {
    private String codigo;
    private double precio;
    private String asiento; // Ej: "12A"
    private ClaseAsiento clase;
    private Pasajero pasajero; // Datos de la persona que se sienta aquí
    private Vuelo vuelo;
    
    public Pasaje(String codigo, double precio, String asiento, ClaseAsiento clase, Pasajero pasajero, Vuelo vuelo) {
        this.codigo = codigo;
        this.precio = precio;
        this.asiento = asiento;
        this.clase = clase;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
    }

    // Getters
    public String getCodigo(){
        return codigo;
    }
    public double getPrecio(){
        return precio;
    }
    public String getAsiento(){
        return asiento;
    }
    public Pasajero getPasajero(){
        return pasajero;
    }
    public Vuelo getVuelo(){
        return vuelo;
    }
}