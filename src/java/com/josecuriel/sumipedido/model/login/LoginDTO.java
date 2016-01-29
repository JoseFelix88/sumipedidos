

package com.josecuriel.sumipedido.model.login;

import java.io.Serializable;


public class LoginDTO implements Serializable{
    
    private String idusuario;
    private String Nombre;
    private String clave;

    public LoginDTO() {
    }

    public LoginDTO(String idusuario, String Nombre, String clave) {
        this.idusuario = idusuario;
        this.Nombre = Nombre;
        this.clave = clave;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }
 
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
    
    
    
    
    
}
