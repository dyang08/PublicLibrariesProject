/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package library;

import javax.swing.JOptionPane;

/**
 *
 * @author oscar
 */
public class ItemStatus extends javax.swing.JFrame {

    int itemStatus;

    /**
     * Creates new form ItemStatus
     */
    public ItemStatus() {
        initComponents();
        itemStatus = 0;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        itemId = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        submitButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        statusBox = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Change Item Status");

        jLabel1.setText("Item ID number:");

        jLabel3.setText("Item Status:");

        submitButton.setText("Submit");
        submitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });

        backButton.setText("back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        statusBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Available", "Reserved", "Checked out", "Lost", "Damaged" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemId, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(statusBox, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(submitButton))
                .addContainerGap(185, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(backButton)
                .addGap(23, 23, 23))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(itemId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(statusBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 101, Short.MAX_VALUE)
                .addComponent(submitButton)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * changes visibility to Librarian window
     *
     * @param event
     */
    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        new Librarian().setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_backButtonActionPerformed

    /**
     * If user clicks submit button, a warning message will pop up which will
     * give the option cancel submission
     *
     * @param event
     */
    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitButtonActionPerformed
        int option = JOptionPane.showConfirmDialog(this, "Are you sure you want to submit changes",
                "warning", JOptionPane.YES_NO_OPTION);
        // If user chooses no the program will just go back to window
        if (option == JOptionPane.YES_OPTION) {
            updateStatus();
            //TODO SQL procedures
            String sql = "UPDATE S900750662.items\n"
                    + "SET item_status_fk = " + itemStatus + "\n"
                    + "WHERE item_id = " + itemId.getText();
            System.out.println(sql);
            new DataManager("S900691255", "1234").writeToDB(sql);
            
            itemId.setText("");
            
        }
    }//GEN-LAST:event_submitButtonActionPerformed

    private void updateStatus() {
        String s = statusBox.getSelectedItem().toString();
        System.out.println(s);
        if (s.equalsIgnoreCase("Available")) {
            itemStatus = 1;
        } else if (s.equalsIgnoreCase("Damaged")) {
            itemStatus = 2;
        } else if (s.equalsIgnoreCase("Lost")) {
            itemStatus = 3;
        } else if (s.equalsIgnoreCase("Damaged")) {
            itemStatus = 4;
        } else {
            itemStatus = 5;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JTextField itemId;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JComboBox statusBox;
    private javax.swing.JButton submitButton;
    // End of variables declaration//GEN-END:variables
}
