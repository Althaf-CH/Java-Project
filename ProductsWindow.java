package gui;
import helper.DBHelper;
import java.awt.*;
import java.sql.SQLException;
import javax.swing.*;
import repository.ProductRepository;

public class ProductsWindow extends JFrame {
    private final ProductRepository productRepo = new ProductRepository();
    private final JTextArea outputArea;

    public ProductsWindow() {
        setTitle("Products Management");
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        // ===== Output Area =====
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // ===== Buttons Panel =====
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");
        JButton searchBtn = new JButton("Search");
        JButton stockBtn = new JButton("Update Stock");

        Dimension btnSize = new Dimension(100, 30);
        addBtn.setPreferredSize(btnSize);
        viewBtn.setPreferredSize(btnSize);
        updateBtn.setPreferredSize(btnSize);
        deleteBtn.setPreferredSize(btnSize);
        searchBtn.setPreferredSize(btnSize);
        stockBtn.setPreferredSize(btnSize);

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchBtn);
        buttonPanel.add(stockBtn);

        add(buttonPanel, BorderLayout.NORTH);

        // ===== Button Actions =====
        addBtn.addActionListener(e -> addProduct());
        viewBtn.addActionListener(e -> viewProducts());
        updateBtn.addActionListener(e -> updateProduct());
        deleteBtn.addActionListener(e -> deleteProduct());
        searchBtn.addActionListener(e -> searchProduct());
        stockBtn.addActionListener(e -> updateStock());
    }

    // ===== Methods for Buttons =====
    private void addProduct() {
        try {
            String name = JOptionPane.showInputDialog(this, "Enter Product Name:");
            String priceStr = JOptionPane.showInputDialog(this, "Enter Price:");
            String qtyStr = JOptionPane.showInputDialog(this, "Enter Quantity:");
            if (name != null && priceStr != null && qtyStr != null) {
                double price = Double.parseDouble(priceStr);
                int qty = Integer.parseInt(qtyStr);
                productRepo.addProduct(name, price, qty);
                outputArea.append("✅ Product added: " + name + " | ₹" + price + " | Qty: " + qty + "\n");
            }
        } catch (NumberFormatException ex) {
            outputArea.append("❌ Invalid input for price or quantity.\n");
        }
    }

    private void viewProducts() {
        outputArea.append("\n--- Products List ---\n");
        try {
            StringBuilder sb = new StringBuilder();
            DBHelper dbHelper = new DBHelper();
            try (java.sql.Connection conn = dbHelper.getConnection(); java.sql.PreparedStatement ps = conn.prepareStatement("SELECT product_id, name, price, quantity FROM Products"); java.sql.ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sb.append(rs.getInt("product_id")).append(" | ")
                            .append(rs.getString("name")).append(" | ₹")
                            .append(rs.getDouble("price")).append(" | Qty: ")
                            .append(rs.getInt("quantity")).append("\n");
                }
                outputArea.append(sb.toString());
 }
        } catch (SQLException e) {
            outputArea.append("❌ Error fetching products: " + e.getMessage() + "\n");
        }
    }

    private void updateProduct() {
        try {
           

            String idStr = JOptionPane.showInputDialog(this, "Enter Product ID to Update:");
            String name = JOptionPane.showInputDialog(this, "Enter New Name:");
            String priceStr = JOptionPane.showInputDialog(this, "Enter New Price:");
            String qtyStr = JOptionPane.showInputDialog(this, "Enter New Quantity:");
            if (idStr != null && name != null && priceStr != null && qtyStr != null) {
                int id = Integer.parseInt(idStr);
                double price = Double.parseDouble(priceStr);
                int qty = Integer.parseInt(qtyStr);
                productRepo.updateProduct(id, name, price, qty);
                boolean success=productRepo.updateProduct(id, name, price, qty);
                if(success) {
                    outputArea.append("✏️ Product updated: ID " + id + "\n");
                } else {
                    outputArea.append("❌ No product found with ID " + id + "\n");
                }   

            }
            
        } catch (NumberFormatException ex) {
            outputArea.append("❌ Invalid input.\n");
        
         
            }
}

    private void deleteProduct() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter Product ID to Delete:");
            if (idStr != null) {
                int id = Integer.parseInt(idStr);
                productRepo.deleteProduct(id);
                outputArea.append("❌ Product deleted: ID " + id + "\n");
            }
        } catch (NumberFormatException ex) {
            outputArea.append("❌ Invalid Product ID.\n");
        }
    }

    private void searchProduct() {
        String keyword = JOptionPane.showInputDialog(this, "Enter Product Name to Search:");
        if (keyword != null && !keyword.isEmpty()) {
            try {
                StringBuilder sb = new StringBuilder();
                DBHelper dbHelper = new DBHelper();
                try (java.sql.Connection conn = dbHelper.getConnection(); java.sql.PreparedStatement ps = conn.prepareStatement("SELECT product_id, name, price, quantity FROM Products WHERE name LIKE ?")) {
                    ps.setString(1, "%" + keyword + "%");
                    try (java.sql.ResultSet rs = ps.executeQuery()) {
                        sb.append("\n🔍 Search Results for '").append(keyword).append("':\n");
                        while (rs.next()) {
                            sb.append(rs.getInt("product_id")).append(" | ")
                                    .append(rs.getString("name")).append(" | ₹")
                                    .append(rs.getDouble("price")).append(" | Qty: ")
                                    .append(rs.getInt("quantity")).append("\n");
                        }
                        outputArea.append(sb.toString());
                    }
 }
            } catch (SQLException e) {
                outputArea.append("❌ Error searching products: " + e.getMessage() + "\n");
            }
        }
    }

    private void updateStock() {
        try {
            String idStr = JOptionPane.showInputDialog(this, "Enter Product ID:");
            String qtyStr = JOptionPane.showInputDialog(this, "Enter Quantity Change (+/-):");
            if (idStr != null && qtyStr != null) {
                int id = Integer.parseInt(idStr);
                int qty = Integer.parseInt(qtyStr);
                productRepo.updateStock(id, qty);
                String msg = qty > 0 ? "📦 Stock increased for Product ID " + id : "📦 Stock reduced for Product ID " + id;
                outputArea.append(msg + "\n");
            }
        } catch (NumberFormatException ex) {
            outputArea.append("❌ Invalid input.\n");
        }
    }

    // ===== Main =====
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ProductsWindow window = new ProductsWindow();
            window.setVisible(true);
        });
    }
}
