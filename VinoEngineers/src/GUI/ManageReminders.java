/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Code.DateChecker;
import Code.JavaEmailSender;
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


public class ManageReminders extends javax.swing.JFrame {
    
    Color mouseEnterColor = new Color(255, 0, 0);
    Color mouseExitColor = new Color(204, 0, 0);
    
    Date reminderDate;
    String reminderid, remindername, description, status;
    int userid;
    DefaultTableModel model;

    /**
     * Creates new form ManageReminders
     */
    public ManageReminders() {
        initComponents();
        setReminderDetailsToTable();
    }
    
    //to set the reminder details into the table
    public void setReminderDetailsToTable(){
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from reminders");
            
            while (rs.next()){
                String reminderID = rs.getString("reminderID");
                int userID = rs.getInt("userID");
                String reminderName = rs.getString("reminderName");
                String Description = rs.getString("description");
                Date Rdate = rs.getDate("date");
                String Status = rs.getString("status");
                
                Object[] obj = {reminderID,userID,reminderName,Description,Rdate,Status};
                model = (DefaultTableModel) tblreminder.getModel();
                model.addRow(obj);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //clear all the text fields in the UI
    public void clearFields(){
        txtreminderid.setText("");
        txtuserid.setText("");
        txtremindername.setText("");
        txtdescription.setText("");
    }
    
    //add reminder to reminders table
    public boolean addReminder(){
        
        boolean added = false;
        reminderid = txtreminderid.getText();
        userid = Integer.parseInt(txtuserid.getText());
        remindername = txtremindername.getText();
        description = txtdescription.getText();
        reminderDate = reminderdate.getDatoFecha();
        status = reminderstatus.getSelectedItem().toString();
        
        //convert date into sql date format
        long l1 = reminderDate.getTime();
        java.sql.Date rDate = new java.sql.Date(l1);
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "insert into reminders(reminderID,userID,reminderName,description,date,status) values(?,?,?,?,?,?);";
            PreparedStatement pst = con.prepareStatement(sql);
            
            pst.setString(1, reminderid);
            pst.setInt(2, userid);
            pst.setString(3, remindername);
            pst.setString(4, description);
            pst.setDate(5, rDate);
            pst.setString(6, status);
            
            //Get the current date
            java.util.Date date = new java.util.Date();
            long l2 = date.getTime();
            java.sql.Date cDate = new java.sql.Date(l2);
            
            String date1 = rDate.toString();
            String date2 = cDate.toString();
            
            //compare and send reminder email to the user
            try {
                if (date1.equals(date2)  && status.equals("Not Completed")) {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select email from users where userid = "+userid+";");
                    rs.next();
                    String email = rs.getString("email");

                    JavaEmailSender j = new JavaEmailSender();
                    j.createAndSendEmail(email, "REMINDER FROM VINO ENGINEERS", "This is to inform you that your reminder for , "+description+" where reminder ID = "+reminderid+" is due today!   Vino Engineers");
                    
                    JOptionPane.showMessageDialog(this, "User Informed via Email!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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
    
    //update reminder details
    public boolean updateReminder(){
        boolean updated = false;
        reminderid = txtreminderid.getText();
        userid = Integer.parseInt(txtuserid.getText()); 
        remindername = txtremindername.getText();
        description = txtdescription.getText();
        reminderDate = reminderdate.getDatoFecha();
        status = reminderstatus.getSelectedItem().toString();
        
        //convert date into sql date format
        long l1 = reminderDate.getTime();
        java.sql.Date rDate = new java.sql.Date(l1);
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "update reminders set  reminderName = ?,userID = ?,description = ?,date = ?,status = ? where reminderID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);
                  
            pst.setString(1, remindername);
            pst.setInt(2, userid);
            pst.setString(3, description);
            pst.setDate(4, rDate);
            pst.setString(5, status);
            pst.setString(6, reminderid);
            
            //Get the current date
            java.util.Date date = new java.util.Date();
            long l2 = date.getTime();
            java.sql.Date cDate = new java.sql.Date(l2);
            
            String date1 = rDate.toString();
            String date2 = cDate.toString();
            
            //compare and send reminder email to the user
            try {
                if (date1.equals(date2)  && status.equals("Not Completed")) {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select email from users where userid = "+userid+";");
                    rs.next();
                    String email = rs.getString("email");

                    JavaEmailSender j = new JavaEmailSender();
                    j.createAndSendEmail(email, "REMINDER FROM VINO ENGINEERS", "This is to inform you that your reminder for , "+description+" where reminder ID = "+reminderid+" is due today!   Vino Engineers");
                    
                    JOptionPane.showMessageDialog(this, "User Informed via Email!");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
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
    
    //method to delete reminder details
    public boolean deleteReminder(){
        boolean deleted = false;
        reminderid = txtreminderid.getText();
        
        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from reminders where reminderID=?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, reminderid);
            
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
        DefaultTableModel model = (DefaultTableModel) tblreminder.getModel();
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
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtremindername = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtuserid = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtdescription = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        reminderdate = new rojeru_san.componentes.RSDateChooser();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        reminderstatus = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        txtreminderid = new app.bolivia.swing.JCTextField();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblreminder = new rojeru_san.complementos.RSTableMetro();
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
        jLabel14.setText("Reminder Name:");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 360, 130, 30));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 50, -1));

        txtremindername.setBackground(new java.awt.Color(0, 153, 153));
        txtremindername.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtremindername.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtremindername.setPlaceholder("Enter Reminder Name..");
        jPanel1.add(txtremindername, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 390, 260, 40));

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("User ID:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, 130, 30));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 50, -1));

        txtuserid.setBackground(new java.awt.Color(0, 153, 153));
        txtuserid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtuserid.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtuserid.setPlaceholder("Enter Reminder ID..");
        jPanel1.add(txtuserid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 260, 40));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle1.setHideActionText(true);
        rSMaterialButtonCircle1.setLabel("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 730, 130, 70));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle2.setLabel("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 730, 130, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle3.setLabel("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 730, 130, 70));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 50, -1));

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Description:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 470, 130, 30));
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 50, -1));

        txtdescription.setBackground(new java.awt.Color(0, 153, 153));
        txtdescription.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtdescription.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtdescription.setPlaceholder("Enter Reminder Description..");
        txtdescription.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtdescriptionActionPerformed(evt);
            }
        });
        jPanel1.add(txtdescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 500, 260, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 50, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 490, 50, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Rewind_48px.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 50, 50));

        reminderdate.setBackground(new java.awt.Color(15, 160, 152));
        reminderdate.setColorBackground(new java.awt.Color(14, 118, 112));
        reminderdate.setColorForeground(new java.awt.Color(14, 118, 112));
        reminderdate.setPlaceholder("Select Reminder Date");
        jPanel1.add(reminderdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 640, 200, 40));

        jLabel18.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Reminder Date:");
        jPanel1.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 600, 130, 30));

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Status:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 590, 70, 50));

        reminderstatus.setBackground(new java.awt.Color(0, 102, 102));
        reminderstatus.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        reminderstatus.setForeground(new java.awt.Color(255, 255, 255));
        reminderstatus.setMaximumRowCount(2);
        reminderstatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Not Completed", "Completed" }));
        reminderstatus.setToolTipText("");
        reminderstatus.setBorder(null);
        jPanel1.add(reminderstatus, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 640, 160, 40));

        jLabel16.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Reminder ID:");
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 160, 130, 30));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/empid.png"))); // NOI18N
        jPanel1.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 50, 50));

        txtreminderid.setBackground(new java.awt.Color(0, 153, 153));
        txtreminderid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtreminderid.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtreminderid.setPlaceholder("Enter Reminder ID..");
        jPanel1.add(txtreminderid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 190, 260, 40));

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

        tblreminder.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Reminder ID", "User ID", "Reminder Name", "Description", "Date", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblreminder.setColorBackgoundHead(new java.awt.Color(204, 0, 0));
        tblreminder.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblreminder.setColorSelBackgound(new java.awt.Color(0, 102, 102));
        tblreminder.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tblreminder.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tblreminder.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tblreminder.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tblreminder.setRowHeight(40);
        tblreminder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblreminderMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblreminder);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 920, 350));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 330, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 140, 330, 5));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/repair.png"))); // NOI18N
        jLabel3.setText("Reminder Details");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 420, 90));

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
        if (deleteReminder()== true){
            JOptionPane.showMessageDialog(this, "Reminder Deleted Successfully!");
            clearTable();
            setReminderDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Reminder Deletion Failed!");
        }

        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (addReminder() == true){
            JOptionPane.showMessageDialog(this, "Reminder Added Successfully!");
            clearTable();
            setReminderDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Reminder Addition Failed!");
        }

        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateReminder()== true){
            JOptionPane.showMessageDialog(this, "Reminder Updated Successfully!");
            clearTable();
            setReminderDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Reminder Updation Failed!");
        }

        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void txtdescriptionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtdescriptionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtdescriptionActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel2MouseClicked

    private void tblreminderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblreminderMouseClicked
        int rowNo = tblreminder.getSelectedRow();
        TableModel model = tblreminder.getModel();

        txtreminderid.setText(model.getValueAt(rowNo, 0).toString());
        txtuserid.setText(model.getValueAt(rowNo, 1).toString());
        txtremindername.setText(model.getValueAt(rowNo, 2).toString());
        txtdescription.setText(model.getValueAt(rowNo, 3).toString());
        reminderdate.setDatoFecha((Date) model.getValueAt(rowNo, 4));
        reminderstatus.setSelectedItem(model.getValueAt(rowNo, 5));
    }//GEN-LAST:event_tblreminderMouseClicked

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
            java.util.logging.Logger.getLogger(ManageReminders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ManageReminders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ManageReminders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ManageReminders.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ManageReminders().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
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
    private rojeru_san.componentes.RSDateChooser reminderdate;
    private javax.swing.JComboBox<String> reminderstatus;
    private rojeru_san.complementos.RSTableMetro tblreminder;
    private app.bolivia.swing.JCTextField txtdescription;
    private app.bolivia.swing.JCTextField txtreminderid;
    private app.bolivia.swing.JCTextField txtremindername;
    private app.bolivia.swing.JCTextField txtuserid;
    // End of variables declaration//GEN-END:variables
}
