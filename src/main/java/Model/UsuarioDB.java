/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import DAO.SNMPExceptions;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Melisa
 */
public class UsuarioDB {

    private AccesoDatos accesoDatos;

    public UsuarioDB() {
        accesoDatos = AccesoDatos.obtenerInstancia();
    }

//    Por medio de este método se hace una consulta en la base de datos sobre todos los los atributos del usuario por medio del correo y contraseña
    public Usuario seleccionarUsuarioPorCredenciales(String correo, String contrasena) throws SNMPExceptions {
        Usuario usuario = null;
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Email, Contrasena from Usuario where Email = ? and Contrasena = ?");
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if (rs.next()) {
                usuario = new Usuario();
                usuario.setCorreo(rs.getString("Email"));
                usuario.setContrasena(rs.getString("Contrasena"));
            }
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return usuario;
    }

//    Por medio de este método se hace un insert en la base de datos de la tabla Usuario con todos los atributos
    public void insertar(Usuario usuario) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion()
                    .prepareStatement("Insert into Usuario(Email, Contrasena, FechaRegistro, FechaUltimaEdicion, Estado, IdCliente) values(?, ?, getdate(), getdate(), ?, ?)");
            ps.setString(1, usuario.getCorreo());
            ps.setString(2, usuario.getContrasena());
            ps.setBoolean(3, usuario.getEstado());
            ps.setString(4, usuario.getCliente().getId());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

//    Por medio de este método se hace un insert en la base de datos de la tabla Usuario con todos los atributos para agregar el rol
    public void insertarRolUsuario(Usuario usuario) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Insert into UsuarioRoles(Email, IdRol, IdCliente) values(?, ?, ?)");
            ps.setString(1, usuario.getCorreo());
            ps.setInt(2, usuario.getRol().getId());
            ps.setString(3, usuario.getCliente().getId());
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }

    public void cambiarContrasena(String usuario, String contrasena) throws SNMPExceptions {
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Update Usuario set Contrasena = ?, FechaUltimaEdicion = getdate() where Email = ?");
            ps.setString(1, contrasena);
            ps.setString(2, usuario);
            accesoDatos.ejecutaSQL(ps);
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
    }
}
