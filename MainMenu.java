package gui;
import java.awt.*;
import javax.swing.*;

public class MainMenu extends JFrame {

    public MainMenu() {
        // Window Title
        setTitle("Kudumbashree Data Entry App - Main Menu");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // Main Panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1, 10, 10));

        // Buttons
        JButton memberBtn = new JButton("👤 Manage Members");
        JButton meetingBtn = new JButton("📅 Manage Meetings");
        JButton passbookBtn = new JButton("💰 Manage Passbook");
        JButton productBtn = new JButton("📦 Manage Products");
        JButton exitBtn = new JButton("❌ Exit");

        // Add Buttons to Panel
        panel.add(memberBtn);
        panel.add(meetingBtn);
        panel.add(passbookBtn);
        panel.add(productBtn);
        panel.add(exitBtn);

        // --- Button Actions ---
        memberBtn.addActionListener(e -> new MembersWindow().setVisible(true));
        meetingBtn.addActionListener(e -> new MeetingsWindow().setVisible(true));
        passbookBtn.addActionListener(e -> new PassbooksWindow().setVisible(true));
        productBtn.addActionListener(e -> new ProductsWindow().setVisible(true));
        exitBtn.addActionListener(e -> System.exit(0));

        // Add Panel to Frame
        add(panel);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainMenu().setVisible(true);
        });
    }
}

