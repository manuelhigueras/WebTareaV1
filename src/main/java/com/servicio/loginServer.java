/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.domain.exceptions.LoginException;
import com.modal.Usuario;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class loginServer {

    public void login(String email, String clave, HttpSession sesion) throws LoginException {
        //En bd ver si existe y coincide email y clave
        Collection<Usuario> usuarios = bd.getUsuarios();
        Usuario usrEncontrado = null;
        for(Usuario u: usuarios){
           if(u.getEmail().equals(email)){
               usrEncontrado = u;
               break;
           }
        }
        //si existe añadir a sesion
        //sino lanzo exception
        if(usrEncontrado == null){
            throw new LoginException("El usuario no existe. Debe registrarse.");
        }
        else{
            if(usrEncontrado.getPassword().equals(clave)){
                sesion.setAttribute("usuario", usrEncontrado);
                usrEncontrado.setAct(true);
            }
            else{
                throw new LoginException("Clave no valida");
            }
        }  
    }
    
    public void logout(HttpSession sesion){
        Collection<Usuario> lista = bd.getUsuarios();
        for(Usuario user: lista){
            if(user.getAct())
                user.setAct(false);
        }
        sesion.invalidate();
    }
    
}
