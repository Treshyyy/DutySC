package Commands;

import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

import static duty.main.Main.perms;

public class RoleTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {

        if (args.length == 1) {
            List<String> role = new ArrayList<>();
            role.add("set");
            role.add("remove");


            return role;

        }else if (args.length == 2) {
            List<String> plyrnames = new ArrayList<>();
            Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
            Bukkit.getServer().getOnlinePlayers().toArray(players);
            for (int i = 0; i < players.length; i++) {
                plyrnames.add(players[i].getName());
            }


            return plyrnames;

        }else if (args.length == 3) {
            if (args[0].equalsIgnoreCase("set")) {


                List<String> roles = new ArrayList<>();
                for (int i = 0; i < perms.getGroups().length; i++) {
                    roles.add(ArrayUtils.toString(perms.getGroups()[i]));


                }
                return roles;

            }
        }

        return null;
    }
}
