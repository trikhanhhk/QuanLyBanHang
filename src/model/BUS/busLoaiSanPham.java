/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.BUS;
import model.DAO.daoLoaiSanPham;
import model.DTO.LoaiSanPham;
import java.util.ArrayList;
/**
 *
 * @author tk0038
 */
public class busLoaiSanPham {
    private static busLoaiSanPham instance;

    public static busLoaiSanPham getInstance() {
        if (instance == null) {
            instance = new busLoaiSanPham();
        }
        return instance;
    }

    public busLoaiSanPham() {
    }
    public ArrayList<LoaiSanPham> getListLoaiSanPham() {
        return daoLoaiSanPham.getInstance().getListLoaiSanPham();
    }
    public LoaiSanPham getTraKho(String MaLSP) {
        return daoLoaiSanPham.getInstance().getLoaiSanPham(MaLSP);
    }
    public void ThemLoaiSanPham(String TenLSP, String MaLSP, String Mota, String maNV) {
        daoLoaiSanPham.getInstance().ThemLoaiSanPham(TenLSP,MaLSP, Mota, maNV);
    }
    
    public void UpdateLoaiSanPham (String TenLSP, String MaLSP, String Mota) {
        daoLoaiSanPham.getInstance().UpdateLoaiSanPham(TenLSP,MaLSP, Mota);
    }
    
    public void DeleteLoaiSanPham(String MaLSP) {
        daoLoaiSanPham.getInstance().DeleteLoaiSanPham(MaLSP);
    }
    public ArrayList<LoaiSanPham> FindListLoaiSanPham(ArrayList<LoaiSanPham> DuLieuMau, String ValToSearch) {
        return daoLoaiSanPham.getInstance().FindListLoaiSanPham(DuLieuMau,ValToSearch);
    }
    public ArrayList<LoaiSanPham> get20LoaiSanPham(ArrayList<LoaiSanPham> arr, long Trang) {
        return daoLoaiSanPham.getInstance().get20LoaiSanPham(arr,Trang);
    }
}
