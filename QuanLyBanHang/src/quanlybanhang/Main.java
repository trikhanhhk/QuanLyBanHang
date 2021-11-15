package QuanLyBanHang;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import quanlybanhang.model.SourceFontEnd.BaseGiaoDien.LoginForm;

public class Main {

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {

        }
        
        new LoginForm().setVisible(true);
    }
}

