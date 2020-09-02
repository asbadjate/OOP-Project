/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oops.project;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author vinay
 */
public class Add_Money extends javax.swing.JFrame {

    /**
     * Creates new form Wallet
     */
    Wallet o;
    public Add_Money(Wallet obj) {
        this.setUndecorated(true);
        setResizable(false);
        o = obj;
        initComponents();
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
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        amt = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel5.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jLabel5.setText("Amount");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 210, -1, -1));

        jButton1.setFont(new java.awt.Font("Tw Cen MT", 3, 24)); // NOI18N
        jButton1.setText("Add Money");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 270, -1, -1));

        jButton2.setFont(new java.awt.Font("Tw Cen MT", 3, 36)); // NOI18N
        jButton2.setText("Back");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 150, 60));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 440, 10));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 190, 10, 70));

        amt.setBackground(new java.awt.Color(245, 187, 4));
        amt.setFont(new java.awt.Font("Trebuchet MS", 3, 24)); // NOI18N
        amt.setBorder(null);
        amt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                amtActionPerformed(evt);
            }
        });
        jPanel1.add(amt, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 210, 250, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/oops/project/profile background.jpg"))); // NOI18N
        jLabel1.setText("jLabel2");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 780, 631));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void amtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_amtActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_amtActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        o.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        try
        {
            double val = 0.0;
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/customers", "root", "prakhar@18");

            String sq = "select bal from customer_info where uid = '" + o.o.userId + "'";
            PreparedStatement pstm = conn.prepareStatement(sq);
            ResultSet rs = pstm.executeQuery();
            while(rs.next())
            {
                val = rs.getDouble("bal");
            }
            double d = Double.parseDouble(amt.getText());
            d = Double.parseDouble(String.format("%.2f", d));
            double updatedBal = val + d;
            updatedBal = Double.parseDouble(String.format("%.2f", updatedBal));

            if(d <= 0)
            {
                JOptionPane.showMessageDialog(null, "Invalid amount entered", "Money not added", JOptionPane.ERROR_MESSAGE);
            }
            else if(updatedBal > 100000)
            {
                JOptionPane.showMessageDialog(null, "Sorry. We dont allow more than 1 lakh INR in your wallet for security purpose. Please keep the total amount in your wallet below 1 lakh INR", "Money not added", JOptionPane.ERROR_MESSAGE);
            }
            else
            {    
                String sql = "update customer_info set bal = " + updatedBal + " where uid = '" + o.o.userId + "'";
                
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.executeUpdate();
                
                String sqll = "select * from customer_info where uid = '" + o.o.userId + "'";
                String wallethistory = "";
                PreparedStatement pstmtt = conn.prepareStatement(sqll);            
                ResultSet rss = pstmtt.executeQuery();
                while(rss.next())
                {
                    wallethistory = rss.getString("wallethistory");
                }
                String temp = Double.toString(d);
                if(wallethistory.charAt(0) == '0')
                {
                    wallethistory = "+" + temp;
                }
                else
                {
                    wallethistory = "+" + temp + wallethistory;
                }
                
                String sqlll = "update customer_info set wallethistory = '" + wallethistory + "' where uid = '" + o.o.userId + "'";
                
                PreparedStatement pstmttt = conn.prepareStatement(sqlll);
                pstmttt.executeUpdate();
                
                JOptionPane.showMessageDialog(null, "Rs. " + d + " added to your wallet. Please Refresh to view the New Balance");
            }
            conn.close();
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Invalid amount entered", "Money not added", JOptionPane.ERROR_MESSAGE);
        } // "Invalid amount entered. Please enter a valid amount to be added to your wallet!"
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Wallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Wallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Wallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Wallet.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                new Add_Money().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField amt;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    // End of variables declaration//GEN-END:variables
}