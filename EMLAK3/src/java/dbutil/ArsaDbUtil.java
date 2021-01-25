
package dbutil;

import entity.Arsa;
import entity.Ilan;
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


public class ArsaDbUtil {
    private static ArsaDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/afb_emlak";
    
    public static ArsaDbUtil getInstance() throws Exception {
	if (instance == null) {
            instance = new ArsaDbUtil();
	}
		
	return instance;
    }
	
    public ArsaDbUtil() throws Exception {		
	dataSource = getDataSource();
    }
    
    public DataSource getDataSource() throws NamingException {
	Context context = new InitialContext();
		
	DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
	return theDataSource;
    }
    
    public void addArsa(Arsa arsa) throws Exception{
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
        try{
            myConn= getConnection();
            String sql= "insert into arsa (ilan_id, metrekare, kanalizasyon, su, telefon, elektrik, dogalgaz) values (?, ?, ?, ?, ?, ?, ?)";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setInt(1, Ilan.ilanInstance.getIlanId());
            myStmt.setInt(2, arsa.getMetreKare());
            myStmt.setString(3, arsa.getKanalizasyon());
            myStmt.setString(4, arsa.getSu());
            myStmt.setString(5, arsa.getTelefon());
            myStmt.setString(6, arsa.getElektrik());
            myStmt.setString(7, arsa.getDogalgaz());
            myStmt.execute();
        }
        finally{
            close(myConn, myStmt);
        }
    }
    //satılık arsa  
    public List<Arsa> getArsaSatilik(int id) throws Exception{
        List<Arsa> arsa_satilik = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                          
                               
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama,arsa.arsa_id, arsa.ilan_id, arsa.metrekare, "+
                                  "arsa.kanalizasyon, arsa.su, arsa.telefon,arsa.elektrik,arsa.dogalgaz " +  
                                     "FROM ilan \n" +
                                     "LEFT JOIN arsa ON ilan.ilan_id = arsa.ilan_id \n" +
                                     "WHERE kategori_ad='Arsa' AND islem_ad='Satilik' AND ilan.ilan_id="+id+";";

			myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql);
                        
                        int arsaID = 0;
                        int ilanID=0;
                        int ailanID=0;
                        int metreKare=0;
                        String kanalizasyon=null;
                        String su=null;
                        String tel=null;
                        String elektrik=null;
                        String dogalgaz=null;
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
				arsaID = myRs.getInt("arsa_id");
				ilanID = myRs.getInt("ilan_id");
                                ailanID = myRs.getInt("ilan_id");
				ilanBaslik = myRs.getString("ilan_baslik");
				ilanFiyat = myRs.getInt("ilan_fiyat");
				kategoriAd = myRs.getString("kategori_ad");
                                islemAd= myRs.getString("islem_ad");
                                userId= myRs.getInt("user_id");
                                ilanAdres= myRs.getString("ilan_adres");
                                ilanAciklama= myRs.getString("ilan_aciklama");
                                metreKare=myRs.getInt("metrekare");
                                kanalizasyon=myRs.getString("kanalizasyon");
                                su=myRs.getString("su");
                                tel=myRs.getString("telefon");
                                elektrik=myRs.getString("elektrik");
                                dogalgaz=myRs.getString("dogalgaz");
                        }
                       
                        String sql2="SELECT resim_ad FROM resim WHERE ilan_id="+id+"; ";
                        myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql2);
                        while (myRs.next()) {
                            String resimAd=myRs.getString("resim_ad");
                            resimPathArrayList.add(resimAd);
                    }
                        Arsa tempKonut = new Arsa(arsaID, ilanID, metreKare, kanalizasyon, su, tel,
						elektrik, dogalgaz, resimPathArrayList, ailanID, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId, ilanAdres,ilanAciklama);

			
			arsa_satilik.add(tempKonut);
                        return arsa_satilik;
		}
		finally {
			close (myConn, myStmt, myRs);
		}
                
    } 
    //kiralık arsa  
    public List<Arsa> getArsaKiralik(int id) throws Exception{
        List<Arsa> arsa_kiralik = new ArrayList<>();

		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {
			myConn = getConnection();
                          
                               
			String sql = "SELECT ilan.ilan_id, ilan.ilan_baslik, ilan.ilan_fiyat, ilan.kategori_ad, ilan.islem_ad, "
                                + "ilan.user_id, ilan.ilan_adres, ilan.ilan_aciklama,arsa.arsa_id, arsa.ilan_id, arsa.metrekare, "+
                                  "arsa.kanalizasyon, arsa.su, arsa.telefon,arsa.elektrik,arsa.dogalgaz " +  
                                     "FROM ilan \n" +
                                     "LEFT JOIN arsa ON ilan.ilan_id = arsa.ilan_id \n" +
                                     "WHERE kategori_ad='Arsa' AND islem_ad='Kiralik' AND ilan.ilan_id="+id+";";

			myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql);
                        
                        int arsaID = 0;
                        int ilanID=0;
                        int ailanID=0;
                        int metreKare=0;
                        String kanalizasyon=null;
                        String su=null;
                        String tel=null;
                        String elektrik=null;
                        String dogalgaz=null;
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
				arsaID = myRs.getInt("arsa_id");
				ilanID = myRs.getInt("ilan_id");
                                ailanID = myRs.getInt("ilan_id");
				ilanBaslik = myRs.getString("ilan_baslik");
				ilanFiyat = myRs.getInt("ilan_fiyat");
				kategoriAd = myRs.getString("kategori_ad");
                                islemAd= myRs.getString("islem_ad");
                                userId= myRs.getInt("user_id");
                                ilanAdres= myRs.getString("ilan_adres");
                                ilanAciklama= myRs.getString("ilan_aciklama");
                                metreKare=myRs.getInt("metrekare");
                                kanalizasyon=myRs.getString("kanalizasyon");
                                su=myRs.getString("su");
                                tel=myRs.getString("telefon");
                                elektrik=myRs.getString("elektrik");
                                dogalgaz=myRs.getString("dogalgaz");
                        }
                       
                        String sql2="SELECT resim_ad FROM resim WHERE ilan_id="+id+"; ";
                        myStmt = myConn.createStatement();
                        myRs= myStmt.executeQuery(sql2);
                        while (myRs.next()) {
                            String resimAd=myRs.getString("resim_ad");
                            resimPathArrayList.add(resimAd);
                    }
                        Arsa tempKonut = new Arsa(arsaID, ilanID, metreKare, kanalizasyon, su, tel,
						elektrik, dogalgaz, resimPathArrayList, ailanID, ilanBaslik, ilanFiyat, kategoriAd, islemAd, userId, ilanAdres,ilanAciklama);

			
			arsa_kiralik.add(tempKonut);
                        return arsa_kiralik;
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
