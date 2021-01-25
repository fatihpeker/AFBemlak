
package entity;

import java.util.ArrayList;
import javax.faces.bean.ManagedBean;

@ManagedBean

public class Isyeri {
    private int id;
    private int ilanId;
    private String odaSayisi;
    private int binaYasi;
    private int binaKat;
    private int binaKacinciKat;
    
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

    public Isyeri(){
        
    }
    
    public Isyeri(int id, int ilanId, String odaSayisi, int binaYasi, int binaKat, int binaKacinciKat) {
        this.id= id;
        this.ilanId = ilanId;
        this.odaSayisi = odaSayisi;
        this.binaYasi = binaYasi;
        this.binaKat = binaKat;
        this.binaKacinciKat = binaKacinciKat;
    }

    public Isyeri(int id, int ilanId, String odaSayisi, int binaYasi, int binaKat, int binaKacinciKat, ArrayList<String> resimPath, int iilanId, String ilanBaslik, int ilanFiyat, String kategoriAd, String islemAd, int userId, String ilanAdres, String ilanAciklama) {
        this.id = id;
        this.ilanId = ilanId;
        this.odaSayisi = odaSayisi;
        this.binaYasi = binaYasi;
        this.binaKat = binaKat;
        this.binaKacinciKat = binaKacinciKat;
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

    public String getOdaSayisi() {
        return odaSayisi;
    }

    public void setOdaSayisi(String odaSayisi) {
        this.odaSayisi = odaSayisi;
    }

    public int getBinaYasi() {
        return binaYasi;
    }

    public void setBinaYasi(int binaYasi) {
        this.binaYasi = binaYasi;
    }

    public int getBinaKat() {
        return binaKat;
    }

    public void setBinaKat(int binaKat) {
        this.binaKat = binaKat;
    }

    public int getBinaKacinciKat() {
        return binaKacinciKat;
    }

    public void setBinaKacinciKat(int binaKacinciKat) {
        this.binaKacinciKat = binaKacinciKat;
    }
    
    
}

