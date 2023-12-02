package cn.starry.challenge.config;

import cn.starry.challenge.Challenge;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class FileManager {

    private final YamlConfiguration config;

    public FileManager(Challenge plugin) {
        if (!Challenge.ProxyMode) {
            File folder = new File("plugins/BedWars1058/Addons/Challenge");
            if (!folder.exists()) folder.mkdirs();
            File configFile = new File(folder, "config.yml");
            if (!configFile.exists()) {
                saveResource(plugin.getResource("config.yml"), configFile);
            }
            config = YamlConfiguration.loadConfiguration(configFile);
        } else {
            File folder = new File("plugins/BedWarsProxy/Addons/Challenge");
            if (!folder.exists()) folder.mkdirs();
            File configFile = new File(folder, "config.yml");
            if (!configFile.exists()) {
                saveResource(plugin.getResource("config.yml"), configFile);
            }
            config = YamlConfiguration.loadConfiguration(configFile);
        }
    }

    private void saveResource(InputStream in, File destination) {
        try {
            Files.copy(in, destination.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public YamlConfiguration getConfig() {
        return this.config;
    }

}
