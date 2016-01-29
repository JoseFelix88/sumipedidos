

package com.josecuriel.sumipedido.util.db;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;



public class Conexcion implements Serializable{
    
    
    public static Conexcion instancia;
    private Connection cnn;
    private String bd = "db_sumipedidos";
    private String login = "root";
    private String password = "negro88";
    private String url = "jdbc:mysql://localhost/" + bd;
   
    private Conexcion(){
    
        
        try {
            //obtenemos el driver de para mysql
            Class.forName("com.mysql.jdbc.Driver");
            //obtenemos la conexión
            cnn = DriverManager.getConnection(url, login, password);
            if (cnn != null) {
                System.out.println("OK base de datos " + url + ". listo");
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexcion.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    
    }
    
    public synchronized static Conexcion saberEstado(){
        
        if (instancia == null) {
            instancia = new Conexcion();
        }
        
        return instancia;
    }

    public Connection getCnn() {
        return cnn;
    }
    
    public void cerrarConexcion(){
        
        instancia = null;
    }
    
    
}
