/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.SNMPExceptions;
import Model.Producto;
import Model.ProductoDB;
import Util.Utilitarios;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author Patrick Osorno
 */
public class gestionProductosBean {

    List<Producto> productos;
    String descripcion, foto, txtBuscar;
    float precio;
    int cantMin, stock, codigo;
    boolean edita;
    Producto producto;

    @Inject
    ProductoDB prodDB;

    public boolean getEdita() {
        return edita;
    }

    public void setEdita(boolean edita) {
        this.edita = edita;
    }

    public ProductoDB getProdDB() {
        return prodDB;
    }

    public void setProdDB(ProductoDB prodDB) {
        this.prodDB = prodDB;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public String getTxtBuscar() {
        return txtBuscar;
    }

    public void setTxtBuscar(String txtBuscar) {
        this.txtBuscar = txtBuscar;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public int getCantMin() {
        return cantMin;
    }

    public void setCantMin(int cantMin) {
        this.cantMin = cantMin;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

//    Se carga las tablas
    @PostConstruct
    public void cargarTablas() {
        this.setEdita(false);
        try {
            productos = prodDB.SeleccionarTodo();
        } catch (SNMPExceptions ex) {
        }
    }

//    Se cargan los atributos de esta clase con los atributos del producto 
    public void cargar() {
        this.setEdita(true);
        this.setCodigo(this.getProducto().getId());
        this.setDescripcion(this.getProducto().getDescripcion());
        this.setFoto(this.getProducto().getFoto());
        this.setPrecio(this.getProducto().getPrecio());
        this.setStock(this.getProducto().getStock());
        this.setCantMin(this.getProducto().getCantMinima());
    }

//    Mediante de este método se limpian los campos de texto 
    public void limpiar() {
        if (!Utilitarios.validarProductoNuevo(this.getCodigo(),
                this.getDescripcion(), this.getFoto(),
                this.getPrecio(), this.getStock(), this.getCantMin())) {

            this.setEdita(false);
            this.setProducto(null);
            this.setCodigo(0);
            this.setDescripcion("");
            this.setFoto("");
            this.setPrecio(0);
            this.setStock(0);
            this.setCantMin(0);
        }

    }

//    Se busca los productos
    public void buscar() {

    }

//    Se agregan los productos
    public void agregarProducto() {
        producto = new Producto(this.getCodigo(), true,
                this.getStock(), this.getCantMin(), this.getDescripcion(),
                this.getFoto(), this.getPrecio());
        try {
            prodDB.insertar(this.getProducto());
            this.setProducto(null);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto guardado con éxito!"));
            this.cargarTablas();
        } catch (SNMPExceptions e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMensajeParaDesarrollador()));
        }
    }

//    Se eliminan los productos
    public void eliminarProducto() {
        try {
            prodDB.eliminar(this.getProducto().getId());
            this.setProducto(null);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto Eliminado con éxito!"));
            this.cargarTablas();
        } catch (SNMPExceptions e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMensajeParaDesarrollador()));
        }
    }

//    Se modifican los productos
    public void modificarProducto() {
        producto = new Producto(this.getCodigo(), true,
                this.getStock(), this.getCantMin(), this.getDescripcion(),
                this.getFoto(), this.getPrecio());
        try {
            prodDB.modificar(this.getProducto());
            this.setProducto(null);
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Producto modificado con éxito!"));
            this.cargarTablas();
        } catch (SNMPExceptions e) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMensajeParaDesarrollador()));
        }
    }

//    Se guardan los productos cuando se quiere agregar un nuevo producto 
    public void guardar() throws SNMPExceptions {
        if (Utilitarios.validarProductoNuevo(this.getCodigo(), this.getDescripcion(), 
                this.getFoto(), this.getPrecio(), this.getStock(), this.getCantMin())) {
            FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Los datos ingresados no son correctos"));
            return;
        }
        
        if (prodDB.seleccionarPorId(this.getCodigo()) == null) {
            this.agregarProducto();
        } else {
            this.modificarProducto();
        }
        this.limpiar();
    }
}
