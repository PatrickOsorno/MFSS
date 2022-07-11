/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.Usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patrick Osorno
 */
public class principalBean {
    
//    Verifica el rol del inicio de sesion
    public void verificarSesion(){
//        Usuario us = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("Usuario");
//        if(us == null){
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
//            } catch (IOException ex) {
//                //Logger.getLogger(principalBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    
//    Se devuelve al inicio de sesion
    public String cerrarSesion(){
//        FacesContext contexto = FacesContext.getCurrentInstance();
//        contexto.getExternalContext().invalidateSession();
       return "index?faces-redirect=true";
    }
}
