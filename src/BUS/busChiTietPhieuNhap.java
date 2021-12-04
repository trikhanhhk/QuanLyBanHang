/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

import DAO.daoChiTietPhieuNhap;
import DAO.daoQuanLyHoaDon;
import DTO.ChiTietPhieuNhap;
import DTO.HoaDon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author trikh
 */
public class busChiTietPhieuNhap {

    private static busChiTietPhieuNhap instance;
    private ArrayList<ChiTietPhieuNhap> dsctpn;

    public static busChiTietPhieuNhap getInstance() {
        if (instance == null) {
            instance = new busChiTietPhieuNhap();
        }
        return instance;
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
