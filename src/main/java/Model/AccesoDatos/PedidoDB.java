/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.PedidoDetalle;
import Model.Entidades.EstadoPedido;
import Model.Entidades.Pedido;
import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Melisa
 */
public class PedidoDB {

    private AccesoDatos accesoDatos;

    public PedidoDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

    public void insertar(Pedido pedido) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Pedido(IdCliente, FechaEntrega, Estado, "
                            + "IdEstadoPedido, subTotal, IdDireccion, IdHorario) "
                            + "values(?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, pedido.getIdCliente());
            ps.setTimestamp(2, new Timestamp(pedido.getFechaEntrega().getTime()));
            ps.setBoolean(3, pedido.getEstado());
            ps.setInt(4, pedido.getEstadoPedido().getId());
            ps.setFloat(5, pedido.calcularSubtotal());
            ps.setInt(6, pedido.getDireccion().getId());
            ps.setInt(7, pedido.getHorario().getId());

            accesoDatos.ejecutaSQL(ps);

            pedido.getDetalle().forEach(det -> {
                try {
                    this.insertarDetalle(det);
                } catch (SNMPExceptions ex) {

                }
            });
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void insertarDetalle(PedidoDetalle detalle) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into PedidoDetalle(IdPedido, IdProducto, "
                            + "Cantidad, Estado, DescuentoProd) "
                            + "values(select @@IDENTITY, ?, ?, ?, ?)");
            ps.setInt(1, detalle.getProducto().getId());
            ps.setInt(2, detalle.getCantidad());
            ps.setBoolean(3, detalle.getEstado());
            ps.setDouble(4, detalle.getDescuentoProd());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public EstadoPedido SeleccionarEstadoPedidoPorId(int id) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, Descripcion, Estado from EstadoPedido where id = ?");
            ps.setInt(1, id);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if(rs.next()){
                return new EstadoPedido(rs.getInt("Id"), rs.getString("Descripcion"), rs.getBoolean("Estado"));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
