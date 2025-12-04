package com.myproject.escapethedungeon.model.ability.spell.offensivespell;

import com.myproject.escapethedungeon.model.ability.Castable;


public enum OffensiveSpellType implements Castable {
    FIREBALL("Fireball",
            "Launches a fiery projectile dealing 15 damage.", 10 ,
            15),

    SHADOW_BLADE("Shadow Blade",
            "Conjures a blade from shadows for 17 damage.", 15,
            17);

    private final String name;
    private final String description;
    private final int mpCost;
    private final int damage;

    OffensiveSpellType(String name, String description, int mpCost, int damage) {
        this.name = name;
        this.description = description;
        this.mpCost = mpCost;
        this.damage = damage;
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

    public int getDamage() {
        return damage;
    }
}
