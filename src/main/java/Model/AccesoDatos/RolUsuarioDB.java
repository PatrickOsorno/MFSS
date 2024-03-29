/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.AccesoDatos;

import Model.Entidades.RolUsuario;
import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class RolUsuarioDB {

    private AccesoDatos accesoDatos;

    public RolUsuarioDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del Rol de usuario
    public List<RolUsuario> SeleccionarTodo() throws SNMPExceptions {
        List<RolUsuario> roles = new ArrayList<>();
        try {
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(accesoDatos.getConexion()
                    .prepareStatement("Select Id, Estado, Descripcion from RolUsuario"))) {
                while (rs.next()) {
                    roles.add(new RolUsuario(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return roles;
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del Rol de usuario por medio del correo
    public List<RolUsuario> seleccionarPorCorreo(String correo) throws SNMPExceptions {
        List<RolUsuario> roles = new ArrayList<>();
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("select Id, Descripcion from RolUsuario r inner join UsuarioRoles u on r.Id = u.idRol and u.email = ?;");
            ps.setString(1, correo);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                while (rs.next()) {
                    roles.add(new RolUsuario(rs.getInt("Id"), true, rs.getString("Descripcion")));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return roles;
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del Rol de usuario por medio del ID
    public RolUsuario seleccionarRolPorId(int IdRol) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Select Id, Estado, Descripcion from RolUsuario where Id = ?;");
            ps.setInt(1, IdRol);
            try (ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps)) {
                if (rs.next()) {
                    return new RolUsuario(rs.getInt("Id"), rs.getBoolean("Estado"), rs.getString("Descripcion"));
                }
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return null;
    }
}
