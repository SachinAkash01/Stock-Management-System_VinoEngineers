/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import DBLayer.DBConnection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class IssueReturnStock extends javax.swing.JFrame {
    
    Color mouseEnterColor = new Color(255, 0, 0);
    Color mouseExitColor = new Color(204, 0, 0);
    
    String stock_id;
    int Quantity;

    /**
     * Creates new form IssueReturnStock
     */
    public IssueReturnStock() {
        initComponents();
    }
    
    //fetch item details from stock table to the ui
    public void getStockDetailsIssue() {

        stock_id = txtstockidissue.getText();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "select stockName, quantity, unitPrice, description from stock where stockID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, stock_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                stockname.setText(rs.getString("stockName"));
                quantity.setText(rs.getString("quantity"));
                unitprice.setText(rs.getString("unitPrice"));
                description.setText(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void getStockDetailsReturn() {

        stock_id = txtstockidreturn.getText();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "select stockName, quantity, unitPrice, description from stock where stockID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, stock_id);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                stockname.setText(rs.getString("stockName"));
                quantity.setText(rs.getString("quantity"));
                unitprice.setText(rs.getString("unitPrice"));
                description.setText(rs.getString("description"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
    public void clearFieldsIssue(){
        txtstockidissue.setText("");
        txtquantityissue.setText("");
    }
    
    public void clearFieldsReturn(){
        txtstockidreturn.setText("");
        txtquantityreturn.setText("");
    }
    
    public boolean updateStockDetailsIssue() {
        boolean update = false;
        stock_id = txtstockidissue.getText();
        Quantity = Integer.parseInt(txtquantityissue.getText());

        try {
            Connection con = DBConnection.getConnection();
            Statement st1 = con.createStatement();
            
            ResultSet Quantity1 = st1.executeQuery("select quantity from stock where stockID = '"+stock_id+"'");
            Quantity1.next();
            int quantity1 =  Quantity1.getInt("quantity");
            quantity1 -= Quantity;
                        
            String sql = "update stock set quantity = ? where stockID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, quantity1);
            pst.setString(2, stock_id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                update = true;
            } else {
                update = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return update;
    }
    
    public boolean updateStockDetailsReturn() {
        boolean update = false;
        stock_id = txtstockidreturn.getText();
        Quantity = Integer.parseInt(txtquantityreturn.getText());

        try {
            Connection con = DBConnection.getConnection();
            Statement st1 = con.createStatement();
            
            ResultSet Quantity1 = st1.executeQuery("select quantity from stock where stockID = '"+stock_id+"'");
            Quantity1.next();
            int quantity1 =  Quantity1.getInt("quantity");
            quantity1 += Quantity;
                        
            String sql = "update stock set quantity = ? where stockID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, quantity1);
            pst.setString(2, stock_id);

            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                update = true;
            } else {
                update = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return update;
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
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        description = new javax.swing.JLabel();
        stockname = new javax.swing.JLabel();
        quantity = new javax.swing.JLabel();
        unitprice = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        jLabel16 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        txtquantityreturn = new app.bolivia.swing.JCTextField();
        txtstockidreturn = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel15 = new javax.swing.JLabel();
        txtstockidissue = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        txtquantityissue = new app.bolivia.swing.JCTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(204, 0, 0));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Rewind_48px.png"))); // NOI18N
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

        jLabel14.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Item Name:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 210, 130, 30));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 50, -1));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 50, -1));

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Unit Price:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 470, 130, 30));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 50, -1));

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Quantity:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 340, 130, 30));
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 50, -1));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/dollar.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 50, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/unit.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 360, 50, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/maintenance.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 230, 50, 50));

        jLabel25.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Description:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 590, 130, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 610, 50, -1));

        description.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        description.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(description, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 620, 230, 40));

        stockname.setBackground(new java.awt.Color(0, 0, 0));
        stockname.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        stockname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(stockname, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 240, 230, 40));

        quantity.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        quantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 370, 230, 40));

        unitprice.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        unitprice.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        jPanel1.add(unitprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 500, 230, 40));

        jLabel5.setBackground(new java.awt.Color(255, 255, 255));
        jLabel5.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Stock Details..");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 70, 280, 90));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 500, 828));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setPreferredSize(new java.awt.Dimension(500, 828));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel6.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 0, 80, 40));

        jPanel8.setBackground(new java.awt.Color(0, 112, 192));
        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 250, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel8.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 100, -1, -1));

        jLabel4.setBackground(new java.awt.Color(255, 255, 255));
        jLabel4.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/spare.png"))); // NOI18N
        jLabel4.setText("Return Stock");
        jPanel8.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, 340, 90));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 51, 51));
        rSMaterialButtonCircle2.setText("Return");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel8.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 510, 260, 70));

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Stock ID:");
        jPanel8.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 210, 130, 30));

        jLabel27.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("Quantity:");
        jPanel8.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 320, 130, 30));

        txtquantityreturn.setBackground(new java.awt.Color(0, 112, 192));
        txtquantityreturn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtquantityreturn.setForeground(new java.awt.Color(255, 255, 255));
        txtquantityreturn.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtquantityreturn.setPlaceholder("Enter Item Quantity..");
        txtquantityreturn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityreturnActionPerformed(evt);
            }
        });
        jPanel8.add(txtquantityreturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 350, 260, 40));

        txtstockidreturn.setBackground(new java.awt.Color(0, 112, 192));
        txtstockidreturn.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtstockidreturn.setForeground(new java.awt.Color(255, 255, 255));
        txtstockidreturn.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtstockidreturn.setPlaceholder("Enter Stock ID..");
        txtstockidreturn.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtstockidreturnFocusLost(evt);
            }
        });
        jPanel8.add(txtstockidreturn, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 240, 260, 40));

        jPanel6.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 120, 460, 670));

        jPanel3.setBackground(new java.awt.Color(153, 204, 153));
        jPanel3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 220, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/tool2.png"))); // NOI18N
        jLabel3.setText("Issue Stock");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 30, 320, 90));

        rSMaterialButtonCircle3.setText("Issue");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel3.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 520, 260, 70));

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Stock ID:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 210, 130, 30));

        txtstockidissue.setBackground(new java.awt.Color(153, 204, 153));
        txtstockidissue.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtstockidissue.setForeground(new java.awt.Color(0, 0, 0));
        txtstockidissue.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtstockidissue.setPlaceholder("Enter Stock ID..");
        txtstockidissue.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtstockidissueFocusLost(evt);
            }
        });
        jPanel3.add(txtstockidissue, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 240, 260, 40));

        jLabel26.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(0, 0, 0));
        jLabel26.setText("Quantity:");
        jPanel3.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 320, 130, 30));

        txtquantityissue.setBackground(new java.awt.Color(153, 204, 153));
        txtquantityissue.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtquantityissue.setForeground(new java.awt.Color(0, 0, 0));
        txtquantityissue.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtquantityissue.setPlaceholder("Enter Item Quantity..");
        txtquantityissue.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityissueActionPerformed(evt);
            }
        });
        jPanel3.add(txtquantityissue, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 350, 260, 40));

        jPanel6.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 460, 670));

        getContentPane().add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 1030, 828));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (updateStockDetailsReturn() == true){
            JOptionPane.showMessageDialog(this, "Stock Returned Successfully!");
            getStockDetailsReturn();
        }else{
            JOptionPane.showMessageDialog(this, "Stock Returning Failed!");
        }
        
        clearFieldsReturn();
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateStockDetailsIssue()== true){
            JOptionPane.showMessageDialog(this, "Stock Issued Successfully!");
            getStockDetailsIssue();
        }else{
            JOptionPane.showMessageDialog(this, "Stock Issuing Failed!");
        }
        
        clearFieldsIssue();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jPanel2.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel1MouseExited

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jPanel2.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        DashboardUI ui = new DashboardUI();
        ui.setVisible(true);
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void txtquantityissueActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityissueActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityissueActionPerformed

    private void txtquantityreturnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityreturnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityreturnActionPerformed

    private void txtstockidissueFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtstockidissueFocusLost
        getStockDetailsIssue();
    }//GEN-LAST:event_txtstockidissueFocusLost

    private void txtstockidreturnFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtstockidreturnFocusLost
        getStockDetailsReturn();
    }//GEN-LAST:event_txtstockidreturnFocusLost

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
            java.util.logging.Logger.getLogger(IssueReturnStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(IssueReturnStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(IssueReturnStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(IssueReturnStock.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new IssueReturnStock().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel description;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JLabel quantity;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private javax.swing.JLabel stockname;
    private app.bolivia.swing.JCTextField txtquantityissue;
    private app.bolivia.swing.JCTextField txtquantityreturn;
    private app.bolivia.swing.JCTextField txtstockidissue;
    private app.bolivia.swing.JCTextField txtstockidreturn;
    private javax.swing.JLabel unitprice;
    // End of variables declaration//GEN-END:variables
}
