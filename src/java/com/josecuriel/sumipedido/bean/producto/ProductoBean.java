package com.josecuriel.sumipedido.bean.producto;

import com.josecuriel.sumipedido.model.producto.ProductoDAO;
import com.josecuriel.sumipedido.model.producto.ProductoDTO;
import com.josecuriel.sumipedido.util.db.CambiaFormatoTexto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import org.primefaces.event.CellEditEvent;

@ManagedBean
@ViewScoped
public class ProductoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<ProductoDTO> listarproductos = new ArrayList<>();
    private List<ProductoDTO> filtrarproductos = new ArrayList<>();
    private List<ProductoDTO> listarproductosSinPrecio = new ArrayList<>();
    private List<ProductoDTO> listarproductosSinPrecioFiltro = new ArrayList<>();
    private List<ProductoDTO> listarproductosEntregado = new ArrayList<>();
    private List<ProductoDTO> listarproductosEntregadoFiltro = new ArrayList<>();
    private List listaProductoentregado, filtrolistaproductoentregado;
    private ProductoDTO producto;
    ProductoDAO DAO;

    @PostConstruct
    public void init() {
        DAO = new ProductoDAO();
        producto = new ProductoDTO();
        listarproductos = DAO.readAll();
        filtrarproductos = listarproductos;
        listarproductosSinPrecio = DAO.ProductosSinPrecio();
        listarproductosSinPrecioFiltro = listarproductosSinPrecio;
     
        listarproductosentregados();
    }

    public void listarproductosentregados() {

        Object[][] rs = DAO.ListadoProductosEntregados();
        if (rs != null) {
            listaProductoentregado = new ArrayList<>();
            listaProductoentregado.addAll(Arrays.asList(rs));
            filtrolistaproductoentregado = listaProductoentregado;
        }
    }

    public List<ProductoDTO> getListarproductos() {
        return listarproductos;
    }

    public void setListarproductos(List<ProductoDTO> listarproductos) {
        this.listarproductos = listarproductos;
    }

    public ProductoDTO getProducto() {
        return producto;
    }

    public void setProducto(ProductoDTO producto) {
        this.producto = producto;
    }

    public List<ProductoDTO> getFiltrarproductos() {
        return filtrarproductos;
    }

    public void setFiltrarproductos(List<ProductoDTO> filtrarproductos) {
        this.filtrarproductos = filtrarproductos;
    }

    public void findproducto(ActionEvent event, Object key) {
        producto = DAO.read(key);
    }

    public void updateproducto(ActionEvent event) {
        boolean verificar = DAO.update(producto);
        if (verificar != false) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_INFO, "", producto.getDescripcion() + " \nACTUALIZADO CORRECTAMENTE."));
            listarproductos = DAO.readAll();
            filtrarproductos = listarproductos;
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(
                    FacesMessage.SEVERITY_FATAL, "", "PRODUCTO NO SE ACTUALIZO"));
        }
    }

    public List<ProductoDTO> getListarproductosSinPrecio() {
        return listarproductosSinPrecio;
    }

    public void setListarproductosSinPrecio(List<ProductoDTO> listarproductosSinPrecio) {
        this.listarproductosSinPrecio = listarproductosSinPrecio;
    }

    public void onCellEdit(CellEditEvent event, String cum, int precio) {
        if (DAO.asignarPrecioVenta(cum, precio)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
                    "", "PRECIO ESTABLECIDO CORRECTAMENTE");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            listarproductosentregados();
        }

    }

    public List<ProductoDTO> getListarproductosSinPrecioFiltro() {
        return listarproductosSinPrecioFiltro;
    }

    public void setListarproductosSinPrecioFiltro(List<ProductoDTO> listarproductosSinPrecioFiltro) {
        this.listarproductosSinPrecioFiltro = listarproductosSinPrecioFiltro;
    }

    public List<ProductoDTO> getListarproductosEntregado() {
        return listarproductosEntregado;
    }

    public void setListarproductosEntregado(List<ProductoDTO> listarproductosEntregado) {
        this.listarproductosEntregado = listarproductosEntregado;
    }

    public List<ProductoDTO> getListarproductosEntregadoFiltro() {
        return listarproductosEntregadoFiltro;
    }

    public void setListarproductosEntregadoFiltro(List<ProductoDTO> listarproductosEntregadoFiltro) {
        this.listarproductosEntregadoFiltro = listarproductosEntregadoFiltro;
    }

    public List<Object> getListaProductoentregado() {
        return listaProductoentregado;
    }

    public void setListaProductoentregado(List<Object> listaProductoentregado) {
        this.listaProductoentregado = listaProductoentregado;
    }

    public List<Object> getFiltrolistaproductoentregado() {
        return filtrolistaproductoentregado;
    }

    public void setFiltrolistaproductoentregado(List<Object> filtrolistaproductoentregado) {
        this.filtrolistaproductoentregado = filtrolistaproductoentregado;
    }

    

    
}
