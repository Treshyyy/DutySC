package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;


public class DutyTabComplete implements TabCompleter {

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            List<String> duty = new ArrayList<>();
            duty.add("-t");
            if (sender.hasPermission("duty.admin")) {
                duty.add("reload");
                duty.add("menu");
                duty.add("help");
            }

            return duty;

        }

        return null;
    }

}
