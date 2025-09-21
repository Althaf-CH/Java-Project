package gui;

import repository.MemberRepository;
import java.awt.*;
import javax.swing.*;

public class MembersWindow extends JFrame {
    private JTextField nameField, ageField, skillField, idField;
    private JTextArea outputArea;
    private MemberRepository repo;

    public MembersWindow() {
        repo = new MemberRepository();

        setTitle("Members Management");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🔹 Input panel
        JPanel inputPanel = new JPanel(new GridLayout(4, 2));
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

        add(inputPanel, BorderLayout.NORTH);

        // 🔹 Buttons
        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        add(buttonPanel, BorderLayout.CENTER);

        // 🔹 Output area
        outputArea = new JTextArea(8, 50);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        // 🔹 Button Actions
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String skill = skillField.getText();
            repo.addMember(name, age, skill);
            outputArea.setText("✅ Member added!");
        });

        viewBtn.addActionListener(e -> {
            outputArea.setText("");
            repo.viewMembersGUI(outputArea);  // new method in repo
        });

        updateBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String skill = skillField.getText();
            repo.updateMember(id, name, age, skill);
            outputArea.setText("✏️ Member updated!");
        });

        deleteBtn.addActionListener(e -> {
            int id = Integer.parseInt(idField.getText());
            repo.deleteMember(id);
            outputArea.setText("🗑️ Member deleted!");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MembersWindow().setVisible(true));
    }
}


