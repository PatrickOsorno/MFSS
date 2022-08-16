/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Entidades.Usuario;
import Util.Utilitarios;
import java.io.IOException;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patrick Osorno
 */
public class principalBean {

    Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");

    boolean esCliente = Utilitarios.cliente(usuario);

    boolean esBodeguero = Utilitarios.bodeguero(usuario);

    boolean esProduccion = Utilitarios.produccion(usuario);
    
    boolean esAdministrador = Utilitarios.administrador(usuario);

    public boolean isEsCliente() {
        return esCliente;
    }

    public void setEsCliente(boolean esCliente) {
        this.esCliente = esCliente;
    }

    public boolean isEsBodeguero() {
        return esBodeguero;
    }

    public void setEsBodeguero(boolean esBodeguero) {
        this.esBodeguero = esBodeguero;
    }

    public boolean isEsProduccion() {
        return esProduccion;
    }

    public void setEsProduccion(boolean esProduccion) {
        this.esProduccion = esProduccion;
    }    

    public boolean isEsAdministrador() {
        return esAdministrador;
    }

    public void setEsAdministrador(boolean esAdministrador) {
        this.esAdministrador = esAdministrador;
    }

//    Verifica el rol del inicio de sesion
    public void verificarSesion() {
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
