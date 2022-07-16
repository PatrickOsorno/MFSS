/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
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
}
