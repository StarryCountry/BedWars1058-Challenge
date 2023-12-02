package cn.starry.challenge.listeners.challenge;

import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.utils.TextUtil;
import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.GameState;
import com.andrei1058.bedwars.api.arena.IArena;
import com.andrei1058.bedwars.api.events.shop.ShopBuyEvent;
import com.andrei1058.bedwars.api.language.Language;
import com.andrei1058.bedwars.shop.ShopManager;
import com.andrei1058.bedwars.shop.main.ShopCategory;
import com.andrei1058.bedwars.shop.quickbuy.PlayerQuickBuyCache;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class Warmonger implements Listener {

    @EventHandler
    public void onOpenToolsMenu(ShopBuyEvent event) {
        Player player = event.getBuyer();
        String Type = new MySQL().getData(player.getUniqueId());
        IArena arena = BedWars.getAPI().getArenaUtil().getArenaByPlayer(player);
        String id = event.getCategoryContent().getIdentifier();
        if (arena != null) {
            if (arena.getStatus().equals(GameState.playing)) {
                if (Type.equals("WARMONGER")) {
                    if (id.contains("utility-category.category-content")) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_CHALLENGE_WARMONGER_BUYITEM.getAsString()));
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
