import java.sql.*;

public class PassbookRepository {

    public void addEntry(int memberId, String date, double amount) {
        String sql = "INSERT INTO Passbook(memberId, date, amount) VALUES(?, ?, ?)";
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, memberId);
            pstmt.setString(2, date);
            pstmt.setDouble(3, amount);
            pstmt.executeUpdate();
            System.out.println("✅ Passbook entry added!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding entry: " + e.getMessage());
        }
    }

    public void viewEntries() {
        String sql = "SELECT * FROM Passbook";
        try (Connection conn = DBHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Passbook Entries ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | Member: " +
                                   rs.getInt("memberId") + " | " +
                                   rs.getString("date") + " | " +
                                   rs.getDouble("amount"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error viewing entries: " + e.getMessage());
        }
    }
}
