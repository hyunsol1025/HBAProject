package LyumapManager;

import hbaproject.hbaproject.HBAProject;
import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class Announce {

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

        // 경로 안내 종료
        if(ServerGlobal.Lyumap_AddressLocation.containsKey(targetAddress) && ServerGlobal.Lyumap_AddressLocation.get(targetAddress).getNearbyPlayers(30,0,30).contains(author)) {
            PlayerGlobal.LYUMAP_ANNOUNCEMODE.put(author,false);
            author.sendActionBar("§5§l[ 류맵 ] §f경로 안내를 종료합니다.");
            author.playSound(author.getLocation(),"lyumap.finish",1,0);
            return;
        }

        Bukkit.broadcastMessage("진입!");
        if(!ServerGlobal.Lyumap_Announce.containsKey(targetLoc)) return;

        String[] AnnounceMeta = new String[0];

        for(String e : ServerGlobal.Lyumap_Announce.get(targetLoc)) {
            if(e.contains(targetAddress)) {
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
                author.playSound(author.getLocation(),"lyumap.5",1,1);
                ttsWaitTick = 20;
                break;

            case "10":
                author.playSound(author.getLocation(),"lyumap.10",1,1);
                ttsWaitTick = 20;
                break;

            case "50":
                author.playSound(author.getLocation(),"lyumap.50",1,1);
                ttsWaitTick = 20;
                break;

            case "100":
                author.playSound(author.getLocation(),"lyumap.100",1,1);
                ttsWaitTick = 20;
                break;

            case "NA":
                author.playSound(author.getLocation(),"lyumap.na",1,1);
                return;
        }

        String finalAnnounceMeta = AnnounceMeta[3];

        Bukkit.getScheduler().runTaskLater(HBAProject.getInstace(), () -> {
            switch(finalAnnounceMeta) {
                case "right":
                    author.playSound(author.getLocation(),"lyumap.goright",1,1);
                    break;

                case "left":
                    author.playSound(author.getLocation(),"lyumap.goleft",1,1);
                    break;

            }
        },ttsWaitTick);
    }
}
