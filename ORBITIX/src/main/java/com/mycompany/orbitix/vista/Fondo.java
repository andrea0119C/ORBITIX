/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.orbitix.vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
/**
 *
 * @author paula
 */

public class Fondo extends JPanel {

    private Image imagenFondo;

    public Fondo(String rutaImagen) {
        try {
            imagenFondo = new ImageIcon(getClass().getResource(rutaImagen)).getImage();
        } catch (Exception e) {
            System.err.println("Error: No se encontró la imagen: " + rutaImagen);
        }
        setOpaque(false); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenFondo != null) {
            g.drawImage(imagenFondo, 0, 0, getWidth(), getHeight(), this);
        }
    }
}