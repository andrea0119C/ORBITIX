/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author valeria
 */
public class Pasaje {
     private String codigo;
    private double precio;
    private String asiento;
    private ClaseAsiento clase;
    private Pasajero pasajero;
    private Vuelo vuelo;
    private List<Equipaje> equipajes;

    public Pasaje(String codigo, double precio, String asiento,
                  ClaseAsiento clase, Pasajero pasajero, Vuelo vuelo) {
        this.codigo = codigo;
        this.precio = precio;
        this.asiento = asiento;
        this.clase = clase;
        this.pasajero = pasajero;
        this.vuelo = vuelo;
        this.equipajes = new ArrayList<>();
    }

    public void agregarEquipaje(Equipaje equipaje) {
        equipajes.add(equipaje);
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public Pasajero getPasajero() {
        return pasajero;
    }

    public Vuelo getVuelo() {
        return vuelo;
    }

    public List<Equipaje> getEquipajes() {
        return equipajes;
    }
    
}
