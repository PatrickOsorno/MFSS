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
public class DistritoDB {
    public AccesoDatos accesoDatos;
    
    public DistritoDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
    public List<Distrito> seleccionarPorProvinciaYCanton(int idProvincia, int idCanton) throws SNMPExceptions{
        List<Distrito> distritos =  new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, Descrip, Estado from Distrito where Idprovincia = ? and IdCanton = ?");
            ps.setInt(1, idProvincia);
            ps.setInt(2, idCanton);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            while(rs.next()){
                distritos.add(new Distrito(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"), 
                        new ProvinciaDB().seleccionarPorId(idProvincia), new CantonDB().seleccionarPorId(idCanton)));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return distritos;
    }
    
    public Distrito seleccionarPorId(int idDistrito) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select IdProvincia, IdCanton, Id, Descrip, Estado from Distrito where Id = ?");
            ps.setInt(1, idDistrito);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            while (rs.next()) {                
                return new Distrito(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"), 
                        new ProvinciaDB().seleccionarPorId(rs.getInt("IdProvincia")), 
                        new CantonDB().seleccionarPorId(rs.getInt("IdCanton")));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
