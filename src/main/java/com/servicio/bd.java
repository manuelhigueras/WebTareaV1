/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servicio;

import com.domain.exceptions.DBException;
import com.modal.Cuaderno;
import com.modal.Tarea;
import com.modal.Usuario;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author user
 */
public class bd {
    
    private static Map<Integer,Usuario> usuarios;
    private static Integer contUser = 1;
    private static Map<Integer, Cuaderno> listaToDo;
    private static Integer contToDo = 1;
    private static Map<Integer, Cuaderno> listaInProgress;
    private static Integer contInProgress = 1;
    private static Map<Integer, Cuaderno> listaDone;
    private static Integer contDone = 1;    
       
    static{
        usuarios = new HashMap<Integer,Usuario>();
        listaToDo = new HashMap<Integer, Cuaderno>();
        listaInProgress = new HashMap<Integer, Cuaderno>();
        listaDone = new HashMap<Integer, Cuaderno>();
        
        try{
            usuarios.put(getContUser(),new Usuario(getContUser(),"manuel@gmail.com", "1234", "Manuel", "Xitzag"));
                       
            addTareaListaToDo(new Cuaderno(getContUser(),new Tarea(getContToDo(), "diseñar el modal", "To Do", getContUser())));
            addTareaListaToDo(new Cuaderno(getContUser(),new Tarea(getContToDo(), "diseñar el servicio", "To Do", getContUser())));  
            addTareaListaToDo(new Cuaderno(getContUser(),new Tarea(getContToDo(), "diseñar el wui", "To Do", getContUser())));        
            addTareaListaToDo(new Cuaderno(getContUser(),new Tarea(getContToDo(), "diseñar el exception", "To Do", getContUser())));

            addTareaListaInProgress(new Cuaderno(getContUser(),new Tarea(getContInProgress(), "Survival", "In Progress", getContUser())));
            addTareaListaInProgress(new Cuaderno(getContUser(),new Tarea(getContInProgress(), "Agregar el mostrar y add", "In Progress",getContUser())));  
            addTareaListaInProgress(new Cuaderno(getContUser(),new Tarea(getContInProgress(), "Agregar modificar y eliminar", "In Progress", getContUser())));        
            addTareaListaInProgress(new Cuaderno(getContUser(),new Tarea(getContInProgress(), "Chequear el jsp", "In Progress", getContUser())));

            addTareaListaDone(new Cuaderno(getContUser(), new Tarea(getContDone(), "Formato html", "Done", getContUser())));
            addTareaListaDone(new Cuaderno(getContUser(), new Tarea(getContDone(), "Servicio html", "Done", getContUser())));  
            addTareaListaDone(new Cuaderno(getContUser(), new Tarea(getContDone(), "Modal", "Done", getContUser())));        
            addTareaListaDone(new Cuaderno(getContUser(), new Tarea(getContDone(), "Exception", "Done", getContUser())));  
            
            setContUser();
        }
        catch(DBException ex){
            
        }
    
    }
    
    private bd(){}
    
/////////////////////////////////////////////////////////////////////////
    
    public static synchronized void addTareaListaToDo(Cuaderno c) throws DBException{
        if (listaToDo.containsKey(listaToDo.get(c.getTarea().getIdTarea()))) {
            throw new DBException("El libro ya existe con el id " + listaToDo.get(c.getTarea().getIdTarea()));
        }
        listaToDo.put(getContToDo(), c);
        setContToDo();
    }

    public static synchronized void addTareaListaDone(Cuaderno c) throws DBException{
        if (listaDone.containsKey(listaDone.get(c.getTarea().getIdTarea()))) {
            throw new DBException("El cuaderno ya existe con el id " + listaDone.get(c.getTarea().getIdTarea()));
        }        
        listaDone.put(getContDone(), c);
        setContDone();
    }
    
    public static synchronized void addTareaListaInProgress(Cuaderno c) throws DBException{
        if (listaInProgress.containsKey(listaInProgress.get(c.getTarea().getIdTarea()))) {
            throw new DBException("El cuaderno ya existe con el id " + listaInProgress.get(c.getTarea().getIdTarea()));
        }        
        listaInProgress.put(getContInProgress(), c);
        setContInProgress();
    }

    public static Set listaApuntadorGeneralToDo(){
        Set<Entry<Integer,Cuaderno>> listaAux = listaToDo.entrySet();
        return listaAux;
    }
    
    public static Set listaApuntadorGeneralDone(){
        Set<Entry<Integer,Cuaderno>> listaAux = listaDone.entrySet();
        return listaAux;
    }
    
    public static Set listaApuntadorGeneralInProgress(){
        Set<Entry<Integer,Cuaderno>> listaAux = listaInProgress.entrySet();
        return listaAux;
    }
    
