package fr.tikgames.jeux;

import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import fr.tikgames.main.Main;

public abstract class MobClass {
    protected Player player;
    protected JavaPlugin plugin;

    public MobClass(Player player, Main plugin) {
        this.player = player;
        this.plugin = plugin;
    }

    public abstract void shootSnowball();
    public abstract void specialAbility();
}
