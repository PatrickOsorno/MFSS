/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

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
public class ClienteDB {

    private AccesoDatos accesoDatos;

    public ClienteDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los que nos se encuentran en la tabla de Clientes
    public List<Cliente> seleccionarNoAceptados() throws SNMPExceptions {
        List<Cliente> clientes =  new ArrayList<>();
        try {
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(accesoDatos
                    .getConexion().prepareStatement("Select Id, Nombre, Apellidos, Email, Telefono, Estado  from Cliente where email not in(select email from Usuario)"));
            while (rs.next()) {                
                clientes.add(new Cliente(rs.getString("Id"), rs.getString("Nombre"), 
                        rs.getString("Apellidos"), rs.getString("Email"), 
                        rs.getString("Telefono"), rs.getBoolean("Estado"), 
                        null,
                        null));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return clientes;
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos de la tabla cliente por medio del ID
    public Cliente seleccionarPorId(String idCliente) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, Nombre, Apellidos, Email, Telefono, Estado  from Cliente where Id = ?");
            ps.setString(1, idCliente);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if(rs.next()){
                return new Cliente(rs.getString("Id"), rs.getString("Nombre"), 
                        rs.getString("Apellidos"), rs.getString("Email"), 
                        rs.getString("Estado"), rs.getBoolean("Estado"), 
                        new DireccionDB().seleccionarPorCliente(rs.getString("Id")), 
                        new HorarioDB().seleccionarPorCliente(idCliente));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }

//    Por medio de este método se hace un insert en la base de datos  de la tabla Cliente con todos los atributos
    public void insertar(Cliente cliente) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Insert into Cliente(Id, Nombre, Apellidos, Email, Telefono, Estado) "
                    + "values(?, ?, ?, ?, ?, ?)");
            ps.setString(1, cliente.getId());
            ps.setString(2, cliente.getNombre());
            ps.setString(3, cliente.getApellidos());
            ps.setString(4, cliente.getCorreo());
            ps.setString(5, cliente.getTelefono());
            ps.setBoolean(6, cliente.getEstado());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void eliminar(String idCliente) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Delete from Cliente where Id = ?");
            ps.setString(1, idCliente);
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
