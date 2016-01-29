package com.josecuriel.sumipedido.model.login;

import com.josecuriel.sumipedido.util.db.database;

public class LoginDAO extends database {

    public LoginDTO Logear(Object[] credencial) {
        LoginDTO dTO = null;
        try {

            Object[][] rs = select("usuarios", "id, nombre, clave", "id = '" + credencial[0] + "' and "
                    + "clave = md5('" + credencial[1] + "')");

            dTO = new LoginDTO( rs[0][0].toString() , rs[0][1].toString(), rs[0][2].toString());

        } catch (Exception e) {
            System.out.println("Error en la consulta: "+e);
            
        }

        return dTO;
    }

}
