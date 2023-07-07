package fr.tikgames.jeux;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import fr.tikgames.main.Main;

public class CowClass extends MobClass {

    private final Main plugin;
    private boolean isSpecialAbilityInCooldown = false;

    public CowClass(Player player, Main plugin) {
        super(player, plugin);
        this.plugin = plugin;
    }

    @Override
    public void shootSnowball() {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVelocity(player.getLocation().getDirection().multiply(1.5)); // Multiply the velocity to increase speed
    }

    @Override
    public void specialAbility() {
        if (isSpecialAbilityInCooldown) {
            player.sendMessage("Your special ability is on cooldown!");
            return;
        }

        player.setVelocity(player.getLocation().getDirection().multiply(3));

        isSpecialAbilityInCooldown = true;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            isSpecialAbilityInCooldown = false;
        }, 200L);
    }
}
