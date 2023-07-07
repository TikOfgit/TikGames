package fr.tikgames.jeux;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;

import fr.tikgames.main.Main;

public class BlazeClass extends MobClass {

    private boolean isSpecialAbilityInCooldown = false;

    public BlazeClass(Player player, Main plugin) {
        super(player, plugin);
    }

    @Override
    public void shootSnowball() {
        Snowball snowball = player.launchProjectile(Snowball.class);
        snowball.setVelocity(player.getLocation().getDirection().multiply(2)); // Multipliez la vitesse pour augmenter la vitesse
    }

    @Override
    public void specialAbility() {
        if (isSpecialAbilityInCooldown) {
            player.sendMessage("Votre capacité spéciale est en cours de recharge !");
            return;
        }

        player.setAllowFlight(true);
        player.setFlying(true);

        isSpecialAbilityInCooldown = true;

        Bukkit.getScheduler().runTaskLater(plugin, () -> {
            isSpecialAbilityInCooldown = false;
            player.setFlying(false);
            player.setAllowFlight(false);
        }, 200L);
    }
}
