package View;

import Controller.StartWindowController;
import Config.Helper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class StartWindowView implements ActionListener {
    public static final int HEADLINE_HEIGHT = 50;
    public static final int NUM_OF_PLAYER_HEIGHT = 25;
    public static final int MISSION_HEIGHT = 25;
    public static final int NAME_HEIGHT = 150;
    public static final int BOARD_CHOICE_HEIGHT = 150;
    public static final int PLAYER_NAME_WIDTH = 250;

    Color[][] allPlayerColors = new Color[][]{
            // Player One
            new Color[]
                    {
                    new Color(61, 194, 87),
                    new Color(60, 101, 195),
                    new Color(197, 60, 58),
            },
            // Player Two
            new Color[] {
                    new Color(194, 61, 168),
                    new Color(195, 154, 60),
                    new Color(58, 195, 197),
            },
            // Player Three
            new Color[] {
                    new Color(106, 11, 222),
                    new Color(224, 221, 2),
                    new Color(97, 96, 49),
            },
            // Player Four
            new Color[] {
                    new Color(225, 99, 128),
                    new Color(99, 224, 170),
                    new Color(108, 139, 113)
            }

    };

    JFrame frame;

    JCheckBox missionCheck;
    JTextField playerOneName;
    JTextField playerTwoName;
    JTextField playerThreeName;
    JTextField playerFourName;
    JButton playerOneColorButton1;
    JButton playerOneColorButton2;
    JButton playerOneColorButton3;
    JButton playerTwoColorButton1;
    JButton playerTwoColorButton2;
    JButton playerTwoColorButton3;
    JButton playerThreeColorButton1;
    JButton playerThreeColorButton2;
    JButton playerThreeColorButton3;
    JButton playerFourColorButton1;
    JButton playerFourColorButton2;
    JButton playerFourColorButton3;

    JButton board1;
    JButton board2;
    JButton board3;

    GridBagLayout startWindowLayout = new GridBagLayout();
    GridBagConstraints startWindowConstraints = new GridBagConstraints();
    JPanel startWindowPanel = new JPanel(startWindowLayout);

    private final StartWindowController controller;

    public StartWindowView(StartWindowController controller) {
        this.controller = controller;
    }

    public JFrame drawStartWindowFrame() {
        frame = new JFrame("Risk");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400,400);
        frame.setResizable(false);

        startWindowLayout.rowHeights = new int[] { HEADLINE_HEIGHT, NUM_OF_PLAYER_HEIGHT, MISSION_HEIGHT, NAME_HEIGHT, NAME_HEIGHT, BOARD_CHOICE_HEIGHT};
        startWindowLayout.columnWidths = new int[] { PLAYER_NAME_WIDTH, PLAYER_NAME_WIDTH};

        startWindowPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));

        JLabel headline = new JLabel("Risk", JLabel.CENTER);
        headline.setFont(new Font(headline.getFont().getName(), Font.PLAIN, 20));

        JPanel playerNumberPanel = new JPanel(new GridLayout(1,2));
        JLabel playerQuestion = new JLabel("How many Players:", JLabel.CENTER);
        ButtonGroup playerNumberGroup = new ButtonGroup();
        JRadioButton twoPlayers = new JRadioButton("Two", true);
        twoPlayers.addActionListener(this);
        twoPlayers.setActionCommand("twoPlayers");
        JRadioButton threePlayers = new JRadioButton("Three");
        threePlayers.addActionListener(this);
        threePlayers.setActionCommand("threePlayers");
        JRadioButton fourPlayers = new JRadioButton("Four");
        fourPlayers.addActionListener(this);
        fourPlayers.setActionCommand("fourPlayers");
        playerNumberGroup.add(twoPlayers);
        playerNumberGroup.add(threePlayers);
        playerNumberGroup.add(fourPlayers);
        playerNumberPanel.add(playerQuestion);
        playerNumberPanel.add(twoPlayers);
        playerNumberPanel.add(threePlayers);
        playerNumberPanel.add(fourPlayers);

        JPanel missionPanel = new JPanel(new GridLayout(1,2));
        JLabel missionQuestion = new JLabel("Missions?", JLabel.CENTER);
        missionCheck = new JCheckBox("", false);
        missionCheck.addItemListener(e -> missionCheck.setSelected(e.getStateChange() == ItemEvent.SELECTED));
        missionPanel.add(missionQuestion);
        missionPanel.add(missionCheck);

        JPanel playerOnePanel = new JPanel(new GridLayout(4, 1));
        playerOnePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel playerOneHeadline = new JLabel("Player One Name: ", JLabel.CENTER);
        playerOneName = new JTextField();
        JLabel playerOneColorHeadline = new JLabel("Choose Player One Color: ", JLabel.CENTER);
        JPanel playerOneColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerOneColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerOneColorButton1 = createButton("", allPlayerColors[0][0], "playerOneColorButton1");
        playerOneColorButton2 = createButton("", allPlayerColors[0][1], "playerOneColorButton2");
        playerOneColorButton3 = createButton("", allPlayerColors[0][2], "playerOneColorButton3");
        playerOneColorButtonPanel.add(playerOneColorButton1);
        playerOneColorButtonPanel.add(playerOneColorButton2);
        playerOneColorButtonPanel.add(playerOneColorButton3);
        playerOnePanel.add(playerOneHeadline);
        playerOnePanel.add(playerOneName);
        playerOnePanel.add(playerOneColorHeadline);
        playerOnePanel.add(playerOneColorButtonPanel);

        JPanel playerTwoPanel = new JPanel(new GridLayout(4, 1));
        playerTwoPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel playerTwoHeadline = new JLabel("Player Two Name: ", JLabel.CENTER);
        playerTwoName = new JTextField();
        JLabel playerTwoColorHeadline = new JLabel("Choose Player Two Color: ", JLabel.CENTER);
        JPanel playerTwoColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerTwoColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerTwoColorButton1 = createButton("", allPlayerColors[1][0], "playerTwoColorButton1");
        playerTwoColorButton2 = createButton("", allPlayerColors[1][1], "playerTwoColorButton2");
        playerTwoColorButton3 = createButton("", allPlayerColors[1][2], "playerTwoColorButton3");
        playerTwoColorButtonPanel.add(playerTwoColorButton1);
        playerTwoColorButtonPanel.add(playerTwoColorButton2);
        playerTwoColorButtonPanel.add(playerTwoColorButton3);
        playerTwoPanel.add(playerTwoHeadline);
        playerTwoPanel.add(playerTwoName);
        playerTwoPanel.add(playerTwoColorHeadline);
        playerTwoPanel.add(playerTwoColorButtonPanel);

        JPanel playerThreePanel = new JPanel(new GridLayout(4, 1));
        playerThreePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel playerThreeHeadline = new JLabel("Player Three Name: ", JLabel.CENTER);
        playerThreeName = new JTextField();
        playerThreeName.setEnabled(false);
        JLabel playerThreeColorHeadline = new JLabel("Choose Player Three Color: ", JLabel.CENTER);
        JPanel playerThreeColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerThreeColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerThreeColorButton1 = createButton("", allPlayerColors[2][0], "playerThreeColorButton1");
        playerThreeColorButton2 = createButton("", allPlayerColors[2][1], "playerThreeColorButton2");
        playerThreeColorButton3 = createButton("", allPlayerColors[2][2], "playerThreeColorButton3");
        playerThreeColorButtonPanel.add(playerThreeColorButton1);
        playerThreeColorButtonPanel.add(playerThreeColorButton2);
        playerThreeColorButtonPanel.add(playerThreeColorButton3);
        playerThreePanel.add(playerThreeHeadline);
        playerThreePanel.add(playerThreeName);
        playerThreePanel.add(playerThreeColorHeadline);
        playerThreePanel.add(playerThreeColorButtonPanel);

        JPanel playerFourPanel = new JPanel(new GridLayout(4, 1));
        playerFourPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        JLabel playerFourHeadline = new JLabel("Player Four Name: ", JLabel.CENTER);
        playerFourName = new JTextField();
        playerFourName.setEnabled(false);
        JLabel playerFourColorHeadline = new JLabel("Choose Player Four Color: ", JLabel.CENTER);
        JPanel playerFourColorButtonPanel = new JPanel(new GridLayout(1, 3));
        playerFourColorButtonPanel.setBorder(BorderFactory.createEmptyBorder(0,10,2,10));
        playerFourColorButton1 = createButton("", allPlayerColors[3][0], "playerFourColorButton1");
        playerFourColorButton2 = createButton("", allPlayerColors[3][1], "playerFourColorButton2");
        playerFourColorButton3 = createButton("", allPlayerColors[3][2], "playerFourColorButton3");
        playerFourColorButtonPanel.add(playerFourColorButton1);
        playerFourColorButtonPanel.add(playerFourColorButton2);
        playerFourColorButtonPanel.add(playerFourColorButton3);
        playerFourPanel.add(playerFourHeadline);
        playerFourPanel.add(playerFourName);
        playerFourPanel.add(playerFourColorHeadline);
        playerFourPanel.add(playerFourColorButtonPanel);


        JPanel boardChoicePanel = new JPanel(new GridLayout(3, 1));
        JLabel choice = new JLabel("Choose a game board:", JLabel.CENTER);
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(0,20,10,20));
        board1 = createButton("Board 1", null, "board1");
        board2 = createButton("Board 2", null, "board2");
        board3 = createButton("Board 3", null, "board3");

        JButton startButton = new JButton("Start Game");
        startButton.addActionListener(this);
        startButton.setActionCommand("startButton");
        buttonPanel.add(board1);
        buttonPanel.add(board2);
        buttonPanel.add(board3);
        boardChoicePanel.add(choice);
        boardChoicePanel.add(buttonPanel);
        boardChoicePanel.add(startButton);

        startWindowPanel.add(headline, Helper.buildBoardConstraints(startWindowConstraints, 0, 0, 1, 2));
        startWindowPanel.add(playerNumberPanel, Helper.buildBoardConstraints(startWindowConstraints, 1,0,1,2));
        startWindowPanel.add(missionPanel, Helper.buildBoardConstraints(startWindowConstraints, 2,0,1,1));
        startWindowPanel.add(playerOnePanel, Helper.buildBoardConstraints(startWindowConstraints, 3, 0, 1, 1));
        startWindowPanel.add(playerTwoPanel, Helper.buildBoardConstraints(startWindowConstraints, 3, 1, 1, 1));
        startWindowPanel.add(playerThreePanel, Helper.buildBoardConstraints(startWindowConstraints, 4, 0, 1, 1));
        startWindowPanel.add(playerFourPanel, Helper.buildBoardConstraints(startWindowConstraints, 4, 1, 1, 1));
        startWindowPanel.add(boardChoicePanel, Helper.buildBoardConstraints(startWindowConstraints, 5, 0, 1, 2));

        frame.setContentPane(startWindowPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        return frame;
    }

    public JButton createButton(String text, Color color, String actionCommand) {
        JButton button = new JButton(text);
        button.setBackground(color);
        button.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        button.addActionListener(this);
        button.setActionCommand(actionCommand);

        return button;
    }

    public void addButtonColors() {
        JButton[] allColorButtons = new JButton[] {
                playerOneColorButton1, playerOneColorButton2, playerOneColorButton3,
                playerTwoColorButton1, playerTwoColorButton2, playerTwoColorButton3,
                playerThreeColorButton1, playerThreeColorButton2, playerThreeColorButton3,
                playerFourColorButton1, playerFourColorButton2, playerFourColorButton3};


    }

    // Highlights the first Button in given Array
    public void highlightButton(JButton activeButton, JButton notActiveButton1, JButton notActiveButton2) {
        notActiveButton1.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        notActiveButton2.setBorder(BorderFactory.createLineBorder(new Color(228, 229, 227), 2));
        activeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()){
            case "twoPlayers":
                controller.setNumOfPlayers(2);
                playerThreeName.setEnabled(false);
                playerFourName.setEnabled(false);
                break;
            case "threePlayers":
                controller.setNumOfPlayers(3);
                playerThreeName.setEnabled(true);
                playerFourName.setEnabled(false);
                break;
            case "fourPlayers":
                controller.setNumOfPlayers(4);
                playerThreeName.setEnabled(true);
                playerFourName.setEnabled(true);
                break;
            case "playerOneColorButton1":
                highlightButton(playerOneColorButton1, playerOneColorButton2, playerOneColorButton3);
                controller.setPlayerOneColor(allPlayerColors[0][0]);
                break;
            case "playerOneColorButton2":
                highlightButton(playerOneColorButton2, playerOneColorButton1, playerOneColorButton3);
                controller.setPlayerOneColor(allPlayerColors[0][1]);
                break;
            case "playerOneColorButton3":
                highlightButton(playerOneColorButton3, playerOneColorButton1, playerOneColorButton2);
                controller.setPlayerOneColor(allPlayerColors[0][2]);
                break;
            case "playerTwoColorButton1":
                highlightButton(playerTwoColorButton1, playerTwoColorButton2, playerTwoColorButton3);
                controller.setPlayerTwoColor(allPlayerColors[1][0]);
                break;
            case "playerTwoColorButton2":
                highlightButton(playerTwoColorButton2, playerTwoColorButton1, playerTwoColorButton3);
                controller.setPlayerTwoColor(allPlayerColors[1][1]);
                break;
            case "playerTwoColorButton3":
                highlightButton(playerTwoColorButton3, playerTwoColorButton1, playerTwoColorButton2);
                controller.setPlayerTwoColor(allPlayerColors[1][2]);
                break;
            case "playerThreeColorButton1":
                highlightButton(playerThreeColorButton1, playerThreeColorButton2, playerThreeColorButton3);
                controller.setPlayerThreeColor(allPlayerColors[2][0]);
                break;
            case "playerThreeColorButton2":
                highlightButton(playerThreeColorButton2, playerThreeColorButton1, playerThreeColorButton3);
                controller.setPlayerThreeColor(allPlayerColors[2][1]);
                break;
            case "playerThreeColorButton3":
                highlightButton(playerThreeColorButton3, playerThreeColorButton1, playerThreeColorButton2);
                controller.setPlayerThreeColor(allPlayerColors[2][2]);
                break;
            case "playerFourColorButton1":
                highlightButton(playerFourColorButton1, playerFourColorButton2, playerFourColorButton3);
                controller.setPlayerFourColor(allPlayerColors[3][0]);
                break;
            case "playerFourColorButton2":
                highlightButton(playerFourColorButton2, playerFourColorButton1, playerFourColorButton3);
                controller.setPlayerFourColor(allPlayerColors[3][1]);
                break;
            case "playerFourColorButton3":
                highlightButton(playerFourColorButton3, playerFourColorButton1, playerFourColorButton2);
                controller.setPlayerFourColor(allPlayerColors[3][2]);
                break;
            case "board1":
                highlightButton(board1, board2, board3);
                controller.setBoardChoice("board1");
                break;
            case "board2":
                highlightButton(board2, board1, board3);
                controller.setBoardChoice("board2");
                break;
            case "board3":
                highlightButton(board3, board1, board2);
                controller.setBoardChoice("board3");
                break;
            case "startButton":
                if(controller.colorsSet() &&
                    controller.boardChosen() &&
                    !playerOneName.getText().isBlank() &&
                    !playerTwoName.getText().isBlank() &&
                    !playerOneName.getText().equals(playerTwoName.getText())) {

                    controller.startGame(playerOneName.getText(), playerTwoName.getText(), playerThreeName.getText(), playerFourName.getText(), missionCheck.isSelected());
                }
        }
    }
}
