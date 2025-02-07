/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.CallableStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author HAPPY
 */
public class Doi_MK_Frame extends javax.swing.JFrame {

    private Thread clockThread;

    /**
     * Creates new form Main_Frame
     */
    public Doi_MK_Frame() {
        initComponents();
    }

    void updateClock() {
        clockThread = new Thread(() -> {
            while (true) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss - dd/MM/yyyy");
                String dateTime = dateFormat.format(new Date());
                timeLabel.setText("" + dateTime);

                try {
                    Thread.sleep(1000); // Cập nhật giờ mỗi giây
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        clockThread.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tk = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        Huy = new javax.swing.JButton();
        DoiMK = new javax.swing.JButton();
        xn_mk = new javax.swing.JPasswordField();
        mk_moi = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        mk_cu = new javax.swing.JTextField();
        user = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tonghocvien = new javax.swing.JLabel();
        tongkhoahoc = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        doimatkhau = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        trangchu = new javax.swing.JLabel();
        quanly = new javax.swing.JLabel();
        khoahoc = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("QUẢN LÝ TRUNG TÂM NGOẠI NGỮ");
        setMaximumSize(new java.awt.Dimension(1300, 800));
        setMinimumSize(new java.awt.Dimension(1300, 800));
        setPreferredSize(new java.awt.Dimension(1300, 800));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tk.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        tk.setForeground(new java.awt.Color(255, 255, 255));
        tk.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                tkAncestorAdded(evt);
            }
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        getContentPane().add(tk, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 130, 120, 40));

        jLabel19.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Tài khoản đăng nhập");
        getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 110, -1, -1));

