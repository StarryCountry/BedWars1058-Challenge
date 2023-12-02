package cn.starry.challenge.listeners;

import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.database.MySQL;
import cn.starry.challenge.utils.TextUtil;
import com.andrei1058.bedwars.api.events.player.PlayerJoinArenaEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class ArenaListener implements Listener {

    @EventHandler
    public void onArenaJoin (PlayerJoinArenaEvent event) {
        Player player = event.getPlayer();
        String Type = new MySQL().getData(player.getUniqueId());
        if (!Type.equals("NONE")) {
            List<String> top = Configuration.CHALLENGE_MESSAGE_ARENA_TOP.getAsStringList();
            List<String> bottom = Configuration.CHALLENGE_MESSAGE_ARENA_BOTTOM.getAsStringList();
            List<String> intro = Configuration.CHALLENGE_MENU_ITEM_INTRO.getAsStringList();
            if (Type.equals("RENEGADE")) {
                String Challenge = Configuration.CHALLENGE_MENU_ITEM_RENEGADE_NAME.getAsString();
                List<String> Rules = Configuration.CHALLENGE_MENU_ITEM_RENEGADE_RULES.getAsStringList();
                for (String line : top) {
                    player.sendMessage(TextUtil.color(line)
                            .replace("{challenge}", Challenge)
                    );
                }
                player.sendMessage("");
                for (String line : Rules) {
                    player.sendMessage(TextUtil.color(line));
                }
                player.sendMessage("");
                for (String line : intro) {
                    player.sendMessage(TextUtil.color(line));
                }
                player.sendMessage("");
                for (String line : bottom) {
                    player.sendMessage(TextUtil.color(line));
                }
            } else if (Type.equals("WARMONGER")) {
                String Challenge = Configuration.CHALLENGE_MENU_ITEM_WARMONGER_NAME.getAsString();
                List<String> Rules = Configuration.CHALLENGE_MENU_ITEM_WARMONGER_RULES.getAsStringList();
                for (String line : top) {
                    player.sendMessage(TextUtil.color(line)
                            .replace("{challenge}", Challenge)
                    );
                }
                player.sendMessage("");
                for (String line : Rules) {
                    player.sendMessage(TextUtil.color(line));
                }
                player.sendMessage("");
                for (String line : intro) {
                    player.sendMessage(TextUtil.color(line));
                }
                player.sendMessage("");
                for (String line : bottom) {
                    player.sendMessage(TextUtil.color(line));
                }
            } else if (Type.equals("SELFISH")) {
                String Challenge = Configuration.CHALLENGE_MENU_ITEM_SELFISH_NAME.getAsString();
                List<String> Rules = Configuration.CHALLENGE_MENU_ITEM_SELFISH_RULES.getAsStringList();
                for (String line : top) {
                    player.sendMessage(TextUtil.color(line)
                            .replace("{challenge}", Challenge)
                    );
                }
                player.sendMessage("");
                for (String line : Rules) {
                    player.sendMessage(TextUtil.color(line));
                }
                player.sendMessage("");
                for (String line : intro) {
                    player.sendMessage(TextUtil.color(line));
                }
                player.sendMessage("");
                for (String line : bottom) {
                    player.sendMessage(TextUtil.color(line));
                }
            }
        }
    }

}
