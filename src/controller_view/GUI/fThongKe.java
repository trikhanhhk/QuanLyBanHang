/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_view.GUI;
import model.DAO.daoChiTietPhieuNhap;
import model.DAO.daoKhachHang;
import model.DAO.daoNhaCungCap;
import model.DAO.daoNhanVien;
import model.DAO.daoQuanLyChiTietHoaDon;
import model.DAO.daoQuanLyHoaDon;
import model.DAO.daoSanPham;
import model.DTO.ChiTietHoaDon;
import model.DTO.ChiTietPhieuNhap;
import model.DTO.HoaDon;
import model.DTO.KhachHang;
import model.DTO.MyCheckDate;
import model.DTO.NhaCungCap;
import model.DTO.NhanVien;
import model.DTO.PhieuNhap;
import model.DTO.SanPham;
import model.DAO.PriceFormatter;
import java.awt.Component;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.lang.*;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import model.DAO.daoPhieuNhap;

/**
 *
 * @author trikha
 */
public class fThongKe extends javax.swing.JFrame {

    /**
     * Creates new form fBaoCao
     */
    public String maNV;
    private JPanel chartPanel;
    ArrayList<SanPham> listSP = new ArrayList<>();
    ArrayList<KhachHang> listKhachHang = new ArrayList<>();
    ArrayList<NhaCungCap> listNcc = new ArrayList<>();
    ArrayList<NhanVien> listNv = new ArrayList<>();
    DefaultTableModel thongKeTongTbl;
    DefaultTableModel ketQuaHoaDonTbl;
    DefaultTableModel thongKePhieuNhapTbl;
    DefaultTableModel ketQuaThongKePhieuNhapTbl;
    DefaultTableModel thongKeSpTbl;
    DefaultTableModel thongKeNvTbl;
    String[] ArrHeaderTable = null;

    public fThongKe() throws FileNotFoundException {
        initComponents();
        setIcon();
        setData();
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
        build();
    }

    public fThongKe(String maNV) {
        initComponents();
        setIcon();
        this.maNV = maNV;
        setData();
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
        build();
    }

    private void getData() {
        this.listKhachHang = daoKhachHang.getInstance().getListKhachHang();
        this.listNcc = daoNhaCungCap.getInstance().getListNhaCungCap();
        this.listNv = daoNhanVien.getInstance().getListNhanVien();
        this.listSP = daoSanPham.getInstance().getListSanPham();
    }

    private void setData() {
        getData();
        comboboxSp.removeAllItems();
        comboboxSp.addItem("");
        for (SanPham sanPham : listSP) {
            comboboxSp.addItem(sanPham.getMaSP() + " " + sanPham.getTenSP());
        }

        comboboxKH.removeAllItems();
        comboboxKH.addItem("");
        for (KhachHang kh : listKhachHang) {
            comboboxKH.addItem(kh.getMaKH() + " " + kh.getTenKH());
        }

        comboboxNcc.removeAllItems();
        comboboxNcc.addItem("");
        for (NhaCungCap ncc : listNcc) {
            comboboxNcc.addItem(ncc.getMaNCC() + " " + ncc.getTenNCC());
        }

        comboboxNv.removeAllItems();
        comboboxNv.addItem("");
        for (NhanVien nv : listNv) {
            comboboxNv.addItem(nv.getMaNV() + " " + nv.getTenNV());
        }
        thongKeTongTbl = (DefaultTableModel) tableThongKeTong.getModel();
        ketQuaHoaDonTbl = (DefaultTableModel) tableKqThongKe.getModel();
        thongKePhieuNhapTbl = (DefaultTableModel) tableThongKePhieuNhap.getModel();
        ketQuaThongKePhieuNhapTbl = (DefaultTableModel) tableKqThongKePhieuNhap.getModel();
        thongKeSpTbl = (DefaultTableModel) tableSp.getModel();
        thongKeNvTbl = (DefaultTableModel) tableTkNv.getModel();
    }

    private ImageIcon getIcon(String filename) {
        return new ImageIcon(getClass().getResource("/icon/" + filename));
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/Logo2.png")));
        tabs.setIconAt(0, getIcon("icons8_pie_chart_30px.png"));
        tabs.setIconAt(1, getIcon("icons8_multiple_smartphones_30px.png"));
        tabs.setIconAt(2, getIcon("icons8_assistant_30px.png"));
        tabs.setIconAt(3, getIcon("icons8_user_30px.png"));
        tabs.setIconAt(4, getIcon("icons8_company_30px.png"));
        tabs.addChangeListener((ce) -> {
            int indexTab = tabs.getSelectedIndex();
            if (indexTab == 1) {
                int index = comboboxSp.getSelectedIndex();
                if (index == 0) {
                    soLuongSanPhamNhap();
                }
            }
            if (indexTab == 2) {
                tongTienTungNhanVien_searchOnChange();
            }

            if (indexTab == 3) {
                tongTienTungKhachHang_searchOnChange();
            }
            
            if(indexTab == 4) {
                tongTienThanhToan();
            }
        });
        tabsThongkeTongQuat.setIconAt(0, getIcon("icons8_small_business_30px_3.png"));
        tabsThongkeTongQuat.setIconAt(1, getIcon("icons8_downloads_30px.png"));
        tabsThongkeTongQuat.addChangeListener((ce) -> {
            Boolean HoaDon_isSelected = (tabsThongkeTongQuat.getSelectedIndex() == 1);
            panelNcc.setVisible(!HoaDon_isSelected);
            panelKh.setVisible(HoaDon_isSelected);
        });
    }

