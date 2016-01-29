package com.josecuriel.sumipedido.model.orden;

import com.josecuriel.sumipedido.util.db.Utilidades;
import com.josecuriel.sumipedido.util.db.crud;
import com.josecuriel.sumipedido.util.db.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrdenDAO extends database implements crud<OrdenDTO> {

    Utilidades util = new Utilidades();

    public OrdenDAO() {
    }

    public Object[][] listarPuntosEntrega() {

        Object[][] rs = select("puntos", "nombre", "tipo = 1");

        if (rs.length > 0) {

            return rs;
        }
        return null;

    }

    public Object[][] listarProductos() {

        Object[][] rs = select("productosbase", "descripcion", null);

        if (rs.length > 0) {

            return rs;
        }
        return null;

    }

    @Override
    public boolean create(OrdenDTO c) {

            if (read(c.getNumOrden()) == null) {

                String valoresEncabezado = "('" + c.getNumOrden() + "','" + util.formatearFecha(c.getFechaOrden()) + "',"
                        + "'" + util.formatearFecha(c.getFechaFormula()) + "',curdate(),'" + c.getPuntoEntrega().replace("}", "") + "',"
                        + "'" + c.getAfiliado().getTipodocutmento().replace("}", "")+ "','" + c.getAfiliado().getNumeroidentidad() + "',"
                        + "'" + c.getNombreApellido().toUpperCase() + "','" + c.getAfiliado().getTelefono() + "',0,"
                        + " '" + c.getProducto().toUpperCase().replace("}", "") + "'," + c.getCantidad() + ",'" + c.getCumProducto() + "')";

                return insert("ordenes", "numorden, fechaorden, fechaformula, fechaingreso, "
                        + " puntoentrega,tipodoc, idafiliado, nombreapellido, telefono, estado, producto, cantidad, cumproducto ", valoresEncabezado);
            }
     
            return false;

    }

    @Override
    public boolean delete(Object key) {
        if (read(key) != null) {

            return Delete("ordenes", "numorden = '" + key + "'");

        } else {

            return false;
        }
    }

    @Override
    public boolean update(OrdenDTO c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean entregarOrden(Object numeroOrden) {

        return update("ordenes", "estado = 1, fechaentrega = curdate()", "itemorden = " + numeroOrden + "");
    }

    @Override
    public OrdenDTO read(Object key) {

        PreparedStatement ps = null;
        OrdenDTO dTO = null;
        ResultSet rs;

        try {

            ps = getConnection().prepareStatement("SELECT itemorden, numorden, fechaorden,fechaformula,"
                    + "puntoentrega,idafiliado,nombreapellido,telefono, cumproducto, PRODUCTO, CANTIDAD, if(estado = 0, 'PENDIENTE','ENTREGADO') AS ESTADO "
                    + "FROM ORDENES WHERE itemorden = ? or numorden = ?");

            
            ps.setString(1, key.toString());
            ps.setString(2, key.toString());
            
            rs = ps.executeQuery();

            if (rs.next()) {
                
                dTO = new OrdenDTO(rs.getInt("itemorden"),rs.getString("numorden"), rs.getDate("fechaorden"),
                        rs.getDate("fechaformula"), rs.getString("puntoentrega"),
                        rs.getString("idafiliado"), rs.getString("nombreapellido"),
                        rs.getString("telefono"), rs.getString("producto"), rs.getInt("cantidad"),
                        rs.getString("estado"), null, null, rs.getString("cumproducto"));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda de la Orden: " + e);
        }

        return dTO;
    }

    public List<OrdenDTO> readAll(OrdenDTO dTO) {

        PreparedStatement ps;
        List<OrdenDTO> listproductos = null;
        ResultSet rs;

        try {

            ps = getConnection().prepareStatement("SELECT PRODUCTO, CANTIDAD FROM ORDENES WHERE numorden = ?");
            ps.setString(1, dTO.getNumOrden());

            rs = ps.executeQuery();
            listproductos = new ArrayList<>();
            while (rs.next()) {

                listproductos.add(new OrdenDTO(null, null, null, null, null, null, null,
                        rs.getString("producto"), rs.getInt("cantidad")));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto");
        }

        return listproductos;
    }

    
    public List<OrdenDTO> readAllCTCPendientes() {
        PreparedStatement ps;
        ArrayList<OrdenDTO> listproductos  = new ArrayList<>();
        ResultSet rs;

        try {

            ps = getConnection().prepareStatement("SELECT itemorden, numorden, fechaformula, puntoentrega,"
                    + " concat(nombreapellido,' - Tel: ',telefono) as afiliado, PRODUCTO, CANTIDAD,"
                    + "if(estado = 0, 'PENDIENTE','ENTREGADO') AS ESTADO FROM ORDENES where estado = 0  "
                    + "ORDER BY PRODUCTO, puntoentrega,fechaformula");

            rs = ps.executeQuery();
            

//            OrdenDTO dto = new OrdenDTO();
            while (rs.next()) {

              /*  dto.setNumOrden(rs.getString("numorden"));
                dto.setProducto(rs.getString("producto"));
                dto.setCantidad(rs.getInt("cantidad"));
                dto.setItemorden(rs.getInt("itemorden"));
                listproductos.add(dto);*/
                listproductos.add(new OrdenDTO(rs.getInt("itemorden"), rs.getString("numorden"), null, rs.getDate("fechaformula"), rs.getString("puntoentrega"), null, rs.getString("afiliado"), null,
                        rs.getString("producto"), rs.getInt("cantidad"), rs.getString("estado"), null, null, ""));
                
            }
           
        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto: " + e);
        }

        return listproductos;
    }

    public List<OrdenDTO> readAllPedido() {
        PreparedStatement ps;
        List<OrdenDTO> listproductos = null;
        ResultSet rs;

        try {

            ps = getConnection().prepareStatement("SELECT   PRODUCTO, sum(CANTIDAD) as cantidad  "
                    + "FROM ORDENES where estado = 0 group by PRODUCTO ORDER BY fechaformula, producto");

            rs = ps.executeQuery();
            listproductos = new ArrayList<>();
            while (rs.next()) {

                listproductos.add(new OrdenDTO("", null, null, "", "", "", "",
                        rs.getString("producto"), rs.getInt("cantidad"), ""));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto: " + e);
        }

        return listproductos;
    }

    public List<OrdenDTO> readAllCTCEntregado() {
        PreparedStatement ps;
        List<OrdenDTO> listproductos = null;
        ResultSet rs;

        try {

            ps = getConnection().prepareStatement("SELECT Itemorden, numorden, fechaorden, fechaformula, puntoentrega,"
                    + " concat(nombreapellido,' - Tel: ',telefono) as afiliado, PRODUCTO, CANTIDAD,"
                    + "if(estado = 0, 'PENDIENTE','ENTREGADO') AS ESTADO,fechaentrega,fechaingreso, cumproducto"
                    + " FROM ORDENES where estado = 1  ORDER BY Itemorden, numorden");

            rs = ps.executeQuery();
            listproductos = new ArrayList<>();
            while (rs.next()) {

                listproductos.add(new OrdenDTO(rs.getInt("Itemorden"), rs.getString("numorden"), rs.getDate("fechaorden"), 
                        rs.getDate("fechaformula"), rs.getString("puntoentrega"),
                        null, rs.getString("afiliado"), null, rs.getString("producto"), rs.getInt("cantidad"), rs.getString("estado"),
                        rs.getDate("fechaentrega"), rs.getDate("fechaingreso"), rs.getString("cumproducto")));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto: " + e);
        }

        return listproductos;
    }

    @Override
    public List<OrdenDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
