package ru.bullsandcows.demo;

import java.lang.reflect.Array;
import java.util.*;
import java.util.regex.Pattern;

public class GameEngine {

    private final int numberDigits = 4;
    private final int maxSteps = 10;
    private final Pattern digitPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
    public String random_number;
    private final List<String> digits = new ArrayList<>(
            Arrays.asList("1", "2", "3", "4", "5", "6", "7", "8", "9", "0"));
    private Integer steps = 0;
    private boolean isActive = false;


    public GameEngine() {
        this.newGame();
    }

    public void newGame() {
        if (!this.isActive) {
            this.random_number = this.getRandomNumber();
            this.isActive = true;
        }

    }

    private Integer getRandomNumberAsInteger() throws Exception {
        return this.stringToInteger(this.random_number);
    }

    private String getRandomNumber() {
        Collections.shuffle(this.digits);

        String randomString = "";
        for (int i = 0; i < this.numberDigits; i++) {
            randomString = randomString + this.digits.get(i);
        }

        return randomString;

    }

    public Integer getSteps() {
        return this.steps;
    }
    private void finishGame() {
        this.isActive = false;
    }

    public boolean isWon(String userInput) {
        this.steps += 1;
        boolean won = userInput.equals(this.random_number);
        if (won) {
            this.finishGame();
        }
        return won;
    }

    public Integer[] getBullsAndCows(String userInput) throws Exception {
        int cows = 0;
        int bulls = 0;

        if (this.maxSteps == this.steps) {
            this.finishGame();
            throw new Exception("Вы проиграли! Вы превысили лимит в " + this.maxSteps + " шагов!");
        }

        List<String> result = this.stringToList(this.random_number);
        List<String> answer = this.stringToList(userInput);

        for (int i = 0; i < answer.size(); i++) {
            String digit = answer.get(i);
            if (result.get(i).equals(digit)) {
                bulls++;
            } else if (result.contains(digit)) {
                cows++;
            }
        }

        return new Integer[] {bulls, cows};
    }

    public boolean isGameActive() {
        return this.isActive;
    }

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return this.digitPattern.matcher(strNum).matches();
    }

    private Set<String> stringToSet(String data) {
        List<String> listData = this.stringToList(data);
        return this.listToSet(listData);
    }

    private Set<String> listToSet(List<String> data) {
        return new HashSet<String>(data);
    }

    private Integer stringToInteger(String number) throws Exception {
        if (this.isNumeric(number)){
            return Integer.parseInt(number);
        }
        throw new Exception("Не число!");
    }

    private List<String> stringToList(String number) {
        return new ArrayList<>(Arrays.asList(number.split("")));
    }

    public void validateAnswer(String userInput) throws Exception {
        if (!this.isNumeric(userInput)) {
            throw new Exception("Ваш ответ должен быть целым числом!");
        }

        if (userInput.length() != this.numberDigits) {
            throw new Exception("Ваш ответ должен быть целым число из " + this.numberDigits + " цифр!");
        }

        if (this.stringToSet(userInput).size() != userInput.length()) {
            throw new Exception("Все цифры в вводимом числе должны быть уникальными!");

        }

    }


}

