/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_view.GUI;

import model.DTO.NhaCungCap;
import model.DAO.daoNhaCungCap;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellType;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.lang.*;

/**
 *
 * @author HoaTran
 */
public class fNhacungcap extends javax.swing.JFrame {

    /**
     * Creates new form fNhacungcap
     */
    public String maNV;
    public ArrayList<NhaCungCap> DanhSachNhaCungCap;
    public long count, SoTrang, Trang = 1;
    public ArrayList<NhaCungCap> DuLieuMau;

    public fNhacungcap() {
        initComponents();
        setIcon();
//        build();
    }

    public fNhacungcap(String id) {
        maNV = id;
        initComponents();
        setIcon();
        DanhSachNhaCungCap = daoNhaCungCap.getInstance().getListNhaCungCap();
        DuLieuMau = DanhSachNhaCungCap;
//        build();
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        ArrayList<NhaCungCap> DuLieuMau = daoNhaCungCap.getInstance().getListNhaCungCap();
        String[] columnName = {"STT", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "FAX"};
        Object[][] rows = new Object[DuLieuMau.size()][7];
        for(int i = 0; i < DuLieuMau.size(); i++){
            rows[i][0] = i+1;
            rows[i][1] = DuLieuMau.get(i).getMaNCC();
            rows[i][2] = DuLieuMau.get(i).getTenNCC();
            rows[i][3] = DuLieuMau.get(i).getDiaChi();
            rows[i][4] = DuLieuMau.get(i).getSDT();
            rows[i][5] = DuLieuMau.get(i).getFax();
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
        jTableNhaCungCap = new javax.swing.JTable(model);
        jButtonThem = new javax.swing.JButton();
        jTextFieldTimKiem = new javax.swing.JTextField();
        jButtonExcel = new javax.swing.JButton();
        jButtonTimKiem = new javax.swing.JButton();
        jButtonLamMoi = new javax.swing.JButton();
        jLabelKetQua = new javax.swing.JLabel();
        jButtonSua = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Nhà cung cấp");
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(51, 51, 51));

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setAlignmentX(0.2F);

        /*
        jTableNhaCungCap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTableNhaCungCap.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên nhà cung cấp", "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Fax"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        */
        jTableNhaCungCap.setRequestFocusEnabled(false);
        jTableNhaCungCap.setRowHeight(50);
        jTableNhaCungCap.setRowSelectionAllowed(true);
        jTableNhaCungCap.setAutoCreateRowSorter(true);
        jTableNhaCungCap.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableNhaCungCapMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTableNhaCungCap);
        if (jTableNhaCungCap.getColumnModel().getColumnCount() > 0) {
            jTableNhaCungCap.getColumnModel().getColumn(0).setMinWidth(30);
            jTableNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableNhaCungCap.getColumnModel().getColumn(0).setMaxWidth(30);
        }

