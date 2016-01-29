package com.josecuriel.sumipedido.bean.login;

import com.josecuriel.sumipedido.model.login.LoginDAO;
import com.josecuriel.sumipedido.model.login.LoginDTO;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;
    private LoginDTO loginDTO;

    public LoginBean() {
        loginDTO = new LoginDTO();
    }

    public LoginDTO getLoginDTO() {
        return loginDTO;
    }

    public void setLoginDTO(LoginDTO loginDTO) {
        this.loginDTO = loginDTO;
    }

    public String LogearUser(ActionEvent event) {

        LoginDAO dAO = new LoginDAO();
        Object[] credencial = {loginDTO.getIdusuario(), loginDTO.getClave()};
        LoginDTO dTO = dAO.Logear(credencial);
        FacesContext context = FacesContext.getCurrentInstance();

        if (dTO != null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "Bienvenido " + dTO.getNombre()));
            return "/inicio";
        } else {

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "ERROR", "ID. USUARIO O CLAVE SON INCORRECTOS"));
            return "/index";
        }

    }

}
