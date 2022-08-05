/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.Distrito;
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
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del distrito por medio de la provincia y canton
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
                        new CantonDB().seleccionarPorId(idProvincia, idCanton)));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return distritos;
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del distrito mediante los ID que posee
    public Distrito seleccionarPorId(int idProvincia, int idCanton, int idDistrito) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select IdProvincia, IdCanton, Id, Descrip, Estado from Distrito where IdProvincia = ? and IdCanton = ? and Id = ?");
            ps.setInt(1, idProvincia);
            ps.setInt(2, idCanton);
            ps.setInt(3, idDistrito);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {                
                return new Distrito(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"), 
                        new CantonDB().seleccionarPorId(idProvincia, idCanton));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
