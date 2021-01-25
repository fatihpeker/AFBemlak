
package dbutil;

import entity.Ilan;
import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class IlanDbUtil {
    
    private static IlanDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/afb_emlak";
    public static int test;
    
    public static IlanDbUtil getInstance() throws Exception {
	if (instance == null) {
            instance = new IlanDbUtil();
	}
		
	return instance;
    }
	
    public IlanDbUtil() throws Exception {		
	dataSource = getDataSource();
    }
    
    public DataSource getDataSource() throws NamingException {
	Context context = new InitialContext();
		
	DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
	return theDataSource;
    }
    //satılık konut listesi
    public List<Ilan> getIlans() throws Exception {

		List<Ilan> ilan = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                        //konut.konut_id, konut.ilan_id, konut.koda_sayisi, " 
                          //      + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama, konut.koda_sayisi, konut.k_bina_kacinci_kat "+ 
                                     "FROM ilan \n" +
                                     "LEFT JOIN konut ON ilan.ilan_id = konut.ilan_id \n" +
                                     "WHERE kategori_ad='Konut' AND islem_ad='Satilik'";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int ilanId = myRs.getInt("ilan.ilan_id");
				String ilanBaslik = myRs.getString("ilan_baslik");
				int ilanFiyat = myRs.getInt("ilan_fiyat");
				String kategoriAd = myRs.getString("kategori_ad");
                                String islemAd= myRs.getString("islem_ad");
                                int userId= myRs.getInt("user_id");
                                String ilanAdres= myRs.getString("ilan_adres");
                                String ilanAciklama= myRs.getString("ilan_aciklama");
                                String odaSayisi= myRs.getString("koda_sayisi");
                                int binaKacinciKat= myRs.getInt("k_bina_kacinci_kat");
				// create newilan object
				Ilan tempIlan = new Ilan(ilanId, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId,
						ilanAdres, ilanAciklama, odaSayisi, binaKacinciKat);

				// add it to the list of ilan
				ilan.add(tempIlan);
			}
			
			return ilan;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
    //kiralık konut listesi
    public List<Ilan> getIlansKiralıkKonut() throws Exception {

		List<Ilan> ilan = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                        //konut.konut_id, konut.ilan_id, konut.koda_sayisi, " 
                          //      + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama, konut.koda_sayisi, konut.k_bina_kacinci_kat "+ 
                                     "FROM ilan \n" +
                                     "LEFT JOIN konut ON ilan.ilan_id = konut.ilan_id \n" +
                                     "WHERE kategori_ad='Konut' AND islem_ad='Kiralik'";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int ilanId = myRs.getInt("ilan.ilan_id");
				String ilanBaslik = myRs.getString("ilan_baslik");
				int ilanFiyat = myRs.getInt("ilan_fiyat");
				String kategoriAd = myRs.getString("kategori_ad");
                                String islemAd= myRs.getString("islem_ad");
                                int userId= myRs.getInt("user_id");
                                String ilanAdres= myRs.getString("ilan_adres");
                                String ilanAciklama= myRs.getString("ilan_aciklama");
                                String odaSayisi= myRs.getString("koda_sayisi");
                                int binaKacinciKat= myRs.getInt("k_bina_kacinci_kat");
				// create newilan object
				Ilan tempIlan = new Ilan(ilanId, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId,
						ilanAdres, ilanAciklama, odaSayisi, binaKacinciKat);

				// add it to the list of ilan
				ilan.add(tempIlan);
			}
			
			return ilan;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
    //satılık isyeri listesi
    public List<Ilan> getIlansSatilikIsyeri() throws Exception {

		List<Ilan> ilan = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                        //konut.konut_id, konut.ilan_id, konut.koda_sayisi, " 
                          //      + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama, isyeri.ioda_sayisi, isyeri.i_bina_kacinci_kat "+ 
                                     "FROM ilan \n" +
                                     "LEFT JOIN isyeri ON ilan.ilan_id = isyeri.ilan_id \n" +
                                     "WHERE kategori_ad='Isyeri' AND islem_ad='Satilik'";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int ilanId = myRs.getInt("ilan.ilan_id");
				String ilanBaslik = myRs.getString("ilan_baslik");
				int ilanFiyat = myRs.getInt("ilan_fiyat");
				String kategoriAd = myRs.getString("kategori_ad");
                                String islemAd= myRs.getString("islem_ad");
                                int userId= myRs.getInt("user_id");
                                String ilanAdres= myRs.getString("ilan_adres");
                                String ilanAciklama= myRs.getString("ilan_aciklama");
                                String odaSayisi= myRs.getString("ioda_sayisi");
                                int binaKacinciKat= myRs.getInt("i_bina_kacinci_kat");
				// create newilan object
				Ilan tempIlan = new Ilan(ilanId, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId,
						ilanAdres, ilanAciklama, odaSayisi, binaKacinciKat);

				// add it to the list of ilan
				ilan.add(tempIlan);
			}
			
			return ilan;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
    //kiralık isyeri listesi
    public List<Ilan> getIlansKiralikIsyeri() throws Exception {

		List<Ilan> ilan = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                        //konut.konut_id, konut.ilan_id, konut.koda_sayisi, " 
                          //      + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama, isyeri.ioda_sayisi, isyeri.i_bina_kacinci_kat "+ 
                                     "FROM ilan \n" +
                                     "LEFT JOIN isyeri ON ilan.ilan_id = isyeri.ilan_id \n" +
                                     "WHERE kategori_ad='Isyeri' AND islem_ad='Kiralik'";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int ilanId = myRs.getInt("ilan.ilan_id");
				String ilanBaslik = myRs.getString("ilan_baslik");
				int ilanFiyat = myRs.getInt("ilan_fiyat");
				String kategoriAd = myRs.getString("kategori_ad");
                                String islemAd= myRs.getString("islem_ad");
                                int userId= myRs.getInt("user_id");
                                String ilanAdres= myRs.getString("ilan_adres");
                                String ilanAciklama= myRs.getString("ilan_aciklama");
                                String odaSayisi= myRs.getString("ioda_sayisi");
                                int binaKacinciKat= myRs.getInt("i_bina_kacinci_kat");
				// create newilan object
				Ilan tempIlan = new Ilan(ilanId, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId,
						ilanAdres, ilanAciklama, odaSayisi, binaKacinciKat);

				// add it to the list of ilan
				ilan.add(tempIlan);
			}
			
			return ilan;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
    //satılık arsa listesi
    public List<Ilan> getIlansSatilikArsa() throws Exception {

		List<Ilan> ilan = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                        //konut.konut_id, konut.ilan_id, konut.koda_sayisi, " 
                          //      + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama,arsa.metrekare,arsa.su "+ 
                                     "FROM ilan \n" +
                                     "LEFT JOIN arsa ON ilan.ilan_id = arsa.ilan_id \n" +
                                     "WHERE kategori_ad='Arsa' AND islem_ad='Satilik'";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int ilanId = myRs.getInt("ilan.ilan_id");
				String ilanBaslik = myRs.getString("ilan_baslik");
				int ilanFiyat = myRs.getInt("ilan_fiyat");
				String kategoriAd = myRs.getString("kategori_ad");
                                String islemAd= myRs.getString("islem_ad");
                                int userId= myRs.getInt("user_id");
                                String ilanAdres= myRs.getString("ilan_adres");
                                String ilanAciklama= myRs.getString("ilan_aciklama");
                                String odaSayisi= myRs.getString("su");
                                int binaKacinciKat= myRs.getInt("metrekare");
				// create newilan object
				Ilan tempIlan = new Ilan(ilanId, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId,
						ilanAdres, ilanAciklama, odaSayisi, binaKacinciKat);

				// add it to the list of ilan
				ilan.add(tempIlan);
			}
			
			return ilan;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
    //kiralık arsa listesi
    public List<Ilan> getIlansKiralikArsa() throws Exception {

		List<Ilan> ilan = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                        //konut.konut_id, konut.ilan_id, konut.koda_sayisi, " 
                          //      + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama,arsa.metrekare,arsa.su "+ 
                                     "FROM ilan \n" +
                                     "LEFT JOIN arsa ON ilan.ilan_id = arsa.ilan_id \n" +
                                     "WHERE kategori_ad='Arsa' AND islem_ad='Kiralik'";

			myStmt = myConn.createStatement();

			myRs = myStmt.executeQuery(sql);

			// process result set
			while (myRs.next()) {
				
				// retrieve data from result set row
				int ilanId = myRs.getInt("ilan.ilan_id");
				String ilanBaslik = myRs.getString("ilan_baslik");
				int ilanFiyat = myRs.getInt("ilan_fiyat");
				String kategoriAd = myRs.getString("kategori_ad");
                                String islemAd= myRs.getString("islem_ad");
                                int userId= myRs.getInt("user_id");
                                String ilanAdres= myRs.getString("ilan_adres");
                                String ilanAciklama= myRs.getString("ilan_aciklama");
                                String odaSayisi= myRs.getString("su");
                                int binaKacinciKat= myRs.getInt("metrekare");
				// create newilan object
				Ilan tempIlan = new Ilan(ilanId, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId,
						ilanAdres, ilanAciklama, odaSayisi, binaKacinciKat);

				// add it to the list of ilan
				ilan.add(tempIlan);
			}
			
			return ilan;		
		}
		finally {
			close (myConn, myStmt, myRs);
		}
	}
    
    public void addIlan(Ilan ilan) throws Exception{
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
        try{
            myConn= getConnection();
            String sql= "insert into ilan (ilan_baslik, ilan_fiyat, kategori_ad, islem_ad, user_id, ilan_adres, ilan_aciklama) values (?, ?, ?, ?, ?, ?, ?)";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setString(1, ilan.getIlanBaslik());
            myStmt.setInt(2, ilan.getIlanFiyat());
            myStmt.setString(3, ilan.getKategoriAd());
            myStmt.setString(4, ilan.getIslemAd());
            myStmt.setInt(5, User.userInstance.getId());
            myStmt.setString(6, ilan.getIlanAdres());
            myStmt.setString(7, ilan.getIlanAciklama());
            myStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        ilan.setIlanId(GetLastIlanId());
        Ilan.ilanInstance=ilan;
    }
    
    public int GetLastIlanId()
   {
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
        try{
            myConn= getConnection();
            String sql= "select max(ilan_id) as ilan_id from ilan where user_id=?";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setInt(1, User.userInstance.getId());
           
            ResultSet rs=myStmt.executeQuery();
            if(rs.next()){
                test= rs.getInt("ilan_id");
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return test;
    }
    
    
    
    public Connection getConnection() throws Exception {
	Connection theConn = dataSource.getConnection();
		
	return theConn;
    }
	
    public void close(Connection theConn, Statement theStmt) {
        close(theConn, theStmt, null);
    }
	
    public void close(Connection theConn, Statement theStmt, ResultSet theRs) {

        try {
            if (theRs != null) {
                theRs.close();
            }

            if (theStmt != null) {
                theStmt.close();
            }

            if (theConn != null) {
                theConn.close();
            }
			
            } catch (Exception exc) {
		exc.printStackTrace();
            }
    }
}
