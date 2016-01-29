package com.josecuriel.sumipedido.bean.informes;

import com.josecuriel.sumipedido.model.informes.InformeDAO;
import com.josecuriel.sumipedido.model.orden.OrdenDAO;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.primefaces.model.StreamedContent;

@ManagedBean
@RequestScoped
public class InformesBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private String puntoentrega;
    private String consecutivo;
    private int cantidadFacturas;
    private int totalvalorFacturasCTC;
    private int cantidadtotalFacturasCTC;
    private List cuentasRegistradas;
    InformeDAO DAO;
    private ArrayList<Object> listarPunto;
    private List filtroctaregistrado;
    JasperPrint jasperPrint;

    private StreamedContent file;

    @PostConstruct
    public void init() {
        DAO = new InformeDAO();
        llenarcombopunto();
        listarCuentasCTCentregados();
    }

    public void listarCuentasCTCentregados() {

        Object[][] rs = DAO.facturasEntregadasPorPunto();

        if (rs != null) {
            cuentasRegistradas = new ArrayList<>();
            cuentasRegistradas.addAll(Arrays.asList(rs));
            filtroctaregistrado = cuentasRegistradas;
        }

        rs = DAO.TotalesfacturasEntregadasCTC();

        if (rs != null) {

            totalvalorFacturasCTC = Integer.parseInt(String.valueOf(rs[0][0]));
            cantidadtotalFacturasCTC = Integer.parseInt(String.valueOf(rs[0][1]));
        }
    }

    public void llenarcombopunto() {
        OrdenDAO ordenDAO = new OrdenDAO();
        Object[] rs = ordenDAO.listarPuntosEntrega();
        if (rs != null) {
            listarPunto = new ArrayList<>();
            listarPunto.addAll(Arrays.asList(rs));
        }
    }

    public void ordenesEntregadas() {
        Object[][] rs = DAO.facturasEntregadasPorPunto(puntoentrega.replace("}", ""));
        cantidadFacturas = Integer.parseInt(rs[0][0].toString());
    }

    public void generarCtasCTC() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (DAO.generarCuentasCTC(cantidadFacturas, puntoentrega.replace("}", ""), consecutivo) != false) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "", "CUENTA REGISTRADA CORRECTAMENTE."));
            listarCuentasCTCentregados();
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "", "LA CUENTA PUDO SER REGISTRA."));
        }
    }

    public void imprimirFacturaCTC(ActionEvent event) throws JRException, IOException {

        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("logo", FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/reportes/logo.png"));
        FacesContext.getCurrentInstance().responseComplete();
        parametros.put("firma", FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/reportes/firma factura0001 - copia.png"));
        FacesContext.getCurrentInstance().responseComplete();
        parametros.put("punto", puntoentrega.replace("}", ""));
        System.out.println("parametros: " + parametros);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/factura ctc.jasper"));
        JasperPrint Printer = JasperFillManager.fillReport(jasper.getPath(), parametros, DAO.getConnection());
        FacesContext.getCurrentInstance().responseComplete();
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=facturactc_" + puntoentrega.replace("}", "") + ".pdf");
        try (ServletOutputStream stream = response.getOutputStream()) {
            JasperExportManager.exportReportToPdfStream(Printer, stream);
            
            stream.flush();
            stream.close();
        }

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void imprimirCtaCobroCTC(ActionEvent event) throws JRException, IOException {

        Date fechaentrega = Calendar.getInstance().getTime();
        Map parametros = new HashMap();
        parametros.clear();
        parametros.put("punto", puntoentrega.replace("}", ""));
        parametros.put("fechaEntrega", fechaentrega);
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Cuenta de Cobro.jasper"));
        JasperPrint printer = JasperFillManager.fillReport(jasper.getPath(), parametros, DAO.getConnection());

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.addHeader("Content-disposition", "attachment; filename=Cuenta_de_Cobro_" + puntoentrega.replace(" ", "_") + ".pdf");
        ServletOutputStream stream = response.getOutputStream();
        JasperExportManager.exportReportToPdfStream(printer, stream);
        stream.flush();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public void PDF(ActionEvent actionEvent) throws JRException, IOException {
        String reportPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Relacion de Cuentas de Cobro.jasper");
        jasperPrint = JasperFillManager.fillReport(reportPath, new HashMap(), DAO.getConnection());

        HttpServletResponse httpServletResponse = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        httpServletResponse.addHeader("Content-disposition", "attachment; filename=report.pdf");

        ServletOutputStream servletOutputStream = httpServletResponse.getOutputStream();

//        file = 
        JasperExportManager.exportReportToPdfStream(jasperPrint, servletOutputStream);

        servletOutputStream.flush();
        servletOutputStream.close();

        FacesContext.getCurrentInstance().responseComplete();

    }

    public void verPDF(ActionEvent actionEvent) throws Exception {
        File jasper = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath("/reportes/Relacion de Cuentas de Cobro.jasper"));

        byte[] bytes = JasperRunManager.runReportToPdf(jasper.getPath(), null, DAO.getConnection());
        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        response.setContentType("application/pdf");
        response.setContentLength(bytes.length);
        ServletOutputStream outStream = response.getOutputStream();
        outStream.write(bytes, 0, bytes.length);
        outStream.flush();
        outStream.close();

        FacesContext.getCurrentInstance().responseComplete();
    }

    public String getPuntoentrega() {
        return puntoentrega;
    }

    public void setPuntoentrega(String puntoentrega) {
        this.puntoentrega = puntoentrega;
    }

    public String getConsecutivo() {
        return consecutivo;
    }

    public void setConsecutivo(String consecutivo) {
        this.consecutivo = consecutivo;
    }

    public int getCantidadFacturas() {
        return cantidadFacturas;
    }

    public void setCantidadFacturas(int cantidadFacturas) {
        this.cantidadFacturas = cantidadFacturas;
    }

    public ArrayList<Object> getListarPunto() {
        return listarPunto;
    }

    public void setListarPunto(ArrayList<Object> listarPunto) {
        this.listarPunto = listarPunto;
    }

    public List getCuentasRegistradas() {
        return cuentasRegistradas;
    }

    public void setCuentasRegistradas(List cuentasRegistradas) {
        this.cuentasRegistradas = cuentasRegistradas;
    }

    public List getFiltroctaregistrado() {
        return filtroctaregistrado;
    }

    public void setFiltroctaregistrado(List filtroctaregistrado) {
        this.filtroctaregistrado = filtroctaregistrado;
    }

    public int getTotalvalorFacturasCTC() {
        return totalvalorFacturasCTC;
    }

    public void setTotalvalorFacturasCTC(int totalvalorFacturasCTC) {
        this.totalvalorFacturasCTC = totalvalorFacturasCTC;
    }

    public int getCantidadtotalFacturasCTC() {
        return cantidadtotalFacturasCTC;
    }

    public void setCantidadtotalFacturasCTC(int cantidadtotalFacturasCTC) {
        this.cantidadtotalFacturasCTC = cantidadtotalFacturasCTC;
    }

    public StreamedContent getFile() {
        return file;
    }

}
