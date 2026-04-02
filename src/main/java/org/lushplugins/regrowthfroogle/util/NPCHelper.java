package org.lushplugins.regrowthfroogle.util;

import de.oliver.fancynpcs.api.FancyNpcsPlugin;
import de.oliver.fancynpcs.api.Npc;
import de.oliver.fancynpcs.api.NpcData;

public class NPCHelper {

    public static void updateFroogleScale(float percentage) {
        Npc npc = FancyNpcsPlugin.get().getNpcManager().getNpc("Froogle");
        if (npc == null) {
            return;
        }

        NpcData npcData = npc.getData();
        npcData.setScale(0.5f + (0.045f * percentage));
        npc.updateForAll();
    }
}
