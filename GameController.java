package gui;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import mod.Computer;
import mod.Player;

import javax.swing.*;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    //INSTANCE VARIABLES
    private Player player1;
    private Player player2;
    private Player curPlayer;
    private Computer comp;
    private boolean twoPlayerGame;
    @FXML
    Label winsLab;
    @FXML
    Label playerLabel;
    @FXML
    AnchorPane pane;
    @FXML
    RadioButton onePlayer;
    @FXML
    RadioButton twoPlayer;
    @FXML
    Label p1Score;
    @FXML
    Label p2Score;
    @FXML
    RadioButton rock;
    @FXML
    RadioButton paper;
    @FXML
    RadioButton scissors;
    @FXML
    Button selectBtn;
    @FXML
    Button startBtn;
    @FXML
    ImageView rockImg;
    @FXML
    ImageView paperImg;
    @FXML
    ImageView scissorsImg;
    @FXML
    Button exitBtn;

    /*
     *Sets the ImageView instance variables to be the appropriate pictures.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        rockImg.setImage(new Image("rock.jpg"));
        paperImg.setImage(new Image("paper.jpg"));
        scissorsImg.setImage(new Image("scissor.jpg"));
    }


    /*
     *Checks if there will be one or two players. If there are two, then it initializes the two
     * player variables. If there is one, then it initializes player1 and the computer variable.
     * Then sets the buttons for selecting how many players there are to be invisible and sets
     * all of the other gui elements to be visible.
     */
    @FXML
    private void start() {
        if(!onePlayer.isSelected() && !twoPlayer.isSelected()) {
            return;
        }
        else if(onePlayer.isSelected()) {
            player1 = new Player();
            comp = new Computer();
            player2 = null;
            twoPlayerGame = false;
        }
        else if(twoPlayer.isSelected()) {
            player1 = new Player();
            player2 = new Player();
            comp = null;
            twoPlayerGame = true;
        }

        onePlayer.setVisible(false);
        twoPlayer.setVisible(false);
        startBtn.setVisible(false);
        p1Score.setVisible(true);
        p2Score.setVisible(true);
        rock.setVisible(true);
        paper.setVisible(true);
        scissors.setVisible(true);
        selectBtn.setVisible(true);
        rockImg.setVisible(true);
        paperImg.setVisible(true);
        scissorsImg.setVisible(true);
        winsLab.setVisible(true);
        exitBtn.setVisible(true);
        playerLabel.setVisible(true);

        curPlayer = player1;
    }

    /*
     *Sets the current move of the current player to the selected move then switches the current
     * player if player2 has not selected a move. If both players have selected a move, then it
     * calls checkScore and checkRound.
     */
    @FXML
    private void checkMove() {
        if(rock.isSelected())
            curPlayer.set_curMove(0);
        else if(paper.isSelected())
            curPlayer.set_curMove(1);
        else if(scissors.isSelected())
            curPlayer.set_curMove(2);
        else
            return;

        if(twoPlayerGame && player2.get_curMove() == -1 || player1.get_curMove() == -1) {
            switchPlayers();
        }
        else if(twoPlayerGame) {
            checkScore();
            switchPlayers();
            checkRound();
        }
        else {
            comp.genMove();
            checkScore();
            checkRound();
        }
    }

    /*
     *Switches the curPlayer variable to swap which player it refers to.
     */
    private void switchPlayers() {
        if(curPlayer == player1) {
            curPlayer = player2;
            playerLabel.setText("Player 2");
            updateWinsLab();
        }
        else {
            curPlayer = player1;
            playerLabel.setText("Player 1");
            updateWinsLab();
        }
    }

    /*
     *Checks the winner of the current hand and changes the appropriate score variable accordingly.
     */
    private void checkScore() {
        if(twoPlayerGame) {
            if(player1.get_curMove() == 0 && player2.get_curMove() == 0) {
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                tieMsg();
            }
            else if(player1.get_curMove() == 0 && player2.get_curMove() == 1) {
                player2.inc_curScore();
                p2Score.setText("P2 Score: " + player2.get_curScore());
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                p2WinMsg();
            }
            else if(player1.get_curMove() == 0 && player2.get_curMove() == 2) {
                player1.inc_curScore();
                p1Score.setText("P1 Score: " + player1.get_curScore());
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                p1WinMsg();
            }
            else if(player1.get_curMove() == 1 && player2.get_curMove() == 0) {
                player1.inc_curScore();
                p1Score.setText("P1 Score: " + player1.get_curScore());
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                p1WinMsg();
            }
            else if(player1.get_curMove() == 1 && player2.get_curMove() == 1) {
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                tieMsg();
            }
            else if(player1.get_curMove() == 1 && player2.get_curMove() == 2) {
                player2.inc_curScore();
                p2Score.setText("P2 Score: " + player2.get_curScore());
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                p2WinMsg();
            }
            else if(player1.get_curMove() == 2 && player2.get_curMove() == 0) {
                player2.inc_curScore();
                p2Score.setText("P2 Score: " + player2.get_curScore());
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                p2WinMsg();
            }
            else if(player1.get_curMove() == 2 && player2.get_curMove() == 1) {
                player1.inc_curScore();
                p1Score.setText("P1 Score: " + player1.get_curScore());
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                p1WinMsg();
            }
            else if(player1.get_curMove() == 2 && player2.get_curMove() == 2) {
                player1.set_curMove(-1);
                player2.set_curMove(-1);
                tieMsg();
            }

        }
        else {
            if(player1.get_curMove() == 0 && comp.get_curMove() == 0) {
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                tieMsg();
            }
            else if(player1.get_curMove() == 0 && comp.get_curMove() == 1) {
                comp.inc_curScore();
                p2Score.setText("P2 Score: " + comp.get_curScore());
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                compWinMsg();
            }
            else if(player1.get_curMove() == 0 && comp.get_curMove() == 2) {
                player1.inc_curScore();
                p1Score.setText("P1 Score: " + player1.get_curScore());
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                p1WinMsg();
            }
            else if(player1.get_curMove() == 1 && comp.get_curMove() == 0) {
                player1.inc_curScore();
                p1Score.setText("P1 Score: " + player1.get_curScore());
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                p1WinMsg();
            }
            else if(player1.get_curMove() == 1 && comp.get_curMove() == 1) {
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                tieMsg();
            }
            else if(player1.get_curMove() == 1 && comp.get_curMove() == 2) {
                comp.inc_curScore();
                p2Score.setText("P2 Score: " + comp.get_curScore());
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                compWinMsg();
            }
            else if(player1.get_curMove() == 2 && comp.get_curMove() == 0) {
                comp.inc_curScore();
                p2Score.setText("P2 Score: " + comp.get_curScore());
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                compWinMsg();
            }
            else if(player1.get_curMove() == 2 && comp.get_curMove() == 1) {
                player1.inc_curScore();
                p1Score.setText("P1 Score: " + player1.get_curScore());
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                p1WinMsg();
            }
            else if(player1.get_curMove() == 2 && comp.get_curMove() == 2) {
                player1.set_curMove(-1);
                comp.set_curMove(-1);
                tieMsg();
            }
        }
    }

    /*
     *Checks if any player or the computer has won the round. If one of them has, then it
     * resets the scores of the players and asks if the user wants to keep playing.
     */
    private void checkRound() {
        if(twoPlayerGame) {
            if(player1.get_curScore() >= 3) {
                player1.incWins();
                updateWinsLab();
                player1.incCurStreak();
                player2.set_curStreak(0);
                p1RoundWinMsg();
                player1.resetScore();
                player2.resetScore();
                p1Score.setText("P1 Score: 0");
                p2Score.setText("P2 Score: 0");
                checkContinue();
            }
            else if(player2.get_curScore() >= 3) {
                player2.incWins();
                updateWinsLab();
                player2.incCurStreak();
                player1.set_curStreak(0);
                p2RoundWinMsg();
                player1.resetScore();
                player2.resetScore();
                p1Score.setText("P1 Score: 0");
                p2Score.setText("P2 Score: 0");
                checkContinue();
            }
        }
        else {
            if(player1.get_curScore() >= 3) {
                player1.incWins();
                updateWinsLab();
                player1.incCurStreak();
                p1RoundWinMsg();
                player1.resetScore();
                comp.resetScore();
                p1Score.setText("P1 Score: 0");
                p2Score.setText("P2 Score: 0");
                checkContinue();
            }
            else if(comp.get_curScore() >= 3) {
                roundLossMsg();
                player1.set_curStreak(0);
                checkReplay();
            }
        }
    }

    /*
     *Uses JOptionPane to display a message that tells the user that player2 won the hand.
     */
    private void p2WinMsg() {
        JOptionPane.showMessageDialog(null, "Player 2 wins the hand!");
    }

    /*
     *Uses JOptionPane to display a message that tells the user that player1 won the hand.
     */
    private void p1WinMsg() {
        JOptionPane.showMessageDialog(null, "Player 1 wins the hand!");
    }

    /*
     *Uses JOptionPane to display a message that tells the user that the hand was a tie.
     */
    private void tieMsg() {
        JOptionPane.showMessageDialog(null, "The round was a tie!");
    }

    /*
     *Uses JOptionPane to display a message that tells the user that the computer won the hand.
     */
    private void compWinMsg() {
        JOptionPane.showMessageDialog(null, "The Computer wins the hand!");
    }

    /*
     *Uses JOptionPane to display a message that tells the user that the player lost the round against
     * the computer.
     */
    private void roundLossMsg() {
        JOptionPane.showMessageDialog(null, "You have lost the round!");
    }

    /*
     *Uses JOptionPane to display a message that tells the user that player1 won the round.
     */
    private void p1RoundWinMsg() {
        JOptionPane.showMessageDialog(null, "Player 1 won the round!");
    }

    /*
     *Uses JOptionPane to display a message that tells the user that player2 won the round.
     */
    private void p2RoundWinMsg() {
        JOptionPane.showMessageDialog(null, "Player 2 won the round!");
    }

    /*
     *Exits the program and stops it from continuing to run.
     */
    @FXML
    private void exit() {
        System.exit(0);
    }

    /*
     *Resets the game as a two player game if the parameter is true and as a one player
     * game if the parameter is false.
     */
    private void replay(boolean twoPlayer) {
        if(twoPlayer) {
            player1.set_curStreak(0);
            player2.set_curStreak(0);
            player1.set_wins(0);
            player2.set_wins(0);
            player1.resetScore();
            player2.resetScore();
            p1Score.setText("P1 Score: 0");
            p2Score.setText("P2 Score: 0");
            winsLab.setText("Rounds Won: 0");
        }
        else {
            player1.set_curStreak(0);
            player1.set_wins(0);
            player1.resetScore();
            comp.resetScore();
            p1Score.setText("P1 Score: 0");
            p2Score.setText("P2 Score: 0");
            winsLab.setText("Rounds Won: 0");
        }
    }

    /*
     *Uses JOptionPane to tell the user how many rounds each player has won as well as the longest
     * streak of each. Also asks the player whether or not they want to play another round.
     */
    private void checkReplay() {
        int rep;
        if(twoPlayerGame) {
            String[] ops = {"Yes (Two Player Game)", "Yes (One Player Game", "No, Exit"};
             rep = option(ops, "Player 1 won " + player1.get_wins() + " rounds with a highest streak of " + player1.get_hiScore() + ". Player 2 won " + player2.get_wins() + " rounds with a highest streak of " + player2.get_hiScore() + ". Do you want to play again?");
        }
        else {
            String[] ops = {"Yes (Two Player Game)", "Yes (One Player Game", "No, Exit"};
            rep = option(ops, "You won " + player1.get_wins() + " rounds with a highest streak of " + player1.get_hiScore() + ". Do you want to play again?");
        }
        if(rep == 2)
            System.exit(0);
        else if(rep == 0)
            replay(true);
        else
            replay(false);
    }

    /*
     *Uses JOptionPane to tell the user how many rounds they have won and their longest streak of wins.
     * Also aks them whether or not they want to keep playing.
     */
    private void checkContinue() {
        int cont;
        if(twoPlayerGame) {
            String[] ops = {"Continue", "Replay(One Player Game)", "Replay(Two Player Game)", "No, Exit"};
            cont = option(ops, "Player 1 has won " + player1.get_wins() + " rounds with a highest streak of " + player1.get_hiScore() + ". Player 2 has won " + player2.get_wins() + " rounds with a highest streak of " + player2.get_hiScore() + ". Do you want to continue or restart the game?");
        }
        else {
            String[] ops = {"Continue", "Replay(One Player Game)", "Replay(Two Player Game)", "No, Exit"};
            cont = option(ops, "You have won " + player1.get_wins() + " rounds with a highest streak of " + player1.get_hiScore() + ". Do you want to continue or restart the game?");
        }
        if(cont == 3)
            System.exit(0);
        if(cont == 2)
            replay(true);
        if(cont == 1)
            replay(false);
        else {
            player1.resetScore();
            if(twoPlayerGame)
                player2.resetScore();
            else
                comp.resetScore();
        }
    }

    /*
     *Uses JOptionPane to display a message to the user along with several buttons. Returns the button the
     * user clicks on as an int.
     */
    private int option(String[] options, String msg) {
        return JOptionPane.showOptionDialog(
                null,
                msg, // my message
                "Click a button", // dialog box title
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                null,
                options, // possible options
                options[0]); // default option
    }

    /*
     *Updates winsLab to show the current number of wins the current player has.
     */
    private void updateWinsLab() {
        winsLab.setText("Rounds Won: " + curPlayer.get_wins());
    }
}
