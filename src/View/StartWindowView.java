package View;

import Controller.BoardController;
import Controller.StartWindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindowView implements ActionListener {
    JFrame frame;
    private StartWindowController controller;

    public StartWindowView(StartWindowController controller) {
        this.controller = controller;
    }

    public JFrame drawStartWindowFrame() {
        frame = new JFrame("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);

        JPanel textPanel = new JPanel(new GridLayout(2,1));
        JLabel headline = new JLabel("Risk", JLabel.CENTER);
        headline.setFont(new Font(headline.getFont().getName(), Font.PLAIN, 20));
        JLabel choice = new JLabel("Choose a game board:", JLabel.CENTER);
        textPanel.add(headline);
        textPanel.add(choice);

        JPanel buttonPanel = new JPanel();
        JButton board1 = new JButton("Board 1");
        board1.addActionListener(this);
        board1.setActionCommand("board1");
        JButton board2 = new JButton("Board 2");
        board2.addActionListener(this);
        board2.setActionCommand("board2");
        JButton board3 = new JButton("Board 3");
        board3.addActionListener(this);
        board3.setActionCommand("board3");
        buttonPanel.add(board1);
        buttonPanel.add(board2);
        buttonPanel.add(board3);

        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);

        return frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("board1")) {
            controller.startGame("board1");
        }
        if (e.getActionCommand().equals("board2")) {
            controller.startGame("board2");
        }
        if (e.getActionCommand().equals("board3")) {
            controller.startGame("board3");
        }
    }
}
