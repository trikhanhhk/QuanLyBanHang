/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.DAO;

import model.DTO.KhuyenMai;
import controller_view.GUI.fKhuyenMai;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author tatho
 */
public class daoKhuyenMai {

    private static daoKhuyenMai instance;
    private ArrayList<KhuyenMai> listKM;

    public static daoKhuyenMai getInstance() {
        if (instance == null) {
            instance = new daoKhuyenMai();
        }
        return instance;
    }

    public daoKhuyenMai() {
        this.listKM = this.getListKhuyenMai();
    }

    public String getNextID() {
        return "KM" + String.valueOf(this.listKM.size() + 1);
    }

    //Lấy danh sách thông tin từ bảng khuyến mãi
    public ArrayList<KhuyenMai> getListKhuyenMai() {
        ArrayList<KhuyenMai> result = new ArrayList<>();
        String query = "select *from khuyenmai";
        ArrayList<Object> arr = new ArrayList<>();
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            while (rs.next()) {
                //String makm, String tenkm, float dkkm, float phantramkm, LocalDate ngaybd, LocalDate ngaykt
                result.add(new KhuyenMai(rs.getString("MaKM"),
                        rs.getString("TenKM"),
                        rs.getFloat("DieuKienKM"),
                        rs.getFloat("PhanTramKM"),
                        rs.getDate("NgayBD").toLocalDate(),
                        rs.getDate("NgayKT").toLocalDate()));
            }

            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return result;
    }

    //Thêm nguồn cung cấp mới
    public boolean insertKhuyenMai(String makm, String tenkm, float dkkm, float phantramkm, String ngaybd, String ngaykt, String maNV) {
        if ("".equals(tenkm) || "".equals(dkkm) || "".equals(phantramkm) || "".equals(ngaybd) || "".equals(ngaykt)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        try {
            model.DAO.ConnectDB.getIntance().open();
            PreparedStatement ps = model.DAO.ConnectDB.getIntance().getconn().prepareStatement("INSERT INTO `khuyenmai`(`MaKM`, `TenKM`, `DieuKienKM`, `PhanTramKM`, `NgayBD`, `NgayKT`) VALUES (?,?,?,?,?,?)");
            ps.setString(1, makm);
            ps.setString(2, tenkm);
            ps.setFloat(3, dkkm);
            ps.setFloat(4, phantramkm);
            ps.setString(5, ngaybd);
            ps.setString(6, ngaykt);
            ps.executeUpdate();
            model.DAO.ConnectDB.getIntance().close();
            JOptionPane.showMessageDialog(null,
                    "Thêm khuyến mãi mới thành công.",
                    "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return true;
    }

    //Lấy ra 1 khuyến mãi bằng id
    public KhuyenMai getKhuyenMai(String makm) {
        String query = "SELECT * FROM `khuyenmai` WHERE MaKM='" + makm + "'";
        ArrayList<Object> arr = new ArrayList<>();
        KhuyenMai km = null;
        try {
            ConnectDB.getIntance().open();
            ResultSet rs = ConnectDB.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                km = new KhuyenMai(rs.getString("MaKM"),
                        rs.getString("TenKM"),
                        rs.getFloat("DieuKienKM"),
                        rs.getFloat("PhanTramKM"),
                        rs.getDate("NgayBD").toLocalDate(),
                        rs.getDate("NgayKT").toLocalDate());

            } else {
                return null;
            }
            ConnectDB.getIntance().close();
        } catch (SQLException ex) {
            ConnectDB.getIntance().displayError(ex);
        }

        return km;
    }

    //Update thông tin nguồn cung cấp
    public boolean UpdateKhuyenMai(String makm, String tenkm, float dkkm, float phantramkm, String ngaybd, String ngaykt) {
        if ("".equals(tenkm) || "".equals(dkkm) || "".equals(phantramkm) || "".equals(ngaybd) || "".equals(ngaykt)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "UPDATE `khuyenmai` SET TenKM ='" + tenkm + "', DieuKienKM ="+dkkm+", PhanTramKM=" + phantramkm + ", NgayBD='" +ngaybd+ "', NgayKT='" + ngaykt + "' where MaKM='" + makm + "'";
        System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        ConnectDB.getIntance().open();
        ConnectDB.getIntance().excuteUpdate(query, arr);
        ConnectDB.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin khuyến mãi thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

    //Tìm kiếm trong bảng nguồn cung cấp (mới)
    public ArrayList<KhuyenMai> FindListKhuyenMai(ArrayList<KhuyenMai> arr, String ValToSearch) {
        ArrayList<KhuyenMai> result = new ArrayList<>();
        for (int i = 0; i < arr.size(); i++) {

            if (arr.get(i).getMaKM().contains(ValToSearch)
                    || arr.get(i).getTenKM().contains(ValToSearch)
                    || String.valueOf(arr.get(i).getNgayBD()).contains(ValToSearch)
                    || String.valueOf(arr.get(i).getNgayKT()).contains(ValToSearch)
                    || String.valueOf(arr.get(i).getDieuKienKM()).contains(ValToSearch)
                    || String.valueOf(arr.get(i).getNgayKT()).contains(ValToSearch)) {

                result.add(arr.get(i));
            }
        }
        return result;
    }

    //Lấy ra danh sách 20 nguồn cung cấp, để làm phân trang
    public ArrayList<KhuyenMai> get20KhuyenMai(ArrayList<KhuyenMai> arr, long Trang) {
        ArrayList<KhuyenMai> result = new ArrayList<>();
        for (long i = (Trang * 20 - 20); i < (Trang * 20); i++) {
            if (i == arr.size()) {
                break;
            }
            result.add(arr.get((int) i));
        }
        return result;
    }

    // Xuất file Excel cho khuyến mãi
    public void ExcelKhuyenMai(ArrayList<KhuyenMai> arr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Khuyến mãi");
        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Mã khuyến mãi");

        cell = row.createCell(1);
        cell.setCellValue("Tên khuyến mãi");

        cell = row.createCell(2);
        cell.setCellValue("Điều kiện");

        cell = row.createCell(3);
        cell.setCellValue("Phần trăm");

        cell = row.createCell(4);
        cell.setCellValue("Ngày bắt đầu");

        cell = row.createCell(5);
        cell.setCellValue("Ngày kết thúc");

        cell = row.createCell(6);
        cell.setCellValue("Trạng thái");

        for (int i = 0; i < arr.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(arr.get(i).getMaKM());
            //
            cell = row.createCell(1);
            cell.setCellValue(arr.get(i).getTenKM());
            //
            cell = row.createCell(2);
            cell.setCellValue(arr.get(i).getDieuKienKM());
            //
            cell = row.createCell(3);
            cell.setCellValue(arr.get(i).getPhanTramKM());
            //
            cell = row.createCell(4);
            cell.setCellValue(arr.get(i).getNgayBD().toString());

            cell = row.createCell(5);
            cell.setCellValue(arr.get(i).getNgayKT().toString());

            cell = row.createCell(6);
            cell.setCellValue(arr.get(i).getTrangThai());
            //

        }
        File file = new File("C:/demo/KhuyenMai.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile;
        try {
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(fKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(fKhuyenMai.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("Created file: " + file.getAbsolutePath());
    }

}
