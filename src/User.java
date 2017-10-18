import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Timer;

// Insert id && username && password && email into user table
public class User {
    private int id;
    private String username;
    private int hours;
    private int minutes;
    private int task_total;
    private int taskDayNum;
    private int taskWeeklyNum;
    private int taskMonthlyNum;
    private groupnumber role [];
    User (){};
    User (String username, int task_total, int minutes) {
        this.username = username;
        this.task_total = task_total;
        this.minutes = minutes;
    }
    public String getUsername() {
       return username;
    }
    public int getTask_total () {
        return task_total;
    }


    public int getMinutes() {
        return minutes;
    }


    public void changeMin (int minutes) throws SQLException {
        this.minutes += minutes;
        Connection connection = sqlConnection.getConnection();
        sqlConnection.updateMinutes(connection, this.minutes);
    }

    public void addTask (Task task) throws SQLException {
        this.task_total ++;
        int taskId;
        Connection connection = sqlConnection.getConnection();
        taskId = sqlConnection.addTaskMembership(connection,username,task.getName());
        connection = sqlConnection.getConnection();
        sqlConnection.addTask(connection, task, taskId);
        connection = sqlConnection.getConnection();
        System.out.println("add Task total is" + task_total);
        sqlConnection.upsetTaskTotal(connection, username , task_total);
    }
    public void addTaskGroup (Task task, String groupname) {

    }
    public void deleteTask (String taskname) throws SQLException { //when delete a task must alter a window to confirm
        Connection connection = sqlConnection.getConnection();
        int taskId = sqlConnection.getTaskId(connection,username, taskname);
        sqlConnection.deleteTask(connection, taskId);
    }
    public void changeTaskTotal (int changeNum) throws  SQLException{
        System.out.println(this.task_total);
        int NowTaskTotal = this.task_total - changeNum;
        Connection connection = sqlConnection.getConnection();
        sqlConnection.upsetTaskTotal(connection,username,NowTaskTotal);
        this.task_total--;
    }
}
