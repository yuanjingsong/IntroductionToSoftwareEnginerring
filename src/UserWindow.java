import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class UserWindow extends JFrame{
    JPanel jp1,jp2,jp3;
    JLabel Username, TaskTotal, Time, showUsername, showTaskTotal,showTime ;
    UserWindow () throws SQLException {
        Username = new JLabel("用户名");
        Time = new JLabel("专心时长");
        TaskTotal = new JLabel("任务总数");
        String username = MainWindow.user.getUsername();
        int time = MainWindow.user.getMinutes();
        Connection connection = sqlConnection.getConnection();
        int tasktotal = sqlConnection.getUserTaskTotal(connection, MainWindow.username);
        showUsername = new JLabel(username);
        showTime = new JLabel( "您已经专注"+time + "分钟");
        showTaskTotal = new JLabel(String.valueOf(tasktotal));
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp3 = new JPanel();
        jp1.add(Username);
        jp1.add(showUsername);
        jp2.add(TaskTotal);
        jp2.add(showTaskTotal);
        jp3.add(Time);
        jp3.add(showTime);
        setLayout(new GridLayout(3,1));
        add(jp1);
        add(jp2);
        add(jp3);
        setTitle("用户信息");
        setSize(600,150);
        setLocation(600,300);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
