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
}
