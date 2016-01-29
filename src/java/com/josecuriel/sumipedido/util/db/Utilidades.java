package com.josecuriel.sumipedido.util.db;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utilidades implements Serializable {
 
    public String formatearFecha(Date fecha) {

        try {

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");

            String fechaConFormato = formato.format(fecha);

            return fechaConFormato;

        } catch (Exception er) {
        }

        return null;
    }
    
     public String formatearFechaDMY(Date fecha) {

        try {

            SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

            String fechaConFormato = formato.format(fecha);

            return fechaConFormato;

        } catch (Exception er) {
        }

        return null;
    }
}
