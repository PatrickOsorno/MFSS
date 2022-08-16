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

    private int id;
    private boolean estado;
    private Cliente cliente;
    private Date fechaEntrega;
    private Direccion direccion;
    private Horario horario;
    private float subTotal;
    private List<PedidoDetalle> detalle;
    private EstadoPedido estadoPedido;
    private MedioDespacho medioDespacho;

    public Pedido(int id, boolean estado, Cliente cliente, Date fechaEntrega, Horario horario, Direccion direccion,
            List<PedidoDetalle> detalle, float subTotal, EstadoPedido estadoPedido, MedioDespacho medioDespacho) {
        this.id = id;
        this.estado = estado;
        this.cliente = cliente;
        this.fechaEntrega = fechaEntrega;
        this.detalle = detalle;
        this.subTotal = subTotal;
        this.horario = horario;
        this.direccion = direccion;
        this.estadoPedido = estadoPedido;
        this.medioDespacho = medioDespacho;
    }

    public Pedido() {
        this.id = 0;
        this.estado = false;
        this.cliente = null;
        this.fechaEntrega = null;
        this.detalle = null;
        this.subTotal = 0f;
        this.horario = null;
        this.direccion = null;
        this.estadoPedido = null;
        this.medioDespacho = null;
    }

    public MedioDespacho getMedioDespacho() {
        return medioDespacho;
    }

    public void setMedioDespacho(MedioDespacho medioDespacho) {
        this.medioDespacho = medioDespacho;
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

    public float getSubTotal() {
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

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public float calcularSubtotal() {
        float subT = 0f;
        for (PedidoDetalle det : this.getDetalle()) {
            subT += (det.getProducto().getPrecio() * det.getCantidad()) - (det.getDescuentoProd() * det.getCantidad());
        }
        return subT;
    }
}
