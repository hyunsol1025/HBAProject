package PlayerManager;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;
import hbaproject.hbaproject.HBAProject;
import hbaproject.hbaproject.PlayerGlobal;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

public class PlayerFunc {

    // 플레이어의 현재 주소 알아내기
    public static ArrayList<String> getAddress(UUID targetUUID) {
        return PlayerGlobal.Player_Address.get(targetUUID);
    }

    // 플레이어 주소 설정
    public static void setAddress(UUID targetUUID, String newAddress) {

        if(newAddress.contains("@S")) {
            PlayerGlobal.Player_Address.put(targetUUID,new ArrayList<>());
            PlayerGlobal.Player_Address.get(targetUUID).add(newAddress.replace("@S", ""));
        }

        // 새로운 구로 진입
        if(newAddress.contains("@G")) {
            if(getAddress(targetUUID).size() != 1) PlayerGlobal.Player_Address.get(targetUUID).remove(1);

            PlayerGlobal.Player_Address.get(targetUUID).add(1, newAddress.replace("@G", ""));
        }

        // 새로운 동로 진입
        if(newAddress.contains("@D")) {
            if(getAddress(targetUUID).size() != 2) PlayerGlobal.Player_Address.get(targetUUID).remove(2);

            PlayerGlobal.Player_Address.get(targetUUID).add(2,newAddress.replace("@D", ""));
        }

        // 새로운 구역으로 진입
        if(newAddress.contains("@O")) {
            if(getAddress(targetUUID).size() != 3) PlayerGlobal.Player_Address.get(targetUUID).remove(3);

            PlayerGlobal.Player_Address.get(targetUUID).add(3,newAddress.replace("@O", ""));
        }

    }

    // 플레이어 TAP LIST HEADER & FOOTER 변경
    public static void setTabList(Player author, String header, String footer) {

        PacketContainer packet = HBAProject.getInstace().protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);

        if(header != null) {
            packet.getChatComponents().write(0, WrappedChatComponent.fromText(header));
        }

        if(footer != null) {
            packet.getChatComponents().write(1, WrappedChatComponent.fromText(footer));
        }

        try {
            HBAProject.getInstace().protocolManager.sendServerPacket(author,packet);
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
