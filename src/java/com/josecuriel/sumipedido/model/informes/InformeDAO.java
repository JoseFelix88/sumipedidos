package com.josecuriel.sumipedido.model.informes;

import com.josecuriel.sumipedido.util.db.database;
import java.io.Serializable;
import java.sql.CallableStatement;

public class InformeDAO extends database implements Serializable {

    public boolean generarCuentasCTC(int cantfacturas, String puntoentrega, String consecutivo) {

        CallableStatement cs;
        String sql = "call asignarnumerodefactura(?,?,?)";
        try {

            cs = getConnection().prepareCall("call insertarordenesparafactura(?)");
            cs.setString(1, puntoentrega);
            cs.execute();
            cs = getConnection().prepareCall(sql);
            cs.setInt(1, cantfacturas);
            cs.setString(2, puntoentrega);
            cs.setString(3, consecutivo);
            cs.execute();

            return true;
        } catch (Exception e) {
            System.out.println("error al momento de generar la cuenta punto: " + puntoentrega + "\nERROR: " + e);
        }
        return false;
    }

    public Object[][] facturasEntregadasPorPunto(String puntoentrega) {

        Object[][] rs = select("ordenes", "Count(DISTINCT ordenes.numorden)",
                "puntoentrega = '" + puntoentrega + "' and estado = 1");

        if (rs.length > 0) {
            return rs;
        }

        return null;
    }

    public Object[][] facturasEntregadasPorPunto() {

        Object[][] rs = select("cuentasctc", "ifnull(numcuenta,''),ifnull(puntoentrega,''),ifnull(facturas,0),"
                + "ifnull(productos,0),ifnull(inicia,0),ifnull(finaliza,0),ifnull(totalcta,0)", null);

        if (rs.length > 0) {
            return rs;
        }

        return null;
    }

    public Object[][] TotalesfacturasEntregadasCTC() {

        /*Object[][] rs = select("ordenes, productosbase", 
         "SUM(ordenes.cantidad*productosbase.precio), COUNT(DISTINCT ordenes.numorden)",
         "ordenes.cumproducto = productosbase.cum AND ordenes.estado = 1");
         */
        Object[][] rs = select("cuentasctc", "ifnull(Sum(cuentasctc.totalcta),0),\n"
                + "ifnull(Sum(cuentasctc.facturas),0)", null);
        if (rs.length > 0) {
            return rs;
        }

        return null;
    }
}
