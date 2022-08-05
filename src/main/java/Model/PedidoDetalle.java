/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Melisa
 */
public class PedidoDetalle {

    Pedido pedido;
    Producto producto;
    int cantidad;
    boolean estado;
    double descuentoProd;

    public PedidoDetalle(Pedido pedido, Producto producto, int cantidad, boolean estado, double descuentoProd) {
        this.pedido = pedido;
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

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
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
