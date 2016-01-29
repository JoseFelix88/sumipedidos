package com.josecuriel.sumipedido.bean.orden;

import com.josecuriel.sumipedido.model.orden.OrdenDAO;
import com.josecuriel.sumipedido.model.orden.OrdenDTO;
import com.josecuriel.sumipedido.util.db.Utilidades;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class PendienteCTCBean implements Serializable{

    private OrdenDTO dTO = new OrdenDTO();
    private List<OrdenDTO> listarpendientes;
    private List<OrdenDTO> filtrarpendientes;
    private Utilidades utilidad = new Utilidades();

    @PostConstruct
    public void init(){
        cargarpendientes();
    }
    public OrdenDTO getdTO() {
        return dTO;
    }

    public void setdTO(OrdenDTO dTO) {
        this.dTO = dTO;
    }

    public List<OrdenDTO> getListarpendientes() {
       
        return listarpendientes;
    }

    public void setListarpendientes(List<OrdenDTO> listarpendientes) {
        this.listarpendientes = listarpendientes;
    }

    public List<OrdenDTO> getFiltrarpendientes() {
     
        return filtrarpendientes;
    }

    public void setFiltrarpendientes(List<OrdenDTO> filtrarpendientes) {
        this.filtrarpendientes = filtrarpendientes;
    }

    public void cargarpendientes() {
        try {
            OrdenDAO dAO = new OrdenDAO();
            listarpendientes = dAO.readAllCTCPendientes();
            filtrarpendientes = dAO.readAllCTCPendientes();
            
        } catch (Exception e) {
            System.out.println("error al cargar ctc pendientes bean: " + e);
        }

    }

    public void consultarctcpendiente(Object idctcpendiente) {
        try {
            OrdenDAO dAO = new OrdenDAO();
            OrdenDTO ordenDTO = dAO.read(idctcpendiente);
            if (ordenDTO != null) {
                dTO = ordenDTO;
            }
        } catch (Exception e) {
            System.out.println("Error al consultar ctc pendiente: "+e);
        }
    }

    public void entregarpendiente(Object idctcpendiente){
        
        try {
            OrdenDAO dAO = new OrdenDAO();
            boolean entrega = dAO.entregarOrden(idctcpendiente);
            if (entrega != false) {
                FacesContext context = FacesContext.getCurrentInstance();
                 context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "ORDEN No. "+dTO.getNumOrden()+" ENTREGA."));
                 cargarpendientes();
            }
        } catch (Exception e) {
            System.out.println("Error en la Entrega de la orden: "+e);
        }
    }

    public Utilidades getUtilidad() {
        return utilidad;
    }
}
