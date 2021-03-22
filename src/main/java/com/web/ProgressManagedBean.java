/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
import com.servicio.ListaInProgressService;
import com.servicio.ListaToDoService;
import com.servicio.bd;
import com.sun.faces.util.MessageFactory;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Manuel
 */
@Named(value = "PMB")
@RequestScoped
public class ProgressManagedBean {

    public ProgressManagedBean() {
        refrescarListasTareasProgress();
    }
   
    private static Logger log = Logger.getLogger("GestorDeListasManagedBean");
    private Collection<Cuaderno> listaInProgress;
    private Cuaderno cuadernoInProgress;
    
    private ListaInProgressService service = new ListaInProgressService();
    
//    @PostConstruct
//    public void iniciarlizarMB(){
//        refrescarListasTareasToDo();
//    }
    
    private void refrescarListasTareasProgress(){
        try {
           this.listaInProgress = service.getlista();
       } catch (Exception ex) {
           log.severe("Error refrescar lista tareas" + ex.getMessage());
           FacesContext fc = FacesContext.getCurrentInstance();
           fc.addMessage(null, MessageFactory.getMessage("tareas_listar_error", ex.getMessage()));
           this.listaInProgress = new ArrayList();
       }
    }

    public Collection<Cuaderno> getLista() {
        return listaInProgress;
    }

    public void setLista(Collection<Cuaderno> lista) {
        this.listaInProgress = lista;
    }

    public Cuaderno getCuadernoInProgress() {
        return cuadernoInProgress;
    }

    public void setCuadernoInProgress(Cuaderno cuaderno) {
        this.cuadernoInProgress = cuaderno;
    }

    public String switchCuadernoEstadoToDo(int caso) throws DBException{
        try {
            bd.switchTareaToDo(caso, this.cuadernoInProgress.getTarea().getIdTarea(), this.cuadernoInProgress);
            refrescarListasTareasProgress();
            return "tarea?faces-redirect=true";
        } catch (DBException ex) {
            throw new DBException("Error no cambio la tarea. Revisa");
        }
    }
    
    public String switchCuadernoEstadoDone(int caso) throws DBException{
        try {
            //listo
            bd.switchTareaDone(caso, this.cuadernoInProgress.getTarea().getIdTarea(), this.cuadernoInProgress);
            refrescarListasTareasProgress();
            return "tarea?faces-redirect=true";
        } catch (DBException ex) {
            throw new DBException("Error no cambio la tarea. Revisa");
        }
    }
    
    public void verTareaSel(){
        System.out.println("... seleccionó " + this.cuadernoInProgress);
    }
    
    public String deleteTaskInProgress(){
        bd.deleteToDo(this.cuadernoInProgress.getTarea().getIdTarea());
        refrescarListasTareasProgress();
        return "tarea?faces-redirect=true";
    }
    
}
