import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqlConnection {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/todoList";
    static final String User = "root";
    static final String pass = "Qq2014..";
    public static java.sql.Connection getConnection() {
        java.sql.Connection connection = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,User, pass);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
    public static boolean User_Exists( connection, String username) throws SQLException {
        String sql = "SELECT username FROM tb_user";
        Statement statement = connecti
        ResultSet rs = statement.executeQuery(sql);
        while (rs.next()) {
            if(rs.getString("username").equals(username)) {
                return true;
            }
        }
        return false;
    }

}
