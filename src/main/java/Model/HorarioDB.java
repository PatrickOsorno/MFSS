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
import java.sql.Timestamp;

/**
 *
 * @author Melisa
 */
public class HorarioDB {

    private AccesoDatos accesoDatos;

    public HorarioDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una insert en la base de datos en la tabla de horario con todos los atributos
    public void insertar(Horario horario) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Insert into Horario(Inicio, Fin, Estado, IdCliente) values(?, ?, ?, ?)");
            ps.setTimestamp(1, new Timestamp(horario.getInicio().getTime()));
            ps.setTimestamp(2, new Timestamp(horario.getFin().getTime()));
            ps.setBoolean(3, horario.getEstado());
            ps.setString(4, horario.getCliente().getId());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del horario mediante el cliente
    public Horario seleccionarPorCliente(String idCliente) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Inicio, Fin, Estado, Id, IdCliente from Horario where IdCliente = ?");
            ps.setString(1, idCliente);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {
                return new Horario(rs.getInt("Id"), rs.getBoolean("Estado"), null, rs.getTimestamp("Inicio"), rs.getTimestamp("Fin"));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }

    public void eliminar(String idCliente) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Delete from Horario where IdCliente = ?");
            ps.setString(1, idCliente);
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
