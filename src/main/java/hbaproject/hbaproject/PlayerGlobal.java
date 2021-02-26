package hbaproject.hbaproject;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PlayerGlobal {
    public static HashMap<UUID, Inventory> inv = new HashMap<>();
    public static HashMap<Player, Location> pos1 = new HashMap<Player, Location>();
    public static HashMap<Player, Location> pos2 = new HashMap<Player, Location>();

    // REGION MANAGER
    public static HashMap<Player, Boolean> Region_Show = new HashMap<>();


    // PLAYER MANAGER
    public static HashMap<UUID, ArrayList<String>> Player_Address = new HashMap<>();

    // LYUMAP MANAGER
    public static HashMap<Player, Boolean> LYUMAP_REGMODE = new HashMap<>();        // 류맵설정 모드인가?
    public static HashMap<Player, String> Lyumap_FIXEDMETA = new HashMap<>();              // 류맵설정 고정 메타데이터

    public static HashMap<Player, ArrayList<Location>> Lyumap_CoolLoc = new HashMap<>();    // 중복 안내 쿨타임 좌표 목록

    public static HashMap<UUID, String> LYUMAP_TARGETADDRESS = new HashMap<>();   // 경로를 안내 중인가?
}
