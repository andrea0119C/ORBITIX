/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
/**
 *
 * @author valeria
 */
public class Compra {
    private String codigo;
    private Date fecha;
    private double total;
    private Pasajero pasajero;
    private Pago pago;
    private List<Pasaje> pasajes;

    public Compra(String codigo, Pasajero pasajero) {
        this.codigo = codigo;
        this.pasajero = pasajero;
        this.fecha = new Date();
        this.pasajes = new ArrayList<>();
        this.total = 0;
    }

    public void agregarPasaje(Pasaje pasaje) {
        pasajes.add(pasaje);
        total += pasaje.getPrecio();
    }

    public boolean confirmarCompra() {
        return pago != null && total > 0;
    }

    // Getters
    public String getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public double getTotal() {
        return total;
    }

    public List<Pasaje> getPasajes() {
        return pasajes;
    }

    public void setPago(Pago pago) {
        this.pago = pago;
    }
}
