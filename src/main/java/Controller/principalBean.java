/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Entidades.Usuario;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patrick Osorno
 */
public class principalBean {

    Usuario usuario; 
//    boolean esCliente = usuario.getRol().getId() == 1;

//    public boolean isEsCliente() {
//        return esCliente;
//    }
//
//    public void setEsCliente(boolean esCliente) {
//        this.esCliente = esCliente;
//    }

//    Verifica el rol del inicio de sesion
    public void verificarSesion() {
        usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        if (usuario == null) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {

            }
        } 
    }

//    Se devuelve al inicio de sesion
    public String cerrarSesion() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index?faces-redirect=true";
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
