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
public class ListaDoneService {
    public Collection<Cuaderno> getlista(){
        return bd.getListaDone();
    }
    public void addTareaDone(Cuaderno cuaderno) throws DBException{
        try {
            bd.addTareaListaDone(cuaderno);
        } catch (DBException ex) {
            throw new DBException("No se pudo crear la tarea "+ ex.getMessage());
        }
    }
    
    public void modificarTareaDone(int id, Cuaderno cuaderno) throws DBException{
        try{
            bd.modifyTareaDone(id, cuaderno);
        }
        catch (DBException ex){
            throw new DBException("No se pudo crear la tarea "+ ex.getMessage());
        }
    }
    
    
}
