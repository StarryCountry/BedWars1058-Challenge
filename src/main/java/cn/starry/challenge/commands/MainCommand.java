package cn.starry.challenge.commands;

import cn.starry.challenge.Challenge;
import cn.starry.challenge.config.Configuration;
import cn.starry.challenge.guis.ChallengeMenu;
import cn.starry.challenge.utils.TextUtil;
import com.andrei1058.bedwars.BedWars;
import com.andrei1058.bedwars.api.arena.IArena;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor {

    public boolean onCommand(final CommandSender s, final Command cmd, final String label, final String[] args) {
        if (!(s instanceof Player)) {
            s.sendMessage(TextUtil.color("&cYou must be a player to execute this command!"));
            return true;
        }
        Player p = (Player) s;
            if (cmd.getName().equalsIgnoreCase("challenge")) {
                if (!Challenge.ProxyMode) {
                    if (!BedWars.getAPI().getArenaUtil().isPlaying(p)) {
                        new ChallengeMenu().openMenu(p);
                        return true;
                    } else {
                        p.sendMessage(TextUtil.color(Configuration.CHALLENGE_MESSAGE_MENU_ARENA_OPEN.getAsString()));
                        return true;
                    }
                } else {
                    new ChallengeMenu().openMenu(p);
                    return true;
                }
            }
        return false;
    }
}
