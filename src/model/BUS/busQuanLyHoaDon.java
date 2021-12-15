/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BUS;

import model.DAO.daoQuanLyHoaDon;
import model.DTO.HoaDon;
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
public class busQuanLyHoaDon {
    
    private static busQuanLyHoaDon instance;
    private ArrayList<HoaDon> dshd;

    public static busQuanLyHoaDon getInstance() {
        if (instance == null) {
            instance = new busQuanLyHoaDon();
        }
        return instance;
    }
    
    public String getNextID() {
        return model.DAO.daoQuanLyHoaDon.getInstance().getNextID();
    }
    
    public ArrayList<HoaDon> searchNew(String keyword, LocalDate _ngay1, LocalDate _ngay2, int _tong1, int _tong2) {
        ArrayList<HoaDon> result = new ArrayList<>();
        dshd = daoQuanLyHoaDon.getInstance().getListHoaDon();
        dshd.forEach((hd) -> {
            if (hd.getMaHoaDon().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getMaNhanVien().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getMaKhachHang().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getMaKhachHang().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getNgayLap().toString().toLowerCase().contains(keyword.toLowerCase())
                            || hd.getGioLap().toString().toLowerCase().contains(keyword.toLowerCase())
                            || String.valueOf(hd.getTongTien()).toLowerCase().contains(keyword.toLowerCase())) {
                        result.add(hd);
                    }
        });
        for (int i = result.size() - 1; i >= 0; i--) {
            HoaDon hd = result.get(i);
            LocalDate ngaylap = hd.getNgayLap();
            float tongtien = hd.getTongTien();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaylap.isBefore(_ngay1)) || (_ngay2 != null && ngaylap.isAfter(_ngay2));
            Boolean tienKhongThoa = (_tong1 != -1 && tongtien < _tong1) || (_tong2 != -1 && tongtien > _tong2);

            if (ngayKhongThoa || tienKhongThoa) {
                result.remove(hd);
            }
        }
        return result;
    }
    
    public ArrayList<HoaDon> search(LocalDate _ngay1, LocalDate _ngay2, int _tong1, int _tong2) {
         ArrayList<HoaDon> result = daoQuanLyHoaDon.getInstance().getListHoaDon();
         for (int i = result.size() - 1; i >= 0; i--) {
            HoaDon hd = result.get(i);
            LocalDate ngaylap = hd.getNgayLap();
            float tongtien = hd.getTongTien();

            Boolean ngayKhongThoa = (_ngay1 != null && ngaylap.isBefore(_ngay1)) || (_ngay2 != null && ngaylap.isAfter(_ngay2));
            Boolean tienKhongThoa = (_tong1 != -1 && tongtien < _tong1) || (_tong2 != -1 && tongtien > _tong2);

            if (ngayKhongThoa || tienKhongThoa) {
                result.remove(hd);
            }
        }
         return result;
     }
     
     public boolean XuatExcel(ArrayList<HoaDon> DuLieuMau) throws IOException {
        //         TODO add your handling code here:
                HSSFWorkbook workbook = new HSSFWorkbook();
                HSSFSheet sheet = workbook.createSheet("Employees sheet");
                int rownum = 0;
                Cell cell;
                Row row;
        
                row = sheet.createRow(rownum);
                cell = row.createCell(0);
                cell.setCellValue("Mã hóa đơn");
        
                cell = row.createCell(1);
                cell.setCellValue("Mã nhân viên");
        
                cell = row.createCell(2);
                cell.setCellValue("Mã khách hàng");
        
                cell = row.createCell(3);
                cell.setCellValue("Mã khuyến mãi");
        
                cell = row.createCell(4);
                cell.setCellValue("Ngày lập");
                
                cell = row.createCell(5);
                cell.setCellValue("Giờ lập");
                
                cell = row.createCell(6);
                cell.setCellValue("Tổng tiền");
        
                for (int i = 0; i < DuLieuMau.size(); i++) {
                        rownum++;
                        row = sheet.createRow(rownum);
                        //
                        cell = row.createCell(0);
                        cell.setCellValue(DuLieuMau.get(i).getMaHoaDon());
                        //
                        cell = row.createCell(1);
                        cell.setCellValue(DuLieuMau.get(i).getMaKhachHang());
                        //
                        cell = row.createCell(2);
                        cell.setCellValue(DuLieuMau.get(i).getMaNhanVien());
                        //
                        cell = row.createCell(3);
                        cell.setCellValue(DuLieuMau.get(i).getMaKhuyenMai());
                        //
                        cell = row.createCell(4);
                        cell.setCellValue(DuLieuMau.get(i).getNgayLap().toString());
                        //
                        cell = row.createCell(5);
                        cell.setCellValue(DuLieuMau.get(i).getGioLap().toString());
                        //
                        cell = row.createCell(6);
                        cell.setCellValue(DuLieuMau.get(i).getTongTien());
            
                    }
                File file = new File("C:/demo/HoaDon.xls");
                file.getParentFile().mkdirs();
        
                FileOutputStream outFile;
                try {
                        outFile = new FileOutputStream(file);
                        workbook.write(outFile);
                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                        return false;
                    } catch (IOException ex) {
                        return false;
                    }

                return  true;
    }
    
}
