/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbutil;

import entity.Document;
import entity.Ilan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author bilal
 */
public class DocumentDbUtil {
    private static DocumentDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/afb_emlak";
    
    public static DocumentDbUtil getInstance() throws Exception {
	if (instance == null) {
            instance = new DocumentDbUtil();
	}
		
	return instance;
    }
	
    public DocumentDbUtil() throws Exception {		
	dataSource = getDataSource();
    }
    
    public DataSource getDataSource() throws NamingException {
	Context context = new InitialContext();
		
	DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
	return theDataSource;
    }
    
    public void insert(Document d){
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
          try{
            myConn= getConnection();
            String sql= "insert into resim (ilan_id, resim_ad, resim_path) values (?,?,?)";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setInt(1, Ilan.ilanInstance.getIlanId());
            myStmt.setString(2, d.getFileName());
            myStmt.setString(3, d.getFilePath());
            
            myStmt.execute();
        }
        catch(Exception e){
            e.printStackTrace();
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
