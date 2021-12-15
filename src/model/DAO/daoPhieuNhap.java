/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.PhieuNhap;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.*;
import java.sql.PreparedStatement;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.swing.JOptionPane;

/**
 *
 * @author HoangAnh
 */
public class daoPhieuNhap {

    private static daoPhieuNhap instance;

    public static daoPhieuNhap getInstance() {
        if (instance == null) {
            instance = new daoPhieuNhap();
        }
        return instance;
    }

    //Lấy ra danh sách thông tin phiếu nhập
    public ArrayList<PhieuNhap> getListPhieuNhap() {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        String query = "select *from phieunhap";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                PhieuNhap pn = new PhieuNhap();
                pn.setMaPN(rs.getString(1));
                pn.setMaNCC(rs.getString(2));
                pn.setMaNV(rs.getString(3));
                pn.setNgayNhap(rs.getDate(4).toLocalDate());
                pn.setGioNhap(rs.getTime(5).toLocalTime());
                pn.setTongTien(rs.getFloat(6));
                result.add(pn);
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }
    
    public String getNextID() {
        return "PN" + (this.getListPhieuNhap().size() + 1);
    }

    public void update(PhieuNhap pn) {
        String query = "UPDATE phieunhap SET "
                + "MaNCC='" + pn.getMaNCC()
                + "', MaNV='" + pn.getMaNV()
                + "', NgayNhap='" + pn.getNgayNhap()
                + "', GioNhap='" + pn.getGioNhap()
                + "', TongTien='" + pn.getTongTien()
                + "' WHERE MaPN='" + pn.getMaPN() + "';";
        DataProvider.getIntance().open();
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
    }

    public void updateTongTien(String _mapn, float _tongTien) {
        String query = "UPDATE phieunhap SET TongTien='" + _tongTien + "' WHERE MaPN='" + _mapn + "';";
        DataProvider.getIntance().open();
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
    }

    public boolean insertPN(String maPN, String MaNCC, String MaNV, String NgayNhap, String GioNhap, float TongTien) {
        if ("".equals(maPN) || "".equals(MaNCC) || "".equals(MaNV) || "".equals(NgayNhap) || "".equals(GioNhap) || "".equals(TongTien)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            model.DAO.DataProvider.getIntance().open();
            PreparedStatement ps = model.DAO.DataProvider.getIntance().getconn().prepareStatement("INSERT INTO phieunhap(MaPN,MaNCC,MaNV,NgayNhap,GioNhap,TongTien) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, maPN);
            ps.setString(2, MaNCC);
            ps.setString(3, MaNV);
            ps.setString(4, NgayNhap);
            ps.setString(5, GioNhap);
            ps.setFloat(6, TongTien);
            ps.executeUpdate();
            model.DAO.DataProvider.getIntance().close();
            JOptionPane.showMessageDialog(null,
                    "Thêm phiếu nhập mới thành công.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }
    
          //Lấy danh sách 20 nhân viên, để làm phân trang

    public ArrayList<PhieuNhap> get20PhieuNhap(ArrayList<PhieuNhap> arr, long Trang) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }
    
    public ArrayList<PhieuNhap> FindListPhieuNhap(ArrayList<PhieuNhap> DuLieuMau, String ValToSearch) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        for (int i = 0; i < DuLieuMau.size(); i++) {
            if (daoNhaCungCap.getInstance().getNhaCungCap(DuLieuMau.get(i).getMaNCC()).getTenNCC().contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getGioNhap()).contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getNgayNhap()).contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getTongTien()).contains(ValToSearch)
                    || daoNhanVien.getInstance().getNameNVByID(DuLieuMau.get(i).getMaNV()).contains(ValToSearch)
                    || DuLieuMau.get(i).getMaPN().contains(ValToSearch)) {
                result.add(DuLieuMau.get(i));
            }
        }
        return result;
    }
}
