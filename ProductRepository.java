
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
            System.out.println("âœ… Product added successfully!");
        } catch (SQLException e) {
            System.out.println("âŒ Error adding product: " + e.getMessage());
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
        System.out.println("âŒ Error viewing products: " + e.getMessage());
    }
    }

        // ðŸ”¹ Update Product
      
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
            System.out.println("âœ… Product updated successfully!");
            return true;   // âœ… Updated
        } else {
            System.out.println("âŒ No product found with the given ID.");
            return false;  // âŒ Not updated
        }
    } catch (SQLException e) {
        System.out.println("âŒ Error updating product: " + e.getMessage());
        return false;
    }
}
    //Delete Product
    // ðŸ”¹ Delete Product
public void deleteProduct(int productId) {
    String sql = "DELETE FROM Products WHERE product_id=?";
    DBHelper dbHelper = new DBHelper();
    try (Connection conn = dbHelper.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, productId);
        int rows = pstmt.executeUpdate();

        if (rows > 0) {
            System.out.println("âœ… Product deleted successfully!");

            try ( // âœ… Reset auto-increment if table is empty
                    Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS cnt FROM Products")) {
                if (rs.next() && rs.getInt("cnt") == 0) {
                    stmt.executeUpdate("DELETE FROM sqlite_sequence WHERE name='Products'");
                }
            }
        } else {
            System.out.println("âŒ No product found with the given ID.");
        }
    } catch (SQLException e) {
        System.out.println("âŒ Error deleting product: " + e.getMessage());
    }
}

   
    // ðŸ”¹ Search Products by Name
    public void searchProducts(String keyword) {
        String sql = "SELECT product_id, name, price, quantity FROM Products WHERE name LIKE ?";
        DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("\nðŸ” Search Results for '" + keyword + "':");
            while (rs.next()) {
                System.out.println(rs.getInt("product_id") + " | " +
                                   rs.getString("name") + " | â‚¹" +
                                   rs.getDouble("price") + " | Qty: " +
                                   rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            System.out.println("âŒ Error searching products: " + e.getMessage());
        }
    }

    // ðŸ”¹ Update Stock (increase/decrease quantity)
    public void updateStock(int productId, int qtyChange) {
        String sql = "UPDATE Products SET quantity = quantity + ? WHERE product_id=?";
         DBHelper dbHelper = new DBHelper();
        try (Connection conn = dbHelper.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, qtyChange);
            pstmt.setInt(2, productId);
            int rows = pstmt.executeUpdate();
            if (rows > 0) {
                String msg = qtyChange > 0 ? "ðŸ“¦ Stock increased!" : "ðŸ“¦ Stock reduced!";
                System.out.println(msg);
            } else {
                System.out.println("âš ï¸ Product not found!");
            }
        } catch (SQLException e) {
            System.out.println("âŒ Error updating stock: " + e.getMessage());
        }
    }
    // inside ProductRepository.java


}
    