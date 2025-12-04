package com.myproject.escapethedungeon.core.combat.decesionmanager;

import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.player.Player;
import com.myproject.escapethedungeon.model.item.Item;

import java.util.List;
import java.util.Scanner;

public class PlayerInputReader {
    private final Scanner scanner;

    public PlayerInputReader() {
        this.scanner = new Scanner(System.in);
    }
    public CombatAction getPlayerAction(Player player, List<Combatant> allCombatants) {
        System.out.println("\n=== " + player.getName() + "'s Turn ===");
        System.out.println("HP: " + player.getHp() + "/" + player.getMaxHP() +
                " MP: " + player.getMp() + "/" + player.getMaxMP());

        System.out.println("Choose action:");
        System.out.println("1. Attack");
        System.out.println("2. Cast Spell");
        System.out.println("3. Use Item");
        System.out.println("4. Guard");
        System.out.println("5. Flee");

        int choice = getIntInput(1, 5);

        switch (choice) {
            case 1 -> {
                Combatant target = selectTarget(allCombatants);
                return CombatAction.attack(target);
            }
            case 2 -> {
                Spell spell = selectSpell(player);
                if (spell == null) return null;
                Combatant target = selectTarget(allCombatants);
                return CombatAction.castSpell(spell, target);
            }
            case 3 -> {
                Item item = selectItem(player);
                if (item == null) return null;
                Combatant target = selectTarget(allCombatants);
                return CombatAction.useItem(item, target);
            }
            case 4 -> {
                return CombatAction.guard();
            }
            case 5 -> {
                return CombatAction.flee();
            }
            default -> {
                return null;
            }
        }
    }

    private Combatant selectTarget(List<Combatant> allCombatants) {
        List<Combatant> aliveTargets = allCombatants.stream()
                .filter(Combatant::isAlive)
                .toList();

        System.out.println("\nSelect target:");

        for (int i = 0; i < aliveTargets.size(); i++) {
            Combatant target = aliveTargets.get(i);
            System.out.printf("%d. %s (HP: %d/%d)%n",
                    i, target.getName(), target.getHp(), target.getMaxHP());
        }

        int choice = getIntInput(0, aliveTargets.size() - 1);
        return aliveTargets.get(choice);
    }

    private Spell selectSpell(Player player) {
        List<Spell> spells = player.getAvailableSpells();
        if (spells.isEmpty()) {
            System.out.println("You have no spells!");
            return null;
        }

        System.out.println("\nSelect spell:");
        for (int i = 0; i < spells.size(); i++) {
            Spell spell = spells.get(i);
            System.out.printf("%d. %s - %d MP%n", i, spell.getName(), spell.getMpCost());
        }
        System.out.println("-1. Cancel");

        int choice = getIntInput(-1, spells.size() - 1);
        if (choice == -1) return null;

        Spell selected = spells.get(choice);

        if (player.getMp() < selected.getMpCost()) {
            System.out.println("Not enough MP!");
            return selectSpell(player);
        }

        return selected;
    }

    private Item selectItem(Player player) {
        List<Item> inventory = player.getInventory();
        if (inventory.isEmpty()) {
            System.out.println("Your inventory is empty!");
            return null;
        }

        System.out.println("\nSelect item:");
        for (int i = 0; i < inventory.size(); i++) {
            Item item = inventory.get(i);
            System.out.printf("%d. %s - %s%n", i, item.getName(), item.getDescription());
        }
        System.out.println("-1. Cancel");

        int choice = getIntInput(-1, inventory.size() - 1);
        return choice == -1 ? null : inventory.get(choice);
    }

    private int getIntInput(int min, int max) {
        while (true) {
            try {
                System.out.print("Enter choice [" + min + "-" + max + "]: ");
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) return input;
                System.out.println("Invalid choice.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }
}
