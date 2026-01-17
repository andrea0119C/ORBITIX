/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paula
 */
public class Aerolinea {

    private String nombre;
    private List<Vuelo> vuelos;

    public Aerolinea(String nombre) {
        this.nombre = nombre;
        this.vuelos = new ArrayList<>();
    }

    public void agregarVuelo(Vuelo v) {
        vuelos.add(v);
    }

    public List<Vuelo> listarVuelos() {
        return vuelos;
    }

    public String getNombre() {
        return nombre;
    }
}
