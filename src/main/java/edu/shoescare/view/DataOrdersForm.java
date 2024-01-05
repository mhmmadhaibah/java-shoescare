/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package edu.shoescare.view;

import edu.shoescare.custom.ActionTableCellEditor;
import edu.shoescare.custom.ActionTableCellRenderer;
import edu.shoescare.custom.ActionTableEvent;
import edu.shoescare.custom.WordWrapCellRenderer;
import edu.shoescare.model.MyConnection;
import edu.shoescare.model.MyReport;
import edu.shoescare.model.User;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableColumnModel;

/**
 *
 * @author mhmmadhaibah
 */
public class DataOrdersForm extends javax.swing.JFrame {

    private DefaultTableModel dataOrdersTableModel;
    
    private DefaultTableColumnModel dataOrdersTableColumnModel;
    
    /**
     * Creates new form DataOrdersForm
     */
    public DataOrdersForm() {
        initComponents();
        initIconImage();
        
        initDataOrdersTable();
    }
    
    private void initIconImage()
    {
        setIconImage(new ImageIcon(getClass().getResource("/images/favicon.png")).getImage());
    }
    
    private void initDataOrdersTable()
    {
        dataOrdersTableModel = (DefaultTableModel) dataOrdersTable.getModel();
        dataOrdersTableColumnModel = (DefaultTableColumnModel) dataOrdersTable.getColumnModel();
        
        resetDataOrdersTable();
        loadDataOrdersTable();
        
        setCustomWidthColumn();
        setCustomWordWrapColumn();
        setCustomActionColumn();
    }
    
    private void resetDataOrdersTable()
    {
        dataOrdersTableModel.getDataVector().removeAllElements();
        dataOrdersTableModel.fireTableStructureChanged();
        dataOrdersTableModel.fireTableDataChanged();
        dataOrdersTableModel.setRowCount(0);
    }
    
