/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.datos;

import com.mycompany.orbitix.modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class RepositorioArchivos implements RepositorioDatos {

    private final String PATH_VUELOS = "vuelos.txt";
    private final String PATH_COMPRAS = "compras.txt";

    // --- MÉTODOS DE GUARDADO ---

    public void guardarVuelo(Vuelo v) {
        // Guardamos: codigo;origen;destino;duracion;modeloAvion;capacidad
        String linea = String.format("%s;%s;%s;%.2f;%s;%d",
                v.getCodigo(),
                v.getRuta().getOrigen(), v.getRuta().getDestino(), v.getRuta().getDuracion(),
                v.getAvion().getModelo(), v.getAvion().getCapacidad());
        escribirArchivo(PATH_VUELOS, linea);
    }

    public void guardarCompra(Compra c) {
        // Guardamos: codigoCompra;total;cedulaPasajero;metodoPago;idPago
        String linea = String.format("%s;%.2f;%s;%s;%s",
                c.getCodigo(),
                c.getTotal(),
                c.getPasajero().getCedula(),
                c.getPago().getMetodo(),
                c.getPago().getId());
        escribirArchivo(PATH_COMPRAS, linea);
    }

    // --- MÉTODOS DE CARGA ---

    @Override
    public List<Vuelo> cargarDatos() {
        List<Vuelo> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_VUELOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                // Reconstrucción jerárquica: Ruta + Avion -> Vuelo
                Ruta r = new Ruta(d[1], d[2], Double.parseDouble(d[3]));
                Avion a = new Avion(d[4], Integer.parseInt(d[5]), "REG-AUTO");
                Vuelo v = new Vuelo(d[0], new java.util.Date(), "12:00", r, a);
                lista.add(v);
            }
        } catch (IOException e) {
            System.out.println("Iniciando archivo de vuelos nuevo...");
        }
        return lista;
    }

    // --- UTILIDAD PRIVADA PARA LIMPIEZA ---
    private void escribirArchivo(String path, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(contenido);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
