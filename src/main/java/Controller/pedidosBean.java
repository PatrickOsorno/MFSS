/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;

/**
 *
 * @author Patrick Osorno
 */
public class pedidosBean {

    List<String> prods;

    @PostConstruct
    public void iniciar() {
        prods = new ArrayList<>();
        prods.add("Hola");
        prods.add("Mundo");
    }

    public List<String> getProds() {
        return prods;
    }

    public void setProds(List<String> prods) {
        this.prods = prods;
    }

}
