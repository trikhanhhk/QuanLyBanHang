/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import java.awt.Toolkit;
import java.lang.*;

/**
 *
 * @author Xoan Tran
 */
public class fResetPass extends javax.swing.JFrame {

    /**
     * Creates new form fResetPass
     */
    public String maNV;
    public fResetPass() {
        initComponents();
        setIcon();
    }
    public fResetPass(String maNV)
    {
        this.maNV=maNV;
        initComponents();
        setIcon();
    }
    private void setIcon() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/icon/Logo2.png")));
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDesktopPane1 = new javax.swing.JDesktopPane();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldTaiKhoan = new javax.swing.JTextField();
        jPasswordFieldMatKhauCu = new javax.swing.JPasswordField();
        jPasswordFieldMatKhauMoi = new javax.swing.JPasswordField();
        jPasswordFieldNhaplaiMK = new javax.swing.JPasswordField();
        jButtonHuy = new javax.swing.JButton();
        jButtonCapNhat = new javax.swing.JButton();
        jCheckBoxHienMatKhau = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Thay đổi mật khẩu");
        setResizable(false);

        jDesktopPane1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/fResetPass.jpg"))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Tên đăng nhập:");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Mật khẩu cũ:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Mật khẩu mới:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nhập lại mật khẩu:");

        jTextFieldTaiKhoan.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPasswordFieldMatKhauCu.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPasswordFieldMatKhauMoi.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jPasswordFieldNhaplaiMK.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButtonHuy.setBackground(new java.awt.Color(255, 255, 255));
        jButtonHuy.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonHuy.setText("Hủy");
        jButtonHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonHuyActionPerformed(evt);
            }
        });

        jButtonCapNhat.setBackground(new java.awt.Color(255, 255, 255));
        jButtonCapNhat.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButtonCapNhat.setText("Cập nhật");
        jButtonCapNhat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCapNhatActionPerformed(evt);
            }
        });

        jCheckBoxHienMatKhau.setForeground(new java.awt.Color(255, 255, 255));
        jCheckBoxHienMatKhau.setText("Hiện mật khẩu");
        jCheckBoxHienMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBoxHienMatKhauActionPerformed(evt);
            }
        });

        jDesktopPane1.setLayer(jLabel1, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel2, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel3, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel4, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jLabel5, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jTextFieldTaiKhoan, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPasswordFieldMatKhauCu, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPasswordFieldMatKhauMoi, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jPasswordFieldNhaplaiMK, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonHuy, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jButtonCapNhat, javax.swing.JLayeredPane.DEFAULT_LAYER);
        jDesktopPane1.setLayer(jCheckBoxHienMatKhau, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout jDesktopPane1Layout = new javax.swing.GroupLayout(jDesktopPane1);
        jDesktopPane1.setLayout(jDesktopPane1Layout);
        jDesktopPane1Layout.setHorizontalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButtonCapNhat)
                .addGap(33, 33, 33)
                .addComponent(jButtonHuy)
                .addGap(31, 31, 31))
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jCheckBoxHienMatKhau)
                    .addComponent(jTextFieldTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldNhaplaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jDesktopPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPasswordFieldMatKhauCu, jPasswordFieldMatKhauMoi, jPasswordFieldNhaplaiMK, jTextFieldTaiKhoan});

        jDesktopPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5});

        jDesktopPane1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButtonCapNhat, jButtonHuy});

        jDesktopPane1Layout.setVerticalGroup(
            jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jDesktopPane1Layout.createSequentialGroup()
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jDesktopPane1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextFieldTaiKhoan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jPasswordFieldMatKhauCu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jPasswordFieldMatKhauMoi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jPasswordFieldNhaplaiMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jCheckBoxHienMatKhau)
                .addGap(18, 18, 18)
                .addGroup(jDesktopPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonHuy)
                    .addComponent(jButtonCapNhat, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        jDesktopPane1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButtonCapNhat, jButtonHuy});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCapNhatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCapNhatActionPerformed
        String taikhoan =jTextFieldTaiKhoan.getText();
        String matkhau = jPasswordFieldMatKhauCu.getText();
        String matkhaumoi=jPasswordFieldMatKhauMoi.getText();
        String nhaplaimk=jPasswordFieldNhaplaiMK.getText();
        int flag = DAO.daoTaiKhoan.getInstance().KiemTraTaiKhoan(taikhoan, matkhau,matkhaumoi,nhaplaimk, this.maNV);
        if(flag==1)//Kiem tra neu tk bằng null thì retrun 1
        {
            jTextFieldTaiKhoan.setText("");
            jPasswordFieldMatKhauCu.setText("");
            jPasswordFieldMatKhauMoi.setText("");
            jPasswordFieldNhaplaiMK.setText("");
        }
         if(flag==2)// neu mk hoặc mk mới để trống thì return 2
        {
            jTextFieldTaiKhoan.setText("");
            jPasswordFieldMatKhauCu.setText("");
            jPasswordFieldMatKhauMoi.setText("");
            jPasswordFieldNhaplaiMK.setText("");
        }
        if(flag==3)// neu mk moi bằng pass (cũ) thì return 3
        {
            jPasswordFieldMatKhauMoi.setText("");
            jPasswordFieldNhaplaiMK.setText("");
            jPasswordFieldMatKhauCu.setText("");
        }
        if(flag==4)// nếu mật khẩu mới khoong trùng với mk xác nhận thì return 4
        {
            jPasswordFieldMatKhauMoi.setText("");
            jPasswordFieldNhaplaiMK.setText("");
        }
        if(flag==0)
        {
            dispose();//Thoát
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonCapNhatActionPerformed

    private void jButtonHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonHuyActionPerformed
        dispose();//Thoat
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonHuyActionPerformed

    private void jCheckBoxHienMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBoxHienMatKhauActionPerformed
        if(jCheckBoxHienMatKhau.isSelected())
        {
            jPasswordFieldMatKhauCu.setEchoChar((char)0);
            jPasswordFieldMatKhauMoi.setEchoChar((char)0);
            jPasswordFieldNhaplaiMK.setEchoChar((char)0);
        }
        else
        {
            jPasswordFieldMatKhauCu.setEchoChar('*');
            jPasswordFieldMatKhauMoi.setEchoChar('*');
            jPasswordFieldNhaplaiMK.setEchoChar('*');
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jCheckBoxHienMatKhauActionPerformed

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
            java.util.logging.Logger.getLogger(fResetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(fResetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(fResetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(fResetPass.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new fResetPass("1").setVisible(true);
                //tienxd
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCapNhat;
    private javax.swing.JButton jButtonHuy;
    private javax.swing.JCheckBox jCheckBoxHienMatKhau;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField jPasswordFieldMatKhauCu;
    private javax.swing.JPasswordField jPasswordFieldMatKhauMoi;
    private javax.swing.JPasswordField jPasswordFieldNhaplaiMK;
    private javax.swing.JTextField jTextFieldTaiKhoan;
    // End of variables declaration//GEN-END:variables
}
