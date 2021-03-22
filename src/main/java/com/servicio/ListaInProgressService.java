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
public class ListaInProgressService {
    
    public Collection<Cuaderno> getlista(){
        return bd.getListaInProgress();
    }
    
    public void agregarTareaProgress(Cuaderno cuaderno) throws DBException{
        try {
            bd.addTareaListaInProgress(cuaderno);
        } catch (DBException ex) {
            throw new DBException("No se pudo agregar la tarea " + ex.getMessage());
        }
    }
    
    public void modificarTareaProgress(int id, Cuaderno cuaderno) throws DBException{
        try {
            bd.modifyTareaInProgress(id, cuaderno);
        } catch (DBException ex) {
           throw new DBException("No se pudo modificar la tarea.");
        }
    }
    
}