        Huy.setBackground(new java.awt.Color(0, 0, 153));
        Huy.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        Huy.setForeground(new java.awt.Color(255, 255, 255));
        Huy.setText("HUỶ");
        Huy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                HuyActionPerformed(evt);
            }
        });
        getContentPane().add(Huy, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 590, 140, 60));

        DoiMK.setBackground(new java.awt.Color(0, 0, 153));
        DoiMK.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        DoiMK.setForeground(new java.awt.Color(255, 255, 255));
        DoiMK.setText("XÁC NHẬN");
        DoiMK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DoiMKActionPerformed(evt);
            }
        });
        getContentPane().add(DoiMK, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 590, 180, 60));
        getContentPane().add(xn_mk, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 500, 260, 50));
        getContentPane().add(mk_moi, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 420, 260, 50));

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mật khẩu mới:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 420, 190, 40));

        jLabel5.setBackground(new java.awt.Color(204, 204, 204));
        jLabel5.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Xác nhận lại:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 500, 180, 40));

        jLabel3.setBackground(new java.awt.Color(204, 204, 204));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu củ:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 340, 180, 40));

        mk_cu.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        mk_cu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mk_cuActionPerformed(evt);
            }
        });
        getContentPane().add(mk_cu, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 340, 260, 50));

        user.setBackground(new java.awt.Color(231, 225, 225));
        user.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        user.setForeground(new java.awt.Color(102, 102, 102));
        user.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                userFocusGained(evt);
            }
        });
        user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userActionPerformed(evt);
            }
        });
        getContentPane().add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 260, 260, 50));

        jLabel2.setBackground(new java.awt.Color(204, 204, 204));
        jLabel2.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên user :");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 140, 40));

        tonghocvien.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        tonghocvien.setForeground(new java.awt.Color(204, 0, 51));
        tonghocvien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tonghocvienMouseClicked(evt);
            }
        });
        getContentPane().add(tonghocvien, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 540, 70, 70));

        tongkhoahoc.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        tongkhoahoc.setForeground(new java.awt.Color(204, 0, 51));
        tongkhoahoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tongkhoahocMouseClicked(evt);
            }
        });
        getContentPane().add(tongkhoahoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 540, 70, 70));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("ĐỔI MẬT KHẨU");
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 150, 370, 60));

        jLabel8.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 102));
        jLabel8.setText("Giờ hệ thống");
        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 0, 170, 40));

        timeLabel.setBackground(new java.awt.Color(255, 51, 0));
        timeLabel.setFont(new java.awt.Font("Segoe UI Black", 1, 24)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(0, 0, 102));
        getContentPane().add(timeLabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 280, 50));

        doimatkhau.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        doimatkhau.setText("MẬT KHẨU");
        doimatkhau.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                doimatkhauMouseClicked(evt);
            }
        });
        getContentPane().add(doimatkhau, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 670, 160, 90));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("HỆ THỐNG QUẢN LÝ TRUNG TÂM NGOẠI NGỮ");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 880, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        jLabel6.setText("ĐỔI ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 640, 50, 90));

        trangchu.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        trangchu.setText("TRANG CHỦ");
        trangchu.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trangchuMouseClicked(evt);
            }
        });
        getContentPane().add(trangchu, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 120, 40));

        quanly.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        quanly.setText("QUẢN LÝ");
        quanly.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                quanlyMouseClicked(evt);
            }
        });
        getContentPane().add(quanly, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 380, 120, 40));

        khoahoc.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        khoahoc.setText("KHOÁ HỌC");
        khoahoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                khoahocMouseClicked(evt);
            }
        });
        getContentPane().add(khoahoc, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 530, 120, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_main.png"))); // NOI18N
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1580, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void trangchuMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trangchuMouseClicked
        // TODO add your handling code here:
        try {
            // Tạo kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Gọi funtion để lấy số lượng học viên
            CallableStatement cs1 = (CallableStatement) conn.prepareCall("{? = call DemSoLuongHocVien()}");
            cs1.registerOutParameter(1, java.sql.Types.INTEGER);
            cs1.execute();
            int soLuongHocVien = cs1.getInt(1);

            // Gọi funtion để lấy số lượng giảng viên
            CallableStatement cs2 = (CallableStatement) conn.prepareCall("{? = call DemSoLuongGiangVien()}");
            cs2.registerOutParameter(1, java.sql.Types.INTEGER);
            cs2.execute();
            int soLuongGiangVien = cs2.getInt(1);

            // Gọi funtion để lấy số lượng khóa học
            CallableStatement cs3 = (CallableStatement) conn.prepareCall("{? = call DemSoLuongKhoaHoc()}");
            cs3.registerOutParameter(1, java.sql.Types.INTEGER);
            cs3.execute();
            int soLuongKhoaHoc = cs3.getInt(1);

            // Tạo một instance của Main_Frame
            Main_Frame main = new Main_Frame();

            // Cập nhật số lượng học viên, giảng viên và khóa học
            main.updateCounts(soLuongHocVien, soLuongGiangVien, soLuongKhoaHoc);

            main.updateClock();
            main.setVisible(true);
            this.setVisible(false);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_trangchuMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseClicked

    private void tonghocvienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tonghocvienMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tonghocvienMouseClicked

    private void tongkhoahocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tongkhoahocMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tongkhoahocMouseClicked

    private void quanlyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_quanlyMouseClicked
        Quanly_Frame ql = new Quanly_Frame();
        ql.updateClock();
        ql.setVisible(true);
    }//GEN-LAST:event_quanlyMouseClicked

    private void khoahocMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_khoahocMouseClicked
        // TODO add your handling code here:
        Ql_KH_Frame kh = new Ql_KH_Frame();
        kh.updateClock();
        kh.setVisible(true);
    }//GEN-LAST:event_khoahocMouseClicked

    private void doimatkhauMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_doimatkhauMouseClicked
        // TODO add your handling code here:

    }//GEN-LAST:event_doimatkhauMouseClicked

    private void userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_userActionPerformed

    private void mk_cuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mk_cuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_mk_cuActionPerformed

    private void HuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_HuyActionPerformed
        // TODO add your handling code here:
        // Đặt tất cả các trường về rỗng
        user.setText("");
        mk_cu.setText("");
        mk_moi.setText("");
        xn_mk.setText("");
        Main_Frame main = new Main_Frame();
        main.updateClock();
        main.setVisible(true);
    }//GEN-LAST:event_HuyActionPerformed

    private void DoiMKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DoiMKActionPerformed
        // TODO add your handling code here:
        String username = user.getText();
        String oldPassword = mk_cu.getText();
        String newPassword = new String(mk_moi.getPassword());
        String confirmPassword = new String(xn_mk.getPassword());
        
        if (username.isEmpty() || oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng điền đầy đủ thông tin.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }else if(username.equals("Not User")){
        JOptionPane.showMessageDialog(this, "Thông tin Tên đăng nhập không chính xác", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới và xác nhận mật khẩu không khớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Thực hiện kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo một CallableStatement để gọi stored procedure
            CallableStatement cs = (CallableStatement) conn.prepareCall("{call ChangePassword(?, ?, ?)}");

            // Thiết lập tham số đầu vào
            cs.setString(1, username); // Tên người dùng
            cs.setString(2, oldPassword); // Mật khẩu cũ
            cs.setString(3, newPassword); // Mật khẩu mới

            // Thực thi stored procedure
            cs.execute();

            // Đóng kết nối
            conn.close();

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(this, "Đổi mật khẩu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            Login_Frame lg = new Login_Frame();
            lg.setVisible(true);
        } catch (SQLException e) {
             JOptionPane.showMessageDialog(this, "Mật khẩu cũ không chính xác!", "Thông báo", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_DoiMKActionPerformed

    private void tkAncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_tkAncestorAdded
        // TODO add your handling code here:
        if (!Login_Frame.getLoggedInUsername().equals("")) {
            tk.setText(Login_Frame.getLoggedInUsername());
        } else {
            tk.setText("Not User");
        }
    }//GEN-LAST:event_tkAncestorAdded

    private void userFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_userFocusGained
        // TODO add your handling code here:
        // Khi JTextField user nhận focus, cập nhật nội dung từ tk
        String us = tk.getText();
        if (us != null && !us.isEmpty()) {
            user.setText(us);
        } else {
            user.setText("Chưa đăng nhập");
        }
        
    }//GEN-LAST:event_userFocusGained

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
            java.util.logging.Logger.getLogger(Doi_MK_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Doi_MK_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Doi_MK_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Doi_MK_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Doi_MK_Frame mainFrame = new Doi_MK_Frame();
                mainFrame.updateClock();
                mainFrame.setVisible(true);
                // Kết nối đến cơ sở dữ liệu MySQL

            }

        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton DoiMK;
    private javax.swing.JButton Huy;
    private javax.swing.JLabel doimatkhau;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel khoahoc;
    private javax.swing.JTextField mk_cu;
    private javax.swing.JPasswordField mk_moi;
    private javax.swing.JLabel quanly;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel tk;
    private javax.swing.JLabel tonghocvien;
    private javax.swing.JLabel tongkhoahoc;
    private javax.swing.JLabel trangchu;
    private javax.swing.JTextField user;
    private javax.swing.JPasswordField xn_mk;
    // End of variables declaration//GEN-END:variables
}
