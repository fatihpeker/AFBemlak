
package dbutil;

import entity.Ilan;
import entity.Konut;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class KonutDbUtil {
    private static KonutDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/afb_emlak";
    
    public static KonutDbUtil getInstance() throws Exception {
	if (instance == null) {
            instance = new KonutDbUtil();
	}
		
	return instance;
    }
	
    public KonutDbUtil() throws Exception {		
	dataSource = getDataSource();
    }
    
    public DataSource getDataSource() throws NamingException {
	Context context = new InitialContext();
		
	DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
	return theDataSource;
    }
    
      public void addKonut(Konut konut) throws Exception{
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
        try{
            myConn= getConnection();
            String sql= "insert into konut (ilan_id, koda_sayisi, kbina_yasi, k_bina_kat, k_bina_kacinci_kat) values (?, ?, ?, ?, ?)";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setInt(1, Ilan.ilanInstance.getIlanId());
            myStmt.setString(2, konut.getOdaSayisi());
            myStmt.setInt(3, konut.getBinaYasi());
            myStmt.setInt(4, konut.getBinaKat());
            myStmt.setInt(5, konut.getBinaKacinciKat());
            myStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
      
    //satılık konut  
    public List<Konut> getkonutSatilik(int id) throws Exception{
        List<Konut> konut_satilik = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                          
                               // + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama, konut.konut_id, konut.ilan_id, konut.koda_sayisi, "+
                                  "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat " +  
                                     "FROM ilan \n" +
                                     "LEFT JOIN konut ON ilan.ilan_id = konut.ilan_id \n" +
                                     "WHERE kategori_ad='Konut' AND islem_ad='Satilik' AND ilan.ilan_id="+id+";";

			myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql);
                        
                        int konutId = 0;
                        int ilanID=0;
                        String odaSayisi=null;
                        int binaYasi=0;
                        int binaKat=0;
                        int binaKacinciKat=0;
                        int iilanID=0;
			String ilanBaslik=null;
			int ilanFiyat=0 ;
			String kategoriAd=null ;
                        String islemAd=null;
                        int userId=0;
                        String ilanAdres=null;
                        String ilanAciklama=null;
                                
			 ArrayList<String> resimPathArrayList=new ArrayList<>();

			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				konutId = myRs.getInt("konut_id");
				ilanID = myRs.getInt("ilan_id");
                                odaSayisi= myRs.getString("koda_sayisi");
                                binaYasi= myRs.getInt("kbina_yasi");
                                binaKat= myRs.getInt("k_bina_kat");
                                binaKacinciKat= myRs.getInt("k_bina_kacinci_kat");
                                iilanID = myRs.getInt("ilan_id");
				ilanBaslik = myRs.getString("ilan_baslik");
				ilanFiyat = myRs.getInt("ilan_fiyat");
				kategoriAd = myRs.getString("kategori_ad");
                                islemAd= myRs.getString("islem_ad");
                                userId= myRs.getInt("user_id");
                                ilanAdres= myRs.getString("ilan_adres");
                                ilanAciklama= myRs.getString("ilan_aciklama");	
                        }
                       
                        String sql2="SELECT resim_ad FROM resim WHERE ilan_id="+id+"; ";
                        myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql2);
                        while (myRs.next()) {
                            String resimAd=myRs.getString("resim_ad");
                            resimPathArrayList.add(resimAd);
                    }
                        Konut tempKonut = new Konut(konutId, ilanID, odaSayisi, binaYasi, binaKat, binaKacinciKat,
						resimPathArrayList, iilanID, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId, ilanAdres, ilanAciklama);

			
			konut_satilik.add(tempKonut);
                        return konut_satilik;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
                
    }
    //kiralık konut
    public List<Konut> getkonutKiralik(int id) throws Exception{
        List<Konut> konut_satilik = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                          
                               // + "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat" +
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama, konut.konut_id, konut.ilan_id, konut.koda_sayisi, "+
                                  "konut.kbina_yasi, konut.k_bina_kat, konut.k_bina_kacinci_kat " +  
                                     "FROM ilan \n" +
                                     "LEFT JOIN konut ON ilan.ilan_id = konut.ilan_id \n" +
                                     "WHERE kategori_ad='Konut' AND islem_ad='Kiralik' AND ilan.ilan_id="+id+";";

			myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql);
                        
                        int konutId = 0;
                        int ilanID=0;
                        String odaSayisi=null;
                        int binaYasi=0;
                        int binaKat=0;
                        int binaKacinciKat=0;
                        int iilanID=0;
			String ilanBaslik=null;
			int ilanFiyat=0 ;
			String kategoriAd=null ;
                        String islemAd=null;
                        int userId=0;
                        String ilanAdres=null;
                        String ilanAciklama=null;
                                
			 ArrayList<String> resimPathArrayList=new ArrayList<>();

			// process result set
			while (myRs.next()) {
				// retrieve data from result set row
				konutId = myRs.getInt("konut_id");
				ilanID = myRs.getInt("ilan_id");
                                odaSayisi= myRs.getString("koda_sayisi");
                                binaYasi= myRs.getInt("kbina_yasi");
                                binaKat= myRs.getInt("k_bina_kat");
                                binaKacinciKat= myRs.getInt("k_bina_kacinci_kat");
                                iilanID = myRs.getInt("ilan_id");
				ilanBaslik = myRs.getString("ilan_baslik");
				ilanFiyat = myRs.getInt("ilan_fiyat");
				kategoriAd = myRs.getString("kategori_ad");
                                islemAd= myRs.getString("islem_ad");
                                userId= myRs.getInt("user_id");
                                ilanAdres= myRs.getString("ilan_adres");
                                ilanAciklama= myRs.getString("ilan_aciklama");	
                        }
                       
                        String sql2="SELECT resim_ad FROM resim WHERE ilan_id="+id+"; ";
                        myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql2);
                        while (myRs.next()) {
                            String resimAd=myRs.getString("resim_ad");
                            resimPathArrayList.add(resimAd);
                    }
                        Konut tempKonut = new Konut(konutId, ilanID, odaSayisi, binaYasi, binaKat, binaKacinciKat,
						resimPathArrayList, iilanID, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId, ilanAdres, ilanAciklama);

			
			konut_satilik.add(tempKonut);
                        return konut_satilik;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
                
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
