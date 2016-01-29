package com.josecuriel.sumipedido.bean.orden;

import com.josecuriel.sumipedido.model.orden.OrdenDAO;
import com.josecuriel.sumipedido.model.orden.OrdenDTO;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class EntregaCTCBean implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    private List<OrdenDTO> listarctcentregado, filtrarentregas;
    private OrdenDTO Selectorden;
    OrdenDAO dAO = new OrdenDAO();

    @PostConstruct
    public void init() {
        listarctcentregado = dAO.readAllCTCEntregado();
        filtrarentregas = listarctcentregado;
    }

    public List<OrdenDTO> getListarctcentregado() {
        return listarctcentregado;
    }

    public void setListarctcentregado(List<OrdenDTO> listarctcentregado) {
        this.listarctcentregado = listarctcentregado;
    }

    public List<OrdenDTO> getFiltrarentregas() {
        return filtrarentregas;
    }

    public void setFiltrarentregas(List<OrdenDTO> filtrarentregas) {
        this.filtrarentregas = filtrarentregas;
    }

    public OrdenDTO getSelectorden() {
        return Selectorden;
    }

    public void setSelectorden(OrdenDTO Selectorden) {
        this.Selectorden = Selectorden;
    }
}
