/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.Producto;
import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class ProductoDB {

    private AccesoDatos accesoDatos;

    public ProductoDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del producto
    public List<Producto> SeleccionarTodo() throws SNMPExceptions {
        List<Producto> productos = new ArrayList<>();
        try {
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(accesoDatos.getConexion()
                    .prepareStatement("Select Id, Descripcion, Foto, Precio, Stock, CantMin, Estado from Producto"));
            while (rs.next()) {
                productos.add(new Producto(rs.getInt("Id"), rs.getBoolean("Estado"),
                        rs.getInt("Stock"), rs.getInt("CantMin"), rs.getString("Descripcion"),
                        rs.getString("Foto"), rs.getFloat("Precio")));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return productos;
    }

//    Por medio de este método se hace un insert en la base de datos de la tabla Producto con todos los atributos
    public void insertar(Producto producto) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Producto(Id, Descripcion, Foto, Precio, Stock, CantMin, Estado) "
                            + "values(?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, producto.getId());
            ps.setString(2, producto.getDescripcion());
            ps.setString(3, producto.getFoto());
            ps.setFloat(4, producto.getPrecio());
            ps.setInt(5, producto.getCantDisponible());
            ps.setInt(6, producto.getCantMinima());
            ps.setBoolean(7, producto.getEstado());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

//    Por medio de este método se modifica en la base de datos los atribitos de la tabla Producto
    public void modificar(Producto producto) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Update Producto set Descripcion =  ?, Foto =  ?,"
                            + " Precio =  ?, Stock =  ?, CantMin =  ?, Estado =  ? where  id =  ?");
            ps.setString(1, producto.getDescripcion());
            ps.setString(2, producto.getFoto());
            ps.setFloat(3, producto.getPrecio());
            ps.setInt(4, producto.getCantDisponible());
            ps.setInt(5, producto.getCantMinima());
            ps.setBoolean(6, producto.getEstado());
            ps.setInt(7, producto.getId());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

//    Por medio de este método se elimina en la base de datos los atributos de la tabla la Producto
    public void eliminar(int idProducto) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Delete From Producto where id = ?");
            ps.setInt(1, idProducto);
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del producto por medio del ID
    public Producto seleccionarPorId(int idProducto) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, Descripcion, Foto, Precio, Stock, CantMin, Estado from Producto where Id = ?");
            ps.setInt(1, idProducto);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {
                return new Producto(rs.getInt("Id"), rs.getBoolean("Estado"),
                        rs.getInt("Stock"), rs.getInt("CantMin"), rs.getString("Descripcion"),
                        rs.getString("Foto"), rs.getFloat("Precio"));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
