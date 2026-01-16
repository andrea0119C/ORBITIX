/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.controlador;

import com.mycompany.orbitix.datos.RepositorioArchivos;
import com.mycompany.orbitix.modelo.Cliente;
import com.mycompany.orbitix.modelo.Usuario;
import com.mycompany.orbitix.vista.VistaAcceso;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorAcceso implements ActionListener {

    private VistaAcceso vista;
    private RepositorioArchivos modelo;

    public ControladorAcceso(VistaAcceso vista, RepositorioArchivos modelo) {
        this.vista = vista;
        this.modelo = modelo;
        asignarEventos();
    }

    private void asignarEventos() {
        // Navegación
        vista.getBtnIrALogin().addActionListener(this);
        vista.getBtnIrARegistro().addActionListener(this);
        vista.getBtnVolverLogin().addActionListener(this);
        vista.getBtnVolverRegistro().addActionListener(this);
        
        // Acciones Lógicas
        vista.getBtnIngresar().addActionListener(this);
        vista.getBtnRegistrar().addActionListener(this);
    }

    public void iniciar() {
        vista.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        // --- LÓGICA DE NAVEGACIÓN ---
        if (fuente == vista.getBtnIrALogin()) {
            vista.mostrarPanelLogin();
        } 
        else if (fuente == vista.getBtnIrARegistro()) {
            vista.mostrarPanelRegistro();
        } 
        else if (fuente == vista.getBtnVolverLogin() || fuente == vista.getBtnVolverRegistro()) {
            vista.mostrarPanelMenu();
        }

        // --- LÓGICA DE LOGIN ---
        else if (fuente == vista.getBtnIngresar()) {
            procesarLogin();
        }

        // --- LÓGICA DE REGISTRO ---
        else if (fuente == vista.getBtnRegistrar()) {
            procesarRegistro();
        }
    }

    private void procesarLogin() {
        String email = vista.getLoginEmail();
        String pass = vista.getLoginPass();

        Usuario u = modelo.autenticarUsuario(email, pass);
        
        if (u != null) {
            JOptionPane.showMessageDialog(vista, "¡Bienvenido " + u.getNombre() + "!");
            vista.dispose(); // Cierra ventana de acceso
            
            // AQUÍ ABRIRÍAS LA VENTANA PRINCIPAL DEL SISTEMA
            // ControladorPrincipal ctrl = ...
        } else {
            JOptionPane.showMessageDialog(vista, "Credenciales incorrectas.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void procesarRegistro() {
        // 1. Obtener datos
        String nom = vista.getRegNombre();
        String ced = vista.getRegCedula();
        String email = vista.getRegEmail();
        String pass = vista.getRegPass();

        // 2. Validar
        if(nom.isEmpty() || ced.isEmpty() || email.isEmpty() || pass.isEmpty()){
            JOptionPane.showMessageDialog(vista, "Todos los campos son obligatorios.");
            return;
        }

        // 3. Crear y Guardar
        Cliente nuevo = new Cliente(ced, nom, email, pass);
        modelo.guardarCliente(nuevo);

        // 4. Feedback y redirigir
        JOptionPane.showMessageDialog(vista, "Cuenta creada con éxito. Ahora inicie sesión.");
        vista.mostrarPanelLogin(); // Automáticamente lo mandamos al login para que entre
    }
}