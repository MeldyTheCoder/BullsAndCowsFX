package ru.bullsandcows.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class BullsAndCowsGame extends Application {

    private TextField guessField;
    private Label messageLabel;
    private Button guessButton;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Быки и коровы");

        GameEngine game = new GameEngine();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label guessLabel = new Label("Ваше число:");
        grid.add(guessLabel, 0, 1);

        guessField = new TextField();
        grid.add(guessField, 1, 1);

        guessButton = new Button("Угадать");
        grid.add(guessButton, 2, 1);

        messageLabel = new Label("");
        grid.add(messageLabel, 1, 2);

        // set action for guess button
        guessButton.setOnAction(e -> {
            if (game.isGameActive()) {
                String input = guessField.getText();
                try {
                    game.validateAnswer(input);

                    if (game.isWon(input)) {
                        System.out.println("\n");
                        String message = "Вы выиграли! Вы прошли игру за " + game.getSteps() + " шагов.";
                        messageLabel.setText(message);
                    }
                    Integer[] response = game.getBullsAndCows(input);
                    String message = "Быки: " + response[0] + " | Коровы: " + response[1];
                    messageLabel.setText(message);
                } catch (Exception exc) {
                    messageLabel.setText(exc.getMessage());
                }

            }
        });

        Scene scene = new Scene(grid, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}