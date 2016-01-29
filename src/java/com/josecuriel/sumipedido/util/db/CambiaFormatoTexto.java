/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.josecuriel.sumipedido.util.db;

import java.text.NumberFormat;


/**
 *
 * @author ellorente
 */


public class CambiaFormatoTexto {

    
    
    public String numerico(String texto){
        
        NumberFormat[] nfa = new NumberFormat[1];
        nfa[0] = NumberFormat.getInstance();
        String respuesta="";

        for (NumberFormat nf : nfa) {
            respuesta = (String)nf.format(Double.parseDouble(texto));
        }
        return respuesta;
    }
    public String numericoInt(int numero){
        
        NumberFormat[] nfa = new NumberFormat[1];
        nfa[0] = NumberFormat.getInstance();
        String respuesta="";

        for (NumberFormat nf : nfa) {
            respuesta = (String)nf.format(numero);
        }
        return respuesta;
    }
    public static String numerico(double numero){
       
        NumberFormat[] nfa = new NumberFormat[1];
        nfa[0] = NumberFormat.getInstance();
        String respuesta="";

        for (NumberFormat nf : nfa) {
            respuesta = (String)nf.format(numero);
        }
        
        
        return respuesta;
        
        
    }
    
    
    
}

/*
        protected void limpiar(Component component) {
                if (component instanceof JTextField) {
                        JTextField text = (JTextField) component;
                        text.setText("");
                } else {
                        if (component instanceof Container) {
                                for (Component c : ((Container) component).getComponents()) {
                                        limpiar(c);
                                }
                        }

                }
        }
 */