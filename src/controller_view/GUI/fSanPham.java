/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_view.GUI;

import model.DTO.SanPham;
import model.DAO.daoSanPham;
import model.DAO.daoLoaiSanPham;
import model.DAO.PriceFormatter;
import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.lang.*;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
import model.DAO.daoTaiKhoan;
import model.DTO.TaiKhoan;

public class fSanPham extends javax.swing.JFrame {

    public String id_nv;
    public TaiKhoan tkNv;

    public fSanPham() {
        initComponents();
        showComboboxLoaiSanPham();
        setIcon();
        LoadThongTinSanPham();
        jButtonHuy.setVisible(false);
    }

    public fSanPham(String idnv) {
        initComponents();
        showComboboxLoaiSanPham();
        setIcon();
        LoadThongTinSanPham();
        jButtonHuy.setVisible(false);
        this.id_nv = idnv;
        tkNv = daoTaiKhoan.getInstance().getTaiKhoan(idnv);

    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/Logo2.png")));
    }

    public void LoadThongTinSanPham() {
        int stt = 1;
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        while (jTableThongTinSanPham.getRowCount() > 0) {
            model.removeRow(0);
        }
        ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPham();
        if (arr.isEmpty() == false) {
            for (SanPham sp : arr) {
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

    }

    public void showComboboxLoaiSanPham() {
        comboboxPhanLoai.removeAllItems();
        comboboxPhanLoai.addItem("Tất cả");
        comboboxPhanLoai.addItem("Đang kinh doanh");
        comboboxPhanLoai.addItem("Ngừng kinh doanh");

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
        String value = "";
        if (comboboxPhanLoai != null) {
            value = comboboxPhanLoai!=null && comboboxPhanLoai.getSelectedItem() != null ? comboboxPhanLoai.getSelectedItem().toString() : "";
        } else {
            value = "Tất cả";
        }
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        while (jTableThongTinSanPham.getRowCount() > 0) {
            model.removeRow(0);
        }
        int stt = 1;
        ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPham();
        if ("Tất cả".equals(value)) {
            arr = daoSanPham.getInstance().getListSanPham();
        } else if ("Đang kinh doanh".equals(value)) {
            arr = daoSanPham.getInstance().getListSanPhamByStatus(0);
        } else if ("Ngừng kinh doanh".equals(value)) {
            arr = daoSanPham.getInstance().getListSanPhamByStatus(1);
        }
//        ArrayList<SanPham> arr = daoSanPham.getInstance().getListSanPham();
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTableThongTinSanPham = new javax.swing.JTable();
        jTextFieldTimKiem = new javax.swing.JTextField();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonThem = new javax.swing.JButton();
        jButtonSua = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabelLoaiSanPham = new javax.swing.JLabel();
        jLabelLoadLoaiSanPham = new javax.swing.JLabel();
        jLabelLoadTenSanPham = new javax.swing.JLabel();
        jLabelTenSanPham = new javax.swing.JLabel();
        jLabelIdSanpham = new javax.swing.JLabel();
        jLabelLoadIDSanPham = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        labelDonGia = new javax.swing.JLabel();
        labelSoluong = new javax.swing.JLabel();
        jLabelLoadhinhAnh = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jButtonLamMoi = new javax.swing.JButton();
        jButtonHuy = new javax.swing.JButton();
        comboboxPhanLoai = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Danh Sách Thông Tin Sản Phẩm");
        setResizable(false);

        jTableThongTinSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableThongTinSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã sản phẩm", "Mã Loại", "Tên", "Đơn giá", "Số lượng", "Hình ảnh", "Trạng thái"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true
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
        if (jTableThongTinSanPham.getColumnModel().getColumnCount() > 0) {
            jTableThongTinSanPham.getColumnModel().getColumn(0).setMinWidth(50);
            jTableThongTinSanPham.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableThongTinSanPham.getColumnModel().getColumn(0).setMaxWidth(50);
        }
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

        jButtonThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonThem.setText("Tạo");
        ImageIcon imgTaoMoi = new ImageIcon(getClass().getResource("/icon/icons8-plus-48.png"));
        ImageIcon ImgTaoMoi = new ImageIcon(imgTaoMoi.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonThem.setIcon(ImgTaoMoi);
        jButtonThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonThemMouseClicked(evt);
            }
        });
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemActionPerformed(evt);
            }
        });

        jButtonSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSua.setText("Sửa");
        ImageIcon imgSua = new ImageIcon(getClass().getResource("/icon/icons8-maintenance-48.png"));
        ImageIcon ImgSua = new ImageIcon(imgSua.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonSua.setIcon(ImgSua);
        jButtonSua.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonSuaMouseClicked(evt);
            }
        });
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Sản phẩm");

        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(jLabel2))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabelLoaiSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelLoaiSanPham.setText("Loại Sản Phẩm  :");

        jLabelLoadLoaiSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelLoadTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabelTenSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelTenSanPham.setText("Tên Sản Phẩm  :");

        jLabelIdSanpham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelIdSanpham.setText("Mã sản phẩm:");

        jLabelLoadIDSanPham.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setText("Đơn giá");

        jLabel4.setText("Số lượng");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabelLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabelLoadLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelTenSanPham)
                            .addComponent(jLabelIdSanpham))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabelLoadTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelLoadIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(49, 49, 49)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSoluong)
                            .addComponent(labelDonGia))))
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelLoadIDSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelIdSanpham)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel3)
                                .addComponent(labelDonGia)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelLoadTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelTenSanPham)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(labelSoluong)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLoaiSanPham)
                    .addComponent(jLabelLoadLoaiSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        jLabelIdSanpham.getAccessibleContext().setAccessibleName("");

        jLabelLoadhinhAnh.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabelLoadhinhAnh.setText("hinh");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabelLoadhinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 143, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabelLoadhinhAnh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );

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

        jButtonHuy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonHuy.setText("Ngừng kinh doanh");
        ImageIcon imgHuy = new ImageIcon(getClass().getResource("/icon/icons8-waste-48.png"));
        ImageIcon ImgHuy = new ImageIcon(imgHuy.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonHuy.setIcon(ImgHuy);
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        comboboxPhanLoai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxPhanLoai.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboboxPhanLoaiItemStateChanged(evt);
            }
        });
        comboboxPhanLoai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxPhanLoaiActionPerformed(evt);
            }
        });
        comboboxPhanLoai.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                comboboxPhanLoaiPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonHuy)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboboxPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLamMoi)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTimKiem)
                    .addComponent(jButtonThem)
                    .addComponent(jButtonSua)
                    .addComponent(jButtonLamMoi)
                    .addComponent(jButtonHuy)
                    .addComponent(comboboxPhanLoai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    //Methods
    //Events
    private void jTableThongTinSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableThongTinSanPhamMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        int row = jTableThongTinSanPham.getSelectedRow();
        jLabelLoadIDSanPham.setText(model.getValueAt(row, 1).toString());
        jLabelLoadTenSanPham.setText(model.getValueAt(row, 2).toString());
        jLabelLoadLoaiSanPham.setText(model.getValueAt(row, 3).toString());
        labelDonGia.setText(model.getValueAt(row, 4).toString());
        labelSoluong.setText(model.getValueAt(row, 5).toString());
        int trangThai = model.getValueAt(row, 7).toString().equals("Đang kinh doanh") ? 0 : 1;
        jButtonHuy.setText(trangThai == 1 ? "Tiếp tục kinh doanh" : "Ngừng kinh doanh");
        if (tkNv.getMaQuyen().equals("Q1")) {
            jButtonHuy.setVisible(true);
        } else {
            jButtonHuy.setVisible(false);
        }
        String id = (model.getValueAt(row, 0).toString());
        SanPham sp = daoSanPham.getInstance().getSanPham(id);
        ImageIcon img = new ImageIcon(getClass().getResource("/Image/ProductImages/" + model.getValueAt(row, 6).toString()));
        Image imgScaled = img.getImage().getScaledInstance(jLabelLoadhinhAnh.getWidth(), jLabelLoadhinhAnh.getHeight(), Image.SCALE_DEFAULT);
        jLabelLoadhinhAnh.setText("");
        jLabelLoadhinhAnh.setIcon(new ImageIcon(imgScaled));
    }//GEN-LAST:event_jTableThongTinSanPhamMouseClicked

    private void jButtonThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonThemMouseClicked
        fThemSanPham TSP = new fThemSanPham();
        TSP.setVisible(true);


    }//GEN-LAST:event_jButtonThemMouseClicked

    private void jButtonSuaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonSuaMouseClicked
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        int row = jTableThongTinSanPham.getSelectedRow();
        JFrame fsuaSp = new fSuaSP(model.getValueAt(row, 1).toString());
        fsuaSp.setVisible(true);
    }//GEN-LAST:event_jButtonSuaMouseClicked

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
            int stt = 1;
            for (SanPham sp : arr) {
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
    }//GEN-LAST:event_jTextFieldTimKiemKeyReleased

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        DefaultTableModel model = (DefaultTableModel) jTableThongTinSanPham.getModel();
        int row = jTableThongTinSanPham.getSelectedRow();
        JFrame fsuaSp = new fSuaSP(model.getValueAt(row, 1).toString());
        fsuaSp.setVisible(true);
    }//GEN-LAST:event_jButtonSuaActionPerformed

    private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyActionPerformed
        int selectedRowIndex = jTableThongTinSanPham.getSelectedRow();
        String id = jTableThongTinSanPham.getValueAt(selectedRowIndex, 1).toString();
        SanPham sp = daoSanPham.getInstance().getSanPham(id);
        int reply = JOptionPane.showConfirmDialog(getRootPane(),
                "Bạn có muốn ngừng kinh doanh sản phẩm: " + sp.getTenSP() + " ?", "Cảnh báo",
                JOptionPane.YES_NO_OPTION);

        if (reply == JOptionPane.OK_OPTION) {
            daoSanPham.getInstance().updateSanPham(id, sp.getMaLSP(), sp.getTenSP(), sp.getDonGia(), sp.getSoLuong(), sp.getFileNameHinhAnh(), sp.getTrangThai() == 0 ? 1 : 0);
            listDanhSachSanPham();
        }
    }//GEN-LAST:event_jButtonHuyActionPerformed

    private void jButtonThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_jButtonThemActionPerformed

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        listDanhSachSanPham();
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    private void comboboxPhanLoaiPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_comboboxPhanLoaiPropertyChange
//        LoadThongTinSanPham();
    }//GEN-LAST:event_comboboxPhanLoaiPropertyChange

    private void comboboxPhanLoaiItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboboxPhanLoaiItemStateChanged
//        LoadThongTinSanPham();
    }//GEN-LAST:event_comboboxPhanLoaiItemStateChanged

    private void comboboxPhanLoaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxPhanLoaiActionPerformed
        listDanhSachSanPham();
    }//GEN-LAST:event_comboboxPhanLoaiActionPerformed

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
            java.util.logging.Logger.getLogger(fSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fSanPham.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fSanPham("NV12").setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboboxPhanLoai;
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelIdSanpham;
    private javax.swing.JLabel jLabelLoadIDSanPham;
    private javax.swing.JLabel jLabelLoadLoaiSanPham;
    private javax.swing.JLabel jLabelLoadTenSanPham;
    private javax.swing.JLabel jLabelLoadhinhAnh;
    private javax.swing.JLabel jLabelLoaiSanPham;
    private javax.swing.JLabel jLabelTenSanPham;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTable jTableThongTinSanPham;
    private javax.swing.JTextField jTextFieldTimKiem;
    private javax.swing.JLabel labelDonGia;
    private javax.swing.JLabel labelSoluong;
    // End of variables declaration//GEN-END:variables
}
