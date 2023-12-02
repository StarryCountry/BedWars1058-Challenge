package cn.starry.challenge.config;

import cn.starry.challenge.Challenge;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.List;

public enum Configuration {

    //DataBase
    DATABASE_HOST("database.host"),
    DATABASE_PORT("database.port"),
    DATABASE_DATABASE("database.database"),
    DATABASE_USERNAME("database.username"),
    DATABASE_PASSWORD("database.password"),
    //Selector
    CHALLENGE_SELECTOR_TITLE("challenge.selector.title"),
    CHALLENGE_SELECTOR_ITEM_SLOT("challenge.selector.item.slot"),
    CHALLENGE_SELECTOR_ITEM_MATERIAL("challenge.selector.item.material"),
    CHALLENGE_SELECTOR_ITEM_NAME("challenge.selector.item.name"),
    CHALLENGE_SELECTOR_ITEM_LORE("challenge.selector.item.lore"),
    //Menu - Public Settings
    CHALLENGE_MENU_SIZE("challenge.menu.size"),
    CHALLENGE_MENU_TITLE("challenge.menu.title"),
    CHALLENGE_MENU_ITEM_COLOR("challenge.menu.item.color"),
    CHALLENGE_MENU_ITEM_CATEGORY("challenge.menu.item.category"),
    CHALLENGE_MENU_ITEM_INTRO("challenge.menu.item.intro"),
    CHALLENGE_MENU_ITEM_STATUS_ACTIVATED("challenge.menu.item.status.activated"),
    CHALLENGE_MENU_ITEM_STATUS_DEACTIVATED("challenge.menu.item.status.deactivated"),
    //Menu - Challenge Items
    CHALLENGE_MENU_ITEM_NONE_NAME("challenge.menu.item.none.name"),
    CHALLENGE_MENU_ITEM_RENEGADE_NAME("challenge.menu.item.renegade.name"),
    CHALLENGE_MENU_ITEM_RENEGADE_RULES("challenge.menu.item.renegade.rules"),
    CHALLENGE_MENU_ITEM_WARMONGER_NAME("challenge.menu.item.warmonger.name"),
    CHALLENGE_MENU_ITEM_WARMONGER_RULES("challenge.menu.item.warmonger.rules"),
    CHALLENGE_MENU_ITEM_SELFISH_NAME("challenge.menu.item.selfish.name"),
    CHALLENGE_MENU_ITEM_SELFISH_RULES("challenge.menu.item.selfish.rules"),
    //Menu - Locked
    CHALLENGE_MENU_ITEM_LOCKED_NAME("challenge.menu.item.locked.name"),
    CHALLENGE_MENU_ITEM_LOCKED_LORE("challenge.menu.item.locked.lore"),
    //Menu - RedStone
    CHALLENGE_MENU_ITEM_REDSTONE_COLOR_ACTIVE("challenge.menu.item.redstone.active-color"),
    CHALLENGE_MENU_ITEM_REDSTONE_COLOR_DEACTIVATE("challenge.menu.item.redstone.deactivate-color"),
    CHALLENGE_MENU_ITEM_REDSTONE_STATE_ACTIVE("challenge.menu.item.redstone.state.active"),
    CHALLENGE_MENU_ITEM_REDSTONE_STATE_DEACTIVATE("challenge.menu.item.redstone.state.deactivate"),
    CHALLENGE_MENU_ITEM_REDSTONE_NAME("challenge.menu.item.redstone.name"),
    CHALLENGE_MENU_ITEM_REDSTONE_LORE("challenge.menu.item.redstone.lore"),
    //Menu - Information
    CHALLENGE_MENU_ITEM_INFORMATION_NAME("challenge.menu.item.information.name"),
    CHALLENGE_MENU_ITEM_INFORMATION_LORE("challenge.menu.item.information.lore"),
    //Menu - Close
    CHALLENGE_MENU_ITEM_CLOSE_NAME("challenge.menu.item.close.name"),
    //Arena - Messages
    CHALLENGE_MESSAGE_MENU_ACTIVATED("challenge.messages.menu.activated"),
    CHALLENGE_MESSAGE_MENU_DEACTIVATED("challenge.messages.menu.deactivated"),
    CHALLENGE_MESSAGE_MENU_ARENA_OPEN("challenge.messages.menu.arena-open"),
    CHALLENGE_MESSAGE_MENU_NOT_UNLOCKED("challenge.messages.menu.not-unlocked"),
    CHALLENGE_MESSAGE_ARENA_TOP("challenge.messages.arena-top"),
    CHALLENGE_MESSAGE_ARENA_BOTTOM("challenge.messages.arena-bottom"),
    CHALLENGE_MESSAGE_CHALLENGE_RENEGADE_OPENUPGRADE("challenge.messages.challenges.renegade.open_upgrade"),
    CHALLENGE_MESSAGE_CHALLENGE_WARMONGER_BUYITEM("challenge.messages.challenges.warmonger.buy_item"),
    CHALLENGE_MESSAGE_CHALLENGE_SELFISH_OEPNCHEST("challenge.messages.challenges.selfish.open_chests"),
    CHALLENGE_MESSAGE_CHALLENGE_SELFISH_DROPITEM("challenge.messages.challenges.selfish.drop_item");


    private static final YamlConfiguration config = Challenge.getInstance().getFileManager().getConfig();
    private final String path;

    public String getAsString() {
        return config.getString(this.path);
    }

    public int getAsInt() {
        return config.getInt(this.path);
    }

    public double getAsDouble() {
        return config.getDouble(this.path);
    }

    public List<String> getAsStringList() {
        return config.getStringList(this.path);
    }

    public boolean getAsBoolean() {
        return config.getBoolean(this.path);
    }

    public ConfigurationSection getAsConfigSection() {
        return config.getConfigurationSection(this.path);
    }

    private Configuration(String path) {
        this.path = path;
    }

}
