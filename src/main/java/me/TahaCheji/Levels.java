package me.TahaCheji;

import me.TahaCheji.mysqlData.MySQL;
import me.TahaCheji.mysqlData.PlayerLevelSQLGetter;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public final class Levels extends JavaPlugin implements Listener {

    private static Levels instance;
    public MySQL mySQL;
    public PlayerLevelSQLGetter data;

    @Override
    public void onEnable() {
        instance = this;
        this.mySQL = new MySQL();
        this.data = new PlayerLevelSQLGetter(this);
        mySQL.connect();
        if(mySQL.isConnected()) {
            data.createTable();
        }
        getServer().getPluginManager().registerEvents(this, this);
    }


    @Override
    public void onDisable() {
        mySQL.disconnect();
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public static Levels getInstance() {
        return instance;
    }
}
