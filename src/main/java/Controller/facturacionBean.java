/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class facturacionBean {

    String tipoPago, txtBuscar;
    int porcDescuento;
    List<Object> pedidos;
    List<Object> detallesPedido;
    List<SelectItem> tiposPago;

    public List<Object> getDetallesPedido() {
        detallesPedido = new ArrayList<>();
        detallesPedido.add("");
        detallesPedido.add("");
        detallesPedido.add("");
        detallesPedido.add("");
        detallesPedido.add("");
         detallesPedido.add("");
          detallesPedido.add("");
        return detallesPedido;
    }

    public void setDetallesPedido(List<Object> detallesPedido) {
        this.detallesPedido = detallesPedido;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(int porcDescuento) {
        this.porcDescuento = porcDescuento;
    }

    public List<Object> getPedidos() {
        pedidos = new ArrayList<>();
        pedidos.add("");
        return pedidos;
    }

    public void setPedidos(List<Object> pedidos) {
        this.pedidos = pedidos;
    }

    public List<SelectItem> getTiposPago() {
        return tiposPago;
    }

    public void setTiposPago(List<SelectItem> tiposPago) {
        this.tiposPago = tiposPago;
    }

//    Carga la tabla
    @PostConstruct
    public void cargarTabla() {

    }

//    Se crea la factura
    public void crearFactura() {

    }

//    Se busca la factura
    public void buscar() {

    }
}
