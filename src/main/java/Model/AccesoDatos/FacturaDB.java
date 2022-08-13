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

//            factura.getDetalle().forEach(det -> {
//                try {
//                    this.insertarDetalle(det);
//                } catch (SNMPExceptions ex) {
//                }
//            });

            for (FacturaDetalle facturaDetalle : factura.getDetalle()) {
                this.insertarDetalle(facturaDetalle);
            }

        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    private int obtenerIdFactura() throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select @@IDENTITY as Id");
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {
                return rs.getInt("Id");
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
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
