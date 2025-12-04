package com.myproject.escapethedungeon.core.combat.actionmanager;

import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.util.Dice;

public class AttackManager {
    public static void meleeAttack(Combatant attacker, Combatant defender) {
        if (!attacker.isAlive() || !defender.isAlive()) return;


        int roll = Dice.rollDice(Dice.DiceType.D20);
        int attackTotal = roll + attacker.getAccuracy();


        int defenderAC = defender.getArmourClass();

        System.out.printf("%s attacks %s → d20: %d + %d = %d vs AC %d\n",
                attacker.getName(), defender.getName(), roll, attacker.getAccuracy(), attackTotal, defenderAC);

        if (attackTotal < defenderAC && roll != 20) {
            System.out.println(" → MISS!");
            return;
        }

        boolean isCritical = (roll == 20);
        if (isCritical) {
            System.out.print(" → CRITICAL HIT!");
        } else {
            System.out.print(" → HIT!");
        }

        int baseDamage = attacker.calculateBaseDamage();
        int totalDamage = attacker.calculateTotalDamage();

        if (isCritical) {
            totalDamage = (baseDamage * 2) + (totalDamage - baseDamage); //double the base damage from dice for critical
            System.out.printf(" Crit! Dice doubled: %d → %d + mod = %d",
                    baseDamage / 2, baseDamage, totalDamage);
        }

        totalDamage = GuardManager.applyGuardReduction(defender, totalDamage);

        defender.applyDamage(totalDamage);

        System.out.printf(" → %s takes %d damage! (HP: %d/%d)%n",
                defender.getName(), totalDamage, defender.getHp(), defender.getMaxHP());

        if (!defender.isAlive()) {
            System.out.println(">>> " + defender.getName() + " has been defeated!");
        }
    }
}
