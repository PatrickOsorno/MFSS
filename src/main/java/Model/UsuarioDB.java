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
    public UsuarioDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
    public Usuario obtenerUsuarioPorCredenciales(String correo, String contrasena) throws SNMPExceptions{
        Usuario usuario = null;
        try {
            PreparedStatement ps = accesoDatos.getConexion().prepareStatement("Select Email, Contrasena from Usuario where Email = ? and Contrasena = ?");
            ps.setString(1, correo);
            ps.setString(2, contrasena);
            ResultSet rs = accesoDatos.ejecutaSQLRetornaRS(ps);
            if(rs.next()){
                usuario = new Usuario();
                usuario.setCorreo(rs.getString("Email"));
                usuario.setContrasena(rs.getString("Contrasena"));
            }
        } catch (SQLException e) {
             throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION, e.getMessage());
        }
        return usuario;
    }
}
