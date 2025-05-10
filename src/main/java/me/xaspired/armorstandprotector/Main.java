package me.xaspired.armorstandprotector;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "ArmorStandProtector" + ChatColor.GRAY + " by xAspired -  " + "Plugin Enabled Successfully!");

        String[] killCommands = { "killall", "ekillall" };

        for (String cmd : killCommands) {
            this.getCommand(cmd).setExecutor((sender, command, label, args) -> {
                if (!(sender instanceof org.bukkit.entity.Player)) return false;
                org.bukkit.entity.Player player = (org.bukkit.entity.Player) sender;

                boolean protectArmorStand = args.length == 0
                        || args[0].equalsIgnoreCase("@e")
                        || args[0].equalsIgnoreCase("entities")
                        || args[0].equalsIgnoreCase("all")
                        || args[0].equalsIgnoreCase("armorstand")
                        || args[0].equalsIgnoreCase("passive")
                        || args[0].equalsIgnoreCase("mob")
                        || args[0].equalsIgnoreCase("mobs");

                for (org.bukkit.entity.Entity entity : player.getWorld().getEntities()) {
                    if (protectArmorStand && entity instanceof org.bukkit.entity.ArmorStand) continue;
                    if (entity instanceof org.bukkit.entity.Player) continue;
                    if (entity instanceof org.bukkit.entity.LivingEntity) ((org.bukkit.entity.LivingEntity) entity).setHealth(0);
                    else entity.remove();
                }

                return true;
            });

        }
    }
}
