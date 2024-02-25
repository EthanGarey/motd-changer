package org.ethangarey.plugins;

import jdk.jfr.internal.LogLevel;
import jdk.jfr.internal.LogTag;
import jdk.jfr.internal.Logger;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.IOException;

public final class MotdChanger extends JavaPlugin {

    public static YamlConfiguration config;

    public static MotdChanger motdChanger;

    @Override
    public void onEnable() {
        // Plugin startup logic
        motdChanger = this;
        getCommand("motdmanager").setExecutor(new MotdCommands());
        new BukkitRunnable() {
            @Override
            public void run() {
                File file = new File(getDataFolder(), "config.yml");
                if (!file.exists()) {
                    saveResource("config.yml", false);
                    config = YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
                    config.set("version", 1);
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
        }.run();

        getServer().getPluginManager().registerEvents(new MotdEvents(), this);
//                new BukkitRunnable() {
//                    @Override
//                    public void run() {
//                        if (config.getInt("version") == 1) {
//                            if (config.getString("motd").isEmpty()) {
//                                config.set("motd", "&5&leee&aWelcome to fun network :)&5&leee");
//
//                            }
//                        }
//                    }
//                };


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
