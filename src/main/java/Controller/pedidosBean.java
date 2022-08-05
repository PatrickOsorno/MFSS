/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Cliente;
import Model.Direccion;
import Model.Horario;
import Model.Pedido;
import Model.PedidoDB;
import Model.PedidoDetalle;
import Model.Producto;
import Model.ProductoDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patrick Osorno
 */
public class pedidosBean {

    List<Producto> prodsSel, productos;
    List<Direccion> direcciones;
    String txtBuscar;
    Date fechaEntrega;
    Direccion direccionEntrega;
    Horario horarioEntrega;
    int cantidad;

    @PostConstruct
    public void cargarComponentes() {
        this.setProdsSel(new ArrayList<>());
        this.setDirecciones(((Cliente) (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("Cliente"))).getDirecciones());
        try {
            this.setProductos(new ProductoDB().SeleccionarTodo());
        } catch (SNMPExceptions ex) {
        }
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<Producto> getProdsSel() {
        return prodsSel;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public void setProdsSel(List<Producto> prods) {
        this.prodsSel = prods;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Date getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(Date fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public Direccion getDireccionEntrega() {
        return direccionEntrega;
    }

    public void setDireccionEntrega(Direccion direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public Horario getHorarioEntrega() {
        return horarioEntrega;
    }

    public void setHorarioEntrega(Horario horarioEntrega) {
        this.horarioEntrega = horarioEntrega;
    }

//    Se confirma el pedido 
    public void confirmarPedido() throws SNMPExceptions {
        List<PedidoDetalle> detallesPedido = new ArrayList<>();
        Cliente cliente = (Cliente) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Cliente");
        Pedido pedido = new Pedido(0, true,
                cliente,
                fechaEntrega, cliente.getHorario(), direccionEntrega, null, 0,
                new PedidoDB().SeleccionarEstadoPedidoPorId(1));

        prodsSel.forEach(prodSel -> {
            detallesPedido.add(new PedidoDetalle(pedido, prodSel, prodSel.getCantidad(), true, 0));
        });

        pedido.setDetalle(detallesPedido);

        new PedidoDB().insertar(pedido);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Éxito", "Pedido realizado"));
    }

//    Se agrega la orden al carrito
    public void agregarAOrden(Producto producto) {
        producto.setCantidad(this.getCantidad());
        if (!prodsSel.contains(producto)) {
            prodsSel.add(producto);
            this.setCantidad(0);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Éxito", "Producto agregado a la orden"));
        } else {
            this.setCantidad(0);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "El producto seleccionado ya fue agregado a la orden"));
        }
    }

//    Se elimina la orden
    public void eliminarDeOrden(Producto producto) {
        prodsSel.remove(producto);
    }

//    Se busca la orden 
    public void buscar() {

    }
}
