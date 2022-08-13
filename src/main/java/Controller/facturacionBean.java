/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.AccesoDatos.FacturaDB;
import Model.AccesoDatos.PedidoDB;
import Model.AccesoDatos.TipoPagoDB;
import Model.Entidades.Cliente;
import Model.Entidades.Direccion;
import Model.Entidades.EstadoPedido;
import Model.Entidades.Factura;
import Model.Entidades.FacturaDetalle;
import Model.Entidades.Horario;
import Model.Entidades.MedioDespacho;
import Model.Entidades.Pedido;
import Model.Entidades.PedidoDetalle;
import Model.Entidades.TipoPago;
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
public class facturacionBean {

    String txtBuscar;
    int porcDescuento, tipoPago;
    List<Pedido> pedidos, pedidosSel;
    List<SelectItem> tiposPago;

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public int getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(int tipoPago) {
        this.tipoPago = tipoPago;
    }

    public int getPorcDescuento() {
        return porcDescuento;
    }

    public void setPorcDescuento(int porcDescuento) {
        this.porcDescuento = porcDescuento;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<Pedido> getPedidosSel() {
        return pedidosSel;
    }

    public void setPedidosSel(List<Pedido> pedidosSel) {
        this.pedidosSel = pedidosSel;
    }

    public List<SelectItem> getTiposPago() {
        return tiposPago;
    }

    public void setTiposPago(List<SelectItem> tiposPago) {
        this.tiposPago = tiposPago;
    }

    public String formatearFecha(Date fecha) {
        return new SimpleDateFormat("dd/M/yyyy").format(fecha);
    }

    public void llenarCombo() throws SNMPExceptions {
        this.setTiposPago(new ArrayList<>());
        List<TipoPago> tiposP = new TipoPagoDB().seleccionarTodos();
        tiposPago.add(new SelectItem(0, "Seleccione el Tipo"));
        tiposP.forEach(tipPago -> {
            tiposPago.add(new SelectItem(tipPago.getId(), tipPago.getDescripcion()));
        });
    }

    public void llenarTabla() throws SNMPExceptions {
        this.setPedidos(new PedidoDB().seleccionarNoFacturados());
    }

//    Carga la tabla
    @PostConstruct
    public void cargarTabla() {
        try {
            this.llenarTabla();
            this.llenarCombo();
        } catch (SNMPExceptions ex) {

        }
    }

//    Se crea la factura
    public void crearFactura() throws SNMPExceptions {
        List<FacturaDetalle> detalles = new ArrayList<>();
        
        if (!validarPerteneciaCliente(this.getPedidosSel())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los pedidos seleccionados no pertenecen al mismo cliente"));
            return;
        }
        this.getPedidosSel().forEach(pedidoSel -> {
            detalles.add(new FacturaDetalle(0, pedidoSel, true));
        });
        
        Factura factura = new Factura(0, true, new TipoPagoDB().seleccionarPorId(this.getTipoPago()), 0, 0, 0, null, detalles);
        if(Util.Utilitarios.validarFacturacion(this.getPedidosSel(), this.getTipoPago())){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Datos incorrectos"));
            return;
        }
        new FacturaDB().insertar(factura);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Facturación exitosa"));
        this.llenarTabla();
    }

    public boolean validarPerteneciaCliente(List<Pedido> pedidos) {
//        String idCliente = "";
        for (Pedido pedido : this.getPedidosSel()) {
            for (Pedido pedido1 : this.getPedidosSel()) {
                if(!pedido.getCliente().getId().equals(pedido1.getCliente().getId())){
                    return false;
                }
            }
        }
        
//        for (Pedido pedido : this.getPedidosSel()) {
//            if (idCliente.equals("")) {
//                idCliente = pedido.getCliente().getId();
//            } else {
//                return !pedido.getCliente().getId().equals(idCliente);
//            }
//        }
        return true;
    }

//    Se busca la factura
    public void buscar() throws SNMPExceptions {
        if(this.getTxtBuscar().equals("")){
            this.setPedidos(new PedidoDB().seleccionarNoFacturados());
        }else{
            this.setPedidos(new PedidoDB().seleccionarPorNombre(this.getTxtBuscar()));
        }
    }
}
