/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.shoescare.view;

import edu.shoescare.model.MyConnection;
import edu.shoescare.model.MyReport;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mhmmadhaibah
 */
public class DataUsersForm extends javax.swing.JFrame {

    private DefaultTableModel dataUsersTableModel;
    
    /**
     * Creates new form DataUsersForm
     */
    public DataUsersForm() {
        initComponents();
        initIconImage();
        
        initDataUsersTable();
    }
    
    private void initIconImage()
    {
        setIconImage(new ImageIcon(getClass().getResource("/images/favicon.png")).getImage());
    }
    
    private void initDataUsersTable()
    {
        dataUsersTableModel = (DefaultTableModel) dataUsersTable.getModel();
        
        resetDataUsersTable();
        loadDataUsersTable();
    }
    
    private void resetDataUsersTable()
    {
        dataUsersTableModel.getDataVector().removeAllElements();
        dataUsersTableModel.fireTableStructureChanged();
        dataUsersTableModel.fireTableDataChanged();
        dataUsersTableModel.setRowCount(0);
    }
    
    private void loadDataUsersTable()
    {
        try
        {
            Connection conn = MyConnection.getConnection();
            
            String sqlq = "SELECT * FROM users";
            PreparedStatement stmt = conn.prepareStatement(sqlq);
            ResultSet rslt = stmt.executeQuery();
            
            while (rslt.next())
            {
                Object[] data = new Object[2];
                data[0] = rslt.getString("username");
                data[1] = rslt.getString("name");
                
                if (!data[0].equals("admin"))
                {
                    if (data[1] == null) data[1] = "null";
                    dataUsersTableModel.addRow(data);
                }
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
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

        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        refreshButton = new javax.swing.JButton();
        newUserButton = new javax.swing.JButton();
        reportsButton = new javax.swing.JButton();
        bodyPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataUsersTable = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("emha Shoescare");

        headerPanel.setBackground(new java.awt.Color(153, 204, 255));

        headerLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(0, 0, 0));
        headerLabel.setText("Users Data");

        refreshButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
            }
        });

        newUserButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        newUserButton.setText("New User");
        newUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newUserButtonActionPerformed(evt);
            }
        });

        reportsButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        reportsButton.setText("Reports");
        reportsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(refreshButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(newUserButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportsButton)
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(headerLabel)
                    .addComponent(refreshButton)
                    .addComponent(newUserButton)
                    .addComponent(reportsButton))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        bodyPanel.setBackground(new java.awt.Color(153, 204, 255));

        dataUsersTable.setAutoCreateRowSorter(true);
        dataUsersTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dataUsersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Username", "Name"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataUsersTable.setFocusable(false);
        dataUsersTable.setOpaque(false);
        dataUsersTable.setRowHeight(40);
        dataUsersTable.setShowHorizontalLines(true);
        dataUsersTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(dataUsersTable);
        dataUsersTable.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16));

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 976, Short.MAX_VALUE)
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        initDataUsersTable();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void newUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newUserButtonActionPerformed
        // TODO add your handling code here:
        CreateUserForm createUserView = new CreateUserForm();
        createUserView.setVisible(true);
    }//GEN-LAST:event_newUserButtonActionPerformed

    private void reportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsButtonActionPerformed
        // TODO add your handling code here:
        MyReport myReport = new MyReport();
        myReport.viewReport("DataUsersReport", false);
    }//GEN-LAST:event_reportsButtonActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataUsersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataUsersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataUsersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataUsersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataUsersForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JTable dataUsersTable;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newUserButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton reportsButton;
    // End of variables declaration//GEN-END:variables
}
