package com.myproject.escapethedungeon.core.combat.flowmanager;

import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.Stats;
import com.myproject.escapethedungeon.model.entity.player.Player;
import com.myproject.escapethedungeon.util.Dice;

import java.util.List;

public class CombatResolver {
    public static boolean attemptFlee(Combatant fleer) {
        System.out.println(fleer.getName() + " attempts to flee!");

        int roll = Dice.rollDice(Dice.DiceType.D20);
        int dexMod = fleer.getStatModifier(Stats.DEXTERITY);
        int total = roll + dexMod;
        int dc = 10;

        System.out.printf("Flee check: d20 %d + DEX %d = %d vs DC %d\n",
                roll, dexMod, total, dc);

        boolean success = total >= dc;

        if (success) {
            System.out.println(fleer.getName() + " successfully escapes!");
        } else {
            System.out.println(fleer.getName() + " fails to escape!");
        }

        return success;
    }

    public static void awardVictoryRewards(Player player, List<Combatant> allCombatants) {
        if (player == null || !player.isAlive()) {
            return;
        }

        int totalSouls = 0;

        for (Combatant c : allCombatants) {
            if (c instanceof Player) continue;

            if (!c.isAlive()) {
                totalSouls += c.getSouls();
                System.out.println("  - " + c.getName() + " drops " + c.getSouls() + " souls");
            }
        }

        if (totalSouls > 0) {
            player.addSouls(totalSouls);
            System.out.println("\nTotal souls gained: " + totalSouls);
            System.out.println("Player now has: " + player.getSouls() + " souls");
        } else {
            System.out.println("No souls gained from this battle.");
        }
    }

    public static void handleDefeat() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║           YOU DIED               ║");
        System.out.println("║                                  ║");
        System.out.println("║   Your journey ends here...     ║");
        System.out.println("╚══════════════════════════════════╝");

        //add respawn logic to hubarea
    }


    public static void displayVictory() {
        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("║           VICTORY!               ║");
        System.out.println("║                                  ║");
        System.out.println("║   The battle is won!             ║");
        System.out.println("╚══════════════════════════════════╝");
    }

    public static boolean isPlayerVictory(List<Combatant> allCombatants) {
        boolean playerAlive = false;
        boolean enemyAlive = false;

        for (Combatant c : allCombatants) {
            if (!c.isAlive()) continue;

            if (c instanceof Player) {
                playerAlive = true;
            } else {
                enemyAlive = true;
            }
        }

        return playerAlive && !enemyAlive;
    }
}
