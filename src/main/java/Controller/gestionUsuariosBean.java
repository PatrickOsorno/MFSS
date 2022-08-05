/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Entidades.Cliente;
import Model.AccesoDatos.ClienteDB;
import Model.Entidades.Direccion;
import Model.AccesoDatos.DireccionDB;
import Model.AccesoDatos.HorarioDB;
import Model.AccesoDatos.RolUsuarioDB;
import Model.Entidades.Usuario;
import Model.AccesoDatos.UsuarioDB;
import Util.Utilitarios;
import java.io.IOException;
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

    String direccion, motivo, contrasena, nuevaContrasena, nuevaContrasenaConfirmada, nombreUsuario;
    Cliente clienteDenegado;
    List<SelectItem> rolesUsuario;
    List<Cliente> clientes;

    public Cliente getClienteDenegado() {
        return clienteDenegado;
    }

    public void setClienteDenegado(Cliente clienteDenegado) {
        this.clienteDenegado = clienteDenegado;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getNuevaContrasena() {
        return nuevaContrasena;
    }

    public void setNuevaContrasena(String nuevaContrasena) {
        this.nuevaContrasena = nuevaContrasena;
    }

    public String getNuevaContrasenaConfirmada() {
        return nuevaContrasenaConfirmada;
    }

    public void setNuevaContrasenaConfirmada(String getNuevaContrasenaConfirmada) {
        this.nuevaContrasenaConfirmada = getNuevaContrasenaConfirmada;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrsena) {
        this.contrasena = contrsena;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<SelectItem> getRolesUsuario() {
        return rolesUsuario;
    }

    public void setRolesUsuario(List<SelectItem> rolesUsuario) {
        this.rolesUsuario = rolesUsuario;
    }

//    Se cargan todos los clientes
    @PostConstruct
    public void cargarCliente() {
        this.setNombreUsuario(((Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario")).getCorreo());
        try {
            List<Cliente> clienteDB = new ClienteDB().seleccionarNoAceptados();
            clientes = new ArrayList<>();
            clienteDB.forEach(cliente -> {
                try {
                    cliente.setDirecciones(new DireccionDB().seleccionarPorCliente(cliente.getId()));
                    cliente.setHorarios(new HorarioDB().seleccionarPorCliente(cliente.getId()));
                } catch (SNMPExceptions ex) {
                }
                clientes.add(cliente);
            });
        } catch (SNMPExceptions ex) {

        }
    }

//    Se cargan las direcciones que posee un clienteDenegado
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
        StringBuilder mensaje = new StringBuilder();
        mensaje.append("Estimado Cliente, le informamos que usted ha sido aceptado en nuestro sistema, su contraseña de ingreso al sistema es la siguiente: \n\n")
                .append(usuario.getContrasena())
                .append("\n\nRecuerde que dicha contraseña puede ser cambiada una vez haya iniciado sesión, en caso de ser requerido.");
        try {
            this.agregarUsuario(usuario);
            Utilitarios.enviarCorreo(usuario.getCorreo(), mensaje.toString(), "Bienvenido al Sistema");
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente aceptado con éxito!"));
            this.cargarCliente();
        } catch (SNMPExceptions e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMensajeParaDesarrollador()));
        }
    }

//    Se denegan los clientes que pidieron un acceso
    public void denegarCliente() throws SNMPExceptions {

        new HorarioDB().eliminar(this.getClienteDenegado().getId());
        new DireccionDB().eliminar(this.getClienteDenegado().getId());
        new ClienteDB().eliminar(this.getClienteDenegado().getId());

        StringBuilder sb = new StringBuilder();
        sb.append("Estimado cliente, le informamos que ha sido denegado y el motivo es el siguiente:\n")
                .append(this.getMotivo());
        Utilitarios.enviarCorreo(clienteDenegado.getCorreo(), sb.toString(), "Denegación de Cliente");
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Cliente denegado con éxito!"));
        this.cargarCliente();
    }

//    Se agrega un usuario nuevo
    private void agregarUsuario(Usuario usuario) throws SNMPExceptions {
        UsuarioDB usDb = new UsuarioDB();
        usDb.insertar(usuario);
        usDb.insertarRolUsuario(usuario);
    }

    public void cambiarContrasena() throws SNMPExceptions, IOException {
        StringBuilder sb = new StringBuilder();
        
        if (!this.getNuevaContrasena().equals(this.getNuevaContrasenaConfirmada())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Las contraseñas no coinciden."));
            return;
        }

        if (new UsuarioDB().seleccionarUsuarioPorCredenciales(this.getNombreUsuario(), this.getContrasena()) == null) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "La contraseña de confirmación es incorrecta."));
            return;
        }

        new UsuarioDB().cambiarContrasena(this.getNombreUsuario(), this.getNuevaContrasena());

        sb.append("Estimado usuario, le informamos que su contraseña fue cambiada, y es la siguiente:\n\n")
                .append(this.getNuevaContrasena());

        Utilitarios.enviarCorreo(this.getNombreUsuario(), sb.toString(), "Cambio de contraseña");

//        FacesContext.getCurrentInstance().addMessage(null,
//                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "La contraseña fue cambiada."));

        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
    }
}