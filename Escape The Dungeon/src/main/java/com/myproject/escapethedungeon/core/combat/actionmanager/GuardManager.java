package com.myproject.escapethedungeon.core.combat.actionmanager;

import com.myproject.escapethedungeon.model.entity.Combatant;

import java.util.IdentityHashMap;
import java.util.Map;

public class GuardManager {
    private static final Map<Combatant, Boolean> activeGuards = new IdentityHashMap<>();

    public static void startGuard(Combatant c) {
        if (c == null || !c.isAlive()){
            return;
        }

        activeGuards.put(c, true);
        System.out.println(c.getName() + " takes a defensive stance! (Damage halved until next turn)");
    }

    public static int applyGuardReduction(Combatant defender, int incomingDamage) {
        if (isGuarding(defender)) {
            int reduced = (incomingDamage + 1) / 2; //rounding to ceil
            System.out.print(" [Guarded: " + incomingDamage + " → " + reduced + "]");
            return reduced;
        }
        return incomingDamage;
    }

    public static boolean isGuarding(Combatant c) {
        return activeGuards.containsKey(c);
    }

    public static void clearGuard(Combatant c) {
        if (c == null) {
            return;
        }
        if (activeGuards.remove(c) != null) {
            System.out.println(c.getName() + " drops their defensive stance.");
        }
    }

    public static void resetAllGuards() {
        activeGuards.clear();
    }

    public static int getActiveGuardCount() {
        return activeGuards.size();
    }
}
