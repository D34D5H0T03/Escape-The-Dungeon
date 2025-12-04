package com.myproject.escapethedungeon.core.combat;

import com.myproject.escapethedungeon.core.combat.actionmanager.AttackManager;
import com.myproject.escapethedungeon.core.combat.actionmanager.GuardManager;
import com.myproject.escapethedungeon.core.combat.actionmanager.ItemManager;
import com.myproject.escapethedungeon.core.combat.actionmanager.SpellManager;
import com.myproject.escapethedungeon.core.combat.decesionmanager.CombatAction;
import com.myproject.escapethedungeon.core.combat.decesionmanager.EnemyAI;
import com.myproject.escapethedungeon.core.combat.decesionmanager.PlayerInputReader;
import com.myproject.escapethedungeon.core.combat.flowmanager.CombatResolver;
import com.myproject.escapethedungeon.core.combat.flowmanager.InitiativeManager;
import com.myproject.escapethedungeon.core.combat.flowmanager.TurnManager;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.player.Player;

import java.util.List;

public class CombatEngine {
    private final InitiativeManager initiativeManager;
    private final TurnManager turnManager;
    private final PlayerInputReader playerInputReader;
    private List<Combatant> allCombatants;
    private boolean combatEndedEarly = false;

    public CombatEngine() {
        this.initiativeManager = new InitiativeManager();
        this.turnManager = new TurnManager();
        this.playerInputReader = new PlayerInputReader();
    }

    public void startCombat(List<Combatant> combatants) {
        System.out.println("\n=== COMBAT STARTED ===");

        this.allCombatants = combatants;
        this.combatEndedEarly = false;

        initiativeManager.rollInitiative(allCombatants);
        initiativeManager.printOrder();

        turnManager.startCombat(initiativeManager.getTurnOrder());

        while (!isCombatOver() && !combatEndedEarly) {
            processTurn();
        }

        if (!combatEndedEarly) {
            endCombat();
        }
    }

    private void processTurn() {
        Combatant current = turnManager.getCurrentCombatant();

        if (current == null || !current.isAlive()) {
            turnManager.nextTurn();
            return;
        }

        System.out.println("\n============================");
        System.out.println("Turn: " + current.getName());

        GuardManager.clearGuard(current);

        CombatAction action = null;

        if (current instanceof Player player) {
            action = processPlayerTurn(player);
        } else {
            action = processEnemyTurn(current);
        }

        if (action != null) {
            executeAction(current, action);
        }

        if (current.canRegenerate()) {
            current.regenerate();
        }

        turnManager.removeDeadCombatants();
        removeDeadCombatantsFromList();

        turnManager.nextTurn();
    }

    private CombatAction processPlayerTurn(Player player) {
        CombatAction action = playerInputReader.getPlayerAction(player, allCombatants);

        if (action == null) {
            System.out.println("Action cancelled or invalid. Skipping turn.");
        }

        return action;
    }

    private CombatAction processEnemyTurn(Combatant enemy) {
        CombatAction action = EnemyAI.decideAction(enemy, allCombatants);

        if (action == null) {
            Combatant player = findPlayer();
            if (player != null) {
                return CombatAction.attack(player);
            }
        }

        return action;
    }

    private void executeAction(Combatant actor, CombatAction action) {
        switch (action.getActionType()) {
            case ATTACK -> AttackManager.meleeAttack(actor, action.getCombatant());
            case CAST_SPELL -> SpellManager.castSpellInCombat(actor, action.getSpell(), action.getCombatant());
            case USE_ITEM -> ItemManager.useItemInCombat(actor, action.getItem(), action.getCombatant());
            case GUARD -> GuardManager.startGuard(actor);
            case FLEE -> {
                if (CombatResolver.attemptFlee(actor)) {
                    combatEndedEarly = true;
                    System.out.println("Combat ended early due to successful flee!");
                }
            }
        }
    }

    private boolean isCombatOver() {
        if (combatEndedEarly) {
            return true;
        }

        boolean playerAlive = false;
        for (Combatant c : allCombatants) {
            if (c instanceof Player && c.isAlive()) {
                playerAlive = true;
                break;
            }
        }

        boolean enemyAlive = false;
        for (Combatant c : allCombatants) {
            if (!(c instanceof Player) && c.isAlive()) {
                enemyAlive = true;
                break;
            }
        }

        return !playerAlive || !enemyAlive;
    }

    private void endCombat() {
        if (CombatResolver.isPlayerVictory(allCombatants)) {
            CombatResolver.displayVictory();

            Player player = findPlayer();
            if (player != null) {
                CombatResolver.awardVictoryRewards(player, allCombatants);
            }
        } else {
            CombatResolver.handleDefeat();
        }
    }
    private Player findPlayer() {
        for (Combatant c : allCombatants) {
            if (c instanceof Player && c.isAlive()) {
                return (Player) c;
            }
        }
        return null;
    }

    private boolean anyPlayerAlive() {
        for (Combatant c : allCombatants) {
            if (c instanceof Player && c.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private boolean anyEnemyAlive() {
        for (Combatant c : allCombatants) {
            if (!(c instanceof Player) && c.isAlive()) {
                return true;
            }
        }
        return false;
    }

    private void removeDeadCombatantsFromList() {
        allCombatants.removeIf(c -> !c.isAlive());
    }
}