    private void loadDataOrdersTable()
    {
        try
        {
            Connection conn = MyConnection.getConnection();
            
            String sqlq = "SELECT * FROM orders";
            PreparedStatement stmt = conn.prepareStatement(sqlq);
            ResultSet rslt = stmt.executeQuery();
            
            while (rslt.next())
            {
                Object[] data = new Object[8];
                data[0] = rslt.getInt("id_order");
                data[1] = rslt.getString("name");
                data[2] = rslt.getString("email");
                data[3] = rslt.getString("phone");
                data[4] = rslt.getInt("quantity");
                data[5] = rslt.getString("services");
                data[6] = rslt.getString("notes");
                data[7] = null;
                
                dataOrdersTableModel.addRow(data);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    private void setCustomWidthColumn()
    {
        dataOrdersTableColumnModel.getColumn(0).setPreferredWidth(50);
        dataOrdersTableColumnModel.getColumn(0).setMaxWidth(50);
        dataOrdersTableColumnModel.getColumn(0).setMinWidth(50);
        
        dataOrdersTableColumnModel.getColumn(7).setPreferredWidth(150);
        dataOrdersTableColumnModel.getColumn(7).setMaxWidth(150);
        dataOrdersTableColumnModel.getColumn(7).setMinWidth(150);
    }
    
    private void setCustomWordWrapColumn()
    {
        dataOrdersTableColumnModel.getColumn(5).setCellRenderer(new WordWrapCellRenderer());
        dataOrdersTableColumnModel.getColumn(6).setCellRenderer(new WordWrapCellRenderer());
    }
    
    private void setCustomActionColumn()
    {
        ActionTableEvent actionTableEvent = new ActionTableEvent() {
            @Override
            public void onEdit(int row)
            {
                int orderId = (Integer) dataOrdersTableModel.getValueAt(row, 0);
                
                UpdateOrderForm updateOrderView = new UpdateOrderForm(orderId);
                updateOrderView.setVisible(true);
            }
            
            @Override
            public void onDelete(int row)
            {
                if (User.getUsername() != null && User.getUsername().equals("admin"))
                {
                    int orderId = (Integer) dataOrdersTableModel.getValueAt(row, 0);
                    
                    try
                    {
                        Connection conn = MyConnection.getConnection();
                        
                        String sqlq = "DELETE FROM orders WHERE id_order = ?";
                        PreparedStatement stmt = conn.prepareStatement(sqlq);
                        
                        stmt.setInt(1, orderId);
                        int rslt = stmt.executeUpdate();
                        
                        if (rslt > 0)
                        {
                            JOptionPane.showMessageDialog(null, "Order-data deleted successfully.");
                            dataOrdersTableModel.removeRow(row);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Something wrong!");
                        }
                    }
                    catch (SQLException e)
                    {
                        e.printStackTrace();
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Sorry, only admins can delete order-data.");
                }
            }
        };
        
        dataOrdersTableColumnModel.getColumn(7).setCellRenderer(new ActionTableCellRenderer());
        dataOrdersTableColumnModel.getColumn(7).setCellEditor(new ActionTableCellEditor(actionTableEvent));
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bodyPanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataOrdersTable = new javax.swing.JTable();
        headerPanel = new javax.swing.JPanel();
        headerLabel = new javax.swing.JLabel();
        newOrderButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        reportsButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("emha Shoescare");

        bodyPanel.setBackground(new java.awt.Color(153, 204, 255));

        dataOrdersTable.setAutoCreateRowSorter(true);
        dataOrdersTable.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        dataOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id", "Name", "Email", "Phone", "Quantity", "Services", "Notes", "Action"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        dataOrdersTable.setFocusable(false);
        dataOrdersTable.setOpaque(false);
        dataOrdersTable.setRowHeight(25);
        dataOrdersTable.setShowHorizontalLines(true);
        dataOrdersTable.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(dataOrdersTable);
        dataOrdersTable.getTableHeader().setFont(new Font("SansSerif", Font.PLAIN, 16));

        javax.swing.GroupLayout bodyPanelLayout = new javax.swing.GroupLayout(bodyPanel);
        bodyPanel.setLayout(bodyPanelLayout);
        bodyPanelLayout.setHorizontalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 1176, Short.MAX_VALUE)
                .addContainerGap())
        );
        bodyPanelLayout.setVerticalGroup(
            bodyPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bodyPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );

        headerPanel.setBackground(new java.awt.Color(153, 204, 255));

        headerLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        headerLabel.setForeground(new java.awt.Color(0, 0, 0));
        headerLabel.setText("Orders Data");

        newOrderButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        newOrderButton.setText("New Order");
        newOrderButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newOrderButtonActionPerformed(evt);
            }
        });

        refreshButton.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        refreshButton.setText("Refresh");
        refreshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshButtonActionPerformed(evt);
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
                .addComponent(newOrderButton)
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
                    .addComponent(newOrderButton)
                    .addComponent(refreshButton)
                    .addComponent(reportsButton))
                .addContainerGap(8, Short.MAX_VALUE))
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bodyPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void newOrderButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newOrderButtonActionPerformed
        // TODO add your handling code here:
        CreateOrderForm createOrderView = new CreateOrderForm();
        createOrderView.setVisible(true);
    }//GEN-LAST:event_newOrderButtonActionPerformed

    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        // TODO add your handling code here:
        initDataOrdersTable();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void reportsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsButtonActionPerformed
        // TODO add your handling code here:
        MyReport myReport = new MyReport();
        myReport.viewReport("DataOrdersReport", false);
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
            java.util.logging.Logger.getLogger(DataOrdersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataOrdersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataOrdersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataOrdersForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DataOrdersForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bodyPanel;
    private javax.swing.JTable dataOrdersTable;
    private javax.swing.JLabel headerLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton newOrderButton;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton reportsButton;
    // End of variables declaration//GEN-END:variables
}
