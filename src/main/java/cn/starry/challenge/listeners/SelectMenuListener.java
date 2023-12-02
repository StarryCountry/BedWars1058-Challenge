package cn.starry.challenge.listeners;

import cn.starry.challenge.Challenge;
import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.utils.TextUtil;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;
import org.bukkit.event.*;
import org.bukkit.event.inventory.*;
import org.bukkit.entity.*;
import cn.starry.challenge.guis.*;
import org.bukkit.*;
import java.util.*;

public class SelectMenuListener implements Listener
{

    @EventHandler
    public void onShopOpen(final InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        if (e.getInventory().getName() != null && e.getInventory().getName().equals(TextUtil.color(Configuration.CHALLENGE_SELECTOR_TITLE.getAsString()))) {
            final ItemStack item = new ItemStack(Material.valueOf(Configuration.CHALLENGE_SELECTOR_ITEM_MATERIAL.getAsString()));
            final ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(TextUtil.color(Configuration.CHALLENGE_SELECTOR_ITEM_NAME.getAsString()));
            List<String> lore = Configuration.CHALLENGE_SELECTOR_ITEM_LORE.getAsStringList();
            meta.setLore(TextUtil.color(lore));
            item.setItemMeta(meta);
            meta.removeItemFlags(ItemFlag.HIDE_ATTRIBUTES);
            meta.removeItemFlags(ItemFlag.HIDE_DESTROYS);
            meta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
            meta.removeItemFlags(ItemFlag.HIDE_PLACED_ON);
            meta.removeItemFlags(ItemFlag.HIDE_POTION_EFFECTS);
            meta.removeItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            e.getInventory().setItem(Configuration.CHALLENGE_SELECTOR_ITEM_SLOT.getAsInt(), item);
        }
    }
    
    @EventHandler
    public void onItemClick(final InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (e.getInventory().getName() != null && e.getInventory().getName().equals(TextUtil.color(Configuration.CHALLENGE_SELECTOR_TITLE.getAsString())) && e.getCurrentItem() != null && e.getCurrentItem().getItemMeta() != null && e.getCurrentItem().getItemMeta().getDisplayName() != null && e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(Configuration.CHALLENGE_SELECTOR_ITEM_NAME.getAsString()))) {
            new ChallengeMenu().openMenu((Player)e.getWhoClicked());
        }
    }

}
