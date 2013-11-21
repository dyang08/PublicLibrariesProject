/*
 * simple utility lets you write to DB01 database
 * Author oscar menendez
 */

package library;
import java.sql.*;


/**
 *
 * @author oscar
 */
public class DataWriter {

   String userid, password;
   String url = "jdbc:oracle:thin:@cncsidb01.msudenver.edu:1521:DB01";
   Statement stmt;
   Connection con;
    
   
    public DataWriter(String  userId, String password) {
        userid = userId;
        this.password = password;
    }
    //executes given sql string
    public void writeToDB(String sql) {
        try {
            con = DriverManager.getConnection(url, userid, password);
            System.out.println("Connected database successfully...");
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Data was written successfuly");
            
                  
        } catch (Exception e) {
            System.err.println(e);
        }
    }
}
