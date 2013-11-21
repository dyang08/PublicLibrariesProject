/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class DataWriter {

    static String userid = "S900691255", password = "oscar1909";
    static String url = "jdbc:oracle:thin:@cncsidb01.msudenver.edu:1521:DB01";
    static Statement stmt;
    static Connection con;
    static ArrayList<String> bookTitles;
    static ArrayList<String> bookAuthor;

    public DataWriter() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        bookTitles = new ArrayList<>();
        bookAuthor = new ArrayList<>();
        initLists("books.txt");
        writeToDb();
    }

    /**
     * writes book titles and authors to books table
     */
    public static void writeToDb() {
        try {
            //STEP 2: Register JDBC driver
            //STEP 3: Open a connection
            Random ran = new Random();
            System.out.println("Connecting to a selected database...");
            con = DriverManager.getConnection(url, userid, password);
            System.out.println("Connected database successfully...");
            stmt = con.createStatement();
            for (int i = 0; i < bookAuthor.size(); i++) {
                String sql = "INSERT INTO ITEMS(title, type, author, library_id_fk)"
                        + "VALUES" + "('" + bookTitles.get(i) + "' " + ",'book'"
                        + ", " + "'" + bookAuthor.get(i) + "' ," + (ran.nextInt(3) + 1) + ")";
                stmt.executeUpdate(sql);
                System.out.println("inserted: " + bookTitles.get(i)
                        + ",and " + bookAuthor.get(i) + " at " + i);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    //executes given sql string
    public void writeToDB(String sql) {
        try {
            con = DriverManager.getConnection(url, userid, password);
            System.out.println("Connected database successfully...");
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public static void initLists(String file) {
        int i = 0;
        boolean done = false;
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line1, line2, blank;
            while (!done) {
                line1 = br.readLine();
                line2 = br.readLine();
                blank = br.readLine();
                if (line1 != null || line2 != null) {
                    bookTitles.add(line1);
                    bookAuthor.add(line2);
                    System.out.println(bookTitles.get(i) + ", " + bookAuthor.get(i));
                    System.out.println(line1 + ", " + line2);
                    i++;
                } else {
                    br.close();
                    done = true;
                }
            }
            br.close();
        } catch (Exception ex) {
            Logger.getLogger(DataWriter.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
