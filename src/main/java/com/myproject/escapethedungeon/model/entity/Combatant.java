package com.myproject.escapethedungeon.model.entity;

import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.item.Item;

import java.util.List;

public interface Combatant {

    //for display purposes
    String getName();
    int getMaxHP();
    int getMaxMP();
    int getLevel();
    int getSouls();


    //damage or heal calculation
    void applyDamage(int amount);
    void applyHealing(int amount);
    int calculateBaseDamage();
    int calculateTotalDamage();

    //regeneration ||NEED TO ADD REGENERATION LOGIC||
    boolean canRegenerate();
    int regenerationRate();
    default void regenerate() {
        if (canRegenerate() && getHp() < getMaxHP()) {
            int regenAmount = Math.min(regenerationRate(), getMaxHP() - getHp());
            applyHealing(regenAmount);
        }
    }


    //other combat logics
    boolean isAlive();
    int getAccuracy();
    int getInitiativeBonus();
    void setMp(int mp);
    int getMp();
    void setHp(int hp);
    int getHp();
    int getArmourClass();
    int getSpellSave();

    //for rewards ||ADD INVENTORY NEEDED||
    void addSouls(int amount);


    //for abilities and items
    default List<Item> getInventory() {
        return List.of();
    }

    default boolean hasItems() {
        return !getInventory().isEmpty();
    }

    default boolean hasItem(Item item){
        return hasItems();
    }

    default void useItem(Item item, Combatant target) {
        if(!hasItems()){
            System.out.println("FIX enemy AI");
        }
    }

    default boolean addItem(Item item) {
        return false;
    }

    default List<Spell> getAvailableSpells() {
        return List.of();
    }

    default boolean hasSpells() {
        return !getAvailableSpells().isEmpty();
    }

    default boolean canCastSpell(Spell spell) {
        return getAvailableSpells().contains(spell) && getMp() >= spell.getMpCost();
    }

    default int getStatModifier(Stats stat){
        return 0;
    }

    default boolean castSpell(Spell spell, Combatant target) {
        if (!getAvailableSpells().contains(spell)) {
            System.out.println(getName() + " doesn't know " + spell.getName() + "!");
            return false;
        }

        spell.cast(this, target, false);
        return true;
    }
}
