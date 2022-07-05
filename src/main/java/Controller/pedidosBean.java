/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class pedidosBean {

    List<Object> prodsSel, productos;
    List<SelectItem> horarios, direcciones;
    String txtBuscar;
    Date fechaEntrega;
    Object direccionEntrega;
    Object horarioEntrega;

    @PostConstruct
    public void cargarComponentes() {
        prodsSel = new ArrayList<>();
        prodsSel.add("Hola");
        prodsSel.add("Mundo");
    }

    public List<Object> getProdsSel() {
        return prodsSel;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public void setProdsSel(List<Object> prods) {
        this.prodsSel = prods;
    }

    public List<Object> getProductos() {
        return productos;
    }

    public void setProductos(List<Object> productos) {
        this.productos = productos;
    }

    public List<SelectItem> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<SelectItem> horarios) {
        this.horarios = horarios;
    }

    public List<SelectItem> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<SelectItem> direcciones) {
        this.direcciones = direcciones;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Object getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Object direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Object getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(Object horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }
    
    public void confirmarPedido(){
        
    }

    public void agregarAOrden(){
        
    }
    
    public void eliminarDeOrden(){
        
    }
    
    public void buscar(){
        
    }
}
