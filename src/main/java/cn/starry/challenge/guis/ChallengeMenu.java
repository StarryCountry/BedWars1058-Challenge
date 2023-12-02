package cn.starry.challenge.guis;

import cn.starry.challenge.Challenge;
import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.utils.TextUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class  ChallengeMenu implements Listener {

        private Inventory inv;
        private static boolean myserver = false;

        public void openMenu(Player player) {
                this.init(player);
                player.openInventory(this.inv);
        }

        String color = TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_COLOR.getAsString());

        public void init(Player player) {

                String Type = new MySQL().getData(player.getUniqueId());

                ItemMeta meta;
                ItemStack item;
                this.inv = Bukkit.createInventory(null, Configuration.CHALLENGE_MENU_SIZE.getAsInt(), Configuration.CHALLENGE_MENU_TITLE.getAsString());

                List<String> intro = Configuration.CHALLENGE_MENU_ITEM_INTRO.getAsStringList();
                String category = TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_CATEGORY.getAsString());

                {
                        item = new ItemStack(Material.DIAMOND, 1);
                        meta = item.getItemMeta();
                        meta.setDisplayName(TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString()));

                        List<String> rules = Configuration.CHALLENGE_MENU_ITEM_RENEGADE_RULES.getAsStringList();

                        List<String> lore = new ArrayList<>();
                        lore.add(category);
                        lore.add("");
                        for (String line : rules) {
                                lore.add(TextUtil.color(line));
                        }
                        lore.add("");
                        for (String line : intro) {
                                lore.add(TextUtil.color(line));
                        }
                        lore.add("");
                        lore.add(!Type.equals("RENEGADE") ? TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_STATUS_ACTIVATED.getAsString()) : TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_STATUS_DEACTIVATED.getAsString()));
                        if (myserver) {
                                lore.add("");
                        }
                        meta.setLore(lore);
                        if (Type.equals("RENEGADE")) {
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        }
                        item.setItemMeta(meta);
                        if (Type.equals("RENEGADE")) {
                                item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                        }
                        this.inv.setItem(10, item);
                }

                {
                        item = new ItemStack(Material.TNT, 1);
                        meta = item.getItemMeta();
                        meta.setDisplayName(TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString()));

                        List<String> rules = Configuration.CHALLENGE_MENU_ITEM_WARMONGER_RULES.getAsStringList();

                        List<String> lore = new ArrayList<>();
                        lore.add(category);
                        lore.add("");
                        for (String line : rules) {
                                lore.add(TextUtil.color(line));
                        }
                        lore.add("");
                        for (String line : intro) {
                                lore.add(TextUtil.color(line));
                        }
                        lore.add("");
                        lore.add(!Type.equals("WARMONGER") ? TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_STATUS_ACTIVATED.getAsString()) : TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_STATUS_DEACTIVATED.getAsString()));
                        if (myserver) {
                                lore.add("");
                        }
                        meta.setLore(lore);
                        if (Type.equals("WARMONGER")) {
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        }
                        item.setItemMeta(meta);
                        if (Type.equals("WARMONGER")) {
                                item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                        }
                        this.inv.setItem(11, item);
                }

                {
                        item = new ItemStack(Material.GOLD_INGOT, 1);
                        meta = item.getItemMeta();
                        meta.setDisplayName(TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString()));

                        List<String> rules = Configuration.CHALLENGE_MENU_ITEM_SELFISH_RULES.getAsStringList();

                        List<String> lore = new ArrayList<>();
                        lore.add(category);
                        lore.add("");
                        for (String line : rules) {
                                lore.add(TextUtil.color(line));
                        }
                        lore.add("");
                        for (String line : intro) {
                                lore.add(TextUtil.color(line));
                        }
                        lore.add("");
                        lore.add(!Type.equals("SELFISH") ? TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_STATUS_ACTIVATED.getAsString()) : TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_STATUS_DEACTIVATED.getAsString()));
                        if (myserver) {
                                lore.add("");
                        }
                        meta.setLore(lore);
                        if (Type.equals("SELFISH")) {
                                meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                        }
                        item.setItemMeta(meta);
                        if (Type.equals("SELFISH")) {
                                item.addUnsafeEnchantment(Enchantment.ARROW_INFINITE, 1);
                        }
                        this.inv.setItem(12, item);
                }

                {
                        item = new ItemStack(Material.REDSTONE_BLOCK, 1);
                        meta = item.getItemMeta();
                        String active = Configuration.CHALLENGE_MENU_ITEM_REDSTONE_COLOR_ACTIVE.getAsString();
                        String deactivate = Configuration.CHALLENGE_MENU_ITEM_REDSTONE_COLOR_DEACTIVATE.getAsString();
                        if (Type.equals("NONE")) {
                                meta.setDisplayName(TextUtil.color(active + Configuration.CHALLENGE_MENU_ITEM_REDSTONE_NAME.getAsString()));
                        } else {
                                meta.setDisplayName(TextUtil.color(deactivate + Configuration.CHALLENGE_MENU_ITEM_REDSTONE_NAME.getAsString()));
                        }

                        List<String> state = Configuration.CHALLENGE_MENU_ITEM_REDSTONE_LORE.getAsStringList();

                        List<String> lore = new ArrayList<>();

                        if (Type.equals("NONE")) {
                                for (String line : state) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_NONE_NAME.getAsString()))
                                                .replace("{state}", TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_REDSTONE_STATE_DEACTIVATE.getAsString()))
                                        );
                                }
                        } else if (Type.equals("RENEGADE")) {
                                for (String line : state) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString()))
                                                .replace("{state}", TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_REDSTONE_STATE_ACTIVE.getAsString()))
                                        );
                                }
                        } else if (Type.equals("WARMONGER")) {
                                for (String line : state) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString()))
                                                .replace("{state}", TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_REDSTONE_STATE_ACTIVE.getAsString()))
                                        );
                                }
                        } else if (Type.equals("SELFISH")) {
                                for (String line : state) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString()))
                                                .replace("{state}", TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_REDSTONE_STATE_ACTIVE.getAsString()))
                                        );
                                }
                        }

                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        this.inv.setItem(48, item);

                }

                {
                        item = new ItemStack(Material.BARRIER, 1);
                        meta = item.getItemMeta();
                        meta.setDisplayName(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_CLOSE_NAME.getAsString()));
                        item.setItemMeta(meta);
                        this.inv.setItem(49, item);

                }

                {
                        item = new ItemStack(Material.REDSTONE_TORCH_ON, 1);
                        meta = item.getItemMeta();
                        meta.setDisplayName(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_INFORMATION_NAME.getAsString()));
                        List<String> itemlore = Configuration.CHALLENGE_MENU_ITEM_INFORMATION_LORE.getAsStringList();
                        List<String> lore = new ArrayList<>();
                        if (Type.equals("NONE")) {
                                for (String line : itemlore) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_NONE_NAME.getAsString()))
                                        );
                                }
                        } else if (Type.equals("RENEGADE")) {
                                for (String line : itemlore) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString()))
                                        );
                                }
                        } else if (Type.equals("WARMONGER")) {
                                for (String line : itemlore) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString()))
                                        );
                                }
                        } else if (Type.equals("SELFISH")) {
                                for (String line : itemlore) {
                                        lore.add(TextUtil.color(line)
                                                .replace("{challenge}", TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString()))
                                        );
                                }
                        }
                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        this.inv.setItem(50, item);

                }

                {
                        item = new ItemStack(351, 1, (short) 8);
                        meta = item.getItemMeta();
                        meta.setDisplayName(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_LOCKED_NAME.getAsString()));

                        List<String> itemlore = Configuration.CHALLENGE_MENU_ITEM_LOCKED_LORE.getAsStringList();
                        List<String> lore = new ArrayList<>();

                        for (String line : itemlore) {
                                lore.add(TextUtil.color(line));
                        }

                        meta.setLore(lore);
                        item.setItemMeta(meta);
                        this.inv.setItem(13, item);
                        this.inv.setItem(14, item);
                        this.inv.setItem(15, item);
                        this.inv.setItem(16, item);

                }


                player.openInventory(this.inv);
        }

        @EventHandler
        public void onClick(InventoryClickEvent e) {
                Player player = (Player) e.getWhoClicked();
                String Type = new MySQL().getData(player.getUniqueId());
                if (e.getCurrentItem() == null) {
                        return;
                }
                if (e.getCurrentItem().getItemMeta() == null) {
                        return;
                }
                if (e.getCurrentItem().getItemMeta().getDisplayName() == null) {
                        return;
                }
                if (!e.getView().getTitle().equalsIgnoreCase(Configuration.CHALLENGE_MENU_TITLE.getAsString())) {
                        return;
                }

                //RENEGADE
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString()))) {
                        if (!Type.equals("RENEGADE")) {
                                Challenge.getSql().setData(player.getUniqueId(), "RENEGADE");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_ACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString())));
                                e.setCancelled(true);
                        } else {
                                Challenge.getSql().setData(player.getUniqueId(), "NONE");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_DEACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString())));
                                e.setCancelled(true);
                        }
                }
                //WARMONGER
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString()))) {
                        if (!Type.equals("WARMONGER")) {
                                Challenge.getSql().setData(player.getUniqueId(), "WARMONGER");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_ACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString())));
                                e.setCancelled(true);
                        } else {
                                Challenge.getSql().setData(player.getUniqueId(), "NONE");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_DEACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString())));
                                e.setCancelled(true);
                        }
                }
                //SELFISH
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(color + Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString()))) {
                        if (!Type.equals("SELFISH")) {
                                Challenge.getSql().setData(player.getUniqueId(), "SELFISH");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_ACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString())));
                                e.setCancelled(true);
                        } else {
                                Challenge.getSql().setData(player.getUniqueId(), "NONE");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_DEACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString())));
                                e.setCancelled(true);
                        }
                }
                //REDSTONE-BLOCK
                if (e.getCurrentItem().getItemMeta().getDisplayName().contains(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_REDSTONE_NAME.getAsString()))) {
                        switch (Type) {
                                case "RENEGADE":
                                        player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_DEACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString())));
                                        break;
                                case "WARMONGER":
                                        player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_DEACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString())));
                                        break;
                                case "SELFISH":
                                        player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_DEACTIVATED.getAsString().replace("{challenge}",Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString())));
                                        break;
                        }
                                Challenge.getSql().setData(player.getUniqueId(), "NONE");
                                this.openMenu(player);
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 1, 1);
                                e.setCancelled(true);
                }
                //CLOSE
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_CLOSE_NAME.getAsString()))) {
                        player.closeInventory();
                        e.setCancelled(true);
                }
                //INFORMATION
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_INFORMATION_NAME.getAsString()))) {
                        e.setCancelled(true);
                }
                //LOCKED
                if (e.getCurrentItem().getItemMeta().getDisplayName().equals(TextUtil.color(Configuration.CHALLENGE_MENU_ITEM_LOCKED_NAME.getAsString()))) {
                        player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                        player.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_NOT_UNLOCKED.getAsString()));
                        e.setCancelled(true);
                }

        }

}
