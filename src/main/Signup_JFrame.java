/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

package main;
import com.mysql.cj.jdbc.CallableStatement;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author HAPPY
 */
public class Signup_JFrame extends javax.swing.JFrame {

    /**
     * Creates new form Signup_JFrame
     */
    public Signup_JFrame() {
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

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        emai = new javax.swing.JTextField();
        user = new javax.swing.JTextField();
        dangky = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(450, 700));
        setPreferredSize(new java.awt.Dimension(414, 700));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setBackground(new java.awt.Color(0, 102, 51));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("ĐĂNG KÝ TÀI KHOẢN");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 420, 40));

        jLabel3.setBackground(new java.awt.Color(0, 102, 51));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TÊN ĐĂNG NHẬP");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 230, 200, 40));

        jLabel4.setBackground(new java.awt.Color(0, 102, 51));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("EMAIL");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 200, 40));

        jLabel5.setBackground(new java.awt.Color(0, 102, 51));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("MẬT KHẨU");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 200, 40));

        jButton1.setBackground(new java.awt.Color(0, 25, 114));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("ĐĂNG NHẬP");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 610, 140, 40));

        emai.setBackground(new java.awt.Color(97, 112, 168));
        emai.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        emai.setForeground(new java.awt.Color(255, 255, 255));
        emai.setBorder(null);
        getContentPane().add(emai, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 360, 240, 40));

        user.setBackground(new java.awt.Color(97, 112, 168));
        user.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setBorder(null);
        getContentPane().add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 270, 230, 40));

        dangky.setBackground(new java.awt.Color(254, 176, 106));
        dangky.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        dangky.setForeground(new java.awt.Color(255, 255, 255));
        dangky.setText("ĐĂNG KÝ");
        dangky.setBorder(null);
        dangky.setBorderPainted(false);
        dangky.setFocusable(false);
        dangky.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dangkyActionPerformed(evt);
            }
        });
        getContentPane().add(dangky, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 510, 250, 50));

        pass.setBackground(new java.awt.Color(97, 112, 168));
        pass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setBorder(null);
        pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passActionPerformed(evt);
            }
        });
        getContentPane().add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 440, 230, 50));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/gb_su.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -30, 780, 722));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void passActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_passActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_passActionPerformed

    private void dangkyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dangkyActionPerformed
        // TODO add your handling code here:
         // Lấy thông tin từ các trường nhập liệu
    String username = user.getText();
    String password = new String(pass.getPassword());
    String email = emai.getText();
    
    // Kiểm tra các điều kiện trước khi tạo tài khoản
    if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.");
    } else if (!isValidEmail(email)) {
        JOptionPane.showMessageDialog(this, "Email không hợp lệ.");
    } else {
        // Kết nối đến cơ sở dữ liệu và thêm người dùng
        try {
            String url = "jdbc:mysql://localhost/ql_lop_av";
            String userDB = "root";
            String passwordDB = "";
            
            Connection connection = DriverManager.getConnection(url, userDB, passwordDB);
            String storedProcedureCall = "{call ThemNguoiDung(?, ?, ?)}";
            CallableStatement callableStatement = (CallableStatement) connection.prepareCall(storedProcedureCall);

            // Truyền tham số cho thủ tục
            callableStatement.setString(1, username);
            callableStatement.setString(2, password);
            callableStatement.setString(3, email);
            
           // Thực hiện thủ tục
            callableStatement.execute();
            connection.close();
            
            JOptionPane.showMessageDialog(this, "Đã đăng ký tài khoản thành công!");
            this.setVisible(false);
            Login_Frame login = new Login_Frame();
            login.setVisible(true);
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi đăng ký tài khoản.");
        }
    }
      
    }//GEN-LAST:event_dangkyActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Login_Frame login = new Login_Frame();
        login.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1ActionPerformed

    private boolean isValidEmail(String email) {
    // Kiểm tra định dạng email sử dụng biểu thức chính quy (regular expression)
    String emailRegex = "^[A-Za-z0-9+_.-]+@(.+)$";
    return email.matches(emailRegex);
}
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
            java.util.logging.Logger.getLogger(Signup_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Signup_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Signup_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Signup_JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Signup_JFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton dangky;
    private javax.swing.JTextField emai;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
