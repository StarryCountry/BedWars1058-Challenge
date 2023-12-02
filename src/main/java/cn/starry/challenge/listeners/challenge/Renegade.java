package cn.starry.challenge.listeners.challenge;

import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.utils.Cooldown;
import cn.starry.challenge.utils.TextUtil;
import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.language.Language;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Renegade implements Listener {

    @EventHandler
    public void onOpenUpgradeMenu(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        String Type = new MySQL().getData(player.getUniqueId());
        IArena arena = BedWars.getAPI().getArenaUtil().getArenaByPlayer(player);
        if (arena != null) {
            if (arena.getStatus().equals(GameState.playing)) {
                if (Type.equals("RENEGADE")) {
                    if (event.getInventory().getName().equals(Language.getMsg(player, "upgrades-menu-gui-name-default"))) {
                        ((Player) event.getPlayer()).playSound(event.getPlayer().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        event.setCancelled(true);
                        TextUtil.sendMessage(player, TextUtil.color(Configuration.CHALLENGE_MESSAGE_CHALLENGE_RENEGADE_OPENUPGRADE.getAsString()));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPickUpDiamond(PlayerPickupItemEvent event) {
        Player player = event.getPlayer();
        String Type = new MySQL().getData(player.getUniqueId());
        IArena arena = BedWars.getAPI().getArenaUtil().getArenaByPlayer(player);
        if (arena != null) {
            if (arena.getStatus().equals(GameState.playing)) {
                if (Type.equals("RENEGADE")) {
                    if (event.getItem().getItemStack().equals(new ItemStack(Material.DIAMOND))) {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }


}
