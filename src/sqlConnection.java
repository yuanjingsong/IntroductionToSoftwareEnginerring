
import com.sun.org.apache.regexp.internal.RE;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import java.sql.*;
import java.util.ArrayList;


// Insert id && username && password && email into user table
// Insert name && id && details into task tables
// Insert groupName && id && taskId && playerId into task_membership
public class sqlConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/todoList";
    static final String User = "root";
    static final String pass = "Qq2014..";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(DB_URL, User, pass);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static boolean UserExists(Connection connection, String username) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "SELECT id FROM user WHERE username = \"" + username + "\"";
        ResultSet rs = statement.executeQuery(sql);
        if (rs.next()) {
            if (rs.getInt("id") > 0) {
                statement.close();
                return true;
            } else {
                statement.close();
                return false;
            }
        } else
            return false;
    }

    public static boolean Login(Connection connection, String username, String pass) throws SQLException {
        Statement statement = connection.createStatement();
        if (!UserExists(connection, username)) {
            return false;
        }
        String sql = "SELECT password FROM user WHERE username = \"" + username + "\"";
        ResultSet rs = statement.executeQuery(sql);
        String queryString = null;
        if (rs.next()) {
            queryString = rs.getString("password");
        }
        statement.close();
        if (queryString.equals(pass)) {
            statement.close();
            return true;
        } else {
            statement.close();
            return false;
        }
    }
    public static void UserLogin(Connection connection, String username) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "update `user` set status=1 where username=" + "\""+ username+ "\"";
        System.out.println(sql);
        statement.executeUpdate(sql);
        connection.close();
    }
    public static void UserQuit(Connection connection, String username) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "update `user` set status=0 where username=" + "\"" + username + "\"";
        statement.executeUpdate(sql);
        connection.close();
    }
    public static boolean Register(Connection connection, String username, String pass, String email) throws SQLException {
        if(UserExists(connection, username)){
            return false;
        }
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO USER (username, password,email) VALUES ( " + "\"" + username + "\"," + "\"" + pass + "\","
                + "\"" + email + "\")";
        boolean results = statement.execute(sql);
        if (!results) {
            System.out.println("WRONG");
            return false;
        }
        statement.close();
        return true;
    }

    public static boolean Register(Connection connection, String username, String pass) throws SQLException {
        if(UserExists(connection,username)) {
            return false;
        }
        Statement statement = connection.createStatement();
        String sql = "INSERT INTO USER (username, password) VALUES (" + "\"" + username + "\"," + "\"" + pass + "\")";
        boolean results = statement.executeUpdate(sql) == 1 ? true : false;
        if (!results) {
            System.out.println("WRONG");
            return false;
        }
        statement.close();
        return true;
    }

    public static void addTask(Connection connection, Task task, int Taskid) throws SQLException {
        //Insert Task name && id && details into task tables
        Statement statement = connection.createStatement();
        String TaskDetails = task.getDescription();
        String TaskName = task.getName();
        String TaskTag = task.getTag();
        String sql = "INSERT INTO task (id, name, description, tag) VALUES (" + Taskid + "," + "\"" + TaskName + "\"," +
                "\"" + TaskDetails + "\"," + "\"" + TaskTag + "\")";
        boolean results = statement.executeUpdate(sql) == 1 ? true : false;
        if (!results) {
            System.out.println("WRONG");
        }
        connection.close();
    }
    public static void updateTask(Connection connection, int changeTaskId, Task ChangedTask) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "update task_member set task_name = \"" + ChangedTask.getName()+"\"" + "where id = " + changeTaskId;
        statement.executeUpdate(sql);
        sql = "update task set name = \"" + ChangedTask.getName() + "\", description = \"" + ChangedTask.getDescription() + "\","
                + "tag = \"" + ChangedTask.getTag() + "\" where id = " + changeTaskId;
        System.out.println("updateTask fun && sql :" + sql);
        statement.executeUpdate(sql);
    }


    public static void upsetTaskTotal(Connection connection, String username, int task_total) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "Update `user` set task_total = " + task_total + " where username =\"" + username
                + "\"";
        System.out.println(sql);
        statement.executeUpdate(sql);
        statement.close();
    }
    public static void Clear() throws SQLException {
        Connection connection = sqlConnection.getConnection();
        Statement statement = connection.createStatement();
        String sql = "update `user` set status = 0 where id > -1";
        System.out.println(sql);
        statement.executeUpdate(sql);
        connection.close();
    }

    public static int addTaskMembership(Connection connection, String member_name, String task_name) throws SQLException {
        int taskId = -1;
        Statement statement = connection.createStatement();
        String sql = "Insert into task_member (task_name, member_name) Values (" + "\"" + task_name + "\"," +
                "\"" + member_name + "\")";
        boolean results = statement.executeUpdate(sql) == 1 ? true : false;
        if(!results) {
            System.out.println("WRONG");
        }
        sql = "select id from task_member order by id desc limit 1";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()) {
            taskId = rs.getInt("id");
        }
        connection.close();
        return taskId;
    }
    public static int getTaskId (Connection connection, String username, String taskName) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select id from task_member where member_name=" + "\"" + username + "\" and task_name = " + "\"" + taskName +"\"";
        System.out.println(sql);
        ResultSet rs = statement.executeQuery(sql);
        int TaskId = 0;
        if (rs.next()) {
            TaskId = rs.getInt("id");
        }
        return TaskId;
    }

    public static void deleteTask(Connection connection, int taskId ) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "DELETE from task_member WHERE id = " + taskId;
        statement.executeUpdate(sql);
        sql = "DELETE  from task WHERE id = " + taskId;
        statement.executeUpdate(sql);
        connection.close();
    }
    public static String getUserLogined(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select username from `user` where status=1";
        ResultSet rs= statement.executeQuery(sql);
        String username = null;
        if(rs.next()){
            username = rs.getString("username");
            System.out.println(username);
        }
        return username;
    }
    public static ArrayList<Task> getTask(Connection connection, String username) throws SQLException {
        ArrayList<Integer> task_name = new ArrayList<>();
        ArrayList<Task>tasks = new ArrayList<>();
        Statement statement = connection.createStatement();
        String sql = "select id from task_member where member_name= " + "\"" + username + "\"";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            int nextTaskName = rs.getInt("id");
            task_name.add(nextTaskName);
        }
        for (int i = 0; i < task_name.size(); i++) {
            int id = task_name.get(i);
            sql = "SELECT id, name, description, tag from task WHERE id = \"" + id + "\"";
            rs= statement.executeQuery(sql);
            if(rs.next()){
                Task newTask = new Task(rs.getInt("id"),rs.getString("name"),rs.getString("description"),rs.getString("tag"));
                tasks.add(newTask);
            }
        }
        return tasks;
    }
    public static Object[] getLastTask(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "select name, description, tag from task order by id desc limit 1 ";
        ResultSet rs = statement.executeQuery(sql);
        Task lastTask = null;
        while (rs.next()) {
            lastTask = new Task(rs.getString("name"),rs.getString("description"), rs.getString("tag")) ;
        }
        Object[] lastTaskItem = new Object[3];
        lastTaskItem[0] = lastTask.getName();
        lastTaskItem[1] = lastTask.getDescription();
        lastTaskItem[2] = lastTask.getTag();
        return lastTaskItem;
    }
    public static User getUser(Connection connection, String username) throws SQLException {
        User user = null;
        Statement statement = connection.createStatement();
        String sql = "select username, task_total, minutes from `user` where username = \""
                + username + "\"";
        ResultSet rs = statement.executeQuery(sql);
        if(rs.next()) {
            user = new User(username, rs.getInt("task_total"), rs.getInt("minutes"));
        }
        return user;
    }
    public static void updateMinutes (Connection connection, int minutes ) throws SQLException {
        Statement statement = connection.createStatement();
        String sql = "update `user` set minutes = " + minutes + " where username = \"" + PanelWindow.username + "\"";
        System.out.println(sql);
        statement.executeUpdate(sql);
        connection.close();
    }
}
