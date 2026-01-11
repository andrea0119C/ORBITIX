/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;
import java.util.Date;

/**
 *
 * @author karla
 */
public class Vuelo {

    private String codigo;
    private Date fecha;
    private String hora;
    private Ruta ruta;
    private Avion avion;
    private int asientosDisponibles;

    public Vuelo(String codigo, Date fecha, String hora, Ruta ruta, Avion avion) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.ruta = ruta;
        this.avion = avion;
        this.asientosDisponibles = avion.getCapacidad();
    }

    public void disminuirAsientos() {
        if (asientosDisponibles > 0) {
            asientosDisponibles--;
        }
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getHora() {
        return hora;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public Avion getAvion() {
        return avion;
    }

    public int getAsientosDisponibles() {
        return asientosDisponibles;
    }
}
