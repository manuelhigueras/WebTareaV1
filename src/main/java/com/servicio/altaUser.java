/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.domain.exceptions.DBException;
import com.modal.Usuario;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel
 */
public class altaUser {
    public void altaUserForm(Usuario u) throws DBException{
        try {
            bd.addUsuario(u);
        } catch (DBException ex) {
            throw new DBException("No se pudo agregar el usuario"); 
        }
    }
    public Collection<Usuario> refrescar(){
        return bd.getUsuarios();
    }
}
