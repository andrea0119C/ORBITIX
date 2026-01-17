/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

/**
 *
 * @author karla
 */
public class Equipaje {
    private TipoEquipaje tipo;
    private double pesoKg;
    private double costoExtra;

    public Equipaje(TipoEquipaje tipo, double pesoKg) {
        this.tipo = tipo;
        this.pesoKg = pesoKg;
        this.costoExtra = calcularCostoExtra();
    }

    private double calcularCostoExtra() {
        switch (tipo) {
            case ARTICULO_PERSONAL:
                return 0.0;
            case MALETA_MANO:
                return 20.0;
            case MALETA_BODEGA:
                return 50.0;
            default:
                return 0.0;
        }
    }

    public TipoEquipaje getTipo() {
        return tipo;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    public double getCostoExtra() {
        return costoExtra;
    }
}