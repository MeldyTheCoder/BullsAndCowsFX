package ru.bullsandcows.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class GameRun {

    public static void main(String[] args) {
        GameEngine game = new GameEngine();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Random number has been generated! Guess it and win!");
        System.out.print(game.random_number);

        while (game.isGameActive()) {
            try {
                System.out.println("\n");
                System.out.print("Number -> ");
                String input = reader.readLine();
                game.validateAnswer(input);

                if (game.isWon(input)) {
                    System.out.println("\n");
                    System.out.print("You won! You passed " + game.getSteps() + " step(s).");
                    break;
                }
                Integer[] response = game.getBullsAndCows(input);
                System.out.print("Bulls: " + response[0] + " | Cows: " + response[1]);

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }


        }
    }
}
