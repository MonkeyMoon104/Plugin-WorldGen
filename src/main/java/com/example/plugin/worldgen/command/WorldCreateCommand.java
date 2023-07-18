package com.example.plugin.worldgen.command;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class WorldCreateCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length < 1 || args.length > 2) {
            sender.sendMessage("Utilizzo corretto: /worldcreate <name> [flat]");
            return true;
        }

        String worldName = args[0];
        boolean isFlat = (args.length == 2 && args[1].equalsIgnoreCase("flat"));

        WorldCreator worldCreator = new WorldCreator(worldName);
        if (isFlat) {
            worldCreator.type(WorldType.FLAT);
            worldCreator.generatorSettings("{\"type\":\"flat\",\"generator\":{\"type\":\"void\",\"settings\":\"{}\"}}");
        } else {
            worldCreator.type(WorldType.NORMAL);
        }
        worldCreator.generateStructures(false);

        Bukkit.createWorld(worldCreator);

        sender.sendMessage("Mondo " + worldName + " generato correttamente.");
        return true;
    }
}
