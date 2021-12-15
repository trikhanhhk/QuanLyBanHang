/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import model.DTO.HoaDon;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author trikh
 */
public class daoQuanLyHoaDon {
    private ArrayList<HoaDon> dshd;
    private static daoQuanLyHoaDon instance;
    private ArrayList<HoaDon> listHD;
    public static daoQuanLyHoaDon getInstance() {
        if (instance == null) {
            instance = new daoQuanLyHoaDon();
        }
        return instance;
    }

    public daoQuanLyHoaDon() {
        this.listHD = this.getListHoaDon();
    }

    //Lấy ra danh sách thông tin từ bảng hóa đơn
    public ArrayList<HoaDon> getListHoaDon() {
        ArrayList<HoaDon> result = new ArrayList<>();
        String query = "select * from hoadon";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
//          String maHoaDon, String maNhanVien, String maKhachHang, String maKhuyenMai, LocalDate ngayNhap, LocalTime gioNhap, float tongTien
//`MaHD`, `MaNV`, `MaKH`, `MaKM`, `NgayLap`, `GioLap`, `TongTien`
                result.add(new HoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getString("MaKM"), 
                        rs.getDate("NgayLap").toLocalDate(), 
                        LocalTime.parse(rs.getString("GioLap")),
                        rs.getFloat("TongTien")
                ));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }
    
    public Boolean updateTongTien(String _mahd,float _tongTien){
        String query = "UPDATE hoadon SET TongTien='" + _tongTien + "' WHERE MaHD='" +_mahd + "';";
        ConnectDB.getIntance().open();
        int result = ConnectDB.getIntance().excuteQueryUpdate(query);
        ConnectDB.getIntance().close();
        return result > 0;
    }

    //Tìm kiếm trong bảng hóa đơn
    public ArrayList<HoaDon> FindListHoaDon(String ValToSearch) {
        ArrayList<HoaDon> hoadonList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `hoadon` WHERE CONCAT(`MaSP`, `TenSP`, `MaLSP`) LIKE '%" + ValToSearch + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            HoaDon hoadon;

            while (rs.next()) {
                hoadon = new HoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getString("MaKM"), 
                        rs.getDate("NgayLap").toLocalDate(), 
                        LocalTime.parse(rs.getDate("GioLap").toString()),
                        rs.getFloat("TongTien")
                );
                hoadonList.add(hoadon);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return hoadonList;
    }
    
     public ArrayList<HoaDon> FindListHoaDon(ArrayList<HoaDon> DuLieuMau, String ValToSearch) {
        ArrayList<HoaDon> result = new ArrayList<>();
        for (int i = 0; i < DuLieuMau.size(); i++) {
            if (DuLieuMau.get(i).getMaHoaDon().contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getMaKhachHang()).contains(ValToSearch)
                    || DuLieuMau.get(i).getMaKhuyenMai().contains(ValToSearch)
                    || DuLieuMau.get(i).getMaNhanVien().toString().contains(ValToSearch)) {
                result.add(DuLieuMau.get(i));
            }
        }
        return result;
    }

    //Lấy ra danh sách thông tin hóa đơn có cùng loại
    public ArrayList<HoaDon> getListHoaDonTheoLoai(int MaLSP) {
        ArrayList<HoaDon> hoadonList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `hoadon` WHERE MaLSP ='%" + MaLSP + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            HoaDon hoadon;

            while (rs.next()) {
                hoadon = new HoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getString("MaKM"), 
                        rs.getDate("NgayLap").toLocalDate(), 
                        LocalTime.parse(rs.getDate("GioLap").toString()),
                        rs.getFloat("TongTien")
                );
                hoadonList.add(hoadon);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return hoadonList;
    }

    //Lấy danh sách thông tin hóa đơn từ số có tồn tại trong id hóa đơn
    public ArrayList<HoaDon> getListHoaDonTheoID(String MaSP) {
        ArrayList<HoaDon> hoadonList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `hoadon` WHERE MaSP ='%" + MaSP + "%'";
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(searchQuery, arr);
            HoaDon hoadon;

            while (rs.next()) {
                hoadon = new HoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getString("MaKM"), 
                        rs.getDate("NgayLap").toLocalDate(), 
                        LocalTime.parse(rs.getDate("GioLap").toString()),
                        rs.getFloat("TongTien")
                );
                hoadonList.add(hoadon);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return hoadonList;
    }
    
    public String getNextID() {
        return "HD" + String.valueOf(getListHoaDon().size() + 1);
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

    //Thêm hóa đơn mới
    public boolean insertHoaDon(String maHoaDon, String maNhanVien, String maKhachHang, String maKhuyenMai, LocalDate ngayNhap, LocalTime gioNhap, float tongTien) {
        try {
            model.DAO.ConnectDB.getIntance().open();
            //INSERT INTO `hoadon` (`MaSP`, `MaLSP`, `TenSP`, `DonGia`, `SoLuong`, `HinhAnh`, `TrangThai`) VALUES
            PreparedStatement ps = model.DAO.ConnectDB.getIntance().getconn().prepareStatement("INSERT INTO `hoadon`( `MaHD`, `MaNV`, `MaKH`, `MaKM`, `NgayLap`, `GioLap`, `TongTien`) VALUES (?,?,?,?,?,?,?)");
            ps.setString(1, maHoaDon);
            ps.setString(2, maNhanVien);
            ps.setString(3, maKhachHang);
            ps.setString(4, maKhuyenMai);
            ps.setString(5, ngayNhap.toString());
            ps.setString(6, gioNhap.toString());
            ps.setFloat(7, tongTien);
            ps.executeUpdate();
            model.DAO.ConnectDB.getIntance().close();
            JOptionPane.showMessageDialog(null,
                    "Thêm hóa đơn mới thành công.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

            //DAO.daoThongBao.getInstance().insertThongBao("[Sản Phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(id_nv).ten_nv + " đã thêm hóa đơn mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //Sửa thông tin hóa đơn
    public boolean updateHoaDon(String maHoaDon, String maNhanVien, String maKhachHang, String maKhuyenMai, LocalDate ngayNhap, LocalTime gioNhap, float tongTien) {
        String query = "UPDATE hoadon SET "
                + "MaNV='" + maNhanVien 
                + "', MaKH='" + maKhachHang
                + "', MaKM='" + maKhuyenMai
                + "', NgayLap='" + ngayNhap 
                + "', GioLap='" + gioNhap
                + "', TongTien='" + tongTien
                + "' WHERE MaHD='" + maHoaDon + "';";
      
        ConnectDB.getIntance().open();
        int result = ConnectDB.getIntance().excuteQueryUpdate(query);
        ConnectDB.getIntance().close();
        return result > 0;
    }

    //Lấy 1 hóa đơn từ id hóa đơn
    public HoaDon getHoaDon(String MaHD) {
        HoaDon result = null;
        String query = "select * from hoadon where MaHD='" + MaHD + "'";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                result = new HoaDon(
                        rs.getString("MaHD"),
                        rs.getString("MaNV"),
                        rs.getString("MaKH"),
                        rs.getString("MaKM"), 
                        rs.getDate("NgayLap").toLocalDate(), 
                        LocalTime.parse(rs.getString("GioLap")),
                        rs.getFloat("TongTien")
                );
            }
            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }
        if (result == null) {
            System.out.print("hoa don bi null");
        }
        return result;
    }
    
      //Lấy danh sách 20 nhân viên, để làm phân trang

    public ArrayList<HoaDon> get20HoaDon(ArrayList<HoaDon> arr, long Trang) {
        ArrayList<HoaDon> result = new ArrayList<>();
        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }
    
}
