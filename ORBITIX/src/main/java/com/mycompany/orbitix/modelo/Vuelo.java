/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Vuelo {

    private String codigo;
    private Date fecha;
    private String hora;
    private Ruta ruta;
    private Avion avion;
    private double precio; 
    private Set<String> asientosOcupados; 

    public Vuelo(String codigo, Date fecha, String hora, Ruta ruta, Avion avion, double precio) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
        this.avion = avion;
        this.precio = precio; 
        this.asientosOcupados = new HashSet<>();
    }
    public Vuelo() {
    }


    public boolean esAsientoDisponible(String numeroAsiento) {
        return !asientosOcupados.contains(numeroAsiento);
    }

    public void ocuparAsiento(String numeroAsiento) {
        if (asientosOcupados.size() < avion.getCapacidad()) {
            asientosOcupados.add(numeroAsiento);
        }
    }

   
    public String getCodigo(){
        return codigo;
    }
    public Date getFecha(){
        return fecha;
    }
    public String getHora(){
        return hora;
    }
    public Ruta getRuta(){
        return ruta;
    }
    public Avion getAvion(){
        return avion;
    }
    public double getPrecio(){
        return precio;
    } 

    public int getAsientosDisponibles() {
        return avion.getCapacidad() - asientosOcupados.size();
    }
    
    public Set<String> getAsientosOcupados() {
        return asientosOcupados;
    }
}