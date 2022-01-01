/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_view.GUI;

import model.DTO.ChiTietHoaDon;
import model.DTO.KhuyenMai;
import model.DTO.KhachHang;
import model.DTO.HoaDon;
import model.DTO.SanPham;
import model.DAO.daoSanPham;
import model.DAO.daoLoaiSanPham;
import model.DAO.daoKhachHang;
import model.DAO.daoKhuyenMai;
import model.DAO.daoQuanLyChiTietHoaDon;
import model.DAO.daoQuanLyHoaDon;
import model.DAO.daoNhanVien;
import model.DAO.WritePDF;
import model.DAO.PriceFormatter;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.lang.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;

public class fBanHang extends javax.swing.JFrame {

    public String id_nv;
    ArrayList<ChiTietHoaDon> dscthd = new ArrayList<>();

    public fBanHang() {
        initComponents();
        setIcon();
        LoadThongTinSanPham();
        setData();
        showComboboxKhuyenMai();
        showComboboxKhachHang();
//        showComboboxLoaiSanPham();
    }

    public fBanHang(String idnv) {
        this.id_nv = idnv;
        initComponents();
        setIcon();
        LoadThongTinSanPham();
        //showComboboxLoaiSanPham();
        setData();
        showComboboxKhuyenMai();
        showComboboxKhachHang();
    }

    public void showComboboxKhuyenMai() {
        comboboxKhuyenMai.removeAllItems();
        ArrayList<KhuyenMai> arr = daoKhuyenMai.getInstance().getListKhuyenMai();
        for (int i = 0; i < arr.size(); i++) {
            if ("Đang diễn ra".equals(arr.get(i).getTrangThai()) || "Không khuyến mãi".equals(arr.get(i).getTenKM())) {
                comboboxKhuyenMai.addItem(arr.get(i).getMaKM() + " " + arr.get(i).getTenKM());
            }
        }
    }

