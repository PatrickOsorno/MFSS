/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Cliente;
import Model.ClienteDB;
import Model.DireccionDB;
import Model.HorarioDB;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class gestionUsuariosBean {

    String correo, contrasena, rolUsuario;
    List<SelectItem> rolesUsuario;
    List<Cliente> clientes;

    public List<Cliente> getClientes() {
//        clientes = new ArrayList<>();
//        clientes.add(new Cliente("504370456", "Patrick", "Osorno Rojas", "posorno@est.utn.ac.cr", "8365-2980", true, null, null));
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
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

    @PostConstruct
    public void cargarCliente() {

        try {
            List<Cliente> clienteDB = new ClienteDB().seleccionarNoAceptados();
            clientes = new ArrayList<>();
            clienteDB.forEach(cliente -> {
                try {
                    cliente.setDirecciones(new DireccionDB().seleccionarPorCliente(cliente.getId()));
                    cliente.setHorario(new HorarioDB().seleccionarPorCliente(cliente.getId()));
                } catch (SNMPExceptions ex) {
                }
                clientes.add(cliente);
            });
        } catch (SNMPExceptions ex) {
            
        }

    }

//    Se aceptan los clientes que pidieron un acceso
    public void aceptarCliente() {

    }

//    Se denegan los clientes que pidieron un acceso
    public void denegarCliente() {

    }

//    Se agrega un usuario nuevo
    public void agregarUsuario() {

    }
}
