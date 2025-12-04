package com.myproject.escapethedungeon.core.combat.flowmanager;

import com.myproject.escapethedungeon.model.entity.Combatant;

import java.util.ArrayList;
import java.util.List;

public class TurnManager {
    private List<Combatant> turnOrder = new ArrayList<>();
    private int currentIndex = 0;

    public void startCombat(List<Combatant> orderedCombatants) {
        this.turnOrder = new ArrayList<>(orderedCombatants);
        this.currentIndex = 0;
    }

    public Combatant getCurrentCombatant() {
        if (currentIndex >= turnOrder.size() || currentIndex < 0) {
            return null;
        }
        return turnOrder.get(currentIndex);
    }

    public void nextTurn() {
        if (turnOrder.isEmpty()){
            return;
        }

        currentIndex++;

        if (currentIndex >= turnOrder.size()) {
            currentIndex = 0;
        }
    }


    public void removeDeadCombatants() {
        turnOrder.removeIf(c -> !c.isAlive());
    }

    public void reset() {
        turnOrder.clear();
        currentIndex = 0;
    }
}
