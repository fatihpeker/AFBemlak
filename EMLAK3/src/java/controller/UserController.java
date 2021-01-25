
package controller;

import dbutil.UserDbUtil;
import entity.User;
import java.sql.Connection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class UserController {
    private UserDbUtil userDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    private User user;
    Connection conn;
    
    public UserController() throws Exception {
        userDbUtil = UserDbUtil.getInstance();
        user=new User();
    }
    
    public String addUser(User theUser){
        logger.info("Adding user: " + theUser);
        try{
            //add user to the db
            userDbUtil.addUser(theUser);
            User.userInstance= theUser;
            User.uyeMi= true;
        }catch(Exception exc){
            // send this to server logs
            logger.log(Level.SEVERE, "Error adding user", exc);
			
            // add error message for JSF page
            addErrorMessage(exc);

            return null;
        }
        return "index?faces-redirect=true";
    }
   
    public String Login(){
        boolean deger= false;
        try {
            User.userInstance= user;
            deger= userDbUtil.CheckUser(conn, user); 
            User.uyeMi= true;
        } catch(Exception exc){
             logger.log(Level.SEVERE, "Error login", exc);
			
            // add error message for JSF page
            addErrorMessage(exc);

           //return null;
        }
        if(deger){
            return "index?faces-redirect=true";
        }
        return "login?faces-redirect=true";
    }
    
    public String ilanControl(){
        if(User.uyeMi){
            return "ilan?faces-redirect=true";
        }
        return "signup?faces-redirect=true";
    }
    
    private void addErrorMessage(Exception exc) {
        FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
}
