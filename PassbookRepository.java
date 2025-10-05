package gui;
import java.awt.*;
import javax.swing.*;
import repository.PassbookRepository;


public class PassbooksWindow extends JFrame {
    public final PassbookRepository passbookRepo = new PassbookRepository();

    // Output area
    private final JTextArea outputArea = new JTextArea();

    // Input fields
    private final JTextField entryIdField = new JTextField(5);
    private final JTextField memberIdField = new JTextField(5);
    private final JTextField dateField = new JTextField(10);
    private final JTextField amountField = new JTextField(7);

    public PassbooksWindow() {
        setTitle("Passbook Manager");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Top panel for inputs
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputPanel.add(new JLabel("Entry ID:"));
        inputPanel.add(entryIdField);
        inputPanel.add(new JLabel("Member ID:"));
        inputPanel.add(memberIdField);
        inputPanel.add(new JLabel("Date (YYYY-MM-DD):"));
        inputPanel.add(dateField);
        inputPanel.add(new JLabel("Amount:"));
        inputPanel.add(amountField);
        add(inputPanel, BorderLayout.NORTH);

        // 🔹 Center panel for output
        outputArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        add(scrollPane, BorderLayout.CENTER);

        // 🔹 Bottom panel for buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton addBtn = new JButton("Add Entry");
        JButton viewBtn = new JButton("View Entries");
        JButton updateBtn = new JButton("Update Entry");
        JButton deleteBtn = new JButton("Delete Entry");
        JButton totalBtn = new JButton("Total Balance");
        JButton memberBtn = new JButton("Member Balance");

        // Small buttons
        Dimension smallBtn = new Dimension(120, 25);
        addBtn.setPreferredSize(smallBtn);
        viewBtn.setPreferredSize(smallBtn);
        updateBtn.setPreferredSize(smallBtn);
        deleteBtn.setPreferredSize(smallBtn);
        totalBtn.setPreferredSize(smallBtn);
        memberBtn.setPreferredSize(smallBtn);

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(totalBtn);
        buttonPanel.add(memberBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        // 🔹 Button actions

        // Add Entry
        addBtn.addActionListener(e -> {
            String memberText = memberIdField.getText().trim();
            String date = dateField.getText().trim();
            String amountText = amountField.getText().trim();
            if (!memberText.isEmpty() && !date.isEmpty() && !amountText.isEmpty()) {
                try {
                    int memberId = Integer.parseInt(memberText);
                    double amount = Double.parseDouble(amountText);
                    passbookRepo.addEntry(memberId, date, amount);
                    String msg = "✅ Added Entry: Member " + memberId + " | " + date + " | " + amount;
                    System.out.println(msg);
                    outputArea.append(msg + "\n");
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Member ID and Amount must be numbers!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill Member ID, Date, and Amount!");
            }
        });

        // View Entries
        viewBtn.addActionListener(e -> {
            outputArea.setText("");
            try {
                passbookRepo.viewEntriesForGUI(outputArea);
            } catch (Exception ex) {
                String msg = "❌ Error viewing entries: " + ex.getMessage();
                System.out.println(msg);
                outputArea.append(msg + "\n");
            }
        });

        // Update Entry
        updateBtn.addActionListener(e -> {
            String entryText = entryIdField.getText().trim();
            String memberText = memberIdField.getText().trim();
            String date = dateField.getText().trim();
            String amountText = amountField.getText().trim();
            if (!entryText.isEmpty() && !memberText.isEmpty() && !date.isEmpty() && !amountText.isEmpty()) {
                try {
                    int entryId = Integer.parseInt(entryText);
                    int memberId = Integer.parseInt(memberText);
                    double amount = Double.parseDouble(amountText);
                    passbookRepo.updateEntry(entryId, memberId, date, amount);
                    String msg = "✏️ Updated Entry ID " + entryId;
                    System.out.println(msg);
                    outputArea.append(msg + "\n");
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Entry ID, Member ID, and Amount must be numbers!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please fill Entry ID, Member ID, Date, and Amount!");
            }
        });

        // Delete Entry
        deleteBtn.addActionListener(e -> {
            String entryText = entryIdField.getText().trim();
            if (!entryText.isEmpty()) {
                try {
                    int entryId = Integer.parseInt(entryText);
                    passbookRepo.deleteEntry(entryId);
                    String msg = "❌ Deleted Entry ID " + entryId;
                    System.out.println(msg);
                    outputArea.append(msg + "\n");
                    clearFields();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Entry ID must be a number!");
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter Entry ID!");
            }
        });

        // Total Balance
        totalBtn.addActionListener(e -> {
            outputArea.setText("");
            passbookRepo.getTotalBalanceForGUI(outputArea);
        });

        // Member Balance
        memberBtn.addActionListener(e -> {
    try {
        int id = Integer.parseInt(memberIdField.getText());
        Double balance = passbookRepo.getMemberBalance(id);

        if (balance == null) {
            outputArea.append("❌ No member found with ID: " + id + "\n");
        } else {
            outputArea.append("💰 Balance for Member " + id + ": " + balance + "\n");
        }
    } catch (NumberFormatException ex) {
        outputArea.append("⚠️ Invalid ID input.\n");
    }
});

        setVisible(true);
    }

    private void clearFields() {
        entryIdField.setText("");
        memberIdField.setText("");
        dateField.setText("");
        amountField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PassbooksWindow::new);
    }
}
