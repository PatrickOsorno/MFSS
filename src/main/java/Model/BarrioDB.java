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
public class BarrioDB {
    private AccesoDatos accesoDatos;
    
    public BarrioDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del Barrio mediante 
    public List<Barrio> seleccionarPorProvinciaCantonDistrito(int idProvincia, int idCanton, int idDistrito) throws SNMPExceptions{
        List<Barrio> barrios = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, Descrip, Estado from Barrio where IdProvincia = ? and IdCanton = ? and IdDistrito = ?");
            ps.setInt(1, idProvincia);
            ps.setInt(2, idCanton);
            ps.setInt(3, idDistrito);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            while (rs.next()) {                
                barrios.add(new Barrio(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"), 
                        new ProvinciaDB().seleccionarPorId(idProvincia), 
                        new CantonDB().seleccionarPorId(idProvincia,idCanton), 
                        new DistritoDB().seleccionarPorId(idProvincia,idCanton,idDistrito)));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return barrios;
    }
    
//    Por medio de este método se hace una cosulta en la base de datos sobre todos los los atributos del Barrio mediante los id del mismo
    public Barrio seleccionarPorId(int idProvincia, int idCanton, int idDistrito, int idBarrio) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select IdProvincia, IdCanton, IdDistrito, Id, Descrip, Estado from Barrio where IdProvincia = ? and IdCanton = ? and IdDistrito  = ? and Id = ?");
            ps.setInt(1, idProvincia);
            ps.setInt(2, idCanton);
            ps.setInt(3, idDistrito);
            ps.setInt(4, idBarrio);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if(rs.next()){
                return new Barrio(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"), 
                        new ProvinciaDB().seleccionarPorId(rs.getInt("IdProvincia")), 
                        new CantonDB().seleccionarPorId(rs.getInt("IdProvincia"),rs.getInt("IdCanton")), 
                        new DistritoDB().seleccionarPorId(rs.getInt("IdProvincia"),rs.getInt("IdCanton"),rs.getInt("IdDistrito")));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
