/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class inicioBean {

    String usuario, contrasena, TipoUsuario;
    List<SelectItem> roles;

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getTipoUsuario() {
        return TipoUsuario;
    }

    public void setTipoUsuario(String TipoUsuario) {
        this.TipoUsuario = TipoUsuario;
    }

    public List<SelectItem> getRoles() {
        return roles;
    }

    public void setRoles(List<SelectItem> roles) {
        this.roles = roles;
    }

    @PostConstruct
    public void cargarRoles() {

    }

    public void cancelar() {
        this.setUsuario("");
        this.setContrasena("");
        this.setTipoUsuario("");
    }

    public String iniciarSesion() {
//        FacesContext contexto = FacesContext.getCurrentInstance();
//        usuario us = new usuario(usuario, contrasena);
//        
//        if (us.getCorreo().equals("Patrick") && us.getContrasena().equals("12345")) {
//            contexto.getExternalContext().getSessionMap().put("usuario", us);
//            return "principal?faces-redirect=true";
//        }
//        return "index?faces-redirect=true";
        return "principal?faces-redirect=true";
    }
    
}
