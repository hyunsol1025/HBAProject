package RegionManager;

import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class RegionFunc {

    // 구역지정
    public static void setRegion(Player author, String regionName, Boolean deleteMode) {
        Location targetLoc = new Location(author.getWorld(),0,0,0);
        ArrayList<Location> targetLocs = new ArrayList<>();

        int x1 = Integer.parseInt(""+Math.round(PlayerGlobal.pos1.get(author).getX()));
        int y1 = Integer.parseInt(""+Math.round(PlayerGlobal.pos1.get(author).getY()));
        int z1 = Integer.parseInt(""+Math.round(PlayerGlobal.pos1.get(author).getZ()));

        int x2 = Integer.parseInt(""+Math.round(PlayerGlobal.pos2.get(author).getX()));
        int y2 = Integer.parseInt(""+Math.round(PlayerGlobal.pos2.get(author).getY()));
        int z2 = Integer.parseInt(""+Math.round(PlayerGlobal.pos2.get(author).getZ()));

        int ax = (x1 > x2) ? -1 : 1;
        int ay = (y1 > y2) ? -1 : 1;
        int az = (z1 > z2) ? -1 : 1;

        // 윗면 검사 x -> z
        for(int lx = x1; lx != x2+ax; lx += ax) {
            for(int lz = z1; lz != z2+az; lz += az) {
                targetLocs.add(targetLoc.clone().set(lx,y1,lz));
            }
        }

        // pos1에 대한 y -> x,z
        for(int ly = y1; ly != y2+ay; ly += ay) {
            for(int lx = x1; lx != x2+ax; lx += ax) {
                targetLocs.add(targetLoc.clone().set(lx,ly,z1));
            }

            for(int lz = z1; lz != z2+az; lz += az) {
                targetLocs.add(targetLoc.clone().set(x1,ly,lz));
            }
        }

        ax = (x1 < x2) ? -1 : 1;
        ay = (y1 < y2) ? -1 : 1;
        az = (z1 < z2) ? -1 : 1;

        // pos2에 대한 y -> x,z
        for(int lx = x2; lx != x1+ax; lx += ax) {
            for(int lz = z2; lz != z1+az; lz += az) {
                Bukkit.broadcastMessage("x = "+lx+", z = "+lz);
                targetLocs.add(targetLoc.clone().set(lx,y2,lz));
            }
        }

        for(int ly = y2; ly != y1+ay; ly += ay) {
            for(int lx = x2; lx != x1+ax; lx += ax) {
                Bukkit.broadcastMessage("x = "+lx);
                targetLocs.add(targetLoc.clone().set(lx,ly,z2));
            }

            for(int lz = z2; lz != z1+az; lz += az) {
                Bukkit.broadcastMessage("z = "+lz);
                targetLocs.add(targetLoc.clone().set(x2,ly,lz));
            }
        }

        if(!deleteMode) {

            // 구역설정
            for(Location loc : targetLocs) {
                if(!ServerGlobal.Region_Locations.get(loc).isEmpty()) {
                    author.sendMessage("§6§l경고! §f이미 지정된 구역: §f"+loc.getX()+", §f"+loc.getY()+", "+loc.getZ()+" -> "+ServerGlobal.Region_Locations.get(loc));
                } else {
                    ServerGlobal.Region_Locations.put(loc,regionName);
                }
            }
        } else {

            // 구역제거
            for(Location loc : targetLocs) {
                ServerGlobal.Region_Locations.remove(loc);
            }
        }

        author.sendMessage("§a§l구역지정 §f구역지정을 완료함 -> "+regionName);
    }

    // 이미 지정된 구역 표시
    public static void getRegionArea(Player author, String regionName) {

        if(!PlayerGlobal.Region_Show.get(author)) {
            BlockData b = Material.LIGHT_BLUE_STAINED_GLASS.createBlockData();

            if (regionName.equals("all")) {

                ServerGlobal.Region_Locations.forEach((key, value) -> {
                    author.sendBlockChange(key, b);
                });

                return;
            }

            ServerGlobal.Region_Locations.forEach((key, value) -> {
                if (value.equals(regionName)) {
                    author.sendBlockChange(key, b);
                }
            });

            author.sendMessage("§a§l구역표시! §f현재 §7"+regionName+"§f 구역에 해당되는 좌표에 블럭을 배치했습니다!");
            PlayerGlobal.Region_Show.put(author,true);
        } else {

            ServerGlobal.Region_Locations.forEach((key, value) -> {
                author.sendBlockChange(key, key.getBlock().getBlockData());
            });

            author.sendMessage("§7§l구역표시해제! §f배치했던 블럭을 돌려놓았습니다.");
            PlayerGlobal.Region_Show.put(author,false);
        }
    }
}
