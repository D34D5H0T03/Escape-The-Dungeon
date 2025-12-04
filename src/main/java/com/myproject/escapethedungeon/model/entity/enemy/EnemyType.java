package com.myproject.escapethedungeon.model.entity.enemy;

import com.myproject.escapethedungeon.util.Dice;

public enum EnemyType {
    UNDEAD_GUARD("Undead Guard", 65, 65,
            10, 10, 15,
            15, 12,8,
            Dice.DiceType.D4, Dice.DiceType.D4, 2,
            Dice.DiceType.D6, Dice.DiceType.D4, 2,
            5,  // Upgrade level
            60, 20),

    SKELETON_SOLDIER(
            "Skeleton Soldier",
            40, 40, 5, 5,
            10, 13, 15, 10,
            Dice.DiceType.D4, Dice.DiceType.D4, 0,
            Dice.DiceType.D6, Dice.DiceType.D4, 0,
            5,  // Upgrade level
            50, 15
    );

    private final String name;
    private final int maxHP;
    private final int hp;
    private final int maxMP;
    private final int mp;
    private final int hpPerLevel;
    private final int armourClass;
    private final int spellSave;
    private final int accuracy;
    private final Dice.DiceType basePrimaryDice;
    private final Dice.DiceType baseSecondaryDice;
    private final int baseDamageBonus;
    private final Dice.DiceType upgradedPrimaryDice; //the level 5 upgrade for fodder enemies
    private final Dice.DiceType upgradedSecondaryDice;
    private final int upgradedDamageBonus;

    private final int upgradeLevel;
    private final int baseSouls;
    private final int soulsPerLevel;

    EnemyType(String name, int maxHP, int hp, int maxMP, int mp, int hpPerLevel,
              int armourClass, int spellSave, int accuracy,
              Dice.DiceType basePrimaryDice, Dice.DiceType baseSecondaryDice, int baseDamageBonus,
              Dice.DiceType upgradedPrimaryDice, Dice.DiceType upgradedSecondaryDice, int upgradedDamageBonus,
              int upgradeLevel, int baseSouls, int soulsPerLevel) {
        this.name = name;
        this.maxHP = maxHP;
        this.hp = hp;
        this.maxMP = maxMP;
        this.mp = mp;
        this.hpPerLevel = hpPerLevel;
        this.armourClass = armourClass;
        this.spellSave = spellSave;
        this.accuracy = accuracy;
        this.basePrimaryDice = basePrimaryDice;
        this.baseSecondaryDice = baseSecondaryDice;
        this.baseDamageBonus = baseDamageBonus;
        this.upgradedPrimaryDice = upgradedPrimaryDice;
        this.upgradedSecondaryDice = upgradedSecondaryDice;
        this.upgradedDamageBonus = upgradedDamageBonus;
        this.upgradeLevel = upgradeLevel;
        this.baseSouls = baseSouls;
        this.soulsPerLevel = soulsPerLevel;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public int getHpPerLevel() {
        return hpPerLevel;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public Dice.DiceType getBasePrimaryDice() {
        return basePrimaryDice;
    }

    public Dice.DiceType getBaseSecondaryDice() {
        return baseSecondaryDice;
    }

    public int getBaseDamageBonus() {
        return baseDamageBonus;
    }

    public Dice.DiceType getUpgradedPrimaryDice() {
        return upgradedPrimaryDice;
    }

    public Dice.DiceType getUpgradedSecondaryDice() {
        return upgradedSecondaryDice;
    }

    public int getUpgradedDamageBonus() {
        return upgradedDamageBonus;
    }

    public int getUpgradeLevel() {
        return upgradeLevel;
    }

    public int getBaseSouls() {
        return baseSouls;
    }

    public int getSoulsPerLevel() {
        return soulsPerLevel;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getMp() {
        return mp;
    }

    public int getSpellSave() {
        return spellSave;
    }
}
