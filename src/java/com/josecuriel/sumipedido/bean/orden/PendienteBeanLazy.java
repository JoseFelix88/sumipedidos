package com.josecuriel.sumipedido.bean.orden;

import com.josecuriel.sumipedido.model.orden.LazyOrdenDataModel;
import com.josecuriel.sumipedido.model.orden.OrdenDAO;
import com.josecuriel.sumipedido.model.orden.OrdenDTO;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@ManagedBean
@ViewScoped
public class PendienteBeanLazy implements Serializable{

    private OrdenDTO selectOrden;
    private LazyDataModel<OrdenDTO> lazyDataModel;
    private OrdenDAO dAO = new OrdenDAO();

    @PostConstruct
    public void init(){
        System.out.println("lazy LlenarTabla antes: "+dAO.readAllCTCPendientes().size());
        lazyDataModel = new LazyOrdenDataModel(dAO.readAllCTCPendientes());
        System.out.println("lazy LlenarTabla despues: "+dAO.readAllCTCPendientes().size());
    }
    public LazyDataModel<OrdenDTO> getLazyDataModel() {
        System.out.println("lazy get: "+lazyDataModel.getRowCount());
        return lazyDataModel;
    }


    public OrdenDTO getSelectOrden() {
        return selectOrden;
    }

    public void setSelectOrden(OrdenDTO selectOrden) {
        this.selectOrden = selectOrden;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Orden Selected", ((OrdenDTO) event.getObject()).getNumOrden());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void setdAO(OrdenDAO dAO) {
        this.dAO = dAO;
    }
}
