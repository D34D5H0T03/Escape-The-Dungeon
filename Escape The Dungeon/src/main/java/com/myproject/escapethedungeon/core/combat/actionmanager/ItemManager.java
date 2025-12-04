package com.myproject.escapethedungeon.core.combat.actionmanager;

import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.item.Item;

public class ItemManager {
    public static boolean useItemInCombat(Combatant user, Item item, Combatant target) {
        if (item == null) {
            System.out.println("No item selected!");
            return false;
        }

        if (!user.hasItem(item)) {
            System.out.println(user.getName() + " doesn't have " + item.getName() + "!");
            return false;
        }

        System.out.println(user.getName() + " uses " + item.getName() + "!");

        user.useItem(item, target);

        return true;
    }
}
