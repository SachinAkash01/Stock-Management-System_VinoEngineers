package GUI;

import DBLayer.DBConnection;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class ManageToolsUI extends javax.swing.JFrame {
    
    Color mouseEnterColor = new Color(255, 0, 0);
    Color mouseExitColor = new Color(204, 0, 0);
    
    int quantity;
    String toolID, toolName, assignedSite;
    Date bDate, rDate;
    DefaultTableModel model;

    /**
     * Creates new form ManageToolsUI
     */
    public ManageToolsUI() {
        initComponents();
        setToolDetailsToTable();
    }
    
    public void setToolDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from tools;");

            while (rs.next()) {
                String toolid = rs.getString("toolID");
                String toolname = rs.getString("toolName");
                int quantity = rs.getInt("quantity");
                String site = rs.getString("assignedSite");
                Date bDate = rs.getDate("borrowedDate");
                Date rDate = rs.getDate("returnedDate");
                
                Object[] obj = {toolid, toolname, quantity, site, bDate, rDate};
                model = (DefaultTableModel) tblTools.getModel();
                model.addRow(obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearFields(){
        txttoolid.setText("");
        txttoolname.setText("");
        txtquantity.setText("");
        txtsite.setText("");
        dateborrowed.setPlaceholder("Select Borrowed Date.. ");
        datereturned.setPlaceholder("Select Returned Date..");
    }

    //add tool to tool table
    public boolean addTool() {

        boolean added = false;
        toolID = txttoolid.getText();
        toolName = txttoolname.getText();
        quantity = Integer.parseInt(txtquantity.getText());
        assignedSite = txtsite.getText();
        bDate = dateborrowed.getDatoFecha();
        rDate = datereturned.getDatoFecha();
        
        long l1 = bDate.getTime();
        long l2 = rDate.getTime();
        
        java.sql.Date borrowdate = new java.sql.Date(l1);
        java.sql.Date returndate = new java.sql.Date(l2);

        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into tools(toolID,toolName,quantity,assignedSite,borrowedDate,returnedDate) values(?,?,?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, toolID);
            pst.setString(2, toolName);
            pst.setInt(3, quantity);
            pst.setString(4, assignedSite);
            pst.setDate(5, borrowdate);
            pst.setDate(6, returndate);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                added = true;
            } else {
                added = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return added;
    }

    //update tool details
    public boolean updateTool() {
         boolean updated = false;
        toolID = txttoolid.getText();
        toolName = txttoolname.getText();
        quantity = Integer.parseInt(txtquantity.getText());
        assignedSite = txtsite.getText();
        bDate = dateborrowed.getDatoFecha();
        rDate = datereturned.getDatoFecha();
        
        long l1 = bDate.getTime();
        long l2 = rDate.getTime();
        
        java.sql.Date borrowdate = new java.sql.Date(l1);
        java.sql.Date returndate = new java.sql.Date(l2);

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update tools set toolName = ?,quantity = ?,assignedSite = ?,borrowedDate = ?,returnedDate = ? where toolID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, toolName);
            pst.setInt(2, quantity);
            pst.setString(3, assignedSite);
            pst.setDate(4, borrowdate);
            pst.setDate(5, returndate);
            pst.setString(6, toolID);

            int rowCount = pst.executeUpdate();

            if (rowCount > 0) {
                updated = true;
            } else {
                updated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return updated;
    }

    //method to delete tool details
    public boolean deleteTool() {
        boolean deleted = false;
        toolID = txttoolid.getText();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from tools where toolID=?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, toolID);
            int rowCount = pst.executeUpdate();
            if (rowCount > 0) {
                deleted = true;
            } else {
                deleted = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return deleted;
    }

    //method to clear table
    public void clearTable() {
        DefaultTableModel model = (DefaultTableModel) tblTools.getModel();
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
        jLabel13 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txttoolid = new app.bolivia.swing.JCTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtquantity = new app.bolivia.swing.JCTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txttoolname = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtsite = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel11 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        dateborrowed = new rojeru_san.componentes.RSDateChooser();
        datereturned = new rojeru_san.componentes.RSDateChooser();
        jLabel22 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblTools = new rojeru_san.complementos.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 153, 153));
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

        jLabel13.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Tool ID:");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 130, 30));
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 50, -1));

        txttoolid.setBackground(new java.awt.Color(0, 153, 153));
        txttoolid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txttoolid.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txttoolid.setPlaceholder("Enter Tool ID..");
        jPanel1.add(txttoolid, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 260, 40));

        jLabel14.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setText("Quantity:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 130, 30));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 50, -1));

        txtquantity.setBackground(new java.awt.Color(0, 153, 153));
        txtquantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtquantity.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtquantity.setPlaceholder("Enter Quantity.");
        jPanel1.add(txtquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 360, 260, 40));

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Returned Date:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 560, 130, 30));
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 50, -1));

        txttoolname.setBackground(new java.awt.Color(0, 153, 153));
        txttoolname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txttoolname.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txttoolname.setPlaceholder("Enter Tool Name..");
        jPanel1.add(txttoolname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 260, 260, 40));

        jLabel17.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Borrowed Date:");
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 560, 130, 30));
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 50, -1));

        jLabel19.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tool Name:");
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 130, 30));

        txtsite.setBackground(new java.awt.Color(0, 153, 153));
        txtsite.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtsite.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtsite.setPlaceholder("Enter Assigned Site.");
        jPanel1.add(txtsite, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 460, 260, 40));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle1.setHideActionText(true);
        rSMaterialButtonCircle1.setLabel("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 690, 130, 70));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle2.setLabel("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 690, 130, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle3.setLabel("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 690, 130, 70));

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Rewind_48px.png"))); // NOI18N
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 450, 50, 50));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 350, 50, 50));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/maintenance.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, 50, 50));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/unit.png"))); // NOI18N
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 250, 50, 50));

        dateborrowed.setBackground(new java.awt.Color(15, 160, 152));
        dateborrowed.setColorBackground(new java.awt.Color(14, 118, 112));
        dateborrowed.setColorForeground(new java.awt.Color(14, 118, 112));
        dateborrowed.setPlaceholder("Select Borrowed Date");
        jPanel1.add(dateborrowed, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 600, 200, 40));

        datereturned.setColorBackground(new java.awt.Color(14, 118, 112));
        datereturned.setColorForeground(new java.awt.Color(14, 118, 112));
        datereturned.setPlaceholder("Select Returned Date");
        jPanel1.add(datereturned, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 600, 200, -1));

        jLabel22.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(255, 255, 255));
        jLabel22.setText("Assigned Site:");
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 430, 130, 30));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel3.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 80, 40));

        tblTools.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tool_ID", "Tool Name", "Quantity", "Assigned Site", "Borrowed Date", "Returned Date"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblTools.setColorBackgoundHead(new java.awt.Color(204, 0, 0));
        tblTools.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblTools.setColorSelBackgound(new java.awt.Color(0, 102, 102));
        tblTools.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tblTools.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tblTools.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tblTools.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tblTools.setRowHeight(40);
        tblTools.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblToolsMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblTools);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 930, 350));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 270, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 270, -1));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon("C:\\Users\\Akash\\OneDrive\\Desktop\\TimeTicker\\TimeTicker\\src\\main\\java\\icons\\repair.png")); // NOI18N
        jLabel3.setText("Manage Tools");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 360, 90));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1530, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 580, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 830, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        if (deleteTool()) {
            JOptionPane.showMessageDialog(this, "Repair Deleted Successfully!");
            clearTable();
            setToolDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Repair Deletion Failed!");
        }
        
        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (addTool()) {
            JOptionPane.showMessageDialog(this, "Repair Added Successfully!");
            clearTable();
            setToolDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Repair Addition Failed!");
        }
        
        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateTool()) {
            JOptionPane.showMessageDialog(this, "Repair Updated Successfully!");
            clearTable();
            setToolDetailsToTable();
        } else {
            JOptionPane.showMessageDialog(this, "Repair Updation Failed!");
        }
        
        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tblToolsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblToolsMouseClicked
        int rowNo = tblTools.getSelectedRow();
        TableModel model = tblTools.getModel();

        txttoolid.setText(model.getValueAt(rowNo, 0).toString());
        txttoolname.setText(model.getValueAt(rowNo, 1).toString());
        txtquantity.setText(model.getValueAt(rowNo, 2).toString());
        txtsite.setText(model.getValueAt(rowNo, 3).toString());
        dateborrowed.setDatoFecha((Date) model.getValueAt(rowNo, 4));
        datereturned.setDatoFecha((Date) model.getValueAt(rowNo, 5));
    }//GEN-LAST:event_tblToolsMouseClicked

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
            java.util.logging.Logger.getLogger(ManageToolsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageToolsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageToolsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageToolsUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageToolsUI().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.componentes.RSDateChooser dateborrowed;
    private rojeru_san.componentes.RSDateChooser datereturned;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tblTools;
    private app.bolivia.swing.JCTextField txtquantity;
    private app.bolivia.swing.JCTextField txtsite;
    private app.bolivia.swing.JCTextField txttoolid;
    private app.bolivia.swing.JCTextField txttoolname;
    // End of variables declaration//GEN-END:variables
}
