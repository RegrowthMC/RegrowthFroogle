package org.lushplugins.regrowthfroogle.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.lushplugins.itempools.event.ItemPoolGoalUpdateEvent;
import org.lushplugins.itempools.goal.Goal;
import org.lushplugins.regrowthfroogle.util.NPCHelper;

public class ItemPoolListener implements Listener {

    @EventHandler
    public void onItemPoolGoalUpdate(ItemPoolGoalUpdateEvent event) {
        if (!event.getItemPool().getId().equals("froogle")) {
            return;
        }

        Goal goal = event.getGoal();
        float percentage = ((float) event.getNewGoalValue() / goal.getGoal()) * 100;
        NPCHelper.updateFroogleScale(percentage);
    }
}
