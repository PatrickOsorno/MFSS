/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Melisa
 */
public class Horario {

    int id;
    boolean estado;
    Cliente cliente;
    Date inicio, fin;

    public Horario(int id, boolean estado, Cliente cliente, Date inicio, Date fin) {
        this.id = id;
        this.estado = estado;
        this.cliente = cliente;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Horario() {
        this.id = 0;
        this.estado = false;
        this.cliente = null;
        this.inicio = null;
        this.fin = null;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
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
    public String toString(){
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm a");
        return new StringBuilder().append("De ").append(formato.format(inicio)).append(" a ").append(formato.format(fin)).toString();
    }
}
