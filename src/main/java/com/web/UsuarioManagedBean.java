package com.web;
import com.domain.exceptions.DBException;
import com.domain.exceptions.LoginException;
import com.modal.Usuario;
import com.servicio.altaUser;
import com.servicio.bd;
import com.servicio.loginServer;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

@Named(value = "usuario")
@SessionScoped
public class UsuarioManagedBean implements Serializable {

    private String email; 
    private String clave;
    private Collection<Usuario> userList;
    private Usuario user;
    private String nombre;
    
    private loginServer loginService = new loginServer();
    private altaUser altaService = new altaUser();

    public UsuarioManagedBean() {
        this.userList = bd.getUsuarios();
        this.user = new Usuario();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Usuario getUser() {
        return user;
    }

    public void setUser(Usuario user) {
        this.user = user;
    }
    
    public Usuario getUserNombre() {
        Usuario userReturn = null;
        userList = bd.getUsuarios();
        for(Usuario user: userList){
            if(email.equals(user.getEmail())){
                userReturn = user;
                break;
            }
        }
        return userReturn;
    }

    public String getNombre() {
        Usuario userName = getUserNombre();
        nombre = userName.getNombre();
        return nombre;
    }
    
    public String login() {
        // BD - ir a buscar usuario y claeveç
        System.out.printf("login %s   y clave %s", email, clave);

        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);

        try {
            loginService.login(email, clave, sesion);
            ctx.addMessage(null,new FacesMessage("Welcome " + email));            
            return "tarea";
        } catch (LoginException ex) {
            Logger.getLogger(UsuarioManagedBean.class.getName()).log(Level.SEVERE, null, ex);
            email = "";
            clave = "";

            //mensaje de error
            FacesMessage msg = new FacesMessage(ex.getMessage());
            //ctx.addMessage("formLogin:pwd", msg);
            ctx.addMessage(null, msg);
            return "login";
        }

    }

    public String logout() {
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) ctx.getExternalContext().getSession(true);
        loginService.logout(sesion);
        return "login?faces-redirect=true";
    }

    public String altaUser(){
        
        try {
            //grabar en bd
            altaService.altaUserForm(user);
            //refrescar
                 
            return "login";
        } catch (DBException ex) {
             System.out.println("... no grabo genro " + ex.getMessage());
             return "altaUser";
        }
    }    
    
}
