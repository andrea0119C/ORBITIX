/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.modelo;

/**
 *
 * @author karla
 */
import com.mycompany.orbitix.metodoPago;
import java.util.Date;

/**
 *
 * @author karla
 */
public class Pago {
    // Atributos privados (Encapsulamiento)
    private String id;
    private double monto;
    private Date fecha;
    private metodoPago metodo;

    // Constructor: Inicializa el objeto
    public Pago(String id, double monto, Date fecha, metodoPago metodo) {
        this.id = id;
        this.monto = monto;
        this.fecha = fecha;
        this.metodo = metodo;
    }

    // Método de negocio indicado en el UML
    public boolean procesarPago() {
        // Lógica simulada para un proyecto de 2do semestre
        if (monto > 0 && metodo != null) {
            System.out.println("Procesando pago de $" + monto + " vía " + metodo);
            System.out.println("Pago con ID " + id + " aprobado exitosamente.");
            return true;
        } else {
            System.out.println("Error: Monto inválido o método de pago no seleccionado.");
            return false;
        }
    }

    // Getters y Setters (Para poder leer/modificar los datos desde otras clases)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public metodoPago getMetodo() {
        return metodo;
    }

    public void setMetodo(metodoPago metodo) {
        this.metodo = metodo;
    }
}

