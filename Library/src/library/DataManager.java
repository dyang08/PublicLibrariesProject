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
            //   stmt.closeOnCompletion();
            con.close();
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

        try {
            try (BufferedReader reader = new BufferedReader(new FileReader("book list.txt"))) {
                String line = "";
               int id = 3444;//change to last id in items
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split("-");
                    String isbn = "978-0-00-00" + (new Random().nextInt(8999) +1000)
                            + "-" + (new Random().nextInt(10));
                    String year = "" + (new Random().nextInt(60) + 1950);
                    id++;
//                    String sqlOne = "INSERT INTO S900750662.ITEMS(item_id, type, item_status_fk, library_id_fk) "
//                            + "VALUES(" + id + ", 1, " + (new Random().nextInt(5) + 1) + ", "
//                            + (new Random().nextInt(4) + 1) + ")";

                    String sqlTwo = "INSERT INTO S900750662.BOOK (book_id, isbn, author, publication_year, edition, title, item_id_fk) "
                            + "VALUES (" + id + ", '" + isbn + "', '" + data[1] + "', " + year
                            + ", " + (new Random().nextInt(4) + 1) + ", '" + data[0] + "', " + id + ")";
                  //  new DataManager("S900691255", "1234").writeToDB(sqlOne);
                          new DataManager("S900691255", "1234").writeToDB(sqlTwo);
                    // takes an average of 5 minutes per thousand rows
                }
            }
        } catch (Exception e) {
            System.out.println("error reading");
        }

    }
}
