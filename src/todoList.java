
import java.sql.*;

public class todoList {
    public static PanelWindow panelWindow;
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        LoginWindow Loginwindow = new LoginWindow() ;
        Loginwindow.init(Loginwindow);
    }
}
