/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package main;

import java.sql.Connection;
import java.sql.DriverManager;
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
import java.sql.Time;
import com.mysql.cj.jdbc.CallableStatement;
import java.sql.PreparedStatement;
/**
 *
 * @author HAPPY
 */
public class Ql_Lop_Frame extends javax.swing.JFrame {

    private Thread clockThread;

    /**
     * Creates new form Main_Frame
     */
    public Ql_Lop_Frame() {
        initComponents();
        customizeTableHeader();
    }

    private void customizeTableHeader() {
        JTableHeader header = lopTable.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());

    }

    public void loadDataToLopTable(String idKhoaHoc) {
        DefaultTableModel model = (DefaultTableModel) lopTable.getModel();
        model.setRowCount(0); // Xóa tất cả dòng hiện có trong JTable
        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn1 = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh SQL để lấy thông tin lớp học dựa trên id_khoa_hoc
            String sql = "SELECT * FROM lop_hoc WHERE id_lop IN (SELECT id_lop FROM khoahoc WHERE id_khoa_hoc = ?)";
            PreparedStatement statement1 = conn1.prepareStatement(sql);
            statement1.setString(1, idKhoaHoc);

            // Thực hiện truy vấn
            ResultSet result1 = statement1.executeQuery();

            while (result1.next()) {
                // Đọc dữ liệu từ ResultSet
                String idLop = result1.getString("id_lop");
                String tenLop = result1.getString("ten_lop");
                String buoiHoc = result1.getString("buoi_hoc");
                String tgBd = result1.getTime("tg_bd").toString();
                String tgKt = result1.getTime("tg_kt").toString();
                String phong = result1.getString("phong");

                // Thêm dữ liệu vào DefaultTableModel
                model.addRow(new Object[]{idLop, tenLop, buoiHoc, tgBd, tgKt, phong});
            }
            lopTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting() && lopTable.getSelectedRow() != -1) {
                        int selectedRow = lopTable.getSelectedRow();
                        // Cập nhật các JTextField với thông tin lớp được chọn
                        jTextField1.setText(lopTable.getValueAt(selectedRow, 0).toString());
                        jTextField2.setText(lopTable.getValueAt(selectedRow, 1).toString());
                        jTextField3.setText(lopTable.getValueAt(selectedRow, 2).toString());
                        jTextField4.setText(lopTable.getValueAt(selectedRow, 3).toString());
                        jTextField5.setText(lopTable.getValueAt(selectedRow, 4).toString());
                        jTextField6.setText(lopTable.getValueAt(selectedRow, 5).toString());
                    }
                }
            });
            result1.close();
            statement1.close();
            conn1.close();

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

    public void loadDataToLopTable(JTable lopTable) {
        DefaultTableModel model = (DefaultTableModel) lopTable.getModel();
        model.setRowCount(0); // Xóa tất cả dòng hiện có trong JTable

        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Thực hiện truy vấn để lấy dữ liệu từ bảng "lop_hoc"
            String sql = "SELECT * FROM lop_hoc";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet và thêm vào DefaultTableModel
                String id_lop = rs.getString("id_lop");
                String ten_lop = rs.getString("ten_lop");
                String buoi_hoc = rs.getString("buoi_hoc");
                Time tg_bd = rs.getTime("tg_bd");
                Time tg_kt = rs.getTime("tg_kt");
                String phong = rs.getString("phong");

                model.addRow(new Object[]{id_lop, ten_lop, buoi_hoc, tg_bd, tg_kt, phong});
            }

            rs.close();
            statement.close();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        refesh = new javax.swing.JButton();
        liet_ke = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        hocvien_lop = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        giangvien_lop = new javax.swing.JTable();
        jLabel15 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        timkiem = new javax.swing.JTextField();
        show = new javax.swing.JButton();
        hienthi = new javax.swing.JButton();
        sapxep = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        xoa = new javax.swing.JButton();
        sua = new javax.swing.JButton();
        them = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        lopTable = new javax.swing.JTable();
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

        refesh.setBackground(new java.awt.Color(102, 102, 102));
        refesh.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        refesh.setForeground(new java.awt.Color(255, 255, 255));
        refesh.setText("Refesh ");
        refesh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refeshActionPerformed(evt);
            }
        });
        getContentPane().add(refesh, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 430, 110, 40));

        liet_ke.setBackground(new java.awt.Color(51, 51, 255));
        liet_ke.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        liet_ke.setForeground(new java.awt.Color(255, 255, 255));
        liet_ke.setText("Liệt kê");
        liet_ke.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liet_keActionPerformed(evt);
            }
        });
        getContentPane().add(liet_ke, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 440, 110, 40));

        hocvien_lop.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        hocvien_lop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID học viên", "Tên học viên"
            }
        ));
        hocvien_lop.setRowHeight(25);
        jScrollPane4.setViewportView(hocvien_lop);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 490, 430, 230));

        giangvien_lop.setFont(new java.awt.Font("Segoe UI", 0, 15)); // NOI18N
        giangvien_lop.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "ID_Giảng viên", "Tên giảng viên phụ trách"
            }
        ));
        giangvien_lop.setRowHeight(25);
        jScrollPane3.setViewportView(giangvien_lop);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 490, 540, 130));

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 19)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 51, 102));
        jLabel15.setText("CHI TIẾT LỚP HỌC:");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
            }
        });
        getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 440, 190, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Tìm kiếm lớp học:");
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
        jLabel12.setText("Sắp xếp lớp học:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 130, 180, 40));

        xoa.setBackground(new java.awt.Color(102, 102, 255));
        xoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        xoa.setForeground(new java.awt.Color(255, 255, 255));
        xoa.setText("Xoá");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });
        getContentPane().add(xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 700, 120, 40));

        sua.setBackground(new java.awt.Color(102, 102, 255));
        sua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sua.setForeground(new java.awt.Color(255, 255, 255));
        sua.setText("Sửa");
        sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaActionPerformed(evt);
            }
        });
        getContentPane().add(sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 650, 120, 40));

        them.setBackground(new java.awt.Color(102, 102, 255));
        them.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        them.setForeground(new java.awt.Color(255, 255, 255));
        them.setText("Thêm");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });
        getContentPane().add(them, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 600, 120, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Thời gian kết thúc:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 170, 40));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(51, 51, 51));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 220, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("Phòng học:");
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 520, 160, 30));

        jTextField6.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(51, 51, 51));
        jTextField6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField6ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 550, 220, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Buổi học:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 180, 30));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(51, 51, 51));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 220, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Thời gian bắt đầu:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 390, 180, 30));

        jTextField4.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField4.setForeground(new java.awt.Color(51, 51, 51));
        jTextField4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField4ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 420, 220, 40));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Tên lớp học:");
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
        jLabel2.setText("ID Lớp học:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 150, 30));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 220, 40));

        lopTable.setAutoCreateRowSorter(true);
        lopTable.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lopTable.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lopTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Tên lớp học", "Buổi học", "Thời gian bắt đầu", "Thời gian kết thúc", "Phòng học"
            }
        ));
        lopTable.setRowHeight(25);
        lopTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        lopTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(lopTable);
        if (lopTable.getColumnModel().getColumnCount() > 0) {
            lopTable.getColumnModel().getColumn(0).setResizable(false);
            lopTable.getColumnModel().getColumn(0).setPreferredWidth(45);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 990, 210));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("QUẢN LÝ THÔNG TIN LỚP HỌC");
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

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        // TODO add your handling code here:
        try {
            String tenlop = jTextField1.getText(); // Lấy tên lớp học

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa lớp này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Kết nối đến cơ sở dữ liệu MySQL
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

                // Tạo lệnh gọi stored procedure
                String sql = "CALL XoaLop(?)"; // Gọi stored procedure để xóa lớp  
                CallableStatement statement = (CallableStatement) conn.prepareCall(sql);
                // Thiết lập tham số cho stored procedure
                statement.setString(1, tenlop);

                // Thực hiện stored procedure để xóa lớp
                statement.executeUpdate();

                // Đóng kết nối
                statement.close();
                conn.close();
                loadDataToLopTable(lopTable);
                // Xóa dữ liệu trong các trường sau khi xóa lớp
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");

                JOptionPane.showMessageDialog(this, "Xóa lớp thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa lớp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_xoaActionPerformed

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // TODO add your handling code here:
        try {
            String id_lop = jTextField1.getText();
            String ten_lop = jTextField2.getText();
            String buoi_hoc = jTextField3.getText();
            String tg_bd = jTextField4.getText();
            String tg_kt = jTextField5.getText();
            String phong = jTextField6.getText();

            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (id_lop.isEmpty() || ten_lop.isEmpty() || buoi_hoc.isEmpty() || tg_bd.isEmpty() || tg_kt.isEmpty() || phong.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin lớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu có trường nhập liệu rỗng
            }

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh gọi stored procedure để thêm lớp
            String sql = "CALL ThemLop(?, ?, ?, ?, ?, ?)";
            CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

            // Thiết lập tham số cho stored procedure
            statement.setString(1, id_lop);
            statement.setString(2, ten_lop);
            statement.setString(3, buoi_hoc);
            statement.setString(4, tg_bd);
            statement.setString(5, tg_kt);
            statement.setString(6, phong);

            // Thực hiện stored procedure để thêm lớp
            statement.executeUpdate();

            // Đóng kết nối
            statement.close();
            conn.close();
            loadDataToLopTable(lopTable);
            // Xóa dữ liệu trong các trường sau khi thêm
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            JOptionPane.showMessageDialog(this, "Thêm lớp thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm lớp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_themActionPerformed

    private void suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaActionPerformed
        // TODO add your handling code here:
        try {
            String id_lop = jTextField1.getText();
            String ten_lop = jTextField2.getText();
            String buoi_hoc = jTextField3.getText();
            String tg_bd = jTextField4.getText();
            String tg_kt = jTextField5.getText();
            String phong = jTextField6.getText();

            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (id_lop.isEmpty() || ten_lop.isEmpty() || buoi_hoc.isEmpty() || tg_bd.isEmpty() || tg_kt.isEmpty() || phong.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin lớp.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu có trường nhập liệu rỗng
            }

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh gọi stored procedure
            String sql = "CALL SuaLop(?, ?, ?, ?, ?, ?)";
            CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

            // Thiết lập tham số cho stored procedure
            statement.setString(1, id_lop);
            statement.setString(2, ten_lop);
            statement.setString(3, buoi_hoc);
            statement.setString(4, tg_bd);
            statement.setString(5, tg_kt);
            statement.setString(6, phong);

            // Thực hiện stored procedure để cập nhật thông tin lớp
            statement.executeUpdate();

            // Đóng kết nối
            statement.close();
            conn.close();

            // Cập nhật lại dữ liệu trong JTable
            loadDataToLopTable(lopTable);
            //loadDataToLopTable(lopTable);
            // Xóa dữ liệu trong các trường sau khi sửa
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");

            JOptionPane.showMessageDialog(this, "Cập nhật thông tin lớp thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin lớp: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }


    }//GEN-LAST:event_suaActionPerformed


    private void hienthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hienthiActionPerformed
        // Lấy lựa chọn từ JComboBox
        String sapXepTheo = sapxep.getSelectedItem().toString();
        String sqlProcedure = ""; // Chuỗi chứa tên stored procedure tương ứng

        // Chọn stored procedure tương ứng dựa trên lựa chọn của người dùng
        if (sapXepTheo.equals("Theo Tên")) {
            sqlProcedure = "SapXepLopTheoTen"; // Đã sửa tên stored procedure sắp xếp theo tên
        } else if (sapXepTheo.equals("Theo ID")) {
            sqlProcedure = "SapXepLopTheoID"; // Đã sửa tên stored procedure sắp xếp theo ID
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ql_lop_av", "root", "");

            // Gọi stored procedure để sắp xếp và hiển thị dữ liệu
            CallableStatement cs = (CallableStatement) connection.prepareCall("{call " + sqlProcedure + "}");
            ResultSet rs = cs.executeQuery();

            // Cập nhật dữ liệu trong bảng lopTable dựa trên kết quả của stored procedure
            DefaultTableModel model = (DefaultTableModel) lopTable.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ
            while (rs.next()) {
                // Lấy thông tin từ ResultSet và thêm vào bảng
                String id_lop = rs.getString("id_lop");
                String ten_lop = rs.getString("ten_lop");
                String buoi_hoc = rs.getString("buoi_hoc");
                String tg_bd = rs.getString("tg_bd");
                String tg_kt = rs.getString("tg_kt");
                String phong = rs.getString("phong");

                // Thêm dữ liệu vào bảng
                model.addRow(new Object[]{id_lop, ten_lop, buoi_hoc, tg_bd, tg_kt, phong});
            }

            // Refresh lại bảng
            lopTable.repaint();

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
            String idLopCanTim = timkiem.getText(); // idLopCanTim

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh SQL để tìm kiếm lớp học
            String sql = "SELECT * FROM lop_hoc WHERE id_lop = ?"; // "id_lop"
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idLopCanTim);

            // Thực hiện truy vấn
            ResultSet result = statement.executeQuery();

            // Cập nhật dữ liệu lên bảng lopTable (giả sử là bảng lớp học)
            DefaultTableModel model = (DefaultTableModel) lopTable.getModel();
            model.setRowCount(0); // Xóa tất cả dữ liệu hiện tại trong bảng

            boolean found = false; // Biến kiểm tra lớp học có được tìm thấy hay không

            while (result.next()) {
                found = true;
                String idLop = result.getString("id_lop");
                String tenLop = result.getString("ten_lop");
                String buoiHoc = result.getString("buoi_hoc");
                String tgBd = result.getString("tg_bd");
                String tgKt = result.getString("tg_kt");
                String phong = result.getString("phong");

                model.addRow(new Object[]{idLop, tenLop, buoiHoc, tgBd, tgKt, phong});
            }

            // Đóng kết nối
            statement.close();
            conn.close();

            // Kiểm tra nếu không tìm thấy lớp học
            if (!found) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy lớp học có ID: " + idLopCanTim, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm lớp học: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showActionPerformed

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel15MouseClicked

    private void liet_keActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liet_keActionPerformed
        DefaultTableModel hvlop = (DefaultTableModel) hocvien_lop.getModel();
        hvlop.setRowCount(0); // Clear all rows in JTable
        DefaultTableModel gv_lop = (DefaultTableModel) giangvien_lop.getModel();
        gv_lop.setRowCount(0); // Clear all rows in JTable
        String id_lop = jTextField1.getText();
        try {
            // Establish a single connection to the MySQL database
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Query to retrieve student information
            String sql1 = "SELECT id_hocvien, ten_hocvien FROM hocvien WHERE id_lop = ?";
            PreparedStatement statement1 = conn.prepareStatement(sql1);
            statement1.setString(1, id_lop);
            ResultSet result1 = statement1.executeQuery();

            while (result1.next()) {
                String idHocvien = result1.getString("id_hocvien");
                String tenHocvien = result1.getString("ten_hocvien");
                hvlop.addRow(new Object[]{idHocvien, tenHocvien});
            }
            result1.close();
            statement1.close();

            // Query to retrieve teacher information
            String sql2 = "SELECT id_gv, ten_gv FROM giangvien WHERE id_lop = ?";
            PreparedStatement statement2 = conn.prepareStatement(sql2);
            statement2.setString(1, id_lop);
            ResultSet result2 = statement2.executeQuery();

            while (result2.next()) {
                String idGV = result2.getString("id_gv");
                String tenGV = result2.getString("ten_gv");
                gv_lop.addRow(new Object[]{idGV, tenGV});
            }
            result2.close();
            statement2.close();

            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database connection error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_liet_keActionPerformed

    private void refeshActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refeshActionPerformed
        // TODO add your handling code here:
        DefaultTableModel model = (DefaultTableModel) lopTable.getModel();
        model.setRowCount(0); // Xóa tất cả dòng hiện có trong bảng

        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Thực hiện truy vấn để lấy dữ liệu từ bảng "lop_hoc"
            String sql = "SELECT * FROM lop_hoc";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet
                String id_lop = rs.getString("id_lop");
                String ten_lop = rs.getString("ten_lop");
                String buoi_hoc = rs.getString("buoi_hoc");
                Time tg_bd = rs.getTime("tg_bd");
                Time tg_kt = rs.getTime("tg_kt");
                String phong = rs.getString("phong");

                // Thêm dữ liệu vào DefaultTableModel
                model.addRow(new Object[]{id_lop, ten_lop, buoi_hoc, tg_bd, tg_kt, phong});
            }

            lopTable.getSelectionModel().addListSelectionListener(e -> {
                if (!e.getValueIsAdjusting() && lopTable.getSelectedRow() != -1) {
                    // Khi một dòng được chọn trong bảng, cập nhật các JTextField tương ứng
                    int selectedRow = lopTable.getSelectedRow();
                    jTextField1.setText(lopTable.getValueAt(selectedRow, 0).toString());
                    jTextField2.setText(lopTable.getValueAt(selectedRow, 1).toString());
                    jTextField3.setText(lopTable.getValueAt(selectedRow, 2).toString());
                    jTextField4.setText(lopTable.getValueAt(selectedRow, 3).toString());
                    jTextField5.setText(lopTable.getValueAt(selectedRow, 4).toString());
                    jTextField6.setText(lopTable.getValueAt(selectedRow, 5).toString());
                }
            });

            rs.close();
            statement.close();
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Lỗi kết nối cơ sở dữ liệu: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_refeshActionPerformed

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
            java.util.logging.Logger.getLogger(Ql_Lop_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ql_Lop_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ql_Lop_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ql_Lop_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                Ql_Lop_Frame mainFrame = new Ql_Lop_Frame();
                mainFrame.updateClock();
                mainFrame.setVisible(true);

            }

        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable giangvien_lop;
    private javax.swing.JButton hienthi;
    private javax.swing.JTable hocvien_lop;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JButton liet_ke;
    private javax.swing.JTable lopTable;
    private javax.swing.JButton refesh;
    private javax.swing.JComboBox<String> sapxep;
    private javax.swing.JButton show;
    private javax.swing.JButton sua;
    private javax.swing.JButton them;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField timkiem;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
