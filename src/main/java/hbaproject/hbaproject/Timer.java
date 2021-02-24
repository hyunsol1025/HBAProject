package hbaproject.hbaproject;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Timer {
    public static void MapDefaultSet() {
        new BukkitRunnable( ) {
            @Override
            public void run()
            {
                for(Player p : Bukkit.getOnlinePlayers()) {

                }
            }
        }.runTaskTimer(HBAProject.getInstace(), 0, 20);
    }
}
