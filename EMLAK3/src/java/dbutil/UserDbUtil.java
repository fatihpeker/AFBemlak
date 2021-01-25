
package dbutil;

import entity.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class UserDbUtil {
    private static UserDbUtil instance;
    private DataSource dataSource;
    private String jndiName = "java:comp/env/jdbc/afb_emlak";
    
    public static UserDbUtil getInstance() throws Exception {
	if (instance == null) {
            instance = new UserDbUtil();
	}
		
	return instance;
    }
	
    private UserDbUtil() throws Exception {		
	dataSource = getDataSource();
    }
    
    public DataSource getDataSource() throws NamingException {
	Context context = new InitialContext();
		
	DataSource theDataSource = (DataSource) context.lookup(jndiName);
		
	return theDataSource;
    }
    
    public void addUser(User theUser) throws Exception{
        Connection myConn= null;
        PreparedStatement myStmt= null;
        
        try{
            myConn= getConnection();
            String sql= "insert into user (user_name, user_last_name, user_email, user_password) values (?, ?, ?, ?)";
            myStmt= myConn.prepareStatement(sql);
            //set parameters
            myStmt.setString(1, theUser.getFirstName());
            myStmt.setString(2, theUser.getLastName());
            myStmt.setString(3, theUser.getEmail());
            myStmt.setString(4, theUser.getPassword());
            
            myStmt.execute();
            String sql2="SELECT user_id, user_name, user_last_name, user_email FROM user ORDER BY user_id DESC LIMIT 1";
                        myStmt = myConn.prepareStatement(sql2);
                        ResultSet rs=myStmt.executeQuery();
                        if(rs.next()){
                            theUser.setId(rs.getInt("user_id"));
                            theUser.setFirstName(rs.getString("user_name"));
                            theUser.setLastName(rs.getString("user_last_name"));
                            theUser.setEmail(rs.getString("user_email"));
                        }
                      
        }
        finally{
            close(myConn, myStmt);
        }
    }
    
    public boolean CheckUser(Connection conn,User user) throws Exception{
        
        PreparedStatement stmt= null;
        ResultSet rs= null;
        String sql="SELECT * FROM user WHERE user_email=? and user_password=?";
        try {
            conn= getConnection();
            stmt= conn.prepareStatement(sql);
            stmt.setString(1,user.getEmail());
            stmt.setString(2,user.getPassword());
            rs= stmt.executeQuery();
            if(rs.next()){
               user.setId(rs.getInt("user_id"));
               user.setFirstName(rs.getString("user_name"));
               user.setEmail(rs.getString("user_email"));
               user.setPassword(rs.getString("user_password"));
               
               return true;
           }else{
               user.setId(rs.getInt(null));
               user.setFirstName(rs.getString(null));
               user.setEmail(rs.getString(null));
               user.setPassword(rs.getString(null));
               return false;
            }
          
        }
        finally{
            close(conn, stmt, rs);
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
