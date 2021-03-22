/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.modal.Cuaderno;
import com.servicio.ListaInProgressService;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "progress")
@ViewScoped
public class ListInProgressManagedBean implements Serializable{

    private Collection<Cuaderno> lista;
    private ListaInProgressService service = new ListaInProgressService();
    
    public ListInProgressManagedBean() {
    }
    
    @PostConstruct
    public void inicializar(){
        System.out.println("............. inicializando  ListInProgressManagedBean");
        this.lista= service.getlista();
    }    
    
    public Collection<Cuaderno> getLista() {
        return lista;
    }
      
    public void setLista(Collection<Cuaderno> lista) {
        this.lista = lista;
    }
}
