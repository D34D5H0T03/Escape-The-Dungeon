package com.myproject.escapethedungeon.model.entity.boss.nonhumanoidboss;

import com.myproject.escapethedungeon.model.entity.boss.Boss;
import com.myproject.escapethedungeon.util.Dice;

public class NonHumanoidBoss extends Boss {
    private NonHumanoidBossType type;
    private Dice.DiceType primaryDice;
    private Dice.DiceType secondaryDice;
    private int damageBonus;

    private boolean canRegenerate;
    private int regenerationRate;

    public NonHumanoidBoss(NonHumanoidBossType type) {
        super(type.getName(), type.getMaxHP(), type.getMaxMP(), type.getArmourClass(), type.getSpellSave(), type.getInitiativeBonus(),
                type.getAccuracy(), type.getLevel(), type.getSouls());
        this.type = type;
        this.primaryDice = type.getPrimaryDice();
        this.secondaryDice = type.getSecondaryDice();
        this.damageBonus = type.getDamageBonus();
        this.canRegenerate = type.isCanRegenerate();
        this.regenerationRate = type.getRegenerationRate();
    }

    @Override
    public String getDescription() {
        return type.getDescription();
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

        return Math.max(1, damage);
    }

    public String getDamageDescription() {
        if (secondaryDice != null) {
            return primaryDice + " + " + secondaryDice + " + " + damageBonus;
        }
        return primaryDice + " + " + damageBonus;
    }

    public NonHumanoidBossType getType() {
        return type;
    }

    @Override
    public boolean canRegenerate() {
        return canRegenerate;
    }

    @Override
    public int regenerationRate() {
        return regenerationRate;
    }

    @Override
    public void regenerate() {
        if (canRegenerate && hp < maxHP) {
            int regenAmount = Math.min(regenerationRate, maxHP - hp);
            applyHealing(regenAmount);
            System.out.println(name + " regenerates " + regenAmount + " HP!");
        }
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

    public void setRegenerationRate(int regenerationRate) {
        this.regenerationRate = regenerationRate;
    }

}
