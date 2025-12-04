package com.myproject.escapethedungeon.model.entity.player;

import com.myproject.escapethedungeon.model.ability.Castable;
import com.myproject.escapethedungeon.model.ability.spell.offensivespell.OffensiveSpellType;
import com.myproject.escapethedungeon.model.ability.spell.restorativespell.RestorativeSpellType;
import com.myproject.escapethedungeon.model.equipment.armour.ArmourType;
import com.myproject.escapethedungeon.model.equipment.weapon.WeaponType;

import java.util.List;

public enum PlayerType {
    WARRIOR(
            "Warrior", 1, 0,
            new int[]{16, 13, 15, 12, 10, 8},
            100,
            50,
            6,
            13,
            1,
            new int[]{1, 0, 1, 0, 0, 0},
            5,
            WeaponType.IRON_SWORD,
            ArmourType.CHAINMAIL,
            List.of()// no starting spells
    ),
    ROGUE(
            "Rogue", 1,0,
            new int[]{13, 16, 15, 12, 10, 8},
            90,
            60,
            8,
            16,
            2,
            new int[]{0, 1, 1, 0, 0, 0},
            7,
            WeaponType.DUAL_DAGGERS,
            ArmourType.LEATHER_GARB,
            List.of(OffensiveSpellType.SHADOW_BLADE)
    ),
    CLERIC(
            "Cleric", 1, 0,
            new int[]{12, 11, 15, 12, 14, 10},
            80,
            70,
            5,
            14,
            4,
            new int[]{0, 0, 1, 0, 1, 0},
            2,
            WeaponType.IRON_MACE,
            ArmourType.CHAINMAIL,
            List.of(
                    RestorativeSpellType.RESTORATION,
                    OffensiveSpellType.FIREBALL
            )
    ),
    MAGE(
            "Mage", 1, 0,
            new int[]{12, 10, 15, 16, 13, 8},
            80,
            70,
            7,
            15,
            5,
            new int[]{0, 0, 1, 1, 0, 0},
            3,
            WeaponType.MAGICIANS_STAFF,
            ArmourType.SCHOLARS_ROBE,
            List.of(
                    RestorativeSpellType.RESTORATION,
                    OffensiveSpellType.FIREBALL,
                    OffensiveSpellType.SHADOW_BLADE
            )
    );

    private final String name;
    private final int level;
    private final int souls;
    private final int[] baseStats;
    private final int baseMaxHp;
    private final int baseMaxMp;
    private final int baseAccuracy;
    private final int baseSpellSave;
    private final int spellSlots;
    private final int[] levelUpBonuses;
    private final int initiativeBonus;
    private final WeaponType weapon;
    private final ArmourType armour;
    private final List<Castable> startingAbilities; //list of spells and skills ||ADD SKILLS||

    PlayerType(String name, int level, int souls,
               int[] baseStats,
               int baseMaxHp,
               int baseMaxMp,
               int baseAccuracy,
               int baseSpellSave,
               int spellSlots,
               int[] levelUpBonuses,
               int initiativeBonus,
               WeaponType startingWeapon,
               ArmourType startingArmour,
               List<Castable> startingAbilities) {
        this.name = name;
        this.level = level;
        this.souls =souls;
        this.baseStats = baseStats;
        this.baseMaxHp = baseMaxHp;
        this.baseMaxMp = baseMaxMp;
        this.baseAccuracy = baseAccuracy;
        this.baseSpellSave = baseSpellSave;
        this.spellSlots = spellSlots;
        this.levelUpBonuses = levelUpBonuses;
        this.initiativeBonus = initiativeBonus;
        this.weapon = startingWeapon;
        this.armour = startingArmour;
        this.startingAbilities = startingAbilities;
    }


    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getSouls() {
        return souls;
    }

    public int[] getBaseStats() {
        return baseStats;
    }

    public int getBaseMaxHp() {
        return baseMaxHp;
    }

    public int getBaseAccuracy() {
        return baseAccuracy;
    }

    public int getBaseMaxMp() {
        return baseMaxMp;
    }

    public int getSpellSlots() {
        return spellSlots;
    }

    public int[] getLevelUpBonuses() {
        return levelUpBonuses;
    }

    public int getInitiativeBonus() {
        return initiativeBonus;
    }

    public ArmourType getArmour() {
        return armour;
    }

    public WeaponType getWeapon() {
        return weapon;
    }

    public List<Castable> getStartingAbilities() {
        return startingAbilities;
    }

    public int getBaseSpellSave() {
        return baseSpellSave;
    }
}
