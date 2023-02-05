package org.woahhs;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.woahhs.essentials.FlyCommand;
import org.woahhs.essentials.GamemodeCommand;
import org.woahhs.essentials.HealCommand;
import org.woahhs.files.ConfigFile;

import java.util.logging.Level;

public class Plugin extends JavaPlugin {

    @Getter
    private static Plugin instance;
    public static String prefix;

    private ConfigFile mainConfig;

    @Override
    public void onEnable() {
        instance = this;
        mainConfig = new ConfigFile(this, "config");
        prefix = mainConfig.getString("core-prefix");
        getLogger().log(Level.WARNING, coreEnabled());
        registerCommands();

    }

    @Override
    public void onDisable() {


    }


    private void registerCommands(){
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
        getCommand("heal").setExecutor(new HealCommand());
    }




    private String coreEnabled(){
        String enabled = " _                        _____ __  __ _____     _____               \n" +
                "                           | |                      / ____|  \\/  |  __ \\   / ____|              \n" +
                "                           | |    _   _ _ __ __ _  | (___ | \\  / | |__) | | |     ___  _ __ ___ \n" +
                "                           | |   | | | | '__/ _` |  \\___ \\| |\\/| |  ___/  | |    / _ \\| '__/ _ \\ \n" +
                "                           | |___| |_| | | | (_| |  ____) | |  | | |      | |___| (_) | | |  __/\n" +
                "                           |______\\__, |_|  \\__,_| |_____/|_|  |_|_|       \\_____\\___/|_|  \\___|\n" +
                "                                   __/ |                                                        \n" +
                "                                  |___/                                                         \n\n" +
                "                            ______             _     _          _ \n" +
                "                           |  ____|           | |   | |        | |\n" +
                "                           | |__   _ __   __ _| |__ | | ___  __| |\n" +
                "                           |  __| | '_ \\ / _` | '_ \\| |/ _ \\/ _` |\n" +
                "                           | |____| | | | (_| | |_) | |  __/ (_| |\n" +
                "                           |______|_| |_|\\__,_|_.__/|_|\\___|\\__,_|\n" +
                "                                                                  \n" +
                "";
        return enabled;
    }


















}