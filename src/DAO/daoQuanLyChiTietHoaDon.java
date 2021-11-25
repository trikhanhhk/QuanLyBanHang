/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietHoaDon;
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
public class daoQuanLyChiTietHoaDon {
    
    private static daoQuanLyChiTietHoaDon instance;
    private ArrayList<ChiTietHoaDon> listNV;

    public static daoQuanLyChiTietHoaDon getInstance() {
        if (instance == null) {
            instance = new daoQuanLyChiTietHoaDon();
        }
        return instance;
    }

    public daoQuanLyChiTietHoaDon() {
        this.listNV = this.getListChiTietHoaDon();
    }

    //Lấy danh sách thông tin từ bảng chi tiết hóa đơn
    public ArrayList<ChiTietHoaDon> getListChiTietHoaDon() {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();
        String query = "select *from chitiethoadon";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //String mahd, String masp, int soluong, float dongia
                result.add(new ChiTietHoaDon(rs.getString("MaHD"),
                        rs.getString("MaSP"),
                        rs.getInt("SoLuong"),
                        rs.getFloat("DonGia")));
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }
    
    public String getNextID() {
        return "NV" + String.valueOf(this.listNV.size() + 1);
    }
    
    public void insertQuanLyChiTietHoaDon(String MaHD, String MaSP, int SoLuong, float DonGia) {
        try {
            PreparedStatement ps = DAO.DataProvider.getIntance().getconn().prepareStatement("INSERT INTO chitiethoadon(`MaHD`, `MaSP`, `SoLuong`, `DonGia`) VALUES (?,?,?,?)");
            ps.setString(1, MaHD);
            ps.setString(2, MaSP);
            ps.setInt(3, SoLuong);
            ps.setFloat(4, DonGia);
            ps.executeUpdate();
            DAO.DataProvider.getIntance().close();
            //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getQuanLyChiTietHoaDon(maNV).getTenNV() + " đã thêm loại sản phẩm mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Thêm loại chi tiết hóa đơn " + TenNV + " Thất bại", "Thông báo", 1);
        }
    }
    
    public void updateQuanLyChiTietHoaDon(String MaHD, String MaSP, int SoLuong, float DonGia) {
        try {
            String query = "UPDATE chitiethoadon set SoLuong= ?, DonGia= ? WHERE MaHD= ? AND MaSP= ?";
            PreparedStatement ps = DAO.DataProvider.getIntance().getconn().prepareStatement(query);
            ps.setInt(1, SoLuong);
            ps.setFloat(2, DonGia);
            ps.setString(3, MaHD);
            ps.setString(4, MaSP);
            ps.executeUpdate();
            DAO.DataProvider.getIntance().close();
        } catch (SQLException ex) {
            Logger.getLogger(daoQuanLyChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteChiTietHoaDon(String MaHD, String MaSP) {
        
    }
    
    public void deleteAll() {
        
    }

}
