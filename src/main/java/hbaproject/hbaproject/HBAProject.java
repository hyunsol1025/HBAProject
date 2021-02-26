package hbaproject.hbaproject;

import EventManager.*;
import LyumapManager.Announce;
import PacketManager.PacketListeners;
import PlayerManager.PlayerFunc;
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
import java.util.Arrays;

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
        getServer().getPluginManager().registerEvents(new onStep(),this);

        // 커맨드 등록
        this.getCommand("hba-region").setExecutor(new onCommand());
        this.getCommand("hba-an").setExecutor(new onCommand());

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
            PlayerGlobal.LYUMAP_TARGETADDRESS.put(p.getUniqueId(),"서울시");

            Bukkit.broadcastMessage("Address: "+PlayerFunc.getAddress(p.getUniqueId()));

            //for(Player p2 : Bukkit.getOnlinePlayers()) {
            //    ServerGlobal.Lyumap_Announce.put(p.getLocation(),new ArrayList<>(Arrays.asList("잭스초@5@현솔IC@right")));
            //    Announce.LetAnnounce(p,p.getLocation(),"잭스초");
            //}
        }

        return false;
    }
}
