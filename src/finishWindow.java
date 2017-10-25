import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class finishWindow extends JFrame {
    JPanel jp1, jp2;
    JLabel Username, finishRate, showUsername, showFinishRate;
    static String username;

    finishWindow() throws SQLException {
        Username = new JLabel("用户名");
        finishRate = new JLabel("完成率");
        showUsername = new JLabel(MainWindow.username);
        Connection connection = sqlConnection.getConnection();
        username = MainWindow.username;
        int task_total = sqlConnection.getUserTaskTotal(connection, username);
        int task_finished = sqlConnection.getUserTaskFinished(connection, username);
        if(task_total == 0) {
           showFinishRate = new JLabel("无任务");
        }else {
            showFinishRate = new JLabel((double) task_finished / task_total * 100 + "%");
        }
        jp1 = new JPanel();
        jp2 = new JPanel();
        jp1.add(Username);
        jp1.add(showUsername);
        jp2.add(finishRate);
        jp2.add(showFinishRate);
        setLayout(new GridLayout(2, 1));
        add(jp1);
        add(jp2);
        setTitle("完成情况");
        setLocation(600, 300);
        setSize(600, 150);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void getUser() throws SQLException {
        Connection connection = sqlConnection.getConnection();
        username = sqlConnection.getUserLogined(connection);
    }
}
