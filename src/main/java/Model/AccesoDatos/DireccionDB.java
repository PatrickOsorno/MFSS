/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.Direccion;
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
public class DireccionDB {

    private AccesoDatos accesoDatos;

    public DireccionDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos de la direccion por medio del cliente
    public List<Direccion> seleccionarPorCliente(String IdCliente) throws SNMPExceptions {
        List<Direccion> direcciones = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select IdCliente, IdProvincia, IdCanton, IdDistrito, IdBarrio, OtrasSenas, IdTipoDireccion, Estado, Id from direccion where idCliente = ?");
            ps.setString(1, IdCliente);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    direcciones.add(new Direccion(rs.getInt("Id"), rs.getBoolean("Estado"),
                            null, new BarrioDB().seleccionarPorId(rs.getInt("IdProvincia"), rs.getInt("IdCanton"), rs.getInt("IdDistrito"), rs.getInt("IdBarrio")),
                            rs.getString("OtrasSenas"),
                            new TipoDireccionDB().seleccionarPorId(rs.getInt("IdTipoDireccion"))));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return direcciones;
    }
    
    public Direccion seleccionarPorId(int idDireccion) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select IdCliente, IdProvincia, IdCanton, IdDistrito, IdBarrio, OtrasSenas, IdTipoDireccion, Estado, Id from direccion where Id = ?");
            ps.setInt(1, idDireccion);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    return new Direccion(rs.getInt("Id"), rs.getBoolean("Estado"),
                            null, new BarrioDB().seleccionarPorId(rs.getInt("IdProvincia"), rs.getInt("IdCanton"), rs.getInt("IdDistrito"), rs.getInt("IdBarrio")),
                            rs.getString("OtrasSenas"),
                            new TipoDireccionDB().seleccionarPorId(rs.getInt("IdTipoDireccion")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }

//    Por medio de este método se hace un insert en la base de datos en la tabla de direcciones con todos los atributos que posee
    public void insertar(Direccion direccion) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Direccion(IdCliente, IdProvincia, IdCanton, IdDistrito, IdBarrio, OtrasSenas, IdTipoDireccion, Estado) "
                            + "values(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, direccion.getIdCliente());
            ps.setInt(2, direccion.getBarrio().getDistrito().getCanton().getProvincia().getId());
            ps.setInt(3, direccion.getBarrio().getDistrito().getCanton().getId());
            ps.setInt(4, direccion.getBarrio().getDistrito().getId());
            ps.setInt(5, direccion.getBarrio().getId());
            ps.setString(6, direccion.getOtrasSenas());
            ps.setInt(7, direccion.getTipo().getId());
            ps.setBoolean(8, direccion.getEstado());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void eliminar(String idCliente) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Delete from Direccion where IdCliente = ?");
            ps.setString(1, idCliente);
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
