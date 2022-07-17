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
public class Horario {

    int id;
    boolean estado;
    Date inicio, fin;

    public Horario(int id, boolean estado, Date inicio, Date fin) {
        this.id = id;
        this.estado = estado;
        this.inicio = inicio;
        this.fin = fin;
    }

    public Horario() {
        this.id = 0;
        this.estado = false;
        this.inicio = null;
        this.fin = null;
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
}
