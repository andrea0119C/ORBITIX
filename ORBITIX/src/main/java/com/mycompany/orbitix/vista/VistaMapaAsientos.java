/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.vista;
import com.mycompany.orbitix.modelo.Vuelo;
import javax.swing.*;
import java.awt.*;

/**
 *
 * @author paula
 */

public class VistaMapaAsientos extends JDialog {
    private String asientoSeleccionado = "";
    private Vuelo vuelo;
    private JButton asientoBotonPresionado = null;

    public VistaMapaAsientos(JFrame padre, Vuelo vuelo) {
        super(padre, "Seleccione su asiento - Orbitix", true);
        this.vuelo = vuelo;
        initComponents();
        setLocationRelativeTo(padre);
    }

    private void initComponents() {
   
        int capacidad = vuelo.getAvion().getCapacidad();
 
        int numColumnas = 6;
        int filas = (int) Math.ceil((double) capacidad / numColumnas);
        
        JPanel panelAsientos = new JPanel(new GridLayout(filas, numColumnas, 8, 8));
        panelAsientos.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panelAsientos.setBackground(new Color(51, 0, 51)); 

        char[] letras = {'A', 'B', 'C', 'D', 'E', 'F'};
        int generados = 0;

        for (int f = 1; f <= filas; f++) {
            for (char letra : letras) {
                if (generados < capacidad) {
                    String nombreAsiento = letra + String.valueOf(f);
                    JButton btnAsiento = new JButton(nombreAsiento);
                    

                    if (!vuelo.esAsientoDisponible(nombreAsiento)) {
                        btnAsiento.setBackground(Color.RED);
                        btnAsiento.setEnabled(false);
                    } else {
                        btnAsiento.setBackground(Color.GREEN);
                        btnAsiento.addActionListener(e -> {
                            if (asientoBotonPresionado != null) {
                                asientoBotonPresionado.setBackground(Color.GREEN);
                            }
                            asientoSeleccionado = nombreAsiento;
                            btnAsiento.setBackground(Color.YELLOW);
                            asientoBotonPresionado = btnAsiento;
                        });
                    }
                    panelAsientos.add(btnAsiento);
                    generados++;
                }
            }
        }

        JButton btnConfirmar = new JButton("CONFIRMAR ASIENTO " + (asientoSeleccionado));
        btnConfirmar.addActionListener(e -> {
            if (asientoSeleccionado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe elegir un asiento verde.");
            } else {
                this.dispose();
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(panelAsientos), BorderLayout.CENTER);
        this.add(btnConfirmar, BorderLayout.SOUTH);
        this.setSize(600, 500);
    }

    public String getAsientoSeleccionado() {
        return asientoSeleccionado;
    }
}