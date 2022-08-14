/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.AccesoDatos.EstadoPedidoDB;
import Model.AccesoDatos.PedidoDB;
import Model.Entidades.EstadoPedido;
import Model.Entidades.Pedido;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class reportesBean {
    
    List<Date> rangoFechasPedido, rangoFechasVenta;
    List<SelectItem> estados, tiposPago;
    List<Pedido> pedidos;
    List<Object> ventas;
    int idEstado, idTipoPago;

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

    public List<Object> getVentas() {
        return ventas;
    }

    public void setVentas(List<Object> ventas) {
        this.ventas = ventas;
    }

    public boolean activarBotonExportar() {
        return this.getPedidos().isEmpty();
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
    public void cargarCombo() {
        this.setEstados(new ArrayList<>());
        this.setPedidos(new ArrayList<>());
        try {
            List<EstadoPedido> estadosPedido = new EstadoPedidoDB().seleccionarTodos();
            estados.add(new SelectItem(0, "Seleccione el estado"));
            estadosPedido.forEach(est -> {
                estados.add(new SelectItem(est.getId(), est.getDescripcion()));
            });
        } catch (SNMPExceptions ex) {
            Logger.getLogger(reportesBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

//    Se muesta el reporte de pedidos
    public void mostrarReportePedidos() throws SNMPExceptions {
        this.setPedidos(new PedidoDB().seleccionarPorRangoDeFechaYEstado(this.getRangoFechasPedido().get(0), 
                this.getRangoFechasPedido().get(1), this.getIdEstado()));
    }

//    Se muestra el reporte de las ventas
    public void mostrarReporteVentas() {

    }
}
