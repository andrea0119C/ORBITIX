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


    @Override
    public void guardarVuelo(Vuelo v) {
    
        String linea = String.format("%s;%s;%s;%.2f;%s;%d",
                v.getCodigo(),
                v.getRuta().getOrigen(), v.getRuta().getDestino(), v.getRuta().getDuracion(),
                v.getAvion().getModelo(), v.getAvion().getCapacidad());
        escribirArchivo(PATH_VUELOS, linea);
    }

    @Override
    public void guardarCompra(Compra c) {
       
        String linea = String.format("%s;%.2f;%s;%s;%s",
                c.getCodigo(),
                c.getTotal(),
                c.getPasajero().getCedula(),
                c.getPago().getMetodo(),
                c.getPago().getId());
        escribirArchivo(PATH_COMPRAS, linea);
    }



    @Override
    public List<Vuelo> cargarVuelos() { 
        List<Vuelo> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_VUELOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d.length >= 6) { // Validación simple para evitar errores
                    // Reconstrucción jerárquica: Ruta + Avion -> Vuelo
                    // .replace(",", ".") ayuda si tu compu está en español y el archivo en inglés
                    Ruta r = new Ruta(d[1], d[2], Double.parseDouble(d[3].replace(",", ".")));
                    Avion a = new Avion(d[4], Integer.parseInt(d[5]), "REG-AUTO");
                    // Nota: La fecha se crea nueva cada vez, idealmente debería guardarse en el txt también
                    Vuelo v = new Vuelo(d[0], new java.util.Date(), "12:00", r, a);
                    lista.add(v);
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("No se pudo cargar vuelos (archivo nuevo o error de formato).");
        }
        return lista;
    }

    @Override
    public void guardarPasajero(Pasajero pasajero) {

    }

    @Override
    public List<Pasajero> cargarPasajeros() {
        return new ArrayList<>(); 
    }

    @Override
    public List<Compra> cargarCompras() {
        return new ArrayList<>(); 
    }

    @Override
    public void borrarTodo() {
        new File(PATH_VUELOS).delete();
        new File(PATH_COMPRAS).delete();
    }

    
    private void escribirArchivo(String path, String contenido) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path, true))) {
            bw.write(contenido);
            bw.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}