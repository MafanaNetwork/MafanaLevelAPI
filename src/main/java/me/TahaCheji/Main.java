package me.TahaCheji;

import me.TahaCheji.levelData.PlayerLevels;
import me.TahaCheji.mysqlData.MySQL;
import me.TahaCheji.mysqlData.PlayerLevelSQLGetter;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private static Main instance;
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
    }

    @Override
    public void onDisable() {
        mySQL.disconnect();
    }

    public MySQL getMySQL() {
        return mySQL;
    }

    public static Main getInstance() {
        return instance;
    }
}
