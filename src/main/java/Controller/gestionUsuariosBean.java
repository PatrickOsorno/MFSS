/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Patrick Osorno
 */
public class gestionUsuariosBean {

    String correo, contrasena, rolUsuario;
    List<SelectItem> rolesUsuario;
    DualListModel<String> clientes;

    @PostConstruct
    public void iniciar() {
        List<String> userSource = new ArrayList<>();
        List<String> userTarget = new ArrayList<>();
        userSource.add("Patrick");
        userSource.add("Melissa");
        clientes = new DualListModel<>(userSource, userTarget);
    }

    public DualListModel<String> getClientes() {
        return clientes;
    }

    public void setClientes(DualListModel<String> clientes) {
        this.clientes = clientes;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getRolUsuario() {
        return rolUsuario;
    }

    public void setRolUsuario(String rolUsuario) {
        this.rolUsuario = rolUsuario;
    }

    public List<SelectItem> getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(List<SelectItem> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }

    public void aceptarCliente(){
        
    }
    
    public void denegarCliente(){
        
    }
    
    public void agregarUsuario(){
        
    }
}
