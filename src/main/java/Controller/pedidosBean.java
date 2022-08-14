/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.AccesoDatos.DespachoDB;
import Model.AccesoDatos.EstadoPedidoDB;
import Model.AccesoDatos.HorarioDB;
import Model.Entidades.Cliente;
import Model.Entidades.Direccion;
import Model.Entidades.Horario;
import Model.Entidades.Pedido;
import Model.AccesoDatos.PedidoDB;
import Model.Entidades.PedidoDetalle;
import Model.Entidades.Producto;
import Model.AccesoDatos.ProductoDB;
import Model.Entidades.EstadoPedido;
import Model.Entidades.MedioDespacho;
import Model.Entidades.Usuario;
import Util.Utilitarios;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class pedidosBean {

    List<Producto> productos;
    List<PedidoDetalle> detallesPedido;
    List<Direccion> direcciones;
    List<SelectItem> horariosEntrega, mediosDespacho;
    String txtBuscar;
    Date fechaEntrega;
    Direccion direccionEntrega;
    Horario horarioEntrega;
    MedioDespacho medioDespacho;
    int cantidad, idHorario, idMedioDespacho;

    @PostConstruct
    public void cargarComponentes() {
        this.setHorariosEntrega(new ArrayList<>());
        this.setDetallesPedido(new ArrayList<>());
        this.setMediosDespacho(new ArrayList<>());

        try {
            this.llenarComponentes();
        } catch (SNMPExceptions ex) {
        }

    }

    public MedioDespacho getMedioDespacho() {
        return medioDespacho;
    }

    public void setMedioDespacho(MedioDespacho medioDespacho) {
        this.medioDespacho = medioDespacho;
    }

    public List<SelectItem> getMediosDespacho() {
        return mediosDespacho;
    }

    public void setMediosDespacho(List<SelectItem> mediosDespacho) {
        this.mediosDespacho = mediosDespacho;
    }

    public int getIdMedioDespacho() {
        return idMedioDespacho;
    }

    public void setIdMedioDespacho(int idMedioDespacho) {
        this.idMedioDespacho = idMedioDespacho;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public List<SelectItem> getHorariosEntrega() {
        return horariosEntrega;
    }

    public void setHorariosEntrega(List<SelectItem> horariosEntrega) {
        this.horariosEntrega = horariosEntrega;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<PedidoDetalle> getDetallesPedido() {
        return detallesPedido;
    }

    public void setDetallesPedido(List<PedidoDetalle> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
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

    public void llenarComponentes() throws SNMPExceptions {
        this.setDirecciones(((Usuario) (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("Usuario"))).getCliente().getDirecciones());
        List<Horario> horarios = ((Usuario) (FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap().get("Usuario"))).getCliente().getHorarios();
        this.horariosEntrega.add(new SelectItem(0, "Seleccione el Horario de Entrega"));
        horarios.forEach(horarioT -> {
            this.horariosEntrega.add(new SelectItem(horarioT.getId(), horarioT.toString()));
        });
        this.setProductos(new ProductoDB().SeleccionarTodo());
        List<MedioDespacho> mediosD = new DespachoDB().seleccionarMediosDespacho();
        mediosD.forEach(medio -> {
            this.mediosDespacho.add(new SelectItem(medio.getId(), medio.getDescripcion()));
        });
    }

//    Se confirma el pedido 
    public void confirmarPedido() throws SNMPExceptions {
        Cliente cliente = ((Usuario) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario"))).getCliente();
        this.setHorarioEntrega(new HorarioDB().seleccionarPorId(this.getIdHorario()));
        this.setMedioDespacho(new DespachoDB().seleccionarMedioDespachoPorId(this.getIdMedioDespacho()));
        Pedido pedido = new Pedido(0, true,
                cliente,
                this.getFechaEntrega(), this.getHorarioEntrega(), this.getDireccionEntrega(), this.getDetallesPedido(), 0,
                new EstadoPedidoDB().SeleccionarEstadoPedidoPorId(1),
                this.getMedioDespacho());

        if (Utilitarios.validarDatosPedido(this.getDetallesPedido(), this.getFechaEntrega(),
                this.getDireccionEntrega(), this.getHorarioEntrega(), this.getMedioDespacho())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Datos incompletos"));
            return;
        }

        new PedidoDB().insertar(pedido);
        this.limpiar();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Éxito", "Pedido realizado"));
    }

//    Se agrega la orden al carrito
    public void agregarAOrden(Producto producto) {
        if(producto.getCantidad() > producto.getCantDisponible()){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "La cantidad seleccionada del producto: " + producto.getDescripcion() 
                                    +" es mayor a la cantidad disponible para la venta"));
            return;
        }
        if (!validarExistenciaProd(producto)) {
            detallesPedido.add(new PedidoDetalle(0, producto, producto.getCantidad(), true, producto.calcularDescuento()));
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Éxito", "Producto agregado a la orden"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "El producto seleccionado ya fue agregado a la orden"));
        }
        producto.setCantidad(0);
    }

    //Se reestablece el valor de los cambios
    private void limpiar() {
        this.setDetallesPedido(new ArrayList<>());
        this.setFechaEntrega(null);
        this.setIdHorario(0);
        this.setIdMedioDespacho(0);
        this.setDireccionEntrega(null);
    }

    private boolean validarExistenciaProd(Producto producto) {
        for (PedidoDetalle detalle : this.getDetallesPedido()) {
            if (detalle.getProducto() == producto) {
                return true;
            }
        }
        return false;
    }

//    Se elimina la orden
    public void eliminarDeOrden(PedidoDetalle detalle) {
        detallesPedido.remove(detalle);
    }

//    Se busca la orden 
    public void buscar() throws SNMPExceptions {
        if (!this.getTxtBuscar().equals("")) {
            this.setProductos(new ProductoDB().seleccionarPorNombre(this.getTxtBuscar()));
        } else {
            this.setProductos(new ProductoDB().SeleccionarTodo());
        }
    }

    public void seleccionarDireccion(Direccion direccion) {
        this.setDireccionEntrega(direccion);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "", "Dirección seleccionada:\n" + direccion.toString()));
    }
}
