/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller_view.GUI;

import model.DTO.NhanVien;
import model.DTO.TaiKhoan;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.lang.*;

/**
 *
 * @author trikh
 */
public class fDangNhap extends javax.swing.JFrame {

    /**
     * Creates new form fDangNhap
     */
    public fDangNhap() {
        initComponents();
        setIcon();
    }

    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/Logo2.png")));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jDesktopPane2 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextFieldTenDangNhap = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jPasswordFieldMatKhau = new javax.swing.JPasswordField();
        jCheckBoxHienMatKhau = new javax.swing.JCheckBox();
        jButtonDangNhap = new javax.swing.JButton();
        jButtonThoat = new javax.swing.JButton();

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Đăng nhập hệ thống");
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KeyPress(evt);
            }
        });

        jDesktopPane2.setBackground(new java.awt.Color(51, 51, 51));
        jDesktopPane2.setForeground(new java.awt.Color(0, 102, 102));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/fDangNhap.png"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập:");

        jTextFieldTenDangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTextFieldTenDangNhap.setText("trikh");
        jTextFieldTenDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTenDangNhapActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu:");

        jPasswordFieldMatKhau.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jPasswordFieldMatKhau.setText("trikh");
        jPasswordFieldMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jPasswordFieldMatKhauActionPerformed(evt);
            }
        });

        jCheckBoxHienMatKhau.setBackground(new java.awt.Color(51, 51, 51));
        jCheckBoxHienMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxHienMatKhau.setText("Hiện mật khẩu");
        jCheckBoxHienMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHienMatKhauActionPerformed(evt);
            }
        });

        jButtonDangNhap.setBackground(new java.awt.Color(255, 255, 255));
        jButtonDangNhap.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonDangNhap.setText("Đăng nhập");
        jButtonDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDangNhapActionPerformed(evt);
            }
        });

        jButtonThoat.setBackground(new java.awt.Color(255, 255, 255));
        jButtonThoat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonThoat.setText("Thoát");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        jDesktopPane2.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jTextFieldTenDangNhap, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jPasswordFieldMatKhau, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jCheckBoxHienMatKhau, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButtonDangNhap, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane2.setLayer(jButtonThoat, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane2Layout = new javax.swing.GroupLayout(jDesktopPane2);
        jDesktopPane2.setLayout(jDesktopPane2Layout);
        jDesktopPane2Layout.setHorizontalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabel2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane2Layout.createSequentialGroup()
                                .addGap(56, 56, 56)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addComponent(jCheckBoxHienMatKhau)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jTextFieldTenDangNhap)
                            .addComponent(jPasswordFieldMatKhau)))
                    .addGroup(jDesktopPane2Layout.createSequentialGroup()
                        .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(94, 94, 94)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jButtonDangNhap)
                                .addGap(18, 18, 18)
                                .addComponent(jButtonThoat)))
                        .addGap(0, 36, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jDesktopPane2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonDangNhap, jButtonThoat});

        jDesktopPane2Layout.setVerticalGroup(
            jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane2Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldTenDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jPasswordFieldMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jCheckBoxHienMatKhau)
                .addGap(45, 45, 45)
                .addGroup(jDesktopPane2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonDangNhap, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jDesktopPane2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3});

        jDesktopPane2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonDangNhap, jButtonThoat});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane2)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDangNhapActionPerformed
        DangNhap();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDangNhapActionPerformed

    private void jCheckBoxHienMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHienMatKhauActionPerformed
        if (jCheckBoxHienMatKhau.isSelected()) {
            jPasswordFieldMatKhau.setEchoChar((char) 0);
        } else {
            jPasswordFieldMatKhau.setEchoChar('*');
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxHienMatKhauActionPerformed

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        dispose();
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonThoatActionPerformed

    private void KeyPress(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KeyPress
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            DangNhap();
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_KeyPress

    private void jPasswordFieldMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jPasswordFieldMatKhauActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jPasswordFieldMatKhauActionPerformed

    private void jTextFieldTenDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTenDangNhapActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTenDangNhapActionPerformed
    public void DangNhap() {
        String taikhoan = jTextFieldTenDangNhap.getText(); //lấy password
        String matkhau = jPasswordFieldMatKhau.getText(); //lấy mật khẩu
        if (model.DAO.daoTaiKhoan.getInstance().KiemTraDangNhap(taikhoan, matkhau)) { // lấy tài khoản kiểm tra 
            TaiKhoan tk = model.DAO.daoTaiKhoan.getInstance().getTaiKhoan(taikhoan, matkhau);
            NhanVien nv = model.DAO.daoTaiKhoan.getInstance().getNhanVien(tk.getMaNV());
            if (nv.getTrangThai() != 0) { // nếu trạng thái khác 0 tức bị khóa
                JOptionPane.showMessageDialog(rootPane,
                        "Tài khoản đã bị khóa.",
                        "Lỗi đăng nhập",
                        JOptionPane.ERROR_MESSAGE);
                return; //thoát luôn
            }
            JFrame TrangChu = new fHome(tk.getMaNV(), tk); //khởi tạo trang chủ
            TrangChu.setVisible(true); //hiện trang chủ
            this.setVisible(false);
            dispose();
        } else {
            JOptionPane.showMessageDialog(rootPane,
                    "Tên đăng nhập hoặc mật khẩu sai.",
                    "Lỗi đăng nhập",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(fDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fDangNhap.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fDangNhap().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonDangNhap;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JCheckBox jCheckBoxHienMatKhau;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JDesktopPane jDesktopPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPasswordField jPasswordFieldMatKhau;
    private javax.swing.JTextField jTextFieldTenDangNhap;
    // End of variables declaration//GEN-END:variables
}
