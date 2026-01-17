/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.vista;
import com.mycompany.orbitix.modelo.Vuelo;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author paula
 */


public class VistaMapaAsientos extends JDialog {
    // CAMBIO: Ahora es una lista para soportar múltiples asientos
    private List<String> asientosSeleccionados = new ArrayList<>();
    private Vuelo vuelo;

    public VistaMapaAsientos(JFrame padre, Vuelo vuelo) {
        super(padre, "Seleccione sus asientos - Orbitix", true);
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

        // Botón de confirmar declarado antes para actualizar su texto
        JButton btnConfirmar = new JButton("CONFIRMAR SELECCIÓN (0)");
        btnConfirmar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnConfirmar.setBackground(new Color(153, 0, 255));
        btnConfirmar.setForeground(Color.WHITE);

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
                            // Lógica de selección múltiple
                            if (asientosSeleccionados.contains(nombreAsiento)) {
                                // Deseleccionar
                                asientosSeleccionados.remove(nombreAsiento);
                                btnAsiento.setBackground(Color.GREEN);
                            } else {
                                // Seleccionar
                                asientosSeleccionados.add(nombreAsiento);
                                btnAsiento.setBackground(Color.YELLOW);
                            }
                            // Actualizar contador en el botón inferior
                            btnConfirmar.setText("CONFIRMAR SELECCIÓN (" + asientosSeleccionados.size() + ")");
                        });
                    }
                    panelAsientos.add(btnAsiento);
                    generados++;
                }
            }
        }

        btnConfirmar.addActionListener(e -> {
            if (asientosSeleccionados.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe elegir al menos un asiento (en verde).");
            } else {
                this.dispose();
            }
        });

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(panelAsientos), BorderLayout.CENTER);
        this.add(btnConfirmar, BorderLayout.SOUTH);
        this.setSize(700, 600); // Un poco más grande para comodidad
    }

    // CAMBIO: Ahora devuelve la lista completa
    public List<String> getAsientosSeleccionados() {
        return asientosSeleccionados;
    }
}