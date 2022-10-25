package Events;

import duty.main.Main;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Objects;


public class InvClick implements Listener {

    private final Main plugin;

    public InvClick(Main pl) {
        plugin = pl;
    }

    @EventHandler
    public void dropEvent(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getItemDrop().getItemStack().equals(plugin.tpcompass)) {
            e.setCancelled(true);
        }


    }

    @EventHandler
    public void invClick(InventoryClickEvent e) {

        if (e.getCurrentItem() == null) {
            return;
        }


        Player p = (Player) e.getWhoClicked();

        if (Objects.equals(e.getClickedInventory(), p.getInventory())) {

            if (e.getCurrentItem().equals(plugin.tpcompass)) {
                if (e.getSlot() == 8) {
                    e.setCancelled(true);
                    p.closeInventory();
                }

            }

        }


        if (e.getView().getTitle().equalsIgnoreCase("§bDuty Settings")) {


            switch (e.getCurrentItem().getType()) {
                case NAME_TAG -> {
                    p.closeInventory();
                    p.openInventory(plugin.Rang(p));
                }
                case BARRIER -> {
                    p.closeInventory();
                    p.sendMessage("§cWrite down the prefix you want to change in the chat(Type CANCEL to cancel)");
                    p.setMetadata("PC", new FixedMetadataValue(plugin, true));
                }
                case RED_WOOL -> {
                    plugin.getConfig().set("dutytp", true);
                    plugin.saveConfig();
                    p.openInventory(plugin.Menu(p));
                }
                case LIME_WOOL -> {
                    plugin.getConfig().set("dutytp", false);
                    plugin.saveConfig();
                    p.openInventory(plugin.Menu(p));
                }
                case PAPER -> {
                    p.closeInventory();
                    p.openInventory(plugin.Egyeb(p));
                }
                case COMMAND_BLOCK -> {
                    p.closeInventory();
                    p.openInventory(plugin.cmds(p));
                }
            }


            e.setCancelled(true);


        } else if (e.getView().getTitle().equalsIgnoreCase("§9Rank configuration")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case RED_STAINED_GLASS:
                    p.closeInventory();
                    p.openInventory(plugin.Menu(p));
                    break;
                case BOOK:
                    if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName().equalsIgnoreCase("§eDefault rank: §7§o§n" + plugin.player())) {
                        p.closeInventory();
                        p.setMetadata("Defa", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite down the name of the default rank in chat(Type CANCEL to cancel)");
                        break;
                    } else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§eDuty rank: §7§o§n" + plugin.dplayer())) {
                        p.closeInventory();
                        p.setMetadata("DP", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§crite down the name of the 'Duty' rank in chat(Type CANCEL to cancel)");
                        break;
                    }


            }
        }else if (e.getView().getTitle().equalsIgnoreCase("§bOther settings")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case RED_STAINED_GLASS -> {
                    p.closeInventory();
                    p.openInventory(plugin.Menu(p));
                }
                case ENCHANTED_BOOK -> {
                    p.closeInventory();
                    p.setMetadata("NP", new FixedMetadataValue(plugin, true));
                    p.sendMessage("§cWrite down what the text of NoPerm should be (Type CANCEL to cancel)");
                }
                case BEACON -> {
                    p.closeInventory();
                    p.setMetadata("dt", new FixedMetadataValue(plugin, true));
                    p.sendMessage("§cWrite down in numbers how many seconds the time limit for using /duty should be in chat (Type CANCEL to cancel)");
                }
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("§6Commands")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case RED_STAINED_GLASS -> {
                    p.closeInventory();
                    p.openInventory(plugin.Menu(p));
                }
                case DIAMOND_SWORD -> {
                    p.closeInventory();
                    p.openInventory(plugin.cmdOn(p));
                }
                case COMPASS -> {
                    p.closeInventory();
                    p.openInventory(plugin.cmdOff(p));
                }
            }
        }else if (e.getView().getTitle().equalsIgnoreCase("§dDuty - ON commands")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case PAPER:
                    if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName().equalsIgnoreCase("§bCommand1")) {
                        p.closeInventory();
                        p.setMetadata("oncmd1", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the first command should be (Type CANCEL to cancel)");
                        break;
                    }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCommand2")) {
                        p.closeInventory();
                        p.setMetadata("oncmd2", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the second command should be (Type CANCEL to cancel)");
                        break;
                    }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCommand3")) {
                        p.closeInventory();
                        p.setMetadata("oncmd3", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the third command should be (Type CANCEL to cancel)");
                        break;
                    }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCommand4")) {
                        p.closeInventory();
                        p.setMetadata("oncmd4", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the fourth command should be (Type CANCEL to cancel)");
                        break;
                    }

                case NETHER_STAR:
                    if (plugin.getConfig().getString("Duty-on.Command1") == null) {
                        p.closeInventory();
                        p.setMetadata("addoncmd1", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the first command should be (Type CANCEL to cancel)");
                        break;
                    }else if (plugin.getConfig().getString("Duty-on.Command2") == null) {
                        if (plugin.getConfig().getString("Duty-on.Command1") != null) {
                            p.closeInventory();
                            p.setMetadata("addoncmd2", new FixedMetadataValue(plugin, true));
                            p.sendMessage("§cWrite in chat what the second command should be (Type CANCEL to cancel)");
                            break;
                        }
                    }else if (plugin.getConfig().getString("Duty-on.Command3") == null) {
                        if (plugin.getConfig().getString("Duty-on.Command2") != null) {
                            if (plugin.getConfig().getString("Duty-on.Command1") != null) {
                                p.closeInventory();
                                p.setMetadata("addoncmd3", new FixedMetadataValue(plugin, true));
                                p.sendMessage("§cWrite in chat what the third command should be (Type CANCEL to cancel)");
                                break;
                            }
                        }
                    }else if (plugin.getConfig().getString("Duty-on.Command4") == null) {
                        if (plugin.getConfig().getString("Duty-on.Command3") != null) {
                            if (plugin.getConfig().getString("Duty-on.Command2") != null) {
                                if (plugin.getConfig().getString("Duty-on.Command1") != null) {
                                    p.closeInventory();
                                    p.setMetadata("addoncmd4", new FixedMetadataValue(plugin, true));
                                    p.sendMessage("§cWrite in chat what the fourth command should be (Type CANCEL to cancel)");
                                    break;
                                }
                            }
                        }
                    }

                case RED_STAINED_GLASS:
                    p.closeInventory();
                    p.openInventory(plugin.cmds(p));
                    break;



            }
        }else if (e.getView().getTitle().equalsIgnoreCase("§dDuty - OFF commands")) {
            e.setCancelled(true);

            switch (e.getCurrentItem().getType()) {
                case PAPER:
                    if (Objects.requireNonNull(e.getCurrentItem().getItemMeta()).getDisplayName().equalsIgnoreCase("§bCommand1")) {
                        p.closeInventory();
                        p.setMetadata("offcmd1", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the first command should be (Type CANCEL to cancel)");
                        break;
                    }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCommand2")) {
                        p.closeInventory();
                        p.setMetadata("offcmd2", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the second command should be (Type CANCEL to cancel)");
                        break;
                    }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCommand3")) {
                        p.closeInventory();
                        p.setMetadata("offcmd3", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the third command should be (Type CANCEL to cancel)");
                        break;
                    }else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bCommand4")) {
                        p.closeInventory();
                        p.setMetadata("offcmd4", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the fourth command should be (Type CANCEL to cancel)");
                        break;
                    }

                case NETHER_STAR:
                    if (plugin.getConfig().getString("Duty-off.Command1") == null) {
                        p.closeInventory();
                        p.setMetadata("addoffcmd1", new FixedMetadataValue(plugin, true));
                        p.sendMessage("§cWrite in chat what the first command should be (Type CANCEL to cancel)");
                        break;
                    }else if (plugin.getConfig().getString("Duty-off.Command2") == null) {
                        if (plugin.getConfig().getString("Duty-off.Command1") != null) {
                            p.closeInventory();
                            p.setMetadata("addoffcmd2", new FixedMetadataValue(plugin, true));
                            p.sendMessage("§cWrite in chat what the second command should be (Type CANCEL to cancel)");
                            break;
                        }
                    }else if (plugin.getConfig().getString("Duty-off.Command3") == null) {
                        if (plugin.getConfig().getString("Duty-off.Command2") != null) {
                            if (plugin.getConfig().getString("Duty-off.Command1") != null) {
                                p.closeInventory();
                                p.setMetadata("addoffcmd3", new FixedMetadataValue(plugin, true));
                                p.sendMessage("§cWrite in chat what the third command should be (Type CANCEL to cancel)");
                                break;
                            }
                        }
                    }else if (plugin.getConfig().getString("Duty-off.Command4") == null) {
                        if (plugin.getConfig().getString("Duty-off.Command3") != null) {
                            if (plugin.getConfig().getString("Duty-off.Command2") != null) {
                                if (plugin.getConfig().getString("Duty-off.Command1") != null) {
                                    p.closeInventory();
                                    p.setMetadata("addoffcmd4", new FixedMetadataValue(plugin, true));
                                    p.sendMessage("§cWrite in chat what the fourth command should be (Type CANCEL to cancel)");
                                    break;
                                }
                            }
                        }
                    }

                case RED_STAINED_GLASS:
                    p.closeInventory();
                    p.openInventory(plugin.cmds(p));
                    break;



            }
        }
    }
}
