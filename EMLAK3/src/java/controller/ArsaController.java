
package controller;

import dbutil.ArsaDbUtil;
import entity.Arsa;
import entity.Ilan;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped


public class ArsaController {
    private List<Arsa> konutList;
    private ArsaDbUtil arsaDbUtil;
    private Arsa arsa;
    private Logger logger = Logger.getLogger(getClass().getName());
    
    private Ilan ilantest;
    
    //satılık arsa
    public String GetArsaList(int id){
        try {
            konutList= arsaDbUtil.getArsaSatilik(id);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "arsa_satilik?faces-redirect=true";
    }
    //kiralık arsa
    public String GetArsaListKiralik(int id){
        try {
            konutList= arsaDbUtil.getArsaKiralik(id);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "arsa_kiralik?faces-redirect=true";
    }

    public List<Arsa> getKonutList() {
        return konutList;
    }

    public void setKonutList(List<Arsa> konutList) {
        this.konutList = konutList;
    }

    public ArsaDbUtil getArsaDbUtil() {
        return arsaDbUtil;
    }

    public void setArsaDbUtil(ArsaDbUtil arsaDbUtil) {
        this.arsaDbUtil = arsaDbUtil;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    public ArsaController() throws Exception {
        arsa=new Arsa();
        arsaDbUtil = arsaDbUtil.getInstance();
        ilantest=Ilan.ilanInstance;
        konutList=new ArrayList<>();
    }
    
    public String addArsa(){
        try{
            arsaDbUtil.addArsa(arsa);
        }catch(Exception exc){
           exc.printStackTrace();
           addErrorMessage(exc);
        }
       
       return "index?faces-redirect=true"; 
    }

    public Arsa getArsa() {
        return arsa;
    }

    public void setArsa(Arsa arsa) {
        this.arsa = arsa;
    }
    
    

    public Ilan getIlantest() {
        return ilantest;
    }

    public void setIlantest(Ilan ilantest) {
        this.ilantest = ilantest;
    }
    
     private void addErrorMessage(Exception exc) {
        FacesMessage message = new FacesMessage("Error: " + exc.getMessage());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
