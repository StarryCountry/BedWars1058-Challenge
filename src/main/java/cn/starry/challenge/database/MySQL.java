package cn.starry.challenge.database;

import cn.starry.challenge.Challenge;
import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.utils.TextUtil;
import org.bukkit.Bukkit;

import java.sql.*;
import java.util.UUID;

public class MySQL {

    private final Challenge plugin = Challenge.getInstance();
    private Connection conn;
    private String Prefix = TextUtil.color("&b[Challenge] &f");

    String host = Configuration.DATABASE_HOST.getAsString();
    int port = Configuration.DATABASE_PORT.getAsInt();
    String database = Configuration.DATABASE_DATABASE.getAsString();
    String username = Configuration.DATABASE_USERNAME.getAsString();
    String password = Configuration.DATABASE_PASSWORD.getAsString();

    public void connect() {

        if (this.conn == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                this.conn = DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database + "?&autoReconnect=true&wait_timeout=31536000&interactive_timeout=31536000&useUnicode=true&characterEncoding=utf8&useSSL=" + "false", this.username, this.password);
            } catch (ClassNotFoundException | SQLException var1) {
                Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cCould not connect to MySQL server, please check your username, password or database."));
                Bukkit.shutdown();
            }
        }
    }

    public void disconnect() {
        if (this.conn != null) {
            try {
                this.conn.close();
                this.conn = null;
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cCould not disconnect from MySQL server,Please check if your MySQL server is online."));
                Bukkit.shutdown();
            }
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void createGameTable() {
        this.connect();
        try (Statement stmt = this.conn.createStatement();) {
            String sql = "CREATE TABLE IF NOT EXISTS `" + database +"`.`bw1058_challenge`  (  `UUID` varchar(36) NOT NULL,  " +
                    "`Name` varchar(32) NOT NULL,  " +
                    "`TYPE` varchar(32) NOT NULL,  " +
                    "PRIMARY KEY (`UUID`, `Name`, `TYPE`));";
            stmt.executeUpdate(sql);
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&aSuccessfully created Challenge table."));
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cCould not create a statement,Please support for Plugin developer."));
            //e.printStackTrace();
        }
        this.disconnect();
    }

    public void createGameData(UUID uuid) {
        this.connect();
        try (Statement stmt = this.conn.createStatement();) {
            String sql = String.format("SELECT * FROM `bw1058_challenge` WHERE `UUID`='%s'", uuid);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            rs.getString("Name");
        } catch (SQLException e) {
            if (e.getMessage().contains("empty result set")) {
                try (Statement stmt2 = this.conn.createStatement();) {
                    String sql1 = String.format("INSERT IGNORE INTO `bw1058_challenge` (`UUID`, `Name`, `TYPE`) VALUES ('%s', '%s', '%s')", uuid, Bukkit.getPlayer(uuid).getName(), "NONE");
                    stmt2.executeUpdate(sql1);
                } catch (SQLException i) {
                    Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cCould not create a statement,Please support for Plugin developer."));
                    //i.printStackTrace();
                }
            }
            //e.printStackTrace();
        }
        this.disconnect();
    }

    public String getData(UUID uuid) {
        this.connect();
        try (Statement stmt = this.conn.createStatement();) {
            String sql = String.format("SELECT * FROM `bw1058_challenge` WHERE `UUID`='%s'", uuid);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            return rs.getString("TYPE");
        } catch (SQLException var4) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cCould not create a statement,Please support for Plugin developer."));
            //var4.printStackTrace();
        }
        this.disconnect();
        return null;
    }

    public void setData(UUID uuid, String type) {
        this.connect();
        try (Statement stmt = this.conn.createStatement();) {
            String sql = String.format("UPDATE `bw1058_challenge` SET `TYPE`='%s' WHERE `UUID`='%s'", type, uuid);
            stmt.executeUpdate(sql);
        } catch (SQLException var31) {
            Bukkit.getConsoleSender().sendMessage(TextUtil.color(Prefix + "&cCould not create a statement,Please support for Plugin developer."));
            //var31.printStackTrace();
        }
        this.disconnect();
    }

}