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
public class registroBean {
    String identificacion, nombre, apellidos, correo, telefono, provincia, canton, 
            distrito,barrio, otrasSenas, tipoDireccion;
    Date fechaHoraInic, fechaHoraFin;
    
    List<SelectItem> provincias, cantones, distritos, barrios, tiposDireccion;

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCanton() {
        return canton;
    }

    public void setCanton(String canton) {
        this.canton = canton;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSenas() {
        return otrasSenas;
    }

    public void setOtrasSenas(String otrasSenas) {
        this.otrasSenas = otrasSenas;
    }

    public String getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(String tipoDireccion) {
        this.tipoDireccion = tipoDireccion;
    }

    public Date getFechaHoraInic() {
        return fechaHoraInic;
    }

    public void setFechaHoraInic(Date fechaHoraInic) {
        this.fechaHoraInic = fechaHoraInic;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public List<SelectItem> getProvincias() {
        return provincias;
    }

    public void setProvincias(List<SelectItem> provincias) {
        this.provincias = provincias;
    }

    public List<SelectItem> getCantones() {
        return cantones;
    }

    public void setCantones(List<SelectItem> cantones) {
        this.cantones = cantones;
    }

    public List<SelectItem> getDistritos() {
        return distritos;
    }

    public void setDsitritos(List<SelectItem> distritos) {
        this.distritos = distritos;
    }

    public List<SelectItem> getBarrios() {
        return barrios;
    }

    public void setBarrios(List<SelectItem> barrios) {
        this.barrios = barrios;
    }

    public List<SelectItem> getTiposDireccion() {
        return tiposDireccion;
    }

    public void setTiposDireccion(List<SelectItem> tiposDireccion) {
        this.tiposDireccion = tiposDireccion;
    }
    
//    Se registra el cliente 
    public void registrarCliente(){
        
    }
    
//    Se registra la direccion
    public void registrarDireccion(){
        
    }
    
//    Se registra el horario
    public void registrarHorario(){
        
    }
}
