/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.WritePDF;
import BUS.busQuanLyHoaDon;
import DAO.*;
import DTO.*;
import Format.PriceFormatter;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Xoan Tran
 */
public class fHoaDon extends javax.swing.JFrame {

    /**
     * Creates new form fNhacungcap
     */
    public String maNV;
    public ArrayList<HoaDon> DanhSachHoaDon;
    public long count, SoTrang, Trang = 1;
    public ArrayList<HoaDon> DuLieuMau;

    public fHoaDon() {
        initComponents();
        setIcon();
        build();
    }

    public fHoaDon(String id) {
        maNV = id;
        initComponents();
        setIcon();
        DanhSachHoaDon = daoQuanLyHoaDon.getInstance().getListHoaDon();
        DuLieuMau = DanhSachHoaDon;
        btnXemChiTiet.setEnabled(false);
        build();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/Logo2.png")));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ArrayList<HoaDon> DuLieuMau = daoQuanLyHoaDon.getInstance().getListHoaDon();
        String[] columnName = {"STT", "Mã HĐ", "Mã nhân viên", "Mã khách hàng", "Mã khuyến mãi", "Ngày lập", "Giờ lập", "Tổng tiền"};
        Object[][] rows = new Object[DuLieuMau.size()][8];
        for(int i = 0; i < DuLieuMau.size(); i++){
            rows[i][0] = i+1;
            rows[i][1] = DuLieuMau.get(i).getMaHoaDon();
            rows[i][2] = DuLieuMau.get(i).getMaNhanVien();
            rows[i][3] = DuLieuMau.get(i).getMaKhachHang();
            rows[i][4] = DuLieuMau.get(i).getMaKhuyenMai();
            rows[i][5] = DuLieuMau.get(i).getNgayLap();
            rows[i][6] = DuLieuMau.get(i).getGioLap();
            rows[i][7] = DuLieuMau.get(i).getTongTien();
            /*
            if(DuLieuMau.get(i).hinh_anh != null){
                ImageIcon image = new ImageIcon(new ImageIcon(DuLieuMau.get(i).hinh_anh).getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH) );
                //ImageIcon image = new ImageIcon(DuLieuMau.get(i).hinh_anh);
                //ImageIcon image = new ImageIcon(getClass().getResource("/Image/fXuatKho.jpg"));
                rows[i][6] = image;

            }
            else{
                rows[i][6] = "Chưa có hình ảnh";
            }
            */
        }
        DefaultTableModel model = new DefaultTableModel (rows,columnName)
        {
            @Override
            public Class getColumnClass(int c) {
                switch (c)

                {
                    //case 6:
                    //return ImageIcon.class;
                    default:
                    return Object.class;
                }
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        jTableHoaDon = new javax.swing.JTable(model);
        jTextFieldTimKiem = new javax.swing.JTextField();
        jButtonExcel = new javax.swing.JButton();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonLamMoi = new javax.swing.JButton();
        jLabelKetQua = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonHuy = new javax.swing.JButton();
        btnXemChiTiet = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jButtonNhoMax = new javax.swing.JButton();
        jButtonNho = new javax.swing.JButton();
        jLabelTrang = new javax.swing.JLabel();
        jButtonLon = new javax.swing.JButton();
        jButtonLonMax = new javax.swing.JButton();
        jLabelSoTrang = new javax.swing.JLabel();
        jComboBoxNhanVien = new javax.swing.JComboBox<>();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nhà cung cấp");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 102, 102));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý cửa hàng");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jPanel13.setBackground(new java.awt.Color(0, 153, 153));
        jPanel13.setAlignmentX(0.2F);

        /*
        jTableHoaDon.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        jTableHoaDon.setRequestFocusEnabled(false);
        jTableHoaDon.setRowHeight(50);
        jTableHoaDon.setRowSelectionAllowed(true);
        jTableHoaDon.setAutoCreateRowSorter(true);
        jTableHoaDon.setUpdateSelectionOnSort(false);
        jTableHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableHoaDonMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableHoaDon);
        if (jTableHoaDon.getColumnModel().getColumnCount() > 0) {
            jTableHoaDon.getColumnModel().getColumn(0).setMinWidth(30);
            jTableHoaDon.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableHoaDon.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jTextFieldTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTimKiemActionPerformed(evt);
            }
        });
        jTextFieldTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTextFieldTimKiemKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTimKiemKeyReleased(evt);
            }
        });

        jButtonExcel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ImageIcon img = new ImageIcon(getClass().getResource("/icon/icons8-microsoft-excel-40.png"));
        ImageIcon Img = new ImageIcon(img.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonExcel.setIcon(Img);
        jButtonExcel.setText("Excel");
        jButtonExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcelActionPerformed(evt);
            }
        });

        jButtonTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ImageIcon imgTimKiem = new ImageIcon(getClass().getResource("/icon/icons8-search.png"));
        ImageIcon ImgTimKiem = new ImageIcon(imgTimKiem.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonTimKiem.setIcon(ImgTimKiem);
        jButtonTimKiem.setText("Tìm kiếm");
        jButtonTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTimKiemActionPerformed(evt);
            }
        });

        jButtonLamMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ImageIcon imgLamMoi = new ImageIcon(getClass().getResource("/icon/icons8-synchronize-30.png"));
        ImageIcon ImgLamMoi = new ImageIcon(imgLamMoi.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonLamMoi.setIcon(ImgLamMoi);
        jButtonLamMoi.setText("Tải lại");
        jButtonLamMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLamMoiActionPerformed(evt);
            }
        });

        jLabelKetQua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelKetQua.setForeground(new java.awt.Color(255, 255, 255));
        jLabelKetQua.setText("Có tổng cộng 000 kết quả");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH HÓA ĐƠN");

        jButtonHuy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonHuy.setText("In PDF");
        ImageIcon imgHuy = new ImageIcon(getClass().getResource("/icon/icons8-waste-48.png"));
        ImageIcon ImgHuy = new ImageIcon(imgHuy.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonHuy.setIcon(ImgHuy);
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        btnXemChiTiet.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnXemChiTiet.setText("Xem chi tiết");
        btnXemChiTiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXemChiTietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelKetQua)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jButtonHuy)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButtonExcel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnXemChiTiet))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLamMoi)))
                .addContainerGap())
            .addComponent(jScrollPane7, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonExcel, jButtonHuy});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimKiem)
                    .addComponent(jButtonLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHuy)
                    .addComponent(jButtonExcel)
                    .addComponent(btnXemChiTiet))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabelKetQua)
                .addGap(19, 19, 19)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 348, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel3.setBackground(new java.awt.Color(0, 153, 153));

        jButtonNhoMax.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonNhoMax.setText("<<");
        jButtonNhoMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNhoMaxActionPerformed(evt);
            }
        });

        jButtonNho.setText("<");
        jButtonNho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonNhoActionPerformed(evt);
            }
        });

        jLabelTrang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelTrang.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTrang.setText("jLabel2");

        jButtonLon.setText(">");
        jButtonLon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLonActionPerformed(evt);
            }
        });

        jButtonLonMax.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButtonLonMax.setText(">>");
        jButtonLonMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLonMaxActionPerformed(evt);
            }
        });

        jLabelSoTrang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabelSoTrang.setForeground(new java.awt.Color(255, 255, 255));
        jLabelSoTrang.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonNhoMax, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonNho)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabelTrang)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonLon)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonLonMax)
                .addGap(18, 18, 18)
                .addComponent(jLabelSoTrang)
                .addGap(10, 10, 10))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonNhoMax)
                    .addComponent(jButtonNho)
                    .addComponent(jButtonLon)
                    .addComponent(jButtonLonMax)
                    .addComponent(jLabelSoTrang)
                    .addComponent(jLabelTrang))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(329, 329, 329)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(361, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, 470, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jComboBoxNhanVien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jComboBoxNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxNhanVienActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 610, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jComboBoxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jComboBoxNhanVien, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBoxNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxNhanVienActionPerformed
        String valueIn = String.valueOf(jComboBoxNhanVien.getSelectedItem());
        if ("Thoát".equals(valueIn)) {
            JFrame dn = new fDangNhap();
            dn.setVisible(true);
            dispose();
        }
        if ("Thông tin".equals(valueIn)) {
            JFrame nv = new fViewNhanVien(maNV, maNV);
            nv.setVisible(true);
        }
        jComboBoxNhanVien.setSelectedIndex(0);
    }//GEN-LAST:event_jComboBoxNhanVienActionPerformed

    
    private void jButtonNhoMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNhoMaxActionPerformed
        Trang = 1;
        ArrayList<HoaDon> table = DAO.daoQuanLyHoaDon.getInstance().get20HoaDon(DanhSachHoaDon, Trang);
        listDanhSachHoaDon(table);
        jLabelTrang.setText("1");
        jLabelSoTrang.setText("1/" + SoTrang);
    }//GEN-LAST:event_jButtonNhoMaxActionPerformed

    private void jButtonNhoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonNhoActionPerformed
        if (Trang > 1) {
            Trang--;
            ArrayList<HoaDon> table = DAO.daoQuanLyHoaDon.getInstance().get20HoaDon(DanhSachHoaDon, Trang);
            listDanhSachHoaDon(table);
            jLabelTrang.setText("" + Trang);
            jLabelSoTrang.setText(Trang + "/" + SoTrang); // do thằng này custom code
        } // đẩy lên đi, sau t đẩy code t viết cũng được
    }//GEN-LAST:event_jButtonNhoActionPerformed

    private void jButtonLonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLonActionPerformed
        if (Trang < SoTrang) {
            Trang++;
            ArrayList<HoaDon> table = DAO.daoQuanLyHoaDon.getInstance().get20HoaDon(DanhSachHoaDon, Trang);
            listDanhSachHoaDon(table);
            jLabelTrang.setText("" + Trang);
            jLabelSoTrang.setText(Trang + "/" + SoTrang);
        }
    }//GEN-LAST:event_jButtonLonActionPerformed

    private void jButtonLonMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLonMaxActionPerformed
        Trang = SoTrang;
        ArrayList<HoaDon> table = DAO.daoQuanLyHoaDon.getInstance().get20HoaDon(DanhSachHoaDon, Trang);
        listDanhSachHoaDon(table);
        jLabelTrang.setText("" + SoTrang);
        jLabelSoTrang.setText(SoTrang + "/" + SoTrang);
    }//GEN-LAST:event_jButtonLonMaxActionPerformed

    private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyActionPerformed
        int selectedRowIndex = jTableHoaDon.getSelectedRow();
        String id = jTableHoaDon.getValueAt(selectedRowIndex, 1).toString();
        if(selectedRowIndex>=0){
         new WritePDF().writeHoaDon(id);
        } else {
            JOptionPane.showMessageDialog(this, "Chọn một hóa đơn để in");
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonHuyActionPerformed

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        invalidate();
        validate();
        repaint();
        DuLieuMau = daoQuanLyHoaDon.getInstance().getListHoaDon();
        build();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        DanhSachHoaDon = DuLieuMau;
        FindList();
        jButtonHuy.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    private void jButtonExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcelActionPerformed
        try {
            boolean result = busQuanLyHoaDon.getInstance().XuatExcel(DuLieuMau);
            if(result) {
                JOptionPane.showMessageDialog(this, "File Excel đã được lưu tại C:\\demo");
            }else {
                JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra khi xuất file Excel");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Đã có lỗi xảy ra khi xuất file Excel");
            Logger.getLogger(fHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButtonExcelActionPerformed

    private void jTextFieldTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemKeyReleased
        if ("".equals(jTextFieldTimKiem.getText())) {
            build();
        }
    }//GEN-LAST:event_jTextFieldTimKiemKeyReleased

    private void jTextFieldTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DanhSachHoaDon = DuLieuMau;
            FindList();
            jButtonHuy.setEnabled(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimKiemKeyPressed

    private void jTextFieldTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimKiemActionPerformed

    private void jTableHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableHoaDonMouseClicked
        btnXemChiTiet.setEnabled(true);
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            int selectedRowIndex = jTableHoaDon.getSelectedRow();
            String id = jTableHoaDon.getValueAt(selectedRowIndex, 1).toString();
            JFrame CtHoaDon = new fChiTietHoaDon(maNV, id);
            CtHoaDon.setVisible(true);
        }
        jButtonHuy.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jTableHoaDonMouseClicked

    private void btnXemChiTietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXemChiTietActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
        int selectedRowIndex = jTableHoaDon.getSelectedRow();
        String id = jTableHoaDon.getValueAt(selectedRowIndex, 1).toString();
        
        JFrame CtHoaDon = new fChiTietHoaDon(maNV, id);
        CtHoaDon.setVisible(true);
    }//GEN-LAST:event_btnXemChiTietActionPerformed

    public void NhanVienDangNhap() {
        if (maNV.length() > 0) {
            TaiKhoan tk = DAO.daoTaiKhoan.getInstance().getTaiKhoan(maNV);
            NhanVien nv = DAO.daoTaiKhoan.getInstance().getNhanVien(tk.getMaNV());
            jComboBoxNhanVien.addItem(nv.getTenNV());
            jComboBoxNhanVien.addItem("Thông tin");
            jComboBoxNhanVien.addItem("Thoát");
        } else {
            jComboBoxNhanVien.addItem("Chưa đăng nhập");
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame kh = new fHoaDon("NV12"); //đây là hàm để chạy
                kh.setVisible(true);
            } // vì đang lấy lại form của nhà cung cấp nên nó hiển thị dât của nhà cung cấp
            // có project ban đầu nữa k
            // có 2 project luôn đấy// ok nhé  1 cột r
        });
    }

    public void build() {
        jButtonHuy.setEnabled(false);
        DanhSachHoaDon = DuLieuMau;
        this.count = this.DanhSachHoaDon.size();
        jLabelKetQua.setText("Có tổng cộng " + count + " kết quả");
        if (count % 20 == 0) {
            SoTrang = count / 20;
        } else {
            SoTrang = count / 20 + 1;
        }
        jLabelSoTrang.setText("1/" + SoTrang);
        jLabelTrang.setText("1");
        ArrayList<HoaDon> table = DAO.daoQuanLyHoaDon.getInstance().get20HoaDon(DanhSachHoaDon, 1);
        listDanhSachHoaDon(table);
        NhanVienDangNhap();//sau khi m sửa phân trang thì mất
    }

    public void FindList() {
        this.DanhSachHoaDon = DAO.daoQuanLyHoaDon.getInstance().FindListHoaDon(DuLieuMau, jTextFieldTimKiem.getText());
        if (DanhSachHoaDon.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Không có dữ liệu hóa đơn",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            build();
        } else {
            this.count = this.DanhSachHoaDon.size();
            jLabelKetQua.setText("Có tổng cộng " + count + " kết quả");
            if (count % 20 == 0) {
                SoTrang = count / 20;
            } else {
                SoTrang = count / 20 + 1;
            }
            jLabelSoTrang.setText("1/" + SoTrang);
            jLabelTrang.setText("1");
            ArrayList<HoaDon> table = DAO.daoQuanLyHoaDon.getInstance().get20HoaDon(DanhSachHoaDon, 1);
            listDanhSachHoaDon(table);
        }
    }

    public void listDanhSachHoaDon(ArrayList<HoaDon> arr) {
        DefaultTableModel model = (DefaultTableModel) jTableHoaDon.getModel();
        while (jTableHoaDon.getRowCount() > 0) {
            model.removeRow(0);
        }
//        arr.stream().forEach((item) -> {
////            ImageIcon icon = new ImageIcon(item.hinh_anh); phần này phải sử lại
//            model.addRow(new Object[]{item.getMaKH(), item.getTenKH(), item.getDiaChi(), item.getSDT(), item.getTrangThai()});
//        });
        if(arr.isEmpty()==false)
        {
            int stt = 1;
            for (HoaDon hd : arr) {
                model.addRow(new String[]{
                    String.valueOf(stt),
                    hd.getMaHoaDon(),
                    hd.getMaNhanVien(),
                    hd.getMaKhachHang(),
                    hd.getMaKhuyenMai(),
                    String.valueOf(hd.getNgayLap()),
                    String.valueOf(hd.getGioLap()),
                    PriceFormatter.format(hd.getTongTien())}
                );
                stt++;
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnXemChiTiet;
    private javax.swing.JButton jButtonExcel;
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonLon;
    private javax.swing.JButton jButtonLonMax;
    private javax.swing.JButton jButtonNho;
    private javax.swing.JButton jButtonNhoMax;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JComboBox<String> jComboBoxNhanVien;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelKetQua;
    private javax.swing.JLabel jLabelSoTrang;
    private javax.swing.JLabel jLabelTrang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTableHoaDon;
    private javax.swing.JTextField jTextFieldTimKiem;
    // End of variables declaration//GEN-END:variables
}