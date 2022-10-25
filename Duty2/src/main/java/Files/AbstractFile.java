package Files;

import duty.main.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class AbstractFile {

    protected Main main;
    private File file;
    protected   FileConfiguration config;





    public AbstractFile(Main main, String fileName) {
        this.main = main;
        this.file = new File("plugins/Duty/dutydata/" + fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();

            }catch (IOException e) {
                e.printStackTrace();
            }
        }
        this.config = YamlConfiguration.loadConfiguration(file);

    }
    public  void save() {
        try {
            config.save(file);
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete() {
        config = null;
        file.delete();
    }

    public void reload() {
        config = YamlConfiguration.loadConfiguration(file);
    }

    public boolean exists() {

        return file.exists();
    }

    public String getName() {
        return file.getName();
    }




}