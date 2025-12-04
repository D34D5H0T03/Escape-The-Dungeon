package com.myproject.escapethedungeon.model.entity.boss.humanoidboss;

import com.myproject.escapethedungeon.model.ability.Castable;
import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.Stats;
import com.myproject.escapethedungeon.model.entity.boss.Boss;
import com.myproject.escapethedungeon.model.equipment.armour.Armour;
import com.myproject.escapethedungeon.model.equipment.weapon.Weapon;
import com.myproject.escapethedungeon.model.item.Item;
import com.myproject.escapethedungeon.model.item.restorativeitem.RestorativeItemType;
import com.myproject.escapethedungeon.util.Factory;

import java.util.ArrayList;

public class HumanoidBoss extends Boss {
    private HumanoidBossType type;
    private ArrayList<Spell> availableSpells;
    private ArrayList<Item> inventory;
    private Weapon mainHand;
    private Armour bodyArmour;

    private final int[] STATS = new int[6];

    public HumanoidBoss(HumanoidBossType type) {
        super(type.getName(), type.getMaxHP(), type.getMaxMP(), type.getArmourClass(), type.getSpellSave(), type.getInitiativeBonus(),
                type.getAccuracy(), type.getLevel(), type.getSouls());
        this.type = type;

        int[] base = type.getStats();
        for (Stats stat : Stats.values()) {
            STATS[stat.getIndex()] = base[stat.getIndex()];
        }

        this.availableSpells = new ArrayList<>();
        this.inventory = new ArrayList<>();

        this.mainHand = Factory.createWeapon(type.getWeaponType());
        this.bodyArmour = Factory.createArmour(type.getArmourType());

        for (Castable ability : type.getStartingAbilities()) {
            Spell spell = Factory.createSpell(ability);
            if (spell != null) {
                availableSpells.add(spell);
            }
        }

        this.inventory.add(Factory.createRestorativeItem(RestorativeItemType.HEALING_POTION));
        this.inventory.add(Factory.createRestorativeItem(RestorativeItemType.HEALING_POTION));
    }

    @Override
    public String getDescription() {
        return type.getDescription();
    }

    public int getStat(Stats stat) {
        return STATS[stat.getIndex()];
    }

    @Override
    public int getStatModifier(Stats stat) {
        return (getStat(stat) - 10) / 2;
    }

    @Override
    public int getArmourClass() {
        int baseAC = bodyArmour.getArmourClass();
        int dexMod = getStatModifier(Stats.DEXTERITY);
        return baseAC + dexMod;
    }

    @Override
    public int getAccuracy() {
        return accuracy + getStatModifier(Stats.DEXTERITY);
    }

    @Override
    public int calculateBaseDamage(){
        return mainHand.calculateBaseDamage();
    }

    @Override
    public int calculateTotalDamage(){
        return mainHand.calculateBaseDamage() + getStatModifier(mainHand.getScalingStat());
    }

    @Override
    public boolean canRegenerate(){
        return false;
    }

    @Override
    public int regenerationRate(){
        return 0;
    }

    public HumanoidBossType getType() {
        return type;
    }

    public int[] getSTATS() {
        return STATS;
    }


    public ArrayList<Spell> getAvailableSpells() {
        return availableSpells;
    }

    @Override
    public int getSpellSave(){
        return getSpellSave() + getStatModifier(Stats.INTELLIGENCE);
    }

    public Weapon getMainHand() {
        return mainHand;
    }

    public Armour getBodyArmour() {
        return bodyArmour;
    }

    //boss inventory
    @Override
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    public boolean removeItem(Item item) {
        return inventory.remove(item);
    }

    @Override
    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }

    @Override
    public void useItem(Item item, Combatant target) {
        item.use(this, target);
        if (item.isConsumable()) {
            System.out.println(removeItem(item) ? "Item Consumed" : "Not consumed");
        }
    }
}
