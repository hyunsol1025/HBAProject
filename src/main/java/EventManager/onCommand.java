package EventManager;

import LyumapManager.Announce;
import RegionManager.RegionFunc;
import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class onCommand implements CommandExecutor {
    private HashMap<Player, ArrayList<Location>> Lyumap_Show = new HashMap<>();
    private HashMap<Player, Boolean> Lyumap_HideSelect = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;

        if(label.contains("hba-region")) {

            if(!p.isOp()) {
                p.sendMessage("§c§l오류! §f이 명령을 사용할 권한이 없어요..");
                return false;
            }

            if(args.length >= 2) {

                if(PlayerGlobal.pos1.get(p) != null && PlayerGlobal.pos2.get(p) != null) {

                    if(args[0].equals("create")) {

                        RegionFunc.setRegion(p,args[1],false);
                    }

                    else if(args[0].equals("delete") || args[0].equals("remove")) {

                        if(!ServerGlobal.Region_Locations.values().contains(args[1])) {
                            p.sendMessage("§c§l오류! §7"+args[1]+"§f(은)는 등록되지 않은 구역입니다.");
                            return false;
                        }

                        RegionFunc.setRegion(p,args[1], true);
                    }

                    else if(args[0].equals("show")) {
                        if(ServerGlobal.Region_Locations.containsValue(args[1])) {
                            RegionFunc.showRegionArea(p,args[1]);
                        } else {
                            p.sendMessage("§c§l오류! §f해당 구역은 등록되지 않았습니다.");
                        }
                    }

                    else if(args[0].equals("list")) {
                        for(String e : ServerGlobal.Region_Locations.values()) {
                            p.sendMessage("§f- "+e);
                        }
                    } else {
                        p.sendMessage("§c§l오류! §f명령 옵션이 제대로 설정되지 않았습니다.");
                    }

                } else {
                    p.sendMessage("§c§l오류! §f지역이 제대로 설정되지 않았습니다.");
                }

            } else {
                p.sendMessage("§c§l오류! §f명령 옵션이 제대로 설정되지 않았습니다.");
            }
        }

        else if(label.contains("hba-an")) {

            // 메타 데이터 고정
            if (args.length >= 2) {

                // 메타 데이터 고정
                if (args[0].equals("set-meta")) {

                    String tmpStr = "";

                    for (int i = 1; i < args.length; i++) {

                        if(i+1 == args.length) {
                            tmpStr += args[i];
                        } else {
                            tmpStr += args[i]+" ";
                        }

                    }

                    PlayerGlobal.Lyumap_FIXEDMETA.put(p, tmpStr);
                    p.sendMessage("§a§l확인! §f류맵 메타데이터가 고정되었습니다. [" + tmpStr + "]");

                }

                // 주소에 대한 안내 구역 표시/표시해제
                else if (args[0].equals("show")) {

                    Lyumap_Show.putIfAbsent(p, new ArrayList<>());

                    if (Lyumap_Show.get(p).size() == 0) {
                        BlockData b = Material.PURPLE_STAINED_GLASS.createBlockData();

                        ServerGlobal.Lyumap_Announce.forEach((key, value) -> {
                            Bukkit.broadcastMessage("비교중 : "+key+" / "+value);

                            for(String e : value) {
                                if(e.contains(args[1])) {
                                    p.sendBlockChange(key, b);
                                    Lyumap_Show.get(p).add(key);
                                }
                            }

                        });

                        p.sendMessage("§a§l류맵표시! §f류맵안내 구역을 표시합니다.");

                    } else {

                        for (Location e : Lyumap_Show.get(p)) {
                            p.sendBlockChange(e, e.getBlock().getBlockData());
                        }

                        Lyumap_Show.put(p, new ArrayList<>());
                        p.sendMessage("§7§l류맵표시해제! §f류맵안내 구역 표시를 해제합니다.");
                    }
                }

                // 멀티선택 관련
                else if(args[0].equals("multi-select")) {
                    Lyumap_HideSelect.putIfAbsent(p,false);

                    if(args[1].equals("hide")) {

                        if(Lyumap_HideSelect.get(p)) {

                            Lyumap_HideSelect.put(p,false);
                            p.sendMessage("§a§l선택보이기! §f선택한 블럭이 표시됩니다.");
                        } else {

                            Lyumap_HideSelect.put(p,true);
                            p.sendMessage("§7§l선택가리기! §f선택한 블럭 표시를 숨깁니다.");
                        }
                    }

                    else if(args[1].equals("set")) {
                        if(PlayerGlobal.Lyumap_MultiSelect.get(p).size() == 0) {
                            p.sendMessage("§c§l오류! §f선택한 블럭이 없습니다!");
                            return false;
                        }

                        // 선택된 블럭들에 대한 AnnounceControl
                        for(Location loc : PlayerGlobal.Lyumap_MultiSelect.get(p)) {
                            Announce.AnnounceControl(p,loc);
                        }
                    }
                }
            }

            // 류맵 수정 모드 활성화/비활성화
            else if (!PlayerGlobal.LYUMAP_REGMODE.get(p)) {
                PlayerGlobal.Lyumap_FIXEDMETA.putIfAbsent(p, "");

                if (PlayerGlobal.Lyumap_FIXEDMETA.get(p).equals("")) {
                    p.sendMessage("§c§l오류! §f입력된 류맵 메타데이터가 없습니다.");
                    return false;
                }

                PlayerGlobal.LYUMAP_REGMODE.put(p, true);
                p.sendMessage("§a§l류맵수정! §f류맵 수정모드가 활성화되었습니다.");

            } else {

                PlayerGlobal.LYUMAP_REGMODE.put(p, false);
                p.sendMessage("§c§l류맵수정! §f류맵수정 모드가 비활성화되었습니다.");
            }
        }

        return false;
    }
}
