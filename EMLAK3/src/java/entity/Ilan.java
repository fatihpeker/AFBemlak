
package entity;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Ilan {
    private int ilanId;

    public int getIlanId() {
        return ilanId;
    }

    public void setIlanId(int ilanId) {
        this.ilanId = ilanId;
    }
    private String ilanBaslik;
    private int ilanFiyat;
    private String kategoriAd;
    private String islemAd;
    private int userId;
    private String ilanAdres;
    private String ilanAciklama;
    
    //satılık konut sayfası için ilana 2 özellik ekleniyor
    private String odaSayisi;
    private int binaKacinciKat;
    
    public static Ilan ilanInstance;
    
    public Ilan(int ilanID, String ilanBaslik, int ilanFiyat, String kategoriAd, String islemAd, int userId, String ilanAdres, String ilanAciklama) {
        this.ilanId= ilanID;
        this.ilanBaslik = ilanBaslik;
        this.ilanFiyat = ilanFiyat;
        this.kategoriAd = kategoriAd;
        this.islemAd = islemAd;
        this.userId = userId;
        this.ilanAdres = ilanAdres;
        this.ilanAciklama = ilanAciklama;
    }
    
    public Ilan(){
        
    }
    
    //listeleme için overload edilmil constructor
    public Ilan(int ilanId, String ilanBaslik, int ilanFiyat, String kategoriAd, String islemAd, int userId, String ilanAdres, String ilanAciklama, String odaSayisi, int binaKacinciKat) {
        this.ilanId = ilanId;
        this.ilanBaslik = ilanBaslik;
        this.ilanFiyat = ilanFiyat;
        this.kategoriAd = kategoriAd;
        this.islemAd = islemAd;
        this.userId = userId;
        this.ilanAdres = ilanAdres;
        this.ilanAciklama = ilanAciklama;
        this.odaSayisi = odaSayisi;
        this.binaKacinciKat = binaKacinciKat;
    }
    
    

    public String getOdaSayisi() {
        return odaSayisi;
    }

    public void setOdaSayisi(String odaSayisi) {
        this.odaSayisi = odaSayisi;
    }

    public int getBinaKacinciKat() {
        return binaKacinciKat;
    }

    public void setBinaKacinciKat(int binaKacinciKat) {
        this.binaKacinciKat = binaKacinciKat;
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
  
    
}