    public static synchronized void switchTareaToDo(int caso, int id, Cuaderno c) throws DBException{
        if(caso == 1){
            listaInProgress.remove(id);
            setContInProgressRest();
            c.getTarea().setIdTarea(getContToDo());
            c.getTarea().setEstado("To Do");
            addTareaListaToDo(c);
        }
        else{
            if(caso == 2){
                listaDone.remove(id);
                setContDoneRest();
                c.getTarea().setIdTarea(getContToDo());
                c.getTarea().setEstado("To Do");
                addTareaListaToDo(c);
            }
        }
    }
    
    public static synchronized void switchTareaInProgress(int caso, int id, Cuaderno c) throws DBException{
        if(caso == 1){
            listaToDo.remove(id);
            setContToDoRest();
            c.getTarea().setIdTarea(getContInProgress());
            c.getTarea().setEstado("In Progress");
            addTareaListaInProgress(c);
        }
        else{
            if(caso == 2){
               listaDone.remove(id);
               setContDoneRest();
               c.getTarea().setIdTarea(getContInProgress());
               c.getTarea().setEstado("In Progress");
               addTareaListaInProgress(c);             
            }
        }
    }

    public static synchronized void switchTareaDone(int caso, int id, Cuaderno c) throws DBException{
        if(caso == 1){
            listaToDo.remove(id);
            setContToDoRest();
            c.getTarea().setIdTarea(getContDone());
            c.getTarea().setEstado("Done");
            addTareaListaDone(c);
        }
        else{
            if(caso == 2){
                listaInProgress.remove(id);
                setContInProgressRest();
                c.getTarea().setIdTarea(getContDone());
                c.getTarea().setEstado("Done");
                addTareaListaDone(c);               
            }
        }
    }    
 
/*TESTED*/    
    public static void main(String[] args) {
//        Set<Entry<Integer,Cuaderno>> listaAux = listaToDo.entrySet();
//        Object a,b,ca;
//        a = null;
//        b = null;
//        ca = null;
//        Cuaderno t = null;
//        String a2 = null;
//        for(Entry c: listaAux){
//            t = (Cuaderno) c.getValue();
//            a = t.getTarea().getEstado();
//            System.out.println(c.getKey() + "/// " + a);
//        }
        for(Cuaderno c: getListaToDo()){
            System.out.println(c.getTarea().toString());
        }
        
        Cuaderno t = listaToDo.get(1);
        try {
            switchTareaDone(1, listaToDo.get(1).getId(), t);
        } catch (DBException ex) {
            System.out.println("NO SE HIZO NADA");
        }
        
        for(Cuaderno c: getListaDone()){
            System.out.println(c.getTarea().toString());
        }
    }
       
//////////////////////////////////////////////////////////////////////////////
    
    public static Map<Integer, Cuaderno> getListaToDoMap() {
        return listaToDo;
    }   
    
    public static synchronized Collection<Cuaderno> getListaToDo() {
        return listaToDo.values();
    }   
    
    public static synchronized Collection<Cuaderno> getListaInProgress() {
        return listaInProgress.values();
    }   
    
    public static synchronized Collection<Cuaderno> getListaDone() {
        return listaDone.values();
    }   
    
    public static void setContToDo(){
        contToDo++;
    }
    
    public static void setContToDoRest(){
        contToDo--;
    }    

    public static Integer getContToDo() {
        return contToDo;
    }

    public static void setContInProgress(){
        contInProgress++;
    }
    
    public static void setContInProgressRest(){
        contInProgress--;
    }        
    
    public static Integer getContInProgress() {
        return contInProgress;
    }

    public static Integer getContDone() {
        return contDone;
    }

    public static void setContDone() {
        contDone++;
    }

    public static void setContDoneRest() {
        contDone--;
    }    

    public static Integer getContUser() {
        return contUser;
    }

    public static void setContUser() {
        contUser++;
    }
        
    public synchronized static Collection<Usuario> getUsuarios() {
        return usuarios.values();
    }
    
    public synchronized static void addUsuario(Usuario u) throws DBException {
        try{
            if (usuarios.containsValue(u)) {
                throw new DBException("El libro ya existe con ese id");
            }
            setContUser();
            usuarios.put(contUser, u);
        }catch(Exception ex){
            throw new DBException("Error: no se pudo agregar, " + ex.getMessage());
        }
    }

    public synchronized static void modifyTareaToDo(int id, Cuaderno c) throws DBException {
        try{
            listaToDo.replace(id, c);
        }
        catch(Exception ex){
            throw new DBException(ex.getMessage());
        }
    }

    public synchronized static void modifyTareaInProgress(int id, Cuaderno c) throws DBException {
        try{
            listaInProgress.replace(id, c);
        }
        catch(Exception ex){
            throw new DBException(ex.getMessage());
        }
    }

    public synchronized static void modifyTareaDone(int id, Cuaderno c) throws DBException {
        try{
            listaDone.replace(id, c);
        }
        catch(Exception ex){
            throw new DBException(ex.getMessage());
        }
    }    
    
    public static synchronized void deleteToDo(int id){
        listaToDo.remove(id);
    }
    
    public static synchronized void deleteInProgress(int id){
        listaInProgress.remove(id);
    }
    
    public static synchronized void deleteDone(int id){
        listaDone.remove(id);
    }
    
}
