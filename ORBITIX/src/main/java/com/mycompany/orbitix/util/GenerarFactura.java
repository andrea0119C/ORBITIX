/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.orbitix.util;

import com.mycompany.orbitix.modelo.Pasaje;
import com.mycompany.orbitix.modelo.Usuario;
import com.mycompany.orbitix.modelo.Vuelo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author paopa
 */
public class GenerarFactura {
    public static String generarFactura(String numFactura, Usuario user, Vuelo vuelo, List<Pasaje> pasajes) {
        Locale.setDefault(Locale.US);

        SimpleDateFormat sdfFecha = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat sdfFechaHora = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        double subtotalBase = 0;
        double totalRecargos = 0;
        double totalDescuentos = 0;

        StringBuilder sb = new StringBuilder();
        sb.append("============================================\n");
        sb.append("               ORBITIX - FACTURA            \n");
        sb.append("============================================\n");
        sb.append("N° Factura: ").append(numFactura).append("\n");
        sb.append("Fecha/Hora: ").append(sdfFechaHora.format(new Date())).append("\n");
        sb.append("--------------------------------------------\n");

        sb.append("CLIENTE:\n");
        sb.append("Nombre : ").append(user != null ? user.getNombre() : "N/A").append("\n");
        // Si tu Usuario tiene email/cedula, descomenta:
        // sb.append("Correo : ").append(user.getEmail()).append("\n");
        // sb.append("Cédula : ").append(user.getCedula()).append("\n");
        sb.append("--------------------------------------------\n");

        sb.append("VUELO:\n");
        sb.append("Código : ").append(vuelo != null ? vuelo.getCodigo() : "N/A").append("\n");
        if (vuelo != null && vuelo.getRuta() != null) {
            sb.append("Ruta   : ").append(vuelo.getRuta().getOrigen())
              .append(" -> ").append(vuelo.getRuta().getDestino()).append("\n");
        }
        if (vuelo != null && vuelo.getFecha() != null) {
            sb.append("Fecha  : ").append(sdfFecha.format(vuelo.getFecha())).append("\n");
        }
        sb.append("--------------------------------------------\n");

        sb.append("DETALLE:\n");
        sb.append("Pasajero | Asiento | Clase | Base | Recargo | Desc | Total\n");
        sb.append("----------------------------------------------------------\n");

        int totalPasajes = pasajes != null ? pasajes.size() : 0;

        if (pasajes != null) {
            for (Pasaje p : pasajes) {
                double base = p.getPrecio();
                double recargo = p.getRecargo();

                // Descuento simple de ejemplo: 10% si compra 3 o más pasajes
                double descuento = 0;
                String motivoDesc = "";
                if (totalPasajes >= 3) {
                    descuento = base * 0.10;
                    motivoDesc = "Descuento por compra grupal (3+ pasajes) -10%";
                }

                String motivoRec = "";
                if (recargo > 0) {
                    motivoRec = "Recargo aplicado por clase/equipaje";
                }

                double totalLinea = base + recargo - descuento;

                subtotalBase += base;
                totalRecargos += recargo;
                totalDescuentos += descuento;

                String nombrePasajero = (p.getPasajero() != null) ? p.getPasajero().getNombre() : "N/A";

                sb.append(nombrePasajero).append(" | ")
                  .append(p.getAsiento()).append(" | ")
                  .append(p.getClase()).append(" | ")
                  .append(String.format("%.2f", base)).append(" | ")
                  .append(String.format("%.2f", recargo)).append(" | ")
                  .append(String.format("%.2f", descuento)).append(" | ")
                  .append(String.format("%.2f", totalLinea)).append("\n");

                if (!motivoRec.isEmpty()) sb.append("   -> Motivo recargo: ").append(motivoRec).append("\n");
                if (!motivoDesc.isEmpty()) sb.append("   -> Motivo descuento: ").append(motivoDesc).append("\n");
            }
        }

        sb.append("--------------------------------------------\n");
        sb.append("RESUMEN:\n");
        sb.append("Subtotal (Base)   : $").append(String.format("%.2f", subtotalBase)).append("\n");
        sb.append("Total Recargos    : $").append(String.format("%.2f", totalRecargos)).append("\n");
        sb.append("Total Descuentos  : $").append(String.format("%.2f", totalDescuentos)).append("\n");

        double totalFinal = subtotalBase + totalRecargos - totalDescuentos;
        sb.append("TOTAL A PAGAR     : $").append(String.format("%.2f", totalFinal)).append("\n");
        sb.append("============================================\n");
        sb.append("Gracias por su compra.\n");

        return sb.toString();
    }
}
   
