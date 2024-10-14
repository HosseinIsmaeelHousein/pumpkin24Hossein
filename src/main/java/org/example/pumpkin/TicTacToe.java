package org.example.pumpkin;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacToe extends Application {
    private boolean isXTurn = true;  // True if X's turn, false for O
    private Button[][] buttons = new Button[3][3];  // 3x3 grid of buttons

    @Override
    public void start(Stage primaryStage) {
        GridPane grid = new GridPane();

        // Create 9 buttons for the grid
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Button button = new Button(" ");
                button.setPrefSize(100, 100);

                // Add click event to the button
                button.setOnAction(e -> handleButtonClick(button));

                // Add the button to the grid and store in the array
                buttons[row][col] = button;
                grid.add(button, col, row);
            }
        }

        Scene scene = new Scene(grid, 300, 300);
        primaryStage.setTitle("Tic-Tac-Toe");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleButtonClick(Button button) {
        // If button already clicked, ignore it
        if (!button.getText().equals(" ")) {
            return;
        }

        // Set X or O depending on the turn
        if (isXTurn) {
            button.setText("X");
        } else {
            button.setText("O");
        }

        // Check if we have a winner
        if (checkForWinner()) {
            // Show the winner in an alert dialog
            String winner = isXTurn ? "X" : "O";
            showWinnerDialog(winner);
            disableButtons();  // Disable all buttons after the game ends
        }

        // Toggle the turn
        isXTurn = !isXTurn;
    }

    private boolean checkForWinner() {
        // Check rows, columns, and diagonals for a win condition
        for (int i = 0; i < 3; i++) {
            // Check rows
            if (checkThreeEqual(buttons[i][0], buttons[i][1], buttons[i][2])) {
                return true;
            }
            // Check columns
            if (checkThreeEqual(buttons[0][i], buttons[1][i], buttons[2][i])) {
                return true;
            }
        }
        // Check diagonals
        if (checkThreeEqual(buttons[0][0], buttons[1][1], buttons[2][2])) {
            return true;
        }
        if (checkThreeEqual(buttons[0][2], buttons[1][1], buttons[2][0])) {
            return true;
        }

        return false;
    }

    private boolean checkThreeEqual(Button b1, Button b2, Button b3) {
        // Check if all three buttons have the same non-empty value
        return !b1.getText().equals(" ") && b1.getText().equals(b2.getText()) && b2.getText().equals(b3.getText());
    }

    private void showWinnerDialog(String winner) {
        // Create an alert dialog to show the winner
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game Over");
        alert.setHeaderText(null);
        alert.setContentText("Player " + winner + " wins!");
        alert.showAndWait();
    }

    private void disableButtons() {
        // Disable all buttons after a player wins
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setDisable(true);
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
