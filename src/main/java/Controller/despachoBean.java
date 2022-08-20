/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.AccesoDatos.DespachoDB;
import Model.AccesoDatos.FacturaDB;
import Model.AccesoDatos.PedidoDB;
import Model.Entidades.Despacho;
import Model.Entidades.Pedido;
import Model.Entidades.Usuario;
import Util.Utilitarios;
import java.io.IOException;
import java.util.Calendar;
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
    Pedido pedido;
    String observacion, txtBuscar;

    public void verificarRol() {
        Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
        if (!(Utilitarios.administrador(usuario) || Utilitarios.bodeguero(usuario))) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("principal.xhtml");
            } catch (IOException ex) {

            }
        }
    }

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
            this.setPedidos(new PedidoDB().seleccionarFacturados());
        } catch (SNMPExceptions ex) {
        }
    }

//    Busca los pedidos
    public void buscar() throws SNMPExceptions {
        if (this.getTxtBuscar().equals("")) {
            this.setPedidos(new PedidoDB().seleccionarFacturados());
        } else {
            this.setPedidos(new PedidoDB().seleccionarFacturadosPorNombre(this.getTxtBuscar()));
        }
    }

//    Registra el despacho del pedido
    public void registrarDespacho() throws SNMPExceptions {

        Despacho despacho = new Despacho(0, true, pedido,
                new FacturaDB().seleccionarPorPedido(this.getPedido().getId()).getId(),
                Calendar.getInstance().getTime(), this.getObservacion());

        if (Utilitarios.validarDespacho(this.getObservacion())) {
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
        this.setObservacion("");
        this.cargarComponentes();
    }
}
