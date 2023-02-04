package org.woahhs;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import org.woahhs.essentials.FlyCommand;
import org.woahhs.essentials.GamemodeCommand;
import org.woahhs.files.ConfigFile;

public class Plugin extends JavaPlugin {

    @Getter
    private static Plugin instance;

    private ConfigFile mainConfig;

    @Override
    public void onEnable() {
        instance = this;
        mainConfig = new ConfigFile(this, "config");
        registerCommands();

    }

    @Override
    public void onDisable() {


    }


    private void registerCommands(){
        getCommand("fly").setExecutor(new FlyCommand());
        getCommand("gamemode").setExecutor(new GamemodeCommand());
    }
}