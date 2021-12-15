/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.ChiTietPhieuNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.*;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author admin
 */
public class daoChiTietPhieuNhap {

    private static daoChiTietPhieuNhap instance;

    public static daoChiTietPhieuNhap getInstance() {
        if (instance == null) {
            instance = new daoChiTietPhieuNhap();
        }
        return instance;
    }

    public daoChiTietPhieuNhap() {
    }

    //Lấy danh sách thông tin trong bảng chi tiết phiếu nhập
    public ArrayList<ChiTietPhieuNhap> getListChiTietPhieuNhap() {
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<>();
        String query = "SELECT * FROM `chitietphieunhap`";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                String ma = rs.getString(1);
                    String maSP = rs.getString(2);
                    Integer soLuong = rs.getInt(3);
                    Float donGia = rs.getFloat(4);

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, maSP, soLuong, donGia);
                    result.add(ctpn);
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }

    //Thêm chi tiết phiếu nhập mới
        public int insertChiTietPhieuNhap(String MaPN, String MaSP, int SoLuong, float DonGia) {
        int a = -100;
        try {
            model.DAO.DataProvider.getIntance().open();
            PreparedStatement ps = model.DAO.DataProvider.getIntance().getconn().prepareStatement("INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`) VALUES (?,?,?,?)");
            ps.setString(1, MaPN);
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
    
    public void updateQuanLyChiTietHoaDon(String MaPN, String MaSP, int SoLuong, float DonGia) {
        try {
            String query = "UPDATE chitietphieunhap set SoLuong= ?, DonGia= ? WHERE MaPN= ? AND MaSP= ?";
            PreparedStatement ps = model.DAO.DataProvider.getIntance().getconn().prepareStatement(query);
            ps.setInt(1, SoLuong);
            ps.setFloat(2, DonGia);
            ps.setString(3, MaPN);
            ps.setString(4, MaSP);
            ps.executeUpdate();
            model.DAO.DataProvider.getIntance().close();
        } catch (SQLException ex) {
            Logger.getLogger(daoQuanLyChiTietHoaDon.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    // Lấy một chi tiết phiếu nhập từ id
    public ChiTietPhieuNhap getChiTietPhieuNhap(String maPn, String maSp) {
        ChiTietPhieuNhap result = null;
        String query = "SELECT * FROM `chitietphieunhap` WHERE MaPN='" + maPn + "' AND MaSP='" + maSp + "'";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if (rs.next()) {

                 String ma = rs.getString("MaPN");
                    String maSP = rs.getString("MaSP");
                    Integer soLuong = rs.getInt("SoLuong");
                    Float donGia = rs.getFloat("DonGia");

                    ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, maSP, soLuong, donGia);
                    result = ctpn;

            } else {
                result = null;
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }
        return result;
    }
}
