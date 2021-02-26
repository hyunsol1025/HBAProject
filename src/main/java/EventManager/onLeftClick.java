package EventManager;

import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onLeftClick implements Listener {
    @EventHandler
    void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if((e.getAction() != Action.LEFT_CLICK_BLOCK && e.getAction() != Action.LEFT_CLICK_AIR) || p.getInventory().getItemInMainHand().getType() == Material.AIR) return;
        if(e.getClickedBlock() == null) return;

        ItemStack item = p.getInventory().getItemInMainHand();

        // REGION MANAGER
        if(item.getType() == Material.NAME_TAG) {
            e.setCancelled(true);
            PlayerGlobal.pos2.put(p,e.getClickedBlock().getLocation());
            Bukkit.broadcastMessage(""+e.getClickedBlock().getLocation());
        }

        // LYUMAP MANAGER
        if(PlayerGlobal.LYUMAP_REGMODE.get(p)) {
            e.setCancelled(true);

            Location targetLocation = e.getClickedBlock().getLocation().clone().add(0,1,0).toBlockLocation();

            // 이미 등록된 주소라면 삭제로 간주
            for(String loopStr : ServerGlobal.Lyumap_Announce.get(targetLocation)) {
                if(loopStr.contains(PlayerGlobal.Lyumap_FIXEDMETA.get(p).split("@")[0])) {
                    ServerGlobal.Lyumap_Announce.get(targetLocation).remove(loopStr);
                    p.sendMessage("§c§l안내삭제! §f한 블럭 위의 류맵안내 메타 데이터§7("+loopStr+"§7)§f가 제거되었습니다.");
                    return;
                }
            }

            ServerGlobal.Lyumap_Announce.get(targetLocation).add(PlayerGlobal.Lyumap_FIXEDMETA.get(p));
            p.sendMessage("§a§l류맵수정! §f류맵안내 메타 데이터가 추가되었습니다.");
        }
    }

}
