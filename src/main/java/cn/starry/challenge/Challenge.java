package cn.starry.challenge;

import cn.starry.challenge.commands.MainCommand;
import cn.starry.challenge.config.FileManager;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.guis.ChallengeMenu;
import cn.starry.challenge.listeners.challenge.Renegade;
import cn.starry.challenge.listeners.challenge.Selfish;
import cn.starry.challenge.listeners.challenge.Warmonger;
import cn.starry.challenge.utils.TextUtil;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import org.bukkit.plugin.java.*;
import org.bukkit.event.*;
import org.bukkit.command.*;
import org.bukkit.*;
import cn.starry.challenge.listeners.*;
import org.bukkit.plugin.*;

import java.util.Arrays;
import java.util.Date;

public class Challenge extends JavaPlugin implements Listener {

    private static Challenge instance;
    private FileManager fileManager;
    public static Boolean ProxyMode = false;
    private final String Prefix = TextUtil.color("&b[Challenge] &f");
    private static MySQL sql;

    public ConsoleCommandSender console;
    public Server server;
    
    public Challenge() {
        this.console = Bukkit.getConsoleSender();
        this.server = this.getServer();
    }
    
    public void onEnable() {

        if (Bukkit.getPluginManager().getPlugin("BedWars1058") != null) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "Server Mode = BedWars1058"));
            ProxyMode = false;
        } else if (Bukkit.getPluginManager().getPlugin("BedWarsProxy") != null) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "Server Mode = BedWarsProxy"));
            ProxyMode = true;
        } else {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cBedWars plugin not detected,plugin disable"));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        fileManager = new FileManager(this);
        instance = this;

        this.isUnofficialVersion();
        this.Register();

    }

    private void isUnofficialVersion() {
        if (!getDescription().getAuthors().contains("Starry_Killer") || !getDescription().getVersion().equals("1.0.1-RELEASED")) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "You are Running a &cUNOFFICIAL &fVersion!"));
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "Please purchase plugins from official channels instead of UNOFFICIAL versions."));
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
    }

    private void ConnectDatabase() {
        //DataBase Connect
        sql = new MySQL();
        sql.createGameTable();
    }
    
    private void registerEvents() {
        final PluginManager manager = this.server.getPluginManager();

        if (ProxyMode) {
            //Player
            manager.registerEvents(new PlayerListener(), this);
            //Menu
            manager.registerEvents(new SelectMenuListener(), this);
            manager.registerEvents(new ChallengeMenu(), this);
        } else {
            //Player
            manager.registerEvents(new PlayerListener(), this);
            manager.registerEvents(new ArenaListener(),this);
            //Menu
            manager.registerEvents(new SelectMenuListener(), this);
            manager.registerEvents(new ChallengeMenu(), this);
            //Challenges
            manager.registerEvents(new Renegade(), this);
            manager.registerEvents(new Selfish(), this);
            manager.registerEvents(new Warmonger(), this);
        }
        //Debug Listener
        //manager.registerEvents(new DebugListener(), this);

    }

    public void registerCommands() {
        Bukkit.getPluginCommand("challenge").setExecutor(new MainCommand());
    }

    private void Register() {
        //Depend detection
            this.ConnectDatabase();
            this.registerEvents();
            this.registerCommands();
    }

    public static Challenge getInstance() {
        return instance;
    }

    public static MySQL getSql() {
        return sql;
    }

    public FileManager getFileManager() {
        return this.fileManager;
    }

}
