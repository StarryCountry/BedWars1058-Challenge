package cn.starry.challenge.listeners;

import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.events.player.PlayerXpGainEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class DebugListener implements Listener {

    @EventHandler
    public void ExperienceAdd (PlayerJoinEvent event) {
        Player player = event.getPlayer();
        int amount = 300;
        BedWars.getAPI().getLevelsUtil().addXp(player, amount, PlayerXpGainEvent.XpSource.OTHER);
        BedWars.plugin.getServer().getScheduler().runTaskAsynchronously(BedWars.plugin, () -> {
            Object[] data = BedWars.getRemoteDatabase().getLevelData(player.getUniqueId());
            BedWars.getRemoteDatabase().setLevelData(player.getUniqueId(), (Integer) data[0], ((Integer)data[1]) + amount, (String) data[2], (Integer)data[3]);
        });
        player.sendMessage("Debug Success");
    }
}
