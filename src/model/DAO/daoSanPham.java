/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.SanPham;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.*;

/**
 *
 * @author HoangAnh
 */
public class daoSanPham {

    private static daoSanPham instance;
    private ArrayList<SanPham> listSP;
    public static daoSanPham getInstance() {
        if (instance == null) {
            instance = new daoSanPham();
        }
        return instance;
    }

    public daoSanPham() {
        this.listSP = this.getListSanPham();
    }

    //Lấy ra danh sách thông tin từ bảng sản phẩm
    public ArrayList<SanPham> getListSanPham() {
        ArrayList<SanPham> result = new ArrayList<>();
        String query = "select * from sanpham";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
//                String MaSP, String MaLSP, String TenSP, float DonGia, int SoLuong, String url, int TrangThai
                result.add(new SanPham(
                        rs.getString("MaSP"),
                        rs.getString("MaLSP"),
                        rs.getString("TenSP"),
                        rs.getFloat("DonGia"), 
                        rs.getInt("SoLuong"), 
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThai")
                ));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }

    //Tìm kiếm trong bảng sản phẩm
    public ArrayList<SanPham> FindListSanPham(String ValToSearch) {
        ArrayList<SanPham> sanphamList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `sanpham` WHERE CONCAT(`MaSP`, `TenSP`, `MaLSP`) LIKE '%" + ValToSearch + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            SanPham sanpham;

            while (rs.next()) {
                sanpham = new SanPham(
                        rs.getString("MaSP"),
                        rs.getString("MaLSP"),
                        rs.getString("TenSP"),
                        rs.getFloat("DonGia"), 
                        rs.getInt("SoLuong"), 
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThai")
                );
                sanphamList.add(sanpham);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return sanphamList;
    }

    //Lấy ra danh sách thông tin sản phẩm có cùng loại
    public ArrayList<SanPham> getListSanPhamTheoLoai(int MaLSP) {
        ArrayList<SanPham> sanphamList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `sanpham` WHERE MaLSP ='%" + MaLSP + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            SanPham sanpham;

            while (rs.next()) {
                sanpham = new SanPham(
                        rs.getString("MaSP"),
                        rs.getString("MaLSP"),
                        rs.getString("TenSP"),
                        rs.getFloat("DonGia"), 
                        rs.getInt("SoLuong"), 
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThai")
                );
                sanphamList.add(sanpham);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return sanphamList;
    }

    //Lấy danh sách thông tin sản phẩm từ số có tồn tại trong id sản phẩm
    public ArrayList<SanPham> getListSanPhamTheoID(String MaSP) {
        ArrayList<SanPham> sanphamList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `sanpham` WHERE MaSP ='%" + MaSP + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            SanPham sanpham;

            while (rs.next()) {
                sanpham = new SanPham(
                        rs.getString("MaSP"),
                        rs.getString("MaLSP"),
                        rs.getString("TenSP"),
                        rs.getFloat("DonGia"), 
                        rs.getInt("SoLuong"), 
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThai")
                );
                sanphamList.add(sanpham);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return sanphamList;
    }
    
    public String getNextID() {
        return "SP" + String.valueOf(this.listSP.size() + 1);
    }

    //Thêm sản phẩm mới
    public boolean insertSanPham(String MaSP, String MaLSP, String TenSP, float DonGia, int SoLuong, String HinhAnh, int TrangThai) {
        try {
            model.DAO.ConnectDB.getIntance().open();
            //INSERT INTO `sanpham` (`MaSP`, `MaLSP`, `TenSP`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`) VALUES
            PreparedStatement ps = model.DAO.ConnectDB.getIntance().getconn().prepareStatement("INSERT INTO `sanpham`( `MaSP`, `MaLSP`, `TenSP`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, MaSP);
            ps.setString(2, MaLSP);
            ps.setString(3, TenSP);
            ps.setFloat(4, DonGia);
            ps.setInt(5, SoLuong);
            ps.setString(6, HinhAnh);
            ps.setInt(7, TrangThai);
            ps.executeUpdate();
            model.DAO.ConnectDB.getIntance().close();
            JOptionPane.showMessageDialog(null,
                    "Thêm sản phẩm mới thành công.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            //DAO.daoThongBao.getInstance().insertThongBao("[Sản Phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(id_nv).ten_nv + " đã thêm sản phẩm mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //Sửa thông tin sản phẩm
    public boolean updateSanPham(int MaSP, String TenSP, byte[] hinh_anh, int id_exist, int MaLSP) {
        String query = "Call USP_updateNhanVien(?,?,?,?,?,?,?)";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(MaSP);
        arr.add(TenSP);
        arr.add(hinh_anh);
        arr.add(id_exist);
        arr.add(MaLSP);
        ConnectDB.getIntance().open();
        int result = ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        return result > 0;
    }

    //Lấy 1 sản phẩm từ id sản phẩm
    public SanPham getSanPham(String MaSP) {
        SanPham result = null;
        String query = "select * from sanpham where MaSP='" + MaSP + "'";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                result = new SanPham(
                        rs.getString("MaSP"),
                        rs.getString("MaLSP"),
                        rs.getString("TenSP"),
                        rs.getFloat("DonGia"), 
                        rs.getInt("SoLuong"), 
                        rs.getString("HinhAnh"),
                        rs.getInt("TrangThai")
                );
            }
            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }
        if (result == null) {
            System.out.print("san pham bi null");
        }
        return result;
    }
}
