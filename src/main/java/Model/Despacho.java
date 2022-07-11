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
public class Despacho {

    int id;
    boolean estado;
    Pedido pedido;
    Factura factura;
    Date fechaHora;
    String observacion;
    MedioDespacho medio;

    public Despacho(int id, boolean estado, Pedido pedido, Factura factura, Date fechaHora, String observacion, MedioDespacho medio) {
        this.id = id;
        this.estado = estado;
        this.pedido = pedido;
        this.factura = factura;
        this.fechaHora = fechaHora;
        this.observacion = observacion;
        this.medio = medio;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public MedioDespacho getMedio() {
        return medio;
    }

    public void setMedio(MedioDespacho medio) {
        this.medio = medio;
    }

}
