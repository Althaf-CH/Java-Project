import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreateTables {
    public static void main(String[] args) {
        // Database URL (this will create a file 'kudumbashree.db' in your project folder)
        String url = "jdbc:sqlite:kudumbashree.db";

        try (Connection conn = DriverManager.getConnection(url);  // Connect to database
             Statement stmt = conn.createStatement()) {           // Create SQL statement object

            // 1. Create Members Table
            String sqlMembers = "CREATE TABLE IF NOT EXISTS Members (" +
                                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                "name TEXT NOT NULL," +
                                "age INTEGER," +
                                "skill TEXT);";
            stmt.execute(sqlMembers);

            // 2. Create Meetings Table
            String sqlMeetings = "CREATE TABLE IF NOT EXISTS Meetings (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "event TEXT NOT NULL," +
                                 "date TEXT NOT NULL);";
            stmt.execute(sqlMeetings);

            // 3. Create Passbook Table
            String sqlPassbook = "CREATE TABLE IF NOT EXISTS Passbook (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "member_id INTEGER," +
                                 "date TEXT NOT NULL," +
                                 "amount REAL," +
                                 "FOREIGN KEY(member_id) REFERENCES Members(id));";
            stmt.execute(sqlPassbook);

            // 4. Create Products Table
            String sqlProducts = "CREATE TABLE IF NOT EXISTS Products (" +
                                 "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                                 "name TEXT NOT NULL," +
                                 "price REAL," +
                                 "quantity INTEGER);";
            stmt.execute(sqlProducts);

            System.out.println("✅ All tables created successfully!");

        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
