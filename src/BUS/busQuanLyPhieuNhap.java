/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;
import DAO.daoPhieuNhap;
import DTO.PhieuNhap;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 *
 * @author trikh
 */
public class busQuanLyPhieuNhap {

    private static busQuanLyPhieuNhap instance;
    private ArrayList<PhieuNhap> dspn;

    public static busQuanLyPhieuNhap getInstance() {
        if (instance == null) {
            instance = new busQuanLyPhieuNhap();
        }
        return instance;
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
