/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
import com.modal.Tarea;
import com.servicio.ListaToDoService;
import com.servicio.bd;
import java.io.Serializable;
import java.util.Collection;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "ToDo")
@ViewScoped
public class ListToDoManagedBean implements Serializable{
    
    private Collection<Cuaderno> lista;
    private ListaToDoService service = new ListaToDoService();
    private int idSelect;
    private Cuaderno cuadernoSelect;
    private int caso;
    
    public ListToDoManagedBean() {
    }    

    @PostConstruct
    public void inicializar(){
        System.out.println("............. inicializando ListToDoManagedBean");
        this.lista= service.getlista();
    }    
    
    public Collection<Cuaderno> getLista() {
        return lista;
    }
      
    public void setLista(Collection<Cuaderno> lista) {
        this.lista = lista;
    }

    public Cuaderno getCuadernoSelect() {
        return cuadernoSelect;
    }

    public void setCuadernoSelect(Cuaderno c) {
        this.cuadernoSelect = cuadernoSelect;
    }

    public int getIdSelect() {
        return idSelect;
    }

    public void setIdSelect(int idSelect) {
        this.idSelect = idSelect;
    }

    public int getCaso() {
        return caso;
    }

    public void setCaso(int caso) {
        this.caso = caso;
    }
    
    public void cambioEstadoInProgress(int id) throws DBException{
        System.out.println("Id: " + id);    
    }
    
}
