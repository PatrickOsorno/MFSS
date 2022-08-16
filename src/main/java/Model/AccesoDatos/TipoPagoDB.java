/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.TipoPago;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class TipoPagoDB {

    private AccesoDatos accesoDatos;

    public TipoPagoDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

    public List<TipoPago> seleccionarTodos() throws SNMPExceptions {
        List<TipoPago> tiposPago = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Estado from TipoPago");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    tiposPago.add(new TipoPago(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return tiposPago;
    }

    public TipoPago seleccionarPorId(int idTipoPago) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Estado from TipoPago where Id = ?");
            ps.setInt(1, idTipoPago);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    return new TipoPago(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion"));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
