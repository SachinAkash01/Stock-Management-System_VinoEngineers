/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package GUI;

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

public class StockDetails extends javax.swing.JFrame {
    
    Color mouseEnterColor = new Color(255, 0, 0);
    Color mouseExitColor = new Color(204, 0, 0);
    
    int quantity;
    double price;
    String stockid, stockname, description, supplier;
    DefaultTableModel model;

    /** Creates new form StockDetails */
    public StockDetails() {
        initComponents();
        setStockDetailsToTable();
    }
    
    public void setStockDetailsToTable() {
        try {
            Connection con = DBConnection.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from stock;");

            while (rs.next()) {
                String stockid = rs.getString("stockID");
                String stockname = rs.getString("stockName");
                int quantity = rs.getInt("quantity");
                int price = rs.getInt("unitPrice");
                String description = rs.getString("description");
                String supplier = rs.getString("supplier");
                
                Object[] obj = {stockid, stockname, quantity, price, description, supplier};
                model = (DefaultTableModel) tblstock.getModel();
                model.addRow(obj);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void clearFields(){
        txtstockid.setText("");
        txtstockname.setText("");
        txtquantity.setText("");
        txtprice.setText("");
        txtdescription.setText("");
        txtsupplier.setText("");
    }

    //add items to stock table
    public boolean addStock(){

        boolean added = false;
        stockid = txtstockid.getText();
        stockname = txtstockname.getText();
        quantity = Integer.parseInt(txtquantity.getText());
        price = Double.parseDouble(txtprice.getText());
        description = txtdescription.getText();
        supplier = txtsupplier.getText();
        
        try {
            Connection con = DBConnection.getConnection();
            Statement st1 = con.createStatement();
            ResultSet ID = st1.executeQuery("select stockID from stock;");
            
            boolean found = false;
            
            while(ID.next()) {
                try {
                    String ID1 = ID.getString("stockID");
                    if (ID1.equals(stockid)){
                        found = true;
                        
                        ResultSet Quantity = st1.executeQuery("select quantity from stock where stockID ='"+ID1+"'");
                        Quantity.next();
                        int quantity1 =  Quantity.getInt("quantity");
                        quantity1 += quantity;

                        String sql = "update stock set quantity = ? where stockID = ?;";
                        PreparedStatement pst = con.prepareStatement(sql);

                        pst.setInt(1, quantity1);
                        pst.setString(2, ID1);
                        int rowCount = pst.executeUpdate();

                        if (rowCount > 0) {
                            added = true;
                        } else {
                            added = false;
                        }
                        
                        break;
                    }
                    if (!found) {

                        Connection con1 = DBConnection.getConnection();
                        String sql = "insert into stock(stockID,stockName,quantity,unitPrice,description,supplier) values(?,?,?,?,?,?);";
                        PreparedStatement pst = con1.prepareStatement(sql);

                        pst.setString(1, stockid);
                        pst.setString(2, stockname);
                        pst.setInt(3, quantity);
                        pst.setDouble(4, price);
                        pst.setString(5, description);
                        pst.setString(6, supplier);

                        int rowCount = pst.executeUpdate();

                        if (rowCount > 0) {
                            added = true;
                        } else {
                            added = false;
                        }
                            
                        break;
                    } 
                }catch (Exception e) {
                        e.printStackTrace();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //insert into stock report (inflow)
        try {
            String month;
            Date d = new Date();
            int num = d.getMonth();
            if (num == 0){
                month = "january";
            } else if (num == 1){
                month = "february";
            } else if (num == 2){
                month = "march";
            } else if (num == 3){
                month = "april";
            } else if (num == 4){
                month = "may";
            } else if (num == 5){
                month = "june";
            } else if (num == 6){
                month = "july";
            } else if (num == 7){
                month = "august";
            } else if (num == 8){
                month = "september";
            } else if (num == 9){
                month = "october";
            } else if (num == 10){
                month = "november";
            } else{
                month = "december";
            }
            
            Connection con = DBConnection.getConnection();
            Statement st2 = con.createStatement();
            ResultSet monthID = st2.executeQuery("select stockID from "+month+";");
            
            boolean found1 = false;
            
            while(monthID.next()) {
                try {
                    String monthID1 = monthID.getString("stockID");
                    if (monthID1.equals(stockid)){ //if stock ID already exists in the stock report
                        found1 = true;
                        
                        ResultSet Quantity1 = st2.executeQuery("select inflow,current from "+month+" where stockID ='"+monthID1+"'");
                        Quantity1.next();
                        int quantity2 =  Quantity1.getInt("inflow");
                        int current = Quantity1.getInt("current");
                        quantity2 += quantity;
                        current += quantity;

                        String sql = "update "+month+" set inflow = ?,current = ? where stockID = ?;";
                        PreparedStatement pst = con.prepareStatement(sql);

                        pst.setInt(1, quantity2);
                        pst.setInt(2, current);
                        pst.setString(3, monthID1);
                        pst.executeUpdate();
                        
                        break;
                    }
                    if (!found1) { //if the stock ID do not exists in the stock report 
                        Connection con2 = DBConnection.getConnection();
                        String sql1 = "insert into "+month+"(stockID,itemName,inflow,outflow,current) values(?,?,?,?,?);";
                        PreparedStatement pst1 = con2.prepareStatement(sql1);

                        pst1.setString(1, stockid);
                        pst1.setString(2, stockname);
                        pst1.setInt(3, quantity);
                        pst1.setInt(4, 0);
                        pst1.setInt(5, quantity);
                        
                        pst1.executeUpdate();
                            
                        break;
                    } 
                }catch (Exception e) {
                        e.printStackTrace();
                }
            }
        } catch (Exception e) {
        }

        return added;
    }

    //update stock details
    public boolean updateStock() {
         boolean updated = false;
        stockid = txtstockid.getText();
        stockname = txtstockname.getText();
        quantity = Integer.parseInt(txtquantity.getText());
        price = Double.parseDouble(txtprice.getText());
        description = txtdescription.getText();
        supplier = txtsupplier.getText();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "update stock set stockName = ?,quantity = ?,unitPrice = ?,description = ?,supplier = ? where stockID = ?;";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, stockname);
            pst.setInt(2, quantity);
            pst.setDouble(3, price);
            pst.setString(4, description);
            pst.setString(5, supplier);
            pst.setString(6, stockid);

            int rowCount = pst.executeUpdate();
            
            //Sending emails for the managers informing stock in re-order limits
            try {
                if (quantity <= 5) {
                    Statement st = con.createStatement();
                    ResultSet rs = st.executeQuery("select email from users where position = 'Manager'");
                    while (rs.next()){
                        String email = rs.getString("email");

                        JavaEmailSender j = new JavaEmailSender();
                        j.createAndSendEmail(email, "STOCK IN RE-ORDER LIMIT", "This is to inform you that we've experienced a high demand of stock allocations and following stock, "+stockname+" where stock ID = "+stockid+" is in re-order limit!   Vino Engineers");
                    }
                    
                    JOptionPane.showMessageDialog(this, "Managers Informed via Email");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            
            
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
    public boolean deleteStock() {
        boolean deleted = false;
        stockid = txtstockid.getText();

        try {
            Connection con = DBConnection.getConnection();
            String sql = "delete from stock where stockID=?;";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, stockid);
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
        DefaultTableModel model = (DefaultTableModel) tblstock.getModel();
        model.setRowCount(0);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblstock = new rojeru_san.complementos.RSTableMetro();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtstockname = new app.bolivia.swing.JCTextField();
        jLabel15 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtstockid = new app.bolivia.swing.JCTextField();
        rSMaterialButtonCircle1 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle2 = new rojerusan.RSMaterialButtonCircle();
        rSMaterialButtonCircle3 = new rojerusan.RSMaterialButtonCircle();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        txtprice = new app.bolivia.swing.JCTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        txtquantity = new app.bolivia.swing.JCTextField();
        jLabel17 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtdescription = new app.bolivia.swing.JCTextField();
        jLabel26 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtsupplier = new app.bolivia.swing.JCTextField();

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

        tblstock.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Stock ID", "Item Name", "Quantity", "Unit Price (LKR)", "Description", "Supplier"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblstock.setColorBackgoundHead(new java.awt.Color(204, 0, 0));
        tblstock.setColorFilasBackgound2(new java.awt.Color(255, 255, 255));
        tblstock.setColorSelBackgound(new java.awt.Color(0, 102, 102));
        tblstock.setFont(new java.awt.Font("Yu Gothic UI Light", 0, 25)); // NOI18N
        tblstock.setFuenteFilas(new java.awt.Font("Yu Gothic UI Semibold", 0, 18)); // NOI18N
        tblstock.setFuenteFilasSelect(new java.awt.Font("Yu Gothic UI", 1, 20)); // NOI18N
        tblstock.setFuenteHead(new java.awt.Font("Yu Gothic UI Semibold", 1, 20)); // NOI18N
        tblstock.setRowHeight(40);
        tblstock.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblstockMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblstock);

        jPanel3.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 280, 890, 350));

        jPanel5.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 260, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 5, Short.MAX_VALUE)
        );

        jPanel3.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 140, 260, 5));

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Sitka Text", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/spare.png"))); // NOI18N
        jLabel3.setText("Stock Details");
        jPanel3.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 80, 350, 90));

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
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 180, 130, 30));
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 50, -1));

        txtstockname.setBackground(new java.awt.Color(0, 153, 153));
        txtstockname.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtstockname.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtstockname.setPlaceholder("Enter Item Name..");
        jPanel1.add(txtstockname, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 210, 260, 40));

        jLabel15.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setText("Stock ID:");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 80, 130, 30));
        jPanel1.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, 50, -1));

        txtstockid.setBackground(new java.awt.Color(0, 153, 153));
        txtstockid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtstockid.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtstockid.setPlaceholder("Enter Stock ID..");
        jPanel1.add(txtstockid, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 260, 40));

        rSMaterialButtonCircle1.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle1.setHideActionText(true);
        rSMaterialButtonCircle1.setLabel("DELETE");
        rSMaterialButtonCircle1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle1ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 700, 130, 70));

        rSMaterialButtonCircle2.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle2.setLabel("ADD");
        rSMaterialButtonCircle2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle2ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle2, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 700, 130, 70));

        rSMaterialButtonCircle3.setBackground(new java.awt.Color(204, 0, 0));
        rSMaterialButtonCircle3.setLabel("UPDATE");
        rSMaterialButtonCircle3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSMaterialButtonCircle3ActionPerformed(evt);
            }
        });
        jPanel1.add(rSMaterialButtonCircle3, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 700, 130, 70));

        jLabel21.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Unit Price:");
        jPanel1.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 380, 130, 30));
        jPanel1.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 380, 50, -1));

        txtprice.setBackground(new java.awt.Color(0, 153, 153));
        txtprice.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtprice.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtprice.setPlaceholder("Enter Unit Price..");
        jPanel1.add(txtprice, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 410, 260, 40));

        jLabel23.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Quantity:");
        jPanel1.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 280, 130, 30));
        jPanel1.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 480, 50, -1));

        txtquantity.setBackground(new java.awt.Color(0, 153, 153));
        txtquantity.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtquantity.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtquantity.setPlaceholder("Enter Item Quantity..");
        txtquantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtquantityActionPerformed(evt);
            }
        });
        jPanel1.add(txtquantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 310, 260, 40));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/empid.png"))); // NOI18N
        jPanel1.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 100, 50, 50));

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/dollar.png"))); // NOI18N
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 400, 50, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/unit.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, 50, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/maintenance.png"))); // NOI18N
        jPanel1.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 50, 50));

        jLabel25.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("Description:");
        jPanel1.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 480, 130, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_menu_48px_1.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 500, 50, -1));

        txtdescription.setBackground(new java.awt.Color(0, 153, 153));
        txtdescription.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtdescription.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtdescription.setPlaceholder("Enter Description..");
        jPanel1.add(txtdescription, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 510, 260, 40));

        jLabel26.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("Supplier:");
        jPanel1.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 580, 130, 30));

        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icons/icons8_Account_50px.png"))); // NOI18N
        jPanel1.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 600, 50, -1));

        txtsupplier.setBackground(new java.awt.Color(0, 153, 153));
        txtsupplier.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 0)));
        txtsupplier.setFont(new java.awt.Font("sansserif", 0, 17)); // NOI18N
        txtsupplier.setPlaceholder("Enter Supplier of Stock..");
        jPanel1.add(txtsupplier, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 610, 260, 40));

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

    private void tblstockMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblstockMouseClicked
        int rowNo = tblstock.getSelectedRow();
        TableModel model = tblstock.getModel();

        txtstockid.setText(model.getValueAt(rowNo, 0).toString());
        txtstockname.setText(model.getValueAt(rowNo, 1).toString());
        txtquantity.setText(model.getValueAt(rowNo, 2).toString());
        txtprice.setText(model.getValueAt(rowNo, 3).toString());
        txtdescription.setText(model.getValueAt(rowNo, 4).toString());
        txtsupplier.setText(model.getValueAt(rowNo, 5).toString());
    }//GEN-LAST:event_tblstockMouseClicked

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        dispose();
    }//GEN-LAST:event_jLabel1MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        jPanel2.setBackground(mouseEnterColor);
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseExited
        jPanel2.setBackground(mouseExitColor);
    }//GEN-LAST:event_jLabel1MouseExited

    private void rSMaterialButtonCircle1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle1ActionPerformed
        if (deleteStock()== true){
            JOptionPane.showMessageDialog(this, "Item Deleted Successfully!");
            clearTable();
            setStockDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Item Deletion Failed!");
        }
        
        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle1ActionPerformed

    private void rSMaterialButtonCircle2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle2ActionPerformed
        if (addStock() == true){
            JOptionPane.showMessageDialog(this, "Item Added Successfully!");
            clearTable();
            setStockDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Item Addition Failed!");
        }
        
        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle2ActionPerformed

    private void rSMaterialButtonCircle3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSMaterialButtonCircle3ActionPerformed
        if (updateStock()== true){
            JOptionPane.showMessageDialog(this, "Item Updated Successfully!");
            clearTable();
            setStockDetailsToTable();
        }else{
            JOptionPane.showMessageDialog(this, "Item Updation Failed!");
        }
        
        clearFields();
    }//GEN-LAST:event_rSMaterialButtonCircle3ActionPerformed

    private void txtquantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtquantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtquantityActionPerformed

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
            java.util.logging.Logger.getLogger(StockDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StockDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StockDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StockDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StockDetails().setVisible(true);
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
    private javax.swing.JLabel jLabel26;
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
    private rojeru_san.complementos.RSTableMetro tblstock;
    private app.bolivia.swing.JCTextField txtdescription;
    private app.bolivia.swing.JCTextField txtprice;
    private app.bolivia.swing.JCTextField txtquantity;
    private app.bolivia.swing.JCTextField txtstockid;
    private app.bolivia.swing.JCTextField txtstockname;
    private app.bolivia.swing.JCTextField txtsupplier;
    // End of variables declaration//GEN-END:variables

}
