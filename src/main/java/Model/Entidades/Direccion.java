/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

/**
 *
 * @author Melisa
 */
public class Direccion {

    int id;
    boolean estado;
    Barrio barrio;
    String otrasSenas, idCliente;
    TipoDireccion tipo;

    public Direccion(int id, boolean estado, String idCliente, Barrio barrio, String otrasSenas, TipoDireccion tipo) {
        this.id = id;
        this.estado = estado;
        this.idCliente = idCliente;
        this.barrio = barrio;
        this.otrasSenas = otrasSenas;
        this.tipo = tipo;
    }

    public Direccion() {
        this.id = 0;
        this.estado = false;
        this.idCliente = "";
        this.barrio = null;
        this.otrasSenas = "";
        this.tipo = null;
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Barrio getBarrio() {
        return barrio;
    }

    public void setBarrio(Barrio barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSenas() {
        return otrasSenas;
    }

    public void setOtrasSenas(String otrasSenas) {
        this.otrasSenas = otrasSenas;
    }

    public TipoDireccion getTipo() {
        return tipo;
    }

    public void setTipo(TipoDireccion tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return new StringBuilder(this.getBarrio().getDistrito().getCanton().getProvincia().toString()).append(", ")
                .append(this.getBarrio().getDistrito().getCanton().toString()).append(", ")
                .append(this.getBarrio().getDistrito().toString()).append(", ")
                .append(this.getBarrio().toString()).append(", ")
                .append(this.getOtrasSenas()).toString();
    }
}
