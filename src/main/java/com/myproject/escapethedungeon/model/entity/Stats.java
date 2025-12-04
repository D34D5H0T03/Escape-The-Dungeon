package com.myproject.escapethedungeon.model.entity;

public enum Stats {
    STRENGTH(0),
    DEXTERITY(1),
    CONSTITUTION(2),
    INTELLIGENCE(3),
    WISDOM(4),
    CHARISMA(5);

    private final int index;

    Stats(int index) {
        this.index = index;
    }

    public int getIndex() {
        return index;
    }
}
