package com.myproject.escapethedungeon.model.entity.boss.humanoidboss;

import com.myproject.escapethedungeon.model.ability.Castable;
import com.myproject.escapethedungeon.model.ability.spell.offensivespell.OffensiveSpellType;
import com.myproject.escapethedungeon.model.equipment.armour.ArmourType;
import com.myproject.escapethedungeon.model.equipment.weapon.WeaponType;

import java.util.List;

public enum HumanoidBossType {
    THE_WARDEN(
            "Cedric Varn The Warden",
            new int[]{16, 14, 18, 12, 14, 10}, //stats
            200,
            100,
            16,
            15,
            10,
            7,
            800,
            7,
            "A corrupted warden who has succumbed to the dark rituals," +
                    " now a monstrous abomination feeding on the dead.",
            WeaponType.WARDEN_SPEAR,
            ArmourType.WARDEN_PLATE,
            List.of(OffensiveSpellType.FIREBALL, OffensiveSpellType.SHADOW_BLADE)
    );

    private final String name;
    private final int[] stats;
    private final int maxHP;
    private final int maxMP;
    private final int armourClass;
    private final int spellSave;
    private final int accuracy;
    private final int level;
    private final int souls;
    private final int initiativeBonus;
    private final String description;
    private final WeaponType weaponType;
    private final ArmourType armourType;
    private final List<Castable> startingAbilities;

    HumanoidBossType(String name, int[] stats, int maxHP, int maxMP, int armourClass, int spellSave, int accuracy,
                     int level, int souls, int initiativeBonus, String description, WeaponType weaponType,
                     ArmourType armourType, List<Castable> startingAbilities) {
        this.name = name;
        this.stats =stats;
        this.maxHP = maxHP;
        this.maxMP = maxMP;
        this.armourClass = armourClass;
        this.spellSave = spellSave;
        this.accuracy = accuracy;
        this.level = level;
        this.souls = souls;
        this.initiativeBonus = initiativeBonus;
        this.description = description;
        this.weaponType = weaponType;
        this.armourType = armourType;
        this.startingAbilities = startingAbilities;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return maxHP;
    }

    public int getMaxMP() {
        return maxMP;
    }

    public int getArmourClass() {
        return armourClass;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getLevel() {
        return level;
    }

    public int getSouls() {
        return souls;
    }

    public int getInitiativeBonus() {
        return initiativeBonus;
    }

    public String getDescription() {
        return description;
    }

    public WeaponType getWeaponType() {
        return weaponType;
    }

    public ArmourType getArmourType() {
        return armourType;
    }

    public List<Castable> getStartingAbilities() {
        return startingAbilities;
    }

    public int[] getStats() {
        return stats;
    }

    public int getSpellSave() {
        return spellSave;
    }
}
