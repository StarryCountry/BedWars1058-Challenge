package cn.starry.challenge.listeners.challenge;

import cn.starry.challenge.Challenge;
import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.utils.Cooldown;
import cn.starry.challenge.utils.TextUtil;
import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.arena.Arena;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;
import java.util.Map;

public class Selfish implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        String Type = new MySQL().getData(player.getUniqueId());
        IArena arena = BedWars.getAPI().getArenaUtil().getArenaByPlayer(player);
        if (arena != null) {
            if (arena.getStatus().equals(GameState.playing)) {
                if (Type.equals("SELFISH")) {
                    if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        Block block = event.getClickedBlock();
                        if (block.getType() == Material.CHEST || block.getType() == Material.ENDER_CHEST) {
                            event.setCancelled(true);
                            event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.CHEST_OPEN, 1, 1.25F);
                            TextUtil.sendMessage(player, TextUtil.color(Configuration.CHALLENGE_MESSAGE_CHALLENGE_SELFISH_OEPNCHEST.getAsString()));
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        String Type = new MySQL().getData(player.getUniqueId());
        IArena arena = BedWars.getAPI().getArenaUtil().getArenaByPlayer(player);
        if (arena != null) {
            if (arena.getStatus().equals(GameState.playing)) {
                if (Type.equals("SELFISH")) {
                    event.setCancelled(true);
                    event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                    TextUtil.sendMessage(player, TextUtil.color(Configuration.CHALLENGE_MESSAGE_CHALLENGE_SELFISH_DROPITEM.getAsString()));
                }
            }
        }
    }

}
