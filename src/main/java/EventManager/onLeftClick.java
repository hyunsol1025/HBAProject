package EventManager;

import LyumapManager.Announce;
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

import java.util.ArrayList;

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

            // 여러 블럭 선택
            if(p.isSneaking()) {
                PlayerGlobal.Lyumap_MultiSelect.putIfAbsent(p,new ArrayList<>());

                if (PlayerGlobal.Lyumap_MultiSelect.get(p).contains(targetLocation)) {

                    PlayerGlobal.Lyumap_MultiSelect.get(p).remove(targetLocation);
                    p.sendBlockChange(targetLocation, targetLocation.getBlock().getBlockData());
                    p.sendMessage("§c§l선택해제! §f해당 블럭에 대한 선택이 해제되었습니다.");
                } else {

                    PlayerGlobal.Lyumap_MultiSelect.get(p).add(targetLocation);
                    p.sendBlockChange(targetLocation, Material.LIME_CONCRETE.createBlockData());
                    p.sendMessage("§a§l선택! §f해당 블럭을 선택하였습니다.");
                }

                return;
            }

            Announce.AnnounceControl(p,targetLocation);
        }
    }

}
