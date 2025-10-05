package gui;
import helper.DBHelper;
import java.awt.*;
import java.sql.*;
import javax.swing.*;
import repository.MemberRepository;

public class MembersWindow extends JFrame {
    private final MemberRepository repo = new MemberRepository();

    private JTextArea outputArea;
    private JTextField idField, nameField, ageField, skillField;

    public MembersWindow() {
        setTitle("Kudumbashree - Members");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Input fields
        JPanel inputPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        inputPanel.add(new JLabel("ID:"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("Skill:"));
        skillField = new JTextField();
        inputPanel.add(skillField);

        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        // Output area
        outputArea = new JTextArea();
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 24));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add to frame
        setLayout(new BorderLayout(10, 10));
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        // Button actions
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String skill = skillField.getText();
            repo.addMember(name, age, skill);
            outputArea.append("Added member: " + name + "\n");
        });

        viewBtn.addActionListener(e -> {
            outputArea.append("\n--- Members ---\n");
            repo.viewMembers(); // prints to console
            outputArea.append("✅ Viewed members (check console)\n");
        });

        
         updateBtn.addActionListener(e -> {
        try {
        int id = Integer.parseInt(idField.getText());
        String name = nameField.getText();
        int age = Integer.parseInt(ageField.getText());
        String skill = skillField.getText();

        boolean success = repo.updateMember(id, name, age, skill); // ✅ check result
        if (success) {
            outputArea.append("✅ Updated member ID: " + id + "\n");
        } else {
            outputArea.append("❌ No member found with ID: " + id + "\n");
        }
    } catch (NumberFormatException ex) {
        outputArea.append("⚠️ Invalid input. Please enter correct values.\n");
    }
});

        deleteBtn.addActionListener(e -> {
            int id;
            try{
            id = Integer.parseInt(idField.getText());
            boolean success = repo.deleteMember(id);
            if (!success) {
                outputArea.append("❌ No member found with ID: " + id + "\n");
                return;
            }
            } catch (NumberFormatException ex) {
                outputArea.append("⚠️ Invalid ID. Please enter a numeric value.\n");
                return;
            }
            outputArea.append("Deleted member ID: " + id + "\n");
        });
    }
    // View ALL Members in GUI
public void viewMembers(JTextArea outputArea) {
    DBHelper dbHelper = new DBHelper();      
    String sql = "SELECT member_id, name, age, skill FROM Members";

    outputArea.setText(""); // clear old text first

    try (Connection conn = dbHelper.getConnection();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)) {

        outputArea.append("--- Members List ---\n");

        while (rs.next()) {
            String row = rs.getInt("member_id") + " | " +
                         rs.getString("name") + " | " +
                         rs.getInt("age") + " | " +
                         rs.getString("skill");
            outputArea.append(row + "\n");  // 🔹 Print to GUI TextArea
        }

    } catch (SQLException e) {
        outputArea.append("❌ Error viewing members: " + e.getMessage() + "\n");
    }
}

 
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MembersWindow().setVisible(true));
    }
}





