/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.PhieuNhap;
import GROUP.ThongTinNhap;
import DTO.XuatKho;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.lang.*;
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
}
