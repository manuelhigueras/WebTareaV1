/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.web;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
import com.modal.Tarea;
import com.modal.Usuario;
import com.servicio.ListaDoneService;
import com.servicio.ListaInProgressService;
import com.servicio.ListaToDoService;
import com.servicio.bd;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
@Named(value = "altaTareaMB")
@SessionScoped
public class AltaTareaManagedBean implements Serializable {

    private Tarea tarea;
    private String descripcion;
    private String estado;
    
    public AltaTareaManagedBean() {
        this.tarea = new Tarea();
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    private int IdTarea(){
        Collection<Usuario> lista = bd.getUsuarios();
        int id = -1;
        for(Usuario user: lista){
            if(user.getAct() == true){
                id = user.getId();
                break;
            }
        }
        return id;
    }
    
    public String addTarea(String descripcion, String estado) throws DBException{
        int id = IdTarea();
        //System.out.println("Descripcion:" + descripcion + "/estado:" + estado);
        if(estado.equals("TO DO")){
            Cuaderno cuaderno = new Cuaderno(bd.getContToDo(),new Tarea(bd.getContToDo(), descripcion, estado, id));
            ListaToDoService service = new ListaToDoService();
            try {
                service.agregarTareaToDo(cuaderno);
            } catch (DBException ex) {
                throw new DBException("No se ha podido agregar");
            }
        } 
        if(estado.equals("IN PROGRESS")){
            Cuaderno cuaderno = new Cuaderno(bd.getContInProgress(),new Tarea(bd.getContInProgress(), descripcion, estado, id));
            ListaInProgressService service = new ListaInProgressService();
            try{
                service.agregarTareaProgress(cuaderno);
            }
            catch (DBException ex){
                throw new DBException("No se ha podido agregar");
            }
        }
        if(estado.equals("DONE")){
            Cuaderno cuaderno = new Cuaderno(bd.getContDone(),new Tarea(bd.getContDone(), descripcion, estado, id));
            ListaDoneService service = new ListaDoneService();
            try{
                service.addTareaDone(cuaderno);
            }
            catch (DBException ex){
                throw new DBException("No se ha podido agregar");
            }
        }
        return "tarea";
    }
    
}
