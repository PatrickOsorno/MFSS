/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.Entidades;

/**
 *
 * @author Melisa
 */
public class PedidoDetalle {

    int idPedido;
    Producto producto;
    int cantidad;
    boolean estado;
    double descuentoProd;

    public PedidoDetalle(int idPedido, Producto producto, int cantidad, boolean estado, double descuentoProd) {
        this.idPedido = idPedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.estado = estado;
        this.descuentoProd = descuentoProd;
    }

    public double getDescuentoProd() {
        return descuentoProd;
    }

    public void setDescuentoProd(double descuentoProd) {
        this.descuentoProd = descuentoProd;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

}
