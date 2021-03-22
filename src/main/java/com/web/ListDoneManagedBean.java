/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
import com.servicio.ListaDoneService;
import com.servicio.bd;
import com.sun.faces.util.MessageFactory;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author user
 */
@Named(value = "done")
@ViewScoped
public class ListDoneManagedBean implements Serializable {

    private static Logger log = Logger.getLogger("ListDoneManagedBean");
    private Collection<Cuaderno> listaDone;
    private Cuaderno cuadernoDone;
    private ListaDoneService service = new ListaDoneService();
    
    public ListDoneManagedBean() {
        refrescarListasTareasDone();
    }    
    
    private void refrescarListasTareasDone(){
        try {
           this.listaDone = service.getlista();
       } catch (Exception ex) {
           log.severe("Error refrescar lista tareas" + ex.getMessage());
           FacesContext fc = FacesContext.getCurrentInstance();
           fc.addMessage(null, MessageFactory.getMessage("tareas_listar_error", ex.getMessage()));
           this.listaDone = new ArrayList();
       }
    }

    public Collection<Cuaderno> getLista() {
        return listaDone;
    }

    public void setLista(Collection<Cuaderno> lista) {
        this.listaDone = lista;
    }

    public Collection<Cuaderno> getListaDone() {
        return listaDone;
    }

    public void setListaDone(Collection<Cuaderno> listaDone) {
        this.listaDone = listaDone;
    }

    public Cuaderno getCuadernoDone() {
        return cuadernoDone;
    }

    public void setCuadernoDone(Cuaderno cuadernoDone) {
        this.cuadernoDone = cuadernoDone;
    }
    
    public void verTareaSel(){
        System.out.println("... seleccionó " + this.cuadernoDone);
    }
    
    public String switchCuadernoEstadoToDo(int caso) throws DBException{
        try{
            bd.switchTareaToDo(caso, this.cuadernoDone.getTarea().getIdTarea(), cuadernoDone);
            refrescarListasTareasDone();
            return "tarea?faces-redirect=true";
        }
        catch(DBException ex){
            throw new DBException("Error no cambio la tarea. Revisa");
        }
    }
    
    public String switchCuadernoEstadoInProgress(int caso) throws DBException{
        try{
            bd.switchTareaInProgress(caso, this.cuadernoDone.getTarea().getIdTarea(), cuadernoDone);
            refrescarListasTareasDone();
            return "tarea?faces-redirect=true";
        }
        catch(DBException ex){
            throw new DBException("Error no cambio la tarea. Revisa");
        }
    }
    
    public String deleteTaskDone(){
        bd.deleteToDo(this.cuadernoDone.getTarea().getIdTarea());
        refrescarListasTareasDone();
        return "tarea?faces-redirect=true";
    }
    
}
