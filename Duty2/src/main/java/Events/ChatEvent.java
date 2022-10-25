package Events;

import duty.main.Main;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.metadata.FixedMetadataValue;


public class ChatEvent implements Listener {

    private final Main plugin;

    public ChatEvent(Main pl) {
        plugin = pl;
    }



    @EventHandler
    public void chatEvent(AsyncPlayerChatEvent e) {


        Player p = e.getPlayer();
        String m = e.getMessage();


        if (p.hasMetadata("PC")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("prefix", m);
                plugin.saveConfig();
                plugin.getMenu(p);
                p.removeMetadata("PC", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("PC", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getMenu(p);
            }



        }else if (p.hasMetadata("Defa")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("player", m);
                plugin.saveConfig();
                plugin.getRMenu(p);
                p.removeMetadata("Defa", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("Defa", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getRMenu(p);
            }



        }else if (p.hasMetadata("DP")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("dplayer", m);
                plugin.saveConfig();
                plugin.getRMenu(p);
                p.removeMetadata("DP", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("DP", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getRMenu(p);
            }



        }else if (p.hasMetadata("NP")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("noperm", m);
                plugin.saveConfig();
                plugin.getEgyeb(p);
                p.removeMetadata("NP", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("NP", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getEgyeb(p);
            }



        }else if (p.hasMetadata("dt")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                int im = Integer.parseInt(m);
                if (plugin.isInt(m)) {
                    plugin.getConfig().set("dutytime", im);
                    plugin.saveConfig();
                    plugin.getEgyeb(p);
                    p.removeMetadata("dt", plugin);
                    p.sendMessage("§aSuccessfully set!");
                }else {
                    p.sendMessage(plugin.prefix() + " §4You must enter a number!");
                    p.removeMetadata("dt", plugin);
                    plugin.getEgyeb(p);
                }


            }else {
                p.removeMetadata("dt", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getEgyeb(p);
            }

//----------------------------------------------------------------------------------------------

        }else if (p.hasMetadata("oncmd1")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command1", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("oncmd1", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("oncmd1", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("oncmd2")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command2", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("oncmd2", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("oncmd2", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("oncmd3")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command3", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("oncmd3", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("oncmd3", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("oncmd4")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command4", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("oncmd4", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("oncmd4", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("addoncmd1")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command1", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("addoncmd1", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoncmd1", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("addoncmd2")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command2", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("addoncmd2", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoncmd2", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("addoncmd3")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command3", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("addoncmd3", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoncmd3", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }



        }else if (p.hasMetadata("addoncmd4")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-on.Command4", m);
                plugin.saveConfig();
                plugin.getCmdOn(p);
                p.removeMetadata("addoncmd4", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoncmd4", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOn(p);
            }

//-----------------------------------------------------------------------------------------------------------------

        }else if (p.hasMetadata("offcmd1")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command1", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("offcmd1", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("offcmd1", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("offcmd2")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command2", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("offcmd2", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("offcmd2", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("offcmd3")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command3", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("offcmd3", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("offcmd3", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("offcmd4")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command4", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("offcmd4", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("offcmd4", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("addoffcmd1")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command1", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("addoffcmd1", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoffcmd1", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("addoffcmd2")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command2", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("addoffcmd2", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoffcmd2", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("addoffcmd3")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command3", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("addoffcmd3", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoffcmd3", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("addoffcmd4")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("Duty-off.Command4", m);
                plugin.saveConfig();
                plugin.getCmdOff(p);
                p.removeMetadata("addoffcmd4", plugin);
                p.sendMessage("§aSuccessfully set!");

            }else {
                p.removeMetadata("addoffcmd4", plugin);
                p.sendMessage("§cCanceled!");
                plugin.getCmdOff(p);
            }



        }else if (p.hasMetadata("AC.1")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                plugin.getConfig().set("player", m);
                plugin.saveConfig();
                p.removeMetadata("AC.1", plugin);
                p.sendMessage("§aSuccessfully set!");
                p.setMetadata("AC.2", new FixedMetadataValue(plugin, true));
                p.sendMessage("§72.§cWrite in chat what the name of the duty-player rank should be (Type CANCEL to cancel)");

            }else {
                p.removeMetadata("AC.1", plugin);
                p.sendMessage("§cCanceled!");
            }



        }else if (p.hasMetadata("AC.2")) {
            e.setCancelled(true);
            if (!e.getMessage().equalsIgnoreCase("cancel")) {
                    plugin.getConfig().set("dplayer", m);
                    plugin.saveConfig();
                    p.removeMetadata("AC.2", plugin);
                    plugin.getAC();
                    p.sendMessage("§aRank: §7" + plugin.dplayer() + " §acreated, use the /duty menu for further settings");


            }else {
                p.removeMetadata("AC.1", plugin);
                p.sendMessage("§cCanceled!");
            }



        }


    }

}
