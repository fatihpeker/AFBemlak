
package controller;

import dbutil.IsyeriDbUtil;
import entity.Ilan;
import entity.Isyeri;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped

public class IsyeriController {
    private List<Isyeri> konutList;
    private IsyeriDbUtil isyeriDbUtil;
    private Logger logger = Logger.getLogger(getClass().getName());
    private Isyeri isyeri;
    
    private Ilan ilantest;
    
    //satılık isyeri
    public String GetIsyeriList(int id){
        try {
            konutList= isyeriDbUtil.getIsyeriSatilik(id);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "isyeri_satilik?faces-redirect=true";
    }
    //kiralık isyeri
    public String GetIsyeriListKiralik(int id){
        try {
            konutList= isyeriDbUtil.getIsyeriKiralik(id);
           
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "isyeri_kiralik?faces-redirect=true";
    }

    public List<Isyeri> getKonutList() {
        return konutList;
    }

    public void setKonutList(List<Isyeri> konutList) {
        this.konutList = konutList;
    }

    public IsyeriDbUtil getIsyeriDbUtil() {
        return isyeriDbUtil;
    }

    public void setIsyeriDbUtil(IsyeriDbUtil isyeriDbUtil) {
        this.isyeriDbUtil = isyeriDbUtil;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }
    
    
    public IsyeriController() throws Exception {
        isyeri=new Isyeri();
        isyeriDbUtil = isyeriDbUtil.getInstance();
        ilantest=Ilan.ilanInstance;
        konutList=new ArrayList<>();
    }
    
    public String addIsyeri(){
        try{
            isyeriDbUtil.addIsYeri(isyeri);
        }catch(Exception exc){
           exc.printStackTrace();
           addErrorMessage(exc);
        }
       
       return "index?faces-redirect=true"; 
    }

    public Isyeri getIsyeri() {
        return isyeri;
    }

    public void setIsyeri(Isyeri isyeri) {
        this.isyeri = isyeri;
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
