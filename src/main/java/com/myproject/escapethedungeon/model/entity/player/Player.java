package com.myproject.escapethedungeon.model.entity.player;

import com.myproject.escapethedungeon.model.ability.Castable;
import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.Stats;
import com.myproject.escapethedungeon.model.equipment.armour.Armour;
import com.myproject.escapethedungeon.model.equipment.weapon.Weapon;
import com.myproject.escapethedungeon.model.item.Item;
import com.myproject.escapethedungeon.model.item.restorativeitem.RestorativeItemType;
import com.myproject.escapethedungeon.util.Factory;

import java.util.ArrayList;
import java.util.Arrays;

public class Player implements Combatant {
    private static final int MAX_INVENTORY_SIZE = 20;

    private final PlayerType type;
    private String name;

    private int hp;
    private int maxHP;
    private int mp;
    private int maxMP;
    private int armourClass;
    private int spellSave;
    private int accuracy;
    private int initiativeBonus;
    private int spellSlots;
    private int level;
    private int souls;

    private final int[] STATS = new int[6];
    private final ArrayList<Item> inventory = new ArrayList<>();
    private final ArrayList<Spell> availableSpells = new ArrayList<>();

    private Weapon mainHand;
    private Armour bodyArmour;

    public Player(PlayerType type, String name) {
        this.type = type;
        this.name = name;

        int[] base = type.getBaseStats();
        for (Stats stat : Stats.values()) {
            STATS[stat.getIndex()] = base[stat.getIndex()];
        }

        //hp/mp with modifiers ||FOR STAT BONUS||
        this.maxHP = type.getBaseMaxHp() + getStatModifier(Stats.CONSTITUTION);
        this.hp = maxHP;
        this.maxMP = type.getBaseMaxMp() + getStatModifier(Stats.WISDOM);
        this.mp = maxMP;

        this.accuracy = type.getBaseAccuracy();
        this.spellSave = type.getBaseSpellSave() + getStatModifier(Stats.INTELLIGENCE);
        this.spellSlots = type.getSpellSlots();
        this.initiativeBonus = type.getInitiativeBonus();

        //equipment
        this.mainHand = new Weapon(type.getWeapon());
        this.bodyArmour = new Armour(type.getArmour());
        this.armourClass = bodyArmour.getArmourClass() + getStatModifier(Stats.DEXTERITY);

        //spells and skill ||ADD SKILLS||
        for (Castable ability : type.getStartingAbilities()) {
            Spell spell = Factory.createSpell(ability);
            if (spell != null) {
                availableSpells.add(spell);
            }
        }

        for (int i = 0; i < 3; i++) {
            inventory.add(Factory.createRestorativeItem(RestorativeItemType.HEALING_POTION));
        }
        if (type == PlayerType.CLERIC || type == PlayerType.MAGE) {
            for (int i = 0; i < 2; i++) {
                inventory.add(Factory.createRestorativeItem(RestorativeItemType.MAGIC_POTION));
            }
        }
    }

    //stat implementation methods
    public int getStat(Stats stat) {
        return STATS[stat.getIndex()];
    }
    private void addToStat(Stats stat, int amount) {
        STATS[stat.getIndex()] += amount;
    }

    @Override
    public int getStatModifier(Stats stat) {
        return (getStat(stat) - 10) / 2;
    }
    public int[] getSTATS() {
        return STATS;
    }

