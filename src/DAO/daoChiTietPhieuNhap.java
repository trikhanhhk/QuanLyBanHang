/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.ChiTietPhieuNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.*;

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
    public boolean insertChiTietPhieuNhap(int so_tien_lo, int so_luong_lo, int id_nguon_cc, int id_phieu_nhap) {
        String query = "INSERT INTO `chitietphieunhap`(`MaPN`, `MaSP`, `SoLuong`, `DonGia`) VALUES ('" + so_tien_lo + "','" + so_luong_lo + "','" + id_nguon_cc + "','" + id_phieu_nhap + "')";
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().open();
        int result = DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
        return result > 0;
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
