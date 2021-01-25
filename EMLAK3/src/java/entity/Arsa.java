
package entity;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean
public class Arsa {
    private int id;
    private int ilanId;
    private int metreKare;
    private String kanalizasyon;
    private String su;
    private String telefon;
    private String elektrik;
    private String dogalgaz;
    
    //overload için
    private ArrayList<String> resimPath;
    private int iilanId;
    private String ilanBaslik;
    private int ilanFiyat;
    private String kategoriAd;
    private String islemAd;
    private int userId;
    private String ilanAdres;
    private String ilanAciklama;

    public Arsa() {
        
    }
    
    
    
    

    public Arsa(int id, int ilanId, int metreKare, String kanalizasyon, String su, String telefon, String elektrik, String dogalgaz) {
        this.id = id;
        this.ilanId = ilanId;
        this.metreKare = metreKare;
        this.kanalizasyon = kanalizasyon;
        this.su = su;
        this.telefon = telefon;
        this.elektrik = elektrik;
        this.dogalgaz = dogalgaz;
    }

    public Arsa(int id, int ilanId, int metreKare, String kanalizasyon, String su, String telefon, String elektrik, String dogalgaz, ArrayList<String> resimPath, int iilanId, String ilanBaslik, int ilanFiyat, String kategoriAd, String islemAd, int userId, String ilanAdres, String ilanAciklama) {
        this.id = id;
        this.ilanId = ilanId;
        this.metreKare = metreKare;
        this.kanalizasyon = kanalizasyon;
        this.su = su;
        this.telefon = telefon;
        this.elektrik = elektrik;
        this.dogalgaz = dogalgaz;
        this.resimPath = resimPath;
        this.iilanId = iilanId;
        this.ilanBaslik = ilanBaslik;
        this.ilanFiyat = ilanFiyat;
        this.kategoriAd = kategoriAd;
        this.islemAd = islemAd;
        this.userId = userId;
        this.ilanAdres = ilanAdres;
        this.ilanAciklama = ilanAciklama;
    }

    public ArrayList<String> getResimPath() {
        return resimPath;
    }

    public void setResimPath(ArrayList<String> resimPath) {
        this.resimPath = resimPath;
    }

    public int getIilanId() {
        return iilanId;
    }

    public void setIilanId(int iilanId) {
        this.iilanId = iilanId;
    }

    public String getIlanBaslik() {
        return ilanBaslik;
    }

    public void setIlanBaslik(String ilanBaslik) {
        this.ilanBaslik = ilanBaslik;
    }

    public int getIlanFiyat() {
        return ilanFiyat;
    }

    public void setIlanFiyat(int ilanFiyat) {
        this.ilanFiyat = ilanFiyat;
    }

    public String getKategoriAd() {
        return kategoriAd;
    }

    public void setKategoriAd(String kategoriAd) {
        this.kategoriAd = kategoriAd;
    }

    public String getIslemAd() {
        return islemAd;
    }

    public void setIslemAd(String islemAd) {
        this.islemAd = islemAd;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIlanAdres() {
        return ilanAdres;
    }

    public void setIlanAdres(String ilanAdres) {
        this.ilanAdres = ilanAdres;
    }

    public String getIlanAciklama() {
        return ilanAciklama;
    }

    public void setIlanAciklama(String ilanAciklama) {
        this.ilanAciklama = ilanAciklama;
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIlanId() {
        return ilanId;
    }

    public void setIlanId(int ilanId) {
        this.ilanId = ilanId;
    }

    public int getMetreKare() {
        return metreKare;
    }

    public void setMetreKare(int metreKare) {
        this.metreKare = metreKare;
    }

    public String getKanalizasyon() {
        return kanalizasyon;
    }

    public void setKanalizasyon(String kanalizasyon) {
        this.kanalizasyon = kanalizasyon;
    }

    public String getSu() {
        return su;
    }

    public void setSu(String su) {
        this.su = su;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public String getElektrik() {
        return elektrik;
    }

    public void setElektrik(String elektrik) {
        this.elektrik = elektrik;
    }

    public String getDogalgaz() {
        return dogalgaz;
    }

    public void setDogalgaz(String dogalgaz) {
        this.dogalgaz = dogalgaz;
    }
    
    
}
