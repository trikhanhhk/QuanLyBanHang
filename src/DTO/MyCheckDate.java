/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.awt.Color;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author trikh
 */
public class MyCheckDate {
    LocalDate fromDate = null;
    LocalDate toDate = null;
    String khoangTG = "";

    public MyCheckDate(JXDatePicker txFrom, JXDatePicker txTo) {
        try {
            DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayBd = "";
            if(txFrom!=null && txFrom.getDate()!=null){
                ngayBd = oDateFormat.format(txFrom.getDate()).toString();
            }
            fromDate = LocalDate.parse(ngayBd);
            txFrom.setForeground(Color.black);
            khoangTG += String.valueOf(fromDate);

        } catch (DateTimeParseException e) {
            txFrom.setForeground(Color.red);
            khoangTG += "Từ ngày đầu";
        }
        try {
            DateFormat oDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String ngayKt = "";
            if(txTo!=null && txTo.getDate()!=null) {
                ngayKt = oDateFormat.format(txTo.getDate()).toString();
            }
            toDate = LocalDate.parse(ngayKt);
            txTo.setForeground(Color.black);
            khoangTG += " đến " + String.valueOf(toDate);

        } catch (DateTimeParseException e) {
            txTo.setForeground(Color.red);
            khoangTG += " đến nay";
        }
    }

    public LocalDate getNgayTu() {
        return fromDate;
    }

    public LocalDate getNgayDen() {
        return toDate;
    }

    public String getKhoangTG() {
        return khoangTG;
    }
}
