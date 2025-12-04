package com.myproject.escapethedungeon.core.combat.actionmanager;

import com.myproject.escapethedungeon.model.ability.spell.Spell;
import com.myproject.escapethedungeon.model.ability.spell.offensivespell.OffensiveSpell;
import com.myproject.escapethedungeon.model.ability.spell.restorativespell.RestorativeSpell;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.Stats;
import com.myproject.escapethedungeon.model.entity.player.Player;
import com.myproject.escapethedungeon.util.Dice;

public class SpellManager {
    public static void castSpellInCombat(Combatant caster, Spell spell, Combatant target) {
        if (caster.getMp() < spell.getMpCost()) {
            System.out.println("NOT ENOUGH MP");
            return;


        }
        if (spell instanceof RestorativeSpell restorative) {
            restorative.cast(caster, target, false);
            return;
        }

        if (spell instanceof OffensiveSpell offensive) {
            castOffensiveSpellInCombat(caster, offensive, target);
            return;
        }
        spell.cast(caster, target, false);
    }

    private static void castOffensiveSpellInCombat(Combatant caster, OffensiveSpell spell, Combatant target) {
        if (caster.getMp() < spell.getMpCost()) {
            System.out.println("NOT ENOUGH MP");
            return;
        }
        caster.setMp(caster.getMp() - spell.getMpCost());

        System.out.println(caster.getName() + " casts " + spell.getName() + "!");

        int roll = Dice.rollDice(Dice.DiceType.D20);
        int spellBonus = getSpellAttackBonus(caster);
        int total = roll + spellBonus;

        boolean isCritical = (roll == 20);

        System.out.printf("Spell Attack → %s: d20 %d + %d = %d vs Spell Save %d → ",
                target.getName(), roll, spellBonus, total, target.getSpellSave());

        if (total < target.getSpellSave() && !isCritical) {
            System.out.println("SAVED! No effect.");
            return;
        }

        System.out.println(isCritical ? "CRITICAL SPELL HIT!" : "SPELL HITS!");

        int damage = spell.getDamage();
        if (isCritical) {
            damage *= 2;
            System.out.print(" CRITICAL! Damage doubled!");
        }

        damage = GuardManager.applyGuardReduction(target, damage);

        target.applyDamage(damage);
        System.out.printf(" → %s takes %d damage! (HP: %d/%d)%n",
                target.getName(), damage, target.getHp(), target.getMaxHP());
    }

    private static int getSpellAttackBonus(Combatant caster) {
        Stats spellStat = switch (caster) {
            case Player p -> switch (p.getType()) {
                case MAGE, ROGUE -> Stats.INTELLIGENCE;
                case CLERIC, WARRIOR -> Stats.CHARISMA;
            };
            default -> Stats.INTELLIGENCE;
        };
        return caster.getAccuracy() + caster.getStatModifier(spellStat);
    }
}
