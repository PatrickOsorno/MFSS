/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

/**
 *
 * @author Melisa
 */
public class Producto {

    private int id, cantDisponible, cantMinima;
    private boolean estado;
    private String descripcion, foto;
    private float precio;

    public Producto(int id, boolean estado, int cantDisponible, int cantMinima, String descripcion, String foto, float precio) {
        this.id = id;
        this.estado = estado;
        this.cantDisponible = cantDisponible;
        this.cantMinima = cantMinima;
        this.descripcion = descripcion;
        this.foto = foto;
        this.precio = precio;
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

    public int getCantDisponible() {
        return cantDisponible;
    }

    public void setCantDisponible(int cantDisponible) {
        this.cantDisponible = cantDisponible;
    }

    public int getCantMinima() {
        return cantMinima;
    }

    public void setCantMinima(int cantMinima) {
        this.cantMinima = cantMinima;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
    @Override
    public String toString(){
        return this.getDescripcion();
    }
}
