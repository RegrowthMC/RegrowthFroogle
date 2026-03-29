package org.lushplugins.regrowthfroogle;

import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.placeholderhandler.PlaceholderHandler;
import org.lushplugins.regrowthfroogle.listener.ItemPoolListener;
import org.lushplugins.regrowthfroogle.placeholder.Placeholders;

public final class RegrowthFroogle extends SpigotPlugin {

    @Override
    public void onEnable() {
        registerListener(new ItemPoolListener());

        PlaceholderHandler.builder(this)
            .build()
            .register(new Placeholders());
    }
}
