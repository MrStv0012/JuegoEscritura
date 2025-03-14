package com.example.juegoescritura;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameLogic {
    private List<String> words;
    private Random random;
    private int currentLevel;
    private int timeLeft;
    private int opportunities;

    public GameLogic() {
        words = new ArrayList<>();
        random = new Random();
        currentLevel = 1;
        timeLeft = 20;
        opportunities = 4;

        initializeWords();
    }

    private void initializeWords() {
        words.add("Java");
        words.add("Programación");
        words.add("IntelliJ");
        words.add("JavaFX");
        words.add("Desarrollo");
    }

    public String getRandomWord() {
        return words.get(random.nextInt(words.size()));
    }

    public void decreaseTime() {
        timeLeft--;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public boolean isTimeOut() {
        return timeLeft <= 0;
    }

    public void resetTime() {
        timeLeft = 20;
    }

    public void decreaseOpportunities() {
        opportunities--;
    }

    public double getSunProgress() {
        return opportunities / 4.0;
    }

    public boolean isGameOver() {
        return opportunities <= 0;
    }

    public boolean validateWord(String inputWord, String currentWord) {
        return inputWord.equals(currentWord);
    }

    public void increaseLevel() {
        currentLevel++;
    }

    public boolean shouldDecreaseTime() {
        return currentLevel % 5 == 0 && timeLeft > 2;
    }

    public void decreaseTimeLimit() {
        timeLeft -= 2;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }
}