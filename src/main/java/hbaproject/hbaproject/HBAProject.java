package hbaproject.hbaproject;

import EventManager.onConnect;
import EventManager.onLeftClick;
import EventManager.onRightClick;
import PacketManager.PacketListeners;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.injector.PacketConstructor;
import com.comphenix.protocol.wrappers.EnumWrappers;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public final class HBAProject extends JavaPlugin {
    private static HBAProject instace;
    public ProtocolManager protocolManager;

    public static HBAProject getInstace() {
        return instace;
    }

    @Override
    public void onEnable() {
        instace = this;
        protocolManager = ProtocolLibrary.getProtocolManager();
        
        // 이벤트 등록
        getServer().getPluginManager().registerEvents(new onConnect(), this);
        getServer().getPluginManager().registerEvents(new onRightClick(), this);
        getServer().getPluginManager().registerEvents(new onLeftClick(), this);

        // 커맨드 등록

        Timer.MapDefaultSet();
        PacketListeners.PacketListen_ServerPlayerListText(this);
    }

    @Override
    public void onDisable() {

    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        if(label.contains("helloworld")) {

            Location targetLoc = new Location(p.getWorld(),0,0,0);
            ArrayList<Location> targetLocs = new ArrayList<>();

            int x1 = Integer.parseInt(""+Math.round(PlayerGlobal.pos1.get(p).getX()));
            int y1 = Integer.parseInt(""+Math.round(PlayerGlobal.pos1.get(p).getY()));
            int z1 = Integer.parseInt(""+Math.round(PlayerGlobal.pos1.get(p).getZ()));

            int x2 = Integer.parseInt(""+Math.round(PlayerGlobal.pos2.get(p).getX()));
            int y2 = Integer.parseInt(""+Math.round(PlayerGlobal.pos2.get(p).getY()));
            int z2 = Integer.parseInt(""+Math.round(PlayerGlobal.pos2.get(p).getZ()));

            int ax = (x1 > x2) ? -1 : 1;
            int ay = (y1 > y2) ? -1 : 1;
            int az = (z1 > z2) ? -1 : 1;

            // 윗면 검사 x -> z
            for(int lx = x1; lx != x2+ax; lx += ax) {
                for(int lz = z1; lz != z2+az; lz += az) {
                    Bukkit.broadcastMessage("x = "+lx+", z = "+lz);
                    targetLocs.add(targetLoc.clone().set(lx,y1,lz));
                }
            }

            // pos1에 대한 y -> x,z
            for(int ly = y1; ly != y2+ay; ly += ay) {
                for(int lx = x1; lx != x2+ax; lx += ax) {
                    Bukkit.broadcastMessage("x = "+lx);
                    targetLocs.add(targetLoc.clone().set(lx,ly,z1));
                }

                for(int lz = z1; lz != z2+az; lz += az) {
                    Bukkit.broadcastMessage("z = "+lz);
                    targetLocs.add(targetLoc.clone().set(x1,ly,lz));
                }
            }

            for(Location loc : targetLocs) {
                Bukkit.broadcastMessage("블럭 배치: x="+loc.getX()+", y="+loc.getY()+", z="+loc.getZ());
                loc.getBlock().setType(Material.RED_WOOL);
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

            for(Location loc : targetLocs) {
                Bukkit.broadcastMessage("블럭 배치: x="+loc.getX()+", y="+loc.getY()+", z="+loc.getZ());
                loc.getBlock().setType(Material.RED_WOOL);
            }
        }

        return false;
    }
}
