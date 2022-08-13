/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Despacho;
import Model.Entidades.MedioDespacho;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class DespachoDB {

    private AccesoDatos accesoDatos;

    public DespachoDB() {
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

    public MedioDespacho seleccionarMedioDespachoPorId(int idMedio) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Costo, Estado from MedioDespacho where Id = ?");
            ps.setInt(1, idMedio);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {
                return new MedioDespacho(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion"), rs.getFloat("Costo"));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }

    public List<MedioDespacho> seleccionarMediosDespacho() throws SNMPExceptions {
        List<MedioDespacho> mediosDespacho = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Costo, Estado from MedioDespacho");
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            while (rs.next()) {
                mediosDespacho.add(new MedioDespacho(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion"), rs.getFloat("Costo")));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return mediosDespacho;
    }
}
