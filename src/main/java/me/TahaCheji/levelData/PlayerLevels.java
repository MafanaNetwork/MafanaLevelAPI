package me.TahaCheji.levelData;

import me.TahaCheji.Levels;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PlayerLevels {

    public Player player;
    public OfflinePlayer offlinePlayer;
    public UUID uuid;

    private Levels plugin;

    public PlayerLevels(Player player) {
        this.player = player;
        this.uuid = player.getUniqueId();
        this.plugin = Levels.getInstance();
    }

    public PlayerLevels(OfflinePlayer offlinePlayer) {
        this.offlinePlayer = offlinePlayer;
        this.uuid = offlinePlayer.getUniqueId();
        this.plugin = Levels.getInstance();
    }

    public void levelEvent() {
        Player player = getPlayer().getPlayer();
        assert player != null;
        if(!player.isOnline()) {
            return;
        }
        player.sendMessage(ChatColor.GRAY + "-----------------------------------------------");
        player.sendMessage(ChatColor.GOLD + "LEVEL UP!");
        player.sendMessage(ChatColor.GOLD + "PlayerLevel: " + getLevel());
        player.sendMessage(ChatColor.GOLD + "LevelXp: " + getXp());
        player.sendMessage(ChatColor.GRAY + "-----------------------------------------------");
    }


    public void addXP(int xp) {
        try {
            PreparedStatement ps = plugin.mySQL.getConnection().prepareStatement("UPDATE playerlevel SET XP=? WHERE UUID=?");
            ps.setInt(1, (getXp() + xp));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            BukkitTask t = new BukkitRunnable() {
                @Override
                public void run() {
                    player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "+" + xp + " xp "));
                }
            }.runTaskTimer(Levels.getInstance(), 0L, 20L);
            new BukkitRunnable() {
                @Override
                public void run() {
                    t.cancel();
                }
            }.runTaskLaterAsynchronously(Levels.getInstance(), 20L);
            if(getXp() >= LevelsXpTo.getXpTo(player).getXp()) {
                addLevel(1);
                setXp(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setXp(int xp) {
        try {
            PreparedStatement ps = plugin.mySQL.getConnection().prepareStatement("UPDATE playerlevel SET XP=? WHERE UUID=?");
            ps.setInt(1, xp);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getXp() {
        try {
            PreparedStatement ps = plugin.mySQL.getConnection().prepareStatement("SELECT XP FROM playerlevel WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int xp = 0;
            if(rs.next()) {
                xp = rs.getInt("XP");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void addLevel(int lvl) {
        try {
            PreparedStatement ps = plugin.mySQL.getConnection().prepareStatement("UPDATE playerlevel SET LEVEL=? WHERE UUID=?");
            ps.setInt(1, (getXp() + lvl));
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            levelEvent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setLevel(int lvl) {
        try {
            PreparedStatement ps = plugin.mySQL.getConnection().prepareStatement("UPDATE playerlevel SET LEVEL=? WHERE UUID=?");
            ps.setInt(1, lvl);
            ps.setString(2, uuid.toString());
            ps.executeUpdate();
            levelEvent();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getLevel() {
        try {
            PreparedStatement ps = plugin.mySQL.getConnection().prepareStatement("SELECT LEVEL FROM playerlevel WHERE UUID=?");
            ps.setString(1, uuid.toString());
            ResultSet rs = ps.executeQuery();
            int xp = 0;
            if(rs.next()) {
                xp = rs.getInt("LEVEL");
                return xp;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public Player getPlayer() {
        return player;
    }
}
