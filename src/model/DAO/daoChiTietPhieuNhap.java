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
import model.DTO.SanPham;

/**
 *
 * @author trikh
 */
public class daoChiTietPhieuNhap {

    private static daoChiTietPhieuNhap instance;
    private ArrayList<ChiTietPhieuNhap> dsctpn;

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
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                String ma = rs.getString(1);
                String maSP = rs.getString(2);
                Integer soLuong = rs.getInt(3);
                Float donGia = rs.getFloat(4);

                ChiTietPhieuNhap ctpn = new ChiTietPhieuNhap(ma, maSP, soLuong, donGia);
                result.add(ctpn);
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }

    //Thêm chi tiết phiếu nhập mới
    public int insertChiTietPhieuNhap(String MaPN, String MaSP, int SoLuong, float DonGia) {
        int a = -100;
        try {
            model.DAO.ConnectDB.getIntance().open();
            PreparedStatement ps = model.DAO.ConnectDB.getIntance().getconn().prepareStatement("INSERT INTO `chitietphieunhap` (`MaPN`, `MaSP`, `SoLuong`, `DonGia`) VALUES (?,?,?,?)");
            SanPham sp = daoSanPham.getInstance().getSanPham(MaSP);
            daoSanPham.getInstance().updateSanPham(MaSP, sp.getMaLSP(), sp.getTenSP(), sp.getDonGia(), SoLuong + sp.getSoLuong(), sp.getFileNameHinhAnh(), sp.getTrangThai()); //update lại số lượng trong db
            ps.setString(1, MaPN);
            ps.setString(2, MaSP);
            ps.setInt(3, SoLuong);
            ps.setFloat(4, DonGia);
            a = ps.executeUpdate();
            System.out.println("Result " + a);
            model.DAO.ConnectDB.getIntance().close();
        } catch (Exception e) {
            System.err.println(e);
        }
        return a;
    }

    public void updateQuanLyChiTietHoaDon(String MaPN, String MaSP, int SoLuong, float DonGia) {
        try {
            String query = "UPDATE chitietphieunhap set SoLuong= ?, DonGia= ? WHERE MaPN= ? AND MaSP= ?";
            PreparedStatement ps = model.DAO.ConnectDB.getIntance().getconn().prepareStatement(query);
            ps.setInt(1, SoLuong);
            ps.setFloat(2, DonGia);
            ps.setString(3, MaPN);
            ps.setString(4, MaSP);
            ps.executeUpdate();
            model.DAO.ConnectDB.getIntance().close();
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
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
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
            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }
        return result;
    }

    public ArrayList<ChiTietPhieuNhap> getAllChiTiet(String mapn) {
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<>();
        dsctpn = daoChiTietPhieuNhap.getInstance().getListChiTietPhieuNhap();
        for (ChiTietPhieuNhap ctpn : dsctpn) {
            if (ctpn.getMa().equals(mapn)) {
                result.add(ctpn);
            }
        }
        return result;
    }

    public ArrayList<ChiTietPhieuNhap> search(String type, String value) {
        dsctpn = daoChiTietPhieuNhap.getInstance().getListChiTietPhieuNhap();
        ArrayList<ChiTietPhieuNhap> result = new ArrayList<>();
        dsctpn.forEach((ctpn) -> {
            if (type.equals("Tất cả")) {
                if (ctpn.getMa().toLowerCase().contains(value.toLowerCase())
                        || ctpn.getMaSP().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(ctpn.getDonGia()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(ctpn.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(ctpn);
                }
            } else {
                switch (type) {
                    case "Mã phiếu nhập":
                        if (ctpn.getMa().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                    case "Mã sản phẩm":
                        if (ctpn.getMaSP().toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                    case "Đơn giá":
                        if (String.valueOf(ctpn.getDonGia()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                    case "Số lượng":
                        if (String.valueOf(ctpn.getSoLuong()).toLowerCase().contains(value.toLowerCase())) {
                            result.add(ctpn);
                        }
                        break;
                }
            }

        });

        return result;
    }
}
