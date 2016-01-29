package com.josecuriel.sumipedido.model.producto;

import com.josecuriel.sumipedido.model.orden.OrdenDTO;
import com.josecuriel.sumipedido.util.db.CambiaFormatoTexto;
import com.josecuriel.sumipedido.util.db.crud;
import com.josecuriel.sumipedido.util.db.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO extends database implements crud<ProductoDTO> {

    @Override
    public boolean create(ProductoDTO c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(ProductoDTO c) {

        String field = "PRECIO = " + c.getPrecioventa() + ", costo = " + c.getCosto() + " ";
        return update("PRODUCTOSBASE", field, "itemproducto = " + c.getItemproducto() + "");

    }

    @Override
    public ProductoDTO read(Object key) {

        Object[][] rs = select("productosbase", "itemproducto,cum, descripcion, precio, costo", "itemproducto = '" + key + "'");
        ProductoDTO dTO = null;

        if (rs.length > 0) {

            dTO = new ProductoDTO();
            dTO.setItemproducto(Integer.parseInt(rs[0][0].toString()));
            dTO.setCumproducto(rs[0][1].toString());
            dTO.setDescripcion(rs[0][2].toString());
            dTO.setPrecioventa(Integer.parseInt(rs[0][3].toString()));
            dTO.setCosto(Integer.parseInt(rs[0][4].toString()));

        }

        return dTO;
    }

    @Override
    public List<ProductoDTO> readAll() {

        PreparedStatement ps;
        List<ProductoDTO> listproductos = null;
        ResultSet rs;

        try {

            ps = getConnection().prepareStatement("SELECT itemproducto,cum, descripcion, precio, costo "
                    + "FROM productosbase ");

            rs = ps.executeQuery();
            listproductos = new ArrayList<>();
            while (rs.next()) {
                listproductos.add(new ProductoDTO(rs.getInt("itemproducto"), rs.getString("cum"),
                        rs.getString("descripcion"), rs.getInt("precio"), rs.getInt("costo")));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto: " + e);
        }

        return listproductos;
    }

    public List<ProductoDTO> ProductosSinPrecio() {

        PreparedStatement ps;
        List<ProductoDTO> listproductos = null;
        ResultSet rs;
        String sql = "SELECT productosbase.itemproducto,\n"
                + "productosbase.cum,\n"
                + "productosbase.descripcion, precio, costo\n"
                + "FROM\n"
                + "productosbase ,\n"
                + "ordenes\n"
                + "WHERE\n"
                + "productosbase.cum = ordenes.cumproducto AND\n"
                + "ordenes.estado = 1 AND\n"
                + "productosbase.precio = 0\n"
                + "GROUP BY\n"
                + "productosbase.cum ORDER BY productosbase.descripcion";

        try {

            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();
            listproductos = new ArrayList<>();
            while (rs.next()) {
                listproductos.add(new ProductoDTO(rs.getInt("itemproducto"), rs.getString("cum"),
                        rs.getString("descripcion"), rs.getInt("precio"), rs.getInt("costo")));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto: " + e);
        }

        return listproductos;
    }

    public List<ProductoDTO> ProductosEntregados() {

        PreparedStatement ps;
        List<ProductoDTO> listproductos = null;
        ResultSet rs;
        String sql = "SELECT productosbase.itemproducto,\n"
                + "productosbase.cum,\n"
                + "productosbase.descripcion, precio, costo\n"
                + "FROM\n"
                + "productosbase ,\n"
                + "ordenes\n"
                + "WHERE\n"
                + "productosbase.cum = ordenes.cumproducto AND\n"
                + "ordenes.estado = 1 "
                + "GROUP BY\n"
                + "productosbase.cum ORDER BY productosbase.descripcion";

        try {

            ps = getConnection().prepareStatement(sql);

            rs = ps.executeQuery();
            listproductos = new ArrayList<>();
            while (rs.next()) {
                listproductos.add(new ProductoDTO(rs.getInt("itemproducto"), rs.getString("cum"),
                        rs.getString("descripcion"), rs.getInt("precio"), rs.getInt("costo")));
            }

        } catch (Exception e) {
            System.out.println("Error en la busqueda del producto: " + e);
        }

        return listproductos;
    }

    public boolean asignarPrecioVenta(String cum, int precio) {

        String field = "PRECIO = " + precio + "";
        return update("PRODUCTOSBASE", field, "cum = '" + cum + "'");

    }

    public Object[][] ListadoProductosEntregados() {

        Object[][] rs = select("productosbase ,ordenes",
                "productosbase.itemproducto,\n"
                + "productosbase.cum,\n"
                + "productosbase.descripcion, precio, costo,"
                + "sum(ordenes.cantidad),sum(ordenes.cantidad)*precio,sum(ordenes.cantidad)*costo\n",
                "productosbase.cum = ordenes.cumproducto AND\n"
                + "ordenes.estado = 1 "
                + "GROUP BY\n"
                + "productosbase.cum"
                + " ORDER BY productosbase.descripcion");

        if (rs.length > 0) {

            return rs;
        }

        return null;
    }
}