    public void showComboboxKhachHang() {
        comboboxKhachHang.removeAllItems();
        comboboxKhachHang.addItem(null);
        ArrayList<KhachHang> arr = daoKhachHang.getInstance().getListKhachHang();
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).getTrangThai() == 0) {
                comboboxKhachHang.addItem(arr.get(i).getMaKH() + " " + arr.get(i).getTenKH());
            }
        }
        jButtonThem.setEnabled(false);
    }

    private void setData() {
        txtNguoiBan.setText(daoNhanVien.getInstance().getNameNVByID(this.id_nv));
        txtNguoiBan.setEditable(false);
        txtMaHĐ.setText(daoQuanLyHoaDon.getInstance().getNextID());
        txtMaHĐ.setEditable(false);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txtNgayLap.setText(LocalDate.now().toString());
                txtGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
            }
        }, 0, 1000);
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/Logo2.png")));
    }

    public void LoadThongTinSanPham() {
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        while (jTableThongTinSanPham.getRowCount() > 0) {
            model.removeRow(0);
        }
        ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPham();
        if (arr.isEmpty() == false) {
            for (SanPham sp : arr) {
                model.addRow(new String[]{
                    sp.getMaSP(),
                    sp.getMaLSP(),
                    sp.getTenSP(),
                    PriceFormatter.format(sp.getDonGia()),
                    String.valueOf(sp.getSoLuong()),
                    sp.getFileNameHinhAnh(),});
            }
        }

    }

    public void listDanhSachSanPhamTheoLoai(String ten_loai_sp) {

        if ("Tất cả".equals(ten_loai_sp)) {
            listDanhSachSanPham();
        } else {
            DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
            while (jTableThongTinSanPham.getRowCount() > 0) {
                model.removeRow(0);
            }
            //int id_loai = daoLoaiSanPham.getInstance().getIDLoaiSanPham(ten_loai_sp).id_loai_sp;
            ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPham();
            for (int i = 0; i < arr.size(); i++) {
                if (arr.get(i).getMaLSP() == daoLoaiSanPham.getInstance().getIDLoaiSanPham(ten_loai_sp).getMaLSP()) {
                    String Loai_sp = daoLoaiSanPham.getInstance().getLoaiSanPham(arr.get(i).getMaLSP()).getTenLSP();
                    model.addRow(new Object[]{arr.get(i).getMaSP(), arr.get(i).getTenSP(), Loai_sp});

                }
            }
            //System.out.println("Khac");
        }
    }

    public void listDanhSachSanPham() {
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        while (jTableThongTinSanPham.getRowCount() > 0) {
            model.removeRow(0);
        }
        ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPham();
        for (int i = 0; i < arr.size(); i++) {
            String Loai_sp = daoLoaiSanPham.getInstance().getLoaiSanPham(arr.get(i).getMaLSP()).getTenLSP();
            model.addRow(new Object[]{arr.get(i).getMaSP(), arr.get(i).getTenSP(), Loai_sp,});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableThongTinSanPham = new javax.swing.JTable();
        jTextFieldTimKiem = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonLamMoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaHĐ = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        comboboxKhachHang = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        txtGioLap = new javax.swing.JTextField();
        txtTongtien = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        comboboxKhuyenMai = new javax.swing.JComboBox<>();
        txtNguoiBan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabelLoadhinhAnh = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableChitietHoaDon = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtSoLuong = new javax.swing.JTextField();
        jButtonThem = new javax.swing.JButton();
        jLabelLoadIDSanPham = new javax.swing.JTextField();
        jLabelLoadTenSanPham = new javax.swing.JTextField();
        jLabelLoadLoaiSanPham = new javax.swing.JTextField();
        labelDonGia = new javax.swing.JTextField();
        btnThanhToan = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh Sách Thông Tin Sản Phẩm");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Quản lý cửa hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1))
        );

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));

        jTableThongTinSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableThongTinSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
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
                "Mã sản phẩm", "Mã Loại", "Tên", "Đơn giá", "Số lượng", "Hình ảnh"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableThongTinSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableThongTinSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTableThongTinSanPham);
        jTableThongTinSanPham.setRowHeight(30);
        jTableThongTinSanPham.setAutoCreateRowSorter(true);

        jTextFieldTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTimKiemKeyReleased(evt);
            }
        });

        jButtonTimKiem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        ImageIcon imgTimKiem = new ImageIcon(getClass().getResource("/icon/icons8-search.png"));
        ImageIcon ImgTimKiem = new ImageIcon(imgTimKiem.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonTimKiem.setIcon(ImgTimKiem);
        jButtonTimKiem.setText("Tìm Kiếm");
        jButtonTimKiem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonTimKiemMouseClicked(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 665, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonTimKiem)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonLamMoi))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimKiem)
                    .addComponent(jButtonLamMoi))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Hóa đơn bán hàng");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Mã hóa đơn:");

        txtMaHĐ.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Khách hàng");

        comboboxKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboboxKhachHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxKhachHangActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Tổng tiền");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Ngày lập");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Giờ lập");

        txtNgayLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtGioLap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        txtTongtien.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Khuyến mãi:");

        comboboxKhuyenMai.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboboxKhuyenMai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxKhuyenMai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxKhuyenMaiActionPerformed(evt);
            }
        });

        txtNguoiBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Người bán");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(232, 232, 232))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtNguoiBan, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtMaHĐ, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayLap)
                            .addComponent(txtGioLap, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(comboboxKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel10)
                                        .addGap(15, 15, 15)))
                                .addGap(17, 17, 17)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboboxKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(83, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaHĐ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(comboboxKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtGioLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(comboboxKhuyenMai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiBan)
                    .addComponent(jLabel14))
                .addGap(7, 7, 7))
        );

        jLabelLoadhinhAnh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelLoadhinhAnh.setText("hinh");

        jTableChitietHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(jTableChitietHoaDon);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Mã SP:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Tên SP:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Nhãn hàng:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Đơn giá");

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel19.setText("Số lượng");

        txtSoLuong.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButtonThem.setText("Thêm");
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jLabelLoadIDSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelLoadTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelLoadTenSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelLoadTenSanPhamActionPerformed(evt);
            }
        });

        jLabelLoadLoaiSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelLoadLoaiSanPham.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jLabelLoadLoaiSanPhamActionPerformed(evt);
            }
        });

        labelDonGia.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel19)
                            .addComponent(jLabel18))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 189, Short.MAX_VALUE)
                        .addComponent(jButtonThem))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel16)
                                .addGap(48, 48, 48)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabelLoadIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(79, 79, 79))
                                    .addComponent(jLabelLoadTenSanPham)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelDonGia)
                                    .addComponent(jLabelLoadLoaiSanPham)
                                    .addComponent(txtSoLuong))))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(jLabelLoadIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButtonThem))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelLoadTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelLoadLoaiSanPham, javax.swing.GroupLayout.DEFAULT_SIZE, 26, Short.MAX_VALUE)
                        .addGap(2, 2, 2))
                    .addComponent(jLabel17))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(4, 4, 4)
                        .addComponent(labelDonGia, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(txtSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(70, 70, 70))
        );

        btnThanhToan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        btnHuy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnHuy.setText("Hủy");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabelLoadhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnThanhToan)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelLoadhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnThanhToan)
                            .addComponent(btnHuy))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableThongTinSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableThongTinSanPhamMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        int row = jTableThongTinSanPham.getSelectedRow();
        jButtonThem.setEnabled(true);
        jLabelLoadIDSanPham.setText(model.getValueAt(row, 0).toString());
        jLabelLoadTenSanPham.setText(model.getValueAt(row, 2).toString());
        jLabelLoadLoaiSanPham.setText(model.getValueAt(row, 1).toString());
        labelDonGia.setText(model.getValueAt(row, 3).toString());
        txtSoLuong.setText("1");
        //ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPhamTheoID(jLabelLoadIDSanPham.getText().toString());
        ImageIcon img = new ImageIcon(getClass().getResource("/Image/ProductImages/" + model.getValueAt(row, 5).toString()));
        Image imgScaled = img.getImage().getScaledInstance(jLabelLoadhinhAnh.getWidth(), jLabelLoadhinhAnh.getHeight(), Image.SCALE_DEFAULT);
        jLabelLoadhinhAnh.setText("");
        jLabelLoadhinhAnh.setIcon(new ImageIcon(imgScaled));
        String masp = model.getValueAt(row, 0).toString();
    }//GEN-LAST:event_jTableThongTinSanPhamMouseClicked

    private void jButtonTimKiemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonTimKiemMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        while (jTableThongTinSanPham.getRowCount() > 0) {
            model.removeRow(0);
        }
        String timkiem = jTextFieldTimKiem.getText().toString();
        ArrayList<SanPham> arr = daoSanPham.getInstance().FindListSanPham(timkiem);
        if (arr.isEmpty() == false) {
            int stt = 1;
//            Boolean hienSanPhamAn = LoginForm.quyenLogin.getChiTietQuyen().contains("qlSanPham");
            for (SanPham sp : arr) {
                //            if (hienSanPhamAn || sp.getTrangThai() == 0) {
                model.addRow(new String[]{
                    String.valueOf(stt),
                    sp.getMaSP(),
                    sp.getMaLSP(),
                    sp.getTenSP(),
                    PriceFormatter.format(sp.getDonGia()),
                    String.valueOf(sp.getSoLuong()),
                    sp.getFileNameHinhAnh(),
                    (sp.getTrangThai() == 0 ? "Đang kinh doanh" : "Ngừng kinh doanh")
                });
                stt++;
                //            }
            }
        }
    }//GEN-LAST:event_jButtonTimKiemMouseClicked

    private void jTextFieldTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemKeyReleased
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        while (jTableThongTinSanPham.getRowCount() > 0) {
            model.removeRow(0);
        }
        String timkiem = jTextFieldTimKiem.getText().toString();
        ArrayList<SanPham> arr = daoSanPham.getInstance().FindListSanPham(timkiem);
        if (arr.isEmpty() == false) {
//            Boolean hienSanPhamAn = LoginForm.quyenLogin.getChiTietQuyen().contains("qlSanPham");
            int stt = 1;
            for (SanPham sp : arr) {
                //            if (hienSanPhamAn || sp.getTrangThai() == 0) {
                model.addRow(new String[]{
                    String.valueOf(stt),
                    sp.getMaSP(),
                    sp.getMaLSP(),
                    sp.getTenSP(),
                    PriceFormatter.format(sp.getDonGia()),
                    String.valueOf(sp.getSoLuong()),
                    sp.getFileNameHinhAnh(),
                    (sp.getTrangThai() == 0 ? "Hiện" : "Ẩn")
                });
                stt++;
                //            }
            }
        }
    }//GEN-LAST:event_jTextFieldTimKiemKeyReleased

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        int soluongMua = Integer.parseInt(txtSoLuong.getText());
//        System.out.println(soluongMua);
        String masp = jLabelLoadIDSanPham.getText();
        addChiTiet(masp, soluongMua);
        txtMaHĐ.setText(daoQuanLyHoaDon.getInstance().getNextID());
        refreshAll();
    }//GEN-LAST:event_jButtonThemActionPerformed

    public void addChiTiet(String masp, int soluong) {
        SanPham sp = daoSanPham.getInstance().getSanPham(masp);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChiTietHoaDon cthd : dscthd) {
            if (cthd.getMaSanPham().equals(sp.getMaSP())) {
                int tongSoLuong = soluong + cthd.getSoLuong();
                if (tongSoLuong > sp.getSoLuong()) {
                    JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoLuong() + ")");
                    return;
                }
                cthd.setSoLuong(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            if (soluong > sp.getSoLuong()) {
                JOptionPane.showMessageDialog(this, "Số lượng sản phẩm trong kho không đủ (" + sp.getSoLuong() + ")");
                return;
            }
            ChiTietHoaDon cthd = new ChiTietHoaDon(daoQuanLyHoaDon.getInstance().getNextID(), masp, soluong, sp.getDonGia());
//            System.out.println(cthd.getDonGia() + " " + cthd.getMaHoaDon());
            dscthd.add(cthd);
        }

        // cập nhật lại table
        DefaultTableModel modelHoaDon = (DefaultTableModel) jTableChitietHoaDon.getModel();
        while (jTableChitietHoaDon.getRowCount() > 0) {
            modelHoaDon.removeRow(0);
        }
        int stt = 1;
        float tongtien = 0;
        for (ChiTietHoaDon cthd : dscthd) {
            SanPham spct = daoSanPham.getInstance().getSanPham(cthd.getMaSanPham());
            float thanhtien = cthd.getDonGia() * cthd.getSoLuong();
            modelHoaDon.addRow(new String[]{
                String.valueOf(stt),
                cthd.getMaSanPham(),
                spct.getTenSP(),
                String.valueOf(cthd.getSoLuong()),
                PriceFormatter.format(cthd.getDonGia()),
                String.valueOf(PriceFormatter.format(thanhtien))
            });
            tongtien += thanhtien;
            stt++;
        }
        String idKhuyenMai = (comboboxKhuyenMai.getSelectedItem().toString()).split(" ")[0];
        KhuyenMai khuyenMai = daoKhuyenMai.getInstance().getKhuyenMai(idKhuyenMai);

        // check khuyến mãi
        modelHoaDon.addRow(new String[]{"", "", "", "", "", ""});
        modelHoaDon.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongtien)});
        if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongtien) {
            float giaTriKhuyenMai = tongtien * khuyenMai.getPhanTramKM() / 100;
            float tongTienSauKhuyenMai = tongtien - giaTriKhuyenMai;
            modelHoaDon.addRow(new String[]{"", "", "", "", "Khuyến mãi", PriceFormatter.format(-giaTriKhuyenMai)});
            modelHoaDon.addRow(new String[]{"", "", "", "", "Còn lại", PriceFormatter.format(tongTienSauKhuyenMai)});
            txtTongtien.setText(String.valueOf(tongTienSauKhuyenMai));
        } else {
            txtTongtien.setText(String.valueOf(tongtien));
        }
    }

    private void jLabelLoadTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelLoadTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLoadTenSanPhamActionPerformed

    private void jLabelLoadLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelLoadLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLoadLoaiSanPhamActionPerformed

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        String idKhachHang = (comboboxKhachHang.getSelectedItem().toString()).split(" ")[0];
//        KhachHang khachHang = daoKhachHang.getInstance().getKhachHangByID(idKhachHang);
        String idKhuyenMai = (comboboxKhuyenMai.getSelectedItem().toString()).split(" ")[0];
        HoaDon hd = new HoaDon(
                txtMaHĐ.getText(),
                this.id_nv,
                idKhachHang,
                idKhuyenMai,
                LocalDate.parse(txtNgayLap.getText()),
                LocalTime.parse(txtGioLap.getText()),
                Float.parseFloat(txtTongtien.getText()));
        daoQuanLyHoaDon.getInstance().insertHoaDon(
                txtMaHĐ.getText(),
                this.id_nv,
                idKhachHang,
                idKhuyenMai,
                LocalDate.parse(txtNgayLap.getText()),
                LocalTime.parse(txtGioLap.getText()),
                Float.parseFloat(txtTongtien.getText()));

        for (ChiTietHoaDon ct : dscthd) {
            int a = daoQuanLyChiTietHoaDon.getInstance().insertQuanLyChiTietHoaDon(ct.getMaHoaDon(), ct.getMaSanPham(), ct.getSoLuong(), ct.getDonGia());
            System.out.println("result " + a);
        }

        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Thanh toán thành công, bạn có muốn IN HÓA ĐƠN?", "Thành công",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            new WritePDF().writeHoaDon(txtMaHĐ.getText());
        }
        txtMaHĐ.setText(daoQuanLyHoaDon.getInstance().getNextID());
        clear();
