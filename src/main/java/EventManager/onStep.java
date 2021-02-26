package EventManager;

import LyumapManager.Announce;
import PlayerManager.PlayerFunc;
import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class onStep implements Listener {

    @EventHandler
    void onStep(PlayerMoveEvent e) {

        if(e.getFrom().getX() == e.getTo().getX() && e.getFrom().getY() == e.getTo().getY() && e.getFrom().getZ() == e.getTo().getZ()) return;

        Player p = e.getPlayer();
        Location locToBlock = p.getLocation().toBlockLocation();

        locToBlock.setPitch(0);
        locToBlock.setYaw(0);

        // 플레이어 주소 변경
        if(ServerGlobal.Region_Locations.containsKey(locToBlock)) {
            Bukkit.broadcastMessage("구역에 입장함!");

            String newAddress = ServerGlobal.Region_Locations.get(locToBlock);
            PlayerFunc.setAddress(p.getUniqueId(),newAddress);
            return;
        }

        // 류맵: 안내
        if(!PlayerGlobal.LYUMAP_TARGETADDRESS.get(p.getUniqueId()).equals("") &&
            ServerGlobal.Lyumap_Announce.containsKey(locToBlock)) {

            Announce.LetAnnounce(p,locToBlock,PlayerGlobal.LYUMAP_TARGETADDRESS.get(p.getUniqueId()));
        }


    }
}
