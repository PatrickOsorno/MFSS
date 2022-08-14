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

    private int id;
    private boolean estado;
    private TipoPago tipoPago;
    private float costoEnvio, descuento, subTotal, total, impuesto;
    private Date fecha;
    private List<FacturaDetalle> detalle;
    private final float IMPUESTO = 0.13f;

    public Factura(int id, boolean estado, TipoPago tipoPago, float costoEnvio, float descuento, float subTotal, float total, float impuesto, Date fecha, List<FacturaDetalle> detalle) {
        this.id = id;
        this.estado = estado;
        this.tipoPago = tipoPago;
        this.costoEnvio = costoEnvio;
        this.descuento = descuento;
        this.subTotal = subTotal;
        this.fecha = fecha;
        this.detalle = detalle;
        this.total = total;
        this.impuesto = impuesto;
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

    public List<FacturaDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<FacturaDetalle> detalle) {
        this.detalle = detalle;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public float getImpuesto() {
        return impuesto;
    }

    public void setImpuesto(float impuesto) {
        this.impuesto = impuesto;
    }

    public float calcularCostoEnvio() {
        float costoEnv = 0f;
        for (FacturaDetalle det : this.getDetalle()) {
            costoEnv += det.getPedido().getMedioDespacho().getCosto();
        }
        return costoEnv;
    }

    public float calcularDescuento() {
        float desc = 0f;
        for (FacturaDetalle det : this.getDetalle()) {
            for (PedidoDetalle detP : det.getPedido().getDetalle()) {
                desc += detP.getDescuentoProd();
            }
        }
        return desc;
    }

    public float calcularSubtotal() {
        float subT = 0f;
        for (FacturaDetalle det : this.getDetalle()) {
            subT += det.getPedido().getSubTotal();
        }
        return subT;
    }

    public float calcularImpuesto() {
        return calcularSubtotal() * this.IMPUESTO;
    }

    public float calcularTotal() {
        return this.calcularSubtotal() + this.calcularCostoEnvio() 
                + this.calcularImpuesto() - this.calcularDescuento();
    }
}
