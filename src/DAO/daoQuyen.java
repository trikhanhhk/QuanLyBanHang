/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DTO.Quyen;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

/**
 *
 * @author tk0038
 */
public class daoQuyen {
    
    private static daoQuyen instance;
    public static daoQuyen getInstance() {
        if (instance == null) {
            instance = new daoQuyen();
        }
        return instance;
    }
    
    public daoQuyen() {
        
    }
    
    public ArrayList<Quyen> getListQuyen() {
        ArrayList<Quyen> result = new ArrayList<>();
        String query = "SELECT * FROM phanquyen";
        ArrayList<Object> arr = new ArrayList<>();
        try{
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            while(rs.next()) {
                result.add(new Quyen(
                    rs.getString("MaQuyen"),
                    rs.getString("TenQuyen"),
                    rs.getString("ChiTietQuyen")
                ));
            }
            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }
        return result;
    }
    
    public String getNextID() {
        ArrayList<Quyen> listQuyen = this.getListQuyen();
        return "Q" + String.valueOf(listQuyen.size()+1);
    }
    
    public boolean UpdateQuyen(
            String maQuyen,
            String tenQuyen,
            String chitiet) {
        if ("".equals(maQuyen) || "".equals(tenQuyen)|| "".equals(chitiet)) {
            JOptionPane.showMessageDialog(null,
                    "Chưa điền đầy đủ thông tin",
                    "Lỗi",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
        String query = "Update phanquyen Set TenQuyen='" + tenQuyen + "', ChiTietQuyen='" + chitiet + "'where MaQuyen='" + maQuyen + "'";
        System.out.println(query);
        ArrayList<Object> arr = new ArrayList<>();
        DataProvider.getIntance().open();
        DataProvider.getIntance().excuteUpdate(query, arr);
        DataProvider.getIntance().close();
        JOptionPane.showMessageDialog(null,
                "Sửa thông tin khách hàng thành công",
                "Thông báo",
                JOptionPane.INFORMATION_MESSAGE);
        //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getNhanVien(maNV).getTenNV() + " đã sửa thông tin loại sản phẩm vào lúc" + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        return true;
    }
    public void insertQuyen(String maQuyen, String tenQuyen, String chitiet) {
        String query = "INSERT INTO `phanquyen`(`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) VALUES ('" + maQuyen + "','" + tenQuyen + "','"  + chitiet + "')";
        System.out.println(query);
        try {
            DataProvider.getIntance().open();
            DataProvider.getIntance().excuteQuery(query);
            DataProvider.getIntance().close();
            JOptionPane.showMessageDialog(null, "Đã thêm quyền " + tenQuyen + " thành công", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            //DAO.daoThongBao.getInstance().insertThongBao("[Loại sản phẩm] Nhân viên " + DAO.daoTaiKhoan.getInstance().getQuyen(maNV).getTenNV() + " đã thêm loại sản phẩm mới vào lúc " + DAO.DateTimeNow.getIntance().Now, DAO.DateTimeNow.getIntance().Now, 6);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Thêm loại quyền " + tenQuyen + " Thất bại", "Thông báo", 1);
        }
    }

    //Tìm kiếm trong bảng quyền (cũ)
    public ArrayList<Quyen> FindListQuyen(String ValToSearch) {
        ArrayList<Quyen> phanquyenList = new ArrayList<>();
        ArrayList<Object> arr = new ArrayList<>();
        String searchQuery = "SELECT * FROM `phanquyen` WHERE CONCAT(`MaQuyen`, `TenQuyen`, `ChiTietQuyen`) LIKE '%" + ValToSearch + "%'";
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(searchQuery, arr);
            Quyen phanquyen;

            while (rs.next()) {
                phanquyen = new Quyen(
                    rs.getString("MaQuyen"),
                    rs.getString("TenQuyen"),
                    rs.getString("ChiTietQuyen")
                );
                phanquyenList.add(phanquyen);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        return phanquyenList;
    }
    //Xuất file Excel thông tin nhân viên

    public String ExcelQuyen(ArrayList<Quyen> arr) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Quyen");
        int rownum = 0;
        Cell cell;
        Row row;
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(0);
        cell.setCellValue("Mã quyền");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(1);
        cell.setCellValue("Tên quyền");
        //
        row = sheet.createRow(rownum);
        cell = row.createCell(2);
        cell.setCellValue("Chi tiết quyền");
        //
        for (int i = 0; i < arr.size(); i++) {
            rownum++;
            row = sheet.createRow(rownum);
            //
            cell = row.createCell(0);
            cell.setCellValue(arr.get(i).getMaQuyen());
            //
            cell = row.createCell(1);
            cell.setCellValue(arr.get(i).getTenQuyen());
            //
            cell = row.createCell(2);
            cell.setCellValue(arr.get(i).getChiTietQuyen());
            //
 
        }
        File file = new File("C:/demo/phanquyen.xls");
        file.getParentFile().mkdirs();

        FileOutputStream outFile;
        try {
            outFile = new FileOutputStream(file);
            workbook.write(outFile);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        System.out.println("Created file: " + file.getAbsolutePath());
        return file.getAbsolutePath();
    }
    //Tìm kiếm trong bảng nhân viên (mới)

    public ArrayList<Quyen> FindListQuyen(ArrayList<Quyen> DuLieuMau, String ValToSearch) {
        ArrayList<Quyen> result = new ArrayList<>();
        for (int i = 0; i < DuLieuMau.size(); i++) {
            if (DuLieuMau.get(i).getChiTietQuyen().contains(ValToSearch)
                    || String.valueOf(DuLieuMau.get(i).getMaQuyen()).contains(ValToSearch)
                    || DuLieuMau.get(i).getTenQuyen().contains(ValToSearch)) {
                result.add(DuLieuMau.get(i));
            }
        }
        return result;
    }
    
     public Quyen getQuyenByID(String ID) {
        Quyen result = null;
        String query = "select * from phanquyen where maQuyen = ?";
        ArrayList<Object> arr = new ArrayList<>();
        arr.add(ID);
        try {
            DataProvider.getIntance().open();
            ResultSet rs = DataProvider.getIntance().excuteQuery(query, arr);
            if (rs.next()) {
                result = new Quyen(
                    rs.getString("MaQuyen"),
                    rs.getString("TenQuyen"),
                    rs.getString("ChiTietQuyen")
                );
            }

            DataProvider.getIntance().close();
        } catch (SQLException ex) {
            DataProvider.getIntance().displayError(ex);
        }

        return result;
    }
}
