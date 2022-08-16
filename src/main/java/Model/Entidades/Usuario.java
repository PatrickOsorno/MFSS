/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

import java.util.Date;

/**
 *
 * @author Patrick Osorno
 */
public class Usuario {

    private String correo, contrasena;
    private Date fechaRegistro;
    private Date ultimaEdicion;
    private boolean estado;
    private RolUsuario rol;
    private Cliente cliente;

    public Usuario(String correo, String contrasena, Date fechaRegistro, Date ultimaEdicion, boolean estado, RolUsuario rol, Cliente cliente) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.ultimaEdicion = ultimaEdicion;
        this.estado = estado;
        this.rol = rol;
        this.cliente = cliente;
    }
    
    public Usuario(){
        this.correo = "";
        this.contrasena = "";
        this.fechaRegistro = null;
        this.ultimaEdicion = null;
        this.estado = false;
        this.rol = null;
        this.cliente = null;
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

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getUltimaEdicion() {
        return ultimaEdicion;
    }

    public void setUltimaEdicion(Date ultimaEdicion) {
        this.ultimaEdicion = ultimaEdicion;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public RolUsuario getRol() {
        return rol;
    }

    public void setRol(RolUsuario rol) {
        this.rol = rol;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}