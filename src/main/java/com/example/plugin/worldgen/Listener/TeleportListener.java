package com.example.plugin.worldgen.Listener;


import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;

public class TeleportListener implements Listener {

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        Location from = event.getFrom();
        Location to = event.getTo();

        if (from.getWorld() != to.getWorld()) {
            // Il giocatore sta cambiando mondo
            player.sendMessage("Sei entrato nel mondo " + to.getWorld().getName());
            // Puoi eseguire altre azioni quando il giocatore entra in un nuovo mondo
        }
    }
}
