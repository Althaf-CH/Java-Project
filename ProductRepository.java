package repository;
import helper.DBHelper;
import java.sql.*;
public class ProductRepository {

    public void addProduct(String name, double price, int quantity) {
        
        String sql = "INSERT INTO Products (name, price, quantity) VALUES (?, ?, ?)";
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection();
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
     String sql = "SELECT product_id, name, price, quantity FROM Products";
     DBHelper dbHelper = new DBHelper();
     try (Connection conn = dbHelper.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql);
         ResultSet rs = pstmt.executeQuery()) {
        System.out.println("\n--- Products List ---");
        while (rs.next()) {
            System.out.println(rs.getInt("product_id") + " | " +
                               rs.getString("name") + " | " +
                               rs.getDouble("price") + " | " +
                               rs.getInt("quantity"));
        }
    } catch (SQLException e) {
        System.out.println("❌ Error viewing products: " + e.getMessage());
    }
    }

        // 🔹 Update Product
      
    public boolean updateProduct(int product_id, String newName, double newPrice, int newQty) {
      String sql = "UPDATE Products SET name=?, price=?, quantity=? WHERE product_id=?";
      DBHelper dbHelper = new DBHelper();
    try (Connection conn = dbHelper.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setString(1, newName);
        pstmt.setDouble(2, newPrice);
        pstmt.setInt(3, newQty);
        pstmt.setInt(4, product_id);

        int affectedRows = pstmt.executeUpdate();
        if (affectedRows > 0) {
            System.out.println("✅ Product updated successfully!");
            return true;   // ✅ Updated
        } else {
            System.out.println("❌ No product found with the given ID.");
            return false;  // ❌ Not updated
        }
    } catch (SQLException e) {
        System.out.println("❌ Error updating product: " + e.getMessage());
        return false;
    }
}
    //Delete Product
    // 🔹 Delete Product
public void deleteProduct(int productId) {
    String sql = "DELETE FROM Products WHERE product_id=?";
    DBHelper dbHelper = new DBHelper();
    try (Connection conn = dbHelper.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, productId);
        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("✅ Product deleted successfully!");

            try ( // ✅ Reset auto-increment if table is empty
                    Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS cnt FROM Products")) {
                if (rs.next() && rs.getInt("cnt") == 0) {
                    stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='Products'");
                }
            }
        } else {
            System.out.println("❌ No product found with the given ID.");
        }
    } catch (SQLException e) {
        System.out.println("❌ Error deleting product: " + e.getMessage());
    }
}

   
    // 🔹 Search Products by Name
    public void searchProducts(String keyword) {
        String sql = "SELECT product_id, name, price, quantity FROM Products WHERE name LIKE ?";
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\n🔍 Search Results for '" + keyword + "':");
            while (rs.next()) {
                System.out.println(rs.getInt("product_id") + " | " +
                                   rs.getString("name") + " | ₹" +
                                   rs.getDouble("price") + " | Qty: " +
                                   rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println("❌ Error searching products: " + e.getMessage());
        }
    }

    // 🔹 Update Stock (increase/decrease quantity)
    public void updateStock(int productId, int qtyChange) {
        String sql = "UPDATE Products SET quantity = quantity + ? WHERE product_id=?";
         DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, qtyChange);
            pstmt.setInt(2, productId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                String msg = qtyChange > 0 ? "📦 Stock increased!" : "📦 Stock reduced!";
                System.out.println(msg);
            } else {
                System.out.println("⚠️ Product not found!");
            }
        } catch (SQLException e) {
            System.out.println("❌ Error updating stock: " + e.getMessage());
        }
    }
    // inside ProductRepository.java


}    