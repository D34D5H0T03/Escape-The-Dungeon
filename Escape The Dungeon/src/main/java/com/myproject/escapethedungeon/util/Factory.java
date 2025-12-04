package com.myproject.escapethedungeon.util;

import com.myproject.escapethedungeon.model.ability.Castable;
import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.ability.spell.offensivespell.OffensiveSpell;
import com.myproject.escapethedungeon.model.ability.spell.offensivespell.OffensiveSpellType;
import com.myproject.escapethedungeon.model.ability.spell.restorativespell.RestorativeSpell;
import com.myproject.escapethedungeon.model.ability.spell.restorativespell.RestorativeSpellType;
import com.myproject.escapethedungeon.model.entity.boss.humanoidboss.HumanoidBoss;
import com.myproject.escapethedungeon.model.entity.boss.humanoidboss.HumanoidBossType;
import com.myproject.escapethedungeon.model.entity.boss.nonhumanoidboss.NonHumanoidBoss;
import com.myproject.escapethedungeon.model.entity.boss.nonhumanoidboss.NonHumanoidBossType;
import com.myproject.escapethedungeon.model.entity.enemy.Enemy;
import com.myproject.escapethedungeon.model.entity.enemy.EnemyType;
import com.myproject.escapethedungeon.model.entity.player.Player;
import com.myproject.escapethedungeon.model.entity.player.PlayerType;
import com.myproject.escapethedungeon.model.equipment.armour.Armour;
import com.myproject.escapethedungeon.model.equipment.armour.ArmourType;
import com.myproject.escapethedungeon.model.equipment.weapon.Weapon;
import com.myproject.escapethedungeon.model.equipment.weapon.WeaponType;
import com.myproject.escapethedungeon.model.item.restorativeitem.RestorativeItem;
import com.myproject.escapethedungeon.model.item.restorativeitem.RestorativeItemType;

public class Factory {
    public static Player createPlayer(PlayerType type, String name) {
        return new Player(type, name);
    }

    public static Enemy createEnemy(EnemyType type, int level) {
        return new Enemy(type, level);
    }

    public static Weapon createWeapon(WeaponType type) {
        return new Weapon(type);
    }

    public static Armour createArmour(ArmourType type) {
        return new Armour(type);
    }

    public static RestorativeItem createRestorativeItem(RestorativeItemType type) {
        return new RestorativeItem(type);
    }

    public static OffensiveSpell createOffensiveSpell(OffensiveSpellType type) {
        return new OffensiveSpell(type);
    }

    public static RestorativeSpell createRestorativeSpell(RestorativeSpellType type) {
        return new RestorativeSpell(type);
    }

    public static Spell createSpell(Castable castable) {
        if (castable instanceof OffensiveSpellType offensiveType) {
            return new OffensiveSpell(offensiveType);
        } else if (castable instanceof RestorativeSpellType restorativeType) {
            return new RestorativeSpell(restorativeType);
        }
        return null;
    }

    public static HumanoidBoss createHumanoidBoss(HumanoidBossType type) {
        return new HumanoidBoss(type);
    }

    public static NonHumanoidBoss createNonHumanoidBoss(NonHumanoidBossType type) {
        return new NonHumanoidBoss(type);
    }

}
