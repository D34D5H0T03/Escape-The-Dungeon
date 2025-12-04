package com.myproject.escapethedungeon.util;

import java.util.Random;

public class Dice {
    public enum DiceType{
        D4 , D6, D8, D10, D12, D20
    }

    private static final Random random = new Random();

    public static int roll4SidedDice() {
        return random.nextInt(4) + 1;
    }

    public static int roll6SidedDice() {
        return random.nextInt(6) + 1;
    }

    public static int roll8SidedDice() {
        return random.nextInt(8) + 1;
    }

    public static int roll10SidedDice() {
        return random.nextInt(10) + 1;
    }
    public static int roll12SidedDice(){
        return random.nextInt(12) + 1;
    }

    public static int roll20SidedDice() {
        return random.nextInt(20) + 1;
    }

    public static int rollMultipleDice(int numberOfDice, DiceType diceType) {
        int total = 0;
        for (int i = 0; i < numberOfDice; i++) {
            total += rollDice(diceType);
        }
        return total;
    }

    public static int rollDice(DiceType type) {
        return switch (type) {
            case D4 -> roll4SidedDice();
            case D6 -> roll6SidedDice();
            case D8 -> roll8SidedDice();
            case D12 -> roll12SidedDice();
            case D10 -> roll10SidedDice();
            case D20 -> roll20SidedDice();
        };
    }

}
