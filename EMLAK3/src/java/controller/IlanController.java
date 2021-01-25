
package controller;

import dbutil.IlanDbUtil;
import entity.Ilan;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class IlanController {
    private List<Ilan> ilanList;
    private IlanDbUtil ilanDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    private Ilan ilan;
    

    public Ilan getIlan() {
        return ilan;
    }

    public void setIlan(Ilan ilan) {
        this.ilan = ilan;
    }

    //satılık konut ilan list
    public List<Ilan> getIlanList() {
        try {
            ilanList= ilanDbUtil.getIlans();
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return ilanList;
    }
    //kiralik konut ilan list
    public List<Ilan> getIlanListKiraKonut() {
        try {
            ilanList= ilanDbUtil.getIlansKiralıkKonut();
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return ilanList;
    }
    //satılık isyeri ilan list
    public List<Ilan> getIlanListIsyeri() {
        try {
            ilanList= ilanDbUtil.getIlansSatilikIsyeri();
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return ilanList;
    }
    //kiralık isyeri ilan list
    public List<Ilan> getIlanListKiraIsyeri() {
        try {
            ilanList= ilanDbUtil.getIlansKiralikIsyeri();
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return ilanList;
    }
    //satılık arsa ilan list
    public List<Ilan> getIlanListArsa() {
        try {
            ilanList= ilanDbUtil.getIlansSatilikArsa();
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return ilanList;
    }
    //kiralık arsa ilan list
    public List<Ilan> getIlanListKiraArsa() {
        try {
            ilanList= ilanDbUtil.getIlansKiralikArsa();
        } catch (Exception ex) {
             ex.printStackTrace();
        }
        return ilanList;
    }

    public void setIlanList(List<Ilan> ilanList) {
        this.ilanList = ilanList;
    }
    
    
    
    public IlanController() throws Exception {
        ilanDbUtil = IlanDbUtil.getInstance();
        ilan= new Ilan();
        ilanList= new ArrayList<>();
    }
    
     public String addIlan(){
        logger.info("Adding ilan: " + ilan);
        try{
            ilanDbUtil.addIlan(ilan);
        }catch(Exception exc){
          exc.printStackTrace();
            return null;
        }
        if(ilan.getKategoriAd().equals("Konut")){
            return "konut?faces-redirect=true";
        }else if(ilan.getKategoriAd().equals("Isyeri")){
            return "isyeri?faces-redirect=true";
        }else{
            return "arsa?faces-redirect=true";
        }
        
    }
    
    
     
       
     
    private void addErrorMessage(Exception exc) {
        FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
