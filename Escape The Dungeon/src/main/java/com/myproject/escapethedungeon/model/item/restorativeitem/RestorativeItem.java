package com.myproject.escapethedungeon.model.item.restorativeitem;

import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.item.Item;

public class RestorativeItem extends Item {
    private RestorativeItemType type;
    private final int hpRestore;
    private final int mpRestore;

    public RestorativeItem(RestorativeItemType type) {
        super(type.getName(), type.getDescription());
        this.hpRestore = type.getHpRestore();
        this.mpRestore = type.getMpRestore();
    }

    @Override
    public void use(Combatant user, Combatant target) {
        if (hpRestore > 0) {
            int newHp = target.getHp() + hpRestore;
            target.setHp(Math.min(newHp, target.getMaxHP()));
            System.out.println(target.getName() + " recovered " + hpRestore + " HP!");
        }

        if (mpRestore > 0) {
            int newMp = target.getMp() + mpRestore;
            target.setMp(Math.min(newMp, target.getMaxMP()));
            System.out.println(target.getName() + " recovered " + mpRestore + " MP!");
        }
    }

    @Override
    public boolean isConsumable() {
        return true;
    }

    public int getHpRestore() {
        return hpRestore;
    }

    public int getMpRestore() {
        return mpRestore;
    }

    @Override
    public String toString() {
        return "RestorativeItem{" +
                "type=" + type +
                ", hpRestore=" + hpRestore +
                ", mpRestore=" + mpRestore +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
