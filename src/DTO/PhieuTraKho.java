/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;
import java.lang.*;
/**
 *
 * @author tk0038
 */
public class PhieuTraKho {
    public int id_phieu_tra_kho;
    public String thoi_gian_tra;
    public int sl_san_pham;
    public String hinh_thuc_tra;
    public int id_lo_sp;
    public String maNV;
    public PhieuTraKho(){}
    public PhieuTraKho(int id, String thoigian,int sl, String hinhthuc, int idlo, String maNV)
    {
        this.hinh_thuc_tra=hinhthuc;
        this.id_lo_sp=idlo;
        this.maNV=maNV;
        this.id_phieu_tra_kho=id;
        this.thoi_gian_tra=thoigian;
        this.sl_san_pham=sl;
    }
    
}
