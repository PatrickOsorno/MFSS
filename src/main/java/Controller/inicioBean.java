/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;
import Model.UsuarioDB;
import java.sql.SQLException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class inicioBean {

    String usuario, contrasena, TipoUsuario, rol;
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

//    Se cargan los roles de usuario
    @PostConstruct
    public void cargarRoles() {

    }

//    Si se cancela se limpiará los campos 
    public void cancelar() {
        this.setUsuario("");
        this.setContrasena("");
        this.setTipoUsuario("");
    }

//    Se valida que el usuario sea valido e ingresa a la siguiente página
    public String iniciarSesion() {
        return "principal?faces-redirect=true";
    }

}
