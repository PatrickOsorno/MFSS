/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.RolUsuario;
import Model.RolUsuarioDB;
import Model.Usuario;
import Model.UsuarioDB;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class inicioBean {

    String usuario, contrasena;
    int idRol;
    List<SelectItem> roles;

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

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

    public List<SelectItem> getRoles() {
        return roles;
    }

    public void setRoles(List<SelectItem> roles) {
        this.roles = roles;
    }

//    Se cargan los roles de usuario
    public void cargarRoles() {
        roles = new ArrayList<>();
        try {
            List<RolUsuario> rolesUsuario = new RolUsuarioDB().seleccionarPorCorreo(usuario);
            rolesUsuario.forEach(rolUsuario -> {
                roles.add(new SelectItem(rolUsuario.getId(), rolUsuario.getDescripcion()));
            });

        } catch (SNMPExceptions ex) {
        }
    }

//    Si se cancela se limpiará los campos 
    public void cancelar() {
        this.setUsuario("");
        this.setContrasena("");
        this.setIdRol(0);
    }

//    Se valida que el usuario sea valido e ingresa a la siguiente página
    public String iniciarSesion() throws SNMPExceptions {
        Usuario usuarioIngresa = new UsuarioDB().obtenerUsuarioPorCredenciales(usuario, contrasena);
        if (usuarioIngresa != null) {
            usuarioIngresa.setRol(new RolUsuarioDB().seleccionarRolPorId(idRol));
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("Usuario", usuarioIngresa);
            cancelar();
            roles = null;
            return "principal?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error de Inicio de Sesión", "Credenciales incorrectas"));
        }
        return null;
    }
}
