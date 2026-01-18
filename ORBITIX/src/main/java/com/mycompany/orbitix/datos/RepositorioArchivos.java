/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.datos;

import com.mycompany.orbitix.modelo.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class RepositorioArchivos implements RepositorioDatos {

    private final String PATH_VUELOS = "vuelos.txt";
    private final String PATH_COMPRAS = "compras.txt";
    private final String PATH_PASAJES = "pasajes.txt"; 
    private final String PATH_USUARIOS = "usuarios.txt"; 
    private final String PATH_PASAJEROS = "pasajeros.txt"; 

        @Override
        public void guardarVuelo(Vuelo v) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_VUELOS, true))) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
       
                String linea = String.format(Locale.US, "%s;%s;%s;%.2f;%s;%d;%s;%.2f",
                        v.getCodigo(),
                        v.getRuta().getOrigen(), 
                        v.getRuta().getDestino(), 
                        v.getRuta().getDuracion(),
                        v.getAvion().getModelo(), 
                        v.getAvion().getCapacidad(),
                        sdf.format(v.getFecha()), 
                        v.getPrecio());           
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
      
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_VUELOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] d = linea.split(";");

            
                if (d.length >= 8) {
                    try {
                      
                        Ruta r = new Ruta(d[1], d[2], Double.parseDouble(d[3].replace(",", ".")));
                        Avion a = new Avion(d[4], Integer.parseInt(d[5]), "REG-X");

                      
                        Date fechaReal = sdf.parse(d[6]);

                       
                        double precioLeido = Double.parseDouble(d[7].replace(",", "."));

               
                        Vuelo v = new Vuelo(d[0], fechaReal, "12:00", r, a, precioLeido);

                        vuelos.add(v);
                    } catch (Exception ex) {
                        System.out.println("Error procesando línea: " + linea + " - " + ex.getMessage());
                    }
                }
            }
        } catch (IOException e) { 
            System.out.println("Error cargando vuelos: " + e.getMessage()); 
        }

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
    

    @Override 
public void guardarPasajero(Pasajero p) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_PASAJEROS, true))) {
        String linea = String.format("%s;%s;%s;%s;%s;%d",
                p.getCedula(), p.getNombre(), p.getApellido(), 
                p.getTelefono(), p.getEmail(), p.getEdad());
        bw.write(linea);
        bw.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
}


public void guardarPasaje(Pasaje p) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_PASAJES, true))) {
        String linea = String.format("%s;%.2f;%s;%s;%s",
                p.getCodigo(), p.getPrecio(), p.getVuelo().getCodigo(), 
                p.getAsiento(), p.getPasajero().getCedula());
        bw.write(linea);
        bw.newLine();
    } catch (IOException e) {
        e.printStackTrace();
    }
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
    


    @Override public void guardarCliente(Cliente c) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(PATH_USUARIOS, true))) {
                String linea = String.format("%s;%s;%s;%s",
                        c.getCedula(), c.getNombre(), c.getEmail(), c.getPassword());
                bw.write(linea);
                bw.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    @Override
    public Usuario autenticarUsuario(String email, String password) {
        if(email.trim().equalsIgnoreCase("admin@test.com") && password.equals("1234")) {
            return new Cliente("1712345678", "Administrador", email, password);
        }

        File archivo = new File(PATH_USUARIOS);
        if (!archivo.exists()) return null;

        try (BufferedReader br = new BufferedReader(new FileReader(PATH_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(";");
                if (datos.length >= 4) {
                    String emailArchivo = datos[2].trim();
                    String passArchivo = datos[3].trim();

                    if (emailArchivo.equalsIgnoreCase(email.trim()) && passArchivo.equals(password.trim())) {
                        return new Cliente(datos[0].trim(), datos[1].trim(), datos[2].trim(), datos[3].trim());
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer usuarios: " + e.getMessage());
        }
        return null; 
    }

 public List<Compra> obtenerListaCompras() {
    List<Compra> lista = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(PATH_COMPRAS))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(";");
            Usuario u = buscarUsuarioPorCedula(d[2]);
            Compra c = new Compra(d[0], u);
            asociarPasajesACompra(c); // Une los pasajes con la compra
            lista.add(c);
        }
    } catch (IOException e) { e.printStackTrace(); }
    return lista;
}

 @Override
public Usuario buscarUsuarioPorCedula(String cedula) {
   
    try (BufferedReader br = new BufferedReader(new FileReader("usuarios.txt"))) { 
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(";");
        
            if (d[0].equals(cedula)) {
         
                return new Cliente(d[0], d[1], d[2], d[3]);
            }
        }
    } catch (IOException e) {
        System.err.println("Error al buscar usuario: " + e.getMessage());
    }
    return null; 
}

private void asociarPasajesACompra(Compra c) {
    try (BufferedReader br = new BufferedReader(new FileReader(PATH_PASAJES))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(";");
            if (d[1].equals(c.getCodigo())) {
                Vuelo v = buscarVueloPorCodigo(d[2]);
                Pasajero p = buscarPasajeroPorCedula(d[4]);
                Pasaje pasaje = new Pasaje(d[0], v.getPrecio(), d[3], null, p, v);
                c.agregarPasaje(pasaje);
            }
        }
    } catch (IOException e) { e.printStackTrace(); }
}

    @Override
    public Pasajero buscarPasajeroPorCedula(String cedula) {
    try (BufferedReader br = new BufferedReader(new FileReader(PATH_PASAJEROS))) {
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(";");
            if (d[0].equals(cedula)) 
                return new Pasajero(d[0], d[1], d[2], d[3], d[4], Integer.parseInt(d[5]));
        }
    } catch (Exception e) { e.printStackTrace(); }
    return null;
}

    @Override
    public Vuelo buscarVueloPorCodigo(String codigoVuelo) {
    try (BufferedReader br = new BufferedReader(new FileReader("vuelos.txt"))) { // Ajusta el nombre de tu archivo
        String linea;
        while ((linea = br.readLine()) != null) {
            String[] d = linea.split(";");
            if (d[0].equals(codigoVuelo)) {
                // Aquí reconstruyes el vuelo según tus constructores de Vuelo.
                // Ejemplo: return new Vuelo(d[0], d[1], d[2], Double.parseDouble(d[3]));
                // Debes adaptarlo a los atributos de tu clase Vuelo
            }
        }
    } catch (IOException e) {
        System.err.println("Error al buscar vuelo: " + e.getMessage());
    }
    return null;
}
}