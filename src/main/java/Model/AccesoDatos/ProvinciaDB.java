/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.Provincia;
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
public class ProvinciaDB {
    private AccesoDatos accesoDatos;
    
    public ProvinciaDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos de la Provincia
    public List<Provincia> seleccionarProvincias() throws SNMPExceptions{
        List<Provincia> provincias = new ArrayList<>();
        try {
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(accesoDatos.getConexion()
                    .prepareStatement("Select Id, Descrip, Estado from Provincia"))) {
                while (rs.next()) {
                    provincias.add(new Provincia(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return provincias;
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos de la Provincia por medio del ID
    public Provincia seleccionarPorId(int Id) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, Descrip, Estado from Provincia where Id = ?");
            ps.setInt(1, Id);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                if (rs.next()) {
                    return new Provincia(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"));
                }
            }
        }  catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
