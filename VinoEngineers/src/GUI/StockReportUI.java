/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBLayer.DBConnection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class StockReportUI extends javax.swing.JFrame {
    
    Color mouseEnterColor = new Color(255, 0, 0);
    Color mouseExitColor = new Color(204, 0, 0);
    
    String month;
    DefaultTableModel model;

    /**
     * Creates new form StockReportUI
     */
    public StockReportUI() {
        initComponents();
    }
    
    public void setStockReportToTable(){
        try {
            month = txtmonth.getSelectedItem().toString();
            
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from "+month+";");
            
            while (rs.next()){
                String stockID = rs.getString("stockID");
                String itemName = rs.getString("itemName");
                int inflow = rs.getInt("inflow");
                int outflow = rs.getInt("outflow");
                int current = rs.getInt("current");
                
                Object[] obj = {stockID,itemName,inflow,outflow,current};
                model = (DefaultTableModel) tblStockReport.getModel();
                model.addRow(obj);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //method to clear table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tblStockReport.getModel();
        model.setRowCount(0);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblStockReport = new rojeru_san.complementos.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        txtmonth = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        btngenerate = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akash\\OneDrive\\Desktop\\TimeTicker\\TimeTicker\\src\\main\\java\\icons\\icons8_Rewind_48px.png")); // NOI18N
        jLabel1.setText("Back");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel1MouseExited(evt);
            }
        });
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, 30));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 130, 50));

        jPanel4.setBackground(new java.awt.Color(0, 153, 153));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 153, 153));
        jLabel2.setFont(new java.awt.Font("Segoe UI Variable", 1, 30)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("x");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 0, 30, 30));

        jPanel1.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1450, 0, 80, 40));

        tblStockReport.setAutoCreateRowSorter(true);
        tblStockReport.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Item Name", "Inflow of Items", "Outflow of Items", "Current Stock"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblStockReport.setColorBackgoundHead(new java.awt.Color(204, 0, 0));
        tblStockReport.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblStockReport.setColorSelBackgound(new java.awt.Color(0, 102, 102));
        tblStockReport.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tblStockReport.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tblStockReport.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tblStockReport.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tblStockReport.setRowHeight(40);
        jScrollPane1.setViewportView(tblStockReport);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 240, 1030, 530));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 420, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 90, 420, 5));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/tool2.png"))); // NOI18N
        jLabel3.setText("Monthly Stock Report");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 30, 520, 90));

        txtmonth.setBackground(new java.awt.Color(255, 255, 255));
        txtmonth.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        txtmonth.setForeground(new java.awt.Color(0, 0, 0));
        txtmonth.setMaximumRowCount(2);
        txtmonth.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        txtmonth.setToolTipText("");
        txtmonth.setBorder(null);
        jPanel1.add(txtmonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 160, -1, 40));

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 18)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setText("Month:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 160, 70, 40));

        btngenerate.setBackground(new java.awt.Color(0, 153, 153));
        btngenerate.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        btngenerate.setForeground(new java.awt.Color(0, 0, 0));
        btngenerate.setText("Generate Report");
        btngenerate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btngenerateMouseClicked(evt);
            }
        });
        btngenerate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngenerateActionPerformed(evt);
            }
        });
        jPanel1.add(btngenerate, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 160, 180, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        LoginUI tempPos = new LoginUI();
        
        if (tempPos.tempPosition.equals("Admin")){
            DashboardUI ui = new DashboardUI();
            ui.setVisible(true);
            dispose();
        } else if (tempPos.tempPosition.equals("Manager")){
            DashboardUIManager ui = new DashboardUIManager();
            ui.setVisible(true);
            dispose();
        } else if (tempPos.tempPosition.equals("Employee")){
            DashboardUIEmployee ui = new DashboardUIEmployee();
            ui.setVisible(true);
            dispose();
        }
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jPanel2.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jPanel2.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btngenerateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngenerateActionPerformed
        //generate stock report
        
    }//GEN-LAST:event_btngenerateActionPerformed

    private void btngenerateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btngenerateMouseClicked
        // TODO add your handling code here:
        clearTable();
        setStockReportToTable();
    }//GEN-LAST:event_btngenerateMouseClicked

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
            java.util.logging.Logger.getLogger(StockReportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockReportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockReportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockReportUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockReportUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btngenerate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private rojeru_san.complementos.RSTableMetro tblStockReport;
    private javax.swing.JComboBox<String> txtmonth;
    // End of variables declaration//GEN-END:variables
}
