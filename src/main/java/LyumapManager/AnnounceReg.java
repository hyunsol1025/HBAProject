package LyumapManager;

import hbaproject.hbaproject.HBAProject;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class AnnounceReg {

    /*

    AnnounceMeta 배열 ------------------

    [0] -> 타깃 주소
    [1] -> 안내 사항의 언급 거리 [N/5/10/50/100/다음 안내시(NA)]
    [2] -> 안내 사항의 언급 장소 -> 안내 사항의 언급 장소는 도로명이나 지하철 역이름으로 할것!
    [3] -> 안내 방향
     */


    // 안내 추가
    public static void AddAnnounce(Location targetLoc, String targetAddress, String AnnounceMeta) {
        for(String e : ServerGlobal.Lyumap_Announce.get(targetLoc)) {
            if(e.contains(targetAddress+"-")) {
                ServerGlobal.Lyumap_Announce.get(targetLoc).remove(targetAddress+"@"+AnnounceMeta);
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

    // 안내 출력
    public static void LetAnnounce(Player author, Location targetLoc, String targetAddress) {
        if(!ServerGlobal.Lyumap_Announce.containsKey(targetLoc)) return;

        String[] AnnounceMeta = new String[0];

        for(String e : ServerGlobal.Lyumap_Announce.get(targetLoc)) {
            if(e.contains(targetAddress+"-")) {
                AnnounceMeta = e.split("@");
                break;
            }
        }

        // TTS 출력
        int ttsWaitTick = 0;

        // 장소로 안내
        switch(AnnounceMeta[2]) {

        }

        // 거리로 안내
        switch(AnnounceMeta[1]) {
            case "5":
                author.playSound(author.getLocation(),"lyumap.5",1,0);
                ttsWaitTick = 20;
                break;

            case "10":
                author.playSound(author.getLocation(),"lyumap.10",1,0);
                ttsWaitTick = 20;
                break;

            case "50":
                author.playSound(author.getLocation(),"lyumap.50",1,0);
                ttsWaitTick = 20;
                break;

            case "100":
                author.playSound(author.getLocation(),"lyumap.100",1,0);
                ttsWaitTick = 20;
                break;

            case "NA":
                author.playSound(author.getLocation(),"lyumap.na",1,0);
                ttsWaitTick = 40;
                return;
        }

        String finalAnnounceMeta = AnnounceMeta[3];

        Bukkit.getScheduler().runTaskLater(HBAProject.getInstace(), () -> {
            switch(finalAnnounceMeta) {
                case "right":
                    break;
                case "left":
                    break;
            }
        },ttsWaitTick);
    }
}
