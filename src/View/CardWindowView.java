package View;

import Config.Helper;
import Controller.CardWindowController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CardWindowView implements ActionListener {
    public static final int CARD_HEADLINE_HEIGHT = 80;
    public static final int CARD_BUTTONS_HEIGHT = 50;
    public static final int CARD_OUTPUT_HEIGHT = 80;
    public static final int CARD_COLUMN_WIDTH = 50;

    GridBagLayout cardLayout = new GridBagLayout();
    GridBagConstraints cardConstraints = new GridBagConstraints();
    JPanel cardPanel = new JPanel(cardLayout);

    JDialog frame;

    BoardView parent;
    CardWindowController cardController;

    public CardWindowView(BoardView parent, CardWindowController cardController) {
        this.parent = parent;
        this.cardController = cardController;
    }

    public void createCardWindow() {
        frame = new JDialog(parent, "Card Window", true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                if (parent.boardController.getCurrentPlayer().getAllCardsSize() > 5) {
                    frame.dispose();
                    JOptionPane.showMessageDialog(frame, "You have more than 5 cards!\nPlease exchange them into armies.");
                    createCardWindow();
                }
            }
        });

        cardLayout.rowHeights = new int[]{CARD_HEADLINE_HEIGHT, CARD_BUTTONS_HEIGHT, CARD_OUTPUT_HEIGHT};
        cardLayout.columnWidths = new int[]{CARD_COLUMN_WIDTH, CARD_COLUMN_WIDTH, CARD_COLUMN_WIDTH, CARD_COLUMN_WIDTH, CARD_COLUMN_WIDTH, CARD_COLUMN_WIDTH};

        JLabel headlineLabel = new JLabel("How do you want to use your cards?", JLabel.CENTER);

        JButton infantryButton = new JButton("3x Infantry");
        infantryButton.addActionListener(this);
        infantryButton.setActionCommand("Infantry");
        infantryButton.setEnabled(cardController.checkThreeCardCombination("Infantry"));

        JButton cavalryButton = new JButton("3x Cavalry");
        cavalryButton.addActionListener(this);
        cavalryButton.setActionCommand("Cavalry");
        cavalryButton.setEnabled(cardController.checkThreeCardCombination("Cavalry"));

        JButton artilleryButton = new JButton("3x Artillery");
        artilleryButton.addActionListener(this);
        artilleryButton.setActionCommand("Artillery");
        artilleryButton.setEnabled(cardController.checkThreeCardCombination("Artillery"));

        JButton oneOfEachButton = new JButton("One of each");
        oneOfEachButton.addActionListener(this);
        oneOfEachButton.setActionCommand("oneOfEachCard");
        oneOfEachButton.setEnabled(cardController.checkOneOfEachCard());

        JLabel outPutLabel = new JLabel("Output:", JLabel.CENTER);
        JLabel outPutInfantry = new JLabel("4 Armies", JLabel.CENTER);
        JLabel outPutCavalry = new JLabel("5 Armies", JLabel.CENTER);
        JLabel outPutArtillery = new JLabel("6 Armies", JLabel.CENTER);
        JLabel outPutOneOfEach = new JLabel("5 Armies", JLabel.CENTER);

        cardPanel.add(headlineLabel, Helper.buildBoardConstraints(cardConstraints, 0,0,1,5));
        cardPanel.add(infantryButton, Helper.buildBoardConstraints(cardConstraints, 1,1,1,1));
        cardPanel.add(cavalryButton, Helper.buildBoardConstraints(cardConstraints, 1,2,1,1));
        cardPanel.add(artilleryButton, Helper.buildBoardConstraints(cardConstraints, 1,3,1,1));
        cardPanel.add(oneOfEachButton, Helper.buildBoardConstraints(cardConstraints, 1,4,1,1));
        cardPanel.add(outPutLabel, Helper.buildBoardConstraints(cardConstraints, 2,0,1,1));
        cardPanel.add(outPutInfantry, Helper.buildBoardConstraints(cardConstraints, 2,1,1,1));
        cardPanel.add(outPutCavalry, Helper.buildBoardConstraints(cardConstraints, 2,2,1,1));
        cardPanel.add(outPutArtillery, Helper.buildBoardConstraints(cardConstraints, 2,3,1,1));
        cardPanel.add(outPutOneOfEach, Helper.buildBoardConstraints(cardConstraints, 2,4,1,1));

        frame.setContentPane(cardPanel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Infantry")) {
            cardController.playerSetCardsPhase("Infantry");
        }
        if(e.getActionCommand().equals("Cavalry")) {
            cardController.playerSetCardsPhase("Cavalry");
        }
        if(e.getActionCommand().equals("Artillery")) {
            cardController.playerSetCardsPhase("Artillery");
        }
        if(e.getActionCommand().equals("oneOfEachCard")) {
            cardController.playerSetCardsPhase("oneOfEachCard");
        }
        frame.dispose();
    }
}
