/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

/**
 *
 * @author USUARIO
 */
public class Avion {

    private String modelo;
    private int capacidad;
    private String registroAeronualico;

    public Avion(String modelo, int capacidad, String registroAeronualico) {
        this.modelo = modelo;
        this.capacidad = capacidad;
        this.registroAeronualico = registroAeronualico;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getRegistroAeronualico() {
        return registroAeronualico;
    }

    public void setRegistroAeronualico(String registroAeronualico) {
        this.registroAeronualico = registroAeronualico;
    }

    @Override
    public String toString() {
        return "Avion{" +
                "modelo=" + modelo +
                ", capacidad=" + capacidad +
                ", registroAeronualico=" + registroAeronualico +
                '}';
    }
}
