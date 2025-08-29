import java.sql.*;

public class ProductRepository {

    public void addProduct(String name, double price, int quantity) {
        String sql = "INSERT INTO Products(name, price, quantity) VALUES(?, ?, ?)";
        try (Connection conn = DBHelper.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, name);
            pstmt.setDouble(2, price);
            pstmt.setInt(3, quantity);
            pstmt.executeUpdate();
            System.out.println("✅ Product added successfully!");
        } catch (SQLException e) {
            System.out.println("❌ Error adding product: " + e.getMessage());
        }
    }

    public void viewProducts() {
        String sql = "SELECT * FROM Products";
        try (Connection conn = DBHelper.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\n--- Products List ---");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " | " +
                                   rs.getString("name") + " | " +
                                   rs.getDouble("price") + " | " +
                                   rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error viewing products: " + e.getMessage());
        }
    }
}
