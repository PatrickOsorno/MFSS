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
public class Pedido {

    int id;
    boolean estado;
    String idCliente;
    Date fechaEntrega;
    Direccion direccion;
    Horario horario;
    double subTotal;
    List<PedidoDetalle> detalle;
    EstadoPedido estadoPedido;

    public Pedido(int id, boolean estado, String idCliente, Date fechaEntrega, Horario horario, Direccion direccion, List<PedidoDetalle> detalle, float subTotal, EstadoPedido estadoPedido) {
        this.id = id;
        this.estado = estado;
        this.idCliente = idCliente;
        this.fechaEntrega = fechaEntrega;
        this.detalle = detalle;
        this.subTotal = subTotal;
        this.horario = horario;
        this.direccion = direccion;
        this.estadoPedido = estadoPedido;
    }

    public Pedido() {
        this.id = 0;
        this.estado = false;
        this.idCliente = "";
        this.fechaEntrega = null;
        this.detalle = null;
        this.subTotal = 0;
        this.horario = null;
        this.direccion = null;
        this.estadoPedido = null;
    }

    public EstadoPedido getEstadoPedido() {
        return estadoPedido;
    }

    public void setEstadoPedido(EstadoPedido estadoPedido) {
        this.estadoPedido = estadoPedido;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(float subTotal) {
        this.subTotal = subTotal;
    }

    public List<PedidoDetalle> getDetalle() {
        return detalle;
    }

    public void setDetalle(List<PedidoDetalle> detalle) {
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

    public String getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(String idCliente) {
        this.idCliente = idCliente;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }
    
    public float calcularSubtotal(){
        float subT = 0f;
        for(PedidoDetalle det : this.getDetalle()){
            subT += (det.getProducto().getPrecio() * det.getCantidad()) - (det.getDescuentoProd() * det.getCantidad());
        }
        return subT;
    }
}
