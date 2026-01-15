/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.datos;

import com.mycompany.orbitix.modelo.*;
import java.io.*;
import java.util.*;

public class RepositorioArchivos implements RepositorioDatos {

    private final String PATH_VUELOS = "vuelos.txt";
    private final String PATH_COMPRAS = "compras.txt";
    private final String PATH_PASAJES = "pasajes.txt"; 
    private final String PATH_USUARIOS = "usuarios.txt"; 

    @Override
    public void guardarVuelo(Vuelo v) {

         try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_VUELOS, true))) {
            String linea = String.format("%s;%s;%s;%.2f;%s;%d",
                    v.getCodigo(),
                    v.getRuta().getOrigen(), v.getRuta().getDestino(), v.getRuta().getDuracion(),
                    v.getAvion().getModelo(), v.getAvion().getCapacidad());
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public void guardarCompra(Compra c) {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_COMPRAS, true))) {
            String linea = String.format("%s;%.2f;%s;%s;%s",
                    c.getCodigo(),
                    c.getTotal(),
                    c.getUsuario().getCedula(),
                    c.getPago().getMetodo(),
                    c.getPago().getId());
            bw.write(linea);
            bw.newLine();
        } catch (IOException e) { e.printStackTrace(); }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_PASAJES, true))) {
            for (Pasaje p : c.getPasajes()) {
                String linea = String.format("%s;%s;%s;%s;%s",
                        p.getCodigo(),
                        c.getCodigo(),
                        p.getVuelo().getCodigo(),
                        p.getAsiento(),
                        p.getPasajero().getCedula());
                bw.write(linea);
                bw.newLine();
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    @Override
    public List<Vuelo> cargarVuelos() {
        List<Vuelo> vuelos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(PATH_VUELOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d.length >= 6) {
                    Ruta r = new Ruta(d[1], d[2], Double.parseDouble(d[3].replace(",", ".")));
                    Avion a = new Avion(d[4], Integer.parseInt(d[5]), "REG-X");
                    Vuelo v = new Vuelo(d[0], new Date(), "12:00", r, a);
                    vuelos.add(v);
                }
            }
        } catch (IOException e) { System.out.println("Error cargando vuelos: " + e.getMessage()); }
        cargarAsientosOcupados(vuelos);
        
        return vuelos;
    }

    private void cargarAsientosOcupados(List<Vuelo> vuelos) {
        File archivo = new File(PATH_PASAJES);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_PASAJES))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");
                if (d.length >= 4) {
                    String codigoVuelo = d[2];
                    String asiento = d[3];

                    for (Vuelo v : vuelos) {
                        if (v.getCodigo().equals(codigoVuelo)) {
                            v.ocuparAsiento(asiento);
                            break;
                        }
                    }
                }
            }
        } catch (IOException e) { e.printStackTrace(); }
    }
    
    public Usuario autenticarUsuario(String email, String password) {
         if(email.equals("admin@test.com") && password.equals("1234")) {
             return new Cliente("1712345678", "Administrador", email, password);
         }
         return null;
    }

    @Override public void guardarPasajero(Pasajero p){
    }
    @Override public List<Pasajero> cargarPasajeros(){
        return new ArrayList<>();
    }
    @Override public List<Compra> cargarCompras(){
        return new ArrayList<>();
    }
    @Override public void borrarTodo() {
        new File(PATH_VUELOS).delete();
        new File(PATH_COMPRAS).delete();
        new File(PATH_PASAJES).delete();
    }
}