package org.lushplugins.regrowthfroogle;

import org.bukkit.Bukkit;
import org.lushplugins.itempools.ItemPools;
import org.lushplugins.itempools.goal.Goal;
import org.lushplugins.itempools.pool.ItemPool;
import org.lushplugins.lushlib.utils.plugin.SpigotPlugin;
import org.lushplugins.placeholderhandler.PlaceholderHandler;
import org.lushplugins.regrowthfroogle.listener.ItemPoolListener;
import org.lushplugins.regrowthfroogle.placeholder.Placeholders;
import org.lushplugins.regrowthfroogle.util.NPCHelper;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public final class RegrowthFroogle extends SpigotPlugin {
    private static RegrowthFroogle plugin;

    private LocalDateTime nextFeedingTime = LocalDate.now()
        .withDayOfMonth(1)
        .atTime(16, 0, 0);

    @Override
    public void onLoad() {
        plugin = this;
    }

    @Override
    public void onEnable() {
        registerListener(new ItemPoolListener());

        PlaceholderHandler.builder(this)
            .build()
            .register(new Placeholders());


        // Makes Froogle slowly shrink during the last 2 weeks before feeding time
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            LocalDateTime now = LocalDateTime.now();
            Duration duration = Duration.between(now, getNextFeedingTime());
            if (duration.toDays() > 14) {
                return;
            }

            ItemPool pool = ItemPools.getInstance().getItemPoolManager().getItemPool("froogle");
            if (pool == null) {
                return;
            }

            Goal goal = pool.getGoals().iterator().next();
            if (goal.getValue() < goal.getGoal()) {
                return;
            }

            float percentage = Math.max(0, (1 - ((float) duration.getSeconds() / TimeUnit.DAYS.toSeconds(14))) * 100);
            NPCHelper.updateFroogleScale(percentage);
        }, 300, 72000); // Hourly
    }

    public LocalDateTime getNextFeedingTime() {
        if (!LocalDateTime.now().isBefore(nextFeedingTime)) {
            nextFeedingTime = nextFeedingTime.plusMonths(1);
        }

        return nextFeedingTime;
    }

    public static RegrowthFroogle getInstance() {
        return plugin;
    }
}
