/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;

/**
 *
 * @author HAPPY
 */
public class LS_GV_Frame extends javax.swing.JFrame {

    private Thread clockThread;

    /**
     * Creates new form Main_Frame
     */
    public LS_GV_Frame() {
        initComponents();
        customizeTableHeader();
    }

    private void customizeTableHeader() {
        JTableHeader header = ls_table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
        loadDataToTable(ls_table);
    }

    public void loadDataToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) ls_table.getModel();
        model.setRowCount(0); // Xóa tất cả dòng hiện có trong JTable

        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");
            // Thực hiện truy vấn để lấy dữ liệu từ bảng "log_hocvien"
            String sql = "SELECT * FROM log_giangvien";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet
                String id_log = rs.getString("id_log");
                String hanhdong = rs.getString("action");
                String id_gv = rs.getString("id_gv");
                String ten_gv = rs.getString("ten_gv");
                String tg = rs.getString("thoi_gian");

                // Thêm dữ liệu vào DefaultTableModel
                model.addRow(new Object[]{id_log, hanhdong, id_gv, ten_gv, tg});
            }

            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void exportExcel(JTable table) {
        JFileChooser chooser = new JFileChooser();
        int i = chooser.showSaveDialog(chooser);
        if (i == JFileChooser.APPROVE_OPTION) {
            File file = chooser.getSelectedFile();
            try {
                FileWriter out = new FileWriter(file + ".xls");
                BufferedWriter bwrite = new BufferedWriter(out);
                DefaultTableModel model = (DefaultTableModel) table.getModel();

                // ten Cot
                for (int j = 0; j < table.getColumnCount(); j++) {
                    bwrite.write(model.getColumnName(j) + "\t");
                }
                bwrite.write("\n");

                // Lay du lieu dong
                for (int j = 0; j < table.getRowCount(); j++) {
                    for (int k = 0; k < table.getColumnCount(); k++) {
                        Object cellValue = model.getValueAt(j, k);

                        // Kiểm tra nếu cột là cột "SĐT", thêm dấu nháy đơn trước giá trị
                        if (model.getColumnName(k).equals("SDT")) {
                            cellValue = "`" + cellValue;
                        }

                        bwrite.write(cellValue + "\t");
                    }
                    bwrite.write("\n");
                }
                bwrite.close();
                JOptionPane.showMessageDialog(null, "Lưu file thành công!");
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(null, "Lỗi khi lưu file!");
            }
        }
    }

    class HeaderRenderer extends DefaultTableCellRenderer {

        public HeaderRenderer() {
//        setHorizontalAlignment(JLabel.CENTER);
            setForeground(Color.BLUE); // Màu văn bản của header (màu blue) 
            setFont(new Font("Times New Roman", Font.BOLD, 28));
            setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Đặt viền cho JTable,  
        }
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

        jScrollPane1 = new javax.swing.JScrollPane();
        ls_table = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("QUẢN LÝ TRUNG TÂM NGOẠI NGỮ");
        setMinimumSize(new java.awt.Dimension(1300, 800));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        ls_table.setAutoCreateRowSorter(true);
        ls_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        ls_table.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        ls_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null}
            },
            new String [] {
                "ID_Log", "Hành động", "ID giảng viên", "Tên giảng viên", "Thời gian"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        ls_table.setRowHeight(25);
        ls_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        ls_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(ls_table);
        if (ls_table.getColumnModel().getColumnCount() > 0) {
            ls_table.getColumnModel().getColumn(0).setResizable(false);
            ls_table.getColumnModel().getColumn(0).setPreferredWidth(45);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 1190, 520));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("LỊCH SỬ CẬP NHẬT GIẢNG VIÊN");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 380, 40));

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

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 153));
        jLabel7.setText("HỆ THỐNG QUẢN LÝ TRUNG TÂM NGOẠI NGỮ");
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 30, 880, 40));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/bg_ql.png"))); // NOI18N
        jLabel1.setMaximumSize(new java.awt.Dimension(1280, 800));
        jLabel1.setMinimumSize(new java.awt.Dimension(1280, 800));
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 800));
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -20, 1280, 800));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel8MouseClicked

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel13MouseClicked

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
            java.util.logging.Logger.getLogger(LS_GV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LS_GV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LS_GV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LS_GV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                LS_GV_Frame mainFrame = new LS_GV_Frame();
                mainFrame.updateClock();
                mainFrame.setVisible(true);

            }

        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable ls_table;
    private javax.swing.JLabel timeLabel;
    // End of variables declaration//GEN-END:variables
}
