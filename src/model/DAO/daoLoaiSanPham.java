/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;
import model.DTO.LoaiSanPham;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.lang.*;

/**
 *
 * @author HoangAnh
 */
public class daoLoaiSanPham {

    private static daoLoaiSanPham instance;

    public static daoLoaiSanPham getInstance() {
        if (instance == null) {
            instance = new daoLoaiSanPham();
        }
        return instance;
    }

    public daoLoaiSanPham() {
    }

    //Lấy ra danh sách thông tin từ bảng loại sản phẩm
    public ArrayList<LoaiSanPham> getListLoaiSanPham() {
        ArrayList<LoaiSanPham> result = new ArrayList<>();
        String query = "select * from loaisanpham";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //(String MaLSP, String TenLSP, String MoTa
                result.add(new LoaiSanPham(
                        rs.getString("MaLSP"),
                        rs.getString("TenLSP"),
                        rs.getString("MoTa")));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }

    //Lấy ra 1 loại sản phẩm từ id
    public LoaiSanPham getLoaiSanPham(String MaLSP) {
        LoaiSanPham result = null;
        String query = "select *from loaisanpham where MaLSP = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(MaLSP);
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = new LoaiSanPham(
                        rs.getString("MaLSP"),
                        rs.getString("TenLSP"),
                        rs.getString("MoTa"));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }

    //Lấy ra loại sản phẩm từ tên loại sản phẩm
    public LoaiSanPham getIDLoaiSanPham(String TenLSP) {
        LoaiSanPham result = null;
        String query = "select *from loaisanpham where TenLSP = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(TenLSP);
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = new LoaiSanPham(
                        rs.getString("MaLSP"),
                        rs.getString("TenLSP"),
                        rs.getString("MoTa"));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }

    //Thêm loại sản phẩm mới
    public void ThemLoaiSanPham(String TenLSP, String MaLSP, String Mota, String maNV) {
        String query = "INSERT INTO `loaisanpham`(`MaLSP`, `TenLSP`, `Mota`) VALUES ('" + MaLSP + "','" + TenLSP + "','" + Mota + "')";
        System.out.println(query);
        try {
            ConnectDB.getIntance().open();
            ConnectDB.getIntance().excuteQuery(query);
            ConnectDB.getIntance().close();
            JOptionPane.showMessageDialog(null, "Đã thêm loại sản phẩm " + TenLSP + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(maNV).getTenNV() + " đã thêm loại sản phẩm mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm " + TenLSP + " Thất bại", "Thông báo", 1);
        }
    }
    //Sửa thông tin loại sản phẩm

    public boolean UpdateLoaiSanPham(
            String TenLSP,
            String MaLSP,
            String Mota) {
        if ("".equals(TenLSP) || "".equals(Mota)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "Update loaisanpham Set TenLSP='" + TenLSP + "', MoTa='" + Mota + "' where MaLSP='" + MaLSP + "'";
        System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().open();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin loại sản phẩm thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(maNV).getTenNV() + " đã sửa thông tin loại sản phẩm vào lúc" + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        return true;
    }
    
    //xóa loại sản phẩm
    public void DeleteLoaiSanPham(String MaLSP) {
        String query = "DELETE FROM `loaisanpham` WHERE `loaisanpham`.`MaLSP` = '" + MaLSP + "'";
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().open();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Xóa sản phẩm " + MaLSP + "Thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        
    }
    
    //Tìm kiếm trong bảng trả kho
    public ArrayList<LoaiSanPham> FindListLoaiSanPham(ArrayList<LoaiSanPham> DuLieuMau, String value) {
        ArrayList<LoaiSanPham> result = new ArrayList<>();

        DuLieuMau.forEach((lsp) -> {
                if (lsp.getMaLSP().toLowerCase().contains(value.toLowerCase())
                        || lsp.getTenLSP().toLowerCase().contains(value.toLowerCase())
                        || lsp.getMoTa().toLowerCase().contains(value.toLowerCase()))  {
                    result.add(lsp);
                }

        });

        return result;
    }
    
    //Lấy ra 20 phiếu tra, để phân trang
    public ArrayList<LoaiSanPham> get20LoaiSanPham(ArrayList<LoaiSanPham> arr, long Trang) {
        ArrayList<LoaiSanPham> result = new ArrayList<>();
        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }
}
