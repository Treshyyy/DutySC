package duty.main;

import Commands.DutyTabComplete;
import Commands.RoleTabComplete;
import Events.ChatEvent;
import Events.InvClick;
import Files.DutyData;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.permission.Permission;
import org.apache.commons.lang.ArrayUtils;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;

import java.io.File;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.List;


public class Main extends JavaPlugin {








    public ItemStack tpcompass = new ItemStack(Material.COMPASS);
    public ItemMeta tpc_m = tpcompass.getItemMeta(); {
        assert tpc_m != null;
        tpc_m.setDisplayName("§8» §bTeleporter");
    tpcompass.setItemMeta(tpc_m);
}
    public static Permission perms = null;
    private static Chat chat = null;





    public HashMap<String, Long> cooldowns = new HashMap<>();

    public Inventory Menu(Player p) {


        Inventory menu = Bukkit.createInventory(p, 9, "§bDuty Settings");


        ItemStack RK = new ItemStack(Material.NAME_TAG);
        ItemStack Prefix = new ItemStack(Material.BARRIER);
        ItemStack Egyeb = new ItemStack(Material.PAPER);
        ItemStack TPO = new ItemStack(Material.LIME_WOOL);
        ItemStack TPOFF = new ItemStack(Material.RED_WOOL);
        ItemStack cmd = new ItemStack(Material.COMMAND_BLOCK);

        ItemMeta RK_m = RK.getItemMeta();
        assert RK_m != null;
        RK_m.setDisplayName("§9Rank configuration");

        ArrayList<String> RK_l = new ArrayList<>();
        RK_l.add("§5Click for more");
        RK_m.setLore(RK_l);
        RK.setItemMeta(RK_m);


        ItemMeta Prefix_m = Prefix.getItemMeta();
        assert Prefix_m != null;
        Prefix_m.setDisplayName("§dPrefix: " + prefix());

        ArrayList<String> Prefix_l = new ArrayList<>();
        Prefix_l.add("§5The plugin's prefix");
        Prefix_m.setLore(Prefix_l);
        Prefix.setItemMeta(Prefix_m);


        ItemMeta Egyeb_m = Egyeb.getItemMeta();
        assert Egyeb_m != null;
        Egyeb_m.setDisplayName("§cOthers");

        ArrayList<String> Egyeb_l = new ArrayList<>();
        Egyeb_l.add("§5Other Settings");
        Egyeb_m.setLore(Egyeb_l);
        Egyeb.setItemMeta(Egyeb_m);


        ItemMeta TPO_m = TPO.getItemMeta();
        assert TPO_m != null;
        TPO_m.setDisplayName("§7Duty - off teleportation: §aON");

        ArrayList<String> TPO_l = new ArrayList<>();
        TPO_l.add("§5Duty teleport");
        TPO_m.setLore(TPO_l);
        TPO.setItemMeta(TPO_m);


        ItemMeta TPOFF_m = TPOFF.getItemMeta();
        assert TPOFF_m != null;
        TPOFF_m.setDisplayName("§7Duty - off teleportation: §cOFF");

        ArrayList<String> TPOFF_l = new ArrayList<>();
        TPOFF_l.add("§5Duty teleport");
        TPOFF_m.setLore(TPOFF_l);
        TPOFF.setItemMeta(TPOFF_m);


        ItemMeta cmd_m = cmd.getItemMeta();
        assert cmd_m != null;
        cmd_m.setDisplayName("§6Duty on-off commands");

        ArrayList<String> cmd_l = new ArrayList<>();
        cmd_l.add("Commands that will run when using /duty ");
        cmd_m.setLore(cmd_l);
        cmd.setItemMeta(cmd_m);

        menu.setItem(0, RK);
        menu.setItem(1, Prefix);
        menu.setItem(4, Egyeb);
        if (!this.getConfig().getBoolean("dutytp")) {
            menu.setItem(8, TPOFF);
        } else if (this.getConfig().getBoolean("dutytp")) {
            menu.setItem(8, TPO);
        }
        menu.setItem(7, cmd);
        return menu;
    }



    public Inventory Rang(Player p) {

        Inventory RANGK = Bukkit.createInventory(p, 9, "§9Rank configuration");

        ItemStack defa = new ItemStack(Material.BOOK);
        ItemStack dplayer = new ItemStack(Material.BOOK);
        ItemStack back = new ItemStack(Material.RED_STAINED_GLASS);

        ItemMeta defa_m = defa.getItemMeta();
        assert defa_m != null;
        defa_m.setDisplayName("§eDefault rank: §7§o§n" + player());

        ArrayList<String> defa_l = new ArrayList<>();
        defa_l.add("§5The 'Duty' rank's name");
        defa_m.setLore(defa_l);
        defa.setItemMeta(defa_m);


        ItemMeta dplayer_m = dplayer.getItemMeta();
        assert dplayer_m != null;
        dplayer_m.setDisplayName("§eDuty rang: §7§o§n" + dplayer());

        ArrayList<String> dplayer_l = new ArrayList<>();
        dplayer_l.add("§5The default rank's name");
        dplayer_m.setLore(dplayer_l);
        dplayer.setItemMeta(dplayer_m);


        ItemMeta back_m = back.getItemMeta();
        assert back_m != null;
        back_m.setDisplayName("§cBack");
        back.setItemMeta(back_m);

        RANGK.setItem(2, defa);
        RANGK.setItem(6, dplayer);
        RANGK.setItem(8, back);

        return RANGK;
    }


