/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.orbitix;

import com.mycompany.orbitix.datos.RepositorioArchivos;
import com.mycompany.orbitix.datos.RepositorioDatos;
import com.mycompany.orbitix.modelo.*;
import java.util.List;
import java.util.Scanner;

//HOLA prueba

public class ORBITIX {

    public static void main(String[] args) {
        RepositorioDatos repo = new RepositorioArchivos();
        List<Vuelo> vuelosDisponibles = repo.cargarVuelos();

        Scanner scanner = new Scanner(System.in);

        System.out.println("=== BIENVENIDO A VUELOS-FIS ===");
        System.out.println("Cargando vuelos del sistema...");
        
        if (vuelosDisponibles.isEmpty()) {
            System.out.println("ERROR: No encontre el archivo 'vuelos.txt' o esta vacio.");
            System.out.println("Por favor, crea el archivo en la carpeta del proyecto.");
            return; 
        }

        System.out.println("Se han cargado " + vuelosDisponibles.size() + " vuelos del archivo txt.");
        System.out.println("-------------------------------------------------");

        System.out.print("Desde donde quieres viajar?: ");
        String origenUsuario = scanner.nextLine();

        System.out.print("A donde quieres ir?: ");
        String destinoUsuario = scanner.nextLine();

        Vuelo vueloEncontrado = null;

        for (Vuelo v : vuelosDisponibles) {
            if (v.getRuta().getOrigen().equalsIgnoreCase(origenUsuario) && 
                v.getRuta().getDestino().equalsIgnoreCase(destinoUsuario)) {
                vueloEncontrado = v;
                break; 
            }
        }

        if (vueloEncontrado != null) {
            System.out.println("\n VUELO ENCONTRADO!");
            System.out.println("---------------------------------");
            System.out.println("Codigo de Vuelo: " + vueloEncontrado.getCodigo());
            System.out.println("Horario: " + vueloEncontrado.getHora());
            System.out.println("Duracion: " + vueloEncontrado.getRuta().getDuracion() + " horas");
            System.out.println("Avion Asignado: " + vueloEncontrado.getAvion().getModelo());
            System.out.println("Capacidad: " + vueloEncontrado.getAvion().getCapacidad() + " pasajeros");
            System.out.println("---------------------------------");
            System.out.println("Desea reservar este pasaje? (S/N)...");
        } else {
            System.out.println("\nLo sentimos, no tenemos vuelos programados para esa ruta.");
            System.out.println("Intenta buscar: Quito -> Miami o Guayaquil -> Madrid");
        }
    }
}