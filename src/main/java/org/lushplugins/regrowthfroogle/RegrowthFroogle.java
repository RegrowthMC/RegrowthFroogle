package org.lushplugins.regrowthfroogle;

import org.lushplugins.lushlib.plugin.SpigotPlugin;
import org.lushplugins.regrowthfroogle.listener.ItemPoolListener;

public final class RegrowthFroogle extends SpigotPlugin {

    @Override
    public void onEnable() {
        registerListener(new ItemPoolListener());
    }
}
