/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.modal;


public class Cuaderno {
    private int id;
    private Tarea t;

    public Cuaderno(int id, Tarea t) {
        this.t = t;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public Tarea getTarea() {
        return t;
    }

    public void setTarea(Tarea t) {
        this.t = t;
    }
    
    public String getTareaDescripcion(){
        return t.getDescripcion();
    }
}
