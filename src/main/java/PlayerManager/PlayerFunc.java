package PlayerManager;

import hbaproject.hbaproject.PlayerGlobal;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
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
            PlayerGlobal.Player_Address.get(targetUUID).add(newAddress.replace("@S ", ""));
        }

        // 새로운 구로 진입
        if(newAddress.contains("@G")) {
            if(getAddress(targetUUID).size() != 1) PlayerGlobal.Player_Address.get(targetUUID).remove(1);

            PlayerGlobal.Player_Address.get(targetUUID).add(1, newAddress.replace("@G ", ""));
        }

        // 새로운 동로 진입
        if(newAddress.contains("@D")) {
            if(getAddress(targetUUID).size() != 2) PlayerGlobal.Player_Address.get(targetUUID).remove(2);

            PlayerGlobal.Player_Address.get(targetUUID).add(2,newAddress.replace("@D ", ""));
        }

        // 새로운 구역으로 진입
        if(newAddress.contains("@O")) {
            if(getAddress(targetUUID).size() != 3) PlayerGlobal.Player_Address.get(targetUUID).remove(3);

            PlayerGlobal.Player_Address.get(targetUUID).add(3,newAddress.replace("@O ", ""));
        }

    }
}
