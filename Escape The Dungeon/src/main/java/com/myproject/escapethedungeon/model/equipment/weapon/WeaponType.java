package com.myproject.escapethedungeon.model.equipment.weapon;

import com.myproject.escapethedungeon.model.entity.Stats;
import com.myproject.escapethedungeon.util.Dice;

public enum WeaponType {
    //||ADD DESCRIPTIONS!!!!!!||||
    IRON_SWORD("Iron Sword", Dice.DiceType.D10, Dice.DiceType.D8, Stats.STRENGTH),
    IRON_MACE("Iron Mace", Dice.DiceType.D10, Dice.DiceType.D4, Stats.STRENGTH),
    MAGICIANS_STAFF("Magician's Staff", Dice.DiceType.D8, Dice.DiceType.D6, Stats.DEXTERITY),
    DUAL_DAGGERS("Dual Daggers", Dice.DiceType.D6, Dice.DiceType.D6, Stats.DEXTERITY),
    WARDEN_SPEAR("Warden's Spear", Dice.DiceType.D12, Dice.DiceType.D10, Stats.DEXTERITY);

    private final String name;
    private final Dice.DiceType primaryDice;
    private final Dice.DiceType secondaryDice;
    private final Stats scalingStat;

    WeaponType(String name, Dice.DiceType primaryDice, Dice.DiceType secondaryDice, Stats scalingStat) {
        this.name = name;
        this.primaryDice = primaryDice;
        this.secondaryDice = secondaryDice;
        this.scalingStat = scalingStat;
    }

    public Dice.DiceType getPrimaryDice() {
        return primaryDice;
    }

    public Dice.DiceType getSecondaryDice() {
        return secondaryDice;
    }

    public String getName() {
        return name;
    }

    public Stats getScalingStat() {
        return scalingStat;
    }
}
