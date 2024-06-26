/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.KhachHang;
import controller_view.GUI.fNhacungcap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author trikh
 */
public class daoKhachHang {
    
    private static daoKhachHang instance;
    private ArrayList<KhachHang> listKH;

    public static daoKhachHang getInstance() {
        if (instance == null) {
            instance = new daoKhachHang();
        }
        return instance;
    }

    public daoKhachHang() {
        this.listKH = this.getListKhachHang();
    }

    //Lấy danh sách thông tin từ bảng nhân viên
    public ArrayList<KhachHang> getListKhachHang() {
        ArrayList<KhachHang> result = new ArrayList<>();
        String query = "select *from khachhang";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //String MaKH, String TenKH, String DiaChi, String SDT, int TrangThai
                result.add(new KhachHang(rs.getString("MaKH"),
                        rs.getString("TenKH"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getInt("TrangThai")));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }
    
    public String getNextID() {
        int size = this.getListKhachHang().size();
        return "KH" + String.valueOf(size + 1);
    }
    public boolean UpdateKhachHang(
            String MaKH,
            String TenKH,
            String DiaChi,
            String SDT) {
        if ("".equals(TenKH) || "".equals(SDT)|| "".equals(DiaChi)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "Update khachhang Set TenKH='" + TenKH + "', DiaChi='" + DiaChi + "', SDT='" + SDT + "' where MaKH='" + MaKH + "'";
        System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().open();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin khách hàng thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    public void insertKhachHang(String MaKH, String TenKH, String DiaChi, String SDT, int trangThai) {
        String query = "INSERT INTO `khachhang`(`MaKH`, `TenKH`, `DiaChi`, `SDT`, `TrangThai`) VALUES ('" + MaKH + "','" + TenKH + "','"  + DiaChi + "','" + SDT + "'," + trangThai + ")";
        System.out.println(query);
        try {
            ConnectDB.getIntance().open();
            ConnectDB.getIntance().excuteQuery(query);
            ConnectDB.getIntance().close();
            JOptionPane.showMessageDialog(null, "Đã thêm nhân viên " + TenKH + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm loại nhân viên " + TenKH + " Thất bại", "Thông báo", 1);
        }
    }

    //Tìm kiếm trong bảng nhân viên (cũ)
    public ArrayList<KhachHang> FindListKhachHang(String ValToSearch) {
        ArrayList<KhachHang> khachhangList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `khachhang` WHERE CONCAT(`MaKH`, `TenNV`,`NgaySinh`,`DiaChi`,`SDT`) LIKE '%" + ValToSearch + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            KhachHang khachhang;

            while (rs.next()) {
                khachhang = new KhachHang(rs.getString("MaKH"),
                        rs.getString("TenKH"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getInt("TrangThai")
                );
                khachhangList.add(khachhang);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return khachhangList;
    }
    //Xuất file Excel thông tin nhân viên

    public void ExcelKhachHang(ArrayList<KhachHang> arr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("KhachHang");
        int rownum = 0;
        Cell cell;
        Row row;
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Mã Khách hàng");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(1);
        cell.setCellValue("Tên khách hàng");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(2);
        cell.setCellValue("Địa chỉ");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(3);
        cell.setCellValue("SĐT");

        cell = row.createCell(4);
        cell.setCellValue("Trạng thái");
        for (int i = 0; i < arr.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(arr.get(i).getMaKH());
            //
            cell = row.createCell(1);
            cell.setCellValue(arr.get(i).getTenKH());
            //
            cell = row.createCell(3);
            cell.setCellValue(arr.get(i).getDiaChi());
            //
            cell = row.createCell(5);
            cell.setCellValue(arr.get(i).getSDT());
            //
            cell = row.createCell(4);
            cell.setCellValue(arr.get(i).getTrangThai());
        }
        File file = new File("C:/demo/khachhang.xls");
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

        System.out.println("Created file: " + file.getAbsolutePath());
    }
    //Tìm kiếm trong bảng nhân viên (mới)

    public ArrayList<KhachHang> FindListKhachHang(ArrayList<KhachHang> DuLieuMau, String ValToSearch) {
        ArrayList<KhachHang> result = new ArrayList<>();
        for (int i = 0; i < DuLieuMau.size(); i++) {
            if (DuLieuMau.get(i).getMaKH().contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getTenKH()).contains(ValToSearch)
                    || DuLieuMau.get(i).getSDT().contains(ValToSearch)
                    || DuLieuMau.get(i).getDiaChi().toString().contains(ValToSearch)) {
                result.add(DuLieuMau.get(i));
            }
        }
        return result;
    }
    
     public KhachHang getKhachHangByID(String ID) {
        KhachHang result = null;
        String query = "select * from khachhang where MaKH = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(ID);
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = new KhachHang(rs.getString("MaKH"),
                        rs.getString("TenKH"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getInt("TrangThai")
                );
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }
     
     public void deleteKhachHang(String id) {
         System.out.println("makh" + id);
         KhachHang kh = this.getKhachHangByID(id);
         int trangThai = 0;
         if(kh.getTrangThai() == 0) {
             trangThai=1;
         }
         String query = "Update khachhang Set  TrangThai= "+ trangThai +" where MaKH='" + id+"'";
         ConnectDB.getIntance().open();
         ConnectDB.getIntance().excuteQuery(query);
         ConnectDB.getIntance().close();
     }
     
    //Lấy danh sách 20 nhân viên, để làm phân trang

    public ArrayList<KhachHang> get20KhachHang(ArrayList<KhachHang> arr, long Trang) {
        ArrayList<KhachHang> result = new ArrayList<>();

        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }
    
}
