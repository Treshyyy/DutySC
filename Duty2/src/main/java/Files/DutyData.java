package Files;
import duty.main.Main;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;


public class DutyData extends AbstractFile{

    public DutyData(Main main, String player){
        super(main, player + ".yml");

    }
    public void roleSet(OfflinePlayer player, String string){
        config.set("Adatok".toString(), player);
        config.set("Rang".toString(), string);
    }

    public void roleRem(OfflinePlayer player){
        config.set("Rang".toString(), "none");
    }

    public void invSet(String string, ItemStack inv) {

        config.set(string.toString(), inv);
    }

    public void invSet(String string, String inve) {

        config.set(string.toString(), inve);
    }

    public void expSet(int Int) {

        config.set("Exp-lvl".toString(), Int);
    }

    public String getData(String string) {

        return config.getString(string);
    }

    public int getExp(String exp) {


        return config.getInt(exp);
    }

    public void potEff(Collection<PotionEffect> eff) {
        config.set("Potion-Effects".toString(), eff);
    }

    public Collection<PotionEffect> getPotEff(String pot) {

        return (Collection<PotionEffect>) config.get(pot);
    }

    public String getDutyGM(String string) {
        return config.getString(string);
    }

    public void setDutyGM(String gm) {
        config.set("Duty-Gamemode".toString(), gm);
    }

    public void HealthSet(Double hlt) {
        config.set("Player-Health".toString(), hlt);
    }

    public double getHealth(String hlt) {
        return (double) config.get(hlt);
    }

    public void FoodSet(int food) {
        config.set("Player-Food".toString(), food);
    }

    public int getFood(String food) {
        return config.getInt(food);
    }

    public void SatuSet(float satu) {
        float satur = (float) config.getDouble("Player-Saturation");
        config.set("Player-Saturation".toString(), satu);
    }
    public float getSatu(String satu) {
        float satur = (float) config.getDouble("Player-Saturation");

        return (float) config.getDouble("Player-Saturation");

    }

    public void setLoc(Location loc) {
        config.set("Player-Location", loc);
    }

    public Location getLoc(String loc) {
        return (Location) config.get("Player-Location");
    }



    public ConfigurationSection getSection(String path) {

        return config.getConfigurationSection(path);
    }

}


