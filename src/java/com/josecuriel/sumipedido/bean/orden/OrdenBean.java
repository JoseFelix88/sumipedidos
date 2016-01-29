package com.josecuriel.sumipedido.bean.orden;

import com.josecuriel.sumipedido.model.afiliado.AfiliadoDAO;
import com.josecuriel.sumipedido.model.afiliado.AfiliadoDTO;
import com.josecuriel.sumipedido.model.orden.OrdenDAO;
import com.josecuriel.sumipedido.model.orden.OrdenDTO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIData;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class OrdenBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private OrdenDTO ordenDTO, selectOrden;
    private List<Object> listarPunto, listarprodcuto, listTipodocumento, listregimen, listsexo;
    private List<OrdenDTO> listarordenes, listaparafiltro, listpedidoprint;
    private UIData usersDataTable;

    private SelectItem[] regimenes = new SelectItem[]{
        new SelectItem("1", "CONTRIBUTIVO"),
        new SelectItem("2", "SUBSIDIADO")};

    private SelectItem[] sexo = new SelectItem[]{
        new SelectItem("F", "FEMENINO"),
        new SelectItem("M", "MASCULINO")};

    @PostConstruct
    public void init() {
        ordenDTO = new OrdenDTO();
        ordenDTO.setAfiliado(new AfiliadoDTO());
        llenarcombotipodocumento();
        llenarproductos();
        llenarcombopunto();
        llenarRegimen();
    }

    public OrdenDTO getOrdenDTO() {
        return ordenDTO;
    }

    public void setOrdenDTO(OrdenDTO ordenDTO) {
        this.ordenDTO = ordenDTO;
    }

    public void llenarcombopunto() {
        OrdenDAO ordenDAO = new OrdenDAO();
        Object[] rs = ordenDAO.listarPuntosEntrega();
        if (rs != null) {
            listarPunto = new ArrayList<>();
            listarPunto.addAll(Arrays.asList(rs));
        }
    }

    public void llenarcombotipodocumento() {

        AfiliadoDAO dAO = new AfiliadoDAO();
        Object[][] rs = dAO.listartiposdedocumento();
        if (rs != null) {
            listTipodocumento = new ArrayList<>();
            listTipodocumento.addAll(Arrays.asList(rs));
        }
    }

    public void llenarRegimen() {
        AfiliadoDAO dAO = new AfiliadoDAO();
        Object[][] rs = dAO.listarRegimenes();
        Object[][] rsexo = dAO.listarsexos();

        if (rs != null | rsexo != null) {
            listsexo = new ArrayList<>();
            listsexo.addAll(Arrays.asList(rsexo));

            listregimen = new ArrayList<>();
            listregimen.addAll(Arrays.asList(rs));

        }
    }

    public void llenarSexo() {
        AfiliadoDAO dAO = new AfiliadoDAO();
        Object[][] rs = dAO.listarRegimenes();

        if (rs != null) {

            listregimen = new ArrayList<>();
            listregimen.addAll(Arrays.asList(rs));
        }
    }

    public void llenarproductos() {

        OrdenDAO ordenDAO = new OrdenDAO();

        Object[] rs = ordenDAO.listarProductos();

        if (rs != null) {
            listarprodcuto = new ArrayList<>();
            listarprodcuto.addAll(Arrays.asList(rs));
        }

    }

    public void registrarOrden(ActionEvent actionEvent) {

        OrdenDAO dAO = new OrdenDAO();
        FacesContext context = FacesContext.getCurrentInstance();

        if (ordenDTO.getNumOrden() != null) {
            if (dAO.create(ordenDTO) == true) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "ORDEN REGISTRADA CORRECTAMENTE."));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "LA ORDEN No " + ordenDTO.getNumOrden() + " SE ENCUENTRA REGISTRA."));
            }
        }

    }

    public void verificarOrden(ActionEvent event) {

        OrdenDAO dAO = new OrdenDAO();
        FacesContext context = FacesContext.getCurrentInstance();

        ordenDTO = dAO.read(ordenDTO.getNumOrden()); 
        
        if (ordenDTO != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "LA ORDEN ESTÁ REGISTRADA."));
        } else {
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "ORDEN NO ESTA REGISTRADA."));
        }
    }

    public void registrarafiliado(ActionEvent actionEvent) {

        AfiliadoDAO dAO = new AfiliadoDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        if (ordenDTO.getAfiliado().getNumeroidentidad() != null) {
            if (dAO.create(ordenDTO.getAfiliado()) == true) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "AFILIADO REGISTRADA CORRECTAMENTE."));

            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "EL AFILIADO NO PUDO SER REGISTRADO."));
            }
        }

    }

    public void consultaafiliado(ActionEvent event) {

        try {

            Object condicion = " TIPODOC = '" + ordenDTO.getAfiliado().getTipodocutmento().replace("}", "") + "' "
                    + "and documento = '" + ordenDTO.getAfiliado().getNumeroidentidad() + "'";

            AfiliadoDAO dAO = new AfiliadoDAO();
            ordenDTO.setAfiliado(dAO.read(condicion));
            if (ordenDTO.getAfiliado() != null) {
                ordenDTO.setNombreApellido(ordenDTO.getAfiliado().getPrimer_apellido() + " " + ordenDTO.getAfiliado().getSeg_apellido()
                        + " " + ordenDTO.getAfiliado().getPrimer_nombre() + " " + ordenDTO.getAfiliado().getSeg_nombre());

            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_WARN,
                                "", "EL AFILIADO NO SE ENCUENTRA REGISTRADO.\n"));
            }

        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_FATAL,
                            "Error", "NO PUDO SER POSIBLE CONSULTAR EL AFILIADO.\n" + e));
        }

    }

    public void anularOrden(ActionEvent actionEvent) {

        OrdenDAO dAO = new OrdenDAO();
        FacesContext context = FacesContext.getCurrentInstance();
        if (ordenDTO.getNumOrden() != null) {
            if (dAO.delete(ordenDTO.getNumOrden()) == true) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "LA ORDEN No. " + ordenDTO.getNumOrden() + " HACIDO ANULADA CORRECTAMENTE."));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "LA ORDEN No " + ordenDTO.getNumOrden() + " NO SE ENCUENTRA REGISTRA."));
            }
        }
    }

    public void consultarOrden(ActionEvent actionEvent) {

        if (ordenDTO.getNumOrden() != null) {
            if (ordenDTO.getNumOrden() != null) {
                String numeroCTC = ordenDTO.getNumOrden();
                OrdenDAO dAO = new OrdenDAO();
                FacesContext context = FacesContext.getCurrentInstance();

                ordenDTO = dAO.read(numeroCTC);

                if (ordenDTO != null) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "LA ORDEN No. " + numeroCTC + " HACIDO ENCONTRADA..."));
                } else {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "", "LA ORDEN No. " + numeroCTC + " NO SE ENCUENTRA REGISTRA."));
                }
            }
        }
    }

    public void nuevaOrden(ActionEvent actionEvent) {

        ordenDTO = new OrdenDTO();
        setOrdenDTO(ordenDTO);
        ordenDTO.setAfiliado(new AfiliadoDTO());
        llenarcombopunto();
        llenarproductos();
    }

    public void entregar(ActionEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        try {

            OrdenDAO dAO = new OrdenDAO();
            if (ordenDTO.getNumOrden() != null) {

                boolean entregado = dAO.entregarOrden(dAO.read(ordenDTO.getNumOrden()).getItemorden());
                if (entregado == true) {
                    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info",
                            "ORDEN No. " + ordenDTO.getNumOrden() + " ENTREGA."));
                    nuevaOrden(event);
                }
            }

        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error",
                    "SE PRODUJO UN ERROR AL MOMENTO DE REGISTRAR LA ORDEN No. " + ordenDTO.getNumOrden() + "\n" + e));
        }
    }

    public List<Object> getListarPunto() {
        return listarPunto;
    }

    public void setListarPunto(List<Object> listarPunto) {
        this.listarPunto = listarPunto;
    }

    public List<Object> getListarprodcuto() {
        return listarprodcuto;
    }

    public void setListarprodcuto(List<Object> listarprodcuto) {
        this.listarprodcuto = listarprodcuto;
    }

    public List<OrdenDTO> getListarordenes() {
        return listarordenes;
    }

    public void setListarordenes(List<OrdenDTO> listarordenes) {
        this.listarordenes = listarordenes;
    }

    public List<OrdenDTO> getListaparafiltro() {

        return listaparafiltro;
    }

    public void setListaparafiltro(List<OrdenDTO> listaparafiltro) {
        this.listaparafiltro = listaparafiltro;
    }

    public List<OrdenDTO> getListpedidoprint() {
        OrdenDAO dAO = new OrdenDAO();
        listpedidoprint = dAO.readAllPedido();
        return listpedidoprint;
    }

    public void setListpedidoprint(List<OrdenDTO> listpedidoprint) {
        this.listpedidoprint = listpedidoprint;
    }

    public UIData getUsersDataTable() {
        return usersDataTable;
    }

    public void setUsersDataTable(UIData usersDataTable) {
        this.usersDataTable = usersDataTable;
    }

    public OrdenDTO getSelectOrden() {
        return selectOrden;
    }

    public void setSelectOrden(OrdenDTO selectOrden) {
        this.selectOrden = selectOrden;
    }

    public List<Object> getListTipodocumento() {
        return listTipodocumento;
    }

    public void setListTipodocumento(List<Object> listTipodocumento) {
        this.listTipodocumento = listTipodocumento;
    }

    public SelectItem[] getRegimenes() {
        return regimenes;
    }

    public void setRegimenes(SelectItem[] regimenes) {
        this.regimenes = regimenes;
    }

    public SelectItem[] getSexo() {
        return sexo;
    }

    public void setSexo(SelectItem[] sexo) {
        this.sexo = sexo;
    }

    public List<Object> getListregimen() {
        return listregimen;
    }

    public void setListregimen(List<Object> listregimen) {
        this.listregimen = listregimen;
    }

    public List<Object> getListsexo() {
        return listsexo;
    }

    public void setListsexo(List<Object> listsexo) {
        this.listsexo = listsexo;
    }

}
