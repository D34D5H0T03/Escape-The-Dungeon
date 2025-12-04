package com.myproject.escapethedungeon.model.entity.enemy;

import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.util.Dice;

public class Enemy implements Combatant {
    private EnemyType type;
    private String name;
    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;
    private int armourClass;
    private int spellSave;
    private int accuracy;
    private int level;
    private int souls;
    private Dice.DiceType primaryDice;
    private Dice.DiceType secondaryDice;
    private int damageBonus;

    public Enemy(EnemyType type, int level) {
        if (level < 1) throw new IllegalArgumentException("Level must be >= 1");

        this.name = type.getName();
        this.type = type;
        this.level = level;

        this.maxHP = type.getMaxHP() + type.getHpPerLevel() * (level - 1);
        this.hp = this.maxHP;
        this.maxMP = type.getMaxMP();
        this.mp = type.getMp();

        this.armourClass = type.getArmourClass();
        this.spellSave = type.getSpellSave();
        this.accuracy = type.getAccuracy();


        if (level >= type.getUpgradeLevel() && type.getUpgradeLevel() > 0) {
            this.primaryDice = type.getUpgradedPrimaryDice();
            this.secondaryDice = type.getUpgradedSecondaryDice();
            this.damageBonus = type.getUpgradedDamageBonus();
        } else {
            this.primaryDice = type.getBasePrimaryDice();
            this.secondaryDice = type.getBaseSecondaryDice();
            this.damageBonus = type.getBaseDamageBonus();
        }

        this.souls = type.getBaseSouls() + type.getSoulsPerLevel() * (level - 1);
    }

    @Override
    public int calculateBaseDamage(){
        int damage = Dice.rollDice(primaryDice);

        if (secondaryDice != null) {
            damage += Dice.rollDice(secondaryDice);
        }
        return damage;
    }

    @Override
    public int calculateTotalDamage() {
        int damage = Dice.rollDice(primaryDice);

        if (secondaryDice != null) {
            damage += Dice.rollDice(secondaryDice);
        }

        damage += damageBonus;
        damage += (level / 2);

        return Math.max(1, damage); //for at least 1 damage
    }

    @Override
    public boolean canRegenerate(){
        return false;
    }

    @Override
    public int regenerationRate(){
        return 0;
    }

    public EnemyType getType() {
        return type;
    }

    @Override
    public int getMp() {
        return mp;
    }

    @Override
    public int getMaxMP() {
        return maxMP;
    }

    @Override
    public void setMp(int mp) {
        this.mp = mp;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    @Override
    public int getHp() {
        return hp;
    }

    @Override
    public int getMaxHP() {
        return maxHP;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getArmourClass() {
        return armourClass;
    }

    @Override
    public int getInitiativeBonus() {
        return level / 2;
    }

    @Override
    public int getSpellSave() {
        return spellSave + getLevel();
    }

    @Override
    public int getAccuracy() {
        return accuracy;
    }

    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getSouls() {
        return souls;
    }

    @Override
    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setSouls(int souls) {
        this.souls = souls;
    }

    @Override
    public void addSouls(int amount) {
        this.souls += amount;
    }

    @Override
    public boolean isAlive() {
        return (hp > 0);
    }

    @Override
    public void applyDamage(int amount) {
        this.hp -= amount;
    }

    @Override
    public void applyHealing(int amount) {
        this.hp += amount;
    }

    //getters for damage
    public Dice.DiceType getPrimaryDice() {
        return primaryDice;
    }

    public Dice.DiceType getSecondaryDice() {
        return secondaryDice;
    }

    public int getDamageBonus() {
        return damageBonus;
    }
}
