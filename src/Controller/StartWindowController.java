package Controller;

import View.StartWindowView;

import javax.swing.*;
import java.awt.*;

public class StartWindowController {
    JFrame startWindowFrame;

    Color playerOneColor;
    Color playerTwoColor;
    Color playerThreeColor;
    Color playerFourColor;
    String boardChoice;
    int numOfPlayers = 2;

    public void setPlayerOneColor(Color color) {
        playerOneColor = color;
    }
    public void setPlayerTwoColor(Color color) {
        playerTwoColor = color;
    }
    public void setPlayerThreeColor(Color color) {
        playerThreeColor = color;
    }
    public void setPlayerFourColor(Color color) {
        playerFourColor = color;
    }
    public void setBoardChoice(String choice) {
        boardChoice = choice;
    }
    public void setNumOfPlayers(int num) {
        numOfPlayers = num;
    }
    public boolean boardChosen() {
        return boardChoice != null;
    }
    public boolean colorsSet() {
        return playerOneColor != null && playerTwoColor != null;
    }

    public void createStartWindow() {
        startWindowFrame = new StartWindowView(this).drawStartWindowFrame();
    }

    public void startGame(String playerOneName, String playerTwoName, String playerThreeName, String playerFourName, boolean missionsEnabled) {
        BoardController board = new BoardController(boardChoice, numOfPlayers, playerOneName, playerTwoName, playerThreeName, playerFourName,
                                                    playerOneColor, playerTwoColor, playerThreeColor, playerFourColor, missionsEnabled);
        board.createBoardView();

        startWindowFrame.dispose();
    }

    public static void main(String[] args) {
        StartWindowController startWindowController = new StartWindowController();
        startWindowController.createStartWindow();

    }

}
