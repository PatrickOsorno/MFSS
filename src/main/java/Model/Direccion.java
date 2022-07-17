/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Melisa
 */
public class Direccion {

    int id;
    boolean estado;
    Cliente cliente;
    Provincia provincia;
    Canton canton;
    Distrito distrito;
    Barrio barrio;
    String otrasSenas;
    TipoDireccion tipo;

    public Direccion(int id, boolean estado, Cliente cliente, Provincia provincia, Canton canton, Distrito distrito, Barrio barrio, String otrasSenas, TipoDireccion tipo) {
        this.id = id;
        this.estado = estado;
        this.cliente = cliente;
        this.provincia = provincia;
        this.canton = canton;
        this.distrito = distrito;
        this.barrio = barrio;
        this.otrasSenas = otrasSenas;
        this.tipo = tipo;
    }

    public Direccion() {
        this.id = 0;
        this.estado = false;
        this.cliente = null;
        this.provincia = null;
        this.canton = null;
        this.distrito = null;
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
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

}
