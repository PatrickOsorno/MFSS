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
public class CantonDB {

    private AccesoDatos accesoDatos;

    public CantonDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del canton por medio de la Provincia
    public List<Canton> seleccionarPorProvincia(int idProvincia) throws SNMPExceptions {
        List<Canton> cantones = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descrip, Estado from Canton where IdProvincia = ?");
            ps.setInt(1, idProvincia);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            while(rs.next()){
                cantones.add(new Canton(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"),
                        new ProvinciaDB().seleccionarPorId(idProvincia)));
            }
        } catch (SNMPExceptions | SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return cantones;
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos de la Provincia, por medio de los ID
    public Canton seleccionarPorId(int idProvincia, int idCanton) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select IdProvincia, Id, Descrip, Estado from Canton where IdProvincia = ? and Id = ?");
            ps.setInt(1, idProvincia);
            ps.setInt(2, idCanton);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if(rs.next()){
                return new Canton(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"),
                        new ProvinciaDB().seleccionarPorId(idProvincia));
            }
        } catch (SNMPExceptions | SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
