/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import com.mysql.cj.jdbc.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import com.mysql.cj.jdbc.CallableStatement;
import com.mysql.cj.xdevapi.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;

/**
 *
 * @author HAPPY
 */
public class Ql_KH_Frame extends javax.swing.JFrame {

    private Thread clockThread;

    /**
     * Creates new form Main_Frame
     */
    public Ql_KH_Frame() {
        initComponents();
        customizeTableHeader();
    }
    private static String idKH ;
    private void customizeTableHeader() {
        JTableHeader header = kh_table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
        loadDataToKhoaHocTable(kh_table);
        
    }

    

    public static String getID_KH() {

        return idKH; //  
    }

    public void loadDataToKhoaHocTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Xóa tất cả dòng hiện có trong JTable
        
        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Thực hiện truy vấn để lấy dữ liệu từ bảng "khoahoc"
            String sql = "SELECT * FROM khoahoc";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();
//           
            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet

                String id_khoa_hoc = rs.getString("id_khoa_hoc");
                String ten_khoa_hoc = rs.getString("ten_khoa_hoc");
                Date ngay_bd = rs.getDate("ngay_bd");
                Date ngay_kt = rs.getDate("ngay_kt");
                String tai_lieu = rs.getString("tai_lieu");
                String mota = rs.getString("mota");
                String id_lop = rs.getString("id_lop");

