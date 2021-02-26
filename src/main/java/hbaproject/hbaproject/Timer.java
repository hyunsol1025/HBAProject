package hbaproject.hbaproject;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class Timer {
    public static void MapDefaultSet() {
        new BukkitRunnable( ) {
            @Override
            public void run()
            {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    PlayerGlobal.Region_Show.putIfAbsent(p,false);

                    PlayerGlobal.LYUMAP_REGMODE.putIfAbsent(p,false);
                    PlayerGlobal.LYUMAP_TARGETADDRESS.putIfAbsent(p.getUniqueId(),"");
                    PlayerGlobal.Lyumap_CoolLoc.putIfAbsent(p,new ArrayList<>());
                }

            }
        }.runTaskTimer(HBAProject.getInstace(), 0, 20);
    }
}
