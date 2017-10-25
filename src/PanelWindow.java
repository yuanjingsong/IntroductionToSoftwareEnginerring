

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class PanelWindow extends JFrame{
    static String username;
    static User user;
    static ArrayList<Task> tasks;
    static Object [][] taskArray;
    static String[] columnNames;
    JPanel jp1,jp2;
    JButton addTask, delTask, finishTask;
    JScrollPane scrollPane;
    static DefaultTableModel defaultTableModel;
    static JTable taskTable;
    PanelWindow() throws SQLException {
        jp1 = new JPanel();
        jp2 = new JPanel();
        addTask = new JButton("添加任务");
        finishTask = new JButton("完成任务");
        delTask = new JButton("删除任务");
        getUser();
        initTable();
        defaultTableModel = new DefaultTableModel(taskArray,columnNames);
        taskTable = new JTable(defaultTableModel);
        jp1.add(addTask);
        jp1.add(delTask);
        jp1.add(finishTask);
        scrollPane = new JScrollPane(taskTable);
        jp2.add(scrollPane);
        add(jp1,BorderLayout.NORTH);
        add(jp2,BorderLayout.CENTER);
        setTitle("Hello");
        setSize(500,400);
        setLocation(550,250);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    public static void init(PanelWindow panelWindow) throws SQLException {
        AddTask addTask = new AddTask(defaultTableModel);
        panelWindow.addTask.addActionListener(addTask);
        DelTask delTask = new DelTask(defaultTableModel, taskTable, tasks);
        panelWindow.delTask.addActionListener(delTask);
        finishTask finishTask = new finishTask(defaultTableModel, taskTable);
        panelWindow.finishTask.addActionListener(finishTask);
//        openTimeWindows open = new openTimeWindows();
//        panelWindow.addTime.addActionListener(open);
//        showUserInfo showUserInfo = new showUserInfo();
//        panelWindow.userCenter.addActionListener(showUserInfo);

//        defaultTableModel.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int row = e.getFirstRow();
//                int ChangedTaskId = tasks.get(row).getId();
//                String ChangeTaskName = (String) defaultTableModel.getValueAt(row, 0);
//                String ChangeTaskDesc = (String) defaultTableModel.getValueAt(row, 1);
//                String ChangeTaskTag = (String) defaultTableModel.getValueAt(row,2);
//                Connection connection = sqlConnection.getConnection();
//                Task task = new Task(ChangedTaskId,ChangeTaskName,ChangeTaskDesc,ChangeTaskTag);
//                try {
//                    sqlConnection.updateTask(connection,ChangedTaskId,task);
//                } catch (SQLException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        });
   }
    public static void getUser() throws SQLException {
        Connection connection = sqlConnection.getConnection();
        username = sqlConnection.getUserLogined(connection);
        user = sqlConnection.getUser(connection, username);
    }
    public static void initTable() throws SQLException {
        Connection connection = sqlConnection.getConnection();
        tasks = sqlConnection.getTask(connection,username);
        taskArray = new Object[tasks.size()][3];
        columnNames = new String[3];
        columnNames[0] = "Name";
        columnNames[1] = "Description";
        columnNames[2] = "Tag";
        for (int i = 0; i < taskArray.length; i++){
            taskArray[i][0] = tasks.get(i).getName();
            taskArray[i][1] = tasks.get(i).getDescription();
            taskArray[i][2] = tasks.get(i).getTag();
        }
    }
    public static void updateTable() throws SQLException {
        Connection connection = sqlConnection.getConnection();
        Object[] lastTaskItem ;
        lastTaskItem = sqlConnection.getLastTask(connection);
        defaultTableModel.addRow(lastTaskItem);
    }

}
