/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.datos;
import com.mycompany.orbitix.modelo.*;
import java.util.List;

public interface RepositorioDatos {
    
    // --- Gestión de Vuelos ---
    void guardarVuelo(Vuelo vuelo); // Guarda un vuelo en vuelos.txt
    List<Vuelo> cargarVuelos();    // Recupera todos los vuelos
    
    // --- Gestión de Usuarios y Pasajeros ---
    // El UML muestra que Pasajero es una entidad clave en la Compra
    void guardarPasajero(Pasajero pasajero); 
    List<Pasajero> cargarPasajeros();
    
    // --- Gestión de Compras y Pagos ---
    // Una compra agrupa Pasaje, Pago y Pasajero según tu UML
    void guardarCompra(Compra compra);
    List<Compra> cargarCompras(); 
    
    // --- Utilidades de limpieza ---
    void borrarTodo(); // Útil para reiniciar el sistema durante pruebas
}


