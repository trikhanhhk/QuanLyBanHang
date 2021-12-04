/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietPhieuNhap;
import DTO.KhuVuc;
import DTO.NhaCungCap;
import DTO.NhanVien;
import DTO.XuatKho;
import GUI.fNhacungcap;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
import java.lang.*;

/**
 *
 * @author tk0038
 */
public class daoNhaCungCap {

    private static daoNhaCungCap instance;

    public static daoNhaCungCap getInstance() {
        if (instance == null) {
            instance = new daoNhaCungCap();
        }
        return instance;
    }

    public daoNhaCungCap() {
    }

    //Lấy danh sách thông tin từ bảng nhà cung cấp
    public ArrayList<NhaCungCap> getListNhaCungCap() {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        String query = "select *from nhacungcap";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //String maNCC,String tenNCC, String diaChi, String SDT, String Fax
                result.add(new NhaCungCap(rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getString("FAX")));
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }

    //Tìm kiếm trong bảng nguồn cung cấp (cũ)
    public ArrayList<NhaCungCap> FindListNhaCungCap(String ValToSearch) {
        ArrayList<NhaCungCap> NhaCungCapList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `nhacungcap` WHERE CONCAT(`MaNCC`, `TenNCC`,`DiaChi`,`SDT`,`FAX`) LIKE '%" + ValToSearch + "%'";
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(searchQuery, arr);
            NhaCungCap Nguon;

            while (rs.next()) {
                Nguon = new NhaCungCap(rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getString("FAX"));
                NhaCungCapList.add(Nguon);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return NhaCungCapList;
    }

    //Thêm nguồn cung cấp mới
    public boolean insertNhaCungCap(String maNCC,String tenNCC, String diaChi, String SDT, String Fax, String maNV) {
        if ("".equals(maNCC) || "".equals(tenNCC) || "".equals(diaChi) || "".equals(SDT) || "".equals(Fax)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            DAO.DataProvider.getIntance().open();
            PreparedStatement ps = DAO.DataProvider.getIntance().getconn().prepareStatement("INSERT INTO `nhacungcap`(`MaNCC`, `TenNCC`, `DiaChi`, `SDT`, `Fax`) VALUES (?,?,?,?,?)");
            ps.setString(1, maNCC);
            ps.setString(2, tenNCC);
            ps.setString(3, diaChi);
            ps.setString(4, SDT);
            ps.setString(5, Fax);
            ps.executeUpdate();
            DAO.DataProvider.getIntance().close();
            JOptionPane.showMessageDialog(null,
                    "Thêm nhà cung cấp mới thành công.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

           // DAO.daoThongBao.getInstance().insertThongBao("[Nhà cung cấp] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(maNV).getTenNV() + " đã thêm nhà cung cấp mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //Lấy ra 1 nhà cung cấp bằng id
    public NhaCungCap getNhaCungCap(String maNCC) {
        String query = "SELECT * FROM `nhacungcap` WHERE MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();
        NhaCungCap ncc = null;
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                ncc = new NhaCungCap(rs.getString("MaNCC"),
                        rs.getString("TenNCC"),
                        rs.getString("DiaChi"),
                        rs.getString("SDT"),
                        rs.getString("FAX"));

            } else {
                return null;
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return ncc;
    }

    //Update thông tin nguồn cung cấp
    public boolean UpdateNhaCungCap(String maNCC, String tenNCC, String diaChi, String SDT, String Fax, String maNV) {
        if ("".equals(tenNCC) || "".equals(diaChi) || "".equals(SDT) || "".equals(Fax)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "UPDATE `nhacungcap` SET `TenNCC`='" + tenNCC + "',`DiaChi`='" + diaChi + "',`SDT`='" + SDT + "',`FAX`='" + Fax + "' WHERE `MaNCC`='" + maNCC + "'";
        //System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().open();
        DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin nhà cung cấp thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        //DAO.daoThongBao.getInstance().insertThongBao("[Nhà cung cấp] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(IdNhanVien).getTenNV() + " đã sửa thông tin của nhà cung cấp vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        return true;
    }
    public boolean HuyNhaCungCap(int maNCC, int maNV)
    {
        String query = "UPDATE `nhacungcap` SET `id_exist`=0 WHERE `MaNCC`=" + maNCC;
        //System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().open();
        DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Xóa thông tin nhà cung cấp thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        //DAO.daoThongBao.getInstance().insertThongBao("[Nhà cung cấp] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(maNV).ten_nv + " đã xóa thông tin của nhà cung cấp vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        return true;
    }

    //Tìm số lần nhập kho của 1 nhà cung cấp
    public int GetSoLanNhapKho(int maNCC) {
        int SoLanNhapKho = 0;
        String query = "SELECT * FROM `Chi_tiet_phieu_nhap` WHERE MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                SoLanNhapKho++;
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }
        return SoLanNhapKho;

    }

    //Tìm số lần xuất kho của một nhà cung cấp
    public int GetSoLanXuatKho(int maNCC) {
        int SoLanXuatKho = 0;
        String query = "SELECT `phieu_xuat_kho`.`id_xuat_kho`, `lo_san_pham`.`id_phieu_nhap`,`chi_tiet_phieu_nhap`.`MaNCC` "
                + "FROM `phieu_xuat_kho`,`lo_san_pham`,`chi_tiet_phieu_nhap` "
                + "WHERE `phieu_xuat_kho`.`id_lo_sp`=`lo_san_pham`.`id_lo_sp` and `chi_tiet_phieu_nhap`.`id_phieu_nhap`=`lo_san_pham`.`id_phieu_nhap` and MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                ++SoLanXuatKho;
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return SoLanXuatKho;

    }
    public int GetSoLanTraKho(int maNCC)
    {
        int SoLanTraKho = 0;
        String query = "SELECT `phieu_tra_kho`.`id_phieu_tra_kho`, `lo_san_pham`.`id_phieu_nhap`,`chi_tiet_phieu_nhap`.`MaNCC` "
                + "FROM `phieu_tra_kho`,`lo_san_pham`,`chi_tiet_phieu_nhap` "
                + "WHERE `phieu_tra_kho`.`id_lo_sp`=`lo_san_pham`.`id_lo_sp` and `chi_tiet_phieu_nhap`.`id_phieu_nhap`=`lo_san_pham`.`id_phieu_nhap` and MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                ++SoLanTraKho;
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return SoLanTraKho;
    }
    //Tìm số lượng lô nhập kho của một nhà cung cấp
    public int GetSoLuongTraKho(int maNCC)
    {
        int SoLuongXuatKho = 0;
        String query = "SELECT * "
                + "FROM `phieu_tra_kho`,`lo_san_pham`,`chi_tiet_phieu_nhap` "
                + "WHERE `phieu_tra_kho`.`id_lo_sp`=`lo_san_pham`.`id_lo_sp` and `chi_tiet_phieu_nhap`.`id_phieu_nhap`=`lo_san_pham`.`id_phieu_nhap` and MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                SoLuongXuatKho = SoLuongXuatKho + rs.getInt("sl_san_pham");
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return SoLuongXuatKho;
    }
    public int GetSoLuongNhapKho(int maNCC) {
        int SoLuongNhapKho = 0;
        String query = "SELECT * FROM `Chi_tiet_phieu_nhap` WHERE MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                SoLuongNhapKho = SoLuongNhapKho + rs.getInt("so_luong_lo");
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }
        return SoLuongNhapKho;
    }

    //Tìm số lượng lô xuất kho của một nhà cung cấp
    public int GetSoLuongXuatKho(int maNCC) {
        int SoLuongXuatKho = 0;
        String query = "SELECT * "
                + "FROM `phieu_xuat_kho`,`lo_san_pham`,`chi_tiet_phieu_nhap` "
                + "WHERE `phieu_xuat_kho`.`id_lo_sp`=`lo_san_pham`.`id_lo_sp` and `chi_tiet_phieu_nhap`.`id_phieu_nhap`=`lo_san_pham`.`id_phieu_nhap` and MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();

        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);

            while (rs.next()) {
                SoLuongXuatKho = SoLuongXuatKho + rs.getInt("sl_san_pham");
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return SoLuongXuatKho;

    }

    //Tìm kiếm trong bảng nguồn cung cấp (mới)
    public ArrayList<NhaCungCap> FindListNhaCungCap(ArrayList<NhaCungCap> DuLieuMau, String ValToSearch) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        for (int i = 0; i < DuLieuMau.size(); i++) {

            if (DuLieuMau.get(i).getDiaChi().contains(ValToSearch)
                    || DuLieuMau.get(i).getFax().contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getMaNCC()).contains(ValToSearch)
                    || DuLieuMau.get(i).getSDT().contains(ValToSearch)
                    || DuLieuMau.get(i).getTenNCC().contains(ValToSearch)) {

                result.add(DuLieuMau.get(i));
            }
        }
        return result;
    }

    //Lấy ra danh sách 20 nguồn cung cấp, để làm phân trang
    public ArrayList<NhaCungCap> get20NhaCungCap(ArrayList<NhaCungCap> arr, long Trang) {
        ArrayList<NhaCungCap> result = new ArrayList<>();
        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }

    // Xuất file Excel cho nguồn cung cấp
    public void ExcelNhaCungCap(ArrayList<NhaCungCap> arr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("NhaCungCap");
        int rownum = 0;
        Cell cell;
        Row row;
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Mã nhà cung cấp");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(1);
        cell.setCellValue("Tên nhà cung cấp");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(2);
        cell.setCellValue("SĐT");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(3);
        cell.setCellValue("Địa Chỉ");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(4);
        cell.setCellValue("Fax");

        for (int i = 0; i < arr.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(arr.get(i).getMaNCC());
            //
            cell = row.createCell(1);
            cell.setCellValue(arr.get(i).getTenNCC());
            //
            cell = row.createCell(2);
            cell.setCellValue(arr.get(i).getSDT());
            //
            cell = row.createCell(3);
            cell.setCellValue(arr.get(i).getDiaChi());
            //
            cell = row.createCell(4);
            cell.setCellValue(arr.get(i).getFax());
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

        System.out.println("Created file: " + file.getAbsolutePath());
    }
}
