/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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

    public List<Cliente> seleccionarNoAceptados() {
        return null;
    }

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
            accesoDatos.ejectaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void eliminar(int idCliente) {

    }
}
