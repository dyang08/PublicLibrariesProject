/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.BufferedReader;
import java.io.FileReader;
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
            if (e instanceof SQLIntegrityConstraintViolationException) {
                error = true;
                JOptionPane.showConfirmDialog(null, "This username already exists!", "warning", JOptionPane.WARNING_MESSAGE);
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

    public static void main(String[] args) {
        for (int i = 13445; i < 15104; i++) {

            String sqlOne = "INSERT INTO S900750662.ITEMS(item_id, type, item_status_fk, library_id_fk) "
                    + "VALUES(" + i + ", 3, " + (new Random().nextInt(5) + 1) + ", "
                    + (new Random().nextInt(4) + 1) + ")";
            System.out.println("id = " + i);
            new DataManager("S900691255", "1234").writeToDB(sqlOne);
        }
    }
}
