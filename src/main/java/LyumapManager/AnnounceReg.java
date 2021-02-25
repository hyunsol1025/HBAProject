package LyumapManager;

import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AnnounceReg {

    // 안내 추가
    public static void AddAnnounce(Location targetLoc, String targetAddress, String AnnounceMeta) {
        for(String e : ServerGlobal.Lyumap_Announce.get(targetLoc)) {
            if(e.contains(targetAddress+"-")) {
                ServerGlobal.Lyumap_Announce.get(targetLoc).remove(targetAddress+"-"+AnnounceMeta);
                break;
            }
        }

        ServerGlobal.Lyumap_Announce.get(targetLoc).add(targetAddress+"-"+AnnounceMeta);
    }

    // 안내 삭제
    public static void DelAnnounce(Location targetLoc, String targetAddress) {
        int i = 0;

        for(String e : ServerGlobal.Lyumap_Announce.get(targetLoc)) {
            if(e.contains(targetAddress+"-")) {
                ServerGlobal.Lyumap_Announce.get(targetLoc).remove(i);
                break;
            }

            i++;
        }
    }
}
