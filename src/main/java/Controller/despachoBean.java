/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.Date;
import java.util.List;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class despachoBean {
    List<Object> pedidos;
    List<SelectItem> mediosDespacho;
    Date fechaHoraEnvio;
    String medioDespacho, txtBuscar;

    public List<Object> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Object> pedidos) {
        this.pedidos = pedidos;
    }

    public List<SelectItem> getMediosDespacho() {
        return mediosDespacho;
    }

    public void setMediosDespacho(List<SelectItem> mediosDespacho) {
        this.mediosDespacho = mediosDespacho;
    }

    public Date getFechaHoraEnvio() {
        return fechaHoraEnvio;
    }

    public void setFechaHoraEnvio(Date fechaHoraEnvio) {
        this.fechaHoraEnvio = fechaHoraEnvio;
    }

    public String getMedioDespacho() {
        return medioDespacho;
    }

    public void setMedioDespacho(String medioDespacho) {
        this.medioDespacho = medioDespacho;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }
    
    public void buscar(){
        
    }
    
    public void registrarDespacho(){
        
    }
}
