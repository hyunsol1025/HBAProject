package EventManager;

import PlayerManager.PlayerFunc;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import hbaproject.hbaproject.HBAProject;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.lang.reflect.InvocationTargetException;

public class onConnect implements Listener {

    @EventHandler
    void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();

        Bukkit.getScheduler().runTaskLater(HBAProject.getInstace(), () -> {
            PlayerFunc.setTabList(p,"\uE002\n\n\n","\n§f    현재 §a§l"+Bukkit.getOnlinePlayers().size()+"§f명의 시민들과 함께하고 있습니다!§f    \n");
        },2);
    }
}
