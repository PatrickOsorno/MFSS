/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.usuario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

/**
 *
 * @author Patrick Osorno
 */
public class principalBean {
    
    public void verificarSesion(){
//        usuario us = (usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
//        if(us == null){
//            try {
//                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
//            } catch (IOException ex) {
//                //Logger.getLogger(principalBean.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
    
    public String cerrarSesion(){
//        FacesContext contexto = FacesContext.getCurrentInstance();
//        contexto.getExternalContext().invalidateSession();
       return "index?faces-redirect=true";
    }
}
