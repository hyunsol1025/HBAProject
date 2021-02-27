package PacketManager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLib;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedServerPing;
import hbaproject.hbaproject.HBAProject;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class PacketListeners {
    public static void PacketListen_PacketListenStart(HBAProject hba) {

        // 서버 플레이어 목록 변경
        HBAProject.getInstace().protocolManager.addPacketListener(new PacketAdapter(hba, ListenerPriority.NORMAL, PacketType.Status.Server.OUT_SERVER_INFO) {
            @Override
            public void onPacketSending(PacketEvent event) {
                ArrayList<WrappedGameProfile> tmpList = new ArrayList<>();

                int strIndex = 0;

                for(String e : ServerGlobal.Server_PlayerListText) {
                    strIndex++;
                    tmpList.add(new WrappedGameProfile("id"+strIndex,e));
                }

                WrappedServerPing serverPing = (WrappedServerPing) event.getPacket().getServerPings().read(0);
                serverPing.setPlayers(tmpList);
            }

        });

    }
}
