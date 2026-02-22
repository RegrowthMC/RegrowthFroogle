package org.lushplugins.regrowthfroogle;

import org.bukkit.plugin.java.JavaPlugin;

public final class RegrowthFroogle extends JavaPlugin {
    private static RegrowthFroogle plugin;

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        // Enable implementation
    }

    @Override
    public void onDisable() {
        // Disable implementation
    }

    public static RegrowthFroogle getInstance() {
        return plugin;
    }
}
