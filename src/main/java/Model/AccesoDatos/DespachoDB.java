/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Despacho;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 *
 * @author Melisa
 */
public class DespachoDB {
    private AccesoDatos accesoDatos;
    
    public DespachoDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
    public void insertar(Despacho despacho) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Insert into Despacho(Id, IdPedido, IdFactura, FechaHora, Observacion, IdMedioDespacho, Estado) "
                    + "values(?, ?, ?, ?, ?, ?, ?)");
            ps.setInt(1, despacho.getId());
            ps.setInt(2, despacho.getPedido().getId());
            ps.setInt(3, despacho.getFactura().getId());
            ps.setTimestamp(4, new Timestamp(despacho.getFechaHora().getTime()));
            ps.setString(5, despacho.getObservacion());
            ps.setInt(6, despacho.getMedio().getId());
            ps.setBoolean(7, despacho.getEstado());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