    // general getters and setters
    @Override
    public String getName() {
        return name;
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
    public void setHp(int hp) {
        this.hp = Math.max(0, Math.min(hp, maxHP));
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
        this.mp = Math.max(0, Math.min(mp, maxMP));
    }


    //combat methods
    @Override
    public int getArmourClass() {
        return armourClass;
    }

    @Override
    public int getSpellSave() {
        return spellSave;
    }

    @Override
    public int getInitiativeBonus() {
        return initiativeBonus;
    }

    @Override
    public boolean isAlive() {
        return hp > 0;
    }

    @Override
    public void applyDamage(int amount) {
        hp = Math.max(0, hp - amount);
    }

    @Override
    public void applyHealing(int amount) {
        hp = Math.min(maxHP, hp + amount);
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


    //level and souls methods
    @Override
    public int getLevel() {
        return level;
    }

    @Override
    public int getSouls() {
        return souls;
    }

    @Override
    public void addSouls(int amount) {
        this.souls += amount;
    }

    public boolean levelUp(Stats bonusStat) {
        if (level >= 10) return false;
        if (souls < 100 * level) return false;

        souls -= 100 * level;
        level++;

        // class bonus
        int[] bonuses = type.getLevelUpBonuses();
        for (Stats stat : Stats.values()) {
            addToStat(stat, bonuses[stat.getIndex()]);
        }

        // player choice
        addToStat(bonusStat, 1);

        // HP/MP growth
        maxHP += 10 + getStatModifier(Stats.CONSTITUTION);
        maxMP += 5 + getStatModifier(Stats.WISDOM);

        hp = maxHP;
        mp = maxMP;

        return true;
    }



    //UI methods
    public PlayerType getType() {
        return type;
    }

    @Override
    public int getAccuracy() {
        return accuracy + getStatModifier(Stats.DEXTERITY);
    }

    //equipment methods ||NEED ADD EQUIP/UNEQUIP||
    public Weapon getMainHand() {
        return mainHand;
    }
    public Armour getBodyArmour() {
        return bodyArmour;
    }


    //abilities methods
    public int getSpellSlots() {
        return spellSlots;
    }
    @Override
    public ArrayList<Spell> getAvailableSpells() {
        return availableSpells;
    }

    @Override
    public boolean castSpell(Spell spell, Combatant target) {
        if (!getAvailableSpells().contains(spell)) {
            System.out.println(getName() + " doesn't know " + spell.getName() + "!");
            return false;
        }

        spell.cast(this, target, false);
        return true;
    }

    //inventory methods
    @Override
    public ArrayList<Item> getInventory() {
        return inventory;
    }

    @Override
    public boolean addItem(Item item) {
        if (item == null) {
            System.out.println("Cannot add null item.");
            return false;
        }

        if (inventory.size() >= MAX_INVENTORY_SIZE) {
            System.out.println("Inventory full! Cannot add " + item.getName());
            return false;
        }

        inventory.add(item);
        System.out.println("Added " + item.getName() + " to inventory.");
        return true;
    }

    @Override
    public void useItem(Item item, Combatant target) {
        if (!inventory.contains(item)) {
            System.out.println("Item not in inventory!");
            return;
        }

        item.use(this, target);
        if (item.isConsumable()) {
            boolean removed = removeItem(item);
            if (removed) {
                System.out.println(item.getName() + " was consumed.");
            }
        }
    }

    public boolean removeItem(Item item) {
        boolean removed = inventory.remove(item);
        if (removed) {
            System.out.println("Removed " + item.getName() + " from inventory.");
        } else {
            System.out.println("Item " + item.getName() + " not found in inventory.");
        }
        return removed;
    }

    public Item dropItemAtIndex(int index) {
        if (index < 0 || index >= inventory.size()) {
            return null;
        }
        return inventory.remove(index);      //these two methods for UI adding and removing from tray
    }
    public Item getItemAtIndex(int index) {
        if (index < 0 || index >= inventory.size()) {
            return null;
        }
        return inventory.get(index);
    }

    @Override
    public boolean hasItem(Item item) {
        return inventory.contains(item);
    }

    public Item findItemByName(String name) {
        return inventory.stream()
                .filter(item -> item.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public int countItemType(Class<? extends Item> itemType) {
        return (int) inventory.stream()
                .filter(itemType::isInstance)
                .count();
    }

    public int getInventorySize() {
        return inventory.size();
    }

    public boolean isInventoryFull() {
        return inventory.size() >= MAX_INVENTORY_SIZE;
    }

    public boolean isInventoryEmpty() {
        return inventory.isEmpty();
    }

    public void clearInventory() {
        inventory.clear();
        System.out.println("Inventory cleared.");
    }

    public void displayInventory() {
        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("=== INVENTORY (" + inventory.size() + "/" + MAX_INVENTORY_SIZE + ") ===");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.printf("%d. %s - %s\n", i + 1, item.getName(), item.getDescription());
        }
    }

    //I don't know methods may remove later
    public void setName(String name) {
        this.name = name;
    }

    public void setMaxHP(int maxHP) {
        this.maxHP = maxHP;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public void setMaxMP(int maxMP) {
        this.maxMP = maxMP;
    }

    public void setArmourClass(int armourClass) {
        this.armourClass = armourClass;
    }

    @Override
    public String toString() {
        return "Player{" +
                "type=" + type +
                ", name='" + name + '\'' +
                ", hp=" + hp +
                ", maxHP=" + maxHP +
                ", mp=" + mp +
                ", maxMP=" + maxMP +
                ", armourClass=" + armourClass +
                ", accuracy=" + accuracy +
                ", initiativeBonus=" + initiativeBonus +
                ", spellSlots=" + spellSlots +
                ", level=" + level +
                ", souls=" + souls +
                ", STATS=" + Arrays.toString(STATS) +
                ", inventory=" + inventory +
                ", availableSpells=" + availableSpells +
                ", mainHand=" + mainHand +
                ", bodyArmour=" + bodyArmour +
                '}';
    }
}
