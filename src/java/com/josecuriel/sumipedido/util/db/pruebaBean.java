package com.josecuriel.sumipedido.util.db;

import com.josecuriel.sumipedido.model.afiliado.AfiliadoDAO;
import com.josecuriel.sumipedido.model.login.LoginDAO;
import com.josecuriel.sumipedido.model.login.LoginDTO;
import com.josecuriel.sumipedido.model.orden.OrdenDAO;
import com.josecuriel.sumipedido.model.producto.ProductoDAO;
import com.josecuriel.sumipedido.model.producto.ProductoDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class pruebaBean {

    public static void main(String[] args) {

        ProductoDAO dAO = new ProductoDAO();

        Object[][] rs = dAO.ListadoProductosEntregados();
        int acumular = 0;

        for (Object[] r : rs) {
            String vtotal = String.valueOf(r[5]);
            int vtotalnum = Integer.parseInt(vtotal);
            acumular = acumular + vtotalnum;
        }
        System.out.println(" cantidad: " + acumular);

        /* List<ProductoDTO> rs = dAO.readAll();
        List<Object> list = new ArrayList<>();
        
        for (ProductoDTO r : rs) {
            list.add(r);
            System.out.println("costo: "+CambiaFormatoTexto.numerico((double)r.getCosto()));
        }
        
      /*  Utilidades u = new Utilidades();
      Date testdate = new GregorianCalendar().getTime();
        System.out.println(""+u.formatearFecha(testdate));
        AfiliadoDAO dAO = new AfiliadoDAO();
        for (Object[] listarRegimene : dAO.listarRegimenes()) {
            System.out.println("regimen: "+listarRegimene[1]);
        }
        /*OrdenDAO ordenDAO = new  OrdenDAO();
        
        Object[] rs = ordenDAO.listarProductos();
        List<Object> list = new ArrayList<>();
        
        for (Object r : rs) {
            list.add(r);
            System.out.println("punto entrega: "+list.get(0));
        }
        
        LoginDAO dAO = new LoginDAO();
        Object[] credencial = {"1102819530","1102"};
        LoginDTO dTO = dAO.Logear(credencial);
                
        if ( dTO != null) {
            System.out.println("login: "+dTO.getNombre());
        }else{
            
            System.out.println("ID. USUARIO O CLAVE SON INCORRECTOS");
        }

        /*   ProductoDAO dAO;

         dAO = new ProductoDAO();
        
         List<ProductoDTO> listcanal = dAO.CanalPos();
         for (ProductoDTO listcanal1 : listcanal) {
         System.out.println("traigo> "+listcanal1.getCanal());
         }
         LaboratorioDAO dAO;
  
        
         dAO = new LaboratorioDAO();
         List<LaboratorioDTO> list = dAO.readAll();
         for (LaboratorioDTO lisdto1 : list) {
         System.out.println("Laboratorio> "+lisdto1.getNombre());
         }
        
         ProductoDAO dAO = new ProductoDAO();
         ProductoDTO dTO = dAO.read("7706569020567");
         List<ProductoDTO> lista = dAO.CanalPos();
         for (ProductoDTO lista1 : lista) {
         System.out.println("canal : "+lista1.getCanal());
         System.out.println("pos: "+lista1.getPos());
         }*/
 /*  System.out.println("Producto: "+lista.getDescripcion()+"\nPresentacion: "+dTO.getPresentacion()+"\n"
         + "laboiratorio: "+dTO.getLaboratorio());
         /*  PacienteDAO pacientedao = new PacienteDAO();
         //        producto.delete("1144");
         PacienteDTO pdto = pacientedao.read("948850");
         System.out.println("Codigo: "+pdto.getDocumento()+" - "
         + "apellidos: "+pdto.getPrimerApellido()+" - nombre: "+pdto.getPrimerNombre()+""
         + " - fecha Nace: "+pdto.getFechaNacimiento()+" - nivel: "+pdto.getNivel()+" "
         + "valor cuota moderadora = "+pdto.getCuotamoderadora());
    
         List<CiudadDTO> lsciudades = new CiudadDAO().readAll();
        
         for (CiudadDTO lspacie : lsciudades) {

         System.out.println("ciudades: "+lspacie.getCodigoPunto()+" ");
            
         }*/
    }

}
