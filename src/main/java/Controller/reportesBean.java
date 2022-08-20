/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.AccesoDatos.EstadoPedidoDB;
import Model.AccesoDatos.FacturaDB;
import Model.AccesoDatos.PedidoDB;
import Model.AccesoDatos.TipoPagoDB;
import Model.Entidades.EstadoPedido;
import Model.Entidades.Factura;
import Model.Entidades.Pedido;
import Model.Entidades.TipoPago;
import Model.Entidades.Usuario;
import Util.Utilitarios;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class reportesBean {

    List<Date> rangoFechasPedido, rangoFechasVenta;
    List<SelectItem> estados, tiposPago;
    List<Pedido> pedidos;
    List<Factura> facturas;
    int idEstado, idTipoPago;

    public void verificarRol() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        if (!Utilitarios.administrador(usuario)) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
            } catch (IOException ex) {

            }
        }
    }

    public List<Date> getRangoFechasPedido() {
        return rangoFechasPedido;
    }

    public void setRangoFechasPedido(List<Date> rangoFechasPedido) {
        this.rangoFechasPedido = rangoFechasPedido;
    }

    public List<Date> getRangoFechasVenta() {
        return rangoFechasVenta;
    }

    public void setRangoFechasVenta(List<Date> rangoFechasVenta) {
        this.rangoFechasVenta = rangoFechasVenta;
    }

    public List<SelectItem> getEstados() {
        return estados;
    }

    public void setEstados(List<SelectItem> estados) {
        this.estados = estados;
    }

    public List<SelectItem> getTiposPago() {
        return tiposPago;
    }

    public void setTiposPago(List<SelectItem> tiposPago) {
        this.tiposPago = tiposPago;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public boolean activarBotonExportarPedidos() {
        return this.getPedidos().isEmpty();
    }

    public boolean activarBotonExportarFacturas() {
        return this.getFacturas().isEmpty();
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(int idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public String formatearFecha(Date fecha) {
        return new SimpleDateFormat("dd/M/yyyy").format(fecha);
    }

    @PostConstruct
    public void cargarCombos() {
        this.setEstados(new ArrayList<>());
        this.setTiposPago(new ArrayList<>());
        this.setPedidos(new ArrayList<>());
        this.setFacturas(new ArrayList<>());
        try {
            List<EstadoPedido> estadosPedido = new EstadoPedidoDB().seleccionarTodos();
            List<TipoPago> tipsPago = new TipoPagoDB().seleccionarTodos();
            estados.add(new SelectItem(0, "Seleccione el estado"));
            estadosPedido.forEach(est -> {
                estados.add(new SelectItem(est.getId(), est.getDescripcion()));
            });

            tiposPago.add(new SelectItem(0, "Seleccione el tipo"));
            tipsPago.forEach(tipoPago -> {
                tiposPago.add(new SelectItem(tipoPago.getId(), tipoPago.getDescripcion()));
            });
        } catch (SNMPExceptions ex) {
        }

    }

//    Se muesta el reporte de pedidos
    public void mostrarReportePedidos() throws SNMPExceptions {

        if (Utilitarios.validarMostrarReportePedidos(this.getRangoFechasPedido(), this.getIdEstado())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos"));
            return;
        }
        if (this.getRangoFechasPedido().size() > 2) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Solo debe seleccionar dos fechas"));
            return;
        }
        this.setPedidos(new PedidoDB().seleccionarPorRangoDeFechaYEstado(this.getRangoFechasPedido().get(0),
                this.getRangoFechasPedido().get(1), this.getIdEstado()));
    }

//    Se muestra el reporte de las facturas
    public void mostrarReporteVentas() throws SNMPExceptions {

        if (Utilitarios.validarMostrarReporteVentas(this.getRangoFechasVenta(), this.getIdTipoPago())) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos"));
            return;
        }
        if (this.getRangoFechasVenta().size() > 2) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Solo debe seleccionar dos fechas"));
            return;
        }
        this.setFacturas(new FacturaDB().seleccionarPorRangoDeFechaYTipoPago(this.getRangoFechasVenta().get(0),
                this.getRangoFechasVenta().get(1), this.getIdTipoPago()));
    }
}
