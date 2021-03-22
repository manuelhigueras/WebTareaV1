/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class ListaToDoService {
    public Collection<Cuaderno> getlista(){
        return bd.getListaToDo();
    }
    
    public void cambiarEstadoInProgress(int caso, int id, Cuaderno c) throws DBException{
        try{
            bd.switchTareaInProgress(caso, id, c);
        }
        catch(DBException ex){
            throw new DBException("No se pudo agregar");
        }
    }
    
    public void agregarTareaToDo(Cuaderno cuaderno) throws DBException{
        try {
            bd.addTareaListaToDo(cuaderno);
        } catch (DBException ex) {
            throw new DBException("No se pudo crear la tarea " + ex.getMessage());
        }
    }
    
    public void modificarTareaToDo(int id, Cuaderno cuaderno) throws DBException{
        try {
            bd.modifyTareaToDo(id, cuaderno);
        } catch (DBException ex) {
            throw new DBException("No se pudo crear la tarea " + ex.getMessage());
        }        
    }
    
}
