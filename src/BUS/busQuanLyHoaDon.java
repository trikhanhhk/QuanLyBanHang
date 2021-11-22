/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BUS;

/**
 *
 * @author trikh
 */
public class busQuanLyHoaDon {
    
    private static busQuanLyHoaDon instance;

    public static busQuanLyHoaDon getInstance() {
        if (instance == null) {
            instance = new busQuanLyHoaDon();
        }
        return instance;
    }
    
    public String getNextID() {
        return DAO.daoQuanLyHoaDon.getInstance().getNextID();
    }
    
}
