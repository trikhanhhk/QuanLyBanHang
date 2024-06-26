/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.NhaCungCap;
import model.DTO.NhanVien;
import model.DTO.SanPham;
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
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import java.lang.*;
import java.time.LocalDate;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author trikh
 */
public class daoNhanVien {

    private static daoNhanVien instance;
    private ArrayList<NhanVien> listNV;

    public static daoNhanVien getInstance() {
        if (instance == null) {
            instance = new daoNhanVien();
        }
        return instance;
    }

    public daoNhanVien() {
        this.listNV = this.getListNhanVien();
    }

    //Lấy danh sách thông tin từ bảng nhân viên
    public ArrayList<NhanVien> getListNhanVien() {
        ArrayList<NhanVien> result = new ArrayList<>();
        String query = "select *from nhanVien";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //String MaNV, String TenNV, LocalDate NgaySinh, String DiaChi, String SDT, int trangtha
                result.add(new NhanVien(rs.getString("MaNV"),
                        rs.getString("TenNV"),
                        rs.getDate("NgaySinh").toLocalDate(),
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
        return "NV" + String.valueOf(this.listNV.size() + 1);
    }
    
    public void insertNhanVien(String MaNV, String TenNV, String NgaySinh, String DiaChi, String SDT, int trangThai) {
        String query = "INSERT INTO `nhanvien`(`MaNV`, `TenNV`, `NgaySinh`, `DiaChi`, `SDT`, `TrangThai`) VALUES ('" + MaNV + "','" + TenNV + "','" + NgaySinh + "','" + DiaChi + "','" + SDT + "'," + trangThai + ")";
        System.out.println(query);
        try {
            ConnectDB.getIntance().open();
            ConnectDB.getIntance().excuteQuery(query);
            ConnectDB.getIntance().close();
            JOptionPane.showMessageDialog(null, "Đã thêm nhân viên " + TenNV + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(maNV).getTenNV() + " đã thêm loại sản phẩm mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm loại nhân viên " + TenNV + " Thất bại", "Thông báo", 1);
        }
    }
    
    public void updateNV (String MaNV, String TenNV, String NgaySinh, String DiaChi, String SDT) {
        String query = "UPDATE `nhanvien` SET TenNV ='" + TenNV + "', NgaySinh ='"+NgaySinh+"', DiaChi='" + DiaChi + "', SDT='" +SDT+ "' where MaNV='" + MaNV + "'";
        System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().open();
        int check = ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        if(check==1) {
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin nhân viên thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        }else {
            JOptionPane.showMessageDialog(null,
                "Đã có lỗi xảy ra",
                "Lỗi",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public NhanVien getNVByID(String ID) {
        NhanVien result = null;
        String query = "select * from nhanvien where MaNV = '" + ID + "'";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = new NhanVien(
                        rs.getString("MaNV"),
                        rs.getString("TenNV"),
                        rs.getDate("NgaySinh").toLocalDate(),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getInt("TrangThai"));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }
    
    public void updateStatus(String id, int status) {
        status = status == 0 ? 1 : 0;
        String query = "Update nhanvien set TrangThai = '" + status +"' where MaNV = '" + id +"'";
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().open();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin nhân viên thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
    }

    public String getNameNVByID(String ID) {
        String result = "";
        String query = "select TenNV from nhanvien where MaNV = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(ID);
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = rs.getString("TenNV");    
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }
    
    //Tìm kiếm trong bảng nhân viên (cũ)
    public ArrayList<NhanVien> FindListNhanVien(String ValToSearch) {
        ArrayList<NhanVien> nhanvienList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `nhanVien` WHERE CONCAT(`MaNV`, `TenNV`,`NgaySinh`,`DiaChi`,`SDT`) LIKE '%" + ValToSearch + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            NhanVien nhanvien;

            while (rs.next()) {
                nhanvien = new NhanVien(rs.getString("MaNV"),
                        rs.getString("TenNV"),
                        rs.getDate("NgaySinh").toLocalDate(),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getInt("TrangThai")
                );
                nhanvienList.add(nhanvien);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return nhanvienList;
    }
    //Xuất file Excel thông tin nhân viên

    public void ExcelNhanVien(ArrayList<NhanVien> arr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("NhanVien");
        int rownum = 0;
        Cell cell;
        Row row;
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Tên Nhân Viên");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(1);
        cell.setCellValue("Ngày Sinh");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(2);
        cell.setCellValue("Trạng thái");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(3);
        cell.setCellValue("SĐT");

        for (int i = 0; i < arr.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(arr.get(i).getTenNV());
            //
            cell = row.createCell(1);
            cell.setCellValue(arr.get(i).getNgaySinh().toString());
            //
            cell = row.createCell(2);
            cell.setCellValue(arr.get(i).getTrangThai());
            //
            cell = row.createCell(3);
            cell.setCellValue(arr.get(i).getSDT());
        }
        File file = new File("C:/demo/nhanvien.xls");
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

    public ArrayList<NhanVien> FindListNhanVien(ArrayList<NhanVien> DuLieuMau, String ValToSearch) {
        ArrayList<NhanVien> result = new ArrayList<>();
        for (int i = 0; i < DuLieuMau.size(); i++) {
            if (DuLieuMau.get(i).getTenNV().contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getMaNV()).contains(ValToSearch)
                    || DuLieuMau.get(i).getSDT().contains(ValToSearch)
                    || DuLieuMau.get(i).getNgaySinh().toString().contains(ValToSearch)
                    || DuLieuMau.get(i).getDiaChi().contains(ValToSearch)) {
                result.add(DuLieuMau.get(i));
            }
        }
        return result;
    }
    //Lấy danh sách 20 nhân viên, để làm phân trang

    public ArrayList<NhanVien> get20NhanVien(ArrayList<NhanVien> arr, long Trang) {
        ArrayList<NhanVien> result = new ArrayList<>();

        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }
}
