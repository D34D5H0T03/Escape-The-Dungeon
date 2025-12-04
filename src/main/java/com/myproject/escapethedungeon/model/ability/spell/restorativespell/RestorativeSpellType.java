package com.myproject.escapethedungeon.model.ability.spell.restorativespell;

import com.myproject.escapethedungeon.model.ability.Castable;

public enum RestorativeSpellType implements Castable {
    RESTORATION("Restoration", "Heals the target for 25 HP", 10, 25, 0),
    MANA_SURGE("Mana Surge", "Restores 20 MP to the caster", 0, 0, 20);

    private final String name;
    private final String description;
    private final int mpCost;
    private final int hpRestore;
    private final int mpRestore;

    RestorativeSpellType(String name, String description, int mpCost, int hpRestore, int mpRestore) {
        this.name = name;
        this.description = description;
        this.mpCost = mpCost;
        this.hpRestore = hpRestore;
        this.mpRestore = mpRestore;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getMpCost() {
        return mpCost;
    }

    public int getMpRestore() {
        return mpRestore;
    }


    public int getHpRestore() {
        return hpRestore;
    }
}
