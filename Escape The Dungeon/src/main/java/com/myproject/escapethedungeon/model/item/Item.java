package com.myproject.escapethedungeon.model.item;

import com.myproject.escapethedungeon.model.entity.Combatant;

public abstract class Item {
    protected String name;
    protected String description;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public abstract void use(Combatant user, Combatant target);
    public abstract boolean isConsumable();

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
