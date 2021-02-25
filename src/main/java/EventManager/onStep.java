package EventManager;

import PlayerManager.PlayerFunc;
import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class onStep implements Listener {

    @EventHandler
    void onStep(PlayerMoveEvent e) {

        if(e.getFrom().getX() == e.getTo().getX() && e.getFrom().getY() == e.getTo().getY() && e.getFrom().getZ() == e.getTo().getZ()) return;

        Location loc = new Location(e.getPlayer().getWorld(),
                Integer.parseInt(""+Math.round(e.getTo().getX())),
                Integer.parseInt(""+Math.round(e.getTo().getY())),
                Integer.parseInt(""+Math.round(e.getTo().getZ())));

        if(!ServerGlobal.Region_Locations.containsKey(loc)) return;

        Player p = e.getPlayer();
        String newAddress = ServerGlobal.Region_Locations.get(loc);

        PlayerFunc.setAddress(p.getUniqueId(),newAddress);
    }
}
