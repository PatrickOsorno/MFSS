/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.PedidoDetalle;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Patrick Osorno
 */
public class PedidoDetalleDB {
    private AccesoDatos accesoDatos;
    
    public PedidoDetalleDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
    public List<PedidoDetalle> seleccionarDetallesPorPedido(int IdPedido) throws SNMPExceptions {
        List<PedidoDetalle> detalles = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select IdPedido, IdProducto, Cantidad, Estado, DescuentoProd from PedidoDetalle where IdPedido = ?");
            ps.setInt(1, IdPedido);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            while (rs.next()) {
                detalles.add(new PedidoDetalle(IdPedido, new ProductoDB().seleccionarPorId(rs.getInt("IdProducto")), rs.getInt("Cantidad"),
                        rs.getBoolean("Estado"), rs.getFloat("DescuentoProd")));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return detalles;
    }
}
