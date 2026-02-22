package org.lushplugins.regrowthfroogle.listener;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.lushplugins.itempools.event.ItemPoolGoalUpdateEvent;
import org.lushplugins.itempools.goal.Goal;

public class ItemPoolListener implements Listener {

    @EventHandler
    public void onItemPoolGoalUpdate(ItemPoolGoalUpdateEvent event) {
        if (!event.getItemPool().getId().equals("froogle")) {
            return;
        }

        Goal goal = event.getGoal();
        float percentage = ((float) event.getNewGoalValue() / goal.getGoal()) * 100;

        Npc npc = FancyNpcsPlugin.get().getNpcManager().getNpc("Froogle");
        if (npc == null) {
            return;
        }

        NpcData npcData = npc.getData();
        npcData.setScale(0.5f + (0.045f * percentage));
        npc.updateForAll();
    }
}
