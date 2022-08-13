/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

import java.util.List;

/**
 *
 * @author Melisa
 */
public class Cliente {

    String id, nombre, apellidos, telefono, correo;
    boolean estado;
    List<Horario> horarios;
    List<Direccion> direcciones;

    public Cliente(String id, String nombre, String apellidos, String correo, String telefono, boolean estado, List<Direccion> direcciones, List<Horario> horarios) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.estado = estado;
        this.direcciones = direcciones;
        this.horarios = horarios;
    }

    public Cliente() {
        this.id = "";
        this.nombre = "";
        this.apellidos = "";
        this.correo = "";
        this.telefono = "";
        this.estado = false;
        this.direcciones = null;
        horarios = null;
    }

    public List<Horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<Horario> horarios) {
        this.horarios = horarios;
    }

    public String getId() {
        return id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
    
    
}
