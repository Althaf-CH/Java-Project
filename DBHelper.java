import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    private static final String URL = "jdbc:sqlite:data/kudumbashree.db";

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}