                // Thêm dữ liệu vào DefaultTableModel
                model.addRow(new Object[]{id_khoa_hoc, ten_khoa_hoc, ngay_bd, ngay_kt, tai_lieu, mota, id_lop});
            }

            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting() && table.getSelectedRow() != -1) {
                        int selectedRow = table.getSelectedRow();
                        // Cập nhật các JTextField với thông tin khóa học được chọn
                        jTextField1.setText(table.getValueAt(selectedRow, 0).toString());
                        jTextField2.setText(table.getValueAt(selectedRow, 1).toString());
                        // Thay đổi định dạng ngày và cập nhật JTextField3 và JTextField4
                        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                        jTextField3.setText(dateFormat.format(table.getValueAt(selectedRow, 2)));
                        jTextField4.setText(dateFormat.format(table.getValueAt(selectedRow, 3)));
                        jTextField5.setText(table.getValueAt(selectedRow, 4).toString());
                        jTextField6.setText(table.getValueAt(selectedRow, 5).toString());
                        jTextField7.setText(table.getValueAt(selectedRow, 6).toString());
                    }
                    idKH = jTextField1.getText();
                }
            });

            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    class HeaderRenderer extends DefaultTableCellRenderer {

        public HeaderRenderer() {
//        setHorizontalAlignment(JLabel.CENTER);
            setForeground(Color.BLUE); // Màu văn bản của header (màu blue) 
            setFont(new Font("Segoe UI", Font.BOLD, 28)); // Chọn font "Segoe UI" với cỡ chữ 28, và in đậm văn bản
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

        lichsu = new javax.swing.JButton();
        thongke = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        timkiem = new javax.swing.JTextField();
        show = new javax.swing.JButton();
        hienthi = new javax.swing.JButton();
        sapxep = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        xemct = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        sua = new javax.swing.JButton();
        them = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        kh_table = new javax.swing.JTable();
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

        lichsu.setBackground(new java.awt.Color(255, 51, 102));
        lichsu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lichsu.setForeground(new java.awt.Color(255, 255, 255));
        lichsu.setText("Lịch sử");
        lichsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lichsuActionPerformed(evt);
            }
        });
        getContentPane().add(lichsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 450, 120, 40));

        thongke.setBackground(new java.awt.Color(255, 51, 51));
        thongke.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        thongke.setForeground(new java.awt.Color(255, 255, 255));
        thongke.setText("Thống kê");
        thongke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                thongkeActionPerformed(evt);
            }
        });
        getContentPane().add(thongke, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 520, 150, 70));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Tìm kiếm khoá học:");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 170, 190, 30));

        timkiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        timkiem.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(timkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 170, 160, 40));

        show.setBackground(new java.awt.Color(204, 204, 204));
        show.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        show.setText("Tìm kiếm");
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });
        getContentPane().add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 170, 120, 40));

        hienthi.setBackground(new java.awt.Color(204, 204, 204));
        hienthi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        hienthi.setText("Hiển thị");
        hienthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hienthiActionPerformed(evt);
            }
        });
        getContentPane().add(hienthi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 130, 100, -1));

        sapxep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sapxep.setForeground(new java.awt.Color(0, 102, 204));
        sapxep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ID", "Theo Tên" }));
        getContentPane().add(sapxep, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 130, 120, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Sắp xếp khoá học:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 180, 40));

        xemct.setBackground(new java.awt.Color(255, 51, 51));
        xemct.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        xemct.setForeground(new java.awt.Color(255, 255, 255));
        xemct.setText("Xem chi tiết");
        xemct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xemctActionPerformed(evt);
            }
        });
        getContentPane().add(xemct, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 520, 170, 70));

        xoa.setBackground(new java.awt.Color(102, 102, 255));
        xoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        xoa.setForeground(new java.awt.Color(255, 255, 255));
        xoa.setText("Xoá");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });
        getContentPane().add(xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 450, 120, 40));

        sua.setBackground(new java.awt.Color(102, 102, 255));
        sua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sua.setForeground(new java.awt.Color(255, 255, 255));
        sua.setText("Sửa");
        sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaActionPerformed(evt);
            }
        });
        getContentPane().add(sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 450, 120, 40));

        them.setBackground(new java.awt.Color(102, 102, 255));
        them.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        them.setForeground(new java.awt.Color(255, 255, 255));
        them.setText("Thêm");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });
        getContentPane().add(them, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 450, 120, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Tài liệu:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 110, 40));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(51, 51, 51));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 220, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Mô tả:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 160, 30));

        jTextField6.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(51, 51, 51));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 220, 40));

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Lớp:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 110, 30));

        jTextField7.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(51, 51, 51));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 220, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Ngày bắt đầu:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 140, 30));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(51, 51, 51));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 220, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Ngày kết thúc:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 160, 30));

        jTextField4.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(51, 51, 51));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 220, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên khoá học:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 250, 160, 30));

        jTextField2.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField2.setForeground(new java.awt.Color(51, 51, 51));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 220, 40));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("ID Khoá học:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 150, 30));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 220, 40));

        kh_table.setAutoCreateRowSorter(true);
        kh_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        kh_table.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        kh_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên khoá học", "Ngày bắt đầu", "Ngày kết thúc", "Tài liệu", "Mô tả", "Lớp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        kh_table.setRowHeight(25);
        kh_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        kh_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(kh_table);
        if (kh_table.getColumnModel().getColumnCount() > 0) {
            kh_table.getColumnModel().getColumn(0).setResizable(false);
            kh_table.getColumnModel().getColumn(0).setPreferredWidth(45);
            kh_table.getColumnModel().getColumn(6).setResizable(false);
            kh_table.getColumnModel().getColumn(6).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 990, 210));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("QUẢN LÝ THÔNG TIN KHOÁ HỌC");
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 140, 470, 40));

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

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jTextField6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField6ActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        // TODO add your handling code here:
        try {
            String tenKhoaHoc = jTextField2.getText(); //  tên khóa học

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa khóa học này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Kết nối đến cơ sở dữ liệu MySQL
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

                // Tạo lệnh gọi stored procedure
                String sql = "CALL XoaKhoaHocTheoTen(?)"; // Đã sửa thành XoaKhoaHocTheoTen
                CallableStatement statement = (CallableStatement) conn.prepareCall(sql);
                // Thiết lập tham số cho stored procedure
                statement.setString(1, tenKhoaHoc); // Sử dụng tên khóa học để xác định xóa

                // Thực hiện stored procedure để xóa khóa học
                statement.executeUpdate();

                // Đóng kết nối
                statement.close();
                conn.close();

                // Cập nhật lại dữ liệu trong JTable
                loadDataToKhoaHocTable(kh_table);

                // Xóa dữ liệu trong các trường sau khi xóa khóa học
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");

                JOptionPane.showMessageDialog(this, "Xóa khóa học thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa khóa học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_xoaActionPerformed

    private void xemctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xemctActionPerformed
        // TODO add your handling code here:
        String idKhoaHoc = jTextField1.getText();
        Ql_Lop_Frame lop = new Ql_Lop_Frame();
        lop.loadDataToLopTable(idKhoaHoc);
        lop.setVisible(true);
        lop.updateClock();


    }//GEN-LAST:event_xemctActionPerformed

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // TODO add your handling code here:
        try {
            String id_khoa_hoc = jTextField1.getText();
            String ten_khoa_hoc = jTextField2.getText();
            String ngay_bd = jTextField3.getText();
            String ngay_kt = jTextField4.getText();
            String tai_lieu = jTextField5.getText();
            String mota = jTextField6.getText();
            String id_lop = jTextField7.getText();

            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (id_khoa_hoc.isEmpty() || ten_khoa_hoc.isEmpty() || ngay_bd.isEmpty() || ngay_kt.isEmpty() || tai_lieu.isEmpty() || mota.isEmpty() || id_lop.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khóa học.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu có trường nhập liệu rỗng
            }

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh gọi stored procedure
            String sql = "CALL ThemKhoaHoc(?, ?, ?, ?, ?, ?, ?)";
            CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

            // Thiết lập tham số cho stored procedure
            statement.setString(1, id_khoa_hoc);
            statement.setString(2, ten_khoa_hoc);
            statement.setString(3, ngay_bd);
            statement.setString(4, ngay_kt);
            statement.setString(5, tai_lieu);
            statement.setString(6, mota);
            statement.setString(7, id_lop);

            // Thực hiện stored procedure để thêm khóa học
            statement.executeUpdate();

            // Đóng kết nối
            statement.close();
            conn.close();

            // Cập nhật lại dữ liệu trong JTable
            loadDataToKhoaHocTable(kh_table);

            // Xóa dữ liệu trong các trường sau khi thêm
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");

            JOptionPane.showMessageDialog(this, "Thêm khóa học thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm khóa học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_themActionPerformed

    private void suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaActionPerformed
        // TODO add your handling code here:
        try {
            String id_khoa_hoc = jTextField1.getText();
            String ten_khoa_hoc = jTextField2.getText();
            String ngay_bd = jTextField3.getText();
            String ngay_kt = jTextField4.getText();
            String tai_lieu = jTextField5.getText();
            String mota = jTextField6.getText();
            String id_lop = jTextField7.getText();

            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (id_khoa_hoc.isEmpty() || ten_khoa_hoc.isEmpty() || ngay_bd.isEmpty() || ngay_kt.isEmpty() || tai_lieu.isEmpty() || mota.isEmpty() || id_lop.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin khóa học.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu có trường nhập liệu rỗng
            }

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh gọi stored procedure
            String sql = "CALL SuaKhoaHoc(?, ?, ?, ?, ?, ?, ?)";
            CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

            // Thiết lập tham số cho stored procedure
            statement.setString(1, id_khoa_hoc);
            statement.setString(2, ten_khoa_hoc);
            statement.setString(3, ngay_bd);
            statement.setString(4, ngay_kt);
            statement.setString(5, tai_lieu);
            statement.setString(6, mota);
            statement.setString(7, id_lop);

            // Thực hiện stored procedure để cập nhật thông tin khóa học
            statement.executeUpdate();

            // Đóng kết nối
            statement.close();
            conn.close();

            // Cập nhật lại dữ liệu trong JTable
            loadDataToKhoaHocTable(kh_table);

            // Xóa dữ liệu trong các trường sau khi cập nhật
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");

            JOptionPane.showMessageDialog(this, "Cập nhật thông tin khóa học thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin khóa học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_suaActionPerformed


    private void hienthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hienthiActionPerformed
        // TODO add your handling code here:
        // Lấy lựa chọn từ JComboBox
        String sapXepTheo = sapxep.getSelectedItem().toString();
        String sqlFunction = ""; // Chuỗi chứa tên stored procedure tương ứng

        // Chọn stored procedure tương ứng dựa trên lựa chọn của người dùng
        if (sapXepTheo.equals("Theo Tên")) {
            sqlFunction = "SapXepKhoaHocTheoTen"; // Tên stored procedure sắp xếp theo tên
        } else if (sapXepTheo.equals("Theo ID")) {
            sqlFunction = "SapXepKhoaHocTheoId"; // Tên stored procedure sắp xếp theo ID
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ql_lop_av", "root", "");

            // Gọi stored procedure để sắp xếp và hiển thị dữ liệu
            CallableStatement cs = (CallableStatement) connection.prepareCall("{call " + sqlFunction + "}");
            ResultSet rs = cs.executeQuery();

            // Cập nhật dữ liệu trong bảng kh_table dựa trên kết quả của stored procedure
            DefaultTableModel model = (DefaultTableModel) kh_table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ
            while (rs.next()) {
                // Lấy thông tin từ ResultSet và thêm vào bảng
                String id_khoa_hoc = rs.getString("id_khoa_hoc");
                String ten_khoa_hoc = rs.getString("ten_khoa_hoc");
                String ngay_bd = rs.getString("ngay_bd");
                String ngay_kt = rs.getString("ngay_kt");
                String tai_lieu = rs.getString("tai_lieu");
                String mota = rs.getString("mota");
                String id_lop = rs.getString("id_lop");

                // Thêm dữ liệu vào bảng
                model.addRow(new Object[]{id_khoa_hoc, ten_khoa_hoc, ngay_bd, ngay_kt, tai_lieu, mota, id_lop});
            }

            // Refresh lại bảng
            kh_table.repaint();

            // Đóng kết nối và tài nguyên
            rs.close();
            cs.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_hienthiActionPerformed

    private void showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showActionPerformed
        // TODO add your handling code here:
        try {
            String idKhoaHocCanTim = timkiem.getText(); // idKhoaHocCanTim
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh SQL để tìm kiếm khoá học
            String sql = "SELECT * FROM khoahoc WHERE id_khoa_hoc = ?"; //  "id_khoa_hoc"
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idKhoaHocCanTim);

            // Thực hiện truy vấn
            ResultSet result = statement.executeQuery();

            // Cập nhật dữ liệu lên bảng kh_table (giả sử là bảng khoá học)
            DefaultTableModel model = (DefaultTableModel) kh_table.getModel();
            model.setRowCount(0); // Xóa tất cả dữ liệu hiện tại trong bảng

            boolean found = false; // Biến kiểm tra khoá học có được tìm thấy hay không

            while (result.next()) {
                found = true;
                String idKhoaHoc = result.getString("id_khoa_hoc"); // "id_khoa_hoc"
                String tenKhoaHoc = result.getString("ten_khoa_hoc");
                String ngayBatDau = result.getString("ngay_bd");
                String ngayKetThuc = result.getString("ngay_kt");
                String taiLieu = result.getString("tai_lieu");
                String moTa = result.getString("mota");
                String idLop = result.getString("id_lop");

                model.addRow(new Object[]{idKhoaHoc, tenKhoaHoc, ngayBatDau, ngayKetThuc, taiLieu, moTa, idLop});
            }

            // Đóng kết nối
            statement.close();
            conn.close();

            // Kiểm tra nếu không tìm thấy khoá học
            if (!found) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy khoá học có ID: " + idKhoaHocCanTim, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm khoá học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showActionPerformed

    private void thongkeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_thongkeActionPerformed
        // TODO add your handling code here:
        String idkhoahoc = jTextField1.getText();
        T_Ke_Frame tk = new T_Ke_Frame();
        tk.updateClock();
        tk.setVisible(true);
//        tk.loadLopData();
        tk.loadData(idkhoahoc);
    }//GEN-LAST:event_thongkeActionPerformed

    private void lichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lichsuActionPerformed
        // TODO add your handling code here:
        LS_KH_Frame1 ls = new LS_KH_Frame1();
        ls.updateClock();
        ls.setVisible(true);
    }//GEN-LAST:event_lichsuActionPerformed

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
            java.util.logging.Logger.getLogger(Ql_KH_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ql_KH_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ql_KH_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ql_KH_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Ql_KH_Frame mainFrame = new Ql_KH_Frame();
                mainFrame.updateClock();
                mainFrame.setVisible(true);

            }

        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hienthi;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTable kh_table;
    private javax.swing.JButton lichsu;
    private javax.swing.JComboBox<String> sapxep;
    private javax.swing.JButton show;
    private javax.swing.JButton sua;
    private javax.swing.JButton them;
    private javax.swing.JButton thongke;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField timkiem;
    private javax.swing.JButton xemct;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
