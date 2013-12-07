package library;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;
import java.sql.*;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * @author Oscar Menendez
 */
public class Search extends JFrame {

    boolean loggedIn;
    Login login;
    String username, type, criteria;
    int colCount = 0;

    /**
     * Creates new form Search
     *
     * @param username
     */
    public Search(String username) {
        initComponents();
        login = new Login();
        loggedIn = false;
        this.username = username;
        type = "";
        criteria = "";
        changeLabels(username);
    }

    /**
     * Change userLabel and login to logout if user name's size > 1
     *
     */
    private void changeLabels(String username) {
        if (username.length() > 1) {
            userLabel.setText(username);
            logButton.setText("logout");
            loggedIn = true;
        }
    }

    /**
     * updates items in table given the criteria or what the user inputs need to
     * add checkboxes
     *
     * @param type
     * @param title
     */
    public void updateTable(String type, String criteria, String search) {
        String items = "S900750662.items";
        String lib = "S900750662.libraries";
        String iStatus = "S900750662.items_status_lk";
        String sql = "";
        Vector columnName = new Vector();

        if (type.equals("CD")) {
            sql = "SELECT item_id, type, album, artist, location, status"
                    + " FROM S900750662.cds" + ", " + items + ", " + lib + ", " + iStatus
                    + " where item_status_id = item_status_fk and item_id = item_id_fk and library_id_fk = library_id"
                    + " AND lower(" + criteria + ") LIKE lower('%" + search + "%')";
            System.out.println("sql: " + sql);
            columnName.add("item_id");
            columnName.add("type");
            columnName.add("album");
            columnName.add("artist");
            columnName.add("location");
            columnName.add("status");
            columnName.add("reserve");
            colCount = 7;
        } else if (type.equals("DVD")) {
            sql = "SELECT item_id, type, title, director, location, status"
                    + " FROM S900750662.dvd" + ", " + items + ", " + lib + ", " + iStatus
                    + " where item_status_id = item_status_fk and item_id = item_id_fk and library_id_fk = library_id"
                    + " AND lower(" + criteria + ") LIKE lower('%" + search + "%')";
            System.out.println("sql: " + sql);
            columnName.add("item_id");
            columnName.add("type");
            columnName.add("title");
            columnName.add("director");
            columnName.add("location");
            columnName.add("status");
            columnName.add("reserve");
            colCount = 7;

        } else {
            sql = "SELECT item_id, type, isbn, title, author, location, status"
                    + " FROM S900750662.book," + items + ", " + lib + ", " + iStatus
                    + " where item_status_id = item_status_fk and item_id = item_id_fk and library_id_fk = library_id"
                    + " AND lower(" + criteria + ") LIKE lower('%" + search + "%')";
            System.out.println("sql: " + sql);
            columnName.add("item_id");
            columnName.add("type");
            columnName.add("isbn");
            columnName.add("title");
            columnName.add("author");
            columnName.add("location");
            columnName.add("status");
            columnName.add("reserve");
            colCount = 8;
            System.out.println("count = " + colCount);
        }
        MyTableModel dtm = null;
        ResultSet rs = new DataManager("S900691255", "1234").resultSet(sql);
        try {
            Vector data = new Vector();

            while (rs.next()) {
                String status = " ";
                Vector row = new Vector();
                for (int i = 1; i < colCount; i++) {
                    if (i == 2) {
                        String itemType = rs.getString(i);
                        switch (itemType) {
                            case "2":
                                row.add("CD");
                                break;
                            case "3":
                                row.add("DVD");
                                break;
                            default:
                                row.add("book");
                                break;
                        }
                    } else if (i == (colCount - 1)) {
                        status = rs.getString(i);
                        row.add(status);
                    } else {
                        row.add(rs.getString(i));
                    }
                }
                if (status.equals("available")) {
                    row.add(false);
                }
                data.add(row);
            }
            dtm = new MyTableModel(data, columnName, colCount);
            itemTable.setModel(dtm);
            itemTable.setRowSelectionAllowed(true);
        } catch (SQLException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        searchBar = new javax.swing.JTextField();
        searchButton = new javax.swing.JButton();
        types = new javax.swing.JComboBox();
        logButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemTable = new javax.swing.JTable();
        submitButton = new javax.swing.JButton();
        userLabel = new javax.swing.JLabel();
        criteriaBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search window");
        setName("searchFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 400));

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        types.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "book", "CD", "DVD" }));
        types.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                typesActionPerformed(evt);
            }
        });

        logButton.setText("login");
        logButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logButtonActionPerformed(evt);
            }
        });

        itemTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Type", "Title", "Author/Artist", "Location", "Status", "Reserve"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Boolean.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(itemTable);

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        criteriaBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "title", "author", "isbn", "location" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 568, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(submitButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(types, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(logButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(criteriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(logButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(userLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(types, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton)
                    .addComponent(criteriaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 221, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(submitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void typesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typesActionPerformed
        type = types.getSelectedItem().toString();
        if (type.equals("CD")) {
            //change values of criteria to represent the CD type
            criteriaBox.removeAllItems();
            criteriaBox.addItem("album");
            criteriaBox.addItem("artist");
            criteriaBox.addItem("year");

        } else if (type.equals("DVD")) {
            criteriaBox.removeAllItems();
            criteriaBox.addItem("title");
            criteriaBox.addItem("director");
            criteriaBox.addItem("year");

        } else if (type.equals("book")) {
            criteriaBox.removeAllItems();
            criteriaBox.addItem("title");
            criteriaBox.addItem("isbn");
            criteriaBox.addItem("author");
            criteriaBox.addItem("year");
        }
    }//GEN-LAST:event_typesActionPerformed

    private void logButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logButtonActionPerformed
        if (loggedIn) {
            logButton.setText("login");
            userLabel.setText("");
            username = "";
            loggedIn = false;
        } else {
            login.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_logButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        criteria = criteriaBox.getSelectedItem().toString();
        System.out.println("criteria " + criteria);
        String search = searchBar.getText();
        updateTable(type, criteria, search);
    }//GEN-LAST:event_searchButtonActionPerformed

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        //testing

        if (!loggedIn) {
            JOptionPane.showConfirmDialog(this, "You need to be logged in to reserve", "warning", JOptionPane.PLAIN_MESSAGE);
        } else {
            for (int i = 0; i < itemTable.getRowCount(); i++) {
                if ((itemTable.getValueAt(i, itemTable.getColumnCount() - 1) != null)
                        && itemTable.getValueAt(i, itemTable.getColumnCount() - 1).toString().equalsIgnoreCase("true")) {
                    int id = Integer.parseInt(itemTable.getValueAt(i, 0).toString());
                    new DataManager("S900691255", "1234").itemReserve(username, id);
                }
            }
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Search.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Search("").setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox criteriaBox;
    private javax.swing.JTable itemTable;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logButton;
    private javax.swing.JTextField searchBar;
    private javax.swing.JButton searchButton;
    private javax.swing.JButton submitButton;
    private javax.swing.JComboBox types;
    private javax.swing.JLabel userLabel;
    // End of variables declaration//GEN-END:variables

}
//override of table model

class MyTableModel extends DefaultTableModel {

    int colCount;

    public MyTableModel(Vector data, Vector columnNames, int columnCount) {
        super(data, columnNames);
        colCount = columnCount;
    }

    @Override
    public Class getColumnClass(int col) {
        if (col == colCount - 1) //second column accepts only Integer values  
        {
            return Boolean.class;
        } else {
            return String.class;  //other columns accept String values  
        }
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        if (col == colCount - 1) //first column will be uneditable  
        {
            if (getValueAt(row, col - 1).equals("available")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
