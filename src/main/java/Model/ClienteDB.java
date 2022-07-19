/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.AccesoDatos;
import java.util.List;

/**
 *
 * @author Melisa
 */
public class ClienteDB {
    private AccesoDatos accesoDatos;
    
    public ClienteDB(){
        accesoDatos = AccesoDatos.obtenerInstancia();
    }
    
    public List<Cliente> seleccionarNoAceptados(){
        return null;
    }
    
    public void insertar(Cliente cliente){
        
    }
    
    public void eliminar(int idCliente){
        
    }
}
