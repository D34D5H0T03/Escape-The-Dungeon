package com.myproject.escapethedungeon.core.combat.decesionmanager;

import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.ability.spell.restorativespell.RestorativeSpell;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.boss.humanoidboss.HumanoidBoss;
import com.myproject.escapethedungeon.model.entity.boss.nonhumanoidboss.NonHumanoidBoss;
import com.myproject.escapethedungeon.model.entity.enemy.Enemy;
import com.myproject.escapethedungeon.model.entity.player.Player;
import com.myproject.escapethedungeon.model.item.Item;
import com.myproject.escapethedungeon.model.item.restorativeitem.RestorativeItem;

import java.util.List;
import java.util.Random;

public class EnemyAI {
    private static final Random random = new Random();

    public static CombatAction decideAction(Combatant enemy, List<Combatant> allCombatants) {
        if (enemy instanceof HumanoidBoss boss) {
            CombatAction action = decideHumanoidBossAction(boss, allCombatants);
            if(action == null){
                action = CombatAction.attack(getPlayer(allCombatants));
            }
            return action;
        }
        else if (enemy instanceof NonHumanoidBoss boss) {
            CombatAction action = decideNonHumanoidBossAction(boss, allCombatants);
            if(action == null){
                action = CombatAction.attack(getPlayer(allCombatants));
            }
            return action;
        }
        else if (enemy instanceof Enemy regularEnemy) {
            CombatAction action = decideRegularEnemyAction(regularEnemy, allCombatants);
            if(action == null){
                action = CombatAction.attack(getPlayer(allCombatants));
            }
            return action;
        }
        return CombatAction.attack(getPlayer(allCombatants));
    }

    private static CombatAction decideRegularEnemyAction(Enemy enemy, List<Combatant> allCombatants) {
        Combatant player = getPlayer(allCombatants);
        if (player == null) return null;

        if (player.getHp() < player.getMaxHP() * 0.3) {
            return CombatAction.attack(player);
        }

        if (random.nextDouble() < 0.8) {
            return CombatAction.attack(player);
        } else {
            return CombatAction.guard();
        }
    }

    private static CombatAction decideNonHumanoidBossAction(NonHumanoidBoss boss, List<Combatant> allCombatants) {
        Combatant player = getPlayer(allCombatants);
        if (player == null) return null;

        boolean playerHasLowHealth = player.getHp() < player.getMaxHP() * 0.5;
        double attackProbability = playerHasLowHealth ? 0.9 : 0.7;

        if (random.nextDouble() < attackProbability) {
            return CombatAction.attack(player);
        } else {
            return CombatAction.guard();
        }
    }

    private static CombatAction decideHumanoidBossAction(HumanoidBoss boss, List<Combatant> allCombatants) {
        Combatant player = getPlayer(allCombatants);
        if (player == null) return null;

        if (boss.getHp() < boss.getMaxHP() * 0.4) {
            CombatAction healAction = tryToHeal(boss);
            if (healAction != null) return healAction;
        }

        boolean playerIsWeak = player.getHp() < player.getMaxHP() * 0.3;
        double chance = random.nextDouble();

        if (playerIsWeak) {
            if (chance < 0.8) {
                return CombatAction.attack(player);
            } else if (chance < 0.9) {
                CombatAction spellAction = tryToCastSpell(boss, player);
                return spellAction != null ? spellAction : CombatAction.attack(player);
            } else {
                return CombatAction.guard();
            }
        } else {
            if (chance < 0.6) {
                return CombatAction.attack(player);
            } else if (chance < 0.8) {
                return CombatAction.guard();
            } else {
                CombatAction spellAction = tryToCastSpell(boss, player);
                return spellAction != null ? spellAction : CombatAction.attack(player);
            }
        }
    }

    private static CombatAction tryToHeal(HumanoidBoss boss) {
        for (Item item : boss.getInventory()) {
            if (item instanceof RestorativeItem) {
                return CombatAction.useItem(item, boss);
            }
        }

        for (Spell spell : boss.getAvailableSpells()) {
            if (spell instanceof RestorativeSpell) {
                RestorativeSpell restorative = (RestorativeSpell) spell;
                if (restorative.getHpRestore() > 0 && boss.getMp() >= spell.getMpCost()) {
                    return CombatAction.castSpell(spell, boss);
                }
            }
        }

        return null;
    }

    private static CombatAction tryToCastSpell(HumanoidBoss boss, Combatant player) {
        for (Spell spell : boss.getAvailableSpells()) {
            if (!(spell instanceof RestorativeSpell) && boss.getMp() >= spell.getMpCost()) {
                return CombatAction.castSpell(spell, player);
            }
        }

        return null;
    }

    private static Combatant getPlayer(List<Combatant> allCombatants) {
        for (Combatant c : allCombatants) {
            if (c instanceof Player && c.isAlive()) {
                return c;
            }
        }
        return null;
    }
}
