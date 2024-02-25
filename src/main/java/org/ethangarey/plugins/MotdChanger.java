package org.ethangarey.plugins;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public final class MotdChanger extends JavaPlugin {

    public static YamlConfiguration config;

    public static MotdChanger motdChanger;

    public static void updateConfig(MotdChanger plugin) {
        File file = new File(plugin.getDataFolder(), "config.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();

            } catch (IOException e) {
                Logger.log(LogTag.JFR, LogLevel.ERROR, "An error occurred, cannot save the configuration file");
                return;
            }
            config = YamlConfiguration.loadConfiguration(new File(plugin.getDataFolder(), "config.yml"));
            config.set("version", 0.1);
            config.set("motd", "&5&l&keee&aWelcome to fun network :)&5&l&keee");
            try {
                config.save(file);
            } catch (IOException e) {
                Logger.log(LogTag.JFR, LogLevel.ERROR, "An error occurred, cannot save the configuration file");
            }

            return;
        }
        config = YamlConfiguration.loadConfiguration(file);

    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        motdChanger = this;
        updateConfig(this);
        new MotdCommands(this);
        getServer().getPluginManager().registerEvents(new MotdEvents(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
