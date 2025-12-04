package com.myproject.escapethedungeon.core.combat.decesionmanager;

import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.item.Item;

public class CombatAction {
    private final ActionType actionType;
    private final Combatant combatant;

    //for casting and item use only
    private final Spell spell;
    private final Item item;

    public CombatAction(ActionType actionType, Combatant combatant, Spell spell, Item item) {
        this.actionType = actionType;
        this.combatant = combatant;
        this.spell = spell;
        this.item = item;
    }
    public static CombatAction attack(Combatant target) {
        return new CombatAction(ActionType.ATTACK, target, null, null);
    }

    public static CombatAction castSpell(Spell spell, Combatant target) {
        return new CombatAction(ActionType.CAST_SPELL, target, spell, null);
    }

    public static CombatAction useItem(Item item, Combatant target) {
        return new CombatAction(ActionType.USE_ITEM, target, null, item);
    }

    public static CombatAction guard() {
        return new CombatAction(ActionType.GUARD, null, null, null);
    }

    public static CombatAction flee() {
        return new CombatAction(ActionType.FLEE, null, null, null);
    }

    public ActionType getActionType() {
        return actionType;
    }

    public Combatant getCombatant() {
        return combatant;
    }

    public Spell getSpell() {
        return spell;
    }

    public Item getItem() {
        return item;
    }
}
