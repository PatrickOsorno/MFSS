/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Barrio;
import Model.BarrioDB;
import Model.Canton;
import Model.CantonDB;
import Model.Cliente;
import Model.Direccion;
import Model.Distrito;
import Model.DistritoDB;
import Model.Horario;
import Model.Provincia;
import Model.ProvinciaDB;
import Model.TipoDireccion;
import Model.TipoDireccionDB;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;

/**
 *
 * @author Patrick Osorno
 */
public class registroBean {
    String identificacion, nombre, apellidos, correo, telefono,  otrasSenas ;
    Date fechaHoraInic, fechaHoraFin;
    int provincia, canton, distrito, barrio, tipoDireccion;
    
    List<SelectItem> provincias, cantones, distritos, barrios, tiposDireccion;
    
    Cliente cliente = new Cliente();;
    Direccion direccion;
    Horario horario;
    List<Direccion> direccs = new ArrayList<>();

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

    public int getProvincia() {
        return provincia;
    }

    public void setProvincia(int provincia) {
        this.provincia = provincia;
    }

    public int getCanton() {
        return canton;
    }

    public void setCanton(int canton) {
        this.canton = canton;
    }

    public int getDistrito() {
        return distrito;
    }

    public void setDistrito(int distrito) {
        this.distrito = distrito;
    }

    public int getBarrio() {
        return barrio;
    }

    public void setBarrio(int barrio) {
        this.barrio = barrio;
    }

    public String getOtrasSenas() {
        return otrasSenas;
    }

    public void setOtrasSenas(String otrasSenas) {
        this.otrasSenas = otrasSenas;
    }

    public int getTipoDireccion() {
        return tipoDireccion;
    }

    public void setTipoDireccion(int tipoDireccion) {
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
        cliente.setId(identificacion);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setTelefono(telefono);
        cliente.setEstado(true);
    }
    
//    Se registra la direccion
    public void registrarDireccion(){

    }
    
//    Se registra el horario
    public void registrarHorario(){
        
    }
    
    public void cargarProvincias() throws SNMPExceptions{
        List<Provincia> provs = new ProvinciaDB().seleccionarProvincias();
        provincias = new ArrayList<>();
        provs.forEach(prov ->{
            provincias.add(new SelectItem(prov.getId(), prov.getDescripcion()));
        });
    }
    
    public void cargarCantones() throws SNMPExceptions{
        List<Canton> cants = new CantonDB().seleccionarPorProvincia(provincia);
        cantones = new ArrayList<>();
        cants.forEach(cant ->{
            cantones.add(new SelectItem(cant.getId(), cant.getDescripcion()));
        });
    }
    
    public void cargarDistritos() throws SNMPExceptions{
        List<Distrito> dists = new DistritoDB().seleccionarPorProvinciaYCanton(provincia, canton);
        distritos = new ArrayList<>();
        dists.forEach(dist -> {
            distritos.add(new SelectItem(dist.getId(), dist.getDescripcion()));
        });
    }
    
    public void cargarBarrios() throws SNMPExceptions{
        List<Barrio> barrs = new BarrioDB().seleccionarPorProvinciaCantonDistrito(provincia, canton, distrito);
        barrios = new ArrayList<>();
        barrs.forEach(barr -> {
            barrios.add(new SelectItem(barr.getId(), barr.getDescripcion()));
        });
    }
    
    public void cargarTiposDireccion() throws SNMPExceptions{
        List<TipoDireccion> tipsDireccion = new TipoDireccionDB().seleccionarTodos();
        tiposDireccion = new ArrayList<>();
        tipsDireccion.forEach(tipDirecc ->{
            tiposDireccion.add(new SelectItem(tipDirecc.getId(), tipDirecc.getDescripcion()));
        });
    }
    
    @PostConstruct
    public void cargarComponentes(){
        try {
            cargarTiposDireccion();
            cargarProvincias();
            cargarCantones();
            cargarDistritos();
            cargarBarrios();
        } catch (SNMPExceptions e) {
            
        }
    }
}
