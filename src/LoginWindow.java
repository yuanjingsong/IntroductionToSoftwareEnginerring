
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;

public class LoginWindow extends JFrame {
    public static String user;
    JPanel jp1, jp2, jp3;
    JLabel jUser, jPass;
    JButton jConfirm, jCancel,jRegister ;
    JTextField jtf;
    JPasswordField jpf;
    LoginWindow() throws SQLException {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jUser = new JLabel("用户名:");
        jPass = new JLabel("密码:");
        jConfirm = new JButton("登录");
        jCancel = new JButton("取消");
        jRegister = new JButton("注册");
        jtf = new JTextField(10);
        jpf = new JPasswordField(10);
        setLayout(new GridLayout(3, 1));
        jp1.add(jUser);
        jp1.add(jtf);
        jp2.add(jPass);
        jp2.add(jpf);
        jp3.add(jConfirm);
        jp3.add(jCancel);
        jp3.add(jRegister);
        add(jp1);
        add(jp2);
        add(jp3);
        setTitle("登录界面");
        setSize(300, 150);
        setLocation(600, 300);
        setDefaultCloseOperation();
        setVisible(true);
    }

    private void setDefaultCloseOperation() throws SQLException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sqlConnection.Clear();
    }

    public void init(LoginWindow window) {
        Login login = new Login(window);
        window.jConfirm.addActionListener(login);
        Cancel cancel = new Cancel(window);
        window.jCancel.addActionListener(cancel);
        Register register = new Register();
        window.jRegister.addActionListener(register);
    }
}