//        this.target.refreshAll();
    }//GEN-LAST:event_btnThanhToanActionPerformed

    public void clear() {
        txtMaHĐ.setText(daoQuanLyHoaDon.getInstance().getNextID());
        comboboxKhachHang.setSelectedIndex(0);
        comboboxKhuyenMai.setSelectedIndex(0);
        DefaultTableModel modelHoaDon = (DefaultTableModel) jTableChitietHoaDon.getModel();
        while (jTableChitietHoaDon.getRowCount() > 0) {
            modelHoaDon.removeRow(0);
        }
        txtTongtien.setText("");

    }

    private void comboboxKhuyenMaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxKhuyenMaiActionPerformed
        DefaultTableModel modelHoaDon = (DefaultTableModel) jTableChitietHoaDon.getModel();
        while (jTableChitietHoaDon.getRowCount() > 0) {
            modelHoaDon.removeRow(0);
        }
        int stt = 1;
        float tongtien = 0;
        for (ChiTietHoaDon cthd : dscthd) {
            SanPham spct = daoSanPham.getInstance().getSanPham(cthd.getMaSanPham());
            float thanhtien = cthd.getDonGia() * cthd.getSoLuong();
            modelHoaDon.addRow(new String[]{
                String.valueOf(stt),
                cthd.getMaSanPham(),
                spct.getTenSP(),
                String.valueOf(cthd.getSoLuong()),
                PriceFormatter.format(cthd.getDonGia()),
                String.valueOf(PriceFormatter.format(thanhtien))
            });
            tongtien += thanhtien;
            stt++;
        }
        KhuyenMai khuyenMai = null;
        if (this.comboboxKhuyenMai.getSelectedIndex() >= 0) {
            String idKhuyenMai = (this.comboboxKhuyenMai.getSelectedItem().toString()).split(" ")[0];
            khuyenMai = daoKhuyenMai.getInstance().getKhuyenMai(idKhuyenMai);
        }

        // check khuyến mãi
        modelHoaDon.addRow(new String[]{"", "", "", "", "", ""});
        modelHoaDon.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongtien)});
        if (khuyenMai != null && khuyenMai.getPhanTramKM() > 0 && khuyenMai.getDieuKienKM() <= tongtien) {
            float giaTriKhuyenMai = tongtien * khuyenMai.getPhanTramKM() / 100;
            float tongTienSauKhuyenMai = tongtien - giaTriKhuyenMai;
            modelHoaDon.addRow(new String[]{"", "", "", "", "Khuyến mãi", PriceFormatter.format(-giaTriKhuyenMai)});
            modelHoaDon.addRow(new String[]{"", "", "", "", "Còn lại", PriceFormatter.format(tongTienSauKhuyenMai)});
            txtTongtien.setText(String.valueOf(tongTienSauKhuyenMai));
        } else {
            txtTongtien.setText(String.valueOf(tongtien));
        }
    }//GEN-LAST:event_comboboxKhuyenMaiActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        clear();

    }//GEN-LAST:event_btnHuyActionPerformed

    private void comboboxKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxKhachHangActionPerformed
//        showComboboxKhachHang();
    }//GEN-LAST:event_comboboxKhachHangActionPerformed

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        LoadThongTinSanPham();
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    public void refreshAll() {
//        refreshTable();
        jLabelLoadIDSanPham.setText("");
        jLabelLoadTenSanPham.setText("");
        jLabelLoadLoaiSanPham.setText("");
        labelDonGia.setText("");
        txtSoLuong.setText("");
        jLabelLoadhinhAnh.setIcon(null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fBanHang("NV1").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JComboBox<String> comboboxKhachHang;
    private javax.swing.JComboBox<String> comboboxKhuyenMai;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField jLabelLoadIDSanPham;
    private javax.swing.JTextField jLabelLoadLoaiSanPham;
    private javax.swing.JTextField jLabelLoadTenSanPham;
    private javax.swing.JLabel jLabelLoadhinhAnh;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableChitietHoaDon;
    private javax.swing.JTable jTableThongTinSanPham;
    private javax.swing.JTextField jTextFieldTimKiem;
    private javax.swing.JTextField labelDonGia;
    private javax.swing.JTextField txtGioLap;
    private javax.swing.JTextField txtMaHĐ;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtNguoiBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
