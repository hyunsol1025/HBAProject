package CarManager;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.ArrayList;
import java.util.List;

public class CarFunc {

    // 자동차 아이템 식별 인덱스:
    // 0 : 자동차 기종
    // 1 : 자동차 주인
    // 2 : 현재연료
    // 3 : 내구성
    // 4 : 마지막 자동차 사용 시간

    public static void spawnCar(Player author, ItemStack item) {
        World w = author.getWorld();
        Entity e = w.spawnEntity(author.getLocation(),EntityType.MINECART);

        List<String> itemLore = item.getLore();

        e.setCustomName(itemLore.get(0)+"|"+itemLore.get(1)+"|"+itemLore.get(2)+"|"+itemLore.get(3)+"|"+itemLore.get(4));
        e.setCustomNameVisible(false);
        author.getInventory().remove(item);
    }
}
