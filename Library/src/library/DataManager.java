/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.TableModel;

/**
 *
 * @author oscar
 */
public class DataManager {

    String userid, password;
    String url = "jdbc:oracle:thin:@cncsidb01.msudenver.edu:1521:DB01";
    Statement stmt;
    Connection con;

    public DataManager(String userId, String password) {
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

    /**
     * returns result set for given sql parameter
     *
     * @param sql
     * @return
     */
    public ResultSet resultSet(String sql) {
        ResultSet rs = null;
        try {
            con = DriverManager.getConnection(url, userid, password);
            System.out.println("Connected database successfully...");
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            System.out.println("excecuted query");  
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        return rs;
    }
    
    public Connection connect(){
        try {
            con = DriverManager.getConnection(url, userid, password);
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println("Connected database successfully...");
        return con;
    }
}
