/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.Horario;
import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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
            ps.setString(4, horario.getIdCliente());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del horario mediante el cliente
    public List<Horario> seleccionarPorCliente(String idCliente) throws SNMPExceptions {
        List<Horario> horarios = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Inicio, Fin, Estado, Id, IdCliente from Horario where IdCliente = ?");
            ps.setString(1, idCliente);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    horarios.add(new Horario(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("IdCliente"), rs.getTimestamp("Inicio"), rs.getTimestamp("Fin")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return horarios;
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

    public Horario seleccionarPorId(int idHorario) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Inicio, Fin, Estado, Id, IdCliente from Horario where Id = ?");
            ps.setInt(1, idHorario);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                if (rs.next()) {
                    return new Horario(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("IdCliente"), rs.getTimestamp("Inicio"), rs.getTimestamp("Fin"));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
