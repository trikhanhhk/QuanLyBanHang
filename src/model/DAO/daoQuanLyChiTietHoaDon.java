/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.ChiTietHoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author trikh
 */
public class daoQuanLyChiTietHoaDon {
    
    private static daoQuanLyChiTietHoaDon instance;

    public static daoQuanLyChiTietHoaDon getInstance() {
        if (instance == null) {
            instance = new daoQuanLyChiTietHoaDon();
        }
        return instance;
    }

    public daoQuanLyChiTietHoaDon() {
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
    
    public ChiTietHoaDon getChiTiet(String mahd, String masp) {
        ChiTietHoaDon result = null;
        String query = "select *from chitiethoadon where MaHD= '" + mahd + "' AND MaSP='" + masp + "'";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if(rs!=null && rs.next()) {
                //String mahd, String masp, int soluong, float dongia
                result = new ChiTietHoaDon(rs.getString("MaHD"),
                        rs.getString("MaSP"),
                        rs.getInt("SoLuong"),
                        rs.getFloat("DonGia"));
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }
        return result;
    }
    
     public ArrayList<ChiTietHoaDon> getAllChiTiet(String mahd) {
        ArrayList<ChiTietHoaDon> result = new ArrayList<>();
        String query = "select *from chitiethoadon where MaHD= '" + mahd + "'";
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
    
    public int insertQuanLyChiTietHoaDon(String MaHD, String MaSP, int SoLuong, float DonGia) {
        int a = -100;
        try {
            model.DAO.DataProvider.getIntance().open();
            PreparedStatement ps = model.DAO.DataProvider.getIntance().getconn().prepareStatement("INSERT INTO `chitiethoadon` (`MaHD`, `MaSP`, `SoLuong`, `DonGia`) VALUES (?,?,?,?)");
            ps.setString(1, MaHD);
            ps.setString(2, MaSP);
            ps.setInt(3, SoLuong);
            ps.setFloat(4, DonGia);
            a = ps.executeUpdate();
            System.out.println("Result " + a);
            model.DAO.DataProvider.getIntance().close();
            //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getQuanLyChiTietHoaDon(maNV).getTenNV() + " đã thêm loại sản phẩm mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception e) {
               System.err.println(e);
        }
        return a;
    }
    
    public void updateQuanLyChiTietHoaDon(String MaHD, String MaSP, int SoLuong, float DonGia) {
        try {
            String query = "UPDATE chitiethoadon set SoLuong= ?, DonGia= ? WHERE MaHD= ? AND MaSP= ?";
            PreparedStatement ps = model.DAO.DataProvider.getIntance().getconn().prepareStatement(query);
            ps.setInt(1, SoLuong);
            ps.setFloat(2, DonGia);
            ps.setString(3, MaHD);
            ps.setString(4, MaSP);
            ps.executeUpdate();
            model.DAO.DataProvider.getIntance().close();
        } catch (SQLException ex) {
            Logger.getLogger(daoQuanLyChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteChiTietHoaDon(String MaHD, String MaSP) {
        
    }
    
    public void deleteAll() {
        
    }

}
