package com.myproject.escapethedungeon.model.ability.spell;

import com.myproject.escapethedungeon.model.ability.Castable;
import com.myproject.escapethedungeon.model.entity.Combatant;

public abstract class Spell implements Castable {
    protected String name;
    protected int mpCost;
    protected String description;

    public abstract void cast(Combatant caster, Combatant target, boolean isCritical);
    public abstract boolean isRequiresAttackRoll();

    public Spell(String name, String description, int mpCost) {
        this.name = name;
        this.mpCost = mpCost;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMpCost() {
        return mpCost;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
