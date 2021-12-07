/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import BUS.WritePDF;
import DAO.*;
import DTO.*;
import Format.PriceFormatter;
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

public class fNhapHang extends javax.swing.JFrame {

    public String id_nv;
    ArrayList<ChiTietPhieuNhap> dsctpn = new ArrayList<>();

    public fNhapHang() {
        initComponents();
        setIcon();
        LoadThongTinSanPham();
        setData();
        showComboboxNcc();
//        showComboboxLoaiSanPham();
    }

    public fNhapHang(String idnv) {
        this.id_nv = idnv;
        initComponents();
        setIcon();
        LoadThongTinSanPham();
        //showComboboxLoaiSanPham();
        setData();
        showComboboxNcc();
    }

    public void showComboboxNcc() {
        comboboxNCC.removeAllItems();
        comboboxNCC.addItem(null);
        ArrayList<NhaCungCap> arr = daoNhaCungCap.getInstance().getListNhaCungCap();
        for (int i = 0; i < arr.size(); i++) {
            comboboxNCC.addItem(arr.get(i).getMaNCC() + " " + arr.get(i).getTenNCC());
        }
        jButtonThem.setEnabled(false);
    }

    private void setData() {
//        if(daoNhanVien.getInstance().getNVByID(this.id_nv)!=null) {
        txtNguoiBan.setText(daoNhanVien.getInstance().getNameNVByID(this.id_nv));
//        }
        txtNguoiBan.setEditable(false);
        txtMaPN.setText(daoPhieuNhap.getInstance().getNextID());
        txtMaPN.setEditable(false);
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                txtNgayLap.setText(LocalDate.now().toString());
                txtGioLap.setText(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
//                if (txNhanVien.getText().equals("")
//                        || txKhachHang.getText().equals("")
//                        || txKhuyenMai.getText().equals("")
//                        || dscthd.isEmpty()) {
//                    btnThanhToan.setEnabled(false);
//                } else {
//                    btnThanhToan.setEnabled(true);
//                }
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
//            Boolean hienSanPhamAn = LoginForm.quyenLogin.getChiTietQuyen().contains("qlSanPham");
            for (SanPham sp : arr) {
                //            if (hienSanPhamAn || sp.getTrangThai() == 0) {
                model.addRow(new String[]{
                    sp.getMaSP(),
                    sp.getMaLSP(),
                    sp.getTenSP(),
                    PriceFormatter.format(sp.getDonGia()),
                    String.valueOf(sp.getSoLuong()),
                    sp.getFileNameHinhAnh(),});
                //            }
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
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableThongTinSanPham = new javax.swing.JTable();
        jTextFieldTimKiem = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonLamMoi = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMaPN = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        comboboxNCC = new javax.swing.JComboBox<>();
        btnCreateKhachHang = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        txtNgayLap = new javax.swing.JTextField();
        txtGioLap = new javax.swing.JTextField();
        txtTongtien = new javax.swing.JTextField();
        txtNguoiBan = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabelLoadhinhAnh = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableChitietPN = new javax.swing.JTable();
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
        btnNhapHang = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh Sách Thông Tin Sản Phẩm");
        setBackground(new java.awt.Color(204, 204, 204));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nhập hàng");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(550, 550, 550))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Phiếu nhập hàng");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Mã phiếu nhập:");

        txtMaPN.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Nhà cung cấp:");

        comboboxNCC.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        comboboxNCC.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btnCreateKhachHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnCreateKhachHang.setText("Mới");
        ImageIcon imgTaoMoi1 = new ImageIcon(getClass().getResource("/icon/icons8-plus-48.png"));
        ImageIcon ImgTaoMoi1 = new ImageIcon(imgTaoMoi1.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonThem.setIcon(ImgTaoMoi1);
        btnCreateKhachHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCreateKhachHangMouseClicked(evt);
            }
        });
        btnCreateKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCreateKhachHangActionPerformed(evt);
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

        txtNguoiBan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("Người nhập:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                            .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNgayLap)
                            .addComponent(txtGioLap, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                        .addGap(51, 51, 51)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(15, 15, 15)))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(comboboxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnCreateKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(256, 256, 256))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel3)
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtMaPN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(comboboxNCC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCreateKhachHang))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(txtNgayLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTongtien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtGioLap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNguoiBan)
                    .addComponent(jLabel14))
                .addGap(7, 7, 7))
        );

        jLabelLoadhinhAnh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelLoadhinhAnh.setText("hinh");

        jTableChitietPN.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã SP", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane2.setViewportView(jTableChitietPN);

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel15.setText("Mã SP:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel16.setText("Tên SP:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel17.setText("Nhãn hàng:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel18.setText("Đơn giá:");

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

        btnNhapHang.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnNhapHang.setText("Nhập hàng");
        btnNhapHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNhapHangActionPerformed(evt);
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
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane2))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnNhapHang)
                        .addGap(18, 18, 18)
                        .addComponent(btnHuy)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, 0)
                                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelLoadhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnNhapHang)
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

    //Methods
    //Events
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
//       ImageIcon imageIcon = new ImageIcon(
//                new ImageIcon(sp.getFileNameHinhAnh()).getImage().getScaledInstance(
//                        jLabelLoadhinhAnh.getWidth(), jLabelLoadhinhAnh.getHeight(), Image.SCALE_DEFAULT));
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
                    (sp.getTrangThai() == 0 ? "Hiện" : "Ẩn")
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

    private void btnCreateKhachHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCreateKhachHangMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreateKhachHangMouseClicked

    private void btnCreateKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCreateKhachHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCreateKhachHangActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        int soluongNhap = Integer.parseInt(txtSoLuong.getText());
