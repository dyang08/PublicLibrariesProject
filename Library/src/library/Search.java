package library;

import javax.swing.JFrame;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import net.proteanit.sql.DbUtils;

/**
 * @author oscar
 */
public class Search extends JFrame {

    boolean logout = false;
    Login login;
    String username, type;

    /**
     * Creates new form Search
     * @param username
     */
    public Search(String username) {
        initComponents();
        login = new Login();
        this.username = username;
        type = "";
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
            logout = true;
        }
    }

    /**
     * updates items in table given the criteria or what the user inputs
     *
     * @param type
     * @param title
     */
    public void updateTable(String type, String title) {

        String sql = "SELECT type, title, author, library"
                + " FROM items LEFT JOIN libraries"
                + " ON libraries.library_id = items.library_id";
               // + " ON title LIKE '" + title + "'";
        System.out.println("sql: " + sql);
        try {
            ResultSet rs = new DataManager("S900691255", "1234").resultSet(sql);
         //   rs.first();
            while (rs.next()){
                
                itemTable.setModel(DbUtils.resultSetToTableModel(rs));   
            }
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Search window");
        setName("searchFrame"); // NOI18N
        setPreferredSize(new java.awt.Dimension(600, 400));

        searchBar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBarActionPerformed(evt);
            }
        });

        searchButton.setText("Search");
        searchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchButtonActionPerformed(evt);
            }
        });

        types.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "any", "book", "CD", "DVD" }));
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
                "Type", "Item", "Copy #", "Status", "Location", "reserve"
            }
        ));
        jScrollPane1.setViewportView(itemTable);

        submitButton.setText("Submit");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(types, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(searchButton))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(logButton)
                                .addGap(36, 36, 36)
                                .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(submitButton))
                        .addGap(0, 11, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logButton)
                    .addComponent(userLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(types, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void typesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_typesActionPerformed
        type = types.getSelectedItem().toString();
    }//GEN-LAST:event_typesActionPerformed

    private void searchBarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBarActionPerformed

    }//GEN-LAST:event_searchBarActionPerformed

    private void logButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logButtonActionPerformed
        if (logout) {
            logButton.setText("login");
            userLabel.setText("");
            username = "";
            logout = false;
        } else {
            login.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_logButtonActionPerformed

    private void searchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchButtonActionPerformed
        String search = searchBar.getText();
        updateTable(type, search);
    }//GEN-LAST:event_searchButtonActionPerformed

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
                String uname = "";
                new Search(uname).setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
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
