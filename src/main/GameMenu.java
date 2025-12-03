package main;

import javax.swing.*;
import java.awt.*;

public class GameMenu extends JFrame {

    public GameMenu() {
        setTitle("Game Menu");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 1, 10, 10));

        JButton btnStart = new JButton("Start Game");
        JButton btnOptions = new JButton("Options");
        JButton btnExit = new JButton("Exit");

        // Add actions
        btnStart.addActionListener(e -> startGame());
        btnOptions.addActionListener(e -> showOptions());
        btnExit.addActionListener(e -> System.exit(0));

        panel.add(btnStart);
        panel.add(btnOptions);
        panel.add(btnExit);

        add(panel);
        setVisible(true);
    }

    public boolean startGame() {
        System.out.println("Starting game...");
        this.dispose();
        return true;
    }

    private void showOptions() {
        JOptionPane.showMessageDialog(this, "Options here");
    }
}
