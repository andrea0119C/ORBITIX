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
public class PasajeControlador {
    private RepositorioArchivos repo = new RepositorioArchivos();

    public boolean registrarVentaTotal(List<Pasaje> listaPasajes) {
        try {
            for (Pasaje p : listaPasajes) {
                repo.guardarPasajero(p.getPasajero());
                repo.guardarPasaje(p);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

