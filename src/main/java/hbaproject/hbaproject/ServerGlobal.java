package hbaproject.hbaproject;

import org.bukkit.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class ServerGlobal {
    public static ArrayList<String> Server_PlayerListText = new ArrayList<>();

    // REGION MANAGER
    public static HashMap<Location, String> Region_Locations = new HashMap<>();

    // LYUMAP MANAGER
    public static ArrayList<Location> Lyumap = new ArrayList<>();
    public static HashMap<Location, ArrayList<String>> Lyumap_Announce = new HashMap<>();

}
