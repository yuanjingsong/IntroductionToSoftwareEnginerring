
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

class Cancel implements ActionListener {
    JFrame window;
    Cancel() {
    }

    Cancel(JFrame window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(window.getClass().equals("LoginWindow")){
            System.exit(0);
        }else {
            window.dispose();
        }
    }
}

class Register implements ActionListener {
    LoginWindow window;
    Register(){}
    @Override
    public void actionPerformed(ActionEvent e) {
        RegisterWindow registerWindow = new RegisterWindow();
        registerWindow.init(registerWindow);
    }
}
class Login implements ActionListener {
    public static PanelWindow panelWindow;
    LoginWindow window;
    Login() {
    }

    Login(LoginWindow window) {
        this.window = window;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = window.jtf.getText();
        String password = window.jpf.getText();
        Connection connection = sqlConnection.getConnection();
        try {
            if (sqlConnection.Login(connection, user, password)) {
                sqlConnection.UserLogin(connection,user);
                panelWindow = new PanelWindow();
                panelWindow.init(panelWindow);
                window.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "用户名或密码错误", "WRONG", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }


}
class Confirm implements ActionListener{
    RegisterWindow registerWindow;
    Confirm(){};
    Confirm (RegisterWindow registerWindow){
        this.registerWindow = registerWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String user =  registerWindow.username.getText();
        String pass = registerWindow.password.getText();
        System.out.println(user);
        System.out.println(pass);
        Connection connection = sqlConnection.getConnection();
        try {
            if(sqlConnection.Register(connection, user,pass)){
                JOptionPane.showMessageDialog(null, "注册成功", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
                registerWindow.dispose();
            }else{
                if(sqlConnection.UserExists(connection,user)){
                    JOptionPane.showMessageDialog(null,"用户名重复","WRONG",JOptionPane.ERROR_MESSAGE);
                }else {

                }
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
class AddTask implements ActionListener{
    static DefaultTableModel tableModel;
    AddTask(DefaultTableModel tableModel) {
        this.tableModel = tableModel;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        addTaskWindow addTaskWindow = new addTaskWindow();
        addTaskWindow.init(addTaskWindow);
    }
}
class DelTask implements ActionListener{
    static JTable jTable;
    static DefaultTableModel tableModel;
    static ArrayList<Task> data;
    DelTask(DefaultTableModel tableModel, JTable table, ArrayList<Task> data) {
        this.tableModel = tableModel;
        this.jTable = table;
        this.data = data;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        int selectedRows[] = jTable.getSelectedRows();
        if (selectedRows.length != 0) {
            int response = JOptionPane.showConfirmDialog(null,"请确认删除","删除",JOptionPane.YES_NO_CANCEL_OPTION);
            if (response == 0) {
                int count = selectedRows.length - 1;
                int deleTaskNum = selectedRows.length;
                for (int i = 0; i < selectedRows.length ; i++) {
                    String delName = (String) tableModel.getValueAt(selectedRows[count],0);
                    try {
                        PanelWindow.user.deleteTask(delName);
                        PanelWindow.defaultTableModel.removeRow(selectedRows[count--]);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                }
                try {
                    System.out.println("deleteTask Num is " + deleTaskNum);
                    PanelWindow.user.changeTaskTotal(deleTaskNum);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
            else
                return;
        }
    }
}
class addTaskButton implements ActionListener{
    addTaskWindow addTaskWindow;
    static String TaskName;
    static String TaskDes;
    static String TaskTag;
    addTaskButton (addTaskWindow window){
        this.addTaskWindow = window;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        TaskName = addTaskWindow.NameField.getText();
        TaskDes = addTaskWindow.DesField.getText();
        TaskTag = addTaskWindow.TagField.getText();
        addTaskWindow.init(addTaskWindow);
        Task task = new Task(TaskName, TaskDes, TaskTag);
        try {
            System.out.println(PanelWindow.user);
            PanelWindow.user.addTask(task);
            PanelWindow.updateTable();
            addTaskWindow.dispose();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}
class setTimer implements ActionListener{
    TimeWindow window;
    static int time;
    setTimer (TimeWindow timeWindow) {
        this.window = timeWindow;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        time = Integer.parseInt(window.inputTime.getText());
        window.remove(window.jp1);
        window.remove(window.jp3);
        window.jp3.remove(window.confirm);
        addMin addMin = new addMin(window);
        window.jp3.add(window.ok);
        window.add(window.jp2);
        window.add(window.jp3);
        long start = System.currentTimeMillis();
        final long end = start + time * 60 * 1000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                long duration = System.currentTimeMillis() - start;
                long show = end - System.currentTimeMillis();
                long minutes = show / 1000 / 60 %60;
                long sec = show / 1000 % 60 ;
                window.showTime.setText("" + minutes +": " + sec );
            }
        },0,1000) ;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                window.ok.addActionListener(addMin);
                timer.cancel();
            }
        },new Date(end));

    }
}
class showUserInfo implements ActionListener{
    @Override
    public void actionPerformed(ActionEvent e) {
        UserWindow usrwindow = new UserWindow();
    }
}
class openTimeWindows implements ActionListener{

    @Override
    public void actionPerformed(ActionEvent e) {
        TimeWindow timeWindow = new TimeWindow();
        timeWindow.init(timeWindow);
    }
}
class addMin implements ActionListener {
    TimeWindow window;
    addMin(){}
    addMin(TimeWindow window) {this.window = window;};
    @Override
    public void actionPerformed(ActionEvent e) {
        window.dispose();
        try {
            PanelWindow.user.changeMin(setTimer.time);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
}