/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.sql.*;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class DataManager {
    
    private String userid, password;
    private String url = "jdbc:oracle:thin:@cncsidb01.msudenver.edu:1521:DB01";
    private Statement stmt;
    private Connection con;
    private boolean error;
    
    public DataManager(String userId, String password) {
        userid = userId;
        this.password = password;
        error = false;
    }

    /**
     * executes given sql string
     */
    public void writeToDB(String sql) {
        try {
            con = DriverManager.getConnection(url, userid, password);
            System.out.println("Connected database successfully...");
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Data was written successfuly");
            //stmt.closeOnCompletion();
            // con.close();
        } catch (Exception e) {
            System.err.println(e);
            error = true;
            if (e instanceof SQLIntegrityConstraintViolationException) {
                error = true;
                JOptionPane.showConfirmDialog(null, "This username already exists!", "warning", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }
    
    public boolean getErrorState() {
        return error;
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
    
    public Connection connect() {
        try {
            con = DriverManager.getConnection(url, userid, password);
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Connected database successfully...");
        return con;
    }
    
    public void itemReserve(String userName, int ItemID) {
        int userID = 0;
        String customer = "SELECT customer_id "
                + "FROM S900750662.LIB_USERS, S900750662.CUSTOMER "
                + "WHERE username = '" + userName + "' and user_id_fk = user_id";
        ResultSet rs = resultSet(customer);
        try {
            rs.next();
            userID = rs.getInt("customer_id");
        } catch (SQLException ex) {
            Logger.getLogger(DataManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        String insertSQL = "INSERT INTO(item_id_fk, customer_id_fk) "
                + "VALUES(" + ItemID + ", " + userID + ")";
        
        writeToDB(insertSQL);
        
        String updateSQL = "UPDATE S900750662.items "
                + "SET item_status_fk = 5 "
                + "WHERE item_id = " + ItemID;
        
        writeToDB(updateSQL);
        
        System.out.println("userid " + userID);
    }
    
    public static void main(String[] args) {
        
    }
}