    public Inventory Egyeb(Player p) {

        Inventory Egyeb = Bukkit.createInventory(p, 9, "§bOther settings");

        ItemStack dutytime = new ItemStack(Material.BEACON);
        ItemStack noperm = new ItemStack(Material.ENCHANTED_BOOK);
        ItemStack back = new ItemStack(Material.RED_STAINED_GLASS);

        ItemMeta dutytime_m = dutytime.getItemMeta();
        assert dutytime_m != null;
        dutytime_m.setDisplayName("§eDuty cooldown: §7§n" + dutytime() + "mp");

        ArrayList<String> dutytime_l = new ArrayList<>();
        dutytime_l.add("/duty command cooldown");
        dutytime_m.setLore(dutytime_l);
        dutytime.setItemMeta(dutytime_m);


        ItemMeta noperm_m = noperm.getItemMeta();
        assert noperm_m != null;
        noperm_m.setDisplayName("§eNo Perm message: §n" + noperm());

        ArrayList<String> noperm_l = new ArrayList<>();
        noperm_l.add("No Perm message");
        noperm_m.setLore(noperm_l);
        noperm.setItemMeta(noperm_m);


        ItemMeta back_m = back.getItemMeta();
        assert back_m != null;
        back_m.setDisplayName("§cBack");
        back.setItemMeta(back_m);


        Egyeb.setItem(2, dutytime);
        Egyeb.setItem(6, noperm);
        Egyeb.setItem(8, back);

        return Egyeb;
    }



    public Inventory cmds(Player p) {

        Inventory cmds = Bukkit.createInventory(p, 9, "§6Commands");

        ItemStack pl = new ItemStack(Material.DIAMOND_SWORD);
        ItemStack dp = new ItemStack(Material.COMPASS);
        ItemStack back = new ItemStack(Material.RED_STAINED_GLASS);

        ItemMeta pl_m = pl.getItemMeta();
        assert pl_m != null;
        pl_m.setDisplayName("§7Duty - OFF");

        ArrayList<String> pl_l = new ArrayList<>();
        pl_l.add("Duty - OFF Commands");
        pl_m.setLore(pl_l);
        pl.setItemMeta(pl_m);


        ItemMeta dp_m = dp.getItemMeta();
        assert dp_m != null;
        dp_m.setDisplayName("§7Duty - ON");

        ArrayList<String> dp_l = new ArrayList<>();
        dp_l.add("Duty - ON Commands");
        dp_m.setLore(dp_l);
        dp.setItemMeta(dp_m);


        ItemMeta back_m = back.getItemMeta();
        assert back_m != null;
        back_m.setDisplayName("§cBack");
        back.setItemMeta(back_m);


        cmds.setItem(2, pl);
        cmds.setItem(6, dp);
        cmds.setItem(8, back);

        return cmds;
    }




    public Inventory cmdOn(Player p) {

        Inventory cmdOn = Bukkit.createInventory(p, 9, "§dDuty - ON Commands");

        ItemStack command1 = new ItemStack(Material.PAPER);
        ItemStack command2 = new ItemStack(Material.PAPER);
        ItemStack command3 = new ItemStack(Material.PAPER);
        ItemStack command4 = new ItemStack(Material.PAPER);
        ItemStack POncmd = new ItemStack(Material.NETHER_STAR);
        ItemStack delete = new ItemStack(Material.BEDROCK);
        ItemStack back = new ItemStack(Material.RED_STAINED_GLASS);

        ItemMeta cmd1_m = command1.getItemMeta();
        assert cmd1_m != null;
        cmd1_m.setDisplayName("§bCommand1");

        ArrayList<String> cmd1_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-on.Command1") != null) {
            cmd1_l.add("§8§oCommand: " + this.getConfig().getString("Duty-on.Command1"));
            cmd1_m.setLore(cmd1_l);
        }
        command1.setItemMeta(cmd1_m);

        ItemMeta cmd2_m = command2.getItemMeta();
        assert cmd2_m != null;
        cmd2_m.setDisplayName("§bCommand2");

