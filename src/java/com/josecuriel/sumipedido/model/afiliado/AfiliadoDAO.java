package com.josecuriel.sumipedido.model.afiliado;

import com.josecuriel.sumipedido.util.db.Utilidades;
import com.josecuriel.sumipedido.util.db.crud;
import com.josecuriel.sumipedido.util.db.database;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class AfiliadoDAO extends database implements crud<AfiliadoDTO> {

    @Override
    public boolean create(AfiliadoDTO c) {

        String values = "('" + c.getTipodocutmento().replace("}", "") + "', '" + c.getNumeroidentidad() + "', "
                + "'" + c.getPrimer_apellido().toUpperCase() + "','" + c.getSeg_apellido().toUpperCase() + "','" + c.getPrimer_nombre().toUpperCase() + "',"
                + "'" + c.getSeg_nombre().toUpperCase() + "','" + new Utilidades().formatearFecha(c.getFecha_nacimiento()) + "',"
                + "'" + c.getSexo().replace("}", "") + "','" + c.getRegimen().replace("}", "") + "','" + c.getTelefono() + "')";
        String campos = " TIPODOC,DOCUMENTO,ape1,ape2, nom1, nom2,FENAC,SEXO, regimen,telefono";
        return insert("afiliados", campos, values);
    }

    @Override
    public boolean delete(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(AfiliadoDTO c) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public AfiliadoDTO read(Object key) {

        AfiliadoDTO dTO = null;
        try {
            String SQL = "SELECT TIPODOC,DOCUMENTO,ape1,ape2, nom1, nom2,FENAC,"
                    + "SEXO, EDAD, DPTO, MPIO,ZONA, regimen,nivel,TIPOAFILIACION "
                    + "FROM pacientes_activos WHERE " + key + "";
            PreparedStatement ps = getConnection().prepareStatement(SQL);
//            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                dTO = new AfiliadoDTO(rs.getString("TIPODOC"), rs.getString("DOCUMENTO"),
                        rs.getString("ape1"), rs.getString("ape2"), rs.getString("nom1"), rs.getString("nom2"),
                        rs.getDate("FENAC"), rs.getInt("edad"), rs.getString("dpto"), rs.getString("mpio"),
                        rs.getString("zona"), rs.getString("regimen"), rs.getString("TIPOAFILIACION"),
                        rs.getInt("nivel"), rs.getString("sexo"),null);
            } else {
                SQL = "SELECT TIPODOC,DOCUMENTO,ape1,ape2, nom1, nom2,FENAC,"
                        + "SEXO, EDAD, DPTO, MPIO,ZONA, regimen,nivel,TIPOAFILIACION, "
                        + "telefono FROM afiliados WHERE " + key + "";
                ps = getConnection().prepareStatement(SQL);
//            System.out.println(ps);
                rs = ps.executeQuery();

                if (rs.next()) {

                    dTO = new AfiliadoDTO(rs.getString("TIPODOC"), rs.getString("DOCUMENTO"),
                            rs.getString("ape1"), rs.getString("ape2"), rs.getString("nom1"), rs.getString("nom2"),
                            rs.getDate("FENAC"), rs.getInt("edad"), rs.getString("dpto"), rs.getString("mpio"),
                            rs.getString("zona"), rs.getString("regimen"), rs.getString("TIPOAFILIACION"),
                            rs.getInt("nivel"), rs.getString("sexo"), rs.getString("telefono"));

                }
            }
        } catch (Exception e) {
            System.out.println("error en la consulta del afiliado: " + e);
        }

        return dTO;
    }

    public Object[][] listartiposdedocumento() {

        return select("tipos_documentos", "descripcion, prefijo", null);
    }

    public Object[][] listarRegimenes() {

        return select("regimenes", "idregimen,descripcion", null);
    }

    public Object[][] listarsexos() {

        return select("sexos", "descripcion, prefijo", null);
    }

    @Override
    public List<AfiliadoDTO> readAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
