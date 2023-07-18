package com.example.plugin.worldgen;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.example.plugin.worldgen.command.WarpCommand;
import com.example.plugin.worldgen.command.WorldCreateCommand;
import com.example.plugin.worldgen.command.WorldDeleteCommand;

public class WorldGen extends JavaPlugin implements Listener {

    private static WorldGen instance;

    @Override
    public void onEnable() {
        instance = this;

        // Registra il listener degli eventi di teletrasporto
        getServer().getPluginManager().registerEvents(this, this);

        // Registra i comandi
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("worldcreate").setExecutor(new WorldCreateCommand());
        getCommand("worlddelete").setExecutor(new WorldDeleteCommand(this));
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public static WorldGen getInstance() {
        return instance;
    }

    public void createWorld(String worldName, WorldType worldType, boolean generateStructures) {
        WorldCreator worldCreator = new WorldCreator(worldName);
        worldCreator.type(worldType);
        worldCreator.generateStructures(generateStructures);
        World world = worldCreator.createWorld();
        world.setSpawnLocation(0, 100, 0);
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event) {
        Player player = event.getPlayer();
        if (event.getCause() == PlayerTeleportEvent.TeleportCause.COMMAND) {
            player.sendMessage("Ti sei teletrasportato a " + event.getTo().getWorld().getName());
        }
    }
}
