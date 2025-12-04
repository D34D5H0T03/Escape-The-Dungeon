package com.myproject.escapethedungeon.core;

import com.myproject.escapethedungeon.core.combat.CombatEngine;
import com.myproject.escapethedungeon.model.entity.Combatant;
import com.myproject.escapethedungeon.model.entity.Stats;
import com.myproject.escapethedungeon.model.entity.enemy.Enemy;
import com.myproject.escapethedungeon.model.entity.enemy.EnemyType;
import com.myproject.escapethedungeon.model.entity.player.Player;
import com.myproject.escapethedungeon.model.entity.player.PlayerType;
import com.myproject.escapethedungeon.util.Factory;

import java.util.ArrayList;

public class TestGame {
    static void main() {
        Player player = new Player(PlayerType.MAGE, "TestPlayer");
        player.levelUp(Stats.STRENGTH);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.CONSTITUTION);
        player.levelUp(Stats.STRENGTH);
        player.levelUp(Stats.STRENGTH);
        player.levelUp(Stats.STRENGTH);
        player.levelUp(Stats.STRENGTH);
        player.levelUp(Stats.STRENGTH);


        Enemy enemy1 = Factory.createEnemy(EnemyType.UNDEAD_GUARD, 4);
        Enemy enemy2 = Factory.createEnemy(EnemyType.SKELETON_SOLDIER, 4);
        Enemy enemy3 = Factory.createEnemy(EnemyType.UNDEAD_GUARD, 5);
        Enemy enemy4 = Factory.createEnemy(EnemyType.SKELETON_SOLDIER, 2);

        ArrayList<Combatant> combatParticipants = new ArrayList<>();
        combatParticipants.add(player);
        combatParticipants.add(enemy1);
        combatParticipants.add(enemy2);
        combatParticipants.add(enemy3);
        combatParticipants.add(enemy4);

        CombatEngine combatEngine = new CombatEngine();
        combatEngine.startCombat(combatParticipants);
    }
}
