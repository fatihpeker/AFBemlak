
package dbutil;

import entity.Ilan;
import entity.Isyeri;
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

public class IsyeriDbUtil {
    private static IsyeriDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/afb_emlak";
    
    public static IsyeriDbUtil getInstance() throws Exception {
	if (instance == null) {
            instance = new IsyeriDbUtil();
	}
		
	return instance;
    }
	
    public IsyeriDbUtil() throws Exception {		
	dataSource = getDataSource();
    }
    
    public DataSource getDataSource() throws NamingException {
	Context context = new InitialContext();
		
	DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
	return theDataSource;
    }
    
    public void addIsYeri(Isyeri isyeri) throws Exception{
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
        try{
            myConn= getConnection();
            String sql= "insert into isyeri (ilan_id, ioda_sayisi, ibina_yasi, i_bina_kat, i_bina_kacinci_kat) values (?, ?, ?, ?, ?)";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setInt(1, Ilan.ilanInstance.getIlanId());
            myStmt.setString(2, isyeri.getOdaSayisi());
            myStmt.setInt(3, isyeri.getBinaYasi());
            myStmt.setInt(4, isyeri.getBinaKat());
            myStmt.setInt(5, isyeri.getBinaKacinciKat());
            myStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    //satılık isyeri  
    public List<Isyeri> getIsyeriSatilik(int id) throws Exception{
        List<Isyeri> isyeri_satilik = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                          
                               
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama,isyeri.isyeri_id, isyeri.ilan_id, isyeri.ioda_sayisi, "+
                                  "isyeri.ibina_yasi, isyeri.i_bina_kat, isyeri.i_bina_kacinci_kat " +  
                                     "FROM ilan \n" +
                                     "LEFT JOIN isyeri ON ilan.ilan_id = isyeri.ilan_id \n" +
                                     "WHERE kategori_ad='Isyeri' AND islem_ad='Satilik' AND ilan.ilan_id="+id+";";

			myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql);
                        
                        int isyeriId = 0;
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
				isyeriId = myRs.getInt("isyeri_id");
				ilanID = myRs.getInt("ilan_id");
                                odaSayisi= myRs.getString("ioda_sayisi");
                                binaYasi= myRs.getInt("ibina_yasi");
                                binaKat= myRs.getInt("i_bina_kat");
                                binaKacinciKat= myRs.getInt("i_bina_kacinci_kat");
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
                        Isyeri tempKonut = new Isyeri(isyeriId, ilanID, odaSayisi, binaYasi, binaKat, binaKacinciKat,
						resimPathArrayList, iilanID, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId, ilanAdres, ilanAciklama);

			
			isyeri_satilik.add(tempKonut);
                        return isyeri_satilik;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
                
    }
    //kiralık isyeri  
    public List<Isyeri> getIsyeriKiralik(int id) throws Exception{
        List<Isyeri> isyeri_kiralik = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                          
                               
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama,isyeri.isyeri_id, isyeri.ilan_id, isyeri.ioda_sayisi, "+
                                  "isyeri.ibina_yasi, isyeri.i_bina_kat, isyeri.i_bina_kacinci_kat " +  
                                     "FROM ilan \n" +
                                     "LEFT JOIN isyeri ON ilan.ilan_id = isyeri.ilan_id \n" +
                                     "WHERE kategori_ad='Isyeri' AND islem_ad='Kiralik' AND ilan.ilan_id="+id+";";

			myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql);
                        
                        int isyeriId = 0;
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
				isyeriId = myRs.getInt("isyeri_id");
				ilanID = myRs.getInt("ilan_id");
                                odaSayisi= myRs.getString("ioda_sayisi");
                                binaYasi= myRs.getInt("ibina_yasi");
                                binaKat= myRs.getInt("i_bina_kat");
                                binaKacinciKat= myRs.getInt("i_bina_kacinci_kat");
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
                        Isyeri tempKonut = new Isyeri(isyeriId, ilanID, odaSayisi, binaYasi, binaKat, binaKacinciKat,
						resimPathArrayList, iilanID, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId, ilanAdres, ilanAciklama);

			
			isyeri_kiralik.add(tempKonut);
                        return isyeri_kiralik;
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
