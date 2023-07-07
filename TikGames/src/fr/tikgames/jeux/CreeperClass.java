package fr.tikgames.jeux;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import fr.tikgames.main.Main;

public class CreeperClass extends MobClass {

    private final Main plugin;
    private boolean isSpecialAbilityInCooldown = false;

    public CreeperClass(Player player, Main plugin) {
        super(player, plugin);
        this.plugin = plugin;
    }

    @Override
    public void shootSnowball() {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVelocity(player.getLocation().getDirection().multiply(1)); // Multiply the velocity to increase speed
    }

    @Override
    public void specialAbility() {

        if (isSpecialAbilityInCooldown) {
            player.sendMessage("Your special ability is on cooldown!");
            return;
        }

        player.getWorld().createExplosion(player.getLocation(), 2.0F);

        isSpecialAbilityInCooldown = true;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            isSpecialAbilityInCooldown = false;
        }, 400L);
    }
}
