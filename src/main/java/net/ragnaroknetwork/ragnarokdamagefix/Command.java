package net.ragnaroknetwork.ragnarokdamagefix;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class Command implements CommandExecutor {
    Plugin plugin = RagnarokDamageFix.getPlugin(RagnarokDamageFix.class);

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command cmd, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (!player.hasPermission("ragnarokbowboosting.admin")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cYou do not have permission to execute this command!"));
                return true;
            }
        }

        if (args.length == 0) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lRagnarokDamageFix Help\n&c&l ► &c/rdf v: &7Change the value to add as additional damage per sharpness level."));
            return true;
        }

        if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&lRagnarokDamageFix Help\n&c&l ► &c/rdf v: &7Change the value to add as additional damage per sharpness level."));
            return true;
        }

        else if (args[0].equalsIgnoreCase("v")) {
            if (args.length == 1) {
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7Amount of damage added per sharpness level: &c" + plugin.getConfig().getString("value")));
                return true;
            }

            try {
                Double.parseDouble(args[1].trim());
            } catch (NumberFormatException e){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&4&l► &cValue is not a number!"));
                return true;
            }

            Double multiplier = Double.parseDouble(args[1].trim());

            plugin.getConfig().set("value", multiplier);
            plugin.saveConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&l► &7The amount of damage added per sharpness level is &c" + args[1]));
            return true;
        }

        if (args[0].equalsIgnoreCase("reload")) {
            plugin.reloadConfig();
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&2&l► &aSuccessfully reloaded &2RagnarokDamageFix&a!"));
            return true;
        }

        return true;
    }
}
