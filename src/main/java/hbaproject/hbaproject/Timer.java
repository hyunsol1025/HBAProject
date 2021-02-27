package hbaproject.hbaproject;

import PlayerManager.PlayerFunc;
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

    public static void setTabListTimer() {
        new BukkitRunnable( ) {
            @Override
            public void run()
            {
                for(Player p : Bukkit.getOnlinePlayers()) {
                    PlayerFunc.setTabList(p,"\uE002\n\n\n","\n§f    현재 §a§l"+Bukkit.getOnlinePlayers().size()+"§f명의 시민들과 함께하고 있습니다!§f    \n");
                }
            }
        }.runTaskTimer(HBAProject.getInstace(), 0, 10);
    }
}
