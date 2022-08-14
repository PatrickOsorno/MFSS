/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.EstadoPedido;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick Osorno
 */
public class EstadoPedidoDB {

    private AccesoDatos accesoDatos;

    public EstadoPedidoDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

    public EstadoPedido SeleccionarEstadoPedidoPorId(int id) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Estado from EstadoPedido where id = ?");
            ps.setInt(1, id);
            try ( ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                if (rs.next()) {
                    return new EstadoPedido(rs.getInt("Id"), rs.getString("Descripcion"), rs.getBoolean("Estado"));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }

    public List<EstadoPedido> seleccionarTodos() throws SNMPExceptions {
        List<EstadoPedido> estadosPedido = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Estado from EstadoPedido");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    estadosPedido.add(new EstadoPedido(rs.getInt("Id"), rs.getString("Descripcion"), rs.getBoolean("Estado")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return estadosPedido;
    }
}
