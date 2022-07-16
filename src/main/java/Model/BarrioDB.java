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
                        new CantonDB().seleccionarPorId(idCanton), 
                        new DistritoDB().seleccionarPorId(idDistrito)));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return barrios;
    }
}
