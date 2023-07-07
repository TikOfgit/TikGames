package fr.tikgames.jeux;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import fr.tikgames.main.Main;

public class IronGolemClass extends MobClass {

    private final Main plugin;
    private boolean isSpecialAbilityInCooldown = false;

    public IronGolemClass(Player player, Main plugin) {
        super(player, plugin);
        this.plugin = plugin;
    }

    @Override
    public void shootSnowball() {
        for (int i = 0; i < 3; i++) {
            Snowball snowball = player.launchProjectile(Snowball.class);
            snowball.setVelocity(player.getLocation().getDirection().multiply(1)); // Multiply the velocity to increase speed
        }
    }

    @Override
    public void specialAbility() {
        
        if (isSpecialAbilityInCooldown) {
            player.sendMessage("Your special ability is on cooldown!");
            return;
        }

        Location playerLoc = player.getLocation(); // Get player's location
        for (int x = -2; x <= 2; x++) {
            for (int y = 0; y <= 2; y++) {
                Block block = player.getWorld().getBlockAt(playerLoc.add(x, y, 0));
                block.setType(Material.BRICK); // Create brick wall
            }
        }

        isSpecialAbilityInCooldown = true;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            isSpecialAbilityInCooldown = false;
        }, 600L);
    }
}
