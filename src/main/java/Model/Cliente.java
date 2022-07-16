/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.List;

/**
 *
 * @author Melisa
 */
public class Cliente {

    String id, nombre, apellidos, telefono;
    boolean estado;
    Horario horario;
    List<Direccion> direcciones;

    public Cliente(String id, String nombre, String apellidos, String telefono, boolean estado, Horario horario, List<Direccion> direcciones) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.estado = estado;
        this.horario = horario;
        this.direcciones = direcciones;
    }

    public Cliente(){
          this.id = "";
        this.nombre = "";
        this.apellidos = "";
        this.telefono = "";
        this.estado = false;
        this.horario = null;
        this.direcciones = null;
    }
    
    public String getId() {
        return id;
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

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }
}
