
package controller;

import dbutil.KonutDbUtil;
import entity.Ilan;
import entity.Konut;
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

public class KonutController {
    private List<Konut> konutList;
    private KonutDbUtil konutDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    private Konut konut;
    
    private Ilan ilantest;
   

   
    //satılık konut
    public String GetKonutList(int id){
        try {
            konutList= konutDbUtil.getkonutSatilik(id);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "konut_satilik?faces-redirect=true";
    }
    //kiralık konut
    public String GetKonutListKiralik(int id){
        try {
            konutList= konutDbUtil.getkonutKiralik(id);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "konut_kiralik?faces-redirect=true";
    }
    public List<Konut> getKonutList() {
        return konutList;
    }

    public void setKonutList(List<Konut> konutList) {
        this.konutList = konutList;
    }
   
    public Ilan getIlantest() {
        return ilantest;
    }

    public void setIlantest(Ilan ilantest) {
        this.ilantest = ilantest;
    }
    
    
    public Konut getKonut() {
        return konut;
    }

    public void setKonut(Konut konut) {
        this.konut = konut;
    }
    
    public KonutController() throws Exception {
        konut=new Konut();
        konutDbUtil = KonutDbUtil.getInstance();
        ilantest=Ilan.ilanInstance;
        konutList= new ArrayList<>();
    }
    
     public String addKonut(){
        try{
            konutDbUtil.addKonut(konut);
        }catch(Exception exc){
           exc.printStackTrace();
        }
       
       return "index?faces-redirect=true"; 
    }
     
    /*public String satılıkKonut(){
        return "konut_satilik";
    }*/
     
    private void addErrorMessage(Exception exc) {
        FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
