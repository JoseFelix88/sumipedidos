package com.josecuriel.sumipedido.model.orden;

import com.josecuriel.sumipedido.model.afiliado.AfiliadoDTO;
import java.io.Serializable;
import java.util.Date;

public class OrdenDTO implements Serializable {

    private int itemorden;
    private String numOrden;
    private Date fechaOrden;
    private Date fechaFormula;
    private String puntoEntrega;
    private String idAfiliado;
    private String nombreApellido;
    private String telefono;
    private String producto;
    private int cantidad;
    private String estado;
    private Date fechaEntrega;
    private Date fechaIngreso;
    private String cumProducto;
    private AfiliadoDTO afiliado;

    public OrdenDTO() {
    }

    public OrdenDTO(int itemorden, String numOrden, Date fechaOrden, Date fechaFormula, String puntoEntrega, String idAfiliado, String nombreApellido, String telefono, String producto, int cantidad, String estado, Date fechaEntrega, Date fechaIngreso, String cumProducto) {
        this.itemorden = itemorden;
        this.numOrden = numOrden;
        this.fechaOrden = fechaOrden;
        this.fechaFormula = fechaFormula;
        this.puntoEntrega = puntoEntrega;
        this.idAfiliado = idAfiliado;
        this.nombreApellido = nombreApellido;
        this.telefono = telefono;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.fechaIngreso = fechaIngreso;
        this.cumProducto = cumProducto;
    }
    
    

    public OrdenDTO(String numOrden, Date fechaOrden, Date fechaFormula, String puntoEntrega, String idAfiliado, String nombreApellido, String telefono, String producto, int cantidad, String estado, Date fechaEntrega, Date fechaIngreso, String cumProducto) {
        this.numOrden = numOrden;
        this.fechaOrden = fechaOrden;
        this.fechaFormula = fechaFormula;
        this.puntoEntrega = puntoEntrega;
        this.idAfiliado = idAfiliado;
        this.nombreApellido = nombreApellido;
        this.telefono = telefono;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado = estado;
        this.fechaEntrega = fechaEntrega;
        this.fechaIngreso = fechaIngreso;
        this.cumProducto = cumProducto;
    }

    public OrdenDTO(String numOrden, Date fechaOrden, Date fechaFormula, 
            String puntoEntrega, String idAfiliado, String nombreApellido, 
            String telefono, String producto, int cantidad, String estado) {
        this.numOrden = numOrden;
        this.fechaOrden = fechaOrden;
        this.fechaFormula = fechaFormula;
        this.puntoEntrega = puntoEntrega;
        this.idAfiliado = idAfiliado;
        this.nombreApellido = nombreApellido;
        this.telefono = telefono;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    public OrdenDTO(String numOrden, Date fechaOrden, Date fechaFormula, String puntoEntrega,
            String idAfiliado, String nombreApellido, String telefono, String producto, int cantidad) {

        this.numOrden = numOrden;
        this.fechaOrden = fechaOrden;
        this.fechaFormula = fechaFormula;
        this.puntoEntrega = puntoEntrega;
        this.idAfiliado = idAfiliado;
        this.nombreApellido = nombreApellido;
        this.telefono = telefono;
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public String getNumOrden() {
        return numOrden;
    }

    public void setNumOrden(String numOrden) {
        this.numOrden = numOrden;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public Date getFechaFormula() {
        return fechaFormula;
    }

    public void setFechaFormula(Date fechaFormula) {
        this.fechaFormula = fechaFormula;
    }

    public String getPuntoEntrega() {
        return puntoEntrega;
    }

    public void setPuntoEntrega(String puntoEntrega) {
        this.puntoEntrega = puntoEntrega;
    }

    public String getIdAfiliado() {
        return idAfiliado;
    }

    public void setIdAfiliado(String idAfiliado) {
        this.idAfiliado = idAfiliado;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getCumProducto() {
        return cumProducto;
    }

    public void setCumProducto(String cumProducto) {
        this.cumProducto = cumProducto;
    }

    public int getItemorden() {
        return itemorden;
    }

    public void setItemorden(int itemorden) {
        this.itemorden = itemorden;
    }

    public AfiliadoDTO getAfiliado() {
        return afiliado;
    }

    public void setAfiliado(AfiliadoDTO afiliado) {
        this.afiliado = afiliado;
    }

}
