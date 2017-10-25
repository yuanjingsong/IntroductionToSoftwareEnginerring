
import sun.applet.Main;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class MainWindow extends JFrame{
    JPanel jp1;
    JButton addTime, userCenter, showPanelwindow, finishRate;
    static User user;
    static String username;
    MainWindow() throws SQLException {
        jp1 = new JPanel();
        addTime = new JButton("番茄时间") ;
        userCenter = new JButton("查看个人信息");
        showPanelwindow = new JButton("查看任务管理");
        finishRate = new JButton("查看完成率") ;
        getUser();
        jp1.add(showPanelwindow);
        jp1.add(addTime) ;
        jp1.add(userCenter);
        jp1.add(finishRate);
        add(jp1, BorderLayout.NORTH);
        setSize(500,400);
        setLocation(550,250);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }
    public static void init(MainWindow mainWindow) {
        openTimeWindows open = new openTimeWindows();
        mainWindow.addTime.addActionListener(open);
        showUserInfo showUserInfo = new showUserInfo();
        mainWindow.userCenter.addActionListener(showUserInfo);
        showPanelWindow showPanelWindow = new showPanelWindow();
        mainWindow.showPanelwindow.addActionListener(showPanelWindow);
        showFinish showFinish = new showFinish();
        mainWindow.finishRate.addActionListener(showFinish);
    }
    public static void getUser() throws SQLException {
        Connection connection = sqlConnection.getConnection();
        username = sqlConnection.getUserLogined(connection);
        user = sqlConnection.getUser(connection, username);
    }
}
