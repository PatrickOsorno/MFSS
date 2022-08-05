/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.TipoDireccion;
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
public class TipoDireccionDB {
    private AccesoDatos accesoDatos;
    
    public TipoDireccionDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del tipo de direccion
    public List<TipoDireccion> seleccionarTodos() throws SNMPExceptions{
        List<TipoDireccion> tiposDireccion =  new ArrayList<>();
        try {
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(accesoDatos.getConexion().prepareStatement("Select Id, Descrip, Estado from TipoDireccion"));
            while (rs.next()) {                
                tiposDireccion.add(new TipoDireccion(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip")));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return tiposDireccion;
    }
    
//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del tipo de direccion por medio del ID
    public TipoDireccion seleccionarPorId(int idTipo) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descrip, Estado from TipoDireccion where Id = ?");
            ps.setInt(1, idTipo);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {                
                return new TipoDireccion(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descrip"));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
