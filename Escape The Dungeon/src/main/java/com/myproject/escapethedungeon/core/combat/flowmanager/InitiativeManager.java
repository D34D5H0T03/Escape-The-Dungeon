package com.myproject.escapethedungeon.core.combat.flowmanager;

import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.util.Dice;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

public class InitiativeManager {
    private final Map<Combatant, Integer> initiativeRolls = new IdentityHashMap<>();
    private List<Combatant> turnOrder = new ArrayList<>();


    public void rollInitiative(List<Combatant> combatants) {
        initiativeRolls.clear();

        for (Combatant c : combatants) {
            int roll = Dice.rollDice(Dice.DiceType.D20);
            int bonus = c.getInitiativeBonus();
            int total = roll + bonus;

            initiativeRolls.put(c, total);
        }

        turnOrder = new ArrayList<>(combatants);
        turnOrder.sort((a, b) -> {
            int initA = initiativeRolls.get(a);
            int initB = initiativeRolls.get(b);
            return Integer.compare(initB, initA);
        });
    }

    public List<Combatant> getTurnOrder() {
        return new ArrayList<>(turnOrder);
    }

    public int getInitiative(Combatant c) {
        return initiativeRolls.get(c);
    }

    public void printOrder() {
        System.out.println("\n=== INITIATIVE ORDER ===");
        for (int i = 0; i < turnOrder.size(); i++) {
            Combatant c = turnOrder.get(i);
            int roll = initiativeRolls.get(c);
            System.out.printf("%d. %s (Roll: %d + Bonus: %d = %d)\n",
                    i + 1, c.getName(),
                    roll - c.getInitiativeBonus(),
                    c.getInitiativeBonus(),
                    roll);
        }
        System.out.println();
    }
}
