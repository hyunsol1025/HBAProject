package EventManager;

import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
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

        if(ServerGlobal.Region_Locations.containsKey(loc)) {
            
        }
    }
}
