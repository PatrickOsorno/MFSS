/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Melisa
 */
public class Horario {

    private int id;
    private boolean estado;
    private String idCliente;
    private Date inicio, fin;

    public Horario(int id, boolean estado, String idCliente, Date inicio, Date fin) {
        this.id = id;
        this.estado = estado;
        this.idCliente = idCliente;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Horario() {
        this.id = 0;
        this.estado = false;
        this.idCliente = "";
        this.inicio = null;
        this.fin = null;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
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

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm a");
        return new StringBuilder().append("De ").append(formato.format(inicio)).append(" a ").append(formato.format(fin)).toString();
    }
}
