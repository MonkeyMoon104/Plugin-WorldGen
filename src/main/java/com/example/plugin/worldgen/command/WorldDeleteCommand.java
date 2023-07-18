package com.example.plugin.worldgen.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;

import java.io.File;

public class WorldDeleteCommand implements CommandExecutor {

    private final Plugin plugin;

    public WorldDeleteCommand(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1) {
            sender.sendMessage("Utilizzo corretto: /worlddelete <name>");
            return true;
        }

        String worldName = args[0];
        World world = Bukkit.getWorld(worldName);

        if (world == null) {
            sender.sendMessage("Il mondo specificato non esiste.");
            return true;
        }

        // Elimina il mondo
        Bukkit.unloadWorld(world, true);
        deleteWorldFolder(world);

        sender.sendMessage("Mondo " + worldName + " eliminato correttamente.");
        return true;
    }

    private void deleteWorldFolder(World world) {
        File worldFolder = world.getWorldFolder();
        deleteFolder(worldFolder);
    }

    private void deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            folder.delete();
        }
    }
}