    void build() {
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        label1 = new java.awt.Label();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        panelKhoangNgay = new javax.swing.JPanel();
        ngayKetThuc = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        ngayBatDau = new org.jdesktop.swingx.JXDatePicker();
        jLabel3 = new javax.swing.JLabel();
        panelSp = new javax.swing.JPanel();
        comboboxSp = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        panelNv = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        comboboxNv = new javax.swing.JComboBox<>();
        panelKh = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        comboboxKH = new javax.swing.JComboBox<>();
        tabsThongkeTongQuat = new javax.swing.JTabbedPane();
        jPanel17 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableThongKeTong = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableKqThongKe = new javax.swing.JTable();
        jPanel18 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tableKqThongKePhieuNhap = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        tableThongKePhieuNhap = new javax.swing.JTable();
        panelNcc = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        comboboxNcc = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tableSp = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        comboboxDKThongKeSp = new javax.swing.JComboBox<>();
        panelKhoangNgay1 = new javax.swing.JPanel();
        ngayKetThucSp = new org.jdesktop.swingx.JXDatePicker();
        jLabel13 = new javax.swing.JLabel();
        ngayBatDauSp = new org.jdesktop.swingx.JXDatePicker();
        jLabel14 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        panelKhoangNgay2 = new javax.swing.JPanel();
        ngayKetThucNV = new org.jdesktop.swingx.JXDatePicker();
        jLabel15 = new javax.swing.JLabel();
        ngayBatDauNV = new org.jdesktop.swingx.JXDatePicker();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        tableTkNv = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        panelKhoangNgay3 = new javax.swing.JPanel();
        ngayKetThucKh = new org.jdesktop.swingx.JXDatePicker();
        jLabel17 = new javax.swing.JLabel();
        ngayBatDauKh = new org.jdesktop.swingx.JXDatePicker();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tableTkKh = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        panelKhoangNgay4 = new javax.swing.JPanel();
        ngayKetThucNcc = new org.jdesktop.swingx.JXDatePicker();
        jLabel19 = new javax.swing.JLabel();
        ngayBatDauNcc = new org.jdesktop.swingx.JXDatePicker();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        tableNcc = new javax.swing.JTable();

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        label1.setText("label1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Báo cáo");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jLabel2.setBackground(new java.awt.Color(51, 51, 51));
        jLabel2.setFont(new java.awt.Font("Times New Roman", 3, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Quản lý thống kê");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        panelKhoangNgay.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date1 = new Date();
        //ngayBatDau.setDate(date1);
        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        ngayKetThuc.setBackground(new java.awt.Color(255, 255, 255));
        ngayKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayKetThucActionPerformed(evt);
            }
        });
        ngayKetThuc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayKetThucPropertyChange(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("-->");

        ngayBatDau.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date = new Date();
        //ngayBatDau.setDate(date);
        ngayBatDau.setBackground(new java.awt.Color(255, 255, 255));
        ngayBatDau.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayBatDauPropertyChange(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("Khoảng ngày");

        javax.swing.GroupLayout panelKhoangNgayLayout = new javax.swing.GroupLayout(panelKhoangNgay);
        panelKhoangNgay.setLayout(panelKhoangNgayLayout);
        panelKhoangNgayLayout.setHorizontalGroup(
            panelKhoangNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgayLayout.createSequentialGroup()
                .addGroup(panelKhoangNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhoangNgayLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgayLayout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(3, 3, 3)))
                .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelKhoangNgayLayout.setVerticalGroup(
            panelKhoangNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgayLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKhoangNgayLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(ngayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelSp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        comboboxSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxSpActionPerformed(evt);
            }
        });

        jLabel5.setText("Sản phẩm");

        javax.swing.GroupLayout panelSpLayout = new javax.swing.GroupLayout(panelSp);
        panelSp.setLayout(panelSpLayout);
        panelSpLayout.setHorizontalGroup(
            panelSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelSpLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboboxSp, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelSpLayout.setVerticalGroup(
            panelSpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelSpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(comboboxSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        panelNv.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel6.setText("Nhân viên");

        comboboxNv.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxNv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxNvActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNvLayout = new javax.swing.GroupLayout(panelNv);
        panelNv.setLayout(panelNvLayout);
        panelNvLayout.setHorizontalGroup(
            panelNvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNvLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelNvLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(comboboxNv, javax.swing.GroupLayout.Alignment.TRAILING, 0, 143, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelNvLayout.setVerticalGroup(
            panelNvLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelNvLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(comboboxNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        panelKh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel7.setText("Khách hàng");

        comboboxKH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxKHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelKhLayout = new javax.swing.GroupLayout(panelKh);
        panelKh.setLayout(panelKhLayout);
        panelKhLayout.setHorizontalGroup(
            panelKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(comboboxKH, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        panelKhLayout.setVerticalGroup(
            panelKhLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelKhLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addComponent(comboboxKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableThongKeTong.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Hóa đơn", "Tên nhân viên", "Tên Khách hàng ", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tableThongKeTong);

        tableKqThongKe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Tổng tất cả", "", "", "", "", "", "Tổng bán ra"
            }
        ));
        jScrollPane2.setViewportView(tableKqThongKe);

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 1230, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabsThongkeTongQuat.addTab("Bán ra", jPanel17);

        tableKqThongKePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Tổng tất cả", "", "", "", "", "", "Tổng nhập vào"
            }
        ));
        jScrollPane4.setViewportView(tableKqThongKePhieuNhap);

        tableThongKePhieuNhap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Phiếu nhập", "Tên nhân viên", "Tên nhà cung cấp", "Tên sản phẩm", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane3.setViewportView(tableThongKePhieuNhap);

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane4)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 1230, Short.MAX_VALUE)
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tabsThongkeTongQuat.addTab("Nhập vào", jPanel18);

        panelNcc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel11.setText("Nhà cung cấp");

        comboboxNcc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboboxNcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxNccActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelNccLayout = new javax.swing.GroupLayout(panelNcc);
        panelNcc.setLayout(panelNccLayout);
        panelNccLayout.setHorizontalGroup(
            panelNccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNccLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelNccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(comboboxNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        panelNccLayout.setVerticalGroup(
            panelNccLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelNccLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel11)
                .addGap(18, 18, 18)
                .addComponent(comboboxNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKhoangNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelSp, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelNv, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(panelNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(tabsThongkeTongQuat, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelKhoangNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelSp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelNv, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(panelKh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelNcc, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabsThongkeTongQuat, javax.swing.GroupLayout.PREFERRED_SIZE, 501, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        tabs.addTab("Thống kê tổng quát", jPanel2);

        tableSp.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Mã phiếu nhập", "Tên nhà cung cấp", "Ngày nhập", "Số lượng", "Ðơn giá", "Tổng tiền"
            }
        ));
        jScrollPane5.setViewportView(tableSp);

        comboboxDKThongKeSp.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Số lượng nhập", "Số lượng bán" }));
        comboboxDKThongKeSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboboxDKThongKeSpActionPerformed(evt);
            }
        });

        panelKhoangNgay1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date1 = new Date();
        //ngayBatDau.setDate(date1);
        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        ngayKetThucSp.setBackground(new java.awt.Color(255, 255, 255));
        ngayKetThucSp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayKetThucSpActionPerformed(evt);
            }
        });
        ngayKetThucSp.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayKetThucSpPropertyChange(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel13.setText("-->");

        ngayBatDau.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date = new Date();
        //ngayBatDau.setDate(date);
        ngayBatDauSp.setBackground(new java.awt.Color(255, 255, 255));
        ngayBatDauSp.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayBatDauSpPropertyChange(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel14.setText("Khoảng ngày");

        javax.swing.GroupLayout panelKhoangNgay1Layout = new javax.swing.GroupLayout(panelKhoangNgay1);
        panelKhoangNgay1.setLayout(panelKhoangNgay1Layout);
        panelKhoangNgay1Layout.setHorizontalGroup(
            panelKhoangNgay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay1Layout.createSequentialGroup()
                .addGroup(panelKhoangNgay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhoangNgay1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayBatDauSp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addGap(3, 3, 3)))
                .addComponent(ngayKetThucSp, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelKhoangNgay1Layout.setVerticalGroup(
            panelKhoangNgay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKhoangNgay1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngayKetThucSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(ngayBatDauSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(comboboxDKThongKeSp, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelKhoangNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 27, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(panelKhoangNgay1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addComponent(comboboxDKThongKeSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27))))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(296, 296, 296)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Sản phẩm", jPanel4);

        panelKhoangNgay2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ngayKetThucNV.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date1 = new Date();
        //ngayBatDau.setDate(date1);
        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        ngayKetThucNV.setBackground(new java.awt.Color(255, 255, 255));
        ngayKetThucNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayKetThucNVActionPerformed(evt);
            }
        });
        ngayKetThucNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayKetThucNVPropertyChange(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel15.setText("-->");

        ngayBatDauNV.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date = new Date();
        //ngayBatDau.setDate(date);
        ngayBatDauNV.setBackground(new java.awt.Color(255, 255, 255));
        ngayBatDauNV.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayBatDauNVPropertyChange(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel16.setText("Khoảng ngày");

        javax.swing.GroupLayout panelKhoangNgay2Layout = new javax.swing.GroupLayout(panelKhoangNgay2);
        panelKhoangNgay2.setLayout(panelKhoangNgay2Layout);
        panelKhoangNgay2Layout.setHorizontalGroup(
            panelKhoangNgay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay2Layout.createSequentialGroup()
                .addGroup(panelKhoangNgay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhoangNgay2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay2Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayBatDauNV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addGap(3, 3, 3)))
                .addComponent(ngayKetThucNV, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelKhoangNgay2Layout.setVerticalGroup(
            panelKhoangNgay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKhoangNgay2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngayKetThucNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15)
                    .addComponent(ngayBatDauNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(99, Short.MAX_VALUE)
                .addComponent(panelKhoangNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelKhoangNgay2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        tableTkNv.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhân viên", "Tên nhân viên", "Mã hóa đơn", "Ngày lập", "Tổng tiền"
            }
        ));
        jScrollPane8.setViewportView(tableTkNv);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 1228, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(352, 352, 352))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 449, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Nhân viên", jPanel5);

        panelKhoangNgay3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ngayKetThucKh.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date1 = new Date();
        //ngayBatDau.setDate(date1);
        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        ngayKetThucKh.setBackground(new java.awt.Color(255, 255, 255));
        ngayKetThucKh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayKetThucKhActionPerformed(evt);
            }
        });
        ngayKetThucKh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayKetThucKhPropertyChange(evt);
            }
        });

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel17.setText("-->");

        ngayBatDauKh.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date = new Date();
        //ngayBatDau.setDate(date);
        ngayBatDauKh.setBackground(new java.awt.Color(255, 255, 255));
        ngayBatDauKh.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayBatDauKhPropertyChange(evt);
            }
        });

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel18.setText("Khoảng ngày");

        javax.swing.GroupLayout panelKhoangNgay3Layout = new javax.swing.GroupLayout(panelKhoangNgay3);
        panelKhoangNgay3.setLayout(panelKhoangNgay3Layout);
        panelKhoangNgay3Layout.setHorizontalGroup(
            panelKhoangNgay3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay3Layout.createSequentialGroup()
                .addGroup(panelKhoangNgay3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhoangNgay3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel18)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayBatDauKh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel17)
                        .addGap(3, 3, 3)))
                .addComponent(ngayKetThucKh, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelKhoangNgay3Layout.setVerticalGroup(
            panelKhoangNgay3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel18)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKhoangNgay3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngayKetThucKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17)
                    .addComponent(ngayBatDauKh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(111, Short.MAX_VALUE)
                .addComponent(panelKhoangNgay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(86, 86, 86))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKhoangNgay3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableTkKh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã khách hàng", "Tên khách hàng", "Mã hóa đơn", "Ngày lập", "Tổng tiền hóa đơn"
            }
        ));
        jScrollPane9.setViewportView(tableTkKh);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(352, 352, 352)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(376, Short.MAX_VALUE))
            .addComponent(jScrollPane9)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE))
        );

        tabs.addTab("Khách hàng", jPanel6);

        panelKhoangNgay4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        ngayKetThucNcc.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date1 = new Date();
        //ngayBatDau.setDate(date1);
        ngayKetThuc.setFormats(new String[]{"yyyy-MM-dd"});
        ngayKetThucNcc.setBackground(new java.awt.Color(255, 255, 255));
        ngayKetThucNcc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ngayKetThucNccActionPerformed(evt);
            }
        });
        ngayKetThucNcc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayKetThucNccPropertyChange(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel19.setText("-->");

        ngayBatDauNcc.setFormats(new String[]{"yyyy-MM-dd"});
        //Date date = new Date();
        //ngayBatDau.setDate(date);
        ngayBatDauNcc.setBackground(new java.awt.Color(255, 255, 255));
        ngayBatDauNcc.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                ngayBatDauNccPropertyChange(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel20.setText("Khoảng ngày");

        javax.swing.GroupLayout panelKhoangNgay4Layout = new javax.swing.GroupLayout(panelKhoangNgay4);
        panelKhoangNgay4.setLayout(panelKhoangNgay4Layout);
        panelKhoangNgay4Layout.setHorizontalGroup(
            panelKhoangNgay4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay4Layout.createSequentialGroup()
                .addGroup(panelKhoangNgay4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelKhoangNgay4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay4Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(ngayBatDauNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addGap(3, 3, 3)))
                .addComponent(ngayKetThucNcc, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        panelKhoangNgay4Layout.setVerticalGroup(
            panelKhoangNgay4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelKhoangNgay4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelKhoangNgay4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ngayKetThucNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19)
                    .addComponent(ngayBatDauNcc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addContainerGap(108, Short.MAX_VALUE)
                .addComponent(panelKhoangNgay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(88, 88, 88))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKhoangNgay4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableNcc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã nhà cung cấp", "Tên nhà cung cấp", "Mã phiếu nhập", "Ngày lập", "Mã sản phẩm", "Đơn giá", "Số lượng", "Thành tiền"
            }
        ));
        jScrollPane6.setViewportView(tableNcc);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(344, 344, 344)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(385, Short.MAX_VALUE))
            .addComponent(jScrollPane6)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 476, Short.MAX_VALUE))
        );

        tabs.addTab("Nhà cung cấp", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 1232, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 626, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ngayBatDauPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayBatDauPropertyChange
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }//GEN-LAST:event_ngayBatDauPropertyChange

    private void ngayKetThucPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayKetThucPropertyChange
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }//GEN-LAST:event_ngayKetThucPropertyChange

    private void ngayKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayKetThucActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngayKetThucActionPerformed

    private void comboboxSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxSpActionPerformed
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }//GEN-LAST:event_comboboxSpActionPerformed

    private void comboboxNvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxNvActionPerformed
        onChangeThongKeBanHang();
        onChangeThongKeNhapHang();
    }//GEN-LAST:event_comboboxNvActionPerformed

    private void comboboxKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxKHActionPerformed
        onChangeThongKeBanHang();
    }//GEN-LAST:event_comboboxKHActionPerformed

    private void comboboxNccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxNccActionPerformed
        onChangeThongKeNhapHang();
    }//GEN-LAST:event_comboboxNccActionPerformed

    private void ngayKetThucSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayKetThucSpActionPerformed
    }//GEN-LAST:event_ngayKetThucSpActionPerformed

    private void ngayKetThucSpPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayKetThucSpPropertyChange
        String item = comboboxDKThongKeSp.getSelectedItem().toString();
        if (item.equals("Số lượng nhập")) {
            soLuongSanPhamNhap();
        } else {
            soLuongSanPhamBan();
        }
    }//GEN-LAST:event_ngayKetThucSpPropertyChange

    private void ngayBatDauSpPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayBatDauSpPropertyChange
        String item = comboboxDKThongKeSp.getSelectedItem().toString();
        if (item.equals("Số lượng nhập")) {
            soLuongSanPhamNhap();
        } else {
            soLuongSanPhamBan();
        }
    }//GEN-LAST:event_ngayBatDauSpPropertyChange

    private void ngayKetThucNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayKetThucNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngayKetThucNVActionPerformed

    private void ngayKetThucNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayKetThucNVPropertyChange
        tongTienTungNhanVien_searchOnChange();
    }//GEN-LAST:event_ngayKetThucNVPropertyChange

    private void ngayBatDauNVPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayBatDauNVPropertyChange
        tongTienTungNhanVien_searchOnChange();
    }//GEN-LAST:event_ngayBatDauNVPropertyChange

    private void ngayKetThucKhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayKetThucKhActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ngayKetThucKhActionPerformed

    private void ngayKetThucKhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayKetThucKhPropertyChange
        tongTienTungKhachHang_searchOnChange();
    }//GEN-LAST:event_ngayKetThucKhPropertyChange

    private void ngayBatDauKhPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayBatDauKhPropertyChange
        tongTienTungKhachHang_searchOnChange();
    }//GEN-LAST:event_ngayBatDauKhPropertyChange

    private void ngayKetThucNccActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ngayKetThucNccActionPerformed

    }//GEN-LAST:event_ngayKetThucNccActionPerformed

    private void ngayKetThucNccPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayKetThucNccPropertyChange
        tongTienThanhToan();
    }//GEN-LAST:event_ngayKetThucNccPropertyChange

    private void ngayBatDauNccPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_ngayBatDauNccPropertyChange
        tongTienThanhToan();
    }//GEN-LAST:event_ngayBatDauNccPropertyChange

    private void comboboxDKThongKeSpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboboxDKThongKeSpActionPerformed
        String item = comboboxDKThongKeSp.getSelectedItem().toString();
        if (item.equals("Số lượng nhập")) {

            JTableHeader th = tableSp.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc2 = tcm.getColumn(2);
            TableColumn tc3 = tcm.getColumn(3);
            tc2.setHeaderValue("Mã phiếu nhập");
            tc3.setHeaderValue("Tên nhà cung cấp");
            th.repaint();
            soLuongSanPhamNhap();
        } else {
            JTableHeader th = tableSp.getTableHeader();
            TableColumnModel tcm = th.getColumnModel();
            TableColumn tc2 = tcm.getColumn(2);
            TableColumn tc3 = tcm.getColumn(3);
            tc2.setHeaderValue("Mã hóa đơn");
            tc3.setHeaderValue("Tên nhân viên");
            th.repaint();
            soLuongSanPhamBan();
        }
    }//GEN-LAST:event_comboboxDKThongKeSpActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new fThongKe().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(fThongKe.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    public void clearTable(DefaultTableModel table, javax.swing.JTable fTable) {
        if (table != null) {
            while (fTable.getRowCount() > 0) {
                table.removeRow(0);
            }
        }
    }

    private void onChangeThongKeBanHang() {
        if (thongKeTongTbl != null && ketQuaHoaDonTbl != null) {
            clearTable(thongKeTongTbl, tableThongKeTong);

            int tongSLHoaDon = 0;
            int tongSLSanPham = 0;
            float tongTatCaTien = 0;

            String valueSelect = "";
            valueSelect = comboboxSp.getSelectedItem().toString();
            String maspLoc = valueSelect.split(" ")[0];
            if (maspLoc == null) {
                maspLoc = "";
            }
            valueSelect = comboboxNv.getSelectedItem().toString();
            String manvLoc = valueSelect.split(" ")[0];
            if (manvLoc == null) {
                manvLoc = "";
            }
            valueSelect = comboboxKH.getSelectedItem().toString();
            String makhLoc = valueSelect.split(" ")[0];
            if (makhLoc == null) {
                makhLoc = "";
            }

            ArrayList<NhanVien> dsnv = new ArrayList<>(); // danh sách lưu các nhân viên có làm hóa đơn
            ArrayList<KhachHang> dskh = new ArrayList<>(); // danh sách lưu các khách hàng có làm hóa đơn
            ArrayList<SanPham> dssp = new ArrayList<>(); // lưu các sản phẩm

            MyCheckDate mcd = new MyCheckDate(ngayBatDau, ngayKetThuc);
            ArrayList<HoaDon> dshd = daoQuanLyHoaDon.getInstance().searchNew("", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1);
            for (HoaDon hd : dshd) {
                float tongTien = 0;
                ArrayList<ChiTietHoaDon> dscthd = daoQuanLyChiTietHoaDon.getInstance().getAllChiTiet(hd.getMaHoaDon());
                if (dscthd.size() > 0) {
                    NhanVien nv = daoNhanVien.getInstance().getNVByID(hd.getMaNhanVien());
                    KhachHang kh = daoKhachHang.getInstance().getKhachHangByID(hd.getMaKhachHang());

                    // lọc theo textfield mã
                    // bỏ qua lần lặp for này nếu nhân viên hoặc khách hàng ko thỏa bộ lọc
                    if (!manvLoc.equals("") && !nv.getMaNV().equals(manvLoc)
                            || !makhLoc.equals("") && !kh.getMaKH().equals(makhLoc)) {
                        continue; // continue này sẽ lấy vòng lặp HoaDon tiếp theo
                    }

                    // thêm vào arraylist để đếm
                    if (!dsnv.contains(nv)) {
                        dsnv.add(nv); // thêm vào nếu chưa có
                    }
                    if (!dskh.contains(kh)) {
                        dskh.add(kh); // thêm vào nếu chưa có
                    }

                    thongKeTongTbl.addRow(new String[]{
                        hd.getMaHoaDon() + " (" + hd.getNgayLap().toString() + ")",
                        nv.getTenNV() + " (" + nv.getMaNV() + ")",
                        kh.getTenKH() + " (" + kh.getMaKH() + ")",
                        "", "", "", ""
                    });

                    for (ChiTietHoaDon cthd : dscthd) {
                        SanPham sp = daoSanPham.getInstance().getSanPham(cthd.getMaSanPham());

                        // lọc
                        if (!maspLoc.equals("") && !sp.getMaSP().equals(maspLoc)) {
                            continue; // continue này sẽ lấy vòng lặp ChiTietHoaDon tiếp theo
                        }

                        // thêm vào danh sách để đếm
                        if (!dssp.contains(sp)) {
                            dssp.add(sp); // thêm vào nếu chưa có
                        }

                        thongKeTongTbl.addRow(new String[]{"", "", "",
                            sp.getTenSP() + " (" + sp.getMaSP() + ")",
                            String.valueOf(cthd.getSoLuong()),
                            PriceFormatter.format(cthd.getDonGia()),
                            PriceFormatter.format(cthd.getSoLuong() * cthd.getDonGia())
                        });

                        tongTien += cthd.getDonGia() * cthd.getSoLuong();
                        tongSLSanPham += cthd.getSoLuong();
                    }
                }
                thongKeTongTbl.addRow(new String[]{"", "", "", "", "", "Tổng cộng", PriceFormatter.format(tongTien)});
                thongKeTongTbl.addRow(new String[]{"", "", "", "", "", "", ""});

                tongTatCaTien += tongTien;
                tongSLHoaDon++;
            }

            clearTable(ketQuaHoaDonTbl, tableKqThongKe);

            ketQuaHoaDonTbl.addRow(new String[]{
                tongSLHoaDon + " hóa đơn",
                dsnv.size() + " nhân viên",
                dskh.size() + " khách hàng",
                dssp.size() + " mặt hàng",
                tongSLSanPham + " sản phẩm",
                "",
                PriceFormatter.format(tongTatCaTien)
            });
        }
    }

    private void onChangeThongKeNhapHang() {
        if (thongKePhieuNhapTbl != null && ketQuaThongKePhieuNhapTbl != null) {
            clearTable(thongKePhieuNhapTbl, tableThongKePhieuNhap);
            int tongSLPhieuNhap = 0;
            int tongSLSanPham = 0;
            float tongTatCaTien = 0;

            String valueSelect = "";
            valueSelect = comboboxSp.getSelectedItem().toString();
            String maspLoc = valueSelect.split(" ")[0];
            if (maspLoc == null) {
                maspLoc = "";
            }
            valueSelect = comboboxNv.getSelectedItem().toString();
            String manvLoc = valueSelect.split(" ")[0];
            if (manvLoc == null) {
                manvLoc = "";
            }
            valueSelect = comboboxNcc.getSelectedItem().toString();
            String manccLoc = valueSelect.split(" ")[0];
            if (manccLoc == null) {
                manccLoc = "";
            }

            ArrayList<NhanVien> dsnv = new ArrayList<>(); // danh sách lưu các nhân viên có làm phiếu nhập
            ArrayList<NhaCungCap> dsncc = new ArrayList<>(); // danh sách lưu các ncc có làm phiếu nhập
            ArrayList<SanPham> dssp = new ArrayList<>(); // lưu các sản phẩm

            MyCheckDate mcd = new MyCheckDate(ngayBatDau, ngayKetThuc);
            ArrayList<PhieuNhap> dspn = daoPhieuNhap.getInstance().search("", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1);
            for (PhieuNhap pn : dspn) {
                float tongTien = 0;
                ArrayList<ChiTietPhieuNhap> dsctpn = daoChiTietPhieuNhap.getInstance().getAllChiTiet(pn.getMaPN());

                if (dsctpn.size() > 0) {
                    NhanVien nv = daoNhanVien.getInstance().getNVByID(pn.getMaNV());
                    NhaCungCap ncc = daoNhaCungCap.getInstance().getNhaCungCap(pn.getMaNCC());

                    // lọc theo textfield mã
                    // bỏ qua lần lặp for này nếu nhân viên hoặc nha cung cap ko thỏa bộ lọc
                    if (!manvLoc.equals("") && !nv.getMaNV().equals(manvLoc)
                            || !manccLoc.equals("") && !ncc.getMaNCC().equals(manccLoc)) {
                        continue; // continue này sẽ lấy vòng lặp PhieuNhap tiếp theo
                    }

                    // thêm vào arraylist để đếm
                    if (!dsnv.contains(nv)) {
                        dsnv.add(nv); // thêm vào nếu chưa có
                    }
                    if (!dsncc.contains(ncc)) {
                        dsncc.add(ncc); // thêm vào nếu chưa có
                    }

                    thongKePhieuNhapTbl.addRow(new String[]{
                        pn.getMaPN() + " (" + pn.getNgayNhap().toString() + ")",
                        nv.getTenNV() + " (" + nv.getMaNV() + ")",
                        ncc.getTenNCC() + " (" + ncc.getMaNCC() + ")",
                        "", "", "", ""
                    });

                    for (ChiTietPhieuNhap ctpn : dsctpn) {
                        SanPham sp = daoSanPham.getInstance().getSanPham(ctpn.getMaSP());

                        // lọc
                        if (!maspLoc.equals("") && !sp.getMaSP().equals(maspLoc)) {
                            continue; // continue này sẽ lấy vòng lặp ChiTietPhieuNhap tiếp theo
                        }

                        // thêm vào danh sách để đếm
                        if (!dssp.contains(sp)) {
                            dssp.add(sp); // thêm vào nếu chưa có
                        }

                        thongKePhieuNhapTbl.addRow(new String[]{"", "", "",
                            sp.getTenSP() + " (" + sp.getMaSP() + ")",
                            String.valueOf(ctpn.getSoLuong()),
                            PriceFormatter.format(ctpn.getDonGia()),
                            PriceFormatter.format(ctpn.getSoLuong() * ctpn.getDonGia())
                        });

                        tongTien += ctpn.getDonGia() * ctpn.getSoLuong();
                        tongSLSanPham += ctpn.getSoLuong();
                    }
                }
                thongKePhieuNhapTbl.addRow(new String[]{"", "", "", "", "", "Tổng cộng", PriceFormatter.format(tongTien)});
                thongKePhieuNhapTbl.addRow(new String[]{"", "", "", "", "", "", ""});

                tongTatCaTien += tongTien;
                tongSLPhieuNhap++;
            }

            clearTable(ketQuaThongKePhieuNhapTbl, tableKqThongKePhieuNhap);
            ketQuaThongKePhieuNhapTbl.addRow(new String[]{
                tongSLPhieuNhap + " phiếu nhập",
                dsnv.size() + " nhân viên",
                dsncc.size() + " nhà cung cấp",
                dssp.size() + " mặt hàng",
                tongSLSanPham + " sản phẩm",
                "",
                PriceFormatter.format(tongTatCaTien)
            });
        }
    }

    private void soLuongSanPhamNhap() {
        if (thongKeSpTbl != null) {
            clearTable(thongKeSpTbl, tableSp);
            MyCheckDate mcd = new MyCheckDate(ngayBatDauSp, ngayKetThucSp);
            int tongTatCa = 0;
            float tongTien = 0;
            for (SanPham sp : daoSanPham.getInstance().getListSanPham()) {
                int tongSoLuong = 0;
                float tongTienPhieuNhapCuaMoiSanPham = 0;
                thongKeSpTbl.addRow(new String[]{sp.getMaSP(), sp.getTenSP(), "", "", "", "", String.valueOf(sp.getDonGia()), ""});
                ArrayList<PhieuNhap> dspn = daoPhieuNhap.getInstance().search("", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1);
                for (PhieuNhap pn : dspn) {
                    ChiTietPhieuNhap ctpn = daoChiTietPhieuNhap.getInstance().getChiTietPhieuNhap(pn.getMaPN(), sp.getMaSP());

                    if (ctpn != null) {
                        thongKeSpTbl.addRow(new String[]{"", "",
                            pn.getMaPN(),
                            daoNhaCungCap.getInstance().getNhaCungCap(pn.getMaNCC()).getTenNCC(),
                            String.valueOf(pn.getNgayNhap()),
                            String.valueOf(ctpn.getSoLuong()),
                            "",
                            String.valueOf(ctpn.getSoLuong() * ctpn.getDonGia())

                        });
                        tongSoLuong += ctpn.getSoLuong();
                        tongTienPhieuNhapCuaMoiSanPham += ctpn.getSoLuong() * ctpn.getDonGia();
                    }
                }

                thongKeSpTbl.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong), "", String.valueOf(tongTienPhieuNhapCuaMoiSanPham)});
                thongKeSpTbl.addRow(new String[]{"", "", "", "", "", "", "", ""});

                tongTatCa += tongSoLuong;
                tongTien += tongTienPhieuNhapCuaMoiSanPham;
            }
            thongKeSpTbl.addRow(new String[]{"", "", "", "", "Tổng tất cả", String.valueOf(tongTatCa), "", String.valueOf(tongTien)});
        }
    }

    private void soLuongSanPhamBan() {
        if (thongKeSpTbl != null) {
            clearTable(thongKeSpTbl, tableSp);
            MyCheckDate mcd = new MyCheckDate(ngayBatDauSp, ngayKetThucSp);

            int tongTatCa = 0;
            float tongTien = 0;
            ArrayList<SanPham> dssp = daoSanPham.getInstance().getListSanPham();
            ArrayList<HoaDon> dshd = null;
            for (SanPham sp : dssp) {
                int tongSoLuong = 0;
                float tongTienHoaDonTungSanPham = 0;
                thongKeSpTbl.addRow(new String[]{sp.getMaSP(), sp.getTenSP(), "", "", "", "", String.valueOf(sp.getDonGia()), ""});
                dshd = daoQuanLyHoaDon.getInstance().searchNew("", mcd.getNgayTu(), mcd.getNgayTu(), -1, -1);
                for (HoaDon hd : dshd) {
                    ChiTietHoaDon cthd = daoQuanLyChiTietHoaDon.getInstance().getChiTiet(hd.getMaHoaDon(), sp.getMaSP());
                    if (cthd != null) {
                        thongKeSpTbl.addRow(new String[]{"", "",
                            hd.getMaHoaDon(),
                            daoNhanVien.getInstance().getNameNVByID(hd.getMaNhanVien()),
                            String.valueOf(hd.getNgayLap()),
                            String.valueOf(cthd.getSoLuong()), "", String.valueOf(cthd.getSoLuong() * cthd.getDonGia())
                        });
                        tongSoLuong += cthd.getSoLuong();
                        tongTienHoaDonTungSanPham += cthd.getSoLuong() * cthd.getDonGia();
                    }
                }

                thongKeSpTbl.addRow(new String[]{"", "", "", "", mcd.getKhoangTG(), String.valueOf(tongSoLuong), "", String.valueOf(tongTienHoaDonTungSanPham)});
                thongKeSpTbl.addRow(new String[]{"", "", "", "", "", ""});
                tongTatCa += tongSoLuong;
                tongTien += tongTienHoaDonTungSanPham;
            }

            thongKeSpTbl.addRow(new String[]{"", "", "", "", "T?ng t?t c?", String.valueOf(tongTatCa), "", String.valueOf(tongTien)});
        }
    }

    public void tongTienTungNhanVien_searchOnChange() {
        thongKeNvTbl = (DefaultTableModel) tableTkNv.getModel();
        if (thongKeNvTbl != null && tableTkNv != null) {
            clearTable(thongKeNvTbl, tableTkNv);
            MyCheckDate mcd = new MyCheckDate(ngayBatDauNV, ngayKetThucNV);

            //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
            float tongTatCa = 0;
            ArrayList<NhanVien> dsnv = daoNhanVien.getInstance().getListNhanVien();
            for (NhanVien nv : dsnv) {
                float tongTien = 0;
                thongKeNvTbl.addRow(new String[]{nv.getMaNV(), nv.getTenNV(), "", ""});

                for (HoaDon hd : daoQuanLyHoaDon.getInstance().searchNew("", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                    if (nv.getMaNV().equals(hd.getMaNhanVien())) {
                        thongKeNvTbl.addRow(new String[]{"", "",
                            hd.getMaHoaDon(),
                            String.valueOf(hd.getNgayLap()),
                            PriceFormatter.format(hd.getTongTien())
                        });
                        tongTien += hd.getTongTien();
                    }
                }
                thongKeNvTbl.addRow(new String[]{"", "", "", mcd.getKhoangTG(), PriceFormatter.format(tongTien)});
                thongKeNvTbl.addRow(new String[]{"", "", "", "", "", ""});

                tongTatCa += tongTien;
            }
            thongKeNvTbl.addRow(new String[]{"", "", "", "Tổng tất cả", PriceFormatter.format(tongTatCa)});
        }
    }

    public void tongTienTungKhachHang_searchOnChange() {
        DefaultTableModel thongKekhTbl = (DefaultTableModel) tableTkKh.getModel();
        if (thongKekhTbl != null) {
            clearTable(thongKekhTbl, tableTkKh);

            MyCheckDate mcd = new MyCheckDate(ngayBatDauKh, ngayKetThucKh);

            //Tim hoa don cua tung nhan vien, sau do xuat tong tien cac hoa don len table
            float tongTatCa = 0;
            for (KhachHang kh : daoKhachHang.getInstance().getListKhachHang()) {
                float tongTien = 0;
                thongKekhTbl.addRow(new String[]{kh.getMaKH(), kh.getTenKH(), "", "", ""});

                for (HoaDon hd : daoQuanLyHoaDon.getInstance().searchNew("", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                    if (kh.getMaKH().equals(hd.getMaKhachHang())) {
                        thongKekhTbl.addRow(new String[]{"", "",
                            hd.getMaHoaDon(),
                            String.valueOf(hd.getNgayLap()),
                            PriceFormatter.format(hd.getTongTien())
                        });
                        tongTien += hd.getTongTien();
                    }
                }
                thongKekhTbl.addRow(new String[]{"", "", "", mcd.getKhoangTG(), PriceFormatter.format(tongTien)});
                thongKekhTbl.addRow(new String[]{"", "", "", "", "", ""});

                tongTatCa += tongTien;
            }

            thongKekhTbl.addRow(new String[]{"", "", "", "Tổng tất cả", PriceFormatter.format(tongTatCa)});
        }
    }

    private void tongTienThanhToan() {
        DefaultTableModel thongKeNccTbl = (DefaultTableModel) tableNcc.getModel();
        if (thongKeNccTbl != null) {
            clearTable(thongKeNccTbl, tableNcc);
            MyCheckDate mcd = new MyCheckDate(ngayBatDauNcc, ngayKetThucNcc);
            float tongTatCa = 0;
            for (NhaCungCap ncc : daoNhaCungCap.getInstance().getListNhaCungCap()) {
                float tongTien = 0;
                thongKeNccTbl.addRow(new String[]{ncc.getMaNCC(), ncc.getTenNCC(), "", "", "", "", "", ""});
                for (PhieuNhap pn : daoPhieuNhap.getInstance().search("", mcd.getNgayTu(), mcd.getNgayDen(), -1, -1)) {
                    if (pn.getMaNCC().equals(ncc.getMaNCC())) {
                        thongKeNccTbl.addRow(new String[]{"", "", pn.getMaPN(), String.valueOf(pn.getNgayNhap()), "", "", "", ""});
                        for (ChiTietPhieuNhap ctpn : daoChiTietPhieuNhap.getInstance().search("Mã phiếu nhập", pn.getMaPN())) {
                            tongTien += ctpn.getSoLuong() * ctpn.getDonGia();
                            thongKeNccTbl.addRow(new String[]{"", "", "", "",
                                ctpn.getMaSP(),
                                String.valueOf(ctpn.getDonGia()),
                                String.valueOf(ctpn.getSoLuong()),
                                PriceFormatter.format(ctpn.getSoLuong() * ctpn.getDonGia())});
                        }
                    }
                }
                thongKeNccTbl.addRow(new String[]{"", "", "", mcd.getKhoangTG(), "", "", "Tổng thành tiền:", PriceFormatter.format(tongTien)});
                thongKeNccTbl.addRow(new String[]{"", "", "", "", "", "", "", ""});

                tongTatCa += tongTien;
            }
            thongKeNccTbl.addRow(new String[]{"", "", "", "", "", "", "Tổng tất cả:", PriceFormatter.format(tongTatCa)});
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboboxDKThongKeSp;
    private javax.swing.JComboBox<String> comboboxKH;
    private javax.swing.JComboBox<String> comboboxNcc;
    private javax.swing.JComboBox<String> comboboxNv;
    private javax.swing.JComboBox<String> comboboxSp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private java.awt.Label label1;
    private org.jdesktop.swingx.JXDatePicker ngayBatDau;
    private org.jdesktop.swingx.JXDatePicker ngayBatDauKh;
    private org.jdesktop.swingx.JXDatePicker ngayBatDauNV;
    private org.jdesktop.swingx.JXDatePicker ngayBatDauNcc;
    private org.jdesktop.swingx.JXDatePicker ngayBatDauSp;
    private org.jdesktop.swingx.JXDatePicker ngayKetThuc;
    private org.jdesktop.swingx.JXDatePicker ngayKetThucKh;
    private org.jdesktop.swingx.JXDatePicker ngayKetThucNV;
    private org.jdesktop.swingx.JXDatePicker ngayKetThucNcc;
    private org.jdesktop.swingx.JXDatePicker ngayKetThucSp;
    private javax.swing.JPanel panelKh;
    private javax.swing.JPanel panelKhoangNgay;
    private javax.swing.JPanel panelKhoangNgay1;
    private javax.swing.JPanel panelKhoangNgay2;
    private javax.swing.JPanel panelKhoangNgay3;
    private javax.swing.JPanel panelKhoangNgay4;
    private javax.swing.JPanel panelNcc;
    private javax.swing.JPanel panelNv;
    private javax.swing.JPanel panelSp;
    private javax.swing.JTable tableKqThongKe;
    private javax.swing.JTable tableKqThongKePhieuNhap;
    private javax.swing.JTable tableNcc;
    private javax.swing.JTable tableSp;
    private javax.swing.JTable tableThongKePhieuNhap;
    private javax.swing.JTable tableThongKeTong;
    private javax.swing.JTable tableTkKh;
    private javax.swing.JTable tableTkNv;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTabbedPane tabsThongkeTongQuat;
    // End of variables declaration//GEN-END:variables
}
