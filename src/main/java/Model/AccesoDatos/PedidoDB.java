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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class PedidoDB {

    private AccesoDatos accesoDatos;
    private int IdPedido = 0;

    public PedidoDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

    public void insertar(Pedido pedido) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Pedido(IdCliente, FechaEntrega, Estado, "
                            + "IdEstadoPedido, subTotal, IdDireccion, IdHorario, IdMedioDespacho, FechaRegistro) "
                            + "values(?, ?, ?, ?, ?, ?, ?, ?, getdate())");
            ps.setString(1, pedido.getCliente().getId());
            ps.setTimestamp(2, new Timestamp(pedido.getFechaEntrega().getTime()));
            ps.setBoolean(3, pedido.getEstado());
            ps.setInt(4, pedido.getEstadoPedido().getId());
            ps.setFloat(5, pedido.calcularSubtotal());
            ps.setInt(6, pedido.getDireccion().getId());
            ps.setInt(7, pedido.getHorario().getId());
            ps.setInt(8, pedido.getMedioDespacho().getId());

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

    private void insertarDetalle(PedidoDetalle detalle) throws SNMPExceptions {
        if (IdPedido == 0) {
            IdPedido = this.obtenerIdPedido();
        }
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into PedidoDetalle(IdPedido, IdProducto, "
                            + "Cantidad, Estado, DescuentoProd) "
                            + "values(?, ?, ?, ?, ?)");
            ps.setInt(1, IdPedido);
            ps.setInt(2, detalle.getProducto().getId());
            ps.setInt(3, detalle.getCantidad());
            ps.setBoolean(4, detalle.getEstado());
            ps.setDouble(5, detalle.getDescuentoProd());
            accesoDatos.ejecutaSQL(ps);
            new ProductoDB().restarCantidadDisponible(detalle.getProducto().getId(), detalle.getCantidad());
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    private int obtenerIdPedido() throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select @@IDENTITY as Id");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                if (rs.next()) {
                    return rs.getInt("Id");
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return 0;
    }

    public List<Pedido> seleccionarNoFacturados() throws SNMPExceptions {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, IdCliente, FechaEntrega, Estado, IdEstadoPedido, SubTotal, "
                            + "IdDireccion, IdHorario, IdMedioDespacho from Pedido where Id not in "
                            + "(Select p.Id from Pedido as p inner join FacturaDetalle as f on p.Id = f.IdPedido)");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    pedidos.add(new Pedido(rs.getInt("Id"), rs.getBoolean("Estado"),
                            new ClienteDB().seleccionarPorId(rs.getString("IdCliente")), rs.getDate("FechaEntrega"),
                            new HorarioDB().seleccionarPorId(rs.getInt("IdHorario")),
                            new DireccionDB().seleccionarPorId(rs.getInt("IdDireccion")),
                            new PedidoDetalleDB().seleccionarDetallesPorPedido(rs.getInt("Id")), rs.getFloat("SubTotal"),
                            new EstadoPedidoDB().SeleccionarEstadoPedidoPorId(rs.getInt("IdEstadoPedido")),
                            new DespachoDB().seleccionarMedioDespachoPorId(rs.getInt("IdMedioDespacho"))));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return pedidos;
    }

    public List<Pedido> seleccionarNoFacturadosPorNombre(String nombre) throws SNMPExceptions {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select p.Id, p.IdCliente, p.FechaEntrega, p.Estado, "
                            + "p.IdEstadoPedido, p.SubTotal, p.IdDireccion, p.IdHorario, "
                            + "p.IdMedioDespacho from Pedido as p "
                            + "inner join Cliente as c on p.IdCliente = c.Id and c.NombreCompleto like ? "
                            + "and p.Id not in(Select p.Id from Pedido as p inner join FacturaDetalle as f on p.Id = f.IdPedido)");
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    pedidos.add(new Pedido(rs.getInt("Id"), rs.getBoolean("Estado"),
                            new ClienteDB().seleccionarPorId(rs.getString("IdCliente")), rs.getDate("FechaEntrega"),
                            new HorarioDB().seleccionarPorId(rs.getInt("IdHorario")),
                            new DireccionDB().seleccionarPorId(rs.getInt("IdDireccion")),
                            new PedidoDetalleDB().seleccionarDetallesPorPedido(rs.getInt("Id")), rs.getFloat("SubTotal"),
                            new EstadoPedidoDB().SeleccionarEstadoPedidoPorId(rs.getInt("IdEstadoPedido")),
                            new DespachoDB().seleccionarMedioDespachoPorId(rs.getInt("IdMedioDespacho"))));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return pedidos;
    }

    public List<Pedido> seleccionarFacturadosPorNombre(String nombre) throws SNMPExceptions {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select p.Id, p.IdCliente, p.FechaEntrega, p.Estado, "
                            + "p.IdEstadoPedido, p.SubTotal, p.IdDireccion, "
                            + "p.IdHorario, p.IdMedioDespacho from Pedido as p "
                            + "inner join Cliente as c on p.IdCliente = c.Id "
                            + "and c.NombreCompleto like ? "
                            + "inner join FacturaDetalle as f on p.Id = f.IdPedido "
                            + "and p.Id not in(Select IdPedido from Despacho)");
            ps.setString(1, "%" + nombre + "%");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    pedidos.add(new Pedido(rs.getInt("Id"), rs.getBoolean("Estado"),
                            new ClienteDB().seleccionarPorId(rs.getString("IdCliente")), rs.getDate("FechaEntrega"),
                            new HorarioDB().seleccionarPorId(rs.getInt("IdHorario")),
                            new DireccionDB().seleccionarPorId(rs.getInt("IdDireccion")),
                            new PedidoDetalleDB().seleccionarDetallesPorPedido(rs.getInt("Id")), rs.getFloat("SubTotal"),
                            new EstadoPedidoDB().SeleccionarEstadoPedidoPorId(rs.getInt("IdEstadoPedido")),
                            new DespachoDB().seleccionarMedioDespachoPorId(rs.getInt("IdMedioDespacho"))));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return pedidos;
    }

    public List<Pedido> seleccionarFacturados() throws SNMPExceptions {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select p.IdCliente, p.FechaEntrega, p.Estado, p.IdEstadoPedido, "
                            + "p.SubTotal, p.IdDireccion, p.IdHorario, p.Id, p.IdMedioDespacho from Pedido as p "
                            + "inner join FacturaDetalle as f on p.Id = f.IdPedido and p.id not in(Select IdPedido from Despacho);");
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    pedidos.add(new Pedido(rs.getInt("Id"), rs.getBoolean("Estado"),
                            new ClienteDB().seleccionarPorId(rs.getString("IdCliente")), rs.getDate("FechaEntrega"),
                            new HorarioDB().seleccionarPorId(rs.getInt("IdHorario")),
                            new DireccionDB().seleccionarPorId(rs.getInt("IdDireccion")), null,
                            rs.getFloat("SubTotal"),
                            new EstadoPedidoDB().SeleccionarEstadoPedidoPorId(rs.getInt("IdEstadoPedido")),
                            new MedioDespachoDB().seleccionarPorId(rs.getInt("IdMedioDespacho"))));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return pedidos;
    }

    public void modificarEstadoPedido(int idPedido, int idEstado) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Update Pedido set IdEstadoPedido = ? where id = ?");
            ps.setInt(1, idEstado);
            ps.setInt(2, idPedido);
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public List<Pedido> seleccionarPorRangoDeFechaYEstado(Date inicio, Date fin, int idEstadoPedido) throws SNMPExceptions {
        List<Pedido> pedidos = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select IdCliente, FechaEntrega, IdEstadoPedido, IdDireccion, IdHorario from Pedido "
                    + "where IdEstadoPedido = ? and FechaRegistro between ? and ?");
            ps.setInt(1, idEstadoPedido);
            ps.setTimestamp(2, new Timestamp(inicio.getTime()));
            ps.setTimestamp(3, new Timestamp(fin.getTime()));
            try ( ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    pedidos.add(new Pedido(0, true,
                            new ClienteDB().seleccionarPorId(rs.getString("IdCliente")), rs.getDate("FechaEntrega"),
                            new HorarioDB().seleccionarPorId(rs.getInt("IdHorario")),
                            new DireccionDB().seleccionarPorId(rs.getInt("IdDireccion")), null,
                            0, new EstadoPedidoDB().SeleccionarEstadoPedidoPorId(rs.getInt("IdEstadoPedido")), null));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return pedidos;
    }
}
