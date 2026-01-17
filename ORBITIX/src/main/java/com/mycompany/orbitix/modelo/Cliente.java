/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

public class Cliente extends Usuario {

    public Cliente(String cedula, String nombre, String email, String password) {
        super(cedula, nombre, email, password);
    }

    @Override
    public String toString() {
        return "Cliente{" + "nombre=" + nombre + '}';
    }
}
