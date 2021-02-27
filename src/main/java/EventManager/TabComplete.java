package EventManager;

import hbaproject.hbaproject.PlayerGlobal;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        ArrayList<String> returnList = new ArrayList<>();

        if(label.contains("hba-an")) {

            if(args.length == 1) {
                returnList.add("set-meta");
                returnList.add("show");
                returnList.add("multi-select");
            }

            else if(args.length == 2) {

                if(args[0].equals("multi-select")) {
                    returnList.add("hide");
                    returnList.add("set");
                }

            }
        }

        else if(label.contains("hba-region")) {
            returnList.add("create");
            returnList.add("delete");
            returnList.add("show");
            returnList.add("list");
        }

        return returnList;
    }
}
