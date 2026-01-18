/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.controlador;

import com.mycompany.orbitix.datos.RepositorioArchivos;
import com.mycompany.orbitix.modelo.Pasaje;
import java.util.List;

/**
 *
 * @author karla
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class PasajeControlador {

    private static final String ARCHIVO = "pasajeros.txt";

    public boolean registrarVentaTotal(List<Pasaje> pasajes) {
        try (FileWriter fw = new FileWriter(ARCHIVO, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {

            for (Pasaje p : pasajes) {
                StringBuilder sb = new StringBuilder();
                sb.append(p.getPasajero().getCedula()).append(";");
                sb.append(p.getPasajero().getNombre()).append(" ").append(p.getPasajero().getApellido()).append(";");
                sb.append(p.getPasajero().getEdad()).append(";");
                sb.append(p.getVuelo().getCodigo()).append(";");
                sb.append(p.getAsiento()).append(";");
                sb.append(p.getEquipaje().getTipo()).append(";");
                sb.append(p.getPrecio()).append(";");
                sb.append(p.getEquipaje().getCostoExtra());

                out.println(sb.toString());
            }
            return true;
        } catch (IOException e) {
            System.err.println("Error al escribir en pasajeros.txt: " + e.getMessage());
            return false;
        }
    }
}
