package Controller;

import View.StartWindowView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartWindowController {
    JFrame startWindowFrame;


    public void createStartWindow() {
        startWindowFrame = new StartWindowView(this).drawStartWindowFrame();
    }

    public void startGame(String boardChoice) {
        BoardController board = new BoardController(boardChoice);
        board.createBoardView();

        startWindowFrame.dispose();
    }

    public static void main(String[] args) {
        StartWindowController startWindowController = new StartWindowController();
        startWindowController.createStartWindow();

    }

}
