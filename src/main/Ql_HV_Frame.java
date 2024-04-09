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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFileChooser;

/**
 *
 * @author HAPPY
 */
public class Ql_HV_Frame extends javax.swing.JFrame {

    private Thread clockThread;

    /**
     * Creates new form Main_Frame
     */
    public Ql_HV_Frame() {
        initComponents();
        customizeTableHeader();
    }

    private void customizeTableHeader() {
        JTableHeader header = hv_table.getTableHeader();
        header.setDefaultRenderer(new HeaderRenderer());
        loadDataToTable(hv_table);
    }

    public void loadDataToTable(JTable table) {
        DefaultTableModel model = (DefaultTableModel) hv_table.getModel();
        model.setRowCount(0); // Xóa tất cả dòng hiện có trong JTable

        try {
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Thực hiện truy vấn để lấy dữ liệu từ bảng "hocvien"
            String sql = "SELECT * FROM hocvien";
            PreparedStatement statement = conn.prepareStatement(sql);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                // Đọc dữ liệu từ ResultSet
                String id_hocvien = rs.getString("id_hocvien");
                String ten_hocvien = rs.getString("ten_hocvien");
                String namsinh = rs.getString("namsinh");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String id_khoa_hoc = rs.getString("id_khoa_hoc");
                String id_lop = rs.getString("id_lop");

                // Thêm dữ liệu vào DefaultTableModel
                model.addRow(new Object[]{id_hocvien, ten_hocvien, namsinh, diachi, email, phone, id_khoa_hoc, id_lop});
            }
            hv_table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (!e.getValueIsAdjusting() && hv_table.getSelectedRow() != -1) {
                        int selectedRow = hv_table.getSelectedRow();
                        jTextField1.setText(hv_table.getValueAt(selectedRow, 0).toString());
                        jTextField2.setText(hv_table.getValueAt(selectedRow, 1).toString());
                        jTextField3.setText(hv_table.getValueAt(selectedRow, 2).toString());
                        jTextField4.setText(hv_table.getValueAt(selectedRow, 3).toString());
                        jTextField5.setText(hv_table.getValueAt(selectedRow, 4).toString());
                        jTextField6.setText(hv_table.getValueAt(selectedRow, 5).toString());
                        jTextField7.setText(hv_table.getValueAt(selectedRow, 6).toString());
                        jTextField8.setText(hv_table.getValueAt(selectedRow, 7).toString());
                    }
                }
            });
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

        sapxep = new javax.swing.JComboBox<>();
        timkiem = new javax.swing.JTextField();
        lichsu = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        show = new javax.swing.JButton();
        hienthi = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        inDS = new javax.swing.JButton();
        xoa = new javax.swing.JButton();
        sua = new javax.swing.JButton();
        them = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        hv_table = new javax.swing.JTable();
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

        sapxep.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        sapxep.setForeground(new java.awt.Color(0, 102, 204));
        sapxep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Theo ID", "Theo Tên" }));
        getContentPane().add(sapxep, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 140, 110, 30));

        timkiem.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        timkiem.setForeground(new java.awt.Color(102, 102, 102));
        getContentPane().add(timkiem, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 170, 160, 40));

        lichsu.setBackground(new java.awt.Color(255, 51, 102));
        lichsu.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lichsu.setForeground(new java.awt.Color(255, 255, 255));
        lichsu.setText("Lịch sử");
        lichsu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lichsuActionPerformed(evt);
            }
        });
        getContentPane().add(lichsu, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 690, 120, 40));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setText("Tìm kiếm HV (ID):");
        getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, 190, 30));

        show.setBackground(new java.awt.Color(204, 204, 204));
        show.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        show.setText("Tìm kiếm");
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });
        getContentPane().add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 170, 120, 40));

        hienthi.setBackground(new java.awt.Color(204, 204, 204));
        hienthi.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        hienthi.setText("Hiển thị");
        hienthi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hienthiActionPerformed(evt);
            }
        });
        getContentPane().add(hienthi, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 130, 120, 40));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel12.setText("Sắp xếp DS:");
        getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(770, 140, 110, 30));

        inDS.setBackground(new java.awt.Color(102, 102, 255));
        inDS.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        inDS.setForeground(new java.awt.Color(255, 255, 255));
        inDS.setText("Xuất file Excel");
        inDS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inDSActionPerformed(evt);
            }
        });
        getContentPane().add(inDS, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 690, 170, 40));

        xoa.setBackground(new java.awt.Color(102, 102, 255));
        xoa.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        xoa.setForeground(new java.awt.Color(255, 255, 255));
        xoa.setText("Xoá");
        xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                xoaActionPerformed(evt);
            }
        });
        getContentPane().add(xoa, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 690, 120, 40));

        sua.setBackground(new java.awt.Color(102, 102, 255));
        sua.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        sua.setForeground(new java.awt.Color(255, 255, 255));
        sua.setText("Sửa");
        sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suaActionPerformed(evt);
            }
        });
        getContentPane().add(sua, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 690, 120, 40));

        them.setBackground(new java.awt.Color(102, 102, 255));
        them.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        them.setForeground(new java.awt.Color(255, 255, 255));
        them.setText("Thêm");
        them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                themActionPerformed(evt);
            }
        });
        getContentPane().add(them, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 690, 120, 40));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel6.setText("Email:");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 450, 110, 30));

        jTextField5.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(51, 51, 51));
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField5, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 480, 220, 40));

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel9.setText("SĐT");
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
        jLabel10.setText("Khoá học:");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 590, 110, 30));

        jTextField7.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField7.setForeground(new java.awt.Color(51, 51, 51));
        jTextField7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField7ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 620, 220, 40));

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel11.setText("Lớp học:");
        getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 660, 160, 30));

        jTextField8.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField8.setForeground(new java.awt.Color(51, 51, 51));
        jTextField8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField8ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField8, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 690, 220, 40));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel4.setText("Năm sinh:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 320, 110, 30));

        jTextField3.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField3.setForeground(new java.awt.Color(51, 51, 51));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 350, 220, 40));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel5.setText("Địa chỉ:");
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
        jLabel3.setText("Họ tên học viên:");
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
        jLabel2.setText("ID Học viên:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 110, 30));

        jTextField1.setFont(new java.awt.Font("Segoe UI", 2, 18)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(51, 51, 51));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 220, 40));

        hv_table.setAutoCreateRowSorter(true);
        hv_table.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        hv_table.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        hv_table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Ho va Ten", "Nam sinh", "Dia chi", "Email", "SDT", "Khoa hoc", "Lop hoc"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, true, true, true, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        hv_table.setRowHeight(25);
        hv_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        hv_table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(hv_table);
        if (hv_table.getColumnModel().getColumnCount() > 0) {
            hv_table.getColumnModel().getColumn(0).setResizable(false);
            hv_table.getColumnModel().getColumn(0).setPreferredWidth(45);
            hv_table.getColumnModel().getColumn(6).setResizable(false);
            hv_table.getColumnModel().getColumn(6).setPreferredWidth(20);
            hv_table.getColumnModel().getColumn(7).setResizable(false);
            hv_table.getColumnModel().getColumn(7).setPreferredWidth(20);
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 210, 990, 460));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 0, 255));
        jLabel13.setText("QUẢN LÝ THÔNG TIN HỌC VIÊN");
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

    private void jTextField8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField8ActionPerformed

    private void xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_xoaActionPerformed
        // TODO add your handling code here:
        try {
            String id_hocvien = jTextField1.getText();

            // Hiển thị hộp thoại xác nhận
            int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa học viên này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);

            if (confirm == JOptionPane.YES_OPTION) {
                // Kết nối đến cơ sở dữ liệu MySQL
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

                // Tạo lệnh gọi stored procedure
                String sql = "CALL XoaHocVien(?)";
                CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

                // Thiết lập tham số cho stored procedure
                statement.setString(1, id_hocvien);

                // Thực hiện stored procedure để xóa học viên
                statement.executeUpdate();

                // Đóng kết nối
                statement.close();
                conn.close();

                // Cập nhật lại dữ liệu trong JTable
                loadDataToTable(hv_table);

                // Xóa dữ liệu trong các trường sau khi xóa học viên
                jTextField1.setText("");
                jTextField2.setText("");
                jTextField3.setText("");
                jTextField4.setText("");
                jTextField5.setText("");
                jTextField6.setText("");
                jTextField7.setText("");
                jTextField8.setText("");

                JOptionPane.showMessageDialog(this, "Xóa học viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi xóa học viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_xoaActionPerformed

    private void inDSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inDSActionPerformed
        // TODO add your handling code here:
        exportExcel(hv_table);
    }//GEN-LAST:event_inDSActionPerformed

    private void themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_themActionPerformed
        // TODO add your handling code here:
        try {
            String id_hocvien = jTextField1.getText();
            String ten_hocvien = jTextField2.getText();
            String namsinh = jTextField3.getText();
            String diachi = jTextField4.getText();
            String email = jTextField5.getText();
            String phone = jTextField6.getText();
            String id_khoa_hoc = jTextField7.getText();
            String id_lop = jTextField8.getText();

            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (id_hocvien.isEmpty() || ten_hocvien.isEmpty() || namsinh.isEmpty() || diachi.isEmpty() || email.isEmpty() || phone.isEmpty() || id_khoa_hoc.isEmpty() || id_lop.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin học viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu có trường nhập liệu rỗng
            }

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh gọi stored procedure
            String sql = "CALL ThemHocVien(?, ?, ?, ?, ?, ?, ?, ?)";
            CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

            // Thiết lập tham số cho stored procedure
            statement.setString(1, id_hocvien);
            statement.setString(2, ten_hocvien);
            statement.setString(3, namsinh);
            statement.setString(4, diachi);
            statement.setString(5, email);
            statement.setString(6, phone);
            statement.setString(7, id_khoa_hoc);
            statement.setString(8, id_lop);

            // Thực hiện stored procedure để thêm học viên
            statement.executeUpdate();

            // Đóng kết nối
            statement.close();
            conn.close();

            // Cập nhật lại dữ liệu trong JTable
            loadDataToTable(hv_table);

            // Xóa dữ liệu trong các trường sau khi thêm
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");

            JOptionPane.showMessageDialog(this, "Thêm học viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi thêm học viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_themActionPerformed

    private void suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suaActionPerformed
        // TODO add your handling code here:
        try {
            String id_hocvien = jTextField1.getText();
            String ten_hocvien = jTextField2.getText();
            String namsinh = jTextField3.getText();
            String diachi = jTextField4.getText();
            String email = jTextField5.getText();
            String phone = jTextField6.getText();
            String id_khoa_hoc = jTextField7.getText();
            String id_lop = jTextField8.getText();

            // Kiểm tra xem các trường nhập liệu có rỗng không
            if (ten_hocvien.isEmpty() || namsinh.isEmpty() || diachi.isEmpty() || email.isEmpty() || phone.isEmpty() || id_khoa_hoc.isEmpty() || id_lop.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin học viên.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return; // Thoát khỏi phương thức nếu có trường nhập liệu rỗng
            }

            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh gọi stored procedure
            String sql = "CALL SuaThongTinHocVien(?, ?, ?, ?, ?, ?, ?, ?)";
            CallableStatement statement = (CallableStatement) conn.prepareCall(sql);

            // Thiết lập tham số cho stored procedure
            statement.setString(1, id_hocvien);
            statement.setString(2, ten_hocvien);
            statement.setString(3, namsinh);
            statement.setString(4, diachi);
            statement.setString(5, email);
            statement.setString(6, phone);
            statement.setString(7, id_khoa_hoc);
            statement.setString(8, id_lop);

            // Thực hiện stored procedure để cập nhật thông tin học viên
            statement.executeUpdate();

            // Đóng kết nối
            statement.close();
            conn.close();

            // Cập nhật lại dữ liệu trong JTable
            loadDataToTable(hv_table);

            // Xóa dữ liệu trong các trường sau khi cập nhật
            jTextField1.setText("");
            jTextField2.setText("");
            jTextField3.setText("");
            jTextField4.setText("");
            jTextField5.setText("");
            jTextField6.setText("");
            jTextField7.setText("");
            jTextField8.setText("");

            JOptionPane.showMessageDialog(this, "Cập nhật thông tin học viên thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi cập nhật thông tin học viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_suaActionPerformed


    private void hienthiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hienthiActionPerformed
        // TODO add your handling code here:
        // Lấy lựa chọn từ JComboBox
        String sapXepTheo = sapxep.getSelectedItem().toString();

        // Gọi hàm SQL tương ứng để sắp xếp dữ liệu
        String sqlFunction = "";
        if (sapXepTheo.equals("Theo Tên")) {
            sqlFunction = "SapXepVaHienThiHocVienTheoTen()"; // Sử dụng stored procedure sắp xếp theo tên
        } else if (sapXepTheo.equals("Theo ID")) {
            sqlFunction = "SapXepVaHienThiHocVienTheoID()"; // Sử dụng stored procedure sắp xếp theo ID
        }

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ql_lop_av", "root", "");

            // Gọi stored procedure để sắp xếp và hiển thị dữ liệu
            CallableStatement cs = (CallableStatement) connection.prepareCall("{call " + sqlFunction + "}");
            ResultSet rs = cs.executeQuery();

            // Cập nhật dữ liệu trong bảng hv_table dựa trên kết quả của stored procedure
            DefaultTableModel model = (DefaultTableModel) hv_table.getModel();
            model.setRowCount(0); // Xóa dữ liệu cũ
            while (rs.next()) {
                // Lấy thông tin từ ResultSet và thêm vào bảng
                String id_hocvien = rs.getString("id_hocvien");
                String ten_hocvien = rs.getString("ten_hocvien");
                String namsinh = rs.getString("namsinh");
                String diachi = rs.getString("diachi");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String id_khoa_hoc = rs.getString("id_khoa_hoc");
                String id_lop = rs.getString("id_lop");

                // Thêm dữ liệu vào bảng
                model.addRow(new Object[]{id_hocvien, ten_hocvien, namsinh, diachi, email, phone, id_khoa_hoc, id_lop});
            }

            // Refresh lại bảng
            hv_table.repaint();

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
        // TODO add your handling code here:
        try {
            String idHocVienCanTim = timkiem.getText();
            // Kết nối đến cơ sở dữ liệu MySQL
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/ql_lop_av", "root", "");

            // Tạo lệnh SQL để tìm kiếm học viên
            String sql = "SELECT * FROM hocvien WHERE id_hocvien = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, idHocVienCanTim);

            // Thực hiện truy vấn
            ResultSet result = statement.executeQuery();

            // Cập nhật dữ liệu lên bảng hv_table
            DefaultTableModel model = (DefaultTableModel) hv_table.getModel();
            model.setRowCount(0); // Xóa tất cả dữ liệu hiện tại trong bảng

            boolean found = false; // Biến kiểm tra học viên có được tìm thấy hay không

            while (result.next()) {
                found = true;
                String idHocVien = result.getString("id_hocvien");
                String tenHocVien = result.getString("ten_hocvien");
                String namSinh = result.getString("namsinh");
                String diaChi = result.getString("diachi");
                String email = result.getString("email");
                String phone = result.getString("phone");
                String idKhoaHoc = result.getString("id_khoa_hoc");
                String idLop = result.getString("id_lop");

                model.addRow(new Object[]{idHocVien, tenHocVien, namSinh, diaChi, email, phone, idKhoaHoc, idLop});
            }

            // Đóng kết nối
            statement.close();
            conn.close();

            // Kiểm tra nếu không tìm thấy học viên
            if (!found) {
                JOptionPane.showMessageDialog(this, "Không tìm thấy học viên có ID: " + idHocVienCanTim, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm học viên: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_showActionPerformed

    private void lichsuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lichsuActionPerformed
        // TODO add your handling code here:
        LS_Frame ls = new LS_Frame();
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
            java.util.logging.Logger.getLogger(Ql_HV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ql_HV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ql_HV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ql_HV_Frame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Ql_HV_Frame mainFrame = new Ql_HV_Frame();
                mainFrame.updateClock();
                mainFrame.setVisible(true);

            }

        });

    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton hienthi;
    private javax.swing.JTable hv_table;
    private javax.swing.JButton inDS;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
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
    private javax.swing.JTextField jTextField8;
    private javax.swing.JButton lichsu;
    private javax.swing.JComboBox<String> sapxep;
    private javax.swing.JButton show;
    private javax.swing.JButton sua;
    private javax.swing.JButton them;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JTextField timkiem;
    private javax.swing.JButton xoa;
    // End of variables declaration//GEN-END:variables
}
