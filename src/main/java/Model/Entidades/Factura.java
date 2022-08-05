/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class Factura {

    int id;
    boolean estado;
    TipoPago tipoPago;
    float costoEnvio, descuento, subTotal;
    Date fecha;
    List<FacturaDetalle> detalle;

    public Factura(int id, boolean estado, TipoPago tipoPago, float costoEnvio, float descuento, float subTotal, Date fecha, List<FacturaDetalle> detalle) {
        this.id = id;
        this.estado = estado;
        this.tipoPago = tipoPago;
        this.costoEnvio = costoEnvio;
        this.descuento = descuento;
        this.subTotal = subTotal;
        this.fecha = fecha;
        this.detalle = detalle;
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

    public TipoPago getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(TipoPago tipoPago) {
        this.tipoPago = tipoPago;
    }

    public float getCostoEnvio() {
        return costoEnvio;
    }

    public void setCostoEnvio(float costoEnvio) {
        this.costoEnvio = costoEnvio;
    }

    public float getDescuento() {
        return descuento;
    }

    public void setDescuento(float descuento) {
        this.descuento = descuento;
    }

    public float getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
