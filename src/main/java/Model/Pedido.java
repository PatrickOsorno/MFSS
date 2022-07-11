/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Melisa
 */
public class Pedido {

    int id;
    boolean estado;
    Cliente cliente;
    Date fechaEntrega;
    Direccion dirreccion;
    Horario horario;

    public Pedido(int id, boolean estado, Cliente cliente, Date fechaEntrega, Direccion dirreccion, Horario horario) {
        this.id = id;
        this.estado = estado;
        this.cliente = cliente;
        this.fechaEntrega = fechaEntrega;
        this.dirreccion = dirreccion;
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Direccion getDirreccion() {
        return dirreccion;
    }

    public void setDirreccion(Direccion dirreccion) {
        this.dirreccion = dirreccion;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

}
