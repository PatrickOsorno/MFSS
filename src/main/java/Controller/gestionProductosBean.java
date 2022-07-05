/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Patrick Osorno
 */
public class gestionProductosBean {

    List<Object> productos;
    String descripcion, foto, txtBuscar;
    float precio;
    int cantMin, stock, codigo;

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public List<Object> getProductos() {
        return productos;
    }

    public void setProductos(List<Object> productos) {
        this.productos = productos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
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

    public int getCantMin() {
        return cantMin;
    }

    public void setCantMin(int cantMin) {
        this.cantMin = cantMin;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    @PostConstruct
    public void cargarTablas(){
        
    }
    
    public void buscar(){
        
    }

    public void agregarProducto() {

    }

    public void eliminarProducto() {

    }
    
    public void modificarProducto(){
        
    }
}
