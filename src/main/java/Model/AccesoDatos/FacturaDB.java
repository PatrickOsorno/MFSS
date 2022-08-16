/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import Model.Entidades.Factura;
import Model.Entidades.FacturaDetalle;
import Model.Entidades.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.context.FacesContext;

/**
 *
 * @author Melisa
 */
public class FacturaDB {

    private AccesoDatos accesoDatos;
    private int idFactura = 0;

    public FacturaDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

    public void insertar(Factura factura) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Factura(IdTipoPago, CostoEnvio, Descuento, "
                            + "SubTotal, Total, Impuesto, Fecha, Estado, UsuarioRegistra) "
                            + "values(?, ?, ?, ?, ?, ?, getdate(), ?, ?)");
            ps.setInt(1, factura.getTipoPago().getId());
            ps.setFloat(2, factura.calcularCostoEnvio());
            ps.setFloat(3, factura.calcularDescuento());
            ps.setFloat(4, factura.calcularSubtotal());
            ps.setFloat(5, factura.calcularTotal());
            ps.setFloat(6, factura.calcularImpuesto());
            ps.setBoolean(7, factura.getEstado());
            ps.setString(8, ((Usuario) (FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario"))).getCorreo());
            accesoDatos.ejecutaSQL(ps);

            factura.getDetalle().forEach(det -> {
                try {
                    this.insertarDetalle(det);
                } catch (SNMPExceptions ex) {
                }
            });
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    private int obtenerIdFactura() throws SNMPExceptions {
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

    private void insertarDetalle(FacturaDetalle detalle) throws SNMPExceptions {
        if (idFactura == 0) {
            idFactura = obtenerIdFactura();
        }
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Insert into FacturaDetalle(IdFactura, IdPedido, Estado) values(?,?,?)");
            ps.setInt(1, idFactura);
            ps.setInt(2, detalle.getPedido().getId());
            ps.setBoolean(3, detalle.isEstado());
            accesoDatos.ejecutaSQL(ps);
            new PedidoDB().modificarEstadoPedido(detalle.getPedido().getId(), 2);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public Factura seleccionarPorPedido(int idPedido) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select f.Id, f.IdTipoPago, f.CostoEnvio, "
                            + "f.Descuento, f.Subtotal, f.Total, f.Impuesto, "
                            + "f.Fecha, f.Estado from Factura as f "
                            + "inner join FacturaDetalle as d on f.Id = d.idFactura "
                            + "and d.IdPedido = ?");
            ps.setInt(1, idPedido);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                if (rs.next()) {
                    return new Factura(rs.getInt("Id"), rs.getBoolean("Estado"),
                            new TipoPagoDB().seleccionarPorId(rs.getInt("IdTipoPago")), rs.getFloat("CostoEnvio"),
                            rs.getFloat("Descuento"), rs.getFloat("SubTotal"), rs.getFloat("Total"), rs.getFloat("Impuesto"), rs.getDate("Fecha"), null);
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }

    public List<Factura> seleccionarPorRangoDeFechaYTipoPago(Date inicio, Date Fin, int idTipoPago) throws SNMPExceptions {
        List<Factura> facturas = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Id, IdTipoPago, Total, Fecha from Factura where IdTipoPago = ? and Fecha between ? and ?");
            ps.setInt(1, idTipoPago);
            ps.setTimestamp(2, new Timestamp(inicio.getTime()));
            ps.setTimestamp(3, new Timestamp(Fin.getTime()));
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    facturas.add(new Factura(rs.getInt("Id"), true,
                            new TipoPagoDB().seleccionarPorId(rs.getInt("IdTipoPago")), 0,
                            0, 0, rs.getFloat("Total"), 0, rs.getDate("Fecha"), null));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return facturas;
    }
}
