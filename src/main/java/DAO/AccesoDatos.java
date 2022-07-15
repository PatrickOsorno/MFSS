/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patrick Osorno
 */
public class AccesoDatos {

    private final String driver, servidor, usuarioBd, contrasenaBd;
    private Statement st = null;
    private Connection conexion = null;

    private static AccesoDatos instancia = null;

    private AccesoDatos() {
        ExternalContext contexto = FacesContext.getCurrentInstance().getExternalContext();
        driver = contexto.getInitParameter("DRIVER-JDBC");
        servidor = contexto.getInitParameter("SERVIDOR-BD");
        usuarioBd = contexto.getInitParameter("USUARIO-BD");
        contrasenaBd = contexto.getInitParameter("CONTRASENA-BD");
        try {
            conexion = conectar();
        } catch (SNMPExceptions ex) {
            Logger.getLogger(AccesoDatos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static AccesoDatos obtenerInstancia() {
        if (instancia == null) {
            instancia = new AccesoDatos();
        }
        return instancia;
    }

    private Connection conectar() throws SNMPExceptions {
        try {
            Class.forName(driver);
            return DriverManager.getConnection(servidor, usuarioBd, contrasenaBd);
        } catch (ClassNotFoundException | SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage());
        }
    }

    public Connection getConexion() {
        return conexion;
    }

//    public void ejectaSQL(String Sql) throws SNMPExceptions {
//        try {
//            st = conectar().createStatement();
//            st.execute(Sql);
//        } catch (SQLException e) {
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
//                    e.getMessage(), e.getErrorCode());
//        }
//    }
//
//    public ResultSet ejecutaSQLRetornaRS(String Sql) throws SNMPExceptions {
//        try {
//            st = conectar().createStatement();
//            return st.executeQuery(Sql);
//        } catch (SQLException e) {
//            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
//                    e.getMessage(), e.getErrorCode());
//        }
//    }

    public void ejectaSQL(PreparedStatement ps) throws SNMPExceptions {
        try {
            ps.execute();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        }
    }

    public ResultSet ejecutaSQLRetornaRS(PreparedStatement ps) throws SNMPExceptions {
        try {
            return ps.executeQuery();
        } catch (SQLException e) {
            throw new SNMPExceptions(SNMPExceptions.SQL_EXCEPTION,
                    e.getMessage(), e.getErrorCode());
        }
    }
}
