package EventManager;

import hbaproject.hbaproject.PlayerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class onRightClick implements Listener {
    @EventHandler
    void onRightClick(PlayerInteractEvent e) {
        Player p = e.getPlayer();

        if((e.getAction() != Action.RIGHT_CLICK_BLOCK && e.getAction() != Action.RIGHT_CLICK_AIR) || p.getInventory().getItemInMainHand().getType() == Material.AIR) return;

        ItemStack item = p.getInventory().getItemInMainHand();

        if(item.getType() == Material.NAME_TAG) {
            PlayerGlobal.pos1.put(p,e.getClickedBlock().getLocation());
            Bukkit.broadcastMessage(""+e.getClickedBlock().getLocation());
        }
    }

}
