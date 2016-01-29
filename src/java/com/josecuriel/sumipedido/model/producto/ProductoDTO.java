

package com.josecuriel.sumipedido.model.producto;

import java.io.Serializable;

public class ProductoDTO implements Serializable{
    
    private int itemproducto;
    private String cumproducto;
    private String descripcion;
    private int costo;
    private int precioventa;

    public ProductoDTO() {
    }

    public ProductoDTO(int itemproducto, String cumproducto, String descripcion, int precioventa,int costo) {
        this.itemproducto = itemproducto;
        this.cumproducto = cumproducto;
        this.descripcion = descripcion;
        this.costo = costo;
        this.precioventa = precioventa;
    }

   

    public String getCumproducto() {
        return cumproducto;
    }

    public void setCumproducto(String cumproducto) {
        this.cumproducto = cumproducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCosto() {
        return costo;
    }

    public void setCosto(int costo) {
        this.costo = costo;
    }

    public int getPrecioventa() {
        return precioventa;
    }

    public void setPrecioventa(int precioventa) {
        this.precioventa = precioventa;
    }

    public int getItemproducto() {
        return itemproducto;
    }

    public void setItemproducto(int itemproducto) {
        this.itemproducto = itemproducto;
    }
    
    
    
}
