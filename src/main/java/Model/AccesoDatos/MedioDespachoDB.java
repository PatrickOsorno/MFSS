/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.MedioDespacho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Melisa
 */
public class MedioDespachoDB {
    private AccesoDatos accesoDatos;
    
    public MedioDespachoDB(){
        this.accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
    public MedioDespacho seleccionarPorId(int idMedioDespacho) throws SNMPExceptions{
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Costo, Estado From MedioDespacho where id = ?");
            ps.setInt(1, idMedioDespacho);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    return new MedioDespacho(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion"), rs.getFloat("Costo"));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
