/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.mycompany.orbitix.vista;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fondo extends JPanel {
    private Image imagen;

    
    public Fondo(String ruta) {
        try {
           
            imagen = new ImageIcon(getClass().getResource(ruta)).getImage();
        } catch (Exception e) {
            System.out.println("Error: No se pudo cargar la imagen en " + ruta);
        }
    }

    @Override
    protected void paintComponent(Graphics g) { 
        super.paintComponent(g);
        if (imagen != null) {
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
        }
    }
}