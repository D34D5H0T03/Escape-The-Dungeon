package com.myproject.escapethedungeon.model.ability.spell.restorativespell;

import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.entity.Combatant;

import java.util.Random;

public class RestorativeSpell extends Spell {
    private final int hpRestore;
    private final int mpRestore;

    public RestorativeSpell(RestorativeSpellType type) {
        super(type.getName(), type.getDescription(), type.getMpCost());
        this.hpRestore = type.getHpRestore();
        this.mpRestore = type.getMpRestore();
    }

    @Override
    public void cast(Combatant caster, Combatant target, boolean isCritical){
        isCritical = false;
        Random rand = new Random();
        int roll = rand.nextInt(20) + 1;

        if(roll == 20){
            isCritical = true;
        }

        int actualHpRestore = hpRestore;
        int actualMpRestore = mpRestore;

        if(isCritical){
            System.out.println("PERFECT CAST");
            actualHpRestore *= 2;
            actualMpRestore *= 2;
        }

        if (actualHpRestore > 0) {
            int newHp = target.getHp() + actualHpRestore;
            target.setHp(Math.min(newHp, target.getMaxHP()));
            System.out.println(target.getName() + " recovered " + actualHpRestore + " HP!");
        }

        if (actualMpRestore > 0) {
            int newMp = target.getMp() + actualMpRestore;
            target.setMp(Math.min(newMp, target.getMaxMP()));
            System.out.println(target.getName() + " recovered " + actualMpRestore + " MP!");
        }
    }

    public boolean isRequiresAttackRoll(){
        return false;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    public int getMpRestore() {
        return mpRestore;
    }
}
