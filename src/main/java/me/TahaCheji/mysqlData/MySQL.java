package me.TahaCheji.mysqlData;

import org.bukkit.Bukkit;
import org.bukkit.command.ConsoleCommandSender;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {

    public static String host = "localhost";
    public static String port = "3306";
    public static String database = "mafana_hub";
    public static String username = "root";
    public static String password = "";
    public static Connection con;

    static ConsoleCommandSender console = Bukkit.getConsoleSender();

    // connect
    public void connect() {
        if (!isConnected()) {
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password);
                console.sendMessage("\247c[\2476Minepedia-System\247c] \247bMySQL-Verbindung wurde aufgebaut!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // disconnect
    public void disconnect() {
        if (isConnected()) {
            try {
                con.close();
                console.sendMessage("\247c[\2476Minepedia-System\247c]\247bMySQL-Verbindung wurde geschlossen!");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    // isConnected
    public boolean isConnected() {
        return (con == null ? false : true);
    }

    // getConnection
    public Connection getConnection() {
        return con;
    }

}
