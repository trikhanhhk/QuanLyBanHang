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
    private ArrayList<PhieuNhap> dspn;

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
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
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

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
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
        ConnectDB.getIntance().open();
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
    }

    public void updateTongTien(String _mapn, float _tongTien) {
        String query = "UPDATE phieunhap SET TongTien='" + _tongTien + "' WHERE MaPN='" + _mapn + "';";
        ConnectDB.getIntance().open();
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
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
            model.DAO.ConnectDB.getIntance().open();
            PreparedStatement ps = model.DAO.ConnectDB.getIntance().getconn().prepareStatement("INSERT INTO phieunhap(MaPN,MaNCC,MaNV,NgayNhap,GioNhap,TongTien) VALUES (?, ?, ?, ?, ?, ?)");
            ps.setString(1, maPN);
            ps.setString(2, MaNCC);
            ps.setString(3, MaNV);
            ps.setString(4, NgayNhap);
            ps.setString(5, GioNhap);
            ps.setFloat(6, TongTien);
            ps.executeUpdate();
            model.DAO.ConnectDB.getIntance().close();
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
    
    public ArrayList<PhieuNhap> search(String value, LocalDate _ngay1, LocalDate _ngay2, int _tong1, int _tong2) {
        ArrayList<PhieuNhap> result = new ArrayList<>();
        dspn = daoPhieuNhap.getInstance().getListPhieuNhap();
        dspn.forEach((pn) -> {
            if (pn.getMaPN().toLowerCase().contains(value.toLowerCase())
                        || pn.getMaNCC().toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(pn.getMaNV()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(pn.getNgayNhap()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(pn.getGioNhap()).toLowerCase().contains(value.toLowerCase())
                        || String.valueOf(pn.getTongTien()).toLowerCase().contains(value.toLowerCase())) {
                    result.add(pn);
                }
        });
        //Ngay lap, tong tien
        for (int i = result.size() - 1; i >= 0; i--) {
            PhieuNhap pn = result.get(i);
            LocalDate ngaynhap = pn.getNgayNhap();
            float tongtien = pn.getTongTien();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaynhap.isBefore(_ngay1)) || (_ngay2 != null && ngaynhap.isAfter(_ngay2));
            Boolean tienKhongThoa = (_tong1 != -1 && tongtien < _tong1) || (_tong2 != -1 && tongtien > _tong2);

            if (ngayKhongThoa || tienKhongThoa) {
                result.remove(pn);
            }
        }

        return result;
    }
}
