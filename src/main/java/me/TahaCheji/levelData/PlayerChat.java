package me.TahaCheji.levelData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.Random;

public class PlayerChat implements Listener {

    @EventHandler
    public void onChatEvent(PlayerChatEvent e) {
        e.setCancelled(true);
        Bukkit.getServer().broadcastMessage(ChatColor.GOLD + "[" + new PlayerLevels(e.getPlayer()).getLevel() + "] " + e.getPlayer().getDisplayName());
        Random rand = new Random(); //instance of random class
        int upperbound = 10;
        int int_random = rand.nextInt(upperbound);
        if(int_random == 1) {
            new PlayerLevels(e.getPlayer()).addXP(15);
        }
    }

}