        ArrayList<String> cmd2_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-on.Command2") != null) {
            cmd2_l.add("§8§oCommand: " + this.getConfig().getString("Duty-on.Command2"));
            cmd2_m.setLore(cmd2_l);
        }
        command2.setItemMeta(cmd2_m);

        ItemMeta cmd3_m = command3.getItemMeta();
        assert cmd3_m != null;
        cmd3_m.setDisplayName("§bCommand3");

        ArrayList<String> cmd3_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-on.Command3") != null) {
            cmd3_l.add("§8§oCommand: " + this.getConfig().getString("Duty-on.Command3"));
            cmd3_m.setLore(cmd3_l);
        }
        command3.setItemMeta(cmd3_m);

        ItemMeta cmd4_m = command4.getItemMeta();
        assert cmd4_m != null;
        cmd4_m.setDisplayName("§bCommand4");

        ArrayList<String> cmd4_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-on.Command4") != null) {
            cmd4_l.add("§8§oCommand: " + this.getConfig().getString("Duty-on.Command4"));
            cmd4_m.setLore(cmd4_l);
        }
        command4.setItemMeta(cmd4_m);

        ItemMeta POncmd_m = POncmd.getItemMeta();
        assert POncmd_m != null;
        POncmd_m.setDisplayName("§aAdd command");

        ArrayList<String> POncmd_l = new ArrayList<>();
        POncmd_l.add("Add new Command");
        POncmd_m.setLore(POncmd_l);
        POncmd.setItemMeta(POncmd_m);

        ItemMeta delete_m = delete.getItemMeta();
        assert delete_m != null;
        delete_m.setDisplayName("§cRemove Command");

        ArrayList<String> delete_l = new ArrayList<>();
        delete_l.add("In order to avoid problems, please delete Commands in the config file");
        delete_m.setLore(delete_l);
        delete.setItemMeta(delete_m);

        ItemMeta back_m = back.getItemMeta();
        assert back_m != null;
        back_m.setDisplayName("§cBack");
        back.setItemMeta(back_m);


        if (this.getConfig().getString("Duty-on.Command1") != null) {
            cmdOn.setItem(0, command1);
        }

        if (this.getConfig().getString("Duty-on.Command2") != null) {
            cmdOn.setItem(1, command2);
        }

        if (this.getConfig().getString("Duty-on.Command3") != null) {
            cmdOn.setItem(2, command3);
        }

        if (this.getConfig().getString("Duty-on.Command4") != null) {
            cmdOn.setItem(3, command4);
        }

        if (this.getConfig().getString("Duty-on.Command4") == null) {
            cmdOn.setItem(6, POncmd);
        }
        cmdOn.setItem(7, delete);
        cmdOn.setItem(8, back);


        return cmdOn;
    }




    public Inventory cmdOff(Player p) {

        Inventory cmdOff = Bukkit.createInventory(p, 9, "§dDuty - OFF Commands");

        ItemStack command1 = new ItemStack(Material.PAPER);
        ItemStack command2 = new ItemStack(Material.PAPER);
        ItemStack command3 = new ItemStack(Material.PAPER);
        ItemStack command4 = new ItemStack(Material.PAPER);
        ItemStack POffcmd = new ItemStack(Material.NETHER_STAR);
        ItemStack delete = new ItemStack(Material.BEDROCK);
        ItemStack back = new ItemStack(Material.RED_STAINED_GLASS);

        ItemMeta cmd1_m = command1.getItemMeta();
        assert cmd1_m != null;
        cmd1_m.setDisplayName("§bCommand1");

        ArrayList<String> cmd1_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-off.Command1") != null) {
            cmd1_l.add("§8§oCommand: " + this.getConfig().getString("Duty-off.Command1"));
            cmd1_m.setLore(cmd1_l);
        }
        command1.setItemMeta(cmd1_m);

        ItemMeta cmd2_m = command2.getItemMeta();
        assert cmd2_m != null;
        cmd2_m.setDisplayName("§bCommand2");

        ArrayList<String> cmd2_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-off.Command2") != null) {
            cmd2_l.add("§8§oCommand: " + this.getConfig().getString("Duty-off.Command2"));
            cmd2_m.setLore(cmd2_l);
        }
        command2.setItemMeta(cmd2_m);

        ItemMeta cmd3_m = command3.getItemMeta();
        assert cmd3_m != null;
        cmd3_m.setDisplayName("§bCommand3");

        ArrayList<String> cmd3_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-off.Command3") != null) {
            cmd3_l.add("§8§oCommand: " + this.getConfig().getString("Duty-off.Command3"));
            cmd3_m.setLore(cmd3_l);
        }
        command3.setItemMeta(cmd3_m);

        ItemMeta cmd4_m = command4.getItemMeta();
        assert cmd4_m != null;
        cmd4_m.setDisplayName("§bCommand4");

        ArrayList<String> cmd4_l = new ArrayList<>();
        if (this.getConfig().getString("Duty-off.Command4") != null) {
            cmd4_l.add("§8§oCommand: " + this.getConfig().getString("Duty-off.Command4"));
            cmd4_m.setLore(cmd4_l);
        }
        command4.setItemMeta(cmd4_m);

        ItemMeta POffcmd_m = POffcmd.getItemMeta();
        assert POffcmd_m != null;
        POffcmd_m.setDisplayName("§aAdd command");

        ArrayList<String> POffcmd_l = new ArrayList<>();
        POffcmd_l.add("Add new Command");
        POffcmd_m.setLore(POffcmd_l);
        POffcmd.setItemMeta(POffcmd_m);

        ItemMeta delete_m = delete.getItemMeta();
        assert delete_m != null;
        delete_m.setDisplayName("§cRemove Command");

        ArrayList<String> delete_l = new ArrayList<>();
        delete_l.add("In order to avoid problems, please delete Commands in the config file");
        delete_m.setLore(delete_l);
        delete.setItemMeta(delete_m);

        ItemMeta back_m = back.getItemMeta();
        assert back_m != null;
        back_m.setDisplayName("§cBack");
        back.setItemMeta(back_m);


        if (this.getConfig().getString("Duty-off.Command1") != null) {
            cmdOff.setItem(0, command1);
        }

        if (this.getConfig().getString("Duty-off.Command2") != null) {
            cmdOff.setItem(1, command2);
        }

        if (this.getConfig().getString("Duty-off.Command3") != null) {
            cmdOff.setItem(2, command3);
        }

        if (this.getConfig().getString("Duty-off.Command4") != null) {
            cmdOff.setItem(3, command4);
        }

        if (this.getConfig().getString("Duty-off.Command4") == null) {
            cmdOff.setItem(6, POffcmd);
        }
        cmdOff.setItem(7, delete);
        cmdOff.setItem(8, back);


        return cmdOff;
    }




    public void getCmdOn(Player p) {
        Bukkit.getScheduler().runTask(this, () -> p.openInventory(cmdOn(p)));


    }

    public void getCmdOff(Player p) {
        Bukkit.getScheduler().runTask(this, () -> p.openInventory(cmdOff(p)));


    }






    public void getMenu(Player p) {
        Bukkit.getScheduler().runTask(this, () -> p.openInventory(Menu(p)));


    }


    public void getRMenu(Player p) {
        Bukkit.getScheduler().runTask(this, () -> p.openInventory(Rang(p)));


    }


    public void getEgyeb(Player p) {
        Bukkit.getScheduler().runTask(this, () -> p.openInventory(Egyeb(p)));


    }


    public boolean isInt(String text){
        try{
            Integer.parseInt(text);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }




    @Override
    public void onEnable() {

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();


        File Duty;


        Duty = new File("plugins/Duty/dutydata");
        if (!Duty.exists()) {
            Duty.mkdirs();
        }

//     String key = getConfig().getString("licence-key");
//     try{
//         String url = "https://pastebin.com/raw/" + key;
//         URLConnection openConnection = new URL(url).openConnection();
//         @SuppressWarnings("resource")
//         //Here we are then reading from the webpage
//         Scanner scan = new Scanner((new InputStreamReader(openConnection.getInputStream())));
//         while(scan.hasNextLine()){
//             String firstline = scan.nextLine();
//             if(firstline.contains("Duty plugin - Licence kulcs")){
//                 String customer = scan.nextLine();
//                 this.getLogger().info("§aDuty plugin sikeresen aktiválva §7- Kulcs: " + key + ", " + customer + ".");
               if (getServer().getPluginManager().getPlugin("LuckPerms") != null) {
                     if (getServer().getPluginManager().getPlugin("Vault") != null) {


                        getLogger().info("§bDuty plugin §7- §aSuccessfully loaded!");




                        setupPermissions();
                        setupChat();


                        getServer().getPluginManager().registerEvents(new ChatEvent(this), this);
                        getServer().getPluginManager().registerEvents(new InvClick(this), this);
                        getCommand("duty").setTabCompleter(new DutyTabComplete());
                        getCommand("role").setTabCompleter(new RoleTabComplete());
                    }else {
                        getLogger().info("§cVault plugin is not detected! - Shuting down");
                        getServer().getPluginManager().disablePlugin(this);
                    }
                 }else {
                     getLogger().info("§cLuckPerms plugin is not detected! - Shuting down");
                     getServer().getPluginManager().disablePlugin(this);
                }
    }
    // }
        //}catch(Exception e){


        //}
        //We return the method having disabled the plugin because it hasnt already been returned.
        //this.getLogger().info("§cA plugint nem sikerült aktiválni! Kérem ellnőrizze le, hogy jó kulcsot írt be.");
        //Bukkit.getPluginManager().disablePlugin(this);






    private boolean setupChat() {
        RegisteredServiceProvider<Chat> rsp = getServer().getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }







        public String prefix() {

            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("prefix")));
        }

        public String noperm() {

            return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(this.getConfig().getString("noperm")));
        }




    public String dplayer() {

        return this.getConfig().getString("dplayer");
    }

    public String player() {

        return this.getConfig().getString("player");
    }

    public boolean dutytp() {

        return this.getConfig().getBoolean("dutytp");
    }

    public int dutytime() {

        return this.getConfig().getInt("dutytime");
    }


    public void autoconf() {
        ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
        Bukkit.dispatchCommand(console, "lp group " + getConfig().getString("player") + " clone " + getConfig().getString("dplayer"));
        Bukkit.dispatchCommand(console, "lp group " + getConfig().getString("dplayer") + " parent add " + getConfig().getString("player"));
        Bukkit.dispatchCommand(console, "lp group " + getConfig().getString("dplayer") + " permission set duty.use");
        Bukkit.dispatchCommand(console, "lp group " + getConfig().getString("dplayer") + " setdisplayname " + dplayer());
        Bukkit.dispatchCommand(console, "lp group " + getConfig().getString("dplayer") + " setweight 1");
    }

    public void getAC() {
        Bukkit.getScheduler().runTask(this, this::autoconf);


    }






    @Override
    public void onDisable() {
        getLogger().info("§bDuty plugin §7- §cUnloaded successfully!");


    }


    public static boolean isPlayerInGroup(Player player, String group) {
        return player.hasPermission("group." + group);
    }


    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        DutyData dutyData;
        if (sender instanceof  Player) {
                Player p = (Player) sender;

                if (label.equalsIgnoreCase("dconfirm")) {
                    if (p.hasPermission("duty.reset")) {

                        if (p.hasMetadata("d.reset")) {
                            getConfig().set("prefix", " ");
                            getConfig().set("noperm", " ");
                            getConfig().set("player", "?");
                            getConfig().set("dplayer", "?");
                            getConfig().set("dutytp", false);
                            getConfig().set("dutytime", 5);
                            getConfig().set("Duty-on.Command1", null);
                            getConfig().set("Duty-on.Command2", null);
                            getConfig().set("Duty-on.Command3", null);
                            getConfig().set("Duty-on.Command4", null);
                            getConfig().set("Duty-off.Command1", null);
                            getConfig().set("Duty-off.Command2", null);
                            getConfig().set("Duty-off.Command3", null);
                            getConfig().set("Duty-off.Command4", null);
                            saveConfig();
                            p.sendMessage("§dEvery settings has been reset!");
                            p.removeMetadata("d.reset", this);
                        } else {
                            p.sendMessage("§bThere are no restore attempts in progress");
                        }
                    }else {
                        p.sendMessage(prefix() + " " + noperm());
                    }
                }



                if (label.equalsIgnoreCase("role")) {
                    if (p.hasPermission("role.set")) {
                        if (!dplayer().equalsIgnoreCase("?")) {
                            if (args.length == 3) {
                                if (args[0].equalsIgnoreCase("set")) {

                                    Player pp = Bukkit.getPlayer(args[1]);
                                    OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);


                                    if (ArrayUtils.contains(perms.getGroups(), args[2])) {
                                        dutyData = new DutyData(this, args[1]);
                                        if (pp != null) {
                                            if ((dutyData.getData("Rang") == null)) {
                                                dutyData.roleSet(pp, args[2]);
                                                dutyData.save();
                                                p.sendMessage(prefix() + " §c" + args[1] + " §7Is now §c " + args[2] + " §7on the server");
                                                pp.sendMessage(prefix() + " §7You are now " + "§c" + args[2] + " §7on the server");
                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                String commandr = "lp user " + pp.getName() + " parent set " + dplayer();
                                                Bukkit.dispatchCommand(console, commandr);

                                            } else if (!dutyData.getData("Rang").equals(args[2])) {
                                                dutyData.roleSet(pp, args[2]);
                                                dutyData.save();
                                                p.sendMessage(prefix() + " §c" + args[1] + " §7Is now §c " + args[2] + " §7on the server");
                                                pp.sendMessage(prefix() + " §7You are now " + "§c" + args[2] + " §7on the server");
                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                String commandr = "lp user " + pp.getName() + " parent set " + dplayer();
                                                Bukkit.dispatchCommand(console, commandr);
                                            } else {
                                                p.sendMessage(prefix() + " §c " + args[1] + " §7already has this rank");
                                            }


                                        } else {
                                            dutyData = new DutyData(this, args[1]);
                                            if ((dutyData.getData("Rang") == null)) {

                                                dutyData.roleSet(op, args[2]);
                                                dutyData.save();
                                                getLogger().info(prefix() + " §c" + args[1] + " §7Is now §c " + args[2] + " §7on the server");
                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                String commandr = "lp user " + op.getName() + " parent set " + dplayer();
                                                Bukkit.dispatchCommand(console, commandr);

                                            } else if (!dutyData.getData("Rang").equals(args[2])) {
                                                dutyData.roleSet(op, args[2]);
                                                dutyData.save();
                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                String commandr = "lp user " + op.getName() + " parent set " + dplayer();
                                                Bukkit.dispatchCommand(console, commandr);

                                            } else {
                                                p.sendMessage(prefix() + " §c " + args[1] + " §7already has this rank");
                                            }
                                        }


                                        //p.sendMessage("§cEnnek a játékosnak nem volt még kezelhető rangja on the server!");


                                    } else {
                                        p.sendMessage(prefix() + " §cError! This rank does not exist!");
                                    }

                                } else if (!args[0].equalsIgnoreCase("remove")) {
                                    p.sendMessage(prefix() + " §7Correct usage: §b/role <set/remove> <player name> [<rank>]");
                                }

                            } else if (args.length >= 1) {
                                if (args[0].equalsIgnoreCase("remove")) {


                                    Player pp = Bukkit.getPlayer(args[1]);
                                    OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);

                                    dutyData = new DutyData(this, args[1]);
                                    if (pp != null) {
                                        if (dutyData.getData("Rang") == null) {
                                            p.sendMessage(prefix() + " §7This player has no rank");
                                        } else {

                                            p.sendMessage(prefix() + " §7The " + "§b" + dutyData.getData("Rang") + " §7rank from" + " §b" + args[1] + " §7successfully removed.");
                                            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                            String commandr = "lp user " + pp.getName() + " parent set " + player();
                                            Bukkit.dispatchCommand(console, commandr);
                                            dutyData.delete();
                                        }
                                    } else {
                                        if (dutyData.getData("Rang") == null) {
                                            p.sendMessage(prefix() + " §7This player has no rank");
                                        } else {

                                            p.sendMessage(prefix() + " §7The " + "§b" + dutyData.getData("Rang") + " §7rank from" + " §b" + args[1] + " §7successfully removed.");
                                            ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                            String commandr = "lp user " + op.getName() + " parent set " + player();
                                            Bukkit.dispatchCommand(console, commandr);
                                            dutyData.delete();
                                        }

                                    }

                                }

                                if (!args[0].equalsIgnoreCase("remove")) {
                                    p.sendMessage(prefix() + " §7Correct usage: §b/role <set/remove> <player name> [<rank>]");

                                }
                            } else {
                                p.sendMessage(prefix() + " §7Correct usage: §b/role <set/remove> <player name> [<rank>]");
                            }
                        } else {
                            p.sendMessage("§cError! Plugin is not set! For more information -> §b/duty help");
                        }


                    } else {
                        p.sendMessage(prefix() + " " + noperm());
                    }
                }


                    if (sender instanceof  Player) {

                        if (label.equalsIgnoreCase("duty")) {


                            if ((args.length == 0) || (args.length == 1) || (args.length == 2)) {
                                if ((args.length == 1) || (args.length == 2)) {
                                    if (!args[0].equalsIgnoreCase("-t")) {
                                        if (args[0].equalsIgnoreCase("reload")) {
                                            if (p.hasPermission("duty.reload")) {
                                                reloadConfig();
                                                p.sendMessage(prefix() + " §bPlugin reloaded successfully!");


                                            } else {
                                                p.sendMessage(prefix() + " " + noperm());
                                            }
                                            return false;


                                        } else if (args[0].equalsIgnoreCase("menu")) {
                                            if (p.hasPermission("duty.admin")) {

                                                p.openInventory(Menu(p));

                                            } else {
                                             p.sendMessage(prefix() + " " + noperm());
                                             }


                                            return false;
                                        } else if (args[0].equalsIgnoreCase("help")) {
                                            if (p.hasPermission("duty.admin")) {
                                                if (args.length == 1) {


                                                    //TextComponent szoveg2 = new TextComponent(" §bkattíntva ezt elvégzem pár másodperc alatt!");
                                                    TextComponent szoveg = new TextComponent("You can also do this operation manually, then you have to enter the data in §7/duty menu §b, but if you think so, I can do it in a few seconds by clicking");
                                                    TextComponent click = new TextComponent(" HERE!");

                                                    click.setColor(net.md_5.bungee.api.ChatColor.RED);
                                                    szoveg.setColor(net.md_5.bungee.api.ChatColor.AQUA);
                                                    //szoveg2.setColor(net.md_5.bungee.api.ChatColor.AQUA);


                                                    click.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/duty autoconfig"));

                                                    p.sendMessage("§8help list (1/2)");
                                                    p.sendMessage("§7<--------------------------------------------->");
                                                    p.sendMessage("§bHi, thank you for purchasing the Plugin!");
                                                    p.sendMessage(" ");
                                                    p.sendMessage("§bTo start, you need to create a new rank, which does exactly the same as the default rank, except that you have to give it a §7duty.use §b permisison.");
                                                    p.sendMessage(" ");
                                                    p.spigot().sendMessage(szoveg, click);
                                                    p.sendMessage("§7<--------------------------------------------->");


                                                } else if (args.length == 2) {
                                                    if (args[1].equalsIgnoreCase("2")) {
                                                        p.sendMessage("§8help list (2/2)");
                                                        p.sendMessage("§c<--------------------------------------------->");
                                                        p.sendMessage("§cPermissions:");
                                                        p.sendMessage("§c- duty.use - /duty usage");
                                                        p.sendMessage("§c- duty.reload - Reloading the config file");
                                                        p.sendMessage("§c- duty.use.inf - /duty cooldown bypass");
                                                        p.sendMessage("§c- duty.admin - Admin perm (/duty menu, /duty help)");
                                                        p.sendMessage("§c- duty.reset - Config file reset");
                                                        p.sendMessage("§c- role.set - Rang set/remove");
                                                        p.sendMessage("§c<--------------------------------------------->");
                                                    }
                                                }
                                            } else {
                                                p.sendMessage(prefix() + " " + noperm());
                                            }
                                            return false;
                                        } else if (args[0].equalsIgnoreCase("autoconfig")) {
                                            if (p.hasPermission("duty.admin")) {
                                                if (!getConfig().getString("dplayer").equalsIgnoreCase("?")) {
                                                    p.sendMessage("§dThe plugin is already set up!");
                                                } else {
                                                    p.sendMessage("§71.§cWrite down the name of the default rank in the chat (if displayname is specified, then it) (Type CANCEL to cancel)");
                                                    p.setMetadata("AC.1", new FixedMetadataValue(this, true));
                                                }
                                                return false;
                                            } else {
                                                p.sendMessage(prefix() + " " + noperm());
                                            }
                                        } else if (args[0].equalsIgnoreCase("reset")) {
                                            if (p.hasPermission("duty.reset")) {
                                                p.setMetadata("d.reset", new FixedMetadataValue(this, true));
                                                p.sendMessage("§bAre you sure? With this you delete all settings in the script §7(/dconfirm - You have 10 sec to enter)");
                                                Bukkit.getScheduler().runTaskLater(this, () -> {
                                                    p.removeMetadata("d.reset", this);
                                                }, 200L);

                                            } else {
                                                p.sendMessage(prefix() + " " + noperm());
                                            }
                                        }
                                        return false;


                                    }
                                }


                                if (p.hasPermission("duty.use")) {

                                    if (!getConfig().getString("dplayer").equalsIgnoreCase("?")) {


                                        int cooldownTime = dutytime();
                                        if (cooldowns.containsKey(p.getName())) {
                                            long secondsLeft = ((cooldowns.get(sender.getName()) / 1000) + cooldownTime) - (System.currentTimeMillis() / 1000);
                                            if (secondsLeft > 0) {
                                                if (!p.hasPermission("duty.use.inf")) {
                                                    p.sendMessage(prefix() + " §cPlease wait §7" + secondsLeft + " §cseconds before using this Command again");

                                                    return true;

                                                }


                                            }

                                        }


                                        cooldowns.put(p.getName(), System.currentTimeMillis());
                                        dutyData = new DutyData(this, p.getName());
                                        if (dutyData.getData("Rang") != null) {


                                            if (perms.getPrimaryGroup(p).equals(dplayer())) {


                                                p.sendMessage(prefix() + " §7Duty: §aON §7- You are in duty now.");


                                                dutyData.expSet(p.getLevel());
                                                dutyData.potEff(p.getActivePotionEffects());
                                                for (PotionEffect effect : p.getActivePotionEffects())
                                                    p.removePotionEffect(effect.getType());
                                                dutyData.HealthSet(p.getHealth());
                                                dutyData.FoodSet(p.getFoodLevel());
                                                dutyData.SatuSet(p.getSaturation());
                                                dutyData.setLoc(p.getLocation());


                                                ItemStack[] inv = p.getInventory().getContents();


                                                for (int i = 0; i < inv.length; i++) { // start iterating into the inv
                                                    ItemStack item = inv[i]; // getting the itemstack
                                                    if (item == null)
                                                        dutyData.invSet("Inventories.InventorySlot." + i, "empty"); // if it's a null itemstack, we save it as a string
                                                    else
                                                        dutyData.invSet("Inventories.InventorySlot." + i, item); // else, we save the itemstack
                                                }

                                                dutyData.save();
                                                dutyData.reload();
                                                p.getInventory().clear();


                                                if (dutyData.getSection("Inventories.duty-InventorySlot") != null) {
                                                    ConfigurationSection cs = dutyData.getSection("Inventories.duty-InventorySlot"); // Getting the correct node
                                                    List<ItemStack> items = new ArrayList<>(); // initializing the future itemstack array
                                                    for (String key : cs.getKeys(false)) { // iterating over the saved keys (itemstacks)
                                                        if (cs != null) {

                                                            Object o = cs.get(key); // getting the object, String (if empty) or ItemStack
                                                            if (o instanceof ItemStack)
                                                                items.add((ItemStack) o); // if correct itemstack, adding it into the list
                                                            else items.add(null); // not an ItemStack, adding null
                                                        }
                                                    }
                                                    ItemStack[] invd = items.toArray(new ItemStack[0]); // converting list into an array
                                                    p.getInventory().setContents(invd);
                                                }


                                                if (dutyData.getData("Duty-Gamemode") != null) {
                                                    String dutyGM = dutyData.getDutyGM("Duty-Gamemode");
                                                    p.setGameMode(GameMode.valueOf(dutyGM));
                                                }


                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                                                if (this.getConfig().getString("Duty-on.Command1") != null) {
                                                    String command1 = this.getConfig().getString("Duty-on.Command1").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command1);
                                                }

                                                if (this.getConfig().getString("Duty-on.Command2") != null) {
                                                    String command2 = this.getConfig().getString("Duty-on.Command2").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command2);
                                                }

                                                if (this.getConfig().getString("Duty-on.Command3") != null) {
                                                    String command3 = this.getConfig().getString("Duty-on.Command3").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command3);
                                                }

                                                if (this.getConfig().getString("Duty-on.Command4") != null) {
                                                    String command4 = this.getConfig().getString("Duty-on.Command4").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command4);
                                                }


                                                p.getInventory().setItem(8, tpcompass);


                                                String commandr = "lp user " + p.getName() + " parent set " + dutyData.getData("Rang");
                                                Bukkit.dispatchCommand(console, commandr);


                                            }
                                            if (!perms.getPrimaryGroup(p).equals(dplayer())) {


                                                p.sendMessage(prefix() + " §7Duty: §cOFF §7- From now others will see you as a default player");
                                                dutyData.setDutyGM(p.getGameMode().name());


                                                ItemStack[] invd = p.getInventory().getContents();

                                                for (int i = 0; i < invd.length; i++) { // start iterating into the inv
                                                    ItemStack item = invd[i]; // getting the itemstack
                                                    if (item == null)
                                                        dutyData.invSet("Inventories.duty-InventorySlot." + i, "empty"); // if it's a null itemstack, we save it as a string
                                                    else
                                                        dutyData.invSet("Inventories.duty-InventorySlot." + i, item); // else, we save the itemstack
                                                }


                                                dutyData.save();
                                                dutyData.reload();
                                                p.getInventory().clear();

                                                if (dutyData.getSection("Inventories.duty-InventorySlot") != null) {
                                                    ConfigurationSection cs = dutyData.getSection("Inventories.InventorySlot"); // Getting the correct node


                                                    List<ItemStack> items = new ArrayList<>(); // initializing the future itemstack array
                                                    if (cs != null) {
                                                        for (String key : cs.getKeys(false)) { // iterating over the saved keys (itemstacks)
                                                            Object o = cs.get(key); // getting the object, String (if empty) or ItemStack
                                                            if (o instanceof ItemStack)
                                                                items.add((ItemStack) o); // if correct itemstack, adding it into the list
                                                            else items.add(null); // not an ItemStack, adding null
                                                        }
                                                    }
                                                    ItemStack[] inv = items.toArray(new ItemStack[0]); // converting list into an array
                                                    p.getInventory().setContents(inv);
                                                }


                                                if (dutyData.getData("Exp-lvl") != null) {
                                                    p.setLevel(dutyData.getExp("Exp-lvl"));
                                                }

                                                if (dutyData.getData("Potion-Effects") != null) {
                                                    p.addPotionEffects(dutyData.getPotEff("Potion-Effects"));
                                                }

                                                if (dutyData.getData("Player-Health") != null) {
                                                    p.setHealth(dutyData.getHealth("Player-Health"));
                                                }

                                                if (dutyData.getData("Player-Food") != null) {
                                                    p.setFoodLevel(dutyData.getFood("Player-Food"));
                                                }

                                                if (dutyData.getData("Player-Saturation") != null) {
                                                    p.setSaturation(dutyData.getSatu("Player-Saturation"));
                                                }

                                                if (args.length == 1) {
                                                    if (!args[0].equalsIgnoreCase("-t")) {
                                                        if (dutyData.getLoc("Player-Location") != null) {
                                                            if (dutytp()) {
                                                                p.teleport(dutyData.getLoc("Player-Location"));
                                                            }

                                                        }
                                                    }
                                                } else if (dutyData.getLoc("Player-Location") != null) {
                                                    if (dutytp()) {
                                                        p.teleport(dutyData.getLoc("Player-Location"));
                                                    }

                                                }


                                                p.setGameMode(GameMode.SURVIVAL);

                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();

                                                if (this.getConfig().getString("Duty-off.Command1") != null) {
                                                    String command1 = this.getConfig().getString("Duty-off.Command1").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command1);
                                                }

                                                if (this.getConfig().getString("Duty-off.Command2") != null) {
                                                    String command2 = this.getConfig().getString("Duty-off.Command2").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command2);
                                                }

                                                if (this.getConfig().getString("Duty-off.Command3") != null) {
                                                    String command3 = this.getConfig().getString("Duty-off.Command3").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command3);
                                                }

                                                if (this.getConfig().getString("Duty-off.Command4") != null) {
                                                    String command4 = this.getConfig().getString("Duty-off.Command4").replaceAll("%player%", p.getName());
                                                    Bukkit.dispatchCommand(console, command4);
                                                }


                                                p.getInventory().removeItem(tpcompass);

                                                String commandr = "lp user " + p.getName() + " parent set " + dplayer();
                                                Bukkit.dispatchCommand(console, commandr);


                                            }

                                        } else {
                                            p.sendMessage(prefix() + " §cError! No rank set.");
                                            try {
                                                Thread.sleep(3000);
                                            } catch (InterruptedException e) {

                                            }
                                            dutyData.delete();
                                        }
                                    } else {
                                        p.sendMessage("§cError! Plugin is not set! For more information -> §b/duty help");
                                    }

                                } else {
                                    p.sendMessage(prefix() + " §cVariations: /duty, /duty help[2], /duty menu, /duty -t, /duty autoconf, /duty reset, /duty reload");
                                }
                            }

                        }


                    }

                } else {
                    if (label.equalsIgnoreCase("role")) {
                            if (!dplayer().equalsIgnoreCase("?")) {
                                if (args.length == 3) {
                                    if (args[0].equalsIgnoreCase("set")) {

                                        Player pp = Bukkit.getPlayer(args[1]);
                                        OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);


                                        if (ArrayUtils.contains(perms.getGroups(), args[2])) {
                                            if (pp != null) {
                                                UUID uuid = pp.getUniqueId();
                                                dutyData = new DutyData(this, args[1]);
                                                if ((dutyData.getData("Rang") == null)) {
                                                    dutyData.roleSet(pp, args[2]);
                                                    dutyData.save();
                                                    getLogger().info(prefix() + " §c" + args[1] + " §7Is now §c " + args[2] + " §7on the server");
                                                    pp.sendMessage(prefix() + " §7Is now " + "§c" + args[2] + " §7vagy on the server");
                                                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                    String commandr = "lp user " + pp.getName() + " parent set " + dplayer();
                                                    Bukkit.dispatchCommand(console, commandr);
                                                } else if (!dutyData.getData("Rang").equals(args[2])) {
                                                    dutyData.roleSet(pp, args[2]);
                                                    dutyData.save();
                                                    getLogger().info(prefix() + " §c" + args[1] + " §7Is now §c " + args[2] + " §7on the server");
                                                    pp.sendMessage(prefix() + " §7Is now " + "§c" + args[2] + " §7vagy on the server");
                                                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                    String commandr = "lp user " + pp.getName() + " parent set " + dplayer();
                                                    Bukkit.dispatchCommand(console, commandr);
                                                } else {
                                                    getLogger().info(prefix() + " §c " + args[1] + " §7már rendlekezik ezzel a ranggal");
                                                }


                                            } else {
                                                dutyData = new DutyData(this, args[1]);
                                                if ((dutyData.getData("Rang") == null)) {

                                                    dutyData.roleSet(op, args[2]);
                                                    dutyData.save();
                                                    getLogger().info(prefix() + " §c" + args[1] + " §7Is now §c " + args[2] + " §7on the server");
                                                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                    String commandr = "lp user " + op.getName() + " parent set " + dplayer();
                                                    Bukkit.dispatchCommand(console, commandr);

                                                } else if (!dutyData.getData("Rang").equals(args[2])) {
                                                    dutyData.roleSet(op, args[2]);
                                                    dutyData.save();
                                                    ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                    String commandr = "lp user " + op.getName() + " parent set " + dplayer();
                                                    Bukkit.dispatchCommand(console, commandr);

                                                } else {
                                                    getLogger().info(prefix() + " §c " + args[1] + " §7már rendlekezik ezzel a ranggal");
                                                }
                                            }


                                            //p.sendMessage("§cEnnek a játékosnak nem volt még kezelhető rangja on the server!");


                                        } else {
                                            getLogger().info(prefix() + " §cHiba! Nem létezik ez a rang!");
                                        }

                                    } else if (!args[0].equalsIgnoreCase("remove")) {
                                        getLogger().info(prefix() + " §7Helyes használat: §b/role <set/remove> <játékosnév> [<rang>]");
                                    }

                                } else if (args.length >= 1) {
                                    if (args[0].equalsIgnoreCase("remove")) {


                                        Player pp = Bukkit.getPlayer(args[1]);
                                        OfflinePlayer op = Bukkit.getOfflinePlayer(args[1]);

                                        dutyData = new DutyData(this, args[1]);
                                        if (pp != null) {
                                            if (dutyData.getData("Rang") == null) {
                                                getLogger().info(prefix() + " §7Ez a játékos nem rendelkezik ranggal");
                                            } else {

                                                getLogger().info(prefix() + " §7A(z) " + "§b" + dutyData.getData("Rang") + " §7rang" + " §b" + args[1] + " §7játékosról sikeresen eltávolítva.");
                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                String commandr = "lp user " + pp.getName() + " parent set " + player();
                                                Bukkit.dispatchCommand(console, commandr);
                                                dutyData.delete();
                                            }
                                        } else {
                                            if (dutyData.getData("Rang") == null) {
                                                getLogger().info(prefix() + " §7Ez a játékos nem rendelkezik ranggal");
                                            } else {

                                                getLogger().info(prefix() + " §7A(z) " + "§b" + dutyData.getData("Rang") + " §7rang" + " §b" + args[1] + " §7játékosról sikeresen eltávolítva.");
                                                ConsoleCommandSender console = Bukkit.getServer().getConsoleSender();
                                                String commandr = "lp user " + pp.getName() + " parent set " + player();
                                                Bukkit.dispatchCommand(console, commandr);
                                                dutyData.delete();
                                            }

                                        }

                                    }

                                    if (!args[0].equalsIgnoreCase("remove")) {
                                        getLogger().info(prefix() + " §7Helyes használat: §b/role <set/remove> <játékosnév> [<rang>]");

                                    }
                                } else {
                                    getLogger().info(prefix() + " §7Helyes használat: §b/role <set/remove> <játékosnév> [<rang>]");
                                }
                            } else {
                                getLogger().info("§cHiba! Plugin nincs beállítva! További infókért -> §b/duty help");
                            }



                    }else if (label.equalsIgnoreCase("duty")) {
                    if (args.length == 0) {
                        getLogger().info(prefix() + " §4Nem futtathatod ezt a konzolbol!");
                        return false;

                    }
                    if (args[0].equalsIgnoreCase("reload")) {
                        reloadConfig();
                        getLogger().info(prefix() + " §bPlugin sikeresen reloadolva!");
                        return false;

                    }
                }
            }









        return true;
    }

}


