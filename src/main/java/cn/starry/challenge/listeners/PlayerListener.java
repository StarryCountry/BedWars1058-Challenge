package cn.starry.challenge.listeners;

import cn.starry.challenge.Challenge;
import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.utils.TextUtil;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;
import java.util.UUID;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();
        Challenge.getSql().createGameData(uuid);
    }

}
