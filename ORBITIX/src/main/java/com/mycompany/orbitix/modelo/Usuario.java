/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;



public abstract class Usuario {
    protected String cedula;
    protected String nombre;
    protected String email;
    protected String password; 

    public Usuario(String cedula, String nombre, String email, String password) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }


    public String getCedula(){
        return cedula;
    }
    public String getNombre(){
        return nombre;
    }
    public String getEmail(){
        return email;
    }
    public String getPassword(){return password;
    }
}