/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

/**
 *
 * @author Melisa
 */
public class MedioDespacho {

    private int id;
    private boolean estado;
    private String descripcion;
    private float costo;

    public MedioDespacho(int id, boolean estado, String descripcion, float costo) {
        this.id = id;
        this.estado = estado;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public MedioDespacho() {
        this.id = 0;
        this.estado = false;
        this.descripcion = "";
        this.costo = 0f;
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

    public float getCosto() {
        return costo;
    }

    public void setCosto(float costo) {
        this.costo = costo;
    }

}
