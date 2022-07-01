/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Patrick Osorno
 */
public class solicitudesBean {

    DualListModel<String> listaUsuarios;

    @PostConstruct
    public void iniciar() {
        List<String> userSource = new ArrayList<>();
        List<String> userTarget = new ArrayList<>();
        userSource.add("Patrick");
        userSource.add("Mauricio");
        listaUsuarios = new DualListModel<>(userSource, userTarget);
    }

    public DualListModel<String> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(DualListModel<String> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

}
