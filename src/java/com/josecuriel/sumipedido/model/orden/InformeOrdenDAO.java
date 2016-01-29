package com.josecuriel.sumipedido.model.orden;

import com.josecuriel.sumipedido.util.db.database;
import java.io.Serializable;

public class InformeOrdenDAO extends database implements Serializable {

    private static final String RIPS_AM_PUNTO_DE_ENTREGA = "";
    private static final String RIPS_AF_PUNTO_DE_ENTREGA = "SELECT\n"
            + "empresas.Nit,\n"
            + "empresas.Nombre,\n"
            + "'NI',\n"
            + "empresas.Nit,\n"
            + "puntos.PREFIJO,\n"
            + "ordenes.fechaingreso,\n"
            + "'2015-12-01' AS FECHAINICIO,\n"
            + "'2015-12-31' AS FECHAFINAL,\n"
            + "clientes.codigo_prest AS CODCLIENTE,\n"
            + "clientes.nombre AS CLIENTE,\n"
            + "'S-CE-001-15A' AS NUMCONTRATO,\n"
            + "'SUBSIDIADO' AS REGIMEN,\n"
            + "'' AS VACIO1,\n"
            + "0 AS COPAGO,\n"
            + "'' AS VACIO2,\n"
            + "0 AS descuento,\n"
            + "Sum(ordenes.cantidad) AS TOTALFACTURA\n"
            + "FROM\n"
            + "clientes ,\n"
            + "empresas ,\n"
            + "ordenes ,\n"
            + "puntos\n"
            + "WHERE\n"
            + "clientes.estado = 1 AND\n"
            + "empresas.estado = 1 AND\n"
            + "ordenes.estado = 1 AND\n"
            + "puntos.Tipo = 1 AND\n"
            + "ordenes.puntoentrega = puntos.NOMBRE AND \n"
            + "puntos.NOMBRE = ? \n"
            + "GROUP BY\n"
            + "ordenes.numorden";
    private static final String RIPS_US_PUNTO_DE_ENTREGA = "";
    private static final String RIPS_CT_PUNTO_DE_ENTREGA = "";
    private static final String RIPS_CT_GENERAL = "";
    private static final String RIPS_AM_GENERAL = "";
    private static final String RIPS_AF_GENERAL = "";
    private static final String RIPS_US_GENERAL = "";

}
