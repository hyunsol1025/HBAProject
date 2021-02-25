package EventManager;

import RegionManager.RegionFunc;
import hbaproject.hbaproject.PlayerGlobal;
import hbaproject.hbaproject.ServerGlobal;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class onCommand implements CommandExecutor {
    private HashMap<Player, String> TargetAddress = new HashMap<>();

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

                } else {
                    p.sendMessage("§c§l오류! §f지역이 제대로 설정되지 않았습니다.");
                }
            }
        }

        else if(label.contains("hba-an")) {

            if(args.length >= 1) {

                TargetAddress.put(p, args[0]);
                PlayerGlobal.LYUMAP_REGMODE.put(p,true);

            } else {

                PlayerGlobal.LYUMAP_REGMODE.put(p,false);
            }
        }
        return false;
    }
}
