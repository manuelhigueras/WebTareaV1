package com.modal;

public class Tarea {
    
    private Integer idTarea;
    private String descripcion;
    private String estado;
    private Integer idUser;

    public Tarea(Integer idTarea, String descripcion, String estado, Integer idUser) {
        this.idTarea = idTarea;
        this.descripcion = descripcion;
        this.estado = estado;
        this.idUser = idUser;
    }

    public Tarea() {
       
    }

    public int getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(int idTarea) {
        this.idTarea = idTarea;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser() {
        this.idUser++;
    }
    
    @Override
    public String toString() {
        return "Tarea{" + "idTarea=" + idTarea + ", descripcion=" + descripcion + ", estado=" + estado + '}';
    }

}
