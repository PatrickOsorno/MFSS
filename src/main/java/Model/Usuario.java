/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Patrick Osorno
 */
public class Usuario {

    String correo, contrasena;
    Date fechaRegistro;
    Date ultimaEdicion;
    boolean estado;
    RolUsuario rol;

    public Usuario(String correo, String contrasena, Date fechaRegistro, Date ultimaEdicion, boolean estado, RolUsuario rol) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.fechaRegistro = fechaRegistro;
        this.ultimaEdicion = ultimaEdicion;
        this.estado = estado;
        this.rol = rol;
    }
    
    public Usuario(){
        this.correo = "";
        this.contrasena = "";
        this.fechaRegistro = null;
        this.ultimaEdicion = null;
        this.estado = false;
        this.rol = null;
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
}