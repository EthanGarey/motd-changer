package org.ethangarey.plugins;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class MotdEvents implements Listener {

    @EventHandler
    public void pingEvent(ServerListPingEvent event) {
        event.setMotd(ChatColor.translateAlternateColorCodes('&', MotdChanger.config.getString("motd")));
    }

}
