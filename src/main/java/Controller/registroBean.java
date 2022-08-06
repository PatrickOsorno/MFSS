/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Entidades.Barrio;
import Model.AccesoDatos.BarrioDB;
import Model.Entidades.Canton;
import Model.AccesoDatos.CantonDB;
import Model.Entidades.Cliente;
import Model.AccesoDatos.ClienteDB;
import Model.Entidades.Direccion;
import Model.AccesoDatos.DireccionDB;
import Model.Entidades.Distrito;
import Model.AccesoDatos.DistritoDB;
import Model.Entidades.Horario;
import Model.AccesoDatos.HorarioDB;
import Model.Entidades.Provincia;
import Model.AccesoDatos.ProvinciaDB;
import Model.Entidades.TipoDireccion;
import Model.AccesoDatos.TipoDireccionDB;
import Util.Utilitarios;
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
public class registroBean {

    String identificacion, nombre, apellidos, correo, contrasena, telefono, otrasSenas;
    Date fechaHoraInic, fechaHoraFin;
    int provincia, canton, distrito, barrio, tipoDireccion;

    List<SelectItem> provincias, cantones, distritos, barrios, tiposDireccion;

    Cliente cliente;
    Direccion direccion;
    Horario horario;
    List<Direccion> direccs = new ArrayList<>();
    List<Horario> horarios = new ArrayList<>();

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

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
    public void registrarCliente() {
        if (Utilitarios.validacionRegistroCliente(this.getIdentificacion(),
                this.getNombre(), this.getApellidos(), this.getCorreo(), this.getTelefono())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los datos ingresados no son correctos"));
            return;
        }

        cliente = new Cliente();
        cliente.setId(identificacion);
        cliente.setNombre(nombre);
        cliente.setApellidos(apellidos);
        cliente.setCorreo(correo);
        cliente.setTelefono(telefono);
        cliente.setEstado(true);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        "Exito", "Cliente agregado"));
    }

//    Se registra la direccion
    public void registrarDireccion() throws SNMPExceptions {
        if (Utilitarios.validacionRegistroDireccion(this.getTipoDireccion(), this.getProvincia(),
                this.getCanton(), this.getDistrito(), this.getBarrio(), this.getOtrasSenas())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Los datos ingresados no son correctos"));
            return;
        }

        direccion = new Direccion();
        direccion.setTipo(new TipoDireccionDB().seleccionarPorId(tipoDireccion));
        direccion.setBarrio(new BarrioDB().seleccionarPorId(provincia, canton, distrito, barrio));
        direccion.setIdCliente(cliente.getId());
        direccion.setOtrasSenas(otrasSenas);
        cliente.setEstado(true);
        direccs.add(direccion);
        if (cliente != null) {
            cliente.setDirecciones(direccs);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito", "Dirección agregada"));
        } else {

            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Para registrar una dirección primero se debe registrar un cliente"));
        }
    }

//    Se registra el horario
    public void registrarHorario() {
        if (Utilitarios.validacionRegistroHorario(this.getFechaHoraInic(), this.getFechaHoraFin())) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Los datos ingresados no son correctos"));
            return;
        }
        
        horario = new Horario();
        horario.setEstado(true);
        horario.setInicio(this.getFechaHoraInic());
        horario.setFin(this.getFechaHoraFin());
        horario.setIdCliente(cliente.getId());
        horarios.add(horario);
        if (cliente != null && cliente.getDirecciones() != null) {
            this.cliente.setHorarios(horarios);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito", "Horario agregado"));
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Para registrar un horario primero se debe registrar un cliente y una dirección"));
        }
    }

//    Se cargan las provincias
    public void cargarProvincias() throws SNMPExceptions {
        List<Provincia> provs = new ProvinciaDB().seleccionarProvincias();
        provincias = new ArrayList<>();
        provincias.add(new SelectItem(0, "PROVINCIA NO ESPECIFICADA"));
        provs.forEach(prov -> {
            provincias.add(new SelectItem(prov.getId(), prov.getDescripcion()));
        });
    }

//    Se cargan los cantones
    public void cargarCantones() throws SNMPExceptions {
        List<Canton> cants = new CantonDB().seleccionarPorProvincia(provincia);
        cantones = new ArrayList<>();
        cantones.add(new SelectItem(0, "CANTON NO ESPECIFICADO"));
        cants.forEach(cant -> {
            cantones.add(new SelectItem(cant.getId(), cant.getDescripcion()));
        });
    }

//    Se cargan los distritos
    public void cargarDistritos() throws SNMPExceptions {
        List<Distrito> dists = new DistritoDB().seleccionarPorProvinciaYCanton(provincia, canton);
        distritos = new ArrayList<>();
        distritos.add(new SelectItem(0, "DISTRITO NO ESPECIFICADO"));
        dists.forEach(dist -> {
            distritos.add(new SelectItem(dist.getId(), dist.getDescripcion()));
        });
    }

//    Se cargan los barrios
    public void cargarBarrios() throws SNMPExceptions {
        List<Barrio> barrs = new BarrioDB().seleccionarPorProvinciaCantonDistrito(provincia, canton, distrito);
        barrios = new ArrayList<>();
        barrios.add(new SelectItem(0, "Barrio no especificado"));
        barrs.forEach(barr -> {
            barrios.add(new SelectItem(barr.getId(), barr.getDescripcion()));
        });
    }

//    Se cargan los tipos de direcciones
    public void cargarTiposDireccion() throws SNMPExceptions {
        List<TipoDireccion> tipsDireccion = new TipoDireccionDB().seleccionarTodos();
        tiposDireccion = new ArrayList<>();
        tipsDireccion.forEach(tipDirecc -> {
            tiposDireccion.add(new SelectItem(tipDirecc.getId(), tipDirecc.getDescripcion()));
        });
    }

//    Se guarda un cliente con el horario y direcciones
    public void guardarClienteBd() {
        if(Utilitarios.validacionRegistroClienteBd(cliente)){
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "No ha llenado todos los datos del cliente"));
            return;
        }
        
        try {
            new ClienteDB().insertar(cliente);
            for(Horario hor : cliente.getHorarios()){
                new HorarioDB().insertar(hor);
            }
            for (Direccion direcc : cliente.getDirecciones()) {
                new DireccionDB().insertar(direcc);
            }
            
             FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO,
                            "Exito", "Registro completo"));
        } catch (SNMPExceptions e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", e.getMessage()));
        }
    }

//    Se cargan todos los componentes
    @PostConstruct
    public void cargarComponentes() {
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
