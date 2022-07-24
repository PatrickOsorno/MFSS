/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Cliente;
import Model.ClienteDB;
import Model.Direccion;
import Model.DireccionDB;
import Model.HorarioDB;
import Model.RolUsuario;
import Model.RolUsuarioDB;
import Model.Usuario;
import Model.UsuarioDB;
import Util.Utilitarios;
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
public class gestionUsuariosBean {

    String correo, contrasena, rolUsuario;
    List<SelectItem> rolesUsuario;
    List<Cliente> clientes;

    String direccion;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public void cargarDireccion(String IdCliente) throws SNMPExceptions {
        List<Direccion> direccionesCliente = new DireccionDB().seleccionarPorCliente(IdCliente);
        StringBuilder strb = new StringBuilder();
        for (Direccion d : direccionesCliente) {
            strb.append(d.toString()).append(".\n");
        }
        this.setDireccion(strb.toString());
    }
//    Se aceptan los clientes que pidieron un acceso

    public void aceptarCliente(Cliente cliente) throws SNMPExceptions {
        Usuario usuario = new Usuario();
        usuario.setCorreo(cliente.getCorreo());
        usuario.setContrasena(Utilitarios.genearContrasenaAleatoria());
        usuario.setRol(new RolUsuarioDB().seleccionarRolPorId(2));
        usuario.setEstado(true);
        usuario.setCliente(cliente);
        try {
            this.agregarUsuario(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente aceptado con éxito!"));
            this.cargarCliente();
        } catch (SNMPExceptions e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMensajeParaDesarrollador()));
        }
    }

//    Se denegan los clientes que pidieron un acceso
    public void denegarCliente() {

    }

//    Se agrega un usuario nuevo
    private void agregarUsuario(Usuario usuario) throws SNMPExceptions {
        UsuarioDB usDb = new UsuarioDB();
        usDb.insertar(usuario);
        usDb.insertarRolUsuario(usuario);
    }
}
