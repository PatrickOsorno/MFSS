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
    
    public Horario seleccionarPorCliente(String idCliente) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Inicio, Fin, Estado, Id, IdCliente from Horario where IdCliente = ?");
            ps.setString(1, idCliente);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if(rs.next()){
                return new Horario(rs.getInt("Id"), rs.getBoolean("Estado"), null, rs.getTimestamp("Inicio"), rs.getTimestamp("Fin"));
            }
        } catch (SQLException e) {
            throw  new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
    
}
