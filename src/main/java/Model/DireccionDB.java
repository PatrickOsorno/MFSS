/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Melisa
 */
public class DireccionDB {

    private AccesoDatos accesoDatos;

    public DireccionDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

    public void insertar(Direccion direccion) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Direccion(IdCliente, IdProvincia, IdCanton, IdDistrito, IdBarrio, OtrasSenas, IdTipoDireccion, Estado) "
                            + "values(?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, direccion.getCliente().getId());
            ps.setInt(2, direccion.getProvincia().getId());
            ps.setInt(3, direccion.getCanton().getId());
            ps.setInt(4, direccion.getDistrito().getId());
            ps.setInt(5, direccion.getBarrio().getId());
            ps.setString(6, direccion.getOtrasSenas());
            ps.setInt(7, direccion.getTipo().getId());
            ps.setBoolean(8, direccion.getEstado());
            accesoDatos.ejectaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
