package com.mycompany.orbitix.modelo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Compra {
    private String codigo;
    private Date fecha;
    private double total;
    private Usuario usuario; 
    private Pago pago;
    private List<Pasaje> pasajes; 

    public Compra(String codigo, Usuario usuario) {
        this.codigo = codigo;
        this.usuario = usuario;
        this.fecha = new Date();
        this.pasajes = new ArrayList<>();
        this.total = 0;
    }

    /**
     * Modificado para sumar precio base + recargo.
     * Esto soluciona que en el archivo de compras solo apareciera el precio base.
     * @param pasaje
     */
    public void agregarPasaje(Pasaje pasaje) {
        if (pasaje != null) {
            pasajes.add(pasaje);
            // Sumamos el precio (1250.0) más el recargo (50.0) 
            // para que el total de la compra sea 1300.0
            this.total += (pasaje.getPrecio() + pasaje.getRecargo());
        }
    }

    /**
     * Método de seguridad para recalcular el total en cualquier momento
     */
    public void recalcularTotal() {
        this.total = 0;
        for (Pasaje p : pasajes) {
            this.total += (p.getPrecio() + p.getRecargo());
        }
    }

    public boolean confirmarCompra() {
        return pago != null && !pasajes.isEmpty();
    }

    // Getters y Setters
    public String getCodigo(){
        return codigo;
    }
    
    public Date getFecha(){
        return fecha;
    }
    
    public double getTotal(){
        return total;
    }
    
    public Usuario getUsuario(){
        return usuario;
    }
    
    public List<Pasaje> getPasajes(){
        return pasajes;
    }
    
    public Pago getPago(){
        return pago;
    }
    
    public void setPago(Pago pago){
        this.pago = pago;
    }
}