//        System.out.println(soluongMua);
        String masp = jLabelLoadIDSanPham.getText();
        addChiTiet(masp, soluongNhap);
        refreshAll();
    }//GEN-LAST:event_jButtonThemActionPerformed

    public void addChiTiet(String masp, int soluong) {
        float tongTien = 0;
        SanPham sp = daoSanPham.getInstance().getSanPham(masp);

        Boolean daCo = false; // check xem trong danh sách chi tiết hóa đơn đã có sản phẩm này chưa
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMaSP().equals(sp.getMaSP())) {
                int tongSoLuong = soluong + ctpn.getSoLuong();
                ctpn.setSoLuong(tongSoLuong); // có rồi thì thay đổi số lượng
                daCo = true;
            }
        }

        if (!daCo) { // nếu chưa có thì thêm mới
            ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(daoPhieuNhap.getInstance().getNextID(), masp, soluong, sp.getDonGia());
            dsctpn.add(ctpn);
        }

        // cập nhật lại table
        DefaultTableModel modelPN = (DefaultTableModel) jTableChitietPN.getModel();
        while (jTableChitietPN.getRowCount() > 0) {
            modelPN.removeRow(0);
        }
        int stt = 1;
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            String masp1 = ctpn.getMaSP();
            SanPham spct = daoSanPham.getInstance().getSanPham(masp1);
            String tensp = spct.getTenSP();
            int soluongct = ctpn.getSoLuong();
            float dongia = ctpn.getDonGia();
            float thanhtien = soluongct * dongia;

            modelPN.addRow(new String[]{
                String.valueOf(stt),
                masp,
                tensp,
                String.valueOf(soluong),
                PriceFormatter.format(dongia),
                PriceFormatter.format(thanhtien)
            });
            stt++;
            tongTien += thanhtien;
        }

        // check khuyến mãi
        modelPN.addRow(new String[]{"", "", "", "", "", ""});
        modelPN.addRow(new String[]{"", "", "", "", "Tổng tiền", PriceFormatter.format(tongTien)});
        txtTongtien.setText(String.valueOf(tongTien));
    }

    private void jLabelLoadTenSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelLoadTenSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLoadTenSanPhamActionPerformed

    private void jLabelLoadLoaiSanPhamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jLabelLoadLoaiSanPhamActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabelLoadLoaiSanPhamActionPerformed

    private void btnNhapHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNhapHangActionPerformed
        String idNcc = "";
        if (comboboxNCC.getSelectedIndex() > 0) {
            idNcc = (comboboxNCC.getSelectedItem().toString()).split(" ")[0];
        }
//        KhachHang khachHang = daoKhachHang.getInstance().getKhachHangByID(idKhachHang);
        PhieuNhap pn = new PhieuNhap(
                txtMaPN.getText(),
                idNcc,
                this.id_nv,
                LocalDate.parse(txtNgayLap.getText()),
                LocalTime.parse(txtGioLap.getText()),
                Float.parseFloat(txtTongtien.getText()));
        daoPhieuNhap.getInstance().insertPN(
                txtMaPN.getText(),
                idNcc,
                this.id_nv,
                LocalDate.parse(txtNgayLap.getText()).toString(),
                LocalTime.parse(txtGioLap.getText()).toString(),
                Float.parseFloat(txtTongtien.getText()));

        for (ChiTietPhieuNhap ct : dsctpn) {
            int a = daoChiTietPhieuNhap.getInstance().insertChiTietPhieuNhap(ct.getMa(), ct.getMaSP(), ct.getSoLuong(), ct.getDonGia());
            System.out.println("result " + a);
        }

        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Thanh toán thành công, bạn có muốn IN phiếu nhập?", "Thành công",
                JOptionPane.YES_NO_OPTION);
        if (reply == JOptionPane.OK_OPTION) {
            new WritePDF().writeHoaDon(txtMaPN.getText());
        }
        txtMaPN.setText(daoPhieuNhap.getInstance().getNextID());
        clear();
//        this.target.refreshAll();
    }//GEN-LAST:event_btnNhapHangActionPerformed

    public void clear() {
        txtMaPN.setText(daoPhieuNhap.getInstance().getNextID());
        comboboxNCC.setSelectedIndex(0);
        DefaultTableModel modelHoaDon = (DefaultTableModel) jTableChitietPN.getModel();
        while (jTableChitietPN.getRowCount() > 0) {
            modelHoaDon.removeRow(0);
        }
        txtTongtien.setText("");

    }

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
        clear();

    }//GEN-LAST:event_btnHuyActionPerformed

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
                new fNhapHang("NV12").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCreateKhachHang;
    private javax.swing.JButton btnHuy;
    private javax.swing.JButton btnNhapHang;
    private javax.swing.JComboBox<String> comboboxNCC;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
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
    private javax.swing.JTable jTableChitietPN;
    private javax.swing.JTable jTableThongTinSanPham;
    private javax.swing.JTextField jTextFieldTimKiem;
    private javax.swing.JTextField labelDonGia;
    private javax.swing.JTextField txtGioLap;
    private javax.swing.JTextField txtMaPN;
    private javax.swing.JTextField txtNgayLap;
    private javax.swing.JTextField txtNguoiBan;
    private javax.swing.JTextField txtSoLuong;
    private javax.swing.JTextField txtTongtien;
    // End of variables declaration//GEN-END:variables
}
