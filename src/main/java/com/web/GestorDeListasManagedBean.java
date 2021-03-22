/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
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
 * @author user
 */
@Named(value = "GLMB")
@RequestScoped
public class GestorDeListasManagedBean {

    private static Logger log = Logger.getLogger("GestorDeListasManagedBean");
    private Collection<Cuaderno> listaToDo;
    private Cuaderno cuadernoToDo;
    
    private ListaToDoService service = new ListaToDoService();
    
//    @PostConstruct
//    public void iniciarlizarMB(){
//        refrescarListasTareasToDo();
//    }
    
    public GestorDeListasManagedBean() {
        refrescarListasTareasToDo();
    }
    
    private void refrescarListasTareasToDo(){
        try {
           this.listaToDo = service.getlista();
       } catch (Exception ex) {
           log.severe("Error refrescar lista tareas" + ex.getMessage());
           FacesContext fc = FacesContext.getCurrentInstance();
           fc.addMessage(null, MessageFactory.getMessage("tareas_listar_error", ex.getMessage()));
           this.listaToDo = new ArrayList();
       }
    }

    public Collection<Cuaderno> getLista() {
        return listaToDo;
    }

    public void setLista(Collection<Cuaderno> lista) {
        this.listaToDo = lista;
    }

    public Cuaderno getCuaderno() {
        return cuadernoToDo;
    }

    public void setCuaderno(Cuaderno cuaderno) {
        this.cuadernoToDo = cuaderno;
    }

    public Collection<Cuaderno> getListaToDo() {
        return listaToDo;
    }

    public void setListaToDo(Collection<Cuaderno> listaToDo) {
        this.listaToDo = listaToDo;
    }

    public Cuaderno getCuadernoToDo() {
        return cuadernoToDo;
    }

    public void setCuadernoToDo(Cuaderno cuadernoToDo) {
        this.cuadernoToDo = cuadernoToDo;
    }

    public String switchCuadernoEstadoInProgress(int caso) throws DBException{
        try {
            bd.switchTareaInProgress(caso, this.cuadernoToDo.getTarea().getIdTarea(), this.cuadernoToDo);
            refrescarListasTareasToDo();
            return "tarea?faces-redirect=true";
        } catch (DBException ex) {
            throw new DBException("Error no cambio la tarea. Revisa");
        }
    }
    
    public String switchCuadernoEstadoDone(int caso) throws DBException{
        try {
            //listo
            bd.switchTareaDone(caso, this.cuadernoToDo.getTarea().getIdTarea(), this.cuadernoToDo);
            refrescarListasTareasToDo();
            return "tarea?faces-redirect=true";
        } catch (DBException ex) {
            throw new DBException("Error no cambio la tarea. Revisa");
        }
    }
    
    public String deleteTaskToDo(){
        bd.deleteToDo(this.cuadernoToDo.getTarea().getIdTarea());
        refrescarListasTareasToDo();
        return "tarea?faces-redirect=true";
    }
    
    public void verTareaSel(){
        System.out.println("... seleccionó " + this.cuadernoToDo);
    }
 
}
