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
                    PlayerGlobal.Region_Show.putIfAbsent(p,false);

                    PlayerGlobal.LYUMAP_REGMODE.putIfAbsent(p,false);
                    PlayerGlobal.LYUMAP_ANNOUNCEMODE.putIfAbsent(p,false);
                }
            }
        }.runTaskTimer(HBAProject.getInstace(), 0, 20);
    }
}
