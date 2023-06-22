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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;


public class ManageUser extends javax.swing.JFrame {
    
    Color mouseEnterColor = new Color(255, 0, 0);
    Color mouseExitColor = new Color(204, 0, 0);
    
    String Username, Password, Contact, Email, Position;
    int userID;
    DefaultTableModel model;

    /**
     * Creates new form RegisterUser
     */
    public ManageUser() {
        initComponents();
        setUserDetailsToTable();
    }
    
    //set user's details to the table
    public void setUserDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from users;");

            while (rs.next()) {
                int userid = rs.getInt("userid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String contact = rs.getString("contact");
                String email = rs.getString("email");
                String position = rs.getString("position");
                
                Object[] obj = {userid, username, password, contact, email, position};
                model = (DefaultTableModel) tblusers.getModel();
                model.addRow(obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //clear text fields
    public void clearFields(){
        txtuserid.setText("");
        txtusername.setText("");
        txtpassword.setText("");
        txtcontact.setText("");
        txtemail.setText("");
    }
    
    //add user to user table
    public boolean addUser(){
        
        boolean added = false;
        userID = Integer.parseInt(txtuserid.getText());
        Username = txtusername.getText();
        Password = txtpassword.getText();
        Contact = txtcontact.getText();
        Email = txtemail.getText();
        Position = txtposition.getSelectedItem().toString();
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into users(userid,username,password,contact,email,position) values(?,?,?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setInt(1, userID);
            pst.setString(2, Username);
            pst.setString(3, Password);
            pst.setString(4, Contact);
            pst.setString(5, Email);
            pst.setString(6, Position);
            
            int rowCount = pst.executeUpdate();
            
            if (rowCount > 0){
                added = true;
            }
            else{
                added = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return added;
    }
    
    //update users' details
    public boolean updateUser(){
        boolean updated = false;
        userID = Integer.parseInt(txtuserid.getText());
        Username = txtusername.getText();
        Password = txtpassword.getText();
        Contact = txtcontact.getText();
        Email = txtemail.getText();
        Position = txtposition.getSelectedItem().toString();
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update users set  username = ?,password = ?,contact = ?,email = ?,position = ? where userid = ?;";
            PreparedStatement pst = con.prepareStatement(sql);
               
            pst.setString(1, Username);
            pst.setString(2, Password);
            pst.setString(3, Contact);
            pst.setString(4, Email);
            pst.setString(5, Position);
            pst.setInt(6, userID);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                updated = true;
            }else{
                updated = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return updated;
    }
    
    //method to delete user detail
    public boolean deleteUser(){
        boolean deleted = false;
        userID = Integer.parseInt(txtuserid.getText());
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from users where userid=?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, userID);
            
            int rowCount = pst.executeUpdate();
            if (rowCount > 0){
                deleted = true;
            }else{
                deleted = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return deleted;
    }
    
    //method to clear table
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) tblusers.getModel();
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

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblusers = new rojeru_san.complementos.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtusername = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtuserid = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtemail = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtcontact = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtpassword = new app.bolivia.swing.JCTextField();
        txtposition = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

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

        tblusers.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "User ID", "Username", "Password", "Contact", "Email", "Position"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblusers.setColorBackgoundHead(new java.awt.Color(204, 0, 0));
        tblusers.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblusers.setColorSelBackgound(new java.awt.Color(0, 102, 102));
        tblusers.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tblusers.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tblusers.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tblusers.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tblusers.setRowHeight(40);
        tblusers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblusersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblusers);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 890, 350));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 240, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 140, 240, 5));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/addcustomer.png"))); // NOI18N
        jLabel3.setText("User Details");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 80, 350, 90));

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
        jLabel14.setText("Password:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 270, 130, 30));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 50, -1));

        txtusername.setBackground(new java.awt.Color(0, 153, 153));
        txtusername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtusername.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtusername.setPlaceholder("Enter Supplier..");
        jPanel1.add(txtusername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 260, 40));

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("User ID:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 70, 130, 30));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 50, -1));

        txtuserid.setBackground(new java.awt.Color(0, 153, 153));
        txtuserid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtuserid.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtuserid.setPlaceholder("Enter Supplier ID..");
        jPanel1.add(txtuserid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 100, 260, 40));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle1.setHideActionText(true);
        rSMaterialButtonCircle1.setLabel("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 680, 130, 70));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle2.setLabel("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 680, 130, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle3.setLabel("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 680, 130, 70));

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Position:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 590, 70, 40));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 50, -1));

        txtemail.setBackground(new java.awt.Color(0, 153, 153));
        txtemail.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtemail.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtemail.setPlaceholder("Enter Supplier Email..");
        jPanel1.add(txtemail, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 260, 40));

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Contact:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 370, 130, 30));
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 50, -1));

        txtcontact.setBackground(new java.awt.Color(0, 153, 153));
        txtcontact.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtcontact.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtcontact.setPlaceholder("Enter Supplier Telephone..");
        txtcontact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtcontactActionPerformed(evt);
            }
        });
        jPanel1.add(txtcontact, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 400, 260, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/empid.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 50, 50));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Secured_Letter_50px.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 50, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Google_Mobile_50px.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 390, 50, 50));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 190, 50, 50));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Secure_50px.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 50, 50));

        txtpassword.setBackground(new java.awt.Color(0, 153, 153));
        txtpassword.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtpassword.setFont(new java.awt.Font("Dialog", 0, 17)); // NOI18N
        txtpassword.setPlaceholder("Enter Password..");
        jPanel1.add(txtpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 260, -1));

        txtposition.setBackground(new java.awt.Color(0, 102, 102));
        txtposition.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtposition.setForeground(new java.awt.Color(255, 255, 255));
        txtposition.setMaximumRowCount(2);
        txtposition.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Admin", "Manager", "Employee" }));
        txtposition.setToolTipText("");
        txtposition.setBorder(null);
        jPanel1.add(txtposition, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 590, -1, 40));

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Username:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 130, 30));

        jLabel25.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Email:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 130, 30));

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

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tblusersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblusersMouseClicked
        int rowNo = tblusers.getSelectedRow();
        TableModel model = tblusers.getModel();

        txtuserid.setText(model.getValueAt(rowNo, 0).toString());
        txtusername.setText(model.getValueAt(rowNo, 1).toString());
        txtpassword.setText(model.getValueAt(rowNo, 2).toString());
        txtcontact.setText(model.getValueAt(rowNo, 3).toString());
        txtemail.setText(model.getValueAt(rowNo, 4).toString());
        txtposition.setSelectedItem(model.getValueAt(rowNo, 5));
    }//GEN-LAST:event_tblusersMouseClicked

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
        if (deleteUser()== true){
            JOptionPane.showMessageDialog(this, "User Deleted Successfully!");
            clearTable();
            setUserDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "User Deletion Failed!");
        }

        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (addUser() == true){
            JOptionPane.showMessageDialog(this, "User Added Successfully!");
            clearTable();
            setUserDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "User Addition Failed!");
        }

        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateUser()== true){
            JOptionPane.showMessageDialog(this, "User Updated Successfully!");
            clearTable();
            setUserDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "User Updation Failed!");
        }

        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void txtcontactActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtcontactActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtcontactActionPerformed

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
            java.util.logging.Logger.getLogger(ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageUser.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageUser().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle1;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle2;
    private rojerusan.RSMaterialButtonCircle rSMaterialButtonCircle3;
    private rojeru_san.complementos.RSTableMetro tblusers;
    private app.bolivia.swing.JCTextField txtcontact;
    private app.bolivia.swing.JCTextField txtemail;
    private app.bolivia.swing.JCTextField txtpassword;
    private javax.swing.JComboBox<String> txtposition;
    private app.bolivia.swing.JCTextField txtuserid;
    private app.bolivia.swing.JCTextField txtusername;
    // End of variables declaration//GEN-END:variables
}