        jButtonThem.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonThem.setText("Tạo");
        ImageIcon imgTaoMoi = new ImageIcon(getClass().getResource("/icon/icons8-plus-48.png"));
        ImageIcon ImgTaoMoi = new ImageIcon(imgTaoMoi.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonThem.setIcon(ImgTaoMoi);
        jButtonThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemnhacungcap(evt);
            }
        });
        jButtonThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThemjButton2ActionPerformed(evt);
            }
        });

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
        jLabelKetQua.setText("Có tổng cộng 000 kết quả");

        jButtonSua.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonSua.setText("Sửa");
        ImageIcon imgSua = new ImageIcon(getClass().getResource("/icon/icons8-maintenance-48.png"));
        ImageIcon ImgSua = new ImageIcon(imgSua.getImage().getScaledInstance(19, 19, Image.SCALE_SMOOTH));
        jButtonSua.setIcon(ImgSua);
        jButtonSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSuaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 960, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabelKetQua))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                        .addComponent(jButtonThem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonSua)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonExcel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonTimKiem)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonLamMoi)))
                .addContainerGap())
        );

        jPanel13Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonExcel, jButtonSua, jButtonThem});

        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButtonThem)
                        .addComponent(jButtonSua)
                        .addComponent(jButtonExcel))
                    .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jTextFieldTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButtonLamMoi, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonTimKiem)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelKetQua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("DANH SÁCH NHÀ CUNG CẤP");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jLabel3)
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

    private void jButtonThemjButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThemjButton2ActionPerformed
        JFrame Them = new fCreateNhaCungCap(maNV);
        Them.setVisible(true);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonThemjButton2ActionPerformed

    private void jTextFieldTimKiemKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemKeyReleased
        if ("".equals(jTextFieldTimKiem.getText())) {
            build();
        }
    }//GEN-LAST:event_jTextFieldTimKiemKeyReleased

    private void btnThemnhacungcap(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemnhacungcap
        // TODO add your handling code here:
    }//GEN-LAST:event_btnThemnhacungcap

    private void jTableNhaCungCapMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableNhaCungCapMouseClicked
        if (evt.getClickCount() == 2 && !evt.isConsumed()) {
            evt.consume();
            int selectedRowIndex = jTableNhaCungCap.getSelectedRow();
            int id = jTableNhaCungCap.getValueAt(selectedRowIndex, 0).hashCode();
//            JFrame Xem = new fViewNhaCungCap(maNV, id,false);
//            Xem.setVisible(true);
            //System.out.print("Nhap dup chuot");
        }
        jButtonSua.setEnabled(true);

        // TODO add your handling code here:
    }//GEN-LAST:event_jTableNhaCungCapMouseClicked


    private void jButtonExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcelActionPerformed
        // TODO add your handling code here:
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");
        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Mã nhà cung cấp");

        cell = row.createCell(1);
        cell.setCellValue("Tên nhà cung cấp");

        cell = row.createCell(2);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(3);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(4);
        cell.setCellValue("Số Fax");

        for (int i = 0; i < DuLieuMau.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(DuLieuMau.get(i).getMaNCC());
            //
            cell = row.createCell(1);
            cell.setCellValue(DuLieuMau.get(i).getTenNCC());
            //
            cell = row.createCell(2);
            cell.setCellValue(DuLieuMau.get(i).getDiaChi());
            //
            cell = row.createCell(3);
            cell.setCellValue(DuLieuMau.get(i).getSDT());
            //
            cell = row.createCell(4);
            cell.setCellValue(DuLieuMau.get(i).getFax());
            //

        }
        File file = new File("C:/demo/NhaCungCap.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile;
        try {
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fNhacungcap.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(fNhacungcap.class.getName()).log(Level.SEVERE, null, ex);
        }

        JOptionPane.showMessageDialog(rootPane,
                "Đã lưu file Excel NhaCungCap trong C:/demo.",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jButtonExcelActionPerformed

    private void jTextFieldTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimKiemActionPerformed

    private void jButtonLamMoiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLamMoiActionPerformed
        DuLieuMau = daoNhaCungCap.getInstance().getListNhaCungCap();
        listDanhSachNhaCungCap(DuLieuMau);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonLamMoiActionPerformed

    private void jTextFieldTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTimKiemKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DanhSachNhaCungCap = DuLieuMau;
            FindList();
            jButtonSua.setEnabled(false);
        }        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTimKiemKeyPressed

    private void jButtonTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTimKiemActionPerformed
        DanhSachNhaCungCap = DuLieuMau;
        FindList();
        jButtonSua.setEnabled(false);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonTimKiemActionPerformed

    private void jButtonSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSuaActionPerformed
        int selectedRowIndex = jTableNhaCungCap.getSelectedRow();
        String id = jTableNhaCungCap.getValueAt(selectedRowIndex, 1).toString();
        JFrame ncc = new fSuaNCC(id);
        ncc.setVisible(true);
    }//GEN-LAST:event_jButtonSuaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame ncc = new fNhacungcap("NV12");
                ncc.setVisible(true);
            }
        });
    }

    public void build() {
        jButtonSua.setEnabled(false);
        DanhSachNhaCungCap = DuLieuMau;
        this.count = this.DanhSachNhaCungCap.size();
        jLabelKetQua.setText("Có tổng cộng " + count + " kết quả");
        if (count % 20 == 0) {
            SoTrang = count / 20;
        } else {
            SoTrang = count / 20 + 1;
        }
        ArrayList<NhaCungCap> table = model.DAO.daoNhaCungCap.getInstance().get20NhaCungCap(DanhSachNhaCungCap, 1);
        listDanhSachNhaCungCap(table);
    }

    public void FindList() {
        this.DanhSachNhaCungCap = model.DAO.daoNhaCungCap.getInstance().FindListNhaCungCap(DuLieuMau, jTextFieldTimKiem.getText());
        if (DanhSachNhaCungCap.isEmpty()) {
            JOptionPane.showMessageDialog(null,
                    "Không có dữ liệu nhà cung cấp",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
//            build();
        } else {
            this.count = this.DanhSachNhaCungCap.size();
            jLabelKetQua.setText("Có tổng cộng " + count + " kết quả");
            if (count % 20 == 0) {
                SoTrang = count / 20;
            } else {
                SoTrang = count / 20 + 1;
            }
            ArrayList<NhaCungCap> table = model.DAO.daoNhaCungCap.getInstance().get20NhaCungCap(DanhSachNhaCungCap, 1);
            listDanhSachNhaCungCap(table);
        }
    }

    public void listDanhSachNhaCungCap(ArrayList<NhaCungCap> arr) {
        DefaultTableModel model = (DefaultTableModel) jTableNhaCungCap.getModel();
        while (jTableNhaCungCap.getRowCount() > 0) {
            model.removeRow(0);
        }
        if (arr.isEmpty() == false) {
            int stt = 1;
            for (NhaCungCap ncc : arr) {
                model.addRow(new String[]{
                    String.valueOf(stt),
                    ncc.getMaNCC(),
                    ncc.getTenNCC(),
                    ncc.getDiaChi(),
                    ncc.getSDT(),
                    ncc.getFax(),}
                );
                stt++;
            }
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExcel;
    private javax.swing.JButton jButtonLamMoi;
    private javax.swing.JButton jButtonSua;
    private javax.swing.JButton jButtonThem;
    private javax.swing.JButton jButtonTimKiem;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelKetQua;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTableNhaCungCap;
    private javax.swing.JTextField jTextFieldTimKiem;
    // End of variables declaration//GEN-END:variables
}
