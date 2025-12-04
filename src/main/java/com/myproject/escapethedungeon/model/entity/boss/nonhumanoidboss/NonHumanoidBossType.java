package com.myproject.escapethedungeon.model.entity.boss.nonhumanoidboss;

import com.myproject.escapethedungeon.util.Dice;

public enum NonHumanoidBossType {
    ABYSSAL_HORROR(
            "Abyssal Horror",
            300,
            50,
            18,
            17,
            12,
            9,
            8,
            1200,
            "A formless entity from the void, its very presence warps reality around it.",
            Dice.DiceType.D20,
            Dice.DiceType.D10,
            4,
            true,
            10
    );

    private final String name;
    private final int maxHP;
    private final int maxMP;
    private final int armourClass;
    private final int spellSave;
    private final int accuracy;
    private final int initiativeBonus;
    private final int level;
    private final int souls;
    private final String description;
    private final Dice.DiceType primaryDice;
    private final Dice.DiceType secondaryDice;
    private final int damageBonus;

    //have to add regeneration logic to combat
    private final boolean canRegenerate;
    private final int regenerationRate;

    NonHumanoidBossType(String name, int maxHP, int maxMP, int armourClass, int spellSave, int accuracy, int initiativeBonus,
                        int level, int souls, String description,
                        Dice.DiceType primaryDice, Dice.DiceType secondaryDice, int damageBonus,
                        boolean canRegenerate, int regenerationRate) {
        this.name = name;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.armourClass = armourClass;
        this.spellSave = spellSave;
        this.accuracy = accuracy;
        this.initiativeBonus = initiativeBonus;
        this.level = level;
        this.souls = souls;
        this.description = description;
        this.primaryDice = primaryDice;
        this.secondaryDice = secondaryDice;
        this.damageBonus = damageBonus;
        this.canRegenerate = canRegenerate;
        this.regenerationRate = regenerationRate;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getInitiativeBonus() {
        return initiativeBonus;
    }

    public int getSouls() {
        return souls;
    }

    public int getLevel() {
        return level;
    }

    public String getDescription() {
        return description;
    }

    public Dice.DiceType getPrimaryDice() {
        return primaryDice;
    }

    public Dice.DiceType getSecondaryDice() {
        return secondaryDice;
    }

    public int getDamageBonus() {
        return damageBonus;
    }

    public boolean isCanRegenerate() {
        return canRegenerate;
    }

    public int getRegenerationRate() {
        return regenerationRate;
    }

    public int getSpellSave() {
        return spellSave;
    }
}
