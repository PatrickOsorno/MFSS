/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class reportesBean {

    Date rangoFechas;
    String estado, tipoPago;
    List<SelectItem> estados, tiposPago;
    List<Object> pedidos;
    List<Object> ventas;

    public Date getRangoFechas() {
        return rangoFechas;
    }

    public void setRangoFechas(Date rangoFechas) {
        this.rangoFechas = rangoFechas;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
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

    public List<Object> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Object> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Object> getVentas() {
        return ventas;
    }

    public void setVentas(List<Object> ventas) {
        this.ventas = ventas;
    }

//    Se carga la tabla
    @PostConstruct
    public void cargarComponentes() {

    }

//    Se muesta el reporte de pedidos
    public void mostrarReportePedidos() {

    }

//    Se muestra el reporte de las ventas
    public void mostrarReporteVentas() {

    }
}
