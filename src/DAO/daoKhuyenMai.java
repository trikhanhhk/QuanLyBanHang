/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.KhuyenMai;
import GUI.fNhacungcap;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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

/**
 *
 * @author trikh
 */
public class daoKhuyenMai {
    private static daoKhuyenMai instance;

    public static daoKhuyenMai getInstance() {
        if (instance == null) {
            instance = new daoKhuyenMai();
        }
        return instance;
    }

    public daoKhuyenMai() {
    }

    //Lấy danh sách thông tin từ bảng nhà cung cấp
    public ArrayList<KhuyenMai> getListKhuyenMai() {
        ArrayList<KhuyenMai> result = new ArrayList<>();
        String query = "select *from khuyenmai";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //String maNCC,String tenNCC, String diaChi, String SDT, String Fax
                result.add(new KhuyenMai(rs.getString("MaNCC"),
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

    //Thêm nguồn cung cấp mới
    public boolean insertKhuyenMai(String maNCC,String tenNCC, String diaChi, String SDT, String Fax, String maNV) {
        if ("".equals(maNCC) || "".equals(tenNCC) || "".equals(diaChi) || "".equals(SDT) || "".equals(Fax)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            DAO.DataProvider.getIntance().open();
            PreparedStatement ps = DAO.DataProvider.getIntance().getconn().prepareStatement("INSERT INTO `khuyenmai`(`MaNCC`, `TenNCC`, `DiaChi`, `SDT`, `Fax`) VALUES (?,?,?,?,?)");
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

    //Lấy ra 1 khuyến mãi bằng id
    public KhuyenMai getKhuyenMai(int maNCC) {
        String query = "SELECT * FROM `khuyenmai` WHERE MaNCC='" + maNCC + "'";
        ArrayList<Object> arr = new ArrayList<>();
        KhuyenMai ncc = null;
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                ncc = new KhuyenMai(rs.getString("MaNCC"),
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
    public boolean UpdateKhuyenMai(String maNCC, String tenNCC, String diaChi, String SDT, String Fax, String maNV) {
        if ("".equals(tenNCC) || "".equals(diaChi) || "".equals(SDT) || "".equals(Fax)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "UPDATE `khuyenmai` SET `TenNCC`='" + tenNCC + "',`DiaChi`='" + diaChi + "',`SDT`='" + SDT + "',`FAX`='" + Fax + "' WHERE `MaNCC`='" + maNCC + "'";
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
    public boolean HuyKhuyenMai(int maNCC, int maNV)
    {
        String query = "UPDATE `khuyenmai` SET `id_exist`=0 WHERE `MaNCC`=" + maNCC;
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
    public ArrayList<KhuyenMai> FindListKhuyenMai(ArrayList<KhuyenMai> arr, String ValToSearch) {
        ArrayList<KhuyenMai> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getMaKM().contains(ValToSearch)
                    || arr.get(i).getTenKM().contains(ValToSearch)
                    || String.valueOf(arr.get(i).getNgayBD()).contains(ValToSearch)
                    || String.valueOf(arr.get(i).getNgayKT()).contains(ValToSearch)
                    || String.valueOf(arr.get(i).getDieuKienKM()).contains(ValToSearch)
                    || String.valueOf(arr.get(i).getNgayKT()).contains(ValToSearch)) {

                result.add(arr.get(i));
            }
        }
        return result;
    }

    //Lấy ra danh sách 20 nguồn cung cấp, để làm phân trang
    public ArrayList<KhuyenMai> get20KhuyenMai(ArrayList<KhuyenMai> arr, long Trang) {
        ArrayList<KhuyenMai> result = new ArrayList<>();
        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }

    // Xuất file Excel cho khuyến mãi
    public void ExcelKhuyenMai(ArrayList<KhuyenMai> arr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Khuyến mãi");
        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Mã khuyến mãi");

        cell = row.createCell(1);
        cell.setCellValue("Tên khuyến mãi");

        cell = row.createCell(2);
        cell.setCellValue("Điều kiện");

        cell = row.createCell(3);
        cell.setCellValue("Phần trăm");

        cell = row.createCell(4);
        cell.setCellValue("Ngày bắt đầu");
        
        cell = row.createCell(5);
        cell.setCellValue("Ngày kết thúc");
        
        cell = row.createCell(6);
        cell.setCellValue("Trạng thái");

        for (int i = 0; i < arr.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(arr.get(i).getMaKM());
            //
            cell = row.createCell(1);
            cell.setCellValue(arr.get(i).getTenKM());
            //
            cell = row.createCell(2);
            cell.setCellValue(arr.get(i).getDieuKienKM());
            //
            cell = row.createCell(3);
            cell.setCellValue(arr.get(i).getPhanTramKM());
            //
            cell = row.createCell(4);
            cell.setCellValue(arr.get(i).getNgayBD().toString());
            
            cell = row.createCell(5);
            cell.setCellValue(arr.get(i).getNgayKT().toString());
            
            cell = row.createCell(6);
            cell.setCellValue(arr.get(i).getTrangThai());
            //

        }
        File file = new File("C:/demo/KhuyenMai.xls");
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