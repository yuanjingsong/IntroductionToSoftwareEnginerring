import javax.swing.*;
import java.awt.*;

public class RegisterWindow extends JFrame {
    JPanel jp1, jp2, jp3;
    JTextField username;
    JPasswordField password;
    JLabel nameLabel, passLabel;
    JButton Confirm, Cancel;
    RegisterWindow() {
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        username = new JTextField(10);
        password =  new JPasswordField(10);
        nameLabel = new JLabel("用户名：");
        passLabel = new JLabel("密码：  ");
        Confirm = new JButton("注册");
        Cancel = new JButton("取消");
        setLayout(new GridLayout(3,1));
        jp1.add(nameLabel);
        jp1.add(username);
        jp2.add(passLabel);
        jp2.add(password);
        jp3.add(Confirm);
        jp3.add(Cancel);
        add(jp1);
        add(jp2);
        add(jp3);
        setTitle("注册");
        setSize(400,200);
        setLocation(600,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void init (RegisterWindow registerWindow){
       Confirm confirm = new Confirm(registerWindow);
       registerWindow.Confirm.addActionListener(confirm);
       Cancel cancel = new Cancel(registerWindow);
       registerWindow.Cancel.addActionListener(cancel);
    }
}

