/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Melisa
 */
public class HorarioDB {
    
    private AccesoDatos accesoDatos;
    
    public HorarioDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    public void insertar(Horario horario) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Insert into Horario(Id, Inicio, Fin, Estado) values(?, ?, ?, ?)");
            ps.setInt(1, horario.getId());
            ps.setTimestamp(2, new Timestamp(horario.getInicio().getTime()));
            ps.setTimestamp(3, new Timestamp(horario.getFin().getTime()));
            ps.setBoolean(4, horario.getEstado());
            accesoDatos.ejectaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
