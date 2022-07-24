/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Melisa
 */
public class Distrito {

    int id;
    boolean estado;
    String descripcion;
    Provincia provincia;
    Canton canton;

    public Distrito(int id, boolean estado, String descripcion, Provincia provincia, Canton canton) {
        this.id = id;
        this.estado = estado;
        this.descripcion = descripcion;
        this.provincia = provincia;
        this.canton = canton;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

    @Override
    public String toString() {
        return this.getDescripcion();
    }
}
