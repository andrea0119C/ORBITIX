/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;
import java.util.List;
/**
 *
 * @author karla
 */
public abstract class Usuario {

    protected String cedula;
    protected String nombre;
    protected String email;

    public Usuario(String cedula, String nombre, String email) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
    }

    public abstract List<Vuelo> buscarVuelos(String origen, String destino);
    public abstract Compra realizarCompra(Vuelo vuelo);
    public abstract List<Compra> verCompras();

    public String getCedula() {
        return cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }
}
