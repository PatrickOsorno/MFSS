/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.AccesoDatos.DespachoDB;
import Model.AccesoDatos.FacturaDB;
import Model.AccesoDatos.MedioDespachoDB;
import Model.AccesoDatos.PedidoDB;
import Model.Entidades.Despacho;
import Model.Entidades.Pedido;
import Util.Utilitarios;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author Patrick Osorno
 */
public class despachoBean {
    List<Pedido> pedidos;
    Date fechaHoraEnvio;
    Pedido pedido;
    String observacion, txtBuscar;

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }
    
    
    @PostConstruct
    public void cargarComponentes() {
        try {
            this.setPedidos(new PedidoDB().SeleccionarFacturados());
        } catch (SNMPExceptions ex) {
        }
    }
    
//    Busca los pedidos
    public void buscar(){
        
    }
    
//    Registra el despacho del pedido
    public void registrarDespacho() throws SNMPExceptions{
        Despacho despacho = new Despacho(0,true, pedido, 
                new FacturaDB().seleccionarPorPedido(this.getPedido().getId()).getId(), 
                this.getFechaHoraEnvio(), this.getObservacion());

        if (Utilitarios.validarDespacho(this.getFechaHoraEnvio(), this.getObservacion())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Datos incompletos"));
            return;
        }
        new DespachoDB().insertar(despacho);
        this.limpiar();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Éxito", "Despacho realizado"));
    }
    
     public void limpiar() {
        this.setFechaHoraEnvio(null);
        this.setObservacion("");
        this.cargarComponentes();
    }
}
