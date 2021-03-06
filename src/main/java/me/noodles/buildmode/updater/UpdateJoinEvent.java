package me.noodles.buildmode.updater;

import me.noodles.buildmode.utils.Settings;
import org.bukkit.event.player.*;

import me.noodles.buildmode.main.MainBuildMode;

import org.bukkit.ChatColor;
import org.bukkit.entity.*;
import org.bukkit.event.*;

public class UpdateJoinEvent implements Listener
{

    private MainBuildMode plugin;
    public UpdateChecker checker;

    public UpdateJoinEvent(MainBuildMode plugin) {
        this.plugin = plugin;
    }


    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        Player p = e.getPlayer();
        if (MainBuildMode.plugin.getConfig().getBoolean("Update.Enabled") == true) {
            if (p.hasPermission("buildmode.update")) {
                new UpdateChecker(plugin, 39103).getLatestVersion(version -> {
                    if (!MainBuildMode.getInstance().getDescription().getVersion().equalsIgnoreCase(version)) {
                        p.sendMessage(ChatColor.GRAY + "****************************************************************");
                        p.sendMessage(ChatColor.RED + "BuildMode is outdated!");
                        p.sendMessage(ChatColor.RED + "Newest version: " + version);
                        p.sendMessage(ChatColor.RED + "Your version: " + ChatColor.BOLD + Settings.VERSION);
                        p.sendMessage(ChatColor.GOLD + "Please Update Here: " + ChatColor.ITALIC + Settings.PLUGIN_URL);
                        p.sendMessage(ChatColor.GRAY + "****************************************************************");                    }
                });
            }
        }
    }


    @EventHandler
    public void onDevJoin(PlayerJoinEvent e) { //THIS EVENT IS USED FOR DEBUG REASONS ONLY!
        Player p = e.getPlayer();
        if (p.getName().equals("Noodles_YT")) {
            p.sendMessage(ChatColor.RED + "BGHDDevelopment Debug Message");
            p.sendMessage(" ");
            p.sendMessage(ChatColor.GREEN + "This server is using " + Settings.NAME + " version " + Settings.VERSION);
            p.sendMessage(" ");
        }
    }

}
    