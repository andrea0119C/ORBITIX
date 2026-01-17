/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.datos;
import com.mycompany.orbitix.modelo.*;
import java.util.List;

public interface RepositorioDatos {
    
  
    void guardarVuelo(Vuelo vuelo); 
    List<Vuelo> cargarVuelos();    
    
    void guardarCliente(Cliente cliente); 
    Usuario autenticarUsuario(String email, String password);
    
    void guardarPasajero(Pasajero pasajero); 
    List<Pasajero> cargarPasajeros();

    void guardarCompra(Compra compra);
    List<Compra> cargarCompras(); 
    
    
    void borrarTodo(); 
}